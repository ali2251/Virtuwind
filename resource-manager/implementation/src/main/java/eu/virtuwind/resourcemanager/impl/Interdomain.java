package eu.virtuwind.resourcemanager.impl;

import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.CheckedFuture;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.md.sal.binding.api.WriteTransaction;
import org.opendaylight.controller.md.sal.common.api.data.LogicalDatastoreType;
import org.opendaylight.controller.md.sal.common.api.data.OptimisticLockFailedException;
import org.opendaylight.controller.md.sal.common.api.data.TransactionCommitFailedException;
import org.opendaylight.openflowplugin.api.OFConstants;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Prefix;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.PortNumber;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Uri;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.action.*;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.action.group.action._case.GroupActionBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.action.output.action._case.OutputActionBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.action.pop.vlan.action._case.PopVlanActionBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.action.push.vlan.action._case.PushVlanActionBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.action.set.dl.dst.action._case.SetDlDstActionBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.action.set.field._case.SetFieldBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.action.set.queue.action._case.SetQueueActionBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.list.Action;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.list.ActionBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.FlowCapableNode;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.FlowId;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.tables.Table;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.tables.TableKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.tables.table.Flow;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.tables.table.FlowBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.tables.table.FlowKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.service.rev130819.*;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.FlowCookie;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.FlowModFlags;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.flow.Instructions;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.flow.InstructionsBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.flow.Match;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.flow.MatchBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.instruction.instruction.ApplyActionsCaseBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.instruction.instruction.MeterCaseBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.instruction.instruction.apply.actions._case.ApplyActions;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.instruction.instruction.apply.actions._case.ApplyActionsBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.instruction.instruction.meter._case.MeterBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.instruction.list.Instruction;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.instruction.list.InstructionBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.group.service.rev130918.*;
import org.opendaylight.yang.gen.v1.urn.opendaylight.group.types.rev131018.BucketId;
import org.opendaylight.yang.gen.v1.urn.opendaylight.group.types.rev131018.group.Buckets;
import org.opendaylight.yang.gen.v1.urn.opendaylight.group.types.rev131018.group.BucketsBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.group.types.rev131018.group.buckets.Bucket;
import org.opendaylight.yang.gen.v1.urn.opendaylight.group.types.rev131018.group.buckets.BucketBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.group.types.rev131018.group.buckets.BucketKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.group.types.rev131018.groups.Group;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeId;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeRef;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.Nodes;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.NodeKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.l2.types.rev130827.VlanId;


import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.match.VlanMatchBuilder;


import org.opendaylight.yang.gen.v1.urn.opendaylight.meter.service.rev130918.*;
import org.opendaylight.yang.gen.v1.urn.opendaylight.meter.types.rev130918.MeterId;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.ethernet.match.fields.EthernetTypeBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.match.EthernetMatchBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.match.IpMatchBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.match.ProtocolMatchFieldsBuilder;

import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.match.layer._3.match.Ipv4MatchBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.match.layer._4.match.TcpMatchBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.match.layer._4.match.UdpMatchBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.vlan.match.fields.VlanIdBuilder;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.TpId;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.link.attributes.DestinationBuilder;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.link.attributes.SourceBuilder;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.Link;
import org.opendaylight.yang.gen.v1.urn.opendaylight.l2.types.rev130827.EtherType;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.action.pop.mpls.action._case.PopMplsActionBuilder;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.LinkBuilder;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.action.push.mpls.action._case.PushMplsActionBuilder;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

import org.opendaylight.yang.gen.v1.urn.opendaylight.group.types.rev131018.GroupId;
import org.opendaylight.yang.gen.v1.urn.opendaylight.group.types.rev131018.GroupTypes;
import org.opendaylight.yang.gen.v1.urn.opendaylight.group.types.rev131018.groups.GroupBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.group.types.rev131018.groups.GroupKey;

/**
 * Created by Ali on 21/04/2017.
 */
public class Interdomain {


    private static final Integer DEFAULT_WEIGHT = new Integer(0);
    private static final Long OFPP_ANY = Long.parseLong("ffffffff", 16);
    private static final Long DEFAULT_WATCH_PORT = OFPP_ANY;
    private static final Long OFPG_ANY = Long.parseLong("ffffffff", 16);
    private static final Long DEFAULT_WATCH_GROUP = OFPG_ANY;

    private static final Integer VLAN_ETHERTYPE = 0x8100;


    //Logger for debugging
    private static final Logger LOG = LoggerFactory.getLogger(Interdomain.class);

    private static AtomicLong flowCookieInc = new AtomicLong(0x2a00000000000000L);

    private static HashMap<Long, List<EmbeddingFlowRequest>> IdToFlowMap = new HashMap<>();

    private static HashMap<Long, List<EmbeddingFlowRequest>> IdToListMap = new HashMap<>();

    private HashMap<Long, List<RemoveGroupInput>> mapOfIdToListOfRemovals = new HashMap<>();

    private HashMap<Long, RemoveGroupInput> mapOfIdToGroup = new HashMap<>();


    private static Long groupId = 0L;
    private static Long groupKey = 0L;

    private static final Integer MPLS_ETHERTYPE = 0x8847;
    private static final Integer IP_EHERTYPE = 0x0800;


    private List<AddGroupInput> listOfGroups = new ArrayList<>();
    private List<Long> listOfGroupIds = new ArrayList<>();


    private static Interdomain interdomain;

    private SalFlowService salFlowService;

    private SalMeterService salMeterService;

    private DataBroker dataBroker;

    private SalGroupService salGroupService;

    private static Long id = 0L;

    /**
     * Constructor for constructing a an Interdomain instance, should not be used and only get instance must be used
     *
     * @param salFlowServiceIn
     * @param salMeterServiceIn
     * @param dataBrokerIn
     * @param salGroupServiceIn
     */

    public Interdomain(SalFlowService salFlowServiceIn, SalMeterService salMeterServiceIn, DataBroker dataBrokerIn, SalGroupService salGroupServiceIn) {
        salFlowService = salFlowServiceIn;
        salMeterService = salMeterServiceIn;
        dataBroker = dataBrokerIn;
        salGroupService = salGroupServiceIn;
        interdomain = this;
    }

    private void createGroup() {

        List<Bucket> buckets = new ArrayList<>();

        //buckets.add(new BucketBuilder().setAction() .build())

        Group g = new GroupBuilder().setBuckets(new BucketsBuilder().build()).setGroupType(GroupTypes.GroupFf)
                .setBarrier(false).setGroupId(new GroupId(1L)).setGroupName("Foo")
                .build();


        Node nodeDpn = null;

        InstanceIdentifier<Group> groupInstanceId = InstanceIdentifier.builder(Nodes.class)
                .child(Node.class, nodeDpn.getKey()).augmentation(FlowCapableNode.class)
                .child(Group.class, new GroupKey(new GroupId(g.getGroupId()))).build();

        WriteTransaction modification = dataBroker.newWriteOnlyTransaction();

        modification.put(LogicalDatastoreType.CONFIGURATION, groupInstanceId, g, true);


        CheckedFuture<Void, TransactionCommitFailedException> submitFuture = modification.submit();


        Futures.addCallback(submitFuture, new FutureCallback<Void>() {

            @Override
            public void onSuccess(final Void result) {
                // Commited successfully
                // s_logger.debug( "Install Flow -- Committedsuccessfully ") ;
            }

            @Override
            public void onFailure(final Throwable t) {
                // Transaction failed

                if (t instanceof OptimisticLockFailedException) {
                    // Failed because of concurrent transaction modifying same data
                    //   s_logger.error( "Install Flow -- Failed because of concurrent transaction modifying same data ") ;
                } else {
                    // Some other type of TransactionCommitFailedException
                    // s_logger.error( "Install Flow -- Some other type of TransactionCommitFailedException " + t) ;
                }
            }
        });


    }


    /**
     * Method to get the instance of Interdomain (Recommended way of accessing the system)
     *
     * @return Interdomain instance.
     */
    public static Interdomain getInstance() {
        return interdomain;
    }

 /*   public void test() {


        Interdomain interdomain = Interdomain.getInstance();


        String destmac = "00:00:00:00:00:00"; //mac address of destination

        //Using the same MPLS label to remove all flows installed with the same MPLS.
        // removeMplsPath(1L);

        System.out.println("inside the test method");

        try {

            System.out.println("inside the test method    testttttt");

            Ipv4Prefix sourceIP = new Ipv4Prefix("192.168.0.1/32");
            Ipv4Prefix destinationIP = new Ipv4Prefix("192.168.0.2/32");


            // commented out, to be fixed after merge
            //Long serviceFlowID = interdomain.embedMplsFlow(sourceIP, destinationIP, 3, 4, 6, null, 4L, "openflow:2", 3);

            Link l = new LinkBuilder().setSource(new SourceBuilder()
                    .setSourceNode(new org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.NodeId("openflow:1"))
                    .setSourceTp(new TpId("openflow:1:2")).build())
                    .setDestination(new DestinationBuilder().
                            setDestNode(new org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.NodeId("openflow:3")).build()).build();

            ArrayList<Link> links = new ArrayList<>();
            links.add(l);

            // Using the MPLS Label 1 and a set of links and the queue 1.

            // commented out, to be fixed after merge
            // boolean success = interdomain.embedMplsPath(1L, links, 2L, 1, destmac); //indicates whether request was successful or not


            // commented out, to be fixed after merge
            //System.out.println("service flow id is " + serviceFlowID);

            //the ID returned by embedMPLSFlow, this will remove all service flows.
            // interdomain.removeFlow(serviceFlowID, "openflow:101");

        } catch (Exception e) {
            System.out.println("exception happened");
            e.printStackTrace();
        }


    }
*/


