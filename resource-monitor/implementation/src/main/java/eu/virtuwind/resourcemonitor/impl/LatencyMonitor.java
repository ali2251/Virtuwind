package eu.virtuwind.resourcemonitor.impl;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.CheckedFuture;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.md.sal.binding.api.ReadOnlyTransaction;
import org.opendaylight.controller.md.sal.common.api.data.LogicalDatastoreType;
import org.opendaylight.controller.md.sal.common.api.data.ReadFailedException;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.service.rev130709.PacketProcessingService;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.NetworkTopology;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.TopologyId;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.Topology;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.TopologyKey;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.Link;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;


public class LatencyMonitor {

    private static DataBroker db;

    public static Long latency = -1L;

    private static HashMap<Link, Long> latencies = new HashMap<>();
    private static HashMap<Link, Long> jitters = new HashMap<>();
    private static ArrayList<Link> links = new ArrayList<>();
    private static PacketProcessingService packetProcessingService;
    private static PacketSender packetSender;

    public LatencyMonitor(DataBroker dataBroker, PacketSender packetSender1) {
        db = dataBroker;
        packetSender = packetSender1;
    }



    public LatencyJitterWrapper MeasureNextLink(Link link) {


        String node_id = link.getSource().getSourceNode().getValue();
        String node_connector_id = link.getSource().getSourceTp().getValue();


        latency = -10000000L;

        final Duration timeout = Duration.ofMillis(30);

        final Integer N = 100;

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<Long> call = new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                packetSender.sendPacket(0, node_connector_id, node_id);

                while (latency.equals(-10000000L)) {
                    //waiting
                    System.out.print("");

                }
                return latency;

            }
        };

        Long delay = 9999999L;

        List<Long> latencies = new ArrayList<>();

        Integer breakCounter = 0;

        for (int j = 0; j < N; j++) {

            delay = 9999999L;

            if (breakCounter > 1) {
                latencies.add(9999999L);
                break;
            }

            for (int i = 0; i < 3; i++) {



                if (delay != 9999999L) {
                    break;
                }
                Future<Long> f = executorService.submit(call);

                try {

                    delay = f.get(timeout.toMillis(), TimeUnit.MILLISECONDS);

                    latencies.add(delay);
                    breakCounter = 0;

                } catch (TimeoutException t) {
                    ++breakCounter;

                    f.cancel(true);

                } catch (Exception e1) {
                    e1.printStackTrace();
                }



            }

        }

        executorService.shutdownNow();



        Long latencyToreturn = 9999999L;
        Long jitter = 9999999L;

        if (latencies.contains(9999999L)) {
            //not reloable latency
            System.out.println("");

        } else {


            Double average = latencies.stream().mapToLong(val -> val).average().getAsDouble();

            latencyToreturn = average.longValue();


            Long sum = 0L;
            for (Long l : latencies) {
                Long absolute =  Math.abs(l - latencyToreturn);
                Long square = absolute * absolute;
                sum  += square;
            }

            Long daviation  = sum / N;

            Double standardDaviation = Math.sqrt(daviation);

            jitter = standardDaviation.longValue();





        }



        return new LatencyJitterWrapper(latencyToreturn, jitter);


    }




    public static List<Link> getAllLinks() {
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

            return linkList;
        } catch (Exception e) {


            return linkList;
        }

    }


}
