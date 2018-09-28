package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkQos;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * <p>This class represents the following YANG schema fragment defined in module <b>resourcemonitor</b>
 * <pre>
 * container qos {
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
 *     uses link-qos;
 * }
 * </pre>
 * The schema path to identify an instance is
 * <i>resourcemonitor/linkChanged/qos</i>
 *
 * <p>To create instances of this class use {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.QosBuilder}.
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.QosBuilder
 *
 */
public interface Qos
    extends
    ChildOf<LinkChanged>,
    Augmentable<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Qos>,
    LinkQos
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.create("urn:eu:virtuwind:resourcemonitor",
        "2016-10-17", "qos").intern();


}

