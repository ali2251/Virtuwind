package eu.virtuwind.resourcemonitor.impl;


import com.google.common.base.Optional;
import com.google.common.util.concurrent.CheckedFuture;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.md.sal.binding.api.ReadOnlyTransaction;
import org.opendaylight.controller.md.sal.common.api.data.LogicalDatastoreType;
import org.opendaylight.controller.md.sal.common.api.data.ReadFailedException;
import org.opendaylight.openflowjava.protocol.api.connection.ThreadConfiguration;
import org.opendaylight.yang.gen.v1.urn.opendaylight.address.tracker.rev140617.AddressCapableNodeConnector;
import org.opendaylight.yang.gen.v1.urn.opendaylight.address.tracker.rev140617.address.node.connector.Addresses;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.FlowCapableNode;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.FlowCapableNodeConnector;
import org.opendaylight.yang.gen.v1.urn.opendaylight.host.tracker.rev140624.HostNode;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.Nodes;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.node.NodeConnector;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.service.rev130709.PacketProcessingService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.port.statistics.rev131214.FlowCapableNodeConnectorStatistics;
import org.opendaylight.yang.gen.v1.urn.opendaylight.port.statistics.rev131214.FlowCapableNodeConnectorStatisticsData;
import org.opendaylight.yang.gen.v1.urn.opendaylight.port.statistics.rev131214.flow.capable.node.connector.statistics.FlowCapableNodeConnectorStatisticsBuilder;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.NetworkTopology;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.TopologyId;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.Topology;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.TopologyKey;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.Link;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.Node;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeId;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.NodeKey;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
//import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.*;
//import org.opendaylight.yangtools.yang.binding.DataContainer;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Class to provode the static methods to get the Nodes and Links
 * As well as providing a translation from an IP address to a NodeID
 */

public class ResourceMonitor implements ResourcemonitorService {

    private static final Logger LOG = LoggerFactory.getLogger(ResourceMonitor.class);

    private static DataBroker dataBroker;

    private static ResourceMonitor resourceMonitor;

    private static LatencyMonitor latencyMonitor;

    public static final Integer NUM_OF_QUEUES = 4;
    public static final Long QUEUE_SIZE = 30000L; //in bytes

    /**
     * Constructor to construct an instance of ResourceMonitor
     * @param db - the databroker for getting the data
     * @param latencyMonitor1 - the latency monitor to be used
     */
    public ResourceMonitor(DataBroker db, LatencyMonitor latencyMonitor1) {
        dataBroker = db;
        latencyMonitor = latencyMonitor1;
        resourceMonitor = this;
    }

    /**
     * Method to get the instance for ResourceMonitor
     * @return ResourceMonitor instance
     */
    public static ResourceMonitor getInstance() {

        return resourceMonitor;
    }

    /**
     * RPC method to get all stats
     * @return the links with statistics
     */
    public Future<RpcResult<GetStatsOutput>> getStats() {

        GetStatsOutputBuilder output = new GetStatsOutputBuilder();
        output.setStats(getAllLinksWithQos().toString());
        return RpcResultBuilder.success(output.build()).buildFuture();
        //setStats

    }

    /**
     * RPC method to get number of packets traversed through a link
     * @param input - the link id
     * @return Total number of packets traversing through the link
     */
    public Future<RpcResult<GetNumberOfPacketsOutput>> getNumberOfPackets(org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsInput input) {
        String nodeConnectorId = input.getNodeConnectorId();
        GetNumberOfPacketsOutputBuilder output = new GetNumberOfPacketsOutputBuilder();
        NumberOfPackets nOfP = getNumberOfPackets(nodeConnectorId);
        output.setTransmitted(new BigDecimal(nOfP.getPacketsTransmitted()));
        output.setReceived(new BigDecimal(nOfP.getPacketsReceived()));
        return RpcResultBuilder.success(output.build()).buildFuture();

    }


