package eu.virtuwind.resourcemanager.impl;

/**
 * Created by Ali on 01/08/2017.
 */

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.*;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.node.NodeConnector;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.node.NodeConnectorKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.NodeKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.meter.service.rev130918.AddMeterInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.meter.service.rev130918.AddMeterInputBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.meter.service.rev130918.RemoveMeterInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.meter.service.rev130918.RemoveMeterInputBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.meter.types.rev130918.MeterBandType;
import org.opendaylight.yang.gen.v1.urn.opendaylight.meter.types.rev130918.MeterId;
import org.opendaylight.yang.gen.v1.urn.opendaylight.meter.types.rev130918.BandId;
import org.opendaylight.yang.gen.v1.urn.opendaylight.meter.types.rev130918.MeterFlags;
import org.opendaylight.yang.gen.v1.urn.opendaylight.meter.types.rev130918.meter.meter.band.headers.MeterBandHeaderKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.meter.types.rev130918.band.type.band.type.Drop;
import org.opendaylight.yang.gen.v1.urn.opendaylight.meter.types.rev130918.band.type.band.type.DropBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.meter.types.rev130918.meter.MeterBandHeaders;
import org.opendaylight.yang.gen.v1.urn.opendaylight.meter.types.rev130918.meter.MeterBandHeadersBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.meter.types.rev130918.meter.meter.band.headers.MeterBandHeader;
import org.opendaylight.yang.gen.v1.urn.opendaylight.meter.types.rev130918.meter.meter.band.headers.MeterBandHeaderBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.meter.types.rev130918.meter.meter.band.headers.meter.band.header.MeterBandTypesBuilder;

import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;

/**
 * A helper class to construct MD-SAL Meters
 */
public class VTNMeterUtils {


    /**
     * The Meter ID  to be set.
     */
    @XmlElement
    private MeterId meterid;

    /**
     * The Bandwidth value (in kbps) to be set.
     */
    @XmlElement
    private long bandwidth;

    /**
     * The burst value (in bytes) to be set.
     */
    @XmlElement
    private long burst;

    /**
     * Meter bands to construct Inputs
     */
    private MeterBandHeaders meterBandHeaders;

    /**
     * Meter flags to construct Inputs
     */
    private MeterFlags meterFlags;

    /**
     * Construct an empty instance.
     */
    VTNMeterUtils() {
    }

    /**
     * Construct a new instance without specifying instruction order.
     *
     * @param metid The Meter ID to be set.
     * @param band  The Bandwidth value [kbps] to be set.
     * @param bur   The the Burst value [bytes]to be set.
     */
    public VTNMeterUtils(MeterId metid, Long band, Long bur) {
        meterid = metid;
        bandwidth = band;
        burst = bur;
    }

    private void buildMeterBandHeaders() {
        MeterBandHeaderBuilder meterBandHeaderBuilder = new MeterBandHeaderBuilder();
        MeterBandTypesBuilder meterBandTypesBuilder = new MeterBandTypesBuilder();
        MeterBandType bandFlag = new MeterBandType(true, false, false);
        meterBandTypesBuilder.setFlags(bandFlag); //_ofpmbtDrop
        DropBuilder drop = new DropBuilder();
        drop.setDropBurstSize(burst);
        drop.setDropRate(bandwidth);
        Drop drp = drop.build();
        meterBandHeaderBuilder.setBandType(drp);
        meterBandHeaderBuilder.setBandBurstSize(burst);
        meterBandHeaderBuilder.setBandRate(bandwidth);

        int bandKey = 0;
        BandId bandId = new BandId((long) bandKey);
        meterBandHeaderBuilder.setKey(new MeterBandHeaderKey(bandId));
        meterBandHeaderBuilder.setBandId(bandId);

        meterBandHeaderBuilder.setMeterBandTypes(meterBandTypesBuilder.build());
        MeterBandHeader meterBH = meterBandHeaderBuilder.build();

        List<MeterBandHeader> meterBandList = new ArrayList<>();
        MeterBandHeadersBuilder meterBandHeadersBuilder = new MeterBandHeadersBuilder();

        meterBandList.add(0, meterBH);
        meterBandHeadersBuilder.setMeterBandHeader(meterBandList);
        meterBandHeaders = meterBandHeadersBuilder.build();
        meterFlags = new MeterFlags(true, true, false, false);
    }

    /**
     * Create a new AddMeterInput instance for a particular MD-SAL switch.
     *
     * @return A {@link AddMeterInput} instance.
     */
    public AddMeterInput createAddMeterInput(NodeId nodeId) {


        NodeRef nodeRef = getNodeRef(nodeId);


        buildMeterBandHeaders();
        return new AddMeterInputBuilder().setMeterId(meterid).setMeterBandHeaders(meterBandHeaders).
                setNode(nodeRef).setFlags(meterFlags).build();
    }

    public static final InstanceIdentifier<Node> createNodePath(final NodeId nodeId) {
        return InstanceIdentifier.builder(Nodes.class)
                .child(Node.class, new NodeKey(nodeId))
                .build();
    }

    private NodeRef getNodeRef(NodeId nodeId) {



/*        NodeConnectorId nodeConnectorId = new NodeConnectorId("openflow:2:1");


        NodeConnectorRef nodeConnectorRef = new NodeConnectorRef(InstanceIdentifier.builder(Nodes.class)
                .child(Node.class, new NodeKey(nodeId))
                .child(NodeConnector.class, new NodeConnectorKey(nodeConnectorId))
                .build());

        InstanceIdentifier<Node> nodeIID = nodeConnectorRef.getValue()
                .firstIdentifierOf(Node.class);
*/

        InstanceIdentifier<Node> nodeIID = createNodePath(nodeId);

        return new NodeRef(nodeIID);
    }

    public RemoveMeterInput createRemoveMeterInput(NodeId nodeId) {

        NodeRef nodeRef = getNodeRef(nodeId);

        buildMeterBandHeaders();
        return new RemoveMeterInputBuilder().setMeterId(meterid).setMeterBandHeaders(meterBandHeaders).
                setNode(nodeRef).setFlags(meterFlags).build();
    }
}
