package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.resourcemonitor.impl.RpcRegistry;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.rev130405.modules.module.Configuration;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.resourcemonitor.impl.NotificationService;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.resourcemonitor.impl.DataBroker;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * <p>This class represents the following YANG schema fragment defined in module <b>resourcemonitor-impl</b>
 * <pre>
 * case resourcemonitor-impl {
 *     container data-broker {
 *         leaf type {
 *             type leafref;
 *         }
 *         leaf name {
 *             type leafref;
 *         }
 *         uses service-ref {
 *             refine (urn:eu:virtuwind:resourcemonitor:impl?revision=2016-10-17)type {
 *                 leaf type {
 *                     type leafref;
 *                 }
 *             }
 *         }
 *     }
 *     container notification-service {
 *         leaf type {
 *             type leafref;
 *         }
 *         leaf name {
 *             type leafref;
 *         }
 *         uses service-ref {
 *             refine (urn:eu:virtuwind:resourcemonitor:impl?revision=2016-10-17)type {
 *                 leaf type {
 *                     type leafref;
 *                 }
 *             }
 *         }
 *     }
 *     container rpc-registry {
 *         leaf type {
 *             type leafref;
 *         }
 *         leaf name {
 *             type leafref;
 *         }
 *         uses service-ref {
 *             refine (urn:eu:virtuwind:resourcemonitor:impl?revision=2016-10-17)type {
 *                 leaf type {
 *                     type leafref;
 *                 }
 *             }
 *         }
 *     }
 * }
 * </pre>
 * The schema path to identify an instance is
 * <i>resourcemonitor-impl/modules/module/configuration/(urn:eu:virtuwind:resourcemonitor:impl?revision=2016-10-17)resourcemonitor-impl</i>
 *
 */
public interface ResourcemonitorImpl
    extends
    DataObject,
    Augmentable<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.ResourcemonitorImpl>,
    Configuration
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.create("urn:eu:virtuwind:resourcemonitor:impl",
        "2016-10-17", "resourcemonitor-impl").intern();

    /**
     * @return <code>org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.resourcemonitor.impl.DataBroker</code> <code>dataBroker</code>, or <code>null</code> if not present
     */
    DataBroker getDataBroker();
    
    /**
     * @return <code>org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.resourcemonitor.impl.NotificationService</code> <code>notificationService</code>, or <code>null</code> if not present
     */
    NotificationService getNotificationService();
    
    /**
     * @return <code>org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.resourcemonitor.impl.RpcRegistry</code> <code>rpcRegistry</code>, or <code>null</code> if not present
     */
    RpcRegistry getRpcRegistry();

}

