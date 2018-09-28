/*
 * Copyright © 2015 George and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package eu.virtuwind.resourcemonitor.impl;
/*
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.md.sal.binding.api.NotificationService;
import org.opendaylight.controller.sal.binding.api.BindingAwareBroker.ProviderContext;
import org.opendaylight.controller.sal.binding.api.BindingAwareProvider;
import org.opendaylight.controller.sal.binding.api.NotificationProviderService;
import org.opendaylight.controller.sal.binding.api.RpcProviderRegistry;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.TopologyChanged;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.monitoring.rev150722.TopologyChangedBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.service.rev130819.SalFlowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; */


import com.google.common.base.Optional;
import com.google.common.util.concurrent.CheckedFuture;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.md.sal.binding.api.ReadOnlyTransaction;
import org.opendaylight.controller.md.sal.common.api.data.LogicalDatastoreType;
import org.opendaylight.controller.md.sal.common.api.data.ReadFailedException;
import org.opendaylight.controller.sal.binding.api.BindingAwareBroker;
import org.opendaylight.controller.sal.binding.api.BindingAwareBroker.ProviderContext;
import org.opendaylight.controller.sal.binding.api.BindingAwareProvider;
import org.opendaylight.controller.sal.binding.api.NotificationProviderService;
import org.opendaylight.controller.sal.binding.api.RpcProviderRegistry;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.service.rev130819.SalFlowService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.service.rev130709.PacketProcessingService;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.NetworkTopology;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.TopologyId;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.Topology;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.TopologyKey;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.Node;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.*;

//import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.Nodes;
//import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.Node;


public class ResourceMonitorProvider implements BindingAwareProvider, AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(ResourceMonitorProvider.class);
    //Members related to MD-SAL operations
    private DataBroker dataBroker;
    private SalFlowService salFlowService;
    private NotificationProviderService notificationService;
    private PacketProcessingService packetProcessingService;
    private BindingAwareBroker.RpcRegistration<ResourcemonitorService> serviceRegistration;

    public ResourceMonitorProvider(DataBroker dataBroker, RpcProviderRegistry rpcProviderRegistry,
                                   NotificationProviderService notificationService) {
        this.dataBroker = dataBroker;
        this.salFlowService = rpcProviderRegistry.getRpcService(SalFlowService.class);
        this.notificationService = notificationService;
        packetProcessingService = rpcProviderRegistry.getRpcService(PacketProcessingService.class);


        PacketProcessingService packetProcessingService = rpcProviderRegistry.getRpcService(PacketProcessingService.class);
        System.out.println("Resource Monitor Loaded Up");
        LOG.info("Resource Monitor loaded up");



        new Thread(new Runnable() {
            @Override
            public void run() {
                PacketProcessing packetProcessing = new PacketProcessing();
                notificationService.registerNotificationListener(packetProcessing);
            }
        }).start();




        final PacketSender packetSender = new PacketSender(packetProcessingService);

        final LatencyMonitor latencyMonitor = new LatencyMonitor(dataBroker, packetSender);

        ResourceMonitor resourceMonitor = new ResourceMonitor(dataBroker, latencyMonitor );


        serviceRegistration = rpcProviderRegistry.
                addRpcImplementation(ResourcemonitorService.class, resourceMonitor);

       /* final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("Running");



                try {
                    System.out.println( resourceMonitor.getAllLinksWithQos());

                    System.out.println("done with test 1");

                    Thread.sleep(10000);
                    System.out.println( resourceMonitor.getAllLinksWithQos());

                    System.out.println("done with test 2");

                    Thread.sleep(10000);

                    System.out.println( resourceMonitor.getAllLinksWithQos());

                    System.out.println("done with test 3");

                    Thread.sleep(10000);

                    System.out.println( resourceMonitor.getAllLinksWithQos());

                    System.out.println("done with test 4");

                    Thread.sleep(10000);

                    System.out.println( resourceMonitor.getAllLinksWithQos());

                    System.out.println("done with test 5");

                    Thread.sleep(10000);

                    System.out.println( resourceMonitor.getAllLinksWithQos());

                    System.out.println("done with test 6");

                    Thread.sleep(10000);

                    System.out.println( resourceMonitor.getAllLinksWithQos());

                    System.out.println("done with test last 7");



                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



            }
        }, 1, TimeUnit.MINUTES); */


        // TestResourceMonitor testResourceMonitor = new TestResourceMonitor();
       // System.out.println("Everything done in ResourceMonitorProvider");
    }

    @Override
    public void onSessionInitiated(ProviderContext session) {
        LOG.info("ResourceMonitorProvider Session Initiated");

    }

    @Override
    public void close() throws Exception {
        LOG.info("ResourceMonitorProvider Closed");
    }

   public static List<Node> getAllNodes(DataBroker db) {
        List<Node> nodeList = new ArrayList<>();

        try {
            TopologyId topoId = new TopologyId("flow:1");
            InstanceIdentifier<Topology> nodesIid = InstanceIdentifier.builder(NetworkTopology.class).child(Topology.class, new TopologyKey(topoId)).toInstance();
            ReadOnlyTransaction nodesTransaction = db.newReadOnlyTransaction();
            CheckedFuture<Optional<Topology>, ReadFailedException> nodesFuture = nodesTransaction
                    .read(LogicalDatastoreType.OPERATIONAL, nodesIid);
            Optional<Topology> nodesOptional = nodesFuture.checkedGet();

            if (nodesOptional != null && nodesOptional.isPresent())
                nodeList = nodesOptional.get().getNode();
            System.out.println("\n\n" + nodeList);
            LOG.info("yes");
            return nodeList;
        }

        catch (Exception e) {
            LOG.info("Node Fetching Failed");
        }

        return nodeList;
    }



    /*public Optional<Topology> getTopology(DataBroker db) {
        List<Node> nodeList = new ArrayList<>();

        try {
            TopologyId topoId = new TopologyId("flow:1");
            InstanceIdentifier topoIdentifier = InstanceIdentifier.builder(NetworkTopology.class).child(Topology.class, new TopologyKey(topoId)).toInstance();
            //ReadOnlyTransaction topoTransaction = db.newReadOnlyTransaction();
            //Topology nodesFuture = (Topology) topoTransaction
              //      .read(LogicalDatastoreType.OPERATIONAL, topoIdentifier);


            CheckedFuture<Optional<Topology>, ReadFailedException> topoFuture =
                (CheckedFuture<Optional<Topology>, ReadFailedException>) dataBroker
                .newReadOnlyTransaction().read(LogicalDatastoreType.OPERATIONAL, topoIdentifier);

                    Optional<Topology> topologyOpt = null;

                    if (topoFuture.isDone()) {
                                    System.out.println("work");
                                    LOG.info("work");
                                  topologyOpt = topoFuture.get();

                    }


                   

            //Topology t = (Topology) nodesFuture.checkedGet();

            System.out.println("about to return topology \n"); //+ t.toString());

             return topologyOpt;

           // return nodesFuture;
            /*if (nodesOptional != null && nodesOptional.isPresent())
                nodeList = nodesOptional.get().getNode();

            return nodeList;
        }
        }
        catch (Exception e) {

            System.out.println("Exception thrown----\n\n\n" );
            e.printStackTrace();
            LOG.info("Node Fetching Failed");
        }

        return null;
    }*/


}
