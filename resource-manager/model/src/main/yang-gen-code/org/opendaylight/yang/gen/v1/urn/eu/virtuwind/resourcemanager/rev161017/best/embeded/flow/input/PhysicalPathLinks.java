package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.BestEmbededFlowInput;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * <p>This class represents the following YANG schema fragment defined in module <b>resourcemanager</b>
 * <pre>
 * list physical-path-links {
 *     key     leaf link {
 *         type string;
 *     }
 * }
 * </pre>
 * The schema path to identify an instance is
 * <i>resourcemanager/best-embeded-flow/input/physical-path-links</i>
 *
 * <p>To create instances of this class use {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinksBuilder}.
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinksBuilder
 *
 *
 */
public interface PhysicalPathLinks
    extends
    ChildOf<BestEmbededFlowInput>,
    Augmentable<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinks>
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.create("urn:eu:virtuwind:resourcemanager",
        "2016-10-17", "physical-path-links").intern();

    /**
     * @return <code>java.lang.String</code> <code>link</code>, or <code>null</code> if not present
     */
    java.lang.String getLink();

}

