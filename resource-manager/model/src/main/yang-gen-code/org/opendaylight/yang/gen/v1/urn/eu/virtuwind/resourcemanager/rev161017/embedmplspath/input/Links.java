package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.embedmplspath.input;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsPathInput;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * <p>This class represents the following YANG schema fragment defined in module <b>resourcemanager</b>
 * <pre>
 * list links {
 *     key     leaf link {
 *         type string;
 *     }
 * }
 * </pre>
 * The schema path to identify an instance is
 * <i>resourcemanager/embedMplsPath/input/links</i>
 *
 * <p>To create instances of this class use {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.embedmplspath.input.LinksBuilder}.
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.embedmplspath.input.LinksBuilder
 *
 *
 */
public interface Links
    extends
    ChildOf<EmbedMplsPathInput>,
    Augmentable<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.embedmplspath.input.Links>
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.create("urn:eu:virtuwind:resourcemanager",
        "2016-10-17", "links").intern();

    /**
     * @return <code>java.lang.String</code> <code>link</code>, or <code>null</code> if not present
     */
    java.lang.String getLink();

}

