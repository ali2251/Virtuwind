package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.configure.fast.failover.groups.input.Path;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;
import java.util.List;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * <p>This class represents the following YANG schema fragment defined in module <b>resourcemanager</b>
 * <pre>
 * container input {
 *     list path {
 *         key     leaf link {
 *             type string;
 *         }
 *     }
 *     leaf ffport {
 *         type int32;
 *     }
 * }
 * </pre>
 * The schema path to identify an instance is
 * <i>resourcemanager/configure-fast-failover-groups/input</i>
 *
 * <p>To create instances of this class use {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.ConfigureFastFailoverGroupsInputBuilder}.
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.ConfigureFastFailoverGroupsInputBuilder
 *
 */
public interface ConfigureFastFailoverGroupsInput
    extends
    DataObject,
    Augmentable<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.ConfigureFastFailoverGroupsInput>
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.create("urn:eu:virtuwind:resourcemanager",
        "2016-10-17", "input").intern();

    /**
     * @return <code>java.util.List</code> <code>path</code>, or <code>null</code> if not present
     */
    List<Path> getPath();
    
    /**
     * @return <code>java.lang.Integer</code> <code>ffport</code>, or <code>null</code> if not present
     */
    java.lang.Integer getFfport();

}