    /**
     * Recieve DataBroker from Path-Manager
     * and return all nodes from Topology.
     * Procedure is as follows:
     * 1. Make a TopologyId with value flow:1
     * 2. Build the Instance Identifier of the Topology
     * 3. Read the transaction from the DataBroker
     * 4. Get the CheckedFuture Of the topology from the Operational Store
     * 5. Return the Nodes if found else empty list
     *
     * @param db - {@link DataBroker} DataBroker to extract data fromn md-sal
     * @return List<Node> {@link List<Node>} - list of nodes found in the topoology
     */
    public static List<Node> getAllNodes(DataBroker db) {
        List<Node> nodeList = new ArrayList<>();

        try {
            //Topology Id
            TopologyId topoId = new TopologyId("flow:1");
            //get the InstanceIdentifier
            InstanceIdentifier<Topology> nodesIid = InstanceIdentifier.builder(NetworkTopology.class).child(Topology.class, new TopologyKey(topoId)).toInstance();
            ReadOnlyTransaction nodesTransaction = db.newReadOnlyTransaction();

            //Read from operational database
            CheckedFuture<Optional<Topology>, ReadFailedException> nodesFuture = nodesTransaction
                    .read(LogicalDatastoreType.OPERATIONAL, nodesIid);
            Optional<Topology> nodesOptional = nodesFuture.checkedGet();

            if (nodesOptional != null && nodesOptional.isPresent()) {
                nodeList = nodesOptional.get().getNode();
            }
            return nodeList;
        } catch (Exception e) {
            LOG.info("Node Fetching Failed");
            return nodeList;
        }

    }

    /**
     * Method to get number of packets transmitted and received of the node connector
     * @param nodeConnectorId - the node connector id for which to get the stats
     * @return Number of packets transmitted and received
     */
    public static NumberOfPackets getNumberOfPackets(String nodeConnectorId) {

        try {
            List<org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node> nodeList = getNodes(dataBroker);

            for (org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node node: nodeList) {

                List<NodeConnector> ncList  = node.getNodeConnector();

                for(NodeConnector nc: ncList) {

                    if(nc.getId().getValue().equals(nodeConnectorId)) {

                        FlowCapableNodeConnectorStatisticsData statData = nc
                                .getAugmentation(FlowCapableNodeConnectorStatisticsData.class);

                        org.opendaylight.yang.gen.v1.urn.opendaylight.port.statistics.rev131214.flow.capable.node.connector.statistics.FlowCapableNodeConnectorStatistics statistics = statData
                                .getFlowCapableNodeConnectorStatistics();

                        BigInteger received = statistics.getPackets().getReceived();
                        BigInteger transmitted  = statistics.getPackets().getTransmitted();

                        if(received == null || transmitted == null ) {
                            return null;
                        } else {
                            return new NumberOfPackets(transmitted, received);
                        }

                    }



                }


            }

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }

        return null;

    }


