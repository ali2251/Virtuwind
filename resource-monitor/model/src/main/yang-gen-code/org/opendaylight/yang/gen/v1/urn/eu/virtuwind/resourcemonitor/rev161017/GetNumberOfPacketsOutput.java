package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import java.math.BigDecimal;


/**
 * <p>This class represents the following YANG schema fragment defined in module <b>resourcemonitor</b>
 * <pre>
 * container output {
 *     leaf transmitted {
 *         type decimal64;
 *     }
 *     leaf received {
 *         type decimal64;
 *     }
 * }
 * </pre>
 * The schema path to identify an instance is
 * <i>resourcemonitor/get-number-of-packets/output</i>
 *
 * <p>To create instances of this class use {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsOutputBuilder}.
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsOutputBuilder
 *
 */
public interface GetNumberOfPacketsOutput
    extends
    DataObject,
    Augmentable<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsOutput>
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.create("urn:eu:virtuwind:resourcemonitor",
        "2016-10-17", "output").intern();

    /**
     * @return <code>java.math.BigDecimal</code> <code>transmitted</code>, or <code>null</code> if not present
     */
    BigDecimal getTransmitted();
    
    /**
     * @return <code>java.math.BigDecimal</code> <code>received</code>, or <code>null</code> if not present
     */
    BigDecimal getReceived();

}