    /**
     * The method to install the flows over the core path
     *
     * @param mplsLabel              - the mpls label to be used
     * @param linksWithFailoverPorts - links with failover ports to be used for buckets in the group
     * @param resilientPathMplsLabel - resillient mpls label
     * @param queueId                - the queue id to be used for prioritisation
     * @return true if request is successful, false otherwise.
     */

    public boolean embedMplsPath(
            Long mplsLabel,
            List<LinkWithFailoverPort> linksWithFailoverPorts,
            Long resilientPathMplsLabel,
            Long queueId
    ) {

        try {

            LOG.info("-------------------------------------------------------------");
            LOG.info("embedMPLSPath Called and params are the following: ");
            LOG.info("MPLS Label is: " + mplsLabel);
            LOG.info("Links with Failover Ports: " + linksWithFailoverPorts.toString());
            LOG.info("Resillient Mpls label is: " + resilientPathMplsLabel);
            LOG.info("Queue ID is: " + queueId);
            LOG.info("-------------------------------------------------------------");

            List<EmbeddingFlowRequest> listofFlowRequests = new ArrayList<>();

            linksWithFailoverPorts.remove(linksWithFailoverPorts.get(0));

            List<Flow> flows = createFlows(linksWithFailoverPorts, queueId, mplsLabel, resilientPathMplsLabel);


            for (int i = 0; i < linksWithFailoverPorts.size(); i++) {


                //the flow which was created
                Flow createdFlow = flows.get(i);

                String nodeId = linksWithFailoverPorts.get(i).getLink().getSource().getSourceNode().getValue();
                //get the add input flow to add to sal

                listofFlowRequests.add(new EmbeddingFlowRequest(nodeId, createdFlow.getCookie().getValue()));

                AddFlowInput flow = getAddFlowInputToSend(nodeId, createdFlow);

                LOG.info("Flow: " + flow.toString());


                // add flow to sal
                Future<RpcResult<AddFlowOutput>> future = salFlowService.addFlow(flow);

                if (future.get().isSuccessful()) {
                    LOG.info("Flow successfully added");
                } else {
                    if (future.get().getErrors().size() > 0) {
                        LOG.error("Flow was not installed: " + future.get().getErrors().toString());
                        return false;
                    }

                }

            }

            IdToFlowMap.put(mplsLabel, listofFlowRequests);
            return true;

        } catch (Exception e) {
            LOG.error("an exception was thrown", e);
            return false;
        }

    }

    /**
     * Method used to embed a protection  path
     *
     * @param links                  - Links for which the path should be established
     * @param resilientPathMplsLabel - mpls label to be used
     * @param queueId                - the queue id to be used, allowed 0-7
     * @return true if successful, false otherwise
     */

