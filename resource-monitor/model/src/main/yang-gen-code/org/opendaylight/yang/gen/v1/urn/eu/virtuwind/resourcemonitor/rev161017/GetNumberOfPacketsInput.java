package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * <p>This class represents the following YANG schema fragment defined in module <b>resourcemonitor</b>
 * <pre>
 * container input {
 *     leaf node-connector-id {
 *         type string;
 *     }
 * }
 * </pre>
 * The schema path to identify an instance is
 * <i>resourcemonitor/get-number-of-packets/input</i>
 *
 * <p>To create instances of this class use {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsInputBuilder}.
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsInputBuilder
 *
 */
public interface GetNumberOfPacketsInput
    extends
    DataObject,
    Augmentable<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsInput>
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.create("urn:eu:virtuwind:resourcemonitor",
        "2016-10-17", "input").intern();

    /**
     * @return <code>java.lang.String</code> <code>nodeConnectorId</code>, or <code>null</code> if not present
     */
    java.lang.String getNodeConnectorId();

}

