package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.resourcemanager.impl;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.rev130405.modules.Module;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.rev130405.ServiceRef;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * <p>This class represents the following YANG schema fragment defined in module <b>resourcemanager-impl</b>
 * <pre>
 * container data-broker {
 *     leaf type {
 *         type leafref;
 *     }
 *     leaf name {
 *         type leafref;
 *     }
 *     uses service-ref {
 *         refine (urn:eu:virtuwind:resourcemanager:impl?revision=2016-10-17)type {
 *             leaf type {
 *                 type leafref;
 *             }
 *         }
 *     }
 * }
 * </pre>
 * The schema path to identify an instance is
 * <i>resourcemanager-impl/modules/module/configuration/(urn:eu:virtuwind:resourcemanager:impl?revision=2016-10-17)resourcemanager-impl/data-broker</i>
 *
 * <p>To create instances of this class use {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.resourcemanager.impl.DataBrokerBuilder}.
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.resourcemanager.impl.DataBrokerBuilder
 *
 */
public interface DataBroker
    extends
    ChildOf<Module>,
    Augmentable<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.resourcemanager.impl.DataBroker>,
    ServiceRef
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.create("urn:eu:virtuwind:resourcemanager:impl",
        "2016-10-17", "data-broker").intern();


}

