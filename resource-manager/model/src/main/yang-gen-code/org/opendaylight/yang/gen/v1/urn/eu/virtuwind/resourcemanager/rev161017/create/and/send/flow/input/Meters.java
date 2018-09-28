package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.CreateAndSendFlowInput;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * <p>This class represents the following YANG schema fragment defined in module <b>resourcemanager</b>
 * <pre>
 * list meters {
 *     key     leaf meter {
 *         type int32;
 *     }
 * }
 * </pre>
 * The schema path to identify an instance is
 * <i>resourcemanager/create-and-send-flow/input/meters</i>
 *
 * <p>To create instances of this class use {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input.MetersBuilder}.
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input.MetersBuilder
 *
 *
 */
public interface Meters
    extends
    ChildOf<CreateAndSendFlowInput>,
    Augmentable<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input.Meters>
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.create("urn:eu:virtuwind:resourcemanager",
        "2016-10-17", "meters").intern();

    /**
     * @return <code>java.lang.Integer</code> <code>meter</code>, or <code>null</code> if not present
     */
    java.lang.Integer getMeter();

}

