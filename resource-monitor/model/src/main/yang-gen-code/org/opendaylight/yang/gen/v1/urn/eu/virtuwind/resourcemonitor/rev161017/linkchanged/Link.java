package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.LinkAttributes;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * <p>This class represents the following YANG schema fragment defined in module <b>resourcemonitor</b>
 * <pre>
 * container link {
 *     leaf link-id {
 *         type link-id;
 *     }
 *     container source {
 *         leaf source-node {
 *             type node-ref;
 *         }
 *         leaf source-tp {
 *             type tp-ref;
 *         }
 *     }
 *     container destination {
 *         leaf dest-node {
 *             type node-ref;
 *         }
 *         leaf dest-tp {
 *             type tp-ref;
 *         }
 *     }
 *     list supporting-link {
 *         key "link-ref"
 *         leaf link-ref {
 *             type link-ref;
 *         }
 *     }
 *     uses link-attributes;
 * }
 * </pre>
 * The schema path to identify an instance is
 * <i>resourcemonitor/linkChanged/link</i>
 *
 * <p>To create instances of this class use {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.LinkBuilder}.
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.LinkBuilder
 *
 */
public interface Link
    extends
    ChildOf<LinkChanged>,
    Augmentable<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Link>,
    LinkAttributes
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.create("urn:eu:virtuwind:resourcemonitor",
        "2016-10-17", "link").intern();


}

