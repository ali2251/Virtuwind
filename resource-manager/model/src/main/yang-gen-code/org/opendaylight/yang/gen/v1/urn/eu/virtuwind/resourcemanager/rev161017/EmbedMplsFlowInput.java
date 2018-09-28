package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * <p>This class represents the following YANG schema fragment defined in module <b>resourcemanager</b>
 * <pre>
 * container input {
 *     leaf mpls-label {
 *         type int32;
 *     }
 *     leaf dst-mac {
 *         type string;
 *     }
 *     leaf mac-to-modify {
 *         type string;
 *     }
 *     leaf queue-id {
 *         type int32;
 *     }
 * }
 * </pre>
 * The schema path to identify an instance is
 * <i>resourcemanager/embedMplsFlow/input</i>
 *
 * <p>To create instances of this class use {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowInputBuilder}.
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowInputBuilder
 *
 */
public interface EmbedMplsFlowInput
    extends
    DataObject,
    Augmentable<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowInput>
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.create("urn:eu:virtuwind:resourcemanager",
        "2016-10-17", "input").intern();

    /**
     * @return <code>java.lang.Integer</code> <code>mplsLabel</code>, or <code>null</code> if not present
     */
    java.lang.Integer getMplsLabel();
    
    /**
     * @return <code>java.lang.String</code> <code>dstMac</code>, or <code>null</code> if not present
     */
    java.lang.String getDstMac();
    
    /**
     * @return <code>java.lang.String</code> <code>macToModify</code>, or <code>null</code> if not present
     */
    java.lang.String getMacToModify();
    
    /**
     * @return <code>java.lang.Integer</code> <code>queueId</code>, or <code>null</code> if not present
     */
    java.lang.Integer getQueueId();

}

