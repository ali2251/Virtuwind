package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.Meters;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;
import java.util.List;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinks;


/**
 * <p>This class represents the following YANG schema fragment defined in module <b>resourcemanager</b>
 * <pre>
 * container input {
 *     leaf sourceIP {
 *         type string;
 *     }
 *     leaf destinationIP {
 *         type string;
 *     }
 *     leaf source-port {
 *         type int32;
 *     }
 *     leaf dest-port {
 *         type int32;
 *     }
 *     leaf protocol {
 *         type int32;
 *     }
 *     list physical-path-links {
 *         key     leaf link {
 *             type string;
 *         }
 *     }
 *     leaf idletimeout {
 *         type int32;
 *     }
 *     leaf hardtimeout {
 *         type int32;
 *     }
 *     list meters {
 *         key     leaf meter {
 *             type int32;
 *         }
 *     }
 *     leaf src-node {
 *         type string;
 *     }
 *     leaf dest-node {
 *         type string;
 *     }
 *     leaf src-node-port {
 *         type string;
 *     }
 *     leaf dest-node-port {
 *         type string;
 *     }
 * }
 * </pre>
 * The schema path to identify an instance is
 * <i>resourcemanager/best-embeded-flow/input</i>
 *
 * <p>To create instances of this class use {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.BestEmbededFlowInputBuilder}.
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.BestEmbededFlowInputBuilder
 *
 */
public interface BestEmbededFlowInput
    extends
    DataObject,
    Augmentable<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.BestEmbededFlowInput>
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.create("urn:eu:virtuwind:resourcemanager",
        "2016-10-17", "input").intern();

    /**
     * @return <code>java.lang.String</code> <code>sourceIP</code>, or <code>null</code> if not present
     */
    java.lang.String getSourceIP();
    
    /**
     * @return <code>java.lang.String</code> <code>destinationIP</code>, or <code>null</code> if not present
     */
    java.lang.String getDestinationIP();
    
    /**
     * @return <code>java.lang.Integer</code> <code>sourcePort</code>, or <code>null</code> if not present
     */
    java.lang.Integer getSourcePort();
    
    /**
     * @return <code>java.lang.Integer</code> <code>destPort</code>, or <code>null</code> if not present
     */
    java.lang.Integer getDestPort();
    
    /**
     * @return <code>java.lang.Integer</code> <code>protocol</code>, or <code>null</code> if not present
     */
    java.lang.Integer getProtocol();
    
    /**
     * @return <code>java.util.List</code> <code>physicalPathLinks</code>, or <code>null</code> if not present
     */
    List<PhysicalPathLinks> getPhysicalPathLinks();
    
    /**
     * @return <code>java.lang.Integer</code> <code>idletimeout</code>, or <code>null</code> if not present
     */
    java.lang.Integer getIdletimeout();
    
    /**
     * @return <code>java.lang.Integer</code> <code>hardtimeout</code>, or <code>null</code> if not present
     */
    java.lang.Integer getHardtimeout();
    
    /**
     * @return <code>java.util.List</code> <code>meters</code>, or <code>null</code> if not present
     */
    List<Meters> getMeters();
    
    /**
     * @return <code>java.lang.String</code> <code>srcNode</code>, or <code>null</code> if not present
     */
    java.lang.String getSrcNode();
    
    /**
     * @return <code>java.lang.String</code> <code>destNode</code>, or <code>null</code> if not present
     */
    java.lang.String getDestNode();
    
    /**
     * @return <code>java.lang.String</code> <code>srcNodePort</code>, or <code>null</code> if not present
     */
    java.lang.String getSrcNodePort();
    
    /**
     * @return <code>java.lang.String</code> <code>destNodePort</code>, or <code>null</code> if not present
     */
    java.lang.String getDestNodePort();

}

