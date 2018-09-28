package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;
import java.math.BigDecimal;


/**
 * <p>This class represents the following YANG schema fragment defined in module <b>resourcemonitor</b>
 * <pre>
 * grouping link-qos {
 *     leaf bandwidth {
 *         type decimal64;
 *     }
 *     leaf packetLoss {
 *         type decimal64;
 *     }
 *     leaf packetDelay {
 *         type decimal64;
 *     }
 *     leaf jitter {
 *         type decimal64;
 *     }
 *     leaf num-queues {
 *         type int32;
 *     }
 *     leaf queue-sizes {
 *         type int64;
 *     }
 * }
 * </pre>
 * The schema path to identify an instance is
 * <i>resourcemonitor/link-qos</i>
 *
 */
public interface LinkQos
    extends
    DataObject
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.create("urn:eu:virtuwind:resourcemonitor",
        "2016-10-17", "link-qos").intern();

    /**
     * @return <code>java.math.BigDecimal</code> <code>bandwidth</code>, or <code>null</code> if not present
     */
    BigDecimal getBandwidth();
    
    /**
     * @return <code>java.math.BigDecimal</code> <code>packetLoss</code>, or <code>null</code> if not present
     */
    BigDecimal getPacketLoss();
    
    /**
     * @return <code>java.math.BigDecimal</code> <code>packetDelay</code>, or <code>null</code> if not present
     */
    BigDecimal getPacketDelay();
    
    /**
     * @return <code>java.math.BigDecimal</code> <code>jitter</code>, or <code>null</code> if not present
     */
    BigDecimal getJitter();
    
    /**
     * @return <code>java.lang.Integer</code> <code>numQueues</code>, or <code>null</code> if not present
     */
    java.lang.Integer getNumQueues();
    
    /**
     * Size of all the queues (in bytes)
     *
     *
     *
     * @return <code>java.lang.Long</code> <code>queueSizes</code>, or <code>null</code> if not present
     */
    java.lang.Long getQueueSizes();

}

