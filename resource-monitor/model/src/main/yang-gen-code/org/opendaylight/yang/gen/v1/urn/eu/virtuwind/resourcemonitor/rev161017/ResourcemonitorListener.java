package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017;
import org.opendaylight.yangtools.yang.binding.NotificationListener;


/**
 * Interface for receiving the following YANG notifications defined in module <b>resourcemonitor</b>
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
 *
 */
public interface ResourcemonitorListener
    extends
    NotificationListener
{




    /**
     * A Link was changed
     *
     */
    void onLinkChanged(LinkChanged notification);

}

