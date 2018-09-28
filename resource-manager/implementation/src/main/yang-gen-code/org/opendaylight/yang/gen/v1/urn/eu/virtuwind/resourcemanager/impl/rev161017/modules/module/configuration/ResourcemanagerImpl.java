package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.resourcemanager.impl.RpcRegistry;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.rev130405.modules.module.Configuration;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.resourcemanager.impl.NotificationService;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.resourcemanager.impl.DataBroker;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * <p>This class represents the following YANG schema fragment defined in module <b>resourcemanager-impl</b>
 * <pre>
 * case resourcemanager-impl {
 *     container data-broker {
 *         leaf type {
 *             type leafref;
 *         }
 *         leaf name {
 *             type leafref;
 *         }
 *         uses service-ref {
 *             refine (urn:eu:virtuwind:resourcemanager:impl?revision=2016-10-17)type {
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
 *             refine (urn:eu:virtuwind:resourcemanager:impl?revision=2016-10-17)type {
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
 *             refine (urn:eu:virtuwind:resourcemanager:impl?revision=2016-10-17)type {
 *                 leaf type {
 *                     type leafref;
 *                 }
 *             }
 *         }
 *     }
 * }
 * </pre>
 * The schema path to identify an instance is
 * <i>resourcemanager-impl/modules/module/configuration/(urn:eu:virtuwind:resourcemanager:impl?revision=2016-10-17)resourcemanager-impl</i>
 *
 */
public interface ResourcemanagerImpl
    extends
    DataObject,
    Augmentable<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.ResourcemanagerImpl>,
    Configuration
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.create("urn:eu:virtuwind:resourcemanager:impl",
        "2016-10-17", "resourcemanager-impl").intern();

    /**
     * @return <code>org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.resourcemanager.impl.DataBroker</code> <code>dataBroker</code>, or <code>null</code> if not present
     */
    DataBroker getDataBroker();
    
    /**
     * @return <code>org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.resourcemanager.impl.NotificationService</code> <code>notificationService</code>, or <code>null</code> if not present
     */
    NotificationService getNotificationService();
    
    /**
     * @return <code>org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.resourcemanager.impl.RpcRegistry</code> <code>rpcRegistry</code>, or <code>null</code> if not present
     */
    RpcRegistry getRpcRegistry();

}