    /**
     * Method to get all links with their statistics including bandwidth, jitter, packet loss, latency and throughput
     * @return links with their statistics
     */
    public List<ResMonitorLink> getAllLinksWithQos() {

        try {

            HashMap<NodeConnector, Link> linksToNodeconnectorMap = new HashMap<NodeConnector, Link>();
            List<NodeConnector> nodeConnectorList = new ArrayList<>();

            List<ResMonitorLink> linksToReturn = new ArrayList<>();

            List<Link> links = getAllLinks(dataBroker);

            List<org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node> nodeList = getNodes(dataBroker);


            HashMap<NodeConnector, BigInteger> throughputHashMap = new HashMap<NodeConnector, BigInteger>();

            List<BigInteger> list1 = new ArrayList<>();
            List<BigInteger> list2 = new ArrayList<>();


            if (links != null) {

                LOG.info("Link size is " + links.size());

                for (Link link : links) {
                    String nodeToFind = link.getSource().getSourceNode().getValue();
                    String outputNodeConnector = link.getSource().getSourceTp().getValue();

                    for (org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node node : nodeList) {


                        if (node.getId().getValue().equals(nodeToFind)) {

                            List<NodeConnector> nodeConnectors = node.getNodeConnector();

                            for (NodeConnector nc : nodeConnectors) {

                                if (nc.getId().getValue().equals(outputNodeConnector)) {

                                    linksToNodeconnectorMap.put(nc, link);
                                    nodeConnectorList.add(nc);


                                    FlowCapableNodeConnectorStatisticsData statData = nc
                                            .getAugmentation(FlowCapableNodeConnectorStatisticsData.class);
                                    org.opendaylight.yang.gen.v1.urn.opendaylight.port.statistics.rev131214.flow.capable.node.connector.statistics.FlowCapableNodeConnectorStatistics statistics = statData
                                            .getFlowCapableNodeConnectorStatistics();


                                    list1.add(statistics.getBytes().getTransmitted());

                                }

                            }

                        }
                    }


                }


                Thread.sleep(1_000);

                List<org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node> nodeListLocal = getNodes(dataBroker);


                for (Link link : links) {
                    String nodeToFind = link.getSource().getSourceNode().getValue();
                    String outputNodeConnector = link.getSource().getSourceTp().getValue();

                    for (org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node node : nodeListLocal) {


                        if (node.getId().getValue().equals(nodeToFind)) {

                            List<NodeConnector> nodeConnectors = node.getNodeConnector();

                            for (NodeConnector nc : nodeConnectors) {

                                if (nc.getId().getValue().equals(outputNodeConnector)) {


                                    FlowCapableNodeConnectorStatisticsData statData = nc
                                            .getAugmentation(FlowCapableNodeConnectorStatisticsData.class);
                                    org.opendaylight.yang.gen.v1.urn.opendaylight.port.statistics.rev131214.flow.capable.node.connector.statistics.FlowCapableNodeConnectorStatistics statistics = statData
                                            .getFlowCapableNodeConnectorStatistics();


                                    list2.add(statistics.getBytes().getTransmitted());

                                }

                            }

                        }
                    }

                }


                for (int i = 0; i < list1.size(); ++i) {


                    FlowCapableNodeConnectorStatisticsData statData = nodeConnectorList.get(i)
                            .getAugmentation(FlowCapableNodeConnectorStatisticsData.class);
                    org.opendaylight.yang.gen.v1.urn.opendaylight.port.statistics.rev131214.flow.capable.node.connector.statistics.FlowCapableNodeConnectorStatistics statistics = statData
                            .getFlowCapableNodeConnectorStatistics();
                    BigInteger packetsTransmitted = BigInteger.ZERO;
                    BigInteger packetErrorsTransmitted = BigInteger.ZERO;
                    if (statistics != null && statistics.getPackets() != null) {
                        packetsTransmitted = statistics.getPackets().getTransmitted();
                        packetErrorsTransmitted = statistics.getTransmitErrors();

                    }
                    Float packetLoss = (packetsTransmitted.floatValue() == 0) ? 0
                            : packetErrorsTransmitted.floatValue()
                            / packetsTransmitted.floatValue();

                    BigInteger throughput = BigInteger.ZERO;


                    throughput = list2.get(i).subtract(list1.get(i));

                    //throughput = throughput.divide(BigInteger.TEN);


                    FlowCapableNodeConnector fcnc = nodeConnectorList.get(i).getAugmentation(FlowCapableNodeConnector.class);


                    Link link = linksToNodeconnectorMap.get(nodeConnectorList.get(i));

                    if (!(link.getLinkId().getValue().contains("host"))) {


                        LatencyJitterWrapper latencyJitterWrapper = latencyMonitor.MeasureNextLink(link);
                        Long latency = latencyJitterWrapper.getLatency();
                        Long jitter = latencyJitterWrapper.getJitter();

                        linksToReturn.add(new ResMonitorLink(fcnc.getCurrentSpeed(), packetLoss.longValue(), latency, jitter, throughput.longValue(), link));


                    } else {
                        linksToReturn.add(new ResMonitorLink(fcnc.getCurrentSpeed(), packetLoss.longValue(), -1L, -1L, throughput.longValue(), link));

                    }


                }


                return linksToReturn;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Recieve DataBroker from Path-Manager
     * and return all links fetched from Topology.
     * Procedure is as follows:
     * 1. Make a TopologyId with value flow:1
     * 2. Build the Instance Identifier of the Topology
     * 3. Read the transaction from the DataBroker
     * 4. Get the CheckedFuture Of the topology from the Operational Store
     * 5. Return the Links if found else return empty list
     *
     * @param db - {@link DataBroker} DataBroker to extract data fromn md-sal
     * @return List<Link> - {@link List<Link>} list of links found in the topoology
     */

    /**
     *
     * @param db
     * @return
     */
    public static List<Link> getAllLinks(DataBroker db) {
        List<Link> linkList = new ArrayList<>();

        try {
            TopologyId topoId = new TopologyId("flow:1");
            InstanceIdentifier<Topology> nodesIid = InstanceIdentifier.builder(NetworkTopology.class).child(Topology.class, new TopologyKey(topoId)).toInstance();
            ReadOnlyTransaction nodesTransaction = db.newReadOnlyTransaction();
            CheckedFuture<Optional<Topology>, ReadFailedException> nodesFuture = nodesTransaction
                    .read(LogicalDatastoreType.OPERATIONAL, nodesIid);
            Optional<Topology> nodesOptional = nodesFuture.checkedGet();

            if (nodesOptional != null && nodesOptional.isPresent())
                linkList = nodesOptional.get().getLink();


            // LOG.info("Nodelist: " + nodeList);

            return linkList;
        } catch (Exception e) {

            LOG.info("Node Fetching Failed");

            return linkList;
        }

    }

    /**
     * Recieves an Ip address of the host or the switch and returns the respective NodeId
     * For Switches NodeID will be of the format openflow:1:2
     * For Hosts NodeID will be of the format host:00:00:00:00:00:01
     *
     * @param ipAddress {@link String} Ip address of the host/switch
     * @param db        {@link DataBroker} Data Broken to retrieve data from
     * @return NodeId  {@link String} NodeId of the host or switch
     */
    public static String getNodeFromIpAddress(String ipAddress, DataBroker db) {
        LOG.info("Finding node with IP address {}. ", ipAddress);
        try {

            List<Node> listofnode = getAllNodes(db);

            for (Node n : listofnode) {

                if (n.getNodeId().toString().contains("host")) {
                    HostNode hostNode = n.getAugmentation(HostNode.class);

                    if (hostNode.getAddresses().get(0).getIp().getIpv4Address().getValue().equals(ipAddress)) {

                        return n.getNodeId().getValue();
                    }

                }

            }


            List<org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node> nodeList = getNodes(db);

            if (nodeList != null) {
                for (org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node n : nodeList) {
                    List<NodeConnector> nodeConnectors = n.getNodeConnector();
                    for (NodeConnector nc : nodeConnectors) {

                        AddressCapableNodeConnector acnc = nc
                                .getAugmentation(AddressCapableNodeConnector.class);

                        FlowCapableNodeConnector fl = nc.getAugmentation(FlowCapableNodeConnector.class);
                        fl.getCurrentSpeed();


                        // FlowCapableNodeConnectorStatistics f = FlowCapableNodeConnectorStatisticsBuilder.


                        if (acnc != null && acnc.getAddresses() != null) {
                            // get address list from augmentation.
                            List<Addresses> addresses = acnc.getAddresses();
                            for (Addresses address : addresses) {
                                if (address.getIp().getIpv4Address().getValue()
                                        .equals(ipAddress))
                                    return n.getId().getValue();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOG.info("IP address reading failed");
        }
        return null;
    }

    /**
     * Method to get all nodes from the topology
     * @param db - databroker for which to read from the inventory
     * @return List of nodes
     * @throws ReadFailedException
     */
    public static List<org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node> getNodes(DataBroker db) throws ReadFailedException {
        List<org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node> nodeList = new ArrayList<>();
        InstanceIdentifier<Nodes> nodesIid = InstanceIdentifier.builder(
                Nodes.class).build();
        ReadOnlyTransaction nodesTransaction = db.newReadOnlyTransaction();
        CheckedFuture<Optional<Nodes>, ReadFailedException> nodesFuture = nodesTransaction
                .read(LogicalDatastoreType.OPERATIONAL, nodesIid);
        Optional<Nodes> nodesOptional = Optional.absent();
        nodesOptional = nodesFuture.checkedGet();

        if (nodesOptional != null && nodesOptional.isPresent()) {
            nodeList = nodesOptional.get().getNode();
        }
        return nodeList;
    }


}
