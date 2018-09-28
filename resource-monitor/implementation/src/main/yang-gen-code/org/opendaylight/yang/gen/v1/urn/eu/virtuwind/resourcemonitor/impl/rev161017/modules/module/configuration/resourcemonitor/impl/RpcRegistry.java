package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.resourcemonitor.impl;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.rev130405.modules.Module;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.rev130405.ServiceRef;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * <p>This class represents the following YANG schema fragment defined in module <b>resourcemonitor-impl</b>
 * <pre>
 * container rpc-registry {
 *     leaf type {
 *         type leafref;
 *     }
 *     leaf name {
 *         type leafref;
 *     }
 *     uses service-ref {
 *         refine (urn:eu:virtuwind:resourcemonitor:impl?revision=2016-10-17)type {
 *             leaf type {
 *                 type leafref;
 *             }
 *         }
 *     }
 * }
 * </pre>
 * The schema path to identify an instance is
 * <i>resourcemonitor-impl/modules/module/configuration/(urn:eu:virtuwind:resourcemonitor:impl?revision=2016-10-17)resourcemonitor-impl/rpc-registry</i>
 *
 * <p>To create instances of this class use {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.resourcemonitor.impl.RpcRegistryBuilder}.
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.resourcemonitor.impl.RpcRegistryBuilder
 *
 */
public interface RpcRegistry
    extends
    ChildOf<Module>,
    Augmentable<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.resourcemonitor.impl.RpcRegistry>,
    ServiceRef
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.create("urn:eu:virtuwind:resourcemonitor:impl",
        "2016-10-17", "rpc-registry").intern();


}

