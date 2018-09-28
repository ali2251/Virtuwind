package eu.virtuwind.resourcemanager.impl;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

import com.sun.org.apache.xpath.internal.SourceTree;
import eu.virtuwind.registryhandler.impl.ResourceManagerRegistryImpl;
import org.opendaylight.openflowplugin.api.OFConstants;
//import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev100924.Ipv4Prefix;
//import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev100924.PortNumber;

import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.PortNumber;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Prefix;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Uri;

//import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev100924.Uri;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.MacAddress;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.action.*;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.registryhandler.referencemonitor.rev170112.Timestamp;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.action.output.action._case.OutputActionBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.action.pop.vlan.action._case.PopVlanActionBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.action.push.vlan.action._case.PushVlanActionBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.action.set.field._case.SetFieldBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.action.set.queue.action._case.SetQueueActionBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.list.Action;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.list.ActionBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.list.ActionKey;
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
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.instruction.list.InstructionBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.instruction.list.Instruction;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeId;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeConnectorId;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeRef;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.Nodes;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.node.NodeConnector;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.NodeKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.l2.types.rev130827.EtherType;
import org.opendaylight.yang.gen.v1.urn.opendaylight.meter.service.rev130918.*;
import org.opendaylight.yang.gen.v1.urn.opendaylight.meter.types.rev130918.MeterId;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.ethernet.match.fields.EthernetDestinationBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.ethernet.match.fields.EthernetSourceBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.ethernet.match.fields.EthernetTypeBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.match.EthernetMatchBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.match.IpMatchBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.match.ProtocolMatchFieldsBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.match.VlanMatchBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.match.layer._3.match.Ipv4MatchBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.match.layer._4.match.TcpMatchBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.vlan.match.fields.VlanIdBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.oxm.rev150225.MplsLabel;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.oxm.rev150225.match.entry.value.grouping.match.entry.value.mpls.bos._case.MplsBosBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.openflow.protocol.rev130731.multipart.reply.multipart.reply.body.multipart.reply.meter.config._case.multipart.reply.meter.config.MeterConfig;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.LinkBuilder;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.registryhandler.resourcemanagerreg.rev161017.ResourceManagerStore;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.registryhandler.resourcemanagerreg.rev161017.ResourceManagerStoreBuilder;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.registryhandler.resourcemanagerreg.rev161017.resource.manager.store.EmbeddingIdCookie;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.registryhandler.resourcemanagerreg.rev161017.resource.manager.store.EmbeddingIdCookieBuilder;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.registryhandler.resourcemanagerreg.rev161017.resource.manager.store.EmbeddingIdCookieKey;

import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.Link;
import org.opendaylight.yang.gen.v1.urn.opendaylight.l2.types.rev130827.VlanId;

import com.google.common.collect.ImmutableList;

/**
 * class PathConfigurator to configure the paths by installing flows
 * privides methods to install the flows and remove the paths
 * {@link PathConfigurator}
 */
public class PathConfigurator {

    //Logger for debugging
    private static final Logger LOG = LoggerFactory.getLogger(PathConfigurator.class);

    //salflow service to add/remove  the flow in the network
    private static SalFlowService salFlowService;

    private static SalMeterService salMeterService;

    //counter for the path ID, static to ensure uniqueness
    private static long id = 0;

    //list of paths to store all the paths created

    private static PathConfigurator pathConfigurator;

    //flow cookie which serves as unique id for flows
    private static AtomicLong flowCookieInc = new AtomicLong(0x2a00000000000000L);


    private static HashMap<Long, List<EmbeddingFlowRequest>> IdToListMap = new HashMap<>();

    private static List<EmbeddingIdCookie> listOfEmbeddingId; //readAllResourceManagerDatastore


    /**
     * Constructor to initialise the SalFlowService and SalMeterService to be able to add flows
     *
     * @param salFlowService1  {@link SalFlowService}
     * @param salMeterService1 {@link SalMeterService}
     */
    public PathConfigurator(SalFlowService salFlowService1, SalMeterService salMeterService1) {

        salFlowService = salFlowService1;
        salMeterService = salMeterService1;
        pathConfigurator = this;
        listOfEmbeddingId = ResourceManagerRegistryImpl.getInstance().readAllResourceManagerDatastore();

        if(listOfEmbeddingId != null) {
            Long presentId = 0L;
            Long pastId = 0L;

            List<EmbeddingFlowRequest> allRequests = new ArrayList<>();


            for(int i = 0; i<listOfEmbeddingId.size(); ++i ) {

                //EmbeddingIdCookie

                if(i == 0) {
                    pastId = listOfEmbeddingId.get(i).getEmbeddingId();
                } else {
                    pastId = listOfEmbeddingId.get(i-1).getEmbeddingId();
                }

                presentId = listOfEmbeddingId.get(i).getEmbeddingId();
                Long cookie = listOfEmbeddingId.get(i).getFlowCookie();
                String switchId = listOfEmbeddingId.get(i).getFlowSwitchId();
                Long meterId = listOfEmbeddingId.get(i).getMeterId();
                Long burst = listOfEmbeddingId.get(i).getMeterBurst();
                Long bandwidth = listOfEmbeddingId.get(i).getMeterBandwidth();

                allRequests.add(new EmbeddingFlowRequest(switchId, BigInteger.valueOf(cookie), meterId, burst, bandwidth));

                if(!presentId.equals(pastId) && i != listOfEmbeddingId.size()-1 ) {
                    //finished one id, reset
                    IdToListMap.put(pastId, allRequests);
                    allRequests.clear();
                } else if(i == listOfEmbeddingId.size()-1) {
                    //last element, must be added to the list
                    IdToListMap.put(presentId, allRequests);
                    allRequests.clear();
                }





            }


        }

    }

    public static PathConfigurator getInstance() {
        return pathConfigurator;

    }

