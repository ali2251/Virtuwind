package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.embedmplspath.input.Links;
import java.util.List;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * <p>This class represents the following YANG schema fragment defined in module <b>resourcemanager</b>
 * <pre>
 * container input {
 *     leaf mplsLabel {
 *         type int32;
 *     }
 *     list links {
 *         key     leaf link {
 *             type string;
 *         }
 *     }
 *     leaf queue-id {
 *         type int32;
 *     }
 * }
 * </pre>
 * The schema path to identify an instance is
 * <i>resourcemanager/embedMplsPath/input</i>
 *
 * <p>To create instances of this class use {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsPathInputBuilder}.
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsPathInputBuilder
 *
 */
public interface EmbedMplsPathInput
    extends
    DataObject,
    Augmentable<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsPathInput>
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.create("urn:eu:virtuwind:resourcemanager",
        "2016-10-17", "input").intern();

    /**
     * @return <code>java.lang.Integer</code> <code>mplsLabel</code>, or <code>null</code> if not present
     */
    java.lang.Integer getMplsLabel();
    
    /**
     * @return <code>java.util.List</code> <code>links</code>, or <code>null</code> if not present
     */
    List<Links> getLinks();
    
    /**
     * @return <code>java.lang.Integer</code> <code>queueId</code>, or <code>null</code> if not present
     */
    java.lang.Integer getQueueId();

}

