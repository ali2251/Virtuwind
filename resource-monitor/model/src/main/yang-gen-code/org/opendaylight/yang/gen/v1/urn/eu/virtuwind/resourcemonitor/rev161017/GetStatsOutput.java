package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * <p>This class represents the following YANG schema fragment defined in module <b>resourcemonitor</b>
 * <pre>
 * container output {
 *     leaf stats {
 *         type string;
 *     }
 * }
 * </pre>
 * The schema path to identify an instance is
 * <i>resourcemonitor/get-stats/output</i>
 *
 * <p>To create instances of this class use {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetStatsOutputBuilder}.
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetStatsOutputBuilder
 *
 */
public interface GetStatsOutput
    extends
    DataObject,
    Augmentable<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetStatsOutput>
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.create("urn:eu:virtuwind:resourcemonitor",
        "2016-10-17", "output").intern();

    /**
     * @return <code>java.lang.String</code> <code>stats</code>, or <code>null</code> if not present
     */
    java.lang.String getStats();

}