    public static Long createDisjointFlows(org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.Match matchingStructure,
                                           List<List<Link>> links,
                                           List<List<Long>> queues,
                                           Long meterId,
                                           NodeId srcNodeId, NodeId dstNodeId,
                                           NodeConnectorId srcNodePort, NodeConnectorId dstNodePort,
                                           Long bandwidth,
                                           Long burst,
                                           VlanId srcVlan,
                                           VlanId destVlan,
                                           Timestamp startTime,
                                           Timestamp endTime
    ) {


        try {


            // System.out.println(startTime.getValue());

            ++id;

            final Long finalId = id;

            String formattedStartTime = startTime.getValue().substring(0, 19);
            String formattedEndTime = endTime.getValue().substring(0, 19);




            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startDate = dateFormatter.parse(formattedStartTime); //"2012-07-06 13:05:45"
            Date endDate = dateFormatter.parse(formattedEndTime); //"2012-07-06 13:05:45"

            //Now create the time and schedule it
            Timer timer = new Timer();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    timer.schedule(new TimerTask() {
                                       @Override
                                       public void run() {

                                           // final Long pathId = createDisjointFlows(matchingStructure, links, queues, meterId, srcNodeId, dstNodeId, srcNodePort, dstNodePort, burst, bandwidth, srcVlan, destVlan);

                                           try {



                                               //	System.out.println("createDisjointFlows called");


                                               List<EmbeddingFlowRequest> listofFlowRequests = new ArrayList<>();


                                               Flow first = null;
                                               if (meterId == null) {

                                                   first = createSourceFlowWithQueuesWithMeterForDisjoint(srcNodePort, matchingStructure, null, links, queues, srcVlan);

                                                   listofFlowRequests.add(new EmbeddingFlowRequest(srcNodeId.getValue(), first.getCookie().getValue(), meterId, burst, bandwidth));

                                                   AddFlowInput srcFlow = getAddFlowInputToSend(srcNodeId.getValue(), first);
                                                   Future<RpcResult<AddFlowOutput>> flowResult = salFlowService.addFlow(srcFlow);


                                                   if (flowResult.get().isSuccessful()) {
                                                       LOG.info("Flow successfully added");

                                                   } else {

                                                       LOG.error("Flow was not added and error is: " + flowResult.get().getErrors().toString());
                                                   }


                                               } else {



                                                   VTNMeterUtils vtnMeterUtils = new VTNMeterUtils(new MeterId(meterId), bandwidth, burst);

                                                   AddMeterInput addMeterInput = vtnMeterUtils.createAddMeterInput(srcNodeId);


                                                   Future<RpcResult<AddMeterOutput>> future = salMeterService.addMeter(addMeterInput);



                                                   if (future.get().isSuccessful()) {

                                                       first = createSourceFlowWithQueuesWithMeterForDisjoint(srcNodePort, matchingStructure, meterId, links, queues, srcVlan);

                                                       listofFlowRequests.add(new EmbeddingFlowRequest(srcNodeId.getValue(), first.getCookie().getValue(), meterId, burst, bandwidth));

                                                       AddFlowInput srcFlow = getAddFlowInputToSend(srcNodeId.getValue(), first);
                                                       Future<RpcResult<AddFlowOutput>> flowResult = salFlowService.addFlow(srcFlow);


                                                       if (flowResult.get().isSuccessful()) {
                                                           LOG.info("Flow successfully added");

                                                       } else {

                                                           LOG.error("Flow was not added and error is: " + flowResult.get().getErrors().toString());
                                                       }

                                                   } else {

                                                       LOG.error("Error with installing a meter: ----> " + future.get().getErrors());
                                                   }

                                               }

                                               Flow last = createDestinationFlowWithQueuesWithoutMeter(dstNodePort, matchingStructure, destVlan);


                                               listofFlowRequests.add(new EmbeddingFlowRequest(dstNodeId.getValue(), last.getCookie().getValue(), meterId, burst, bandwidth) );


                                               AddFlowInput destFlow = getAddFlowInputToSend(dstNodeId.getValue(), last);
                                               salFlowService.addFlow(destFlow);

                                               NodeConnectorId inPortFirst = new NodeConnectorId(links.get(0).get(0).getDestination().getDestTp().getValue().split(":")[2]);
                                               NodeConnectorId inPortSecond = new NodeConnectorId(links.get(1).get(0).getDestination().getDestTp().getValue().split(":")[2]);


                                               links.get(0).remove(links.get(0).get(0));
                                               links.get(1).remove(links.get(1).get(0));

                                               queues.get(0).remove(queues.get(0).get(0));
                                               queues.get(1).remove(queues.get(1).get(0));

                                               for (int i = 0; i < links.size(); i++) {

                                                   List<Link> listOfLinks = links.get(i);
                                                   List<Long> queuesForLinkList = queues.get(i);

                                                   List<Flow> flows = new ArrayList<>();

                                                   if (i == 0) {
                                                       flows = createFlows(matchingStructure, listOfLinks, queuesForLinkList, inPortFirst);

                                                   } else {
                                                       flows = createFlows(matchingStructure, listOfLinks, queuesForLinkList, inPortSecond);
                                                   }


                                                   for (int j = 0; j < flows.size(); j++) {


                                                       Flow createdFlow = flows.get(j);


                                                       // the first link should be ignored
                                                       String nodeId = listOfLinks.get(j)
                                                               .getSource().getSourceNode().getValue();

                                                       //get the add input flow to add to sal

                                                       listofFlowRequests.add(new EmbeddingFlowRequest(nodeId, createdFlow.getCookie().getValue(), meterId, burst, bandwidth));

                                                       AddFlowInput flow = getAddFlowInputToSend(nodeId, createdFlow);

                                                       LOG.info("Adding flows to salflow");

                                                       // add flow to sal
                                                       salFlowService.addFlow(flow);
                                                       LOG.info("Flow successfully added");

                                                   }

                                               }


                                               IdToListMap.put(finalId, listofFlowRequests);

                                               populateEmbeddingId(finalId, listofFlowRequests);


                                           } catch (Exception e) {
                                               LOG.error("Exception Thrown by Resource Manager Disjoint Flows");
                                               e.printStackTrace();

                                           }


                                           timer.schedule(new TimerTask() {
                                               @Override
                                               public void run() {
                                                   removePath(finalId);
                                               }
                                           }, endDate);


                                       }
                                   }
                            , startDate);

                }
            }).start();

            return finalId;


        } catch (Exception e) {
            LOG.error("Exception Thrown by Resource Manager Disjoint Flows");
            e.printStackTrace();
            return -1L;
        }


    }

    public static Long createDisjointFlows(org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.Match matchingStructure,
                                           List<List<Link>> links,
                                           List<List<Long>> queues,
                                           Long meterId,
                                           NodeId srcNodeId, NodeId dstNodeId,
                                           NodeConnectorId srcNodePort, NodeConnectorId dstNodePort,
                                           Long bandwidth,
                                           Long burst,
                                           VlanId srcVlan,
                                           VlanId destVlan
    ) {


        try {

            //	System.out.println("createDisjointFlows called");

            ++id;

            List<EmbeddingFlowRequest> listofFlowRequests = new ArrayList<>();


            Flow first = null;
            if (meterId == null) {

                first = createSourceFlowWithQueuesWithMeterForDisjoint(srcNodePort, matchingStructure, null, links, queues, srcVlan);

                listofFlowRequests.add(new EmbeddingFlowRequest(srcNodeId.getValue(), first.getCookie().getValue(), meterId, burst, bandwidth));

                AddFlowInput srcFlow = getAddFlowInputToSend(srcNodeId.getValue(), first);
                Future<RpcResult<AddFlowOutput>> flowResult = salFlowService.addFlow(srcFlow);


                if (flowResult.get().isSuccessful()) {
                    LOG.info("Flow successfully added");

                } else {

                    LOG.error("Flow was not added and error is: " + flowResult.get().getErrors().toString());
                }


            } else {


                VTNMeterUtils vtnMeterUtils = new VTNMeterUtils(new MeterId(meterId), bandwidth, burst);

                AddMeterInput addMeterInput = vtnMeterUtils.createAddMeterInput(srcNodeId);


                Future<RpcResult<AddMeterOutput>> future = salMeterService.addMeter(addMeterInput);



                if (future.get().isSuccessful()) {

                    first = createSourceFlowWithQueuesWithMeterForDisjoint(srcNodePort, matchingStructure, meterId, links, queues, srcVlan);

                    listofFlowRequests.add(new EmbeddingFlowRequest(srcNodeId.getValue(), first.getCookie().getValue(), meterId, burst, bandwidth));

                    AddFlowInput srcFlow = getAddFlowInputToSend(srcNodeId.getValue(), first);
                    Future<RpcResult<AddFlowOutput>> flowResult = salFlowService.addFlow(srcFlow);


                    if (flowResult.get().isSuccessful()) {
                        LOG.info("Flow successfully added");

                    } else {

                        LOG.error("Flow was not added and error is: " + flowResult.get().getErrors().toString());
                    }

                } else {

                    LOG.error("Error with installing a meter: ----> " + future.get().getErrors());
                }

            }

            Flow last = createDestinationFlowWithQueuesWithoutMeter(dstNodePort, matchingStructure, destVlan);


            listofFlowRequests.add(new EmbeddingFlowRequest(dstNodeId.getValue(), last.getCookie().getValue(), meterId, burst, bandwidth));


            AddFlowInput destFlow = getAddFlowInputToSend(dstNodeId.getValue(), last);
            salFlowService.addFlow(destFlow);

            NodeConnectorId inPortFirst = new NodeConnectorId(links.get(0).get(0).getDestination().getDestTp().getValue().split(":")[2]);
            NodeConnectorId inPortSecond = new NodeConnectorId(links.get(1).get(0).getDestination().getDestTp().getValue().split(":")[2]);


            links.get(0).remove(links.get(0).get(0));
            links.get(1).remove(links.get(1).get(0));

            queues.get(0).remove(queues.get(0).get(0));
            queues.get(1).remove(queues.get(1).get(0));

            for (int i = 0; i < links.size(); i++) {

                List<Link> listOfLinks = links.get(i);
                List<Long> queuesForLinkList = queues.get(i);

                List<Flow> flows = new ArrayList<>();

                if (i == 0) {
                    flows = createFlows(matchingStructure, listOfLinks, queuesForLinkList, inPortFirst);

                } else {
                    flows = createFlows(matchingStructure, listOfLinks, queuesForLinkList, inPortSecond);
                }


                for (int j = 0; j < flows.size(); j++) {


                    Flow createdFlow = flows.get(j);


                    // the first link should be ignored
                    String nodeId = listOfLinks.get(j)
                            .getSource().getSourceNode().getValue();

                    //get the add input flow to add to sal

                    listofFlowRequests.add(new EmbeddingFlowRequest(nodeId, createdFlow.getCookie().getValue(), meterId, burst, bandwidth));

                    AddFlowInput flow = getAddFlowInputToSend(nodeId, createdFlow);

                    LOG.info("Adding flows to salflow");

                    // add flow to sal
                    salFlowService.addFlow(flow);
                    LOG.info("Flow successfully added");

                }

            }


            IdToListMap.put(id, listofFlowRequests);
            populateEmbeddingId(id, listofFlowRequests);

            return id;

        } catch (Exception e) {
            LOG.error("Exception Thrown by Resource Manager Disjoint Flows");
            e.printStackTrace();
            return null;
        }


    }

    public static Long createBestEffortDisjointFlows(org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.Match matchingStructure,
                                                     List<List<Link>> links,
                                                     Long meterId,
                                                     NodeId srcNodeId, NodeId dstNodeId,
                                                     NodeConnectorId srcNodePort, NodeConnectorId dstNodePort,
                                                     Long bandwidth,
                                                     Long burst,
                                                     VlanId srcVlan,
                                                     VlanId destVlan,
                                                     Timestamp startTime,
                                                     Timestamp endTime
    ) {


        try {

            ++id;

            final Long finalId = id;

                       // System.out.println(startTime.getValue());

            String formattedStartTime = startTime.getValue().substring(0, 19);
            String formattedEndTime = endTime.getValue().substring(0, 19);


            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startDate = dateFormatter.parse(formattedStartTime); //"2012-07-06 13:05:45"
            Date endDate = dateFormatter.parse(formattedEndTime); //"2012-07-06 13:05:45"

            //Now create the time and schedule it
            Timer timer = new Timer();

            //Use this if you want to execute it once

            new Thread(new Runnable() {
                @Override
                public void run() {
                    timer.schedule(new TimerTask() {
                                       @Override
                                       public void run() {

                                           try {

                                               //	System.out.println("createDisjointFlows called");



                                               List<EmbeddingFlowRequest> listofFlowRequests = new ArrayList<>();


                                               Flow first = null;

                                               if (meterId == null) {

                                                   first = createBestEffortSourceFlowWithQueuesWithMeterForDisjoint(srcNodePort, matchingStructure, null, links, srcVlan);

                                                   listofFlowRequests.add(new EmbeddingFlowRequest(srcNodeId.getValue(), first.getCookie().getValue(), meterId, burst, bandwidth));

                                                   AddFlowInput srcFlow = getAddFlowInputToSend(srcNodeId.getValue(), first);
                                                   Future<RpcResult<AddFlowOutput>> flowResult = salFlowService.addFlow(srcFlow);


                                                   if (flowResult.get().isSuccessful()) {
                                                       LOG.info("Flow successfully added");

                                                   } else {

                                                       LOG.error("Flow was not added and error is: " + flowResult.get().getErrors().toString());
                                                   }


                                               } else {


                                                   VTNMeterUtils vtnMeterUtils = new VTNMeterUtils(new MeterId(meterId), bandwidth, burst);

                                                   AddMeterInput addMeterInput = vtnMeterUtils.createAddMeterInput(srcNodeId);


                                                   Future<RpcResult<AddMeterOutput>> future = salMeterService.addMeter(addMeterInput);


                                                   if (future.get().isSuccessful()) {

                                                       first = createBestEffortSourceFlowWithQueuesWithMeterForDisjoint(srcNodePort, matchingStructure, meterId, links, srcVlan);

                                                       listofFlowRequests.add(new EmbeddingFlowRequest(srcNodeId.getValue(), first.getCookie().getValue(), meterId, burst, bandwidth));

                                                       AddFlowInput srcFlow = getAddFlowInputToSend(srcNodeId.getValue(), first);
                                                       Future<RpcResult<AddFlowOutput>> flowResult = salFlowService.addFlow(srcFlow);


                                                       if (flowResult.get().isSuccessful()) {
                                                           LOG.info("Flow successfully added");

                                                       } else {

                                                           LOG.error("Flow was not added and error is: " + flowResult.get().getErrors().toString());
                                                       }

                                                   } else {

                                                       LOG.error("Error with installing a meter: ----> " + future.get().getErrors());
                                                   }

                                               }

                                               Flow last = createDestinationFlowWithQueuesWithoutMeter(dstNodePort, matchingStructure, destVlan);


                                               listofFlowRequests.add(new EmbeddingFlowRequest(dstNodeId.getValue(), last.getCookie().getValue(), meterId, burst, bandwidth));


                                               AddFlowInput destFlow = getAddFlowInputToSend(dstNodeId.getValue(), last);
                                               salFlowService.addFlow(destFlow);

                                               NodeConnectorId inPortFirst = new NodeConnectorId(links.get(0).get(0).getDestination().getDestTp().getValue().split(":")[2]);
                                               NodeConnectorId inPortSecond = new NodeConnectorId(links.get(1).get(0).getDestination().getDestTp().getValue().split(":")[2]);


                                               links.get(0).remove(links.get(0).get(0));
                                               links.get(1).remove(links.get(1).get(0));


                                               for (int i = 0; i < links.size(); i++) {

                                                   List<Link> listOfLinks = links.get(i);

                                                   List<Flow> flows = new ArrayList<>();

                                                   if (i == 0) {
                                                       flows = createFlowsWithoutQueues(matchingStructure, listOfLinks, inPortFirst);

                                                   } else {
                                                       flows = createFlowsWithoutQueues(matchingStructure, listOfLinks, inPortSecond);
                                                   }


                                                   for (int j = 0; j < flows.size(); j++) {


                                                       Flow createdFlow = flows.get(j);


                                                       // the first link should be ignored
                                                       String nodeId = listOfLinks.get(j)
                                                               .getSource().getSourceNode().getValue();

                                                       //get the add input flow to add to sal

                                                       listofFlowRequests.add(new EmbeddingFlowRequest(nodeId, createdFlow.getCookie().getValue(), meterId, burst, bandwidth));

                                                       AddFlowInput flow = getAddFlowInputToSend(nodeId, createdFlow);

                                                       LOG.info("Adding flows to salflow");

                                                       // add flow to sal
                                                       salFlowService.addFlow(flow);
                                                       LOG.info("Flow successfully added");

                                                   }

                                               }


                                               IdToListMap.put(finalId, listofFlowRequests);
                                               populateEmbeddingId(finalId, listofFlowRequests);


                                           } catch (Exception e) {
                                               LOG.error("Exception Thrown by Resource Manager Disjoint Flows");
                                               e.printStackTrace();

                                           }


                                           timer.schedule(new TimerTask() {
                                               @Override
                                               public void run() {
                                                   removePath(finalId);
                                               }
                                           }, endDate);


                                       }
                                   }
                            , startDate);
                }
            }).start();


            return finalId;


        } catch (Exception e) {
            LOG.error("Exception Thrown by Resource Manager Disjoint Flows");
            e.printStackTrace();
            return -1L;
        }


    }


    public static Long createBestEffortDisjointFlows(org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.Match matchingStructure,
                                                     List<List<Link>> links,
                                                     Long meterId,
                                                     NodeId srcNodeId, NodeId dstNodeId,
                                                     NodeConnectorId srcNodePort, NodeConnectorId dstNodePort,
                                                     Long bandwidth,
                                                     Long burst,
                                                     VlanId srcVlan,
                                                     VlanId destVlan

    ) {


        try {

            //	System.out.println("createDisjointFlows called");

            ++id;

            List<EmbeddingFlowRequest> listofFlowRequests = new ArrayList<>();


            Flow first = null;

            if (meterId == null) {

                first = createBestEffortSourceFlowWithQueuesWithMeterForDisjoint(srcNodePort, matchingStructure, null, links, srcVlan);
                listofFlowRequests.add(new EmbeddingFlowRequest(srcNodeId.getValue(), first.getCookie().getValue(), meterId, burst, bandwidth));

                AddFlowInput srcFlow = getAddFlowInputToSend(srcNodeId.getValue(), first);
                Future<RpcResult<AddFlowOutput>> flowResult = salFlowService.addFlow(srcFlow);


                if (flowResult.get().isSuccessful()) {
                    LOG.info("Flow successfully added");

                } else {

                    LOG.error("Flow was not added and error is: " + flowResult.get().getErrors().toString());
                }


            } else {


                VTNMeterUtils vtnMeterUtils = new VTNMeterUtils(new MeterId(meterId), bandwidth, burst);

                AddMeterInput addMeterInput = vtnMeterUtils.createAddMeterInput(srcNodeId);


                Future<RpcResult<AddMeterOutput>> future = salMeterService.addMeter(addMeterInput);


                if (future.get().isSuccessful()) {

                    first = createBestEffortSourceFlowWithQueuesWithMeterForDisjoint(srcNodePort, matchingStructure, meterId, links, srcVlan);

                    listofFlowRequests.add(new EmbeddingFlowRequest(srcNodeId.getValue(), first.getCookie().getValue(), meterId, burst, bandwidth));

                    AddFlowInput srcFlow = getAddFlowInputToSend(srcNodeId.getValue(), first);
                    Future<RpcResult<AddFlowOutput>> flowResult = salFlowService.addFlow(srcFlow);


                    if (flowResult.get().isSuccessful()) {
                        LOG.info("Flow successfully added");

                    } else {

                        LOG.error("Flow was not added and error is: " + flowResult.get().getErrors().toString());
                    }

                } else {

                    LOG.error("Error with installing a meter: ----> " + future.get().getErrors());
                }

            }

            Flow last = createDestinationFlowWithQueuesWithoutMeter(dstNodePort, matchingStructure, destVlan);


            listofFlowRequests.add(new EmbeddingFlowRequest(dstNodeId.getValue(), last.getCookie().getValue(), meterId, burst, bandwidth));


            AddFlowInput destFlow = getAddFlowInputToSend(dstNodeId.getValue(), last);
            salFlowService.addFlow(destFlow);

            NodeConnectorId inPortFirst = new NodeConnectorId(links.get(0).get(0).getDestination().getDestTp().getValue().split(":")[2]);
            NodeConnectorId inPortSecond = new NodeConnectorId(links.get(1).get(0).getDestination().getDestTp().getValue().split(":")[2]);


            links.get(0).remove(links.get(0).get(0));
            links.get(1).remove(links.get(1).get(0));


            for (int i = 0; i < links.size(); i++) {

                List<Link> listOfLinks = links.get(i);

                List<Flow> flows = new ArrayList<>();

                if (i == 0) {
                    flows = createFlowsWithoutQueues(matchingStructure, listOfLinks, inPortFirst);

                } else {
                    flows = createFlowsWithoutQueues(matchingStructure, listOfLinks, inPortSecond);
                }


                for (int j = 0; j < flows.size(); j++) {


                    Flow createdFlow = flows.get(j);


                    // the first link should be ignored
                    String nodeId = listOfLinks.get(j)
                            .getSource().getSourceNode().getValue();

                    //get the add input flow to add to sal

                    listofFlowRequests.add(new EmbeddingFlowRequest(nodeId, createdFlow.getCookie().getValue(), meterId, burst, bandwidth));

                    AddFlowInput flow = getAddFlowInputToSend(nodeId, createdFlow);

                    LOG.info("Adding flows to salflow");

                    // add flow to sal
                    salFlowService.addFlow(flow);
                    LOG.info("Flow successfully added");

                }

            }


            IdToListMap.put(id, listofFlowRequests);
            populateEmbeddingId(id, listofFlowRequests);

            return id;

        } catch (Exception e) {
            LOG.error("Exception Thrown by Resource Manager Disjoint Flows");
            e.printStackTrace();
            return -1L;
        }


    }

    private static Flow createSourceFlowWithQueuesWithMeterForDisjoint(NodeConnectorId srcNodeConnectorId,
                                                                       org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.Match match1,
                                                                       Long meterId, List<List<Link>> links, List<List<Long>> queues, VlanId srcVlan) {
        FlowBuilder flowBuilder = new FlowBuilder()
                .setTableId((short) 0)
                .setFlowName("random");

        int pp = 0;


        //Flow ID
        flowBuilder.setId(new FlowId(Long.toString(flowBuilder.hashCode())));

        MatchBuilder matchBuilder = new MatchBuilder();

        matchBuilder.setEthernetMatch(match1.getEthernetMatch())
                .setLayer3Match(match1.getLayer3Match())
                .setInPort(srcNodeConnectorId);


        if (match1.getIpMatch() != null && match1.getLayer4Match() != null) {

            //ip and layer 4 are not null
            matchBuilder.setIpMatch(match1.getIpMatch())
                    .setLayer4Match(match1.getLayer4Match());


        }
        if (match1.getVlanMatch() != null) {
            matchBuilder.setVlanMatch(match1.getVlanMatch());
        }


        Match match = matchBuilder
                .build();

        ActionBuilder actionBuilder = new ActionBuilder();

        //Actions
        //currently changing tos and sending to output connector


        List<List<Action>> listofactions = new ArrayList<>();

        listofactions.add(new ArrayList<Action>());


        String firstElemOfFirstList = links.get(0).get(0).getSource().getSourceTp().getValue().split(":")[2];

        String firstElemOfSecondList = links.get(1).get(0).getSource().getSourceTp().getValue().split(":")[2];


        if (srcVlan != null) {

            PopVlanActionCaseBuilder popVlanBuilder = new PopVlanActionCaseBuilder();
            popVlanBuilder.setPopVlanAction(new PopVlanActionBuilder().build());

            Action vlanAction = actionBuilder.setOrder(0).setAction(popVlanBuilder.build()).build();

            Action outputNodeConnectorActionsrcFirstList = actionBuilder
                    .setOrder(2).setAction(new OutputActionCaseBuilder()
                            .setOutputAction(new OutputActionBuilder()
                                    .setOutputNodeConnector(new Uri(firstElemOfFirstList))
                                    .build())
                            .build())
                    .build();

            Action outputNodeConnectorActionsrcSecondList = actionBuilder
                    .setOrder(3).setAction(new OutputActionCaseBuilder()
                            .setOutputAction(new OutputActionBuilder()
                                    .setOutputNodeConnector(new Uri(firstElemOfSecondList))
                                    .build())
                            .build())
                    .build();

            Action queueAction = actionBuilder
                    .setOrder(1).setAction(new SetQueueActionCaseBuilder()
                            .setSetQueueAction(new SetQueueActionBuilder()
                                    .setQueueId(queues.get(0).get(0))
                                    .build())
                            .build())

                    .build();

            listofactions.get(0).add(vlanAction);
            listofactions.get(0).add(outputNodeConnectorActionsrcFirstList);
            listofactions.get(0).add(outputNodeConnectorActionsrcSecondList);
            listofactions.get(0).add(queueAction);


        } else {


            Action outputNodeConnectorActionsrcFirstList = actionBuilder
                    .setOrder(1).setAction(new OutputActionCaseBuilder()
                            .setOutputAction(new OutputActionBuilder()
                                    .setOutputNodeConnector(new Uri(firstElemOfFirstList))
                                    .build())
                            .build())
                    .build();

            Action outputNodeConnectorActionsrcSecondList = actionBuilder
                    .setOrder(2).setAction(new OutputActionCaseBuilder()
                            .setOutputAction(new OutputActionBuilder()
                                    .setOutputNodeConnector(new Uri(firstElemOfSecondList))
                                    .build())
                            .build())
                    .build();

            Action queueAction = actionBuilder
                    .setOrder(0).setAction(new SetQueueActionCaseBuilder()
                            .setSetQueueAction(new SetQueueActionBuilder()
                                    .setQueueId(queues.get(0).get(0))
                                    .build())
                            .build())

                    .build();


            listofactions.get(0).add(outputNodeConnectorActionsrcFirstList);
            listofactions.get(0).add(outputNodeConnectorActionsrcSecondList);
            listofactions.get(0).add(queueAction);

            //ApplyActions
        }

        LOG.info("applying action");
        ApplyActions applyActions = new ApplyActionsBuilder().setAction(listofactions.get(0)).build();

        //Instruction
        Instruction applyActionsInstruction = new InstructionBuilder() //
                .setOrder(0).setInstruction(new ApplyActionsCaseBuilder()//
                        .setApplyActions(applyActions) //
                        .build())

                .build();

        List<Instruction> instructionList = new ArrayList<>();

        if (meterId == null) {

            instructionList.add(applyActionsInstruction);


        } else {

            Instruction meterInstruction = new InstructionBuilder()
                    .setOrder(1).setInstruction((new MeterCaseBuilder()
                            .setMeter(new MeterBuilder()
                                    .setMeterId(new MeterId(meterId))
                                    .build())
                            .build()))
                    .build();


            instructionList.add(applyActionsInstruction);
            instructionList.add(meterInstruction);

        }


        Instructions applyInstructions = new InstructionsBuilder()
                .setInstruction(instructionList)
                .build();


        // Put our Instruction in a list of Instructions

        LOG.info("adding flow");
        Flow flow = flowBuilder
                .setMatch(match)
                .setBufferId(OFConstants.OFP_NO_BUFFER)
                .setInstructions(applyInstructions)
                .setPriority(1000)
                .setCookie(new FlowCookie(BigInteger.valueOf(flowCookieInc.getAndIncrement())))
                .setFlags(new FlowModFlags(false, false, false, false, false)).build();

        return flow;
    }


    private static Flow createBestEffortSourceFlowWithQueuesWithMeterForDisjoint(NodeConnectorId srcNodeConnectorId,
                                                                                 org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.Match match1,
                                                                                 Long meterId, List<List<Link>> links,
                                                                                 VlanId srcVlan
    ) {
        FlowBuilder flowBuilder = new FlowBuilder()
                .setTableId((short) 0)
                .setFlowName("random");

        int pp = 0;


        //Flow ID
        flowBuilder.setId(new FlowId(Long.toString(flowBuilder.hashCode())));

        MatchBuilder matchBuilder = new MatchBuilder();

        matchBuilder.setEthernetMatch(match1.getEthernetMatch())
                .setLayer3Match(match1.getLayer3Match())
                .setInPort(srcNodeConnectorId);


        if (match1.getIpMatch() != null && match1.getLayer4Match() != null) {

            //ip and layer 4 are not null
            matchBuilder.setIpMatch(match1.getIpMatch())
                    .setLayer4Match(match1.getLayer4Match());


        }
        if (match1.getVlanMatch() != null) {
            matchBuilder.setVlanMatch(match1.getVlanMatch());
        }


        Match match = matchBuilder
                .build();

        ActionBuilder actionBuilder = new ActionBuilder();

        //Actions
        //currently changing tos and sending to output connector


        List<List<Action>> listofactions = new ArrayList<>();

        listofactions.add(new ArrayList<Action>());


        String firstElemOfFirstList = links.get(0).get(0).getSource().getSourceTp().getValue().split(":")[2];

        String firstElemOfSecondList = links.get(1).get(0).getSource().getSourceTp().getValue().split(":")[2];


        if (srcVlan != null) {

            PopVlanActionCaseBuilder popVlanBuilder = new PopVlanActionCaseBuilder();
            popVlanBuilder.setPopVlanAction(new PopVlanActionBuilder().build());

            Action vlanAction = actionBuilder.setOrder(0).setAction(popVlanBuilder.build()).build();

            Action outputNodeConnectorActionsrcFirstList = actionBuilder
                    .setOrder(1).setAction(new OutputActionCaseBuilder()
                            .setOutputAction(new OutputActionBuilder()
                                    .setOutputNodeConnector(new Uri(firstElemOfFirstList))
                                    .build())
                            .build())
                    .build();

            Action outputNodeConnectorActionsrcSecondList = actionBuilder
                    .setOrder(2).setAction(new OutputActionCaseBuilder()
                            .setOutputAction(new OutputActionBuilder()
                                    .setOutputNodeConnector(new Uri(firstElemOfSecondList))
                                    .build())
                            .build())
                    .build();


            listofactions.get(0).add(vlanAction);
            listofactions.get(0).add(outputNodeConnectorActionsrcFirstList);
            listofactions.get(0).add(outputNodeConnectorActionsrcSecondList);


        } else {


            Action outputNodeConnectorActionsrcFirstList = actionBuilder
                    .setOrder(1).setAction(new OutputActionCaseBuilder()
                            .setOutputAction(new OutputActionBuilder()
                                    .setOutputNodeConnector(new Uri(firstElemOfFirstList))
                                    .build())
                            .build())
                    .build();

            Action outputNodeConnectorActionsrcSecondList = actionBuilder
                    .setOrder(2).setAction(new OutputActionCaseBuilder()
                            .setOutputAction(new OutputActionBuilder()
                                    .setOutputNodeConnector(new Uri(firstElemOfSecondList))
                                    .build())
                            .build())
                    .build();


            listofactions.get(0).add(outputNodeConnectorActionsrcFirstList);
            listofactions.get(0).add(outputNodeConnectorActionsrcSecondList);


        }
        //ApplyActions


        LOG.info("applying action");
        ApplyActions applyActions = new ApplyActionsBuilder().setAction(listofactions.get(0)).build();

        //Instruction
        Instruction applyActionsInstruction = new InstructionBuilder() //
                .setOrder(0).setInstruction(new ApplyActionsCaseBuilder()//
                        .setApplyActions(applyActions) //
                        .build())

                .build();

        List<Instruction> instructionList = new ArrayList<>();

        if (meterId == null) {

            instructionList.add(applyActionsInstruction);


        } else {

            Instruction meterInstruction = new InstructionBuilder()
                    .setOrder(1).setInstruction((new MeterCaseBuilder()
                            .setMeter(new MeterBuilder()
                                    .setMeterId(new MeterId(meterId))
                                    .build())
                            .build()))
                    .build();


            instructionList.add(applyActionsInstruction);
            instructionList.add(meterInstruction);

        }


        Instructions applyInstructions = new InstructionsBuilder()
                .setInstruction(instructionList)
                .build();


        // Put our Instruction in a list of Instructions

        LOG.info("adding flow");
        Flow flow = flowBuilder
                .setMatch(match)
                .setBufferId(OFConstants.OFP_NO_BUFFER)
                .setInstructions(applyInstructions)
                .setPriority(1000)
                .setCookie(new FlowCookie(BigInteger.valueOf(flowCookieInc.getAndIncrement())))
                .setFlags(new FlowModFlags(false, false, false, false, false)).build();

        return flow;
    }


    public static Long createAndSendFlow(org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.Match endpointMatch,
                                         List<org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.Link> physicalPathLinks,
                                         List<Long> queuesOnPath,
                                         Long meterId,
                                         org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeId srcNode,
                                         org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeId destNode,
                                         org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeConnectorId srcNodePort,
                                         org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeConnectorId destNodePort,
                                         Long bandwidth,
                                         Long burst,
                                         VlanId srcVlan,
                                         VlanId destVlan
    ) {

        try {

            LOG.info("Real time flow method called");

            ++id;


            // System.out.println("create and send flow for real time called");


            LOG.info("endpoint match recieved is {}" + endpointMatch.toString());

            LOG.info("meter is {}" + meterId);
            LOG.info("source node is: {}  and port {} " + srcNode.getValue() + srcNodePort.getValue());

            LOG.info("destination node is  {} and port is {}" + destNode.getValue() + destNodePort.getValue());

            LOG.info("Physical path links on the path: " + physicalPathLinks);

           // LOG.info("Source VLAN : " + srcVlan.getValue() );
           // LOG.info("Dest VLAN: " + destVlan.getValue());

            List<EmbeddingFlowRequest> listofFlowRequests = new ArrayList<>();

            Flow sourceFlowWithMeter = null;

            if (meterId == null) {
                sourceFlowWithMeter = createSourceFlowWithQueuesWithMeter(srcNodePort, endpointMatch, null, physicalPathLinks, queuesOnPath, srcVlan);
            } else {

                VTNMeterUtils vtnMeterUtils = new VTNMeterUtils(new MeterId(meterId), bandwidth, burst);

                AddMeterInput addMeterInput = vtnMeterUtils.createAddMeterInput(srcNode);


                Future<RpcResult<AddMeterOutput>> future = salMeterService.addMeter(addMeterInput);


                if (future.get().isSuccessful()) {
                    LOG.info("Meter " + meterId + "added successfully");
                    LOG.info("Creating Source flow with meter now");
                    sourceFlowWithMeter = createSourceFlowWithQueuesWithMeter(srcNodePort, endpointMatch, meterId, physicalPathLinks, queuesOnPath, srcVlan);
                    LOG.info("Source flow created on {} with port: {}" + srcNode.getValue() + srcNodePort.getValue());

                } else {

                    LOG.error("Error with installing a meter: ----> " + future.get().getErrors());
                }

            }

            NodeConnectorId destNodeInPort = new NodeConnectorId(physicalPathLinks.get(physicalPathLinks.size() - 1).getDestination().getDestTp().getValue().split(":")[2]);


            Flow destinationFlow = createDestinationFlowWithoutQueues(destNodePort, 0, 0, endpointMatch, destNodeInPort, destVlan); //createDestinationFlowWithQueuesWithoutMeter(destNodePort, endpointMatch);

            LOG.info("Destination flow created on {} with port: {}" + destNode.getValue() + destNodePort.getValue());


            NodeConnectorId inPortForSecond = new NodeConnectorId(physicalPathLinks.get(0).getDestination().getDestTp().getValue().split(":")[2]);


            physicalPathLinks.remove(physicalPathLinks.get(0));
            queuesOnPath.remove(queuesOnPath.get(0));

            LOG.info("Link 1 and queue 1 removed and remaining sizes are: Queue List size {} , Links list Size {}" + queuesOnPath.size() + " " + physicalPathLinks.size());

            System.out.println("Link 1 and queue 1 removed and remaining sizes are: Queue List size {} , Links list Size {}" + queuesOnPath.size() + physicalPathLinks.size());


            List<Flow> flows = createFlows(endpointMatch, physicalPathLinks, queuesOnPath, inPortForSecond);


            if (sourceFlowWithMeter != null) {

                listofFlowRequests.add(new EmbeddingFlowRequest(srcNode.getValue(), sourceFlowWithMeter.getCookie().getValue(), meterId, burst, bandwidth));
                AddFlowInput srcFlow = getAddFlowInputToSend(srcNode.getValue(), sourceFlowWithMeter);
                Future<RpcResult<AddFlowOutput>> resultFuture = salFlowService.addFlow(srcFlow);

                if (resultFuture.get().isSuccessful()) {
                    LOG.info("Source flow added successfully, The cookie is: " + srcFlow.getCookie());
                } else {

                    LOG.info("Source Flow was not added successfully");
                    LOG.info("Error is: " + resultFuture.get().getErrors().toString());
                }


            } else {

                LOG.info("Source flow is null, means meter installation was not successful");
                System.out.println("Meter Installation was not successful, hence not installing first flow");

            }


            listofFlowRequests.add(new EmbeddingFlowRequest(destNode.getValue(), destinationFlow.getCookie().getValue(), meterId, burst, bandwidth));


            AddFlowInput destFlow = getAddFlowInputToSend(destNode.getValue(), destinationFlow);
            salFlowService.addFlow(destFlow);

            LOG.info("Destination Flow added " + destFlow.toString());


            for (int i = 0; i < physicalPathLinks.size(); i++) {


                //the flow which was created
                Flow createdFlow = flows.get(i);

                String nodeId = physicalPathLinks.get(i)
                        .getSource().getSourceNode().getValue();

                //get the add input flow to add to sal

                LOG.info("Flow is being installed on " + physicalPathLinks.get(0).getSource().getSourceTp().getValue());

                listofFlowRequests.add(new EmbeddingFlowRequest(nodeId, createdFlow.getCookie().getValue(), meterId, burst, bandwidth));

                AddFlowInput flow = getAddFlowInputToSend(nodeId, createdFlow);


                // add flow to sal
                salFlowService.addFlow(flow);
            }

            IdToListMap.put(id, listofFlowRequests);
            populateEmbeddingId(id, listofFlowRequests);

            return id;

        } catch (Exception e) {
            LOG.error("Exception thrown by Resource Manager - PathConfigurator - CreateAndSendFlow and error is:   ");
            e.printStackTrace();
            return null;
        }

    }


    public static Long createAndSendFlow(org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.Match endpointMatch,
                                         List<org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.Link> physicalPathLinks,
                                         List<Long> queuesOnPath,
                                         Long meterId,
                                         org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeId srcNode,
                                         org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeId destNode,
                                         org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeConnectorId srcNodePort,
                                         org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeConnectorId destNodePort,
                                         Long bandwidth,
                                         Long burst,
                                         VlanId srcVlan,
                                         VlanId destVlan,
                                         Timestamp startTime,
                                         Timestamp endTime
    ) {

        try {

            // System.out.println(startTime.getValue());

            ++id;

            final Long finalId = id;

            String formattedStartTime = startTime.getValue().substring(0, 19);
            String formattedEndTime = endTime.getValue().substring(0, 19);


            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startDate = dateFormatter.parse(formattedStartTime); //"2012-07-06 13:05:45"
            Date endDate = dateFormatter.parse(formattedEndTime); //"2012-07-06 13:05:45"

            //Now create the time and schedule it
            Timer timer = new Timer();

            //Use this if you want to execute it once
            new Thread(new Runnable() {
                @Override
                public void run() {
                    timer.schedule(new TimerTask() {
                                       @Override
                                       public void run() {

                                           // final Long pathId = createAndSendFlow(endpointMatch, physicalPathLinks, queuesOnPath, meterId, srcNode, destNode, srcNodePort, destNodePort, burst, bandwidth, srcVlan, destVlan);

                                           try {


                                               LOG.info("Real time flow method called");


                                               // System.out.println("create and send flow for real time called");


                                               LOG.info("endpoint match recieved is {}" + endpointMatch.toString());

                                               LOG.info("meter is {}" + meterId);
                                               LOG.info("source node is: {}  and port {} " + srcNode.getValue() + srcNodePort.getValue());

                                               LOG.info("destination node is  {} and port is {}" + destNode.getValue() + destNodePort.getValue());

                                               LOG.info("Physical path links on the path: " + physicalPathLinks);

                                   //            LOG.info("Source VLAN : " + srcVlan.getValue() );
                                     ///          LOG.info("Dest VLAN: " + destVlan.getValue());

                                               List<EmbeddingFlowRequest> listofFlowRequests = new ArrayList<>();

                                               Flow sourceFlowWithMeter = null;

                                               if (meterId == null) {

                                                   sourceFlowWithMeter = createSourceFlowWithQueuesWithMeter(srcNodePort, endpointMatch, null, physicalPathLinks, queuesOnPath, srcVlan);

                                               } else {


                                                   VTNMeterUtils vtnMeterUtils = new VTNMeterUtils(new MeterId(meterId), bandwidth, burst);

                                                   AddMeterInput addMeterInput = vtnMeterUtils.createAddMeterInput(srcNode);


                                                   Future<RpcResult<AddMeterOutput>> future = salMeterService.addMeter(addMeterInput);


                                                   if (future.get().isSuccessful()) {
                                                       LOG.info("Meter " + meterId + "added successfully");
                                                       LOG.info("Creating Source flow with meter now");
                                                       sourceFlowWithMeter = createSourceFlowWithQueuesWithMeter(srcNodePort, endpointMatch, meterId, physicalPathLinks, queuesOnPath, srcVlan);
                                                       LOG.info("Source flow created on {} with port: {}" + srcNode.getValue() + srcNodePort.getValue());

                                                   } else {

                                                       LOG.error("Error with installing a meter: ----> " + future.get().getErrors());
                                                   }
                                               }


                                               NodeConnectorId destNodeInPort = new NodeConnectorId(physicalPathLinks.get(physicalPathLinks.size() - 1).getDestination().getDestTp().getValue().split(":")[2]);


                                               Flow destinationFlow = createDestinationFlowWithoutQueues(destNodePort, 0, 0, endpointMatch, destNodeInPort, destVlan); //createDestinationFlowWithQueuesWithoutMeter(destNodePort, endpointMatch);

                                               LOG.info("Destination flow created on {} with port: {}" + destNode.getValue() + destNodePort.getValue());


                                               NodeConnectorId inPortForSecond = new NodeConnectorId(physicalPathLinks.get(0).getDestination().getDestTp().getValue().split(":")[2]);


                                               physicalPathLinks.remove(physicalPathLinks.get(0));
                                               queuesOnPath.remove(queuesOnPath.get(0));

                                               LOG.info("Link 1 and queue 1 removed and remaining sizes are: Queue List size {} , Links list Size {}" + queuesOnPath.size() + " " + physicalPathLinks.size());

                                               System.out.println("Link 1 and queue 1 removed and remaining sizes are: Queue List size {} , Links list Size {}" + queuesOnPath.size() + physicalPathLinks.size());


                                               List<Flow> flows = createFlows(endpointMatch, physicalPathLinks, queuesOnPath, inPortForSecond);


                                               if (sourceFlowWithMeter != null) {

                                                   listofFlowRequests.add(new EmbeddingFlowRequest(srcNode.getValue(), sourceFlowWithMeter.getCookie().getValue(), meterId, burst, bandwidth));
                                                   AddFlowInput srcFlow = getAddFlowInputToSend(srcNode.getValue(), sourceFlowWithMeter);
                                                   Future<RpcResult<AddFlowOutput>> resultFuture = salFlowService.addFlow(srcFlow);

                                                   if (resultFuture.get().isSuccessful()) {
                                                       LOG.info("Source flow added successfully, The cookie is: " + srcFlow.getCookie());
                                                   } else {

                                                       LOG.info("Source Flow was not added successfully");
                                                       LOG.info("Error is: " + resultFuture.get().getErrors().toString());
                                                   }


                                               } else {

                                                   LOG.info("Source flow is null, means meter installation was not successful");
                                                   System.out.println("Meter Installation was not successful, hence not installing first flow");

                                               }


                                               listofFlowRequests.add(new EmbeddingFlowRequest(destNode.getValue(), destinationFlow.getCookie().getValue(), meterId, burst, bandwidth));


                                               AddFlowInput destFlow = getAddFlowInputToSend(destNode.getValue(), destinationFlow);
                                               salFlowService.addFlow(destFlow);

                                               LOG.info("Destination Flow added " + destFlow.toString());


                                               for (int i = 0; i < physicalPathLinks.size(); i++) {


                                                   //the flow which was created
                                                   Flow createdFlow = flows.get(i);

                                                   String nodeId = physicalPathLinks.get(i)
                                                           .getSource().getSourceNode().getValue();

                                                   //get the add input flow to add to sal

                                                   LOG.info("Flow is being installed on " + physicalPathLinks.get(0).getSource().getSourceTp().getValue());

                                                   listofFlowRequests.add(new EmbeddingFlowRequest(nodeId, createdFlow.getCookie().getValue(), meterId, burst, bandwidth));

                                                   AddFlowInput flow = getAddFlowInputToSend(nodeId, createdFlow);


                                                   // add flow to sal
                                                   salFlowService.addFlow(flow);
                                               }

                                               IdToListMap.put(finalId, listofFlowRequests);
                                               populateEmbeddingId(finalId, listofFlowRequests);



                                           } catch (Exception e) {
                                               LOG.error("Exception thrown by Resource Manager - PathConfigurator - CreateAndSendFlow and error is:   ");
                                               e.printStackTrace();

                                           }


                                           timer.schedule(new TimerTask() {
                                               @Override
                                               public void run() {
                                                   removePath(finalId);
                                               }
                                           }, endDate);


                                       }
                                   }
                            , startDate);

                }
            }).start();

            return finalId;


        } catch (Exception e) {
            LOG.error("Exception thrown by Resource Manager - PathConfigurator - CreateAndSendFlow and error is:   ");
            e.printStackTrace();
            return -1L;
        }

    }

    /**
     * Method to create the addFlowInput for it to be added to sal flow service
     *
     * @param edge_switch {@link String} - the switch for the flow to be added on
     * @param createdFlow {@link Flow} - created flow to be added
     * @return {@link AddFlowInput} - addflowinput to be added to salflow
     */
    private static AddFlowInput getAddFlowInputToSend(String edge_switch, Flow createdFlow) {
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


    private static List<Flow> createFlows(org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.Match match1,
                                          List<Link> physicalPathLinks, List<Long> queuesOnPath, NodeConnectorId inPort
    ) {
        FlowBuilder flowBuilder = new FlowBuilder()
                .setTableId((short) 0)
                .setFlowName("random");

        //Flow ID
        flowBuilder.setId(new FlowId(Long.toString(flowBuilder.hashCode())));

        // TcpMatchBuilder tcpMatchBuilder = new TcpMatchBuilder().setTcpSourcePort(new PortNumber(sourcePort)).setTcpDestinationPort(new PortNumber(destPort));
        //IpMatchBuilder ipMatchBuilder = new IpMatchBuilder().setIpProtocol(protocol);

        MatchBuilder matchBuilder = new MatchBuilder();

        matchBuilder.setEthernetMatch(match1.getEthernetMatch())
                .setLayer3Match(match1.getLayer3Match());


        if (match1.getIpMatch() != null && match1.getLayer4Match() != null) {

            //ip and layer 4 are not null
            matchBuilder.setIpMatch(match1.getIpMatch())
                    .setLayer4Match(match1.getLayer4Match());


        }
        if (match1.getVlanMatch() != null) {
            matchBuilder.setVlanMatch(match1.getVlanMatch());
        }


        ArrayList<Match> matches = new ArrayList<>();

        ActionBuilder actionBuilder = new ActionBuilder();


        List<List<Action>> listofactions = new ArrayList<>();

        for (int j = 0; j < physicalPathLinks.size(); ++j) {
            listofactions.add(new ArrayList<Action>());
        }
        for (int i = 0; i < physicalPathLinks.size(); i++) {

            NodeConnectorId inPortLocal = null;

            if (i == 0) {
                inPortLocal = inPort;
            } else {
                inPortLocal = new NodeConnectorId(physicalPathLinks.get(i - 1).getDestination().getDestTp().getValue().split(":")[2]);
            }

            matchBuilder.setInPort(inPortLocal);

            matches.add(matchBuilder.build());


            LOG.info("queuesOnPath {}", queuesOnPath);
            Action queueAction = actionBuilder
                    .setOrder(0).setAction(new SetQueueActionCaseBuilder()
                            .setSetQueueAction(new SetQueueActionBuilder()
                                    .setQueueId(queuesOnPath.get(i))
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
                    .setMatch(matches.get(i))
                    .setBufferId(OFConstants.OFP_NO_BUFFER)
                    .setInstructions(applyInstructions)
                    .setPriority(1000)
                    .setHardTimeout(0)
                    .setIdleTimeout(0)
                    .setCookie(new FlowCookie(BigInteger.valueOf(flowCookieInc.getAndIncrement())))
                    .setFlags(new FlowModFlags(false, false, false, false, false)).build());


        }
        return flows;
    }

    private static List<Flow> createFlowsWithoutQueues(org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.Match match1,
                                                       List<Link> physicalPathLinks, NodeConnectorId inPort
    ) {
        FlowBuilder flowBuilder = new FlowBuilder()
                .setTableId((short) 0)
                .setFlowName("random");

        //Flow ID
        flowBuilder.setId(new FlowId(Long.toString(flowBuilder.hashCode())));

        // TcpMatchBuilder tcpMatchBuilder = new TcpMatchBuilder().setTcpSourcePort(new PortNumber(sourcePort)).setTcpDestinationPort(new PortNumber(destPort));
        //IpMatchBuilder ipMatchBuilder = new IpMatchBuilder().setIpProtocol(protocol);

        MatchBuilder matchBuilder = new MatchBuilder();

        matchBuilder.setEthernetMatch(match1.getEthernetMatch())
                .setLayer3Match(match1.getLayer3Match());


        if (match1.getIpMatch() != null && match1.getLayer4Match() != null) {

            //ip and layer 4 are not null
            matchBuilder.setIpMatch(match1.getIpMatch())
                    .setLayer4Match(match1.getLayer4Match());


        }
        if (match1.getVlanMatch() != null) {
            matchBuilder.setVlanMatch(match1.getVlanMatch());
        }


        ArrayList<Match> matches = new ArrayList<>();

        ActionBuilder actionBuilder = new ActionBuilder();


        List<List<Action>> listofactions = new ArrayList<>();

        for (int j = 0; j < physicalPathLinks.size(); ++j) {
            listofactions.add(new ArrayList<Action>());
        }
        for (int i = 0; i < physicalPathLinks.size(); i++) {

            NodeConnectorId inPortLocal = null;

            if (i == 0) {
                inPortLocal = inPort;
            } else {
                inPortLocal = new NodeConnectorId(physicalPathLinks.get(i - 1).getDestination().getDestTp().getValue().split(":")[2]);
            }

            matchBuilder.setInPort(inPortLocal);

            matches.add(matchBuilder.build());


            String outputNodeConnector = physicalPathLinks.get(i).getSource().getSourceTp().getValue().split(":")[2];
            LOG.info("setting OutputNodeConnector = " + outputNodeConnector);

            Action outputNodeConnectorAction = actionBuilder
                    .setOrder(1).setAction(new OutputActionCaseBuilder()
                            .setOutputAction(new OutputActionBuilder()
                                    .setOutputNodeConnector(new Uri(outputNodeConnector))
                                    .build())
                            .build())
                    .build();


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
                    .setMatch(matches.get(i))
                    .setBufferId(OFConstants.OFP_NO_BUFFER)
                    .setInstructions(applyInstructions)
                    .setPriority(1000)
                    .setHardTimeout(0)
                    .setIdleTimeout(0)
                    .setCookie(new FlowCookie(BigInteger.valueOf(flowCookieInc.getAndIncrement())))
                    .setFlags(new FlowModFlags(false, false, false, false, false)).build());


        }
        return flows;
    }


    public static Long bestembededflow(
            org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.Match endpointMatch,
            List<org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.Link> physicalPathLinks,
            Integer idletimeout, Integer hardtimeout,
            Long meter,
            org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeId srcNode,
            org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeId destNode,
            org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeConnectorId srcNodePort,
            org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeConnectorId destNodePort,
            Long bandwidth,
            Long burst,
            VlanId srcVlan,
            VlanId destVlan
    ) {


        try {

            //increment the counter to make sure uniqueness
            ++id;

            LOG.info("endpoint match recieved is {}", endpointMatch.toString());
            LOG.info("hard timeout {} idle timeout {} ", hardtimeout, idletimeout);
            LOG.info("meter is {}", meter);
            LOG.info("source node is: {}  and port {} ", srcNode.getValue(), srcNodePort.getValue());

            LOG.info("destination node is  {} and port is {}", destNode.getValue(), destNodePort.getValue());

           // LOG.info("Source VLAN : " + srcVlan.getValue() );
           // LOG.info("Dest VLAN: " + destVlan.getValue());

            List<EmbeddingFlowRequest> listofFlowRequests = new ArrayList<>();

            LOG.info("Physical path links on the path: " + physicalPathLinks.toString());

/*
            System.out.println(endpointMatch.toString() + " \n\n");

	    System.out.println(physicalPathLinks.toString() + " \n\n");

	    System.out.println("source is: " + srcNode.getValue() + " \n\n");
	    System.out.println("dest is: " + destNode.getValue() + " \n\n");

	    System.out.println("source is: " + srcNodePort.getValue() + " \n\n");
	    System.out.println("dest is: " + destNodePort.getValue() + " \n\n");

	*/


            NodeConnectorId destNodeInPort = new NodeConnectorId(physicalPathLinks.get(physicalPathLinks.size() - 1).getDestination().getDestTp().getValue().split(":")[2]);

            if (meter == null) {
                Flow sourceFlowWithMeter = createSourceFlowWithoutQueues(srcNodePort, idletimeout, hardtimeout, endpointMatch, null, physicalPathLinks, srcVlan);
                AddFlowInput flowinputSrcNode = getAddFlowInputToSend(srcNode.getValue(), sourceFlowWithMeter);

                listofFlowRequests.add(new EmbeddingFlowRequest(srcNode.getValue(), sourceFlowWithMeter.getCookie().getValue(), meter, burst, bandwidth));

                Future<RpcResult<AddFlowOutput>> resultFuture = salFlowService.addFlow(flowinputSrcNode);

                if (resultFuture.get().isSuccessful()) {
                    LOG.info("Source Flow added successfully and cookie is: " + sourceFlowWithMeter.getCookie());

                } else {

                    LOG.error("Source Flow was not installed and error is " + resultFuture.get().getErrors().toString());

                }


            } else {



                VTNMeterUtils vtnMeterUtils = new VTNMeterUtils(new MeterId(meter), bandwidth, burst);

                AddMeterInput addMeterInput = vtnMeterUtils.createAddMeterInput(srcNode);


                Future<RpcResult<AddMeterOutput>> future = salMeterService.addMeter(addMeterInput);

                Flow sourceFlowWithMeter = null;

                if (future.get().isSuccessful()) {
                    LOG.info("Meter " + meter + " added successfully");
                    LOG.info("Creating Source flow with meter now");
                    sourceFlowWithMeter = createSourceFlowWithoutQueues(srcNodePort, idletimeout, hardtimeout, endpointMatch, meter, physicalPathLinks, srcVlan);
                    LOG.info("source flow created on {} with port {} ", srcNode.getValue(), srcNodePort.getValue());

                }


                if (sourceFlowWithMeter == null) {
                    LOG.info("Error!, Meter was not created");
                    LOG.info("Error is: " + future.get().getErrors().toString());
                } else {

                    AddFlowInput flowinputSrcNode = getAddFlowInputToSend(srcNode.getValue(), sourceFlowWithMeter);

                    listofFlowRequests.add(new EmbeddingFlowRequest(srcNode.getValue(), sourceFlowWithMeter.getCookie().getValue(), meter, burst, bandwidth));

                    Future<RpcResult<AddFlowOutput>> resultFuture = salFlowService.addFlow(flowinputSrcNode);

                    if (resultFuture.get().isSuccessful()) {
                        LOG.info("Source Flow added successfully and cookie is: " + sourceFlowWithMeter.getCookie());

                    } else {

                        LOG.error("Source Flow was not installed and error is " + resultFuture.get().getErrors().toString());

                    }


                }

            }

            Flow destFlowWithoutMeter = createDestinationFlowWithoutQueues(destNodePort, idletimeout, hardtimeout, endpointMatch, destNodeInPort, destVlan);

            LOG.info("source flow created on {} with port {} ", destNode.getValue(), destNodePort.getValue());


            NodeConnectorId inPortForSecond = new NodeConnectorId(physicalPathLinks.get(0).getDestination().getDestTp().getValue().split(":")[2]);

            //removing first link because already used
            physicalPathLinks.remove(physicalPathLinks.get(0));

            List<Flow> flows = createFlowsWithoutQueues(physicalPathLinks, idletimeout, hardtimeout, endpointMatch, inPortForSecond);


            AddFlowInput flowinputDestNode = getAddFlowInputToSend(destNode.getValue(), destFlowWithoutMeter);

            listofFlowRequests.add(new EmbeddingFlowRequest(destNode.getValue(), destFlowWithoutMeter.getCookie().getValue(), meter, burst, bandwidth));


            salFlowService.addFlow(flowinputDestNode);

            LOG.info("Destination Flow added");

            //System.out.println("source and destination flows installed");


            for (int i = 0; i < physicalPathLinks.size(); i++) {

                // System.out.println("started for loop");


                String edge_switch = physicalPathLinks.get(i)
                        .getSource().getSourceNode().getValue();

                LOG.info("Flow is now being installed on " + physicalPathLinks.get(i).getSource().getSourceTp().getValue());

                Flow createdFlow = flows.get(i);

                listofFlowRequests.add(new EmbeddingFlowRequest(edge_switch, createdFlow.getCookie().getValue(), meter, burst, bandwidth));


                AddFlowInput flow = getAddFlowInputToSend(edge_switch, createdFlow);

                // add flow to sal

                salFlowService.addFlow(flow);

            }


            //  System.out.println("done");

            IdToListMap.put(id, listofFlowRequests);
            populateEmbeddingId(id, listofFlowRequests);

            return id;

        } catch (Exception e) {
            LOG.error(e.getMessage() + "  exception thrown");
            e.printStackTrace();
            return null;
        }


    }


    public static Long bestembededflow(
            org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.Match endpointMatch,
            List<org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.Link> physicalPathLinks,
            Integer idletimeout, Integer hardtimeout,
            Long meter,
            org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeId srcNode,
            org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeId destNode,
            org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeConnectorId srcNodePort,
            org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeConnectorId destNodePort,
            Long bandwidth,
            Long burst,
            VlanId srcVlan,
            VlanId destVlan,
            Timestamp startTime,
            Timestamp endTime
    ) {


        try {
            ++id;
            final Long finalId = id;

            // System.out.println(startTime.getValue());

            final String formattedStartTime = startTime.getValue().substring(0, 19);
            final String formattedEndTime = endTime.getValue().substring(0, 19);


            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            final Date startDate = dateFormatter.parse(formattedStartTime); //"2012-07-06 13:05:45"
            final Date endDate = dateFormatter.parse(formattedEndTime); //"2012-07-06 13:05:45"

            //Now create the time and schedule it
            Timer timer = new Timer();

            new Thread(new Runnable() {
                @Override
                public void run() {

                    timer.schedule(new TimerTask() {
                                       @Override
                                       public void run() {

                                           try {

                                               //increment the counter to make sure uniqueness


                                               LOG.info("endpoint match recieved is {}", endpointMatch.toString());
                                               LOG.info("hard timeout {} idle timeout {} ", hardtimeout, idletimeout);
                                               LOG.info("meter is {}", meter);
                                               LOG.info("source node is: {}  and port {} ", srcNode.getValue(), srcNodePort.getValue());

                                               LOG.info("destination node is  {} and port is {}", destNode.getValue(), destNodePort.getValue());

                                               List<EmbeddingFlowRequest> listofFlowRequests = new ArrayList<>();

                                               LOG.info("Physical path links on the path: " + physicalPathLinks.toString());

/*
            System.out.println(endpointMatch.toString() + " \n\n");

	    System.out.println(physicalPathLinks.toString() + " \n\n");

	    System.out.println("source is: " + srcNode.getValue() + " \n\n");
	    System.out.println("dest is: " + destNode.getValue() + " \n\n");

	    System.out.println("source is: " + srcNodePort.getValue() + " \n\n");
	    System.out.println("dest is: " + destNodePort.getValue() + " \n\n");

	*/


                                               NodeConnectorId destNodeInPort = new NodeConnectorId(physicalPathLinks.get(physicalPathLinks.size() - 1).getDestination().getDestTp().getValue().split(":")[2]);

                                               if (meter == null) {

                                                   Flow sourceFlowWithMeter = createSourceFlowWithoutQueues(srcNodePort, idletimeout, hardtimeout, endpointMatch, null, physicalPathLinks, srcVlan);
                                                   AddFlowInput flowinputSrcNode = getAddFlowInputToSend(srcNode.getValue(), sourceFlowWithMeter);

                                                   listofFlowRequests.add(new EmbeddingFlowRequest(srcNode.getValue(), sourceFlowWithMeter.getCookie().getValue(), meter, burst, bandwidth));

                                                   Future<RpcResult<AddFlowOutput>> resultFuture = salFlowService.addFlow(flowinputSrcNode);

                                                   if (resultFuture.get().isSuccessful()) {
                                                       LOG.info("Source Flow added successfully and cookie is: " + sourceFlowWithMeter.getCookie());

                                                   } else {

                                                       LOG.error("Source Flow was not installed and error is " + resultFuture.get().getErrors().toString());

                                                   }


                                               } else {


                                                   VTNMeterUtils vtnMeterUtils = new VTNMeterUtils(new MeterId(meter), bandwidth, burst);

                                                   AddMeterInput addMeterInput = vtnMeterUtils.createAddMeterInput(srcNode);


                                                   Future<RpcResult<AddMeterOutput>> future = salMeterService.addMeter(addMeterInput);

                                                   Flow sourceFlowWithMeter = null;

                                                   if (future.get().isSuccessful()) {
                                                       LOG.info("Meter " + meter + " added successfully");
                                                       LOG.info("Creating Source flow with meter now");
                                                       sourceFlowWithMeter = createSourceFlowWithoutQueues(srcNodePort, idletimeout, hardtimeout, endpointMatch, meter, physicalPathLinks, srcVlan);
                                                       LOG.info("source flow created on {} with port {} ", srcNode.getValue(), srcNodePort.getValue());

                                                   }


                                                   if (sourceFlowWithMeter == null) {
                                                       LOG.info("Error!, Meter was not created");
                                                       LOG.info("Error is: " + future.get().getErrors().toString());
                                                   } else {

                                                       AddFlowInput flowinputSrcNode = getAddFlowInputToSend(srcNode.getValue(), sourceFlowWithMeter);

                                                       listofFlowRequests.add(new EmbeddingFlowRequest(srcNode.getValue(), sourceFlowWithMeter.getCookie().getValue(), meter, burst, bandwidth));

                                                       Future<RpcResult<AddFlowOutput>> resultFuture = salFlowService.addFlow(flowinputSrcNode);

                                                       if (resultFuture.get().isSuccessful()) {
                                                           LOG.info("Source Flow added successfully and cookie is: " + sourceFlowWithMeter.getCookie());

                                                       } else {

                                                           LOG.error("Source Flow was not installed and error is " + resultFuture.get().getErrors().toString());

                                                       }


                                                   }
                                               }


                                               Flow destFlowWithoutMeter = createDestinationFlowWithoutQueues(destNodePort, idletimeout, hardtimeout, endpointMatch, destNodeInPort, destVlan);

                                               LOG.info("source flow created on {} with port {} ", destNode.getValue(), destNodePort.getValue());


                                               NodeConnectorId inPortForSecond = new NodeConnectorId(physicalPathLinks.get(0).getDestination().getDestTp().getValue().split(":")[2]);

                                               //removing first link because already used
                                               physicalPathLinks.remove(physicalPathLinks.get(0));

                                               List<Flow> flows = createFlowsWithoutQueues(physicalPathLinks, idletimeout, hardtimeout, endpointMatch, inPortForSecond);


                                               AddFlowInput flowinputDestNode = getAddFlowInputToSend(destNode.getValue(), destFlowWithoutMeter);

                                               listofFlowRequests.add(new EmbeddingFlowRequest(destNode.getValue(), destFlowWithoutMeter.getCookie().getValue(), meter, burst, bandwidth));


                                               salFlowService.addFlow(flowinputDestNode);

                                               LOG.info("Destination Flow added");

                                               //System.out.println("source and destination flows installed");


                                               for (int i = 0; i < physicalPathLinks.size(); i++) {

                                                   // System.out.println("started for loop");


                                                   String edge_switch = physicalPathLinks.get(i)
                                                           .getSource().getSourceNode().getValue();

                                                   LOG.info("Flow is now being installed on " + physicalPathLinks.get(i).getSource().getSourceTp().getValue());

                                                   Flow createdFlow = flows.get(i);

                                                   listofFlowRequests.add(new EmbeddingFlowRequest(edge_switch, createdFlow.getCookie().getValue(), meter, burst, bandwidth));


                                                   AddFlowInput flow = getAddFlowInputToSend(edge_switch, createdFlow);

                                                   // add flow to sal

                                                   salFlowService.addFlow(flow);

                                               }


                                               //  System.out.println("done");

                                               IdToListMap.put(finalId, listofFlowRequests);
                                               populateEmbeddingId(id, listofFlowRequests);



                                           } catch (Exception e) {
                                               LOG.error(e.getMessage() + "  exception thrown");
                                               e.printStackTrace();

                                           }


                                           timer.schedule(new TimerTask() {
                                               @Override
                                               public void run() {
                                                   removePath(finalId);
                                               }
                                           }, endDate);


                                       }
                                   }
                            , startDate);

                }
            }).start();


            return finalId;


        } catch (Exception e) {
            LOG.error(e.getMessage() + "  exception thrown");
            e.printStackTrace();
            return -1L;
        }


    }


    private static List<Flow> createFlowsWithoutQueues(List<Link> physicalPathLinks, Integer idletimeout, Integer hardtimeout,
                                                       org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.Match match1,
                                                       NodeConnectorId inPort
    ) {
        FlowBuilder flowBuilder = new FlowBuilder()
                .setTableId((short) 0)
                .setFlowName("random");
        LOG.info("Inside createFlowsWithoutQueues");


        //Flow ID
        flowBuilder.setId(new FlowId(Long.toString(flowBuilder.hashCode())));

        TcpMatchBuilder tcpMatchBuilder = null;
        IpMatchBuilder ipMatchBuilder = null;

/*
        if (sourcePort != null && destPort != null) {

            tcpMatchBuilder = new TcpMatchBuilder().setTcpSourcePort(new PortNumber(sourcePort)).setTcpDestinationPort(new PortNumber(destPort));


        } else {

            //values are null




        }
*/

        if (match1.getIpMatch() != null && match1.getIpMatch().getIpProtocol() != null) {

            ipMatchBuilder = new IpMatchBuilder().setIpProtocol(match1.getIpMatch().getIpProtocol());

        } else {
            //no ip protocol specified

        }


        MatchBuilder matchBuilder = new MatchBuilder();

        matchBuilder.setEthernetMatch(match1.getEthernetMatch())
                .setLayer3Match(match1.getLayer3Match());


        if (match1.getIpMatch() != null && match1.getLayer4Match() != null) {

            //ip and layer 4 are not null
            matchBuilder.setIpMatch(match1.getIpMatch())
                    .setLayer4Match(match1.getLayer4Match());


        }
        if (match1.getVlanMatch() != null) {
            matchBuilder.setVlanMatch(match1.getVlanMatch());
        }


        ActionBuilder actionBuilder = new ActionBuilder();
        List<Match> matches = new ArrayList<Match>();

        //Actions
        //currently changing tos and sending to output connector


        List<List<Action>> listofactions = new ArrayList<>();

        for (int j = 0; j < physicalPathLinks.size(); ++j) {
            listofactions.add(new ArrayList<Action>());
        }

        for (int i = 0; i < physicalPathLinks.size(); i++) {


            String outputNodeConnector = physicalPathLinks.get(i).getSource().getSourceTp().getValue().split(":")[2];
            NodeConnectorId inPortLocal = null;

            if (i == 0) {
                inPortLocal = inPort;
            } else {
                inPortLocal = new NodeConnectorId(physicalPathLinks.get(i - 1).getDestination().getDestTp().getValue().split(":")[2]);
            }

            matchBuilder.setInPort(inPortLocal);

            matches.add(matchBuilder.build());

            LOG.info("Setting the output port to: " + outputNodeConnector);


            Action outputNodeConnectorAction = actionBuilder
                    .setOrder(1).setAction(new OutputActionCaseBuilder()
                            .setOutputAction(new OutputActionBuilder()
                                    .setOutputNodeConnector(new Uri(outputNodeConnector))
                                    .build())
                            .build())
                    .build();

            //    System.out.println( "link source is " + physicalPathLinks.get(i).getSource().getSourceTp().getValue());


            listofactions.get(i).add(outputNodeConnectorAction);

        }


        //ApplyActions

     /*   Match match = matchBuilder
                .build();*/

        List<Flow> flows = new ArrayList<>();

        for (int i = 0; i < listofactions.size(); i++) {

            LOG.info("applying action");
            ApplyActions applyActions = new ApplyActionsBuilder().setAction(listofactions.get(i)).build();

            //Instruction
            Instruction applyActionsInstruction = new InstructionBuilder() //
                    .setOrder(0).setInstruction(new ApplyActionsCaseBuilder()//
                            .setApplyActions(applyActions) //
                            .build()) //
                    .build();

            Instructions applyInstructions = new InstructionsBuilder()
                    .setInstruction(ImmutableList.of(applyActionsInstruction))
                    .build();

            // Put our Instruction in a list of Instructions


            LOG.info("adding flow");
            flows.add(flowBuilder
                    .setMatch(matches.get(i))
                    .setBufferId(OFConstants.OFP_NO_BUFFER)
                    .setInstructions(applyInstructions)
                    .setPriority(1000)
                    .setHardTimeout(hardtimeout)
                    .setIdleTimeout(idletimeout)
                    .setCookie(new FlowCookie(BigInteger.valueOf(flowCookieInc.getAndIncrement())))
                    .setFlags(new FlowModFlags(false, false, false, false, false)).build());


        }
        LOG.info(flows.toString());
        return flows;
    }


    private static Flow createSourceFlowWithoutQueues(NodeConnectorId srcNodeConnectorId,
                                                      Integer idletimeout, Integer hardtimeout,
                                                      org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.Match match1,
                                                      Long meterId, List<Link> links, VlanId srcVlan) {
        FlowBuilder flowBuilder = new FlowBuilder()
                .setTableId((short) 0)
                .setFlowName("random");

        int pp = 0;


        //Flow ID
        flowBuilder.setId(new FlowId(Long.toString(flowBuilder.hashCode())));

        MatchBuilder matchBuilder = new MatchBuilder();

        NodeConnectorId temp = new NodeConnectorId(srcNodeConnectorId.getValue().split(":")[2]);

        LOG.info("setting the in port for the source flow to: " + temp.getValue());

        matchBuilder.setEthernetMatch(match1.getEthernetMatch())
                .setLayer3Match(match1.getLayer3Match())
                .setInPort(temp);


        if (match1.getIpMatch() != null && match1.getLayer4Match() != null) {

            //ip and layer 4 are not null
            matchBuilder.setIpMatch(match1.getIpMatch())
                    .setLayer4Match(match1.getLayer4Match());


        }
        if (match1.getVlanMatch() != null) {
            matchBuilder.setVlanMatch(match1.getVlanMatch());
        }


        Match match = matchBuilder
                .build();

        ActionBuilder actionBuilder = new ActionBuilder();
        List<Action> actions = new ArrayList<Action>();

        //Actions
        //currently changing tos and sending to output connector


        List<List<Action>> listofactions = new ArrayList<>();

        for (int j = 0; j < 1; ++j) {
            listofactions.add(new ArrayList<Action>());
        }


        String outputNodeConnector = links.get(0).getSource().getSourceTp().getValue().split(":")[2];
        LOG.info("Setting the output source/first flow port to: " + outputNodeConnector);


        if (srcVlan != null) {

            LOG.info("Source VLAN is not null, adding pop VLAN Action");
            PopVlanActionCaseBuilder popVlanBuilder = new PopVlanActionCaseBuilder();
            popVlanBuilder.setPopVlanAction(new PopVlanActionBuilder().build());

            Action vlanAction = actionBuilder.setOrder(0).setAction(popVlanBuilder.build()).build();


            Action outputNodeConnectorActionsrc = actionBuilder
                    .setOrder(1).setAction(new OutputActionCaseBuilder()
                            .setOutputAction(new OutputActionBuilder()
                                    .setOutputNodeConnector(new Uri(outputNodeConnector))
                                    .build())
                            .build())
                    .build();


            listofactions.get(0).add(vlanAction);
            listofactions.get(0).add(outputNodeConnectorActionsrc);


        } else {

            Action outputNodeConnectorActionsrc = actionBuilder
                    .setOrder(0).setAction(new OutputActionCaseBuilder()
                            .setOutputAction(new OutputActionBuilder()
                                    .setOutputNodeConnector(new Uri(outputNodeConnector))
                                    .build())
                            .build())
                    .build();


            listofactions.get(0).add(outputNodeConnectorActionsrc);

        }
        //ApplyActions


        LOG.info("applying action");
        ApplyActions applyActions = new ApplyActionsBuilder().setAction(listofactions.get(0)).build();

        //Instruction
        Instruction applyActionsInstruction = new InstructionBuilder() //
                .setOrder(0).setInstruction(new ApplyActionsCaseBuilder()//
                        .setApplyActions(applyActions) //
                        .build())

                .build();

        List<Instruction> instructionList = new ArrayList<>();

        if (meterId == null) {

            instructionList.add(applyActionsInstruction);

        } else {

            Instruction meterInstruction = new InstructionBuilder().setOrder(1).setInstruction((new MeterCaseBuilder()
                    .setMeter(new MeterBuilder()
                            .setMeterId(new MeterId(meterId))
                            .build())
                    .build()))
                    .build();


            instructionList.add(applyActionsInstruction);
            instructionList.add(meterInstruction);

        }


        Instructions applyInstructions = new InstructionsBuilder()
                .setInstruction(instructionList)
                .build();


        // Put our Instruction in a list of Instructions

        LOG.info("adding flow");
        Flow flow = flowBuilder
                .setMatch(match)
                .setBufferId(OFConstants.OFP_NO_BUFFER)
                .setInstructions(applyInstructions)
                .setPriority(1000)
                .setHardTimeout(hardtimeout)
                .setIdleTimeout(idletimeout)
                .setCookie(new FlowCookie(BigInteger.valueOf(flowCookieInc.getAndIncrement())))
                .setFlags(new FlowModFlags(false, false, false, false, false)).build();


        return flow;

    }


    private static Flow createDestinationFlowWithoutQueues(NodeConnectorId destNodeConnectorId,
                                                           Integer idletimeout, Integer hardtimeout,
                                                           org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.Match match1,
                                                           NodeConnectorId inPort, VlanId destVlan
    ) {
        FlowBuilder flowBuilder = new FlowBuilder()
                .setTableId((short) 0)
                .setFlowName("random");


        //Flow ID
        flowBuilder.setId(new FlowId(Long.toString(flowBuilder.hashCode())));


        MatchBuilder matchBuilder = new MatchBuilder();

        matchBuilder.setEthernetMatch(match1.getEthernetMatch())
                .setLayer3Match(match1.getLayer3Match())
                .setInPort(inPort);


        if (match1.getIpMatch() != null && match1.getLayer4Match() != null) {

            //ip and layer 4 are not null
            matchBuilder.setIpMatch(match1.getIpMatch())
                    .setLayer4Match(match1.getLayer4Match());


        }
        if (match1.getVlanMatch() != null) {
            matchBuilder.setVlanMatch(match1.getVlanMatch());
        }


        Match match = matchBuilder
                .build();

        ActionBuilder actionBuilder = new ActionBuilder();
        List<Action> actions = new ArrayList<Action>();

        //Actions
        //currently changing tos and sending to output connector


        List<List<Action>> listofactions = new ArrayList<>();

        for (int j = 0; j < 1; ++j) {
            listofactions.add(new ArrayList<Action>());
        }

        if (destVlan != null) {


            PushVlanActionCaseBuilder pushVlanActionCaseBuilder = new PushVlanActionCaseBuilder();


            Action pushVlanAction = actionBuilder.setOrder(0).setAction(
                    pushVlanActionCaseBuilder.setPushVlanAction(new PushVlanActionBuilder().setEthernetType(0x8100)
                            .build()).build()
            ).build();

            Action setVlanIdAction = actionBuilder
                    .setOrder(1).setAction(new SetFieldCaseBuilder()
                            .setSetField(new SetFieldBuilder()
                                    .setVlanMatch(new VlanMatchBuilder()
                                            .setVlanId(new VlanIdBuilder()
                                                    .setVlanId(new VlanId(destVlan))
                                                    .setVlanIdPresent(true)
                                                    .build())
                                            .build())
                                    .build())
                            .build())
                    .build();



            Action outputNodeConnectorActionsrc = actionBuilder
                    .setOrder(2).setAction(new OutputActionCaseBuilder()
                            .setOutputAction(new OutputActionBuilder()
                                    .setOutputNodeConnector(new Uri(destNodeConnectorId.getValue().split(":")[2]))
                                    .build())
                            .build())
                    .build();

            listofactions.get(0).add(pushVlanAction);
            listofactions.get(0).add(setVlanIdAction);
            listofactions.get(0).add(outputNodeConnectorActionsrc);

        } else {

            Action outputNodeConnectorActionsrc = actionBuilder
                    .setOrder(1).setAction(new OutputActionCaseBuilder()
                            .setOutputAction(new OutputActionBuilder()
                                    .setOutputNodeConnector(new Uri(destNodeConnectorId.getValue().split(":")[2]))
                                    .build())
                            .build())
                    .build();


            listofactions.get(0).add(outputNodeConnectorActionsrc);

        }

        //ApplyActions


        LOG.info("applying action");
        ApplyActions applyActions = new ApplyActionsBuilder().setAction(listofactions.get(0)).build();

        //Instruction
        Instruction applyActionsInstruction = new InstructionBuilder() //
                .setOrder(0).setInstruction(new ApplyActionsCaseBuilder()//
                        .setApplyActions(applyActions) //
                        .build())

                .build();


        Instructions applyInstructions = new InstructionsBuilder()
                .setInstruction(ImmutableList.of(applyActionsInstruction))
                .build();


        // Put our Instruction in a list of Instructions

        LOG.info("adding flow");
        Flow flow = flowBuilder
                .setMatch(match)
                .setBufferId(OFConstants.OFP_NO_BUFFER)
                .setInstructions(applyInstructions)
                .setPriority(1000)
                .setHardTimeout(hardtimeout)
                .setIdleTimeout(idletimeout)
                .setCookie(new FlowCookie(BigInteger.valueOf(flowCookieInc.getAndIncrement())))
                .setFlags(new FlowModFlags(false, false, false, false, false)).build();


        return flow;


    }


    private static Flow createSourceFlowWithQueuesWithMeter(NodeConnectorId srcNodeConnectorId,
                                                            org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.Match match1,
                                                            Long meterId, List<Link> links, List<Long> queues, VlanId srcVlanId) {
        FlowBuilder flowBuilder = new FlowBuilder()
                .setTableId((short) 0)
                .setFlowName("random");

        int pp = 0;


        //Flow ID
        flowBuilder.setId(new FlowId(Long.toString(flowBuilder.hashCode())));

        MatchBuilder matchBuilder = new MatchBuilder();


        NodeConnectorId temp = new NodeConnectorId(srcNodeConnectorId.getValue().split(":")[2]);

        matchBuilder.setEthernetMatch(match1.getEthernetMatch())
                .setLayer3Match(match1.getLayer3Match())
                .setInPort(temp);


        if (match1.getIpMatch() != null && match1.getLayer4Match() != null) {

            //ip and layer 4 are not null
            matchBuilder.setIpMatch(match1.getIpMatch())
                    .setLayer4Match(match1.getLayer4Match());


        }
        if (match1.getVlanMatch() != null) {
            matchBuilder.setVlanMatch(match1.getVlanMatch());
        }


        Match match = matchBuilder
                .build();

        ActionBuilder actionBuilder = new ActionBuilder();

        //Actions
        //currently changing tos and sending to output connector


        List<List<Action>> listofactions = new ArrayList<>();

        listofactions.add(new ArrayList<Action>());


        String outputNodeConnector = links.get(0).getSource().getSourceTp().getValue().split(":")[2];
        LOG.info("Setting the output First flow port to: " + outputNodeConnector);
        LOG.info("setting queue id to  " + queues.get(0));


        if (srcVlanId != null) {


            PopVlanActionCaseBuilder popVlanBuilder = new PopVlanActionCaseBuilder();
            popVlanBuilder.setPopVlanAction(new PopVlanActionBuilder().build());

            Action vlanAction = actionBuilder.setOrder(0).setAction(popVlanBuilder.build()).build();


            Action queueAction = actionBuilder
                    .setOrder(1).setAction(new SetQueueActionCaseBuilder()
                            .setSetQueueAction(new SetQueueActionBuilder()
                                    .setQueueId(queues.get(0))
                                    .build())
                            .build())

                    .build();

            Action outputNodeConnectorActionsrc = actionBuilder
                    .setOrder(2).setAction(new OutputActionCaseBuilder()
                            .setOutputAction(new OutputActionBuilder()
                                    .setOutputNodeConnector(new Uri(outputNodeConnector))
                                    .build())
                            .build())
                    .build();


            listofactions.get(0).add(vlanAction);
            listofactions.get(0).add(queueAction);
            listofactions.get(0).add(outputNodeConnectorActionsrc);

        } else {

            Action queueAction = actionBuilder
                    .setOrder(0).setAction(new SetQueueActionCaseBuilder()
                            .setSetQueueAction(new SetQueueActionBuilder()
                                    .setQueueId(queues.get(0))
                                    .build())
                            .build())

                    .build();

            Action outputNodeConnectorActionsrc = actionBuilder
                    .setOrder(1).setAction(new OutputActionCaseBuilder()
                            .setOutputAction(new OutputActionBuilder()
                                    .setOutputNodeConnector(new Uri(outputNodeConnector))
                                    .build())
                            .build())
                    .build();


            listofactions.get(0).add(queueAction);
            listofactions.get(0).add(outputNodeConnectorActionsrc);
        }

        //ApplyActions


        LOG.info("applying action");
        ApplyActions applyActions = new ApplyActionsBuilder().setAction(listofactions.get(0)).build();

        //Instruction
        Instruction applyActionsInstruction = new InstructionBuilder() //
                .setOrder(0).setInstruction(new ApplyActionsCaseBuilder()//
                        .setApplyActions(applyActions) //
                        .build())

                .build();

        List<Instruction> instructionList = new ArrayList<>();


        if (meterId == null) {

            instructionList.add(applyActionsInstruction);

        } else {


            Instruction meterInstruction = new InstructionBuilder()
                    .setOrder(1).setInstruction((new MeterCaseBuilder()
                            .setMeter(new MeterBuilder()
                                    .setMeterId(new MeterId(meterId))
                                    .build())
                            .build()))
                    .build();


            instructionList.add(applyActionsInstruction);
            instructionList.add(meterInstruction);


        }


        Instructions applyInstructions = new InstructionsBuilder()
                .setInstruction(instructionList)
                .build();


        // Put our Instruction in a list of Instructions

        LOG.info("adding flow");
        Flow flow = flowBuilder
                .setMatch(match)
                .setBufferId(OFConstants.OFP_NO_BUFFER)
                .setInstructions(applyInstructions)
                .setPriority(1000)
                .setHardTimeout(0)
                .setIdleTimeout(0)
                .setCookie(new FlowCookie(BigInteger.valueOf(flowCookieInc.getAndIncrement())))
                .setFlags(new FlowModFlags(false, false, false, false, false)).build();

        return flow;
    }


    private static Flow createDestinationFlowWithQueuesWithoutMeter(NodeConnectorId destNodeConnectorId,
                                                                    org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.Match match1, VlanId destVlan) {
        FlowBuilder flowBuilder = new FlowBuilder()
                .setTableId((short) 0)
                .setFlowName("random");

        int pp = 0;


        //Flow ID
        flowBuilder.setId(new FlowId(Long.toString(flowBuilder.hashCode())));

        MatchBuilder matchBuilder = new MatchBuilder();

        matchBuilder.setEthernetMatch(match1.getEthernetMatch())
                .setLayer3Match(match1.getLayer3Match());


        if (match1.getIpMatch() != null && match1.getLayer4Match() != null) {

            //ip and layer 4 are not null
            matchBuilder.setIpMatch(match1.getIpMatch())
                    .setLayer4Match(match1.getLayer4Match());


        }
        if (match1.getVlanMatch() != null) {
            matchBuilder.setVlanMatch(match1.getVlanMatch());
        }


        Match match = matchBuilder
                .build();

        ActionBuilder actionBuilder = new ActionBuilder();

        //Actions
        //currently changing tos and sending to output connector


        List<List<Action>> listofactions = new ArrayList<>();

        listofactions.add(new ArrayList<Action>());


        //TODO Statically setting queue ID to 0L, Confirm with others


        if (destVlan != null) {


            PushVlanActionCaseBuilder pushVlanActionCaseBuilder = new PushVlanActionCaseBuilder();


            Action pushVlanAction = actionBuilder.setOrder(0).setAction(
                    pushVlanActionCaseBuilder.setPushVlanAction(new PushVlanActionBuilder().setEthernetType(0x8100)
                            .build()).build()
            ).build();

            Action setVlanIdAction = actionBuilder
                    .setOrder(1).setAction(new SetFieldCaseBuilder()
                            .setSetField(new SetFieldBuilder()
                                    .setVlanMatch(new VlanMatchBuilder()
                                            .setVlanId(new VlanIdBuilder()
                                                    .setVlanId(new VlanId(destVlan))
                                                    .setVlanIdPresent(true)
                                                    .build())
                                            .build())
                                    .build())
                            .build())
                    .build();



            Action queueAction = actionBuilder
                    .setOrder(2).setAction(new SetQueueActionCaseBuilder()
                            .setSetQueueAction(new SetQueueActionBuilder()
                                    .setQueueId(0L)
                                    .build())
                            .build())

                    .build();


            String outputNodeConnector = destNodeConnectorId.getValue().split(":")[2];

            LOG.info("Setting the output dest port to: " + outputNodeConnector);

            Action outputNodeConnectorActionsrc = actionBuilder
                    .setOrder(3).setAction(new OutputActionCaseBuilder()
                            .setOutputAction(new OutputActionBuilder()
                                    .setOutputNodeConnector(new Uri(outputNodeConnector))
                                    .build())
                            .build())
                    .build();

            listofactions.get(0).add(pushVlanAction);
            listofactions.get(0).add(setVlanIdAction);
            listofactions.get(0).add(queueAction);
            listofactions.get(0).add(outputNodeConnectorActionsrc);


        } else {


            Action queueAction = actionBuilder
                    .setOrder(0).setAction(new SetQueueActionCaseBuilder()
                            .setSetQueueAction(new SetQueueActionBuilder()
                                    .setQueueId(0L)
                                    .build())
                            .build())

                    .build();


            String outputNodeConnector = destNodeConnectorId.getValue().split(":")[2];

            LOG.info("Setting the output dest port to: " + outputNodeConnector);

            Action outputNodeConnectorActionsrc = actionBuilder
                    .setOrder(1).setAction(new OutputActionCaseBuilder()
                            .setOutputAction(new OutputActionBuilder()
                                    .setOutputNodeConnector(new Uri(outputNodeConnector))
                                    .build())
                            .build())
                    .build();

            listofactions.get(0).add(queueAction);
            listofactions.get(0).add(outputNodeConnectorActionsrc);

        }
        //ApplyActions


        LOG.info("applying action");
        ApplyActions applyActions = new ApplyActionsBuilder().setAction(listofactions.get(0)).build();

        //Instruction
        Instruction applyActionsInstruction = new InstructionBuilder() //
                .setOrder(0).setInstruction(new ApplyActionsCaseBuilder()//
                        .setApplyActions(applyActions) //
                        .build())

                .build();


        Instructions applyInstructions = new InstructionsBuilder()
                .setInstruction(ImmutableList.of(applyActionsInstruction))
                .build();


        // Put our Instruction in a list of Instructions

        LOG.info("adding flow");
        Flow flow = flowBuilder
                .setMatch(match)
                .setBufferId(OFConstants.OFP_NO_BUFFER)
                .setInstructions(applyInstructions)
                .setPriority(1000)
                .setHardTimeout(0)
                .setIdleTimeout(0)
                .setCookie(new FlowCookie(BigInteger.valueOf(flowCookieInc.getAndIncrement())))
                .setFlags(new FlowModFlags(false, false, false, false, false)).build();

        return flow;
    }


    /**
     * Method to remove the path from the network given the path id
     * deletes all the flows within the path
     *
     * @param {@link Long} - The path to delete
     * @return {@link Boolean} true if successfully removed all the paths, otherwise false.
     */

    public static boolean removePath(Long embeddingID) {


        listOfEmbeddingId = ResourceManagerRegistryImpl.getInstance().readAllResourceManagerDatastore();

        if(listOfEmbeddingId != null) {
            Long presentId = 0L;
            Long pastId = 0L;

            List<EmbeddingFlowRequest> allRequests = new ArrayList<>();


            for(int i = 0; i<listOfEmbeddingId.size(); ++i ) {

                //EmbeddingIdCookie

                if(i == 0) {
                    pastId = listOfEmbeddingId.get(i).getEmbeddingId();
                } else {
                    pastId = listOfEmbeddingId.get(i-1).getEmbeddingId();
                }

                presentId = listOfEmbeddingId.get(i).getEmbeddingId();
                Long cookie = listOfEmbeddingId.get(i).getFlowCookie();
                String switchId = listOfEmbeddingId.get(i).getFlowSwitchId();
                Long meterId = listOfEmbeddingId.get(i).getMeterId();
                Long burst = listOfEmbeddingId.get(i).getMeterBurst();
                Long bandwidth = listOfEmbeddingId.get(i).getMeterBandwidth();

                allRequests.add(new EmbeddingFlowRequest(switchId, BigInteger.valueOf(cookie), meterId, burst, bandwidth));

                if(!presentId.equals(pastId) && i != listOfEmbeddingId.size()-1 ) {
                    //finished one id, reset
                    IdToListMap.put(pastId, allRequests);
                    allRequests.clear();
                } else if(i == listOfEmbeddingId.size()-1) {
                    //last element, must be added to the list
                    IdToListMap.put(presentId, allRequests);
                    allRequests.clear();
                }





            }


        }


        //Found path will be stored in this
        List<EmbeddingFlowRequest> allRequests = IdToListMap.get(embeddingID);
        List<Long> deletedMeters =  new ArrayList<>();

        //find if a path with the path id exists


        //ensuring that path is found
        if (allRequests != null) {

            //path found

            //Going through all the flows within the path to delete them one by one
            for (int i = 0; i < allRequests.size(); i++) {

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
                RemoveFlowInput flow = b.build();


                // remove flow from sal
                salFlowService.removeFlow(flow);

            }

            for(EmbeddingFlowRequest embeddingFlowRequest: allRequests) {
                if (embeddingFlowRequest.getMeterId().equals(-1L) || deletedMeters.contains(embeddingFlowRequest.getMeterId())) {
                    // either meter does not exist or meter was deleted already
                } else {
                    //meter exists
                    VTNMeterUtils vtnMeterUtils =  new VTNMeterUtils(new MeterId(embeddingFlowRequest.getMeterId()), embeddingFlowRequest.getBandwidth(), embeddingFlowRequest.getBurst());

                    RemoveMeterInput removeMeterInput = vtnMeterUtils.createRemoveMeterInput(new NodeId(embeddingFlowRequest.getNode_id()));

                    Future<RpcResult<RemoveMeterOutput>> future = salMeterService.removeMeter(removeMeterInput);

                    deletedMeters.add(embeddingFlowRequest.getMeterId());

                    try {
                        if (future.get().isSuccessful()) {
                            LOG.info("Meter Removal Successful");
                        } else {
                            LOG.info("Meter Removal unsuccessfull and error is: " + future.get().getErrors().toString() );
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }



        } else {

            //no path found hence nothing to delete

            return false;
        }

        // all flows successfully removed
        return true;

    }


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

    private static List<Action> AddVlanActions(VlanId srcVlan, VlanId destVlan) {

        List<Action> actionList = new ArrayList<>();

        if (srcVlan == null && destVlan == null) {
            return actionList;
        }

        if (srcVlan != null) {
            PopVlanActionCaseBuilder popVlanBuilder = new PopVlanActionCaseBuilder();
            popVlanBuilder.setPopVlanAction(new PopVlanActionBuilder().build());

            ActionBuilder ab = new ActionBuilder().setOrder(1);
            ab.setAction(popVlanBuilder.build());

            actionList.add(ab.build());


        }
        if (destVlan != null) {
            PopVlanActionCaseBuilder popVlanBuilder = new PopVlanActionCaseBuilder();
            popVlanBuilder.setPopVlanAction(new PopVlanActionBuilder().build());

            ActionBuilder ab = new ActionBuilder().setOrder(1).setKey(new ActionKey(1));
            ab.setAction(popVlanBuilder.build());


            PushVlanActionCaseBuilder pushVlanActionCaseBuilder = new PushVlanActionCaseBuilder();
            pushVlanActionCaseBuilder.setPushVlanAction(new PushVlanActionBuilder().setEthernetType(0x8100).setVlanId(destVlan)
                    .build());


        }

        return actionList;

    }


    public boolean test() {


        TcpMatchBuilder tcpMatchBuilder = new TcpMatchBuilder();
        IpMatchBuilder ipMatchBuilder = new IpMatchBuilder().setIpProtocol((short) 6);

        Ipv4Prefix sourceIP = new Ipv4Prefix("192.168.0.1/32");
        Ipv4Prefix destinationIP = new Ipv4Prefix("192.168.0.2/32");

        String destmac = "00:00:00:00:00:00"; //mac address of destination
        String srcMac = "00:00:00:00:00:00"; //mac address of source

        org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.Match match = new MatchBuilder().setEthernetMatch(new EthernetMatchBuilder()
                .setEthernetType(new EthernetTypeBuilder()
                        .setType(new EtherType(0x0800L))
                        .build()).build())
                .setIpMatch(ipMatchBuilder.build())
                .setLayer4Match(tcpMatchBuilder.build())
                .setLayer3Match(new Ipv4MatchBuilder()
                        .setIpv4Source(sourceIP)
                        .setIpv4Destination(destinationIP)
                        .build())
                .setEthernetMatch(new EthernetMatchBuilder()
                        .setEthernetDestination(new EthernetDestinationBuilder().setAddress(new MacAddress(destmac)).build())
                        .setEthernetSource(new EthernetSourceBuilder().setAddress(new MacAddress(srcMac)).build())
                        .build())

                .build();

        NodeId src = new NodeId("openflow:1");
        NodeId dest = new NodeId("openflow:2");

        List<Long> queues = new ArrayList<>();
        queues.add(1L);
        queues.add(2L);

        NodeConnectorId sourceNodeConnectorID = new NodeConnectorId("openflow:1:2");
        NodeConnectorId destinationNodeConnectorID = new NodeConnectorId("openflow:2:3");
        Long meterID = 1L;
/*

        bestembededflow(match, listoflinks, 3000,3000,meterID,src,dest, sourceNodeConnectorID, destinationNodeConnectorID);

        createAndSendFlow(match, listOfLinks,queues, meterID, src, dest, sourceNodeConnectorID, destinationNodeConnectorID );
*/

        return false;
    }



    public static void populateEmbeddingId (Long id, List<EmbeddingFlowRequest> listofFlowRequests) {

        EmbeddingIdCookieBuilder cookieId = new EmbeddingIdCookieBuilder();
        for(EmbeddingFlowRequest request: listofFlowRequests) {


                    cookieId.setEmbeddingId(id)
                    .setFlowCookie(request.getFlowCookie().longValue())
                    .setFlowSwitchId(request.getNode_id());

                    if(request.getMeterId() != null){
                        cookieId.setMeterBandwidth(request.getBandwidth())
                                .setMeterBurst(request.getBurst())
                                .setMeterId(request.getMeterId());

                    } else {
                        cookieId.setMeterBandwidth(-1L)
                                .setMeterBurst(-1L)
                                .setMeterId(-1L);

                    }

            EmbeddingIdCookie cookieToWrite =  cookieId.build();

            ResourceManagerRegistryImpl.getInstance().writeNewEmbeddingIdCookie(cookieToWrite);

        }


    }


}
/*             TEST CASE FOR CLASS !!!!!

        try {

                        final PathConfigurator sc = new PathConfigurator(salFlowService);
                        List<String> links = new ArrayList<>();
                        links.add("openflow:2:5");
                        links.add("openflow:1:3");
                        int[] queues =  new int[2];

                        queues[0] = 0;
                        queues[1] = 0;

                        Ipv4Prefix srcIp = new Ipv4Prefix("192.168.0.1/32");
                        Ipv4Prefix dstIp = new Ipv4Prefix("192.168.0.2/32");


                        final Long id = sc.createFlow(srcIp, dstIp, 40,42, (short) 6, links, queues, null );
                       // sc.bestembededflow(srcIp,dstIp,21,22,(short) 6,links,180,240,null);



                // tO CHECK EXECEUTION AFTER 1 MINUTE WHETHER FLOWS ARE BEING DELETED OR NOT
                final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
                    executor.schedule(new Runnable() {
                      @Override
                      public void run() {

                        boolean b = sc.removePath(id);

                        System.out.println("\n\n\n\n everything done successfully \n\n\n\n\n" + "Result is: " + b + "\n\n\n\n\n");



                      }
                    }, 1, TimeUnit.MINUTES);



        } catch (Exception e) {
            System.out.println("Exception thrown");
            e.printStackTrace();
            //e.printStackTrace();

        }


    */
