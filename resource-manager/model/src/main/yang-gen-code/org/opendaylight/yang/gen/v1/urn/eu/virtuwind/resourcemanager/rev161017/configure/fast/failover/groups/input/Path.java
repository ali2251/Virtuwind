package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.configure.fast.failover.groups.input;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.ConfigureFastFailoverGroupsInput;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * <p>This class represents the following YANG schema fragment defined in module <b>resourcemanager</b>
 * <pre>
 * list path {
 *     key     leaf link {
 *         type string;
 *     }
 * }
 * </pre>
 * The schema path to identify an instance is
 * <i>resourcemanager/configure-fast-failover-groups/input/path</i>
 *
 * <p>To create instances of this class use {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.configure.fast.failover.groups.input.PathBuilder}.
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.configure.fast.failover.groups.input.PathBuilder
 *
 *
 */
public interface Path
    extends
    ChildOf<ConfigureFastFailoverGroupsInput>,
    Augmentable<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.configure.fast.failover.groups.input.Path>
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.create("urn:eu:virtuwind:resourcemanager",
        "2016-10-17", "path").intern();

    /**
     * @return <code>java.lang.String</code> <code>link</code>, or <code>null</code> if not present
     */
    java.lang.String getLink();

}

