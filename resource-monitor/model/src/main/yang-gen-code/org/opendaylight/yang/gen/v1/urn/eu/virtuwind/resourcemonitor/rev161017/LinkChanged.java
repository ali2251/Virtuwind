package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Qos;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged.TopologyUpdate;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.Notification;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Link;


/**
 * A Link was changed
 *
 * <p>This class represents the following YANG schema fragment defined in module <b>resourcemonitor</b>
 * <pre>
 * notification linkChanged {
 *     description
 *         "A Link was changed";
 *     leaf topology-update {
 *         type enumeration;
 *     }
 *     container link {
 *         leaf link-id {
 *             type link-id;
 *         }
 *         container source {
 *             leaf source-node {
 *                 type node-ref;
 *             }
 *             leaf source-tp {
 *                 type tp-ref;
 *             }
 *         }
 *         container destination {
 *             leaf dest-node {
 *                 type node-ref;
 *             }
 *             leaf dest-tp {
 *                 type tp-ref;
 *             }
 *         }
 *         list supporting-link {
 *             key "link-ref"
 *             leaf link-ref {
 *                 type link-ref;
 *             }
 *         }
 *         uses link-attributes;
 *     }
 *     container qos {
 *         leaf bandwidth {
 *             type decimal64;
 *         }
 *         leaf packetLoss {
 *             type decimal64;
 *         }
 *         leaf packetDelay {
 *             type decimal64;
 *         }
 *         leaf jitter {
 *             type decimal64;
 *         }
 *         leaf num-queues {
 *             type int32;
 *         }
 *         leaf queue-sizes {
 *             type int64;
 *         }
 *         uses link-qos;
 *     }
 * }
 * </pre>
 * The schema path to identify an instance is
 * <i>resourcemonitor/linkChanged</i>
 *
 * <p>To create instances of this class use {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChangedBuilder}.
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChangedBuilder
 *
 */
public interface LinkChanged
    extends
    DataObject,
    Augmentable<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged>,
    Notification
{


    public enum TopologyUpdate {
        /**
         * The link was removed.
         *
         */
        LinkRemoved(0, "linkRemoved"),
        
        /**
         * The link was added.
         *
         */
        LinkAdded(1, "linkAdded"),
        
        /**
         * The link was updated
         *
         */
        LinkUpdated(2, "linkUpdated")
        ;
    
    
        java.lang.String name;
        int value;
        private static final java.util.Map<java.lang.Integer, TopologyUpdate> VALUE_MAP;
    
        static {
            final com.google.common.collect.ImmutableMap.Builder<java.lang.Integer, TopologyUpdate> b = com.google.common.collect.ImmutableMap.builder();
            for (TopologyUpdate enumItem : TopologyUpdate.values())
            {
                b.put(enumItem.value, enumItem);
            }
    
            VALUE_MAP = b.build();
        }
    
        private TopologyUpdate(int value, java.lang.String name) {
            this.value = value;
            this.name = name;
        }
    
        /**
         * Returns the name of the enumeration item as it is specified in the input yang.
         *
         * @return the name of the enumeration item as it is specified in the input yang
         */
        public java.lang.String getName() {
            return name;
        }
    
        /**
         * @return integer value
         */
        public int getIntValue() {
            return value;
        }
    
        /**
         * @param valueArg
         * @return corresponding TopologyUpdate item
         */
        public static TopologyUpdate forValue(int valueArg) {
            return VALUE_MAP.get(valueArg);
        }
    }

    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.create("urn:eu:virtuwind:resourcemonitor",
        "2016-10-17", "linkChanged").intern();

    /**
     * @return <code>org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged.TopologyUpdate</code> <code>topologyUpdate</code>, or <code>null</code> if not present
     */
    TopologyUpdate getTopologyUpdate();
    
    /**
     * @return <code>org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Link</code> <code>link</code>, or <code>null</code> if not present
     */
    Link getLink();
    
    /**
     * @return <code>org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Qos</code> <code>qos</code>, or <code>null</code> if not present
     */
    Qos getQos();

}