    public boolean embedProtectionPath(
            List<Link> links,
            Long resilientPathMplsLabel,
            Long queueId) {

        if (links.size() > 1) {


            List<EmbeddingFlowRequest> listofFlowRequests = new ArrayList<>();

            links.remove(0);

            List<Flow> flows = createFlowsForProtectionPath(links, queueId, resilientPathMplsLabel);

            for (int i = 0; i < links.size(); i++) {

                //the flow which was created
                Flow createdFlow = flows.get(i);

                String nodeId = links.get(i).getSource().getSourceNode().getValue();
                //get the add input flow to add to sal

                listofFlowRequests.add(new EmbeddingFlowRequest(nodeId, createdFlow.getCookie().getValue()));

                AddFlowInput flow = getAddFlowInputToSend(nodeId, createdFlow);

                LOG.info("Flow: " + flow.toString());
                // add flow to sal
                Future<RpcResult<AddFlowOutput>> future = salFlowService.addFlow(flow);

                try {
                    if (future.get().isSuccessful()) {
                        LOG.info("Flow successfully added");
                    } else {
                        LOG.error("Flow was not installed: " + future.get().getErrors().toString());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }

            IdToFlowMap.put(resilientPathMplsLabel, listofFlowRequests);
            return true;
        } else {

            return true;
        }

    }

    /**
     * Method to create the flows for protection path
     *
     * @param physicalPathLinks - the links for which the flows should be created
     * @param queue             - the queue to be used, 0-7 allowed
     * @param mplsLabel         - mpls label to be used
     * @return List<Flow> List of flows
     */


    private List<Flow> createFlowsForProtectionPath(List<Link> physicalPathLinks, Long queue, Long mplsLabel) {
        FlowBuilder flowBuilder = new FlowBuilder()
                .setTableId((short) 0)
                .setFlowName("random");

        //Flow ID
        flowBuilder.setId(new FlowId(Long.toString(flowBuilder.hashCode())));

        // TcpMatchBuilder tcpMatchBuilder = new TcpMatchBuilder().setTcpSourcePort(new PortNumber(sourcePort)).setTcpDestinationPort(new PortNumber(destPort));
        //IpMatchBuilder ipMatchBuilder = new IpMatchBuilder().setIpProtocol(protocol);

        Match match = new MatchBuilder()
                .setEthernetMatch(new EthernetMatchBuilder().setEthernetType(new EthernetTypeBuilder().setType(new EtherType(0x8847L)).build()).build())
                .setProtocolMatchFields(new ProtocolMatchFieldsBuilder().setMplsLabel(mplsLabel).build())
                .build();


        ActionBuilder actionBuilder = new ActionBuilder();
        List<Action> actions = new ArrayList<Action>();


        List<List<Action>> listofactions = new ArrayList<>();

        for (int j = 0; j < physicalPathLinks.size(); ++j) {
            listofactions.add(new ArrayList<Action>());
        }
        for (int i = 0; i < physicalPathLinks.size(); i++) {


            Action queueAction = actionBuilder
                    .setOrder(0).setAction(new SetQueueActionCaseBuilder()
                            .setSetQueueAction(new SetQueueActionBuilder()
                                    .setQueueId(queue)
                                    .build())
                            .build())

                    .build();

            String outputNodeConnector = physicalPathLinks.get(i).getSource().getSourceTp().getValue().split(":")[2];
            LOG.info("setting OutputNodeConnector = " + outputNodeConnector);

            Action outputNodeConnectorAction = actionBuilder
                    .setOrder(1).setAction(new OutputActionCaseBuilder()
                            .setOutputAction(new OutputActionBuilder()
                                    .setOutputNodeConnector(new Uri(outputNodeConnector))
                                    .build())
                            .build())
                    .build();


            listofactions.get(i).add(queueAction);
            listofactions.get(i).add(outputNodeConnectorAction);

        }


        //ApplyActions

        List<Flow> flows = new ArrayList<>();
        for (int i = 0; i < listofactions.size(); i++) {

            ApplyActions applyActions = new ApplyActionsBuilder().setAction(listofactions.get(i)).build();

            //Instruction
            Instruction applyActionsInstruction = new InstructionBuilder() //
                    .setOrder(0).setInstruction(new ApplyActionsCaseBuilder()//
                            .setApplyActions(applyActions) //
                            .build())

                    .build();

            Instructions applyInstructions = new InstructionsBuilder()
                    .setInstruction(ImmutableList.of(applyActionsInstruction))
                    .build();


            flows.add(flowBuilder
                    .setMatch(match)
                    .setBufferId(OFConstants.OFP_NO_BUFFER)
                    .setInstructions(applyInstructions)
                    .setPriority(1000)
                    .setCookie(new FlowCookie(BigInteger.valueOf(flowCookieInc.getAndIncrement())))
                    .setFlags(new FlowModFlags(false, false, false, false, false)).build());

        }
        return flows;
    }

    /**
     * Method to create the addFlowInput for it to be added to sal flow service
     *
     * @param edge_switch {@link String} - the switch for the flow to be added on
     * @param createdFlow {@link Flow} - created flow to be added
     * @return {@link AddFlowInput} - addflowinput to be added to salflow
     */
    private AddFlowInput getAddFlowInputToSend(String edge_switch, Flow createdFlow) {
        InstanceIdentifier<Flow> flowPath = InstanceIdentifier
                .builder(Nodes.class)
                .child(Node.class, new NodeKey(new NodeId(edge_switch)))
                .augmentation(FlowCapableNode.class)
                .child(Table.class, new TableKey(createdFlow.getTableId()))
                .child(Flow.class, new FlowKey(createdFlow.getId())).build();


        final AddFlowInputBuilder builder = new AddFlowInputBuilder(createdFlow);
        final InstanceIdentifier<Table> tableInstanceId = flowPath
                .<Table>firstIdentifierOf(Table.class);
        final InstanceIdentifier<Node> nodeInstanceId = flowPath
                .<Node>firstIdentifierOf(Node.class);
        builder.setNode(new NodeRef(nodeInstanceId));
        builder.setFlowTable(new FlowTableRef(tableInstanceId));
        builder.setTransactionUri(new Uri(createdFlow.getId().getValue()));
        return builder.build();
    }


    /**
     * Method to create flows
     *
     * @param physicalPathLinks   - the links for which the flows should be created
     * @param queue               - the queue to be used, 0-7 allowed
     * @param mplsLabel           - mpls label to be used
     * @param resillientMPLSLabel - resillient mpls label
     * @return List<Flows> the created flows
     */
    private List<Flow> createFlows(List<LinkWithFailoverPort> physicalPathLinks, Long queue, Long mplsLabel, Long resillientMPLSLabel

    ) {

        HashMap<String, Long> switchToGroupMap = new HashMap<>();


        List<RemoveGroupInput> removeGroupInputList = new ArrayList<>();

        FlowBuilder flowBuilder = new FlowBuilder()
                .setTableId((short) 0)
                .setFlowName("random");

        //Flow ID
        flowBuilder.setId(new FlowId(Long.toString(flowBuilder.hashCode())));


        Match match = new MatchBuilder()
                .setEthernetMatch(new EthernetMatchBuilder().setEthernetType(new EthernetTypeBuilder().setType(new EtherType(0x8847L)).build()).build())
                .setProtocolMatchFields(new ProtocolMatchFieldsBuilder().setMplsLabel(mplsLabel).build())
                .build();


        ActionBuilder actionBuilder = new ActionBuilder();


        List<List<Action>> listofactions = new ArrayList<>();

        for (int j = 0; j < physicalPathLinks.size(); ++j) {
            listofactions.add(new ArrayList<Action>());
        }
        for (int i = 0; i < physicalPathLinks.size(); i++) {


            String outputNodeConnector = physicalPathLinks.get(i).getLink().getSource().getSourceTp().getValue().split(":")[2];
            String switchId = physicalPathLinks.get(i).getLink().getSource().getSourceNode().getValue();


            AddGroupInput addGroupInput = getFFGroup(Integer.parseInt(outputNodeConnector),
                    resillientMPLSLabel, physicalPathLinks.get(i).getFailoverPort(), switchId, queue);

            RemoveGroupInput removeGroupInput = new RemoveGroupInputBuilder()
                    .setGroupId(addGroupInput.getGroupId())
                    .setGroupName(addGroupInput.getGroupName())
                    .setGroupRef(addGroupInput.getGroupRef())
                    .setGroupType(addGroupInput.getGroupType())
                    .setBarrier(addGroupInput.isBarrier())
                    .setNode(addGroupInput.getNode())
                    .setBuckets(addGroupInput.getBuckets())
                    .build();

            removeGroupInputList.add(removeGroupInput);


            switchToGroupMap.put(switchId, addGroupInput.getGroupId().getValue());

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Future<RpcResult<AddGroupOutput>> future = salGroupService.addGroup(addGroupInput);


            try {
                if (future.isDone() && future.get().isSuccessful()) {
                    LOG.info("FF Group successfully installed");

                } else {
                    LOG.info("FF Group not installed and error is: " + future.get().getErrors());
                    LOG.error("FF Group not installed and error is: " + future.get().getErrors());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        }

        mapOfIdToListOfRemovals.put(mplsLabel, removeGroupInputList);

        LOG.info("All group ids are: " + listOfGroupIds.toString());

        //ApplyActions

        List<Flow> flows = new ArrayList<>();
        for (int i = 0; i < physicalPathLinks.size(); i++) {


            List<Action> actions = new ArrayList<Action>();

            String switchId = physicalPathLinks.get(i).getLink().getSource().getSourceNode().getValue();


            Long groupId1 = switchToGroupMap.get(switchId);
            LOG.info("Group : " + groupId1 + "   node : " + switchId);


            Action groupAction = new ActionBuilder()
                    .setOrder(0).setAction(new GroupActionCaseBuilder()
                            .setGroupAction(new GroupActionBuilder()
                                    .setGroupId(groupId1)
                                    .build())
                            .build())
                    .build();
            actions.add(groupAction);

            ApplyActions applyActions = new ApplyActionsBuilder().setAction(actions).build();

            //Instruction
            Instruction applyActionsInstruction = new InstructionBuilder() //
                    .setOrder(0).setInstruction(new ApplyActionsCaseBuilder()//
                            .setApplyActions(applyActions) //
                            .build())

                    .build();

            Instructions applyInstructions = new InstructionsBuilder()
                    .setInstruction(ImmutableList.of(applyActionsInstruction))
                    .build();


            flows.add(flowBuilder
                    .setMatch(match)
                    .setBufferId(OFConstants.OFP_NO_BUFFER)
                    .setInstructions(applyInstructions)
                    .setPriority(1000)
                    .setCookie(new FlowCookie(BigInteger.valueOf(flowCookieInc.getAndIncrement())))
                    .setFlags(new FlowModFlags(false, false, false, false, false)).build());

        }
        return flows;
    }


    /**
     * Method to be used by the path manager to install Ingress and Egress flow
     *
     * @param srcIP                  - the source IP to be used for matching
     * @param destIP                 - the destination Ip to be used for matching
     * @param destPort               - the destination TCP port to be used
     * @param protocol               - the protocol number to be used
     * @param meterID                - the meter Id to be used
     * @param mplsLabel              - the mpls label to be used
     * @param IngressEdgeSwitchID    -id of the ingress switch
     * @param EgressEdgeSwitchID     - id of the egress switch
     * @param IngressEdgeSwitchPort  - the node connector value for ingress switch
     * @param EgressEdgeSwitchPort   - the node connector value for egress switch
     * @param resilientPathMplsLabel - the resillient mpls label
     * @param failoverPort           - the output node connector port for failover
     * @param ingressRouteVlanId     - Vlan Id for matching at ingress
     * @param egressRouteVlanId      - Vlan Id to be pushed in egress
     * @param isFinalDomain          - is it final domain
     * @param isFirstDomain          - is it first domain
     * @return service id which will be used to delete both of the flows
     */
    public Long embedMplsFlow(

            Ipv4Prefix srcIP, // only relevant if isFirstDomain is true, otherwise will be ignored
            Ipv4Prefix destIP, // only relevant if isFirstDomain is true, otherwise will be ignored
            Integer destPort, // only relevant if isFirstDomain is true, otherwise will be ignored
            Integer protocol, // only relevant if isFirstDomain is true, otherwise will be ignored
            Long meterID,
            Long mplsLabel,
            String IngressEdgeSwitchID,
            String EgressEdgeSwitchID,
            Integer IngressEdgeSwitchPort,
            Integer EgressEdgeSwitchPort,
            Long resilientPathMplsLabel,
            Integer failoverPort,
            Integer ingressRouteVlanId, // only relevant if isFirstDomain is false, otherwise will be ignored
            Integer egressRouteVlanId, // only relevant if isFinalDomain is false, otherwise will be ignored
            Boolean isFinalDomain,
            Boolean isFirstDomain,
            String destMac,
            Integer priority

    ) {

        try {
            LOG.info("**********************************************************");
            LOG.info("EmbedMPLSFlow called, Params are the following:");
            LOG.info("Source Ip address is: " + srcIP);
            LOG.info("Destination Ip address is: " + destIP);
            LOG.info("Destination Port is: " + destPort);
            LOG.info("Protocol is: " + protocol);
            LOG.info("Meter ID is: " + meterID);
            LOG.info("MPLS Label is: " + mplsLabel);
            LOG.info("Ingress Switch ID is: " + IngressEdgeSwitchID);
            LOG.info("Ingress switch port is: " + IngressEdgeSwitchPort);
            LOG.info("Resillient MPLS label is: " + resilientPathMplsLabel);
            LOG.info("Failover port is: " + failoverPort);
            LOG.info("Is First Domain: " + isFirstDomain);
            LOG.info("Is Final Domain: " + isFinalDomain);
            LOG.info("Ingress route VLAN ID: " + ingressRouteVlanId);
            LOG.info("Egress route VLAN ID: " + egressRouteVlanId);
            LOG.info("**********************************************************");


            List<EmbeddingFlowRequest> listofFlowRequests = new ArrayList<>();

            if (isFirstDomain && !isFinalDomain) {


                Flow sourceFlow = createSourceFlow(meterID, srcIP, destIP, destPort, protocol, mplsLabel, IngressEdgeSwitchPort, failoverPort, resilientPathMplsLabel, IngressEdgeSwitchID, false, ingressRouteVlanId, priority);


                AddFlowInput flowinputSrcNode = getAddFlowInputToSend(IngressEdgeSwitchID, sourceFlow);


                listofFlowRequests.add(new EmbeddingFlowRequest(IngressEdgeSwitchID, sourceFlow.getCookie().getValue()));


                Future<RpcResult<AddFlowOutput>> future = salFlowService.addFlow(flowinputSrcNode);

                if (future.isDone() && future.get().isSuccessful()) {
                    LOG.info("Ingress flow successfully installed");

                } else {

                    if (future.get().getErrors().size() > 0) {
                        LOG.error("Ingress Flow Installation unsuccessful and error is: \n" + future.get().getErrors().toString());
                        return -1L;
                    }

                }


                String last = EgressEdgeSwitchID;
                Flow destinationFlow = createEgressFlow(mplsLabel, EgressEdgeSwitchPort, true, egressRouteVlanId, null, priority);

                AddFlowInput flowinputDestNode = getAddFlowInputToSend(last, destinationFlow);

                listofFlowRequests.add(new EmbeddingFlowRequest(last, destinationFlow.getCookie().getValue()));


                Future<RpcResult<AddFlowOutput>> futureEgreeFlow = salFlowService.addFlow(flowinputDestNode);

                if (futureEgreeFlow.isDone() && futureEgreeFlow.get().isSuccessful()) {
                    LOG.info("Flow installation Successful: Eggress flow: " + last);
                } else {

                    if (futureEgreeFlow.get().getErrors().size() > 0) {
                        LOG.error("Flow Installation unsuccessful: Egress error is: " + futureEgreeFlow.get().getErrors().toString());
                        return -1L;
                    }

                }
                if(!resilientPathMplsLabel.equals(-1L)){
                Flow destinationFlow2 = createEgressFlow(resilientPathMplsLabel, EgressEdgeSwitchPort, true, egressRouteVlanId, null, priority);

                AddFlowInput flowinputDestNode2 = getAddFlowInputToSend(last, destinationFlow2);

                listofFlowRequests.add(new EmbeddingFlowRequest(last, destinationFlow2.getCookie().getValue()));


                Future<RpcResult<AddFlowOutput>> futureEgreeFlow2 = salFlowService.addFlow(flowinputDestNode2);

                if (futureEgreeFlow2.isDone() && futureEgreeFlow2.get().isSuccessful()) {
                    LOG.info("Flow installation Successful: Eggress flow: " + last);
                } else {

                    if (futureEgreeFlow2.get().getErrors().size() > 0) {
                        LOG.error("Flow Installation unsuccessful: Egress error is: " + futureEgreeFlow2.get().getErrors().toString());
                        return -1L;
                    }

                }
            }


            } else if (!isFirstDomain && !isFinalDomain) {


                //ip and other fields are passed here but ignored inside the method
                Flow sourceFlow = createSourceFlow(meterID, srcIP, destIP, destPort, protocol, mplsLabel, IngressEdgeSwitchPort, failoverPort, resilientPathMplsLabel, IngressEdgeSwitchID, true, ingressRouteVlanId, priority);


                AddFlowInput flowinputSrcNode = getAddFlowInputToSend(IngressEdgeSwitchID, sourceFlow);


                listofFlowRequests.add(new EmbeddingFlowRequest(IngressEdgeSwitchID, sourceFlow.getCookie().getValue()));


                Future<RpcResult<AddFlowOutput>> future = salFlowService.addFlow(flowinputSrcNode);

                if (future.isDone() && future.get().isSuccessful()) {
                    LOG.info("Ingress flow successfully installed");

                } else {

                    if (future.get().getErrors().size() > 0) {
                        LOG.error("Ingress Flow Installation unsuccessful and error is: \n" + future.get().getErrors().toString());
                        return -1L;
                    }

                }


                String last = EgressEdgeSwitchID;
                Flow destinationFlow = createEgressFlow(mplsLabel, EgressEdgeSwitchPort, true, egressRouteVlanId, null, priority);

                AddFlowInput flowinputDestNode = getAddFlowInputToSend(last, destinationFlow);

                listofFlowRequests.add(new EmbeddingFlowRequest(last, destinationFlow.getCookie().getValue()));


                Future<RpcResult<AddFlowOutput>> futureEgreeFlow = salFlowService.addFlow(flowinputDestNode);

                if (futureEgreeFlow.isDone() && futureEgreeFlow.get().isSuccessful()) {
                    LOG.info("Flow installation Successful: Eggress flow: " + last);
                } else {
                    if (futureEgreeFlow.get().getErrors().size() > 0) {
                        LOG.error("Flow Installation unsuccessful: Egress error is: " + futureEgreeFlow.get().getErrors().toString());
                        return -1L;
                    }
                }
                if(!resilientPathMplsLabel.equals(-1L)){
                Flow destinationFlow2 = createEgressFlow(resilientPathMplsLabel, EgressEdgeSwitchPort, true, egressRouteVlanId, null, priority);

                AddFlowInput flowinputDestNode2 = getAddFlowInputToSend(last, destinationFlow2);

                listofFlowRequests.add(new EmbeddingFlowRequest(last, destinationFlow2.getCookie().getValue()));


                Future<RpcResult<AddFlowOutput>> futureEgreeFlow2 = salFlowService.addFlow(flowinputDestNode2);

                if (futureEgreeFlow2.isDone() && futureEgreeFlow2.get().isSuccessful()) {
                    LOG.info("Flow installation Successful: Eggress flow: " + last);
                } else {
                    if (futureEgreeFlow2.get().getErrors().size() > 0) {
                        LOG.error("Flow Installation unsuccessful: Egress error is: " + futureEgreeFlow2.get().getErrors().toString());
                        return -1L;
                    }
                }
            }


            } else if (!isFirstDomain && isFinalDomain) {


                Flow sourceFlow = createSourceFlow(meterID, srcIP, destIP, destPort, protocol, mplsLabel, IngressEdgeSwitchPort, failoverPort, resilientPathMplsLabel, IngressEdgeSwitchID, true, ingressRouteVlanId, priority);


                AddFlowInput flowinputSrcNode = getAddFlowInputToSend(IngressEdgeSwitchID, sourceFlow);


                listofFlowRequests.add(new EmbeddingFlowRequest(IngressEdgeSwitchID, sourceFlow.getCookie().getValue()));


                Future<RpcResult<AddFlowOutput>> future = salFlowService.addFlow(flowinputSrcNode);

                if (future.isDone() && future.get().isSuccessful()) {
                    LOG.info("Ingress flow successfully installed");

                } else {

                    if (future.get().getErrors().size() > 0) {
                        LOG.error("Ingress Flow Installation unsuccessful and error is: \n" + future.get().getErrors().toString());
                        return -1L;
                    }

                }


                String last = EgressEdgeSwitchID;
                Flow destinationFlow = createEgressFlow(mplsLabel, EgressEdgeSwitchPort, false, egressRouteVlanId, destMac, priority);

                AddFlowInput flowinputDestNode = getAddFlowInputToSend(last, destinationFlow);

                listofFlowRequests.add(new EmbeddingFlowRequest(last, destinationFlow.getCookie().getValue()));


                Future<RpcResult<AddFlowOutput>> futureEgreeFlow = salFlowService.addFlow(flowinputDestNode);

                if (futureEgreeFlow.isDone() && futureEgreeFlow.get().isSuccessful()) {
                    LOG.info("Flow installation Successful: Eggress flow: " + last);
                } else {
                    if (futureEgreeFlow.get().getErrors().size() > 0) {
                        LOG.error("Flow Installation unsuccessful: Egress error is: " + futureEgreeFlow.get().getErrors().toString());
                        return -1L;
                    }
                }
                if(!resilientPathMplsLabel.equals(-1L)) {
                    Flow destinationFlow2 = createEgressFlow(resilientPathMplsLabel, EgressEdgeSwitchPort, false, egressRouteVlanId, destMac, priority);

                    AddFlowInput flowinputDestNode2 = getAddFlowInputToSend(last, destinationFlow2);

                    listofFlowRequests.add(new EmbeddingFlowRequest(last, destinationFlow2.getCookie().getValue()));


                    Future<RpcResult<AddFlowOutput>> futureEgreeFlow2 = salFlowService.addFlow(flowinputDestNode2);

                    if (futureEgreeFlow2.isDone() && futureEgreeFlow2.get().isSuccessful()) {
                        LOG.info("Flow installation Successful: Eggress flow: " + last);
                    } else {
                        if (futureEgreeFlow2.get().getErrors().size() > 0) {
                            LOG.error("Flow Installation unsuccessful: Egress error is: " + futureEgreeFlow2.get().getErrors().toString());
                            return -1L;
                        }
                    }
                }


            } else if (isFinalDomain && isFirstDomain) {

                Flow sourceFlow = createSourceFlow(meterID, srcIP, destIP, destPort, protocol, mplsLabel, IngressEdgeSwitchPort, failoverPort, resilientPathMplsLabel, IngressEdgeSwitchID, false, ingressRouteVlanId, priority);


                AddFlowInput flowinputSrcNode = getAddFlowInputToSend(IngressEdgeSwitchID, sourceFlow);


                listofFlowRequests.add(new EmbeddingFlowRequest(IngressEdgeSwitchID, sourceFlow.getCookie().getValue()));


                Future<RpcResult<AddFlowOutput>> future = salFlowService.addFlow(flowinputSrcNode);

                if (future.isDone() && future.get().isSuccessful()) {
                    LOG.info("Ingress flow successfully installed");

                } else {

                    if (future.get().getErrors().size() > 0) {
                        LOG.error("Ingress Flow Installation unsuccessful and error is: \n" + future.get().getErrors().toString());
                        return -1L;
                    }

                }


                String last = EgressEdgeSwitchID;
                Flow destinationFlow = createEgressFlow(mplsLabel, EgressEdgeSwitchPort, false, egressRouteVlanId, destMac, priority);

                AddFlowInput flowinputDestNode = getAddFlowInputToSend(last, destinationFlow);

                listofFlowRequests.add(new EmbeddingFlowRequest(last, destinationFlow.getCookie().getValue()));


                Future<RpcResult<AddFlowOutput>> futureEgreeFlow = salFlowService.addFlow(flowinputDestNode);

                if (futureEgreeFlow.isDone() && futureEgreeFlow.get().isSuccessful()) {
                    LOG.info("Flow installation Successful: Eggress flow: " + last);
                } else {
                    if (futureEgreeFlow.get().getErrors().size() > 0) {
                        LOG.error("Flow Installation unsuccessful: Egress error is: " + futureEgreeFlow.get().getErrors().toString());
                        return -1L;
                    }
                }
                if(!resilientPathMplsLabel.equals(-1L)) {
                    Flow destinationFlow2 = createEgressFlow(resilientPathMplsLabel, EgressEdgeSwitchPort, false, egressRouteVlanId, destMac, priority);

                    AddFlowInput flowinputDestNode2 = getAddFlowInputToSend(last, destinationFlow2);

                    listofFlowRequests.add(new EmbeddingFlowRequest(last, destinationFlow2.getCookie().getValue()));


                    Future<RpcResult<AddFlowOutput>> futureEgreeFlow2 = salFlowService.addFlow(flowinputDestNode2);

                    if (futureEgreeFlow2.isDone() && futureEgreeFlow2.get().isSuccessful()) {
                        LOG.info("Flow installation Successful: Eggress flow: " + last);
                    } else {
                        if (futureEgreeFlow2.get().getErrors().size() > 0) {
                            LOG.error("Flow Installation unsuccessful: Egress error is: " + futureEgreeFlow2.get().getErrors().toString());
                            return -1L;
                        }
                    }
                }

            } else {
                return -1L;
            }

            ++id;

            IdToListMap.put(id, listofFlowRequests);
            return id;


        } catch (Exception e) {

            LOG.error("exception from interdomain embedMPLSFlow ");
            e.printStackTrace();

            return -1L;
        }


    }

/*
    public Long embedMplsFlow(

            Ipv4Prefix srcIP,
            Ipv4Prefix destIP,
            Integer srcPort,
            Integer destPort,
            Integer protocol,
            Long meterID,
            Long mplsLabel,
            String IngressEdgeSwitchID,
            Integer IngressEdgeSwitchPort

    ) {


        try {

            ++id;

            System.out.println("embed mpls flow called");

            Flow sourceFlow = createSourceFlow(meterID, srcIP, destIP, srcPort, destPort, protocol, mplsLabel, IngressEdgeSwitchPort);


            //createSourceFlow(meterID, srcIP, destIP, srcPort, destPort, protocol, mplsLabel, EgressEdgeSwitchPort);
            //createDestinationFlow(srcIP, destIP, srcPort, destPort, protocol, mplsLabel, EgressEdgeSwitchPort, destMac);

            List<EmbeddingFlowRequest> listofFlowRequests = new ArrayList<>();

            AddFlowInput flowinputSrcNode = getAddFlowInputToSend(IngressEdgeSwitchID, sourceFlow);


            listofFlowRequests.add(new EmbeddingFlowRequest(IngressEdgeSwitchID, sourceFlow.getCookie().getValue()));

            System.out.println("flow input Source is: " + flowinputSrcNode.toString());

            salFlowService.addFlow(flowinputSrcNode);


            IdToListMap.put(id, listofFlowRequests);
            return id;


        } catch (Exception e) {

            LOG.error("exception from interdomain embedMPLSFlow ");
            e.printStackTrace();

            return null;
        }
    }

*/


    /**
     * Method to create a Source flow
     *
     * @param meterId                  - the meter id to be used
     * @param srcIP                    - the source ip to be used for matching
     * @param destIP                   - the destination ip to be used for matching
     * @param destPort                 - the destination TCP port to be used
     * @param protocol                 - the protocol to be used
     * @param mplsLabel                - the mpls label
     * @param outputNodeConnectorValue - the output node connector value to be used in actions
     * @param failoverPort             - the failover port to be used in the FF group
     * @param failoverMpls             - the failover MPLS label to be used in the FF group
     * @param ingressEdgeSwitchID      - the switch Id
     * @param popVlan                  - whether VLAN should be popped or not
     * @param IngressVlanId            - if PopVlan is true then this is used for matching Vlan
     * @return
     */

    private Flow createSourceFlow(Long meterId, Ipv4Prefix srcIP, Ipv4Prefix destIP,
                                  Integer destPort, Integer protocol, Long mplsLabel,
                                  Integer outputNodeConnectorValue, Integer failoverPort, Long failoverMpls,
                                  String ingressEdgeSwitchID, Boolean popVlan, Integer IngressVlanId, Integer priority
    ) {

        FlowBuilder flowBuilder = new FlowBuilder()
                .setTableId((short) 0)
                .setFlowName("random");


        //Flow ID
        flowBuilder.setId(new FlowId(Long.toString(flowBuilder.hashCode())));


        Match match = null;

        if (!popVlan) {
            match = getMatch(srcIP, destIP, destPort, protocol);
        } else {
            //pop vlan is true hence match should exist
            match = getMatchWithVlan(IngressVlanId);
        }


        // List<List<Action>> listofactions =    getLists(mplsLabel, outputNodeConnectorValue, null);


        AddGroupInput groupInput = getFFGroupForIngress(mplsLabel, outputNodeConnectorValue, failoverMpls, failoverPort, ingressEdgeSwitchID, popVlan);

        RemoveGroupInput removeGroupInput = new RemoveGroupInputBuilder()
                .setGroupId(groupInput.getGroupId())
                .setGroupName(groupInput.getGroupName())
                .setGroupRef(groupInput.getGroupRef())
                .setGroupType(groupInput.getGroupType())
                .setBarrier(groupInput.isBarrier())
                .setNode(groupInput.getNode())
                .setBuckets(groupInput.getBuckets())
                .build();


        mapOfIdToGroup.put(id, removeGroupInput);
        //ApplyActions

        try {

            Instructions applyInstructions = getInstructions(meterId, groupInput.getGroupId().getValue());


            Future<RpcResult<AddGroupOutput>> future = salGroupService.addGroup(groupInput);

            if (future.isDone() && future.get().isSuccessful()) {
                LOG.info(" Group was installed successfully with ID " + groupInput.getGroupId());
            } else {
                LOG.error("Group was not installed and the error is:  " + future.get().getErrors().toString());
            }


            // Put our Instruction in a list of Instructions

            LOG.info("adding flow");
            Flow flow = flowBuilder
                    .setMatch(match)
                    .setBufferId(OFConstants.OFP_NO_BUFFER)
                    .setInstructions(applyInstructions)
//                    .setPriority(1000)
                    .setPriority(priority)
                    .setHardTimeout(0)
                    .setIdleTimeout(0)
                    .setCookie(new FlowCookie(BigInteger.valueOf(flowCookieInc.getAndIncrement())))
                    .setFlags(new FlowModFlags(false, false, false, false, false)).build();

            return flow;
        } catch (Exception e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return null;

    }


    /**
     * Method to create an egress flow
     *
     * @param mplsLabel                - the mpls label to be used
     * @param outputNodeConnectorValue -  the output node connector value which should be used
     * @param pushVlan                 - whether VLAN will be pushed or not
     * @param egressVlanId             - If pushVlan is true then this will be used as the Id
     * @return
     */
    private Flow createEgressFlow(Long mplsLabel,
                                  Integer outputNodeConnectorValue, Boolean pushVlan, Integer egressVlanId, String destMac,
                                  Integer priority) {

        FlowBuilder flowBuilder = new FlowBuilder()
                .setTableId((short) 0)
                .setFlowName("random");


        //Flow ID
        flowBuilder.setId(new FlowId(Long.toString(flowBuilder.hashCode())));

        Match match = getMatchForEgress(mplsLabel);


        List<Action> listofactions = getLists(outputNodeConnectorValue, pushVlan, egressVlanId, destMac);


        //ApplyActions

        Instructions applyInstructions = getInstructionsForEggress(listofactions);


        // Put our Instruction in a list of Instructions

        LOG.info("adding flow");
        Flow flow = flowBuilder
                .setMatch(match)
                .setBufferId(OFConstants.OFP_NO_BUFFER)
                .setInstructions(applyInstructions)
                //.setPriority(1000)
                .setPriority(priority)
                .setHardTimeout(0)
                .setIdleTimeout(0)
                .setCookie(new FlowCookie(BigInteger.valueOf(flowCookieInc.getAndIncrement())))
                .setFlags(new FlowModFlags(false, false, false, false, false)).build();

        return flow;

    }


    /**
     * Method to return instructions for egress switch
     *
     * @param actions - list of actions from which instructions must be created with
     * @return Instructions
     */
    private Instructions getInstructionsForEggress(List<Action> actions) {

        ApplyActions applyActions = new ApplyActionsBuilder().setAction(actions).build();

        //Instruction
        Instruction applyActionsInstruction = new InstructionBuilder() //
                .setOrder(0).setInstruction(new ApplyActionsCaseBuilder()//
                        .setApplyActions(applyActions) //
                        .build())

                .build();

        return new InstructionsBuilder()
                .setInstruction(ImmutableList.of(applyActionsInstruction))
                .build();

    }

    /**
     * Method to return instructions based on meter id and group Id
     *
     * @param meterId  - the meter id for which the instruction will be created
     * @param groupId1 - the group id
     * @return Instructions which can now be used to install a flow
     */
    private Instructions getInstructions(Long meterId, Long groupId1) {

        List<Action> actions = new ArrayList<>();

        Action groupAction = new ActionBuilder()
                .setOrder(0).setAction(new GroupActionCaseBuilder()
                        .setGroupAction(new GroupActionBuilder()
                                .setGroupId(groupId1)
                                .build())
                        .build())
                .build();
        actions.add(groupAction);

        ApplyActions applyActions = new ApplyActionsBuilder().setAction(actions).build();

        //Instruction

        if (meterId != null) {

            Instruction applyActionsInstruction = new InstructionBuilder() //
                    .setOrder(0).setInstruction(new ApplyActionsCaseBuilder()//
                            .setApplyActions(applyActions) //
                            .build())

                    .build();

            Instruction meterInstruction = new InstructionBuilder().setOrder(1).setInstruction((new MeterCaseBuilder()
                    .setMeter(new MeterBuilder()
                            .setMeterId(new MeterId(meterId))
                            .build())
                    .build()))
                    .build();

            List<Instruction> instructionList = new ArrayList<>();

            instructionList.add(applyActionsInstruction);
            instructionList.add(meterInstruction);

            return new InstructionsBuilder()
                    .setInstruction(instructionList)
                    .build();

        } else {

            Instruction applyActionsInstruction = new InstructionBuilder() //
                    .setOrder(0).setInstruction(new ApplyActionsCaseBuilder()//
                            .setApplyActions(applyActions) //
                            .build())

                    .build();


            List<Instruction> instructionList = new ArrayList<>();

            instructionList.add(applyActionsInstruction);

            return new InstructionsBuilder()
                    .setInstruction(instructionList)
                    .build();


        }


    }

    /**
     * Method to return list of actions
     *
     * @param outputNodeConnectorValue - output node connector which should be used in the action
     * @param pushVlan                 - whether push VLAN action should be included or not
     * @param egressVlan               - if pushVlan is true, then this value will be used as VLAN tag
     * @return List<Action> - a list of actions based on params
     */

    private List<Action> getLists(Integer outputNodeConnectorValue, Boolean pushVlan, Integer egressVlan, String destMac) {
        ActionBuilder actionBuilder = new ActionBuilder();


        //Actions


        List<Action> listofactions = new ArrayList<>();

        String outputNodeConnector = String.valueOf(outputNodeConnectorValue);
        LOG.info("Setting the output src port to: " + outputNodeConnector);


        Action popMPLS = actionBuilder.setOrder(0).setAction(new PopMplsActionCaseBuilder()
                .setPopMplsAction(new PopMplsActionBuilder()
                        .setEthernetType(IP_EHERTYPE).build()).build()).build();


        PushVlanActionCaseBuilder pushVlanActionCaseBuilder = new PushVlanActionCaseBuilder();


        Action pushVlanAction = actionBuilder.setOrder(1).setAction(
                pushVlanActionCaseBuilder.setPushVlanAction(new PushVlanActionBuilder()
                        .setEthernetType(VLAN_ETHERTYPE)
                        .build()).build()
        ).build();


        Action setVlanIdAction = actionBuilder
                .setOrder(2).setAction(new SetFieldCaseBuilder()
                        .setSetField(new SetFieldBuilder()
                                .setVlanMatch(new VlanMatchBuilder()
                                        .setVlanId(new VlanIdBuilder()
                                                .setVlanId(new VlanId(egressVlan))
                                                .setVlanIdPresent(true)
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build();


        if (!pushVlan) {


            Action destMacAction = actionBuilder.setOrder(1).setAction(new SetDlDstActionCaseBuilder()
                    .setSetDlDstAction(new SetDlDstActionBuilder()
                            .setAddress(new org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.MacAddress(destMac))
                            .build()).build()).build();


            Action outputNodeConnectorActionsrc1 = actionBuilder
                    .setOrder(2).setAction(new OutputActionCaseBuilder()
                            .setOutputAction(new OutputActionBuilder()
                                    .setOutputNodeConnector(new Uri(outputNodeConnector))
                                    .build())
                            .build())
                    .build();


            listofactions.add(popMPLS);
            listofactions.add(destMacAction);
            listofactions.add(outputNodeConnectorActionsrc1);

            return listofactions;

        } else {


            Action outputNodeConnectorAction = actionBuilder
                    .setOrder(3).setAction(new OutputActionCaseBuilder()
                            .setOutputAction(new OutputActionBuilder()
                                    .setOutputNodeConnector(new Uri(outputNodeConnector))
                                    .build())
                            .build())
                    .build();

            listofactions.add(popMPLS);
            listofactions.add(pushVlanAction);
            listofactions.add(setVlanIdAction);
            listofactions.add(outputNodeConnectorAction);

            return listofactions;

        }


    }

    /**
     * Method to get a FF group
     *
     * @param outputNodeConnectorValue - the output node connector flow should use
     * @param failoverMpls             - failover MPLS label
     * @param failoverPort             - Failover Output node connector value
     * @param switchId                 - the switch id
     * @param queue                    - the queue
     * @return AddGroupInput - a representation of the created group
     */
    private AddGroupInput getFFGroup(Integer outputNodeConnectorValue, Long failoverMpls, Integer failoverPort, String switchId, Long queue) {

        ++groupId;


        ActionBuilder actionBuilder = new ActionBuilder();

        List<Action> listofactions = new ArrayList<>();


        String outputNodeConnector = String.valueOf(outputNodeConnectorValue);
        LOG.info("Setting the output src port to: " + outputNodeConnector);


        Action queueAction = actionBuilder
                .setOrder(0).setAction(new SetQueueActionCaseBuilder()
                        .setSetQueueAction(new SetQueueActionBuilder()
                                .setQueueId(queue)
                                .build())
                        .build())

                .build();


        Action outputNodeConnectorAction = actionBuilder
                .setOrder(1).setAction(new OutputActionCaseBuilder()
                        .setOutputAction(new OutputActionBuilder()
                                .setOutputNodeConnector(new Uri(outputNodeConnector))
                                .build())
                        .build())
                .build();


        listofactions.add(queueAction);
        listofactions.add(outputNodeConnectorAction);

        Bucket bucket = new BucketBuilder()
                .setBucketId(new BucketId(1L))
                .setWatchPort(Long.parseLong(outputNodeConnector))
                .setKey(new BucketKey(new BucketId(1L)))
                .setAction(listofactions)
                .setWatchGroup(DEFAULT_WATCH_GROUP)
                .build();

        List<Bucket> bucketList = new ArrayList<>();

        bucketList.add(bucket);

        if ((!failoverMpls.equals(-1L)) && failoverPort != null) {

            List<Action> failoverActionsList = new ArrayList<>();
            ActionBuilder failoverActions = new ActionBuilder();

            Action popMPLS = failoverActions.setOrder(0).setAction(new PopMplsActionCaseBuilder()
                    .setPopMplsAction(new PopMplsActionBuilder()
                            .setEthernetType(IP_EHERTYPE).build()).build()).build();

            Action mplsPushActionResillence = failoverActions.setOrder(1).setAction(new PushMplsActionCaseBuilder()
                    .setPushMplsAction(new PushMplsActionBuilder().setEthernetType(MPLS_ETHERTYPE).build()).build()).build();

            ProtocolMatchFieldsBuilder matchFieldsBuilder = new ProtocolMatchFieldsBuilder();

            SetFieldBuilder setFieldBuilderFailover = new SetFieldBuilder()
                    .setProtocolMatchFields(matchFieldsBuilder.setMplsLabel(failoverMpls).build());

            Action mplsActionFailover = failoverActions.setOrder(2)
                    .setAction(new SetFieldCaseBuilder()
                            .setSetField(setFieldBuilderFailover.build()).build()).build();

            Action outputNodeConnectorActionsrcFailover = failoverActions
                    .setOrder(2).setAction(new OutputActionCaseBuilder()
                            .setOutputAction(new OutputActionBuilder()
                                    .setOutputNodeConnector(new Uri(failoverPort.toString()))
                                    .build())
                            .build())
                    .build();

            failoverActionsList.add(popMPLS);
            failoverActionsList.add(mplsPushActionResillence);
            failoverActionsList.add(mplsActionFailover);
            failoverActionsList.add(outputNodeConnectorActionsrcFailover);

            Bucket bucketFailover = new BucketBuilder()
                    .setBucketId(new BucketId(2L))
                    .setKey(new BucketKey(new BucketId(2L)))
                    .setWatchPort(failoverPort.longValue())
                    .setAction(failoverActionsList)
                    .setWatchGroup(DEFAULT_WATCH_GROUP)
                    .build();

            bucketList.add(bucketFailover);

        }
        Buckets buckets = new BucketsBuilder().setBucket(bucketList).build();


        return new AddGroupInputBuilder()
                .setGroupName("FF")
                .setBarrier(false)
                .setGroupId(new GroupId(groupId))
                .setGroupType(GroupTypes.GroupFf)
                .setBuckets(buckets)
                .setNode(getNodeRef(new NodeId(switchId)))
                .build();


    }


    /**
     * Method to return a FF Group for the ingress node
     *
     * @param mplsLabel                - the mpls label to be used
     * @param outputNodeConnectorValue - output node connector value
     * @param failoverMpls             - MPLS label for the failover bucket
     * @param failoverPort             - Output node connector for for failover action bucket
     * @param IngressSwitchID          - the switch id
     * @param popVlan                  - based on whether its a final domain or not
     * @return
     */
    private AddGroupInput getFFGroupForIngress(Long mplsLabel, Integer outputNodeConnectorValue, Long failoverMpls, Integer failoverPort, String IngressSwitchID, Boolean popVlan) {


        ++groupId;


        ActionBuilder actionBuilder = new ActionBuilder();

        List<Action> listofactions = new ArrayList<>();


        PopVlanActionCaseBuilder popVlanBuilder = new PopVlanActionCaseBuilder();
        popVlanBuilder.setPopVlanAction(new PopVlanActionBuilder().build());

        Action vlanAction = actionBuilder.setOrder(0).setAction(popVlanBuilder.build()).build();


        String outputNodeConnector = String.valueOf(outputNodeConnectorValue);
        LOG.info("Setting the output src port to: " + outputNodeConnector);

        Action mplsPushAction = actionBuilder.setOrder(1).setAction(new PushMplsActionCaseBuilder()
                .setPushMplsAction(new PushMplsActionBuilder().setEthernetType(MPLS_ETHERTYPE).build()).build()).build();


        ProtocolMatchFieldsBuilder matchFieldsBuilder = new ProtocolMatchFieldsBuilder()
                .setMplsLabel(mplsLabel);

        SetFieldBuilder setFieldBuilder = new SetFieldBuilder()
                .setProtocolMatchFields(matchFieldsBuilder.build());

        Action mplsAction = actionBuilder.setOrder(2)
                .setAction(new SetFieldCaseBuilder()
                        .setSetField(setFieldBuilder.build()).build()).build();


        Action outputNodeConnectorActionsrc = actionBuilder
                .setOrder(3).setAction(new OutputActionCaseBuilder()
                        .setOutputAction(new OutputActionBuilder()
                                .setOutputNodeConnector(new Uri(outputNodeConnector))
                                .build())
                        .build())
                .build();


        if (!popVlan) {

            listofactions.add(mplsPushAction);
            listofactions.add(mplsAction);
            listofactions.add(outputNodeConnectorActionsrc);


        } else {

            listofactions.add(vlanAction);
            listofactions.add(mplsPushAction);
            listofactions.add(mplsAction);
            listofactions.add(outputNodeConnectorActionsrc);


        }


        Bucket bucket = new BucketBuilder()
                .setBucketId(new BucketId(1L))
                .setWatchPort(Long.parseLong(outputNodeConnector))
                .setKey(new BucketKey(new BucketId(1L)))
                .setAction(listofactions)
                .setWatchGroup(DEFAULT_WATCH_GROUP)
                .build();

        List<Bucket> bucketList = new ArrayList<>();

        bucketList.add(bucket);

        if ((!failoverMpls.equals(-1L)) && failoverPort != null) {


            List<Action> failoverActionsList = new ArrayList<>();
            ActionBuilder failoverActions = new ActionBuilder();

 /*       Action popMPLS = failoverActions.setOrder(0).setAction(new PopMplsActionCaseBuilder()
                .setPopMplsAction(new PopMplsActionBuilder()
                        .setEthernetType(IP_EHERTYPE).build()).build()).build();
*/
            Action mplsPushActionResillence = failoverActions.setOrder(1).setAction(new PushMplsActionCaseBuilder()
                    .setPushMplsAction(new PushMplsActionBuilder().setEthernetType(MPLS_ETHERTYPE).build()).build()).build();

            SetFieldBuilder setFieldBuilderFailover = new SetFieldBuilder()
                    .setProtocolMatchFields(matchFieldsBuilder.setMplsLabel(failoverMpls).build());

            Action mplsActionFailover = failoverActions.setOrder(2)
                    .setAction(new SetFieldCaseBuilder()
                            .setSetField(setFieldBuilderFailover.build()).build()).build();

            Action outputNodeConnectorActionsrcFailover = failoverActions
                    .setOrder(3).setAction(new OutputActionCaseBuilder()
                            .setOutputAction(new OutputActionBuilder()
                                    .setOutputNodeConnector(new Uri(failoverPort.toString()))
                                    .build())
                            .build())
                    .build();

            // failoverActionsList.add(popMPLS);

            if (!popVlan) {
                failoverActionsList.add(mplsPushActionResillence);
                failoverActionsList.add(mplsActionFailover);
                failoverActionsList.add(outputNodeConnectorActionsrcFailover);

            } else {

                failoverActionsList.add(vlanAction);
                failoverActionsList.add(mplsPushActionResillence);
                failoverActionsList.add(mplsActionFailover);
                failoverActionsList.add(outputNodeConnectorActionsrcFailover);


            }


            Bucket bucketFailover = new BucketBuilder()
                    .setBucketId(new BucketId(2L))
                    .setKey(new BucketKey(new BucketId(2L)))
                    .setWatchPort(failoverPort.longValue())
                    .setAction(failoverActionsList)
                    .setWatchGroup(DEFAULT_WATCH_GROUP)
                    .build();

            bucketList.add(bucketFailover);

        }

        Buckets buckets = new BucketsBuilder().setBucket(bucketList).build();


        return new AddGroupInputBuilder().setGroupName("FF")
                .setBarrier(false)
                .setGroupId(new GroupId(groupId))
                .setGroupType(GroupTypes.GroupFf)
                .setBuckets(buckets)
                .setNode(getNodeRef(new NodeId(IngressSwitchID)))
                .build();


    }

    /**
     * Method to get a node reference from NodeId
     *
     * @param nodeId - Nodeid for which to get the node reference
     * @return NodeRef object
     */
    public NodeRef getNodeRef(NodeId nodeId) {
        return new NodeRef(InstanceIdentifier.builder(Nodes.class)
                .child(Node.class, new NodeKey(nodeId))
                .build());
    }


    /**
     * Method to get a match for Ingress switch/flow
     *
     * @param srcIP    - The Ip match for the source node
     * @param destIP   - the IP match for the destination node
     * @param destPort - the detination TCP port
     * @param protocol - the protocol number i.e TCP, UDP, IP
     * @return
     */
    private Match getMatch(Ipv4Prefix srcIP, Ipv4Prefix destIP, Integer destPort, Integer protocol) {


        LOG.info("getMatch called, protocol is: " + protocol);

        if (protocol == 6) {


            TcpMatchBuilder tcpMatchBuilder = new TcpMatchBuilder().setTcpDestinationPort(new PortNumber(destPort));
            IpMatchBuilder ipMatchBuilder = new IpMatchBuilder().setIpProtocol(protocol.shortValue());


            return new MatchBuilder()

                    .setIpMatch(ipMatchBuilder.build())
                    .setLayer4Match(tcpMatchBuilder.build())
                    .setLayer3Match(new Ipv4MatchBuilder().setIpv4Source(srcIP).setIpv4Destination(destIP).build())
                    .setEthernetMatch(new EthernetMatchBuilder()
                            .setEthernetType(new EthernetTypeBuilder()
                                    .setType(new EtherType(IP_EHERTYPE.longValue()))
                                    .build())
                            .build())
                    .build();
        }
     else if (protocol == 17){

            UdpMatchBuilder udpMatchBuilder = new UdpMatchBuilder().setUdpDestinationPort(new PortNumber(destPort));
            IpMatchBuilder ipMatchBuilder = new IpMatchBuilder().setIpProtocol(protocol.shortValue());

            return new MatchBuilder()

                    .setIpMatch(ipMatchBuilder.build())
                    .setLayer4Match(udpMatchBuilder.build())
                    .setLayer3Match(new Ipv4MatchBuilder().setIpv4Source(srcIP).setIpv4Destination(destIP).build())
                    .setEthernetMatch(new EthernetMatchBuilder()
                            .setEthernetType(new EthernetTypeBuilder()
                                    .setType(new EtherType(IP_EHERTYPE.longValue()))
                                    .build())
                            .build())
                    .build();

    }

    LOG.error("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
    LOG.error("No matching parameters");
    return null;

    }




    /**
     * Method returns a  match with a VLAN
     * @param vlanId
     * @return
     */
    private Match getMatchWithVlan(Integer vlanId) {


        return new MatchBuilder()

         /*       .setEthernetMatch(new EthernetMatchBuilder()
                        .setEthernetType(new EthernetTypeBuilder()
                                .setType(new EtherType(VLAN_ETHERTYPE.longValue()))
                                .build())
                        .build())*/
                .setVlanMatch(new VlanMatchBuilder().setVlanId(new VlanIdBuilder().setVlanId(new VlanId(vlanId)).setVlanIdPresent(true).build()).build())
                .build();
    }


    public static MatchBuilder createVlanIdMatch(MatchBuilder matchBuilder, VlanId vlanId, boolean present) {
        VlanMatchBuilder vlanMatchBuilder = new VlanMatchBuilder();
        VlanIdBuilder vlanIdBuilder = new VlanIdBuilder();
        vlanIdBuilder.setVlanId(new VlanId(vlanId));
        vlanIdBuilder.setVlanIdPresent(present);
        vlanMatchBuilder.setVlanId(vlanIdBuilder.build());
        matchBuilder.setVlanMatch(vlanMatchBuilder.build());

        return matchBuilder;
    }


    /**
     * Method to get a match for egress flow
     * @param mpls - mpls label for matching it
     * @return match object
     */
    private Match getMatchForEgress(Long mpls) {


        return new MatchBuilder()

                .setEthernetMatch(new EthernetMatchBuilder()
                        .setEthernetType(new EthernetTypeBuilder()
                                .setType(new EtherType(MPLS_ETHERTYPE.longValue()))
                                .build())
                        .build())
                .setProtocolMatchFields(new ProtocolMatchFieldsBuilder().setMplsLabel(mpls).build())

                .build();
    }



    private Match getMatchForDestination(Ipv4Prefix srcIP, Ipv4Prefix destIP, Integer srcPort, Integer destPort, Integer protocol, Long mpls) {

        TcpMatchBuilder tcpMatchBuilder = new TcpMatchBuilder().setTcpDestinationPort(new PortNumber(destPort)).setTcpSourcePort(new PortNumber(srcPort));
        IpMatchBuilder ipMatchBuilder = new IpMatchBuilder().setIpProtocol(protocol.shortValue());


        return new MatchBuilder()

                .setIpMatch(ipMatchBuilder.build())
                .setLayer4Match(tcpMatchBuilder.build())
                .setLayer3Match(new Ipv4MatchBuilder().setIpv4Source(srcIP).setIpv4Destination(destIP).build())
                .setProtocolMatchFields(new ProtocolMatchFieldsBuilder().setMplsLabel(mpls).build())
                .setEthernetMatch(new EthernetMatchBuilder()
                        .setEthernetType(new EthernetTypeBuilder()
                                .setType(new EtherType(MPLS_ETHERTYPE.longValue()))
                                .build())
                        .build())
                .build();
    }

  /*  public static org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.Action setMplsLabelBosAction(Long label, boolean bos) {
        ProtocolMatchFieldsBuilder matchFieldsBuilder = new ProtocolMatchFieldsBuilder()
                .setMplsLabel(label)
                .setMplsBos((short) (bos ? 1 : 0));
        SetFieldBuilder setFieldBuilder = new SetFieldBuilder()
                .setProtocolMatchFields(matchFieldsBuilder.build());
        return new SetFieldCaseBuilder()
                .setSetField(setFieldBuilder.build())
                .build();
    }
*/

    /**
     * Method to remove the ingress and egress flows
     * @param serviceId - the id that was returned when the flows were installed
     * @return true if successful, false otherwise
     */
    public boolean removeFlow(Long serviceId) {

        List<EmbeddingFlowRequest> allRequests = IdToListMap.get(serviceId);
        RemoveGroupInput removeGroupInput = mapOfIdToGroup.get(serviceId);

        //find if a path with the path id exists


        //ensuring that path is found
        if (allRequests != null) {

            //path found

            for (int i = 0; i < allRequests.size(); i++) {

                RemoveFlowInput flow = getRemoveFlowInput(allRequests, i);

                // remove flow from sal
                Future<RpcResult<RemoveFlowOutput>> future = salFlowService.removeFlow(flow);

                try {
                    if (future.get().isSuccessful()) {
                        LOG.info("Flow removal successful and flow was: " + flow.getCookie() + "   on node " + flow.getNode());
                    } else {
                        LOG.info("!!!!Flow removal Unsuccessful and flow was: " + flow.getCookie() + "   on node " + flow.getNode() + "\n and errors are:  " + future.get().getErrors().toString());

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            if (removeGroupInput != null) {
                Future<RpcResult<RemoveGroupOutput>> future = salGroupService.removeGroup(removeGroupInput);

                try {
                    if (future.get().isSuccessful()) {
                        LOG.info("Removing group successful and Group was: " + removeGroupInput.getGroupId() + " on switch :" + removeGroupInput.getNode().getValue());
                    } else {
                        LOG.info("!!!Removing group Unsuccessful and Group was: " + removeGroupInput.getGroupId() + " on switch :" + removeGroupInput.getNode().getValue() + "\n and errors are " + future.get().getErrors().toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


        } else {

            //no path found hence nothing to delete

            return false;
        }

        // all flows successfully removed
        return true;

    }


    /**
     * Method to remove an MPLS path (Core)
     * @param mplsLabel - mpls label represents a path
     * @return true if successful, false otherwise
     */
    public boolean removeMplsPath(Long mplsLabel) {

        //Found path will be stored in this
        List<EmbeddingFlowRequest> allRequests = IdToFlowMap.get(mplsLabel);

        //find if a path with the path id exists


        //ensuring that path is found
        if (allRequests != null) {

            List<RemoveGroupInput> removeGroupInputList = mapOfIdToListOfRemovals.get(mplsLabel);
            //path found

            //Going through all the flows within the path to delete them one by one
            for (int i = 0; i < allRequests.size(); i++) {

                RemoveFlowInput flow = getRemoveFlowInput(allRequests, i);

                // remove flow from sal
                Future<RpcResult<RemoveFlowOutput>> future = salFlowService.removeFlow(flow);

                try {
                    if (future.get().isSuccessful()) {
                        LOG.info("Flow removal successful and flow was: " + flow.getCookie() + "   on node " + flow.getNode());
                    } else {
                        LOG.info("!!!!Flow removal Unsuccessful and flow was: " + flow.getCookie() + "   on node " + flow.getNode() + "\n and errors are:  " + future.get().getErrors().toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            for (RemoveGroupInput removeGroupInput : removeGroupInputList) {

                Future<RpcResult<RemoveGroupOutput>> future = salGroupService.removeGroup(removeGroupInput);

                try {
                    if (future.get().isSuccessful()) {
                        LOG.info("Removing group successful and Group was: " + removeGroupInput.getGroupId() + " on switch :" + removeGroupInput.getNode().getValue());
                    } else {
                        LOG.info("!!!Removing group Unsuccessful and Group was: " + removeGroupInput.getGroupId() + " on switch :" + removeGroupInput.getNode().getValue() + "\n and errors are " + future.get().getErrors().toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        } else {

            //no path found hence nothing to delete

            return false;
        }

        // all flows successfully removed
        return true;

    }

    /**
     * Return the removeFlowInput to remove a given request
     * @param allRequests - list of all requests
     * @param i - the index in the list
     * @return removeFlowInput which can be used to delete the flow
     */
    private RemoveFlowInput getRemoveFlowInput(List<EmbeddingFlowRequest> allRequests, int i) {
        Flow flowtoDelete = populateFlowRemoveStructure(new FlowCookie(allRequests.get(i).getFlowCookie()));

        String edge_switch = allRequests.get(i).getNode_id();

        //identify the flow
        InstanceIdentifier<Flow> flowPath = InstanceIdentifier
                .builder(Nodes.class)
                .child(Node.class, new NodeKey(new NodeId(edge_switch)))
                .augmentation(FlowCapableNode.class)
                .child(Table.class, new TableKey(flowtoDelete.getTableId()))
                .child(Flow.class, new FlowKey(flowtoDelete.getId())).build();


        RemoveFlowInputBuilder b = new RemoveFlowInputBuilder(flowtoDelete);
        InstanceIdentifier<Table> tableInstanceId = flowPath
                .<Table>firstIdentifierOf(Table.class);
        InstanceIdentifier<Node> nodeInstanceId = flowPath
                .<Node>firstIdentifierOf(Node.class);
        b.setNode(new NodeRef(nodeInstanceId));
        b.setFlowTable(new FlowTableRef(tableInstanceId));
        b.setTransactionUri(new Uri(flowtoDelete.getId().getValue()));

        //the flow to remove
        return b.build();
    }


    /**
     * Method to construct the flow from flow cookie
     * @param cookiePath - cookie of the flow
     * @return Flow - constructed flow
     */
    private static Flow populateFlowRemoveStructure(FlowCookie cookiePath) {
        FlowBuilder flowBuilder = new FlowBuilder() //
                .setTableId((short) 0);
        flowBuilder
                .setBufferId(OFConstants.OFP_NO_BUFFER)
                .setCookie(cookiePath)
                .setCookieMask(new FlowCookie(BigInteger.valueOf(0xffffL)))
                .setFlags(new FlowModFlags(false, false, false, false, true))
                .setId(new FlowId(cookiePath.toString()));
        return flowBuilder.build();
    }

    /**
     * Method to install a meter in a given switch
     * @param meterId - the id that meter should have
     * @param burst - burst of the meter
     * @param bandwidth - bandwidth allowance of the meter
     * @param nodeID - the node on which meter will be installed
     * @return true if installation was successful, false otherwise
     */
    public boolean installMeter(Long meterId, Long burst, Long bandwidth, String nodeID) {

        NodeId nodeId = new NodeId(nodeID);
        VTNMeterUtils vtnMeterUtils = new VTNMeterUtils(new MeterId(meterId), bandwidth, burst);

        AddMeterInput addMeterInput = vtnMeterUtils.createAddMeterInput(nodeId);


        Future<RpcResult<AddMeterOutput>> future = salMeterService.addMeter(addMeterInput);

        try {

            if (future.get().isSuccessful()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    /**
     * Method to delete the specific meter
     * @param meterId - the id of the meter
     * @param burst  - the burst of the installed meter
     * @param bandwidth - bandwidth of the meter
     * @param nodeID - the node on which the meter is installed
     * @return true if deletion was successfull, otherwise false
     */
    public boolean deleteMeters(Long meterId, Long burst, Long bandwidth, String nodeID) {

        NodeId nodeId = new NodeId(nodeID);
        VTNMeterUtils vtnMeterUtils = new VTNMeterUtils(new MeterId(meterId), bandwidth, burst);

        RemoveMeterInput addMeterInput = vtnMeterUtils.createRemoveMeterInput(nodeId);


        Future<RpcResult<RemoveMeterOutput>> future = salMeterService.removeMeter(addMeterInput);

        try {

            if (future.get().isSuccessful()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }
}
