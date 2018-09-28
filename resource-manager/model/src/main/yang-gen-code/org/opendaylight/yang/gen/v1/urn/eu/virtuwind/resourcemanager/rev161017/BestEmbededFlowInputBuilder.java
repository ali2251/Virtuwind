package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.Meters;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.DataObject;
import java.util.HashMap;
import org.opendaylight.yangtools.concepts.Builder;
import java.util.Objects;
import java.util.List;
import java.util.Collections;
import java.util.Map;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinks;


/**
 * Class that builds {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.BestEmbededFlowInput} instances.
 *
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.BestEmbededFlowInput
 *
 */
public class BestEmbededFlowInputBuilder implements Builder <org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.BestEmbededFlowInput> {

    private java.lang.String _destNode;
    private java.lang.String _destNodePort;
    private java.lang.Integer _destPort;
    private java.lang.String _destinationIP;
    private java.lang.Integer _hardtimeout;
    private java.lang.Integer _idletimeout;
    private List<Meters> _meters;
    private List<PhysicalPathLinks> _physicalPathLinks;
    private java.lang.Integer _protocol;
    private java.lang.String _sourceIP;
    private java.lang.Integer _sourcePort;
    private java.lang.String _srcNode;
    private java.lang.String _srcNodePort;

    Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.BestEmbededFlowInput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.BestEmbededFlowInput>> augmentation = Collections.emptyMap();

    public BestEmbededFlowInputBuilder() {
    }

    public BestEmbededFlowInputBuilder(BestEmbededFlowInput base) {
        this._destNode = base.getDestNode();
        this._destNodePort = base.getDestNodePort();
        this._destPort = base.getDestPort();
        this._destinationIP = base.getDestinationIP();
        this._hardtimeout = base.getHardtimeout();
        this._idletimeout = base.getIdletimeout();
        this._meters = base.getMeters();
        this._physicalPathLinks = base.getPhysicalPathLinks();
        this._protocol = base.getProtocol();
        this._sourceIP = base.getSourceIP();
        this._sourcePort = base.getSourcePort();
        this._srcNode = base.getSrcNode();
        this._srcNodePort = base.getSrcNodePort();
        if (base instanceof BestEmbededFlowInputImpl) {
            BestEmbededFlowInputImpl impl = (BestEmbededFlowInputImpl) base;
            if (!impl.augmentation.isEmpty()) {
                this.augmentation = new HashMap<>(impl.augmentation);
            }
        } else if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.BestEmbededFlowInput> casted =(AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.BestEmbededFlowInput>) base;
            if (!casted.augmentations().isEmpty()) {
                this.augmentation = new HashMap<>(casted.augmentations());
            }
        }
    }


    public java.lang.String getDestNode() {
        return _destNode;
    }
    
    public java.lang.String getDestNodePort() {
        return _destNodePort;
    }
    
    public java.lang.Integer getDestPort() {
        return _destPort;
    }
    
    public java.lang.String getDestinationIP() {
        return _destinationIP;
    }
    
    public java.lang.Integer getHardtimeout() {
        return _hardtimeout;
    }
    
    public java.lang.Integer getIdletimeout() {
        return _idletimeout;
    }
    
    public List<Meters> getMeters() {
        return _meters;
    }
    
    public List<PhysicalPathLinks> getPhysicalPathLinks() {
        return _physicalPathLinks;
    }
    
    public java.lang.Integer getProtocol() {
        return _protocol;
    }
    
    public java.lang.String getSourceIP() {
        return _sourceIP;
    }
    
    public java.lang.Integer getSourcePort() {
        return _sourcePort;
    }
    
    public java.lang.String getSrcNode() {
        return _srcNode;
    }
    
    public java.lang.String getSrcNodePort() {
        return _srcNodePort;
    }
    
    @SuppressWarnings("unchecked")
    public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.BestEmbededFlowInput>> E getAugmentation(java.lang.Class<E> augmentationType) {
        if (augmentationType == null) {
            throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
        }
        return (E) augmentation.get(augmentationType);
    }

     
    public BestEmbededFlowInputBuilder setDestNode(final java.lang.String value) {
        this._destNode = value;
        return this;
    }
    
     
    public BestEmbededFlowInputBuilder setDestNodePort(final java.lang.String value) {
        this._destNodePort = value;
        return this;
    }
    
     
    public BestEmbededFlowInputBuilder setDestPort(final java.lang.Integer value) {
        this._destPort = value;
        return this;
    }
    
     
    public BestEmbededFlowInputBuilder setDestinationIP(final java.lang.String value) {
        this._destinationIP = value;
        return this;
    }
    
     
    public BestEmbededFlowInputBuilder setHardtimeout(final java.lang.Integer value) {
        this._hardtimeout = value;
        return this;
    }
    
     
    public BestEmbededFlowInputBuilder setIdletimeout(final java.lang.Integer value) {
        this._idletimeout = value;
        return this;
    }
    
     
    public BestEmbededFlowInputBuilder setMeters(final List<Meters> value) {
        this._meters = value;
        return this;
    }
    
     
    public BestEmbededFlowInputBuilder setPhysicalPathLinks(final List<PhysicalPathLinks> value) {
        this._physicalPathLinks = value;
        return this;
    }
    
     
    public BestEmbededFlowInputBuilder setProtocol(final java.lang.Integer value) {
        this._protocol = value;
        return this;
    }
    
     
    public BestEmbededFlowInputBuilder setSourceIP(final java.lang.String value) {
        this._sourceIP = value;
        return this;
    }
    
     
    public BestEmbededFlowInputBuilder setSourcePort(final java.lang.Integer value) {
        this._sourcePort = value;
        return this;
    }
    
     
    public BestEmbededFlowInputBuilder setSrcNode(final java.lang.String value) {
        this._srcNode = value;
        return this;
    }
    
     
    public BestEmbededFlowInputBuilder setSrcNodePort(final java.lang.String value) {
        this._srcNodePort = value;
        return this;
    }
    
    public BestEmbededFlowInputBuilder addAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.BestEmbededFlowInput>> augmentationType, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.BestEmbededFlowInput> augmentation) {
        if (augmentation == null) {
            return removeAugmentation(augmentationType);
        }
    
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }
    
    public BestEmbededFlowInputBuilder removeAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.BestEmbededFlowInput>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    public BestEmbededFlowInput build() {
        return new BestEmbededFlowInputImpl(this);
    }

    private static final class BestEmbededFlowInputImpl implements BestEmbededFlowInput {

        public java.lang.Class<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.BestEmbededFlowInput> getImplementedInterface() {
            return org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.BestEmbededFlowInput.class;
        }

        private final java.lang.String _destNode;
        private final java.lang.String _destNodePort;
        private final java.lang.Integer _destPort;
        private final java.lang.String _destinationIP;
        private final java.lang.Integer _hardtimeout;
        private final java.lang.Integer _idletimeout;
        private final List<Meters> _meters;
        private final List<PhysicalPathLinks> _physicalPathLinks;
        private final java.lang.Integer _protocol;
        private final java.lang.String _sourceIP;
        private final java.lang.Integer _sourcePort;
        private final java.lang.String _srcNode;
        private final java.lang.String _srcNodePort;

        private Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.BestEmbededFlowInput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.BestEmbededFlowInput>> augmentation = Collections.emptyMap();

        private BestEmbededFlowInputImpl(BestEmbededFlowInputBuilder base) {
            this._destNode = base.getDestNode();
            this._destNodePort = base.getDestNodePort();
            this._destPort = base.getDestPort();
            this._destinationIP = base.getDestinationIP();
            this._hardtimeout = base.getHardtimeout();
            this._idletimeout = base.getIdletimeout();
            this._meters = base.getMeters();
            this._physicalPathLinks = base.getPhysicalPathLinks();
            this._protocol = base.getProtocol();
            this._sourceIP = base.getSourceIP();
            this._sourcePort = base.getSourcePort();
            this._srcNode = base.getSrcNode();
            this._srcNodePort = base.getSrcNodePort();
            switch (base.augmentation.size()) {
            case 0:
                this.augmentation = Collections.emptyMap();
                break;
            case 1:
                final Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.BestEmbededFlowInput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.BestEmbededFlowInput>> e = base.augmentation.entrySet().iterator().next();
                this.augmentation = Collections.<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.BestEmbededFlowInput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.BestEmbededFlowInput>>singletonMap(e.getKey(), e.getValue());
                break;
            default :
                this.augmentation = new HashMap<>(base.augmentation);
            }
        }

        @Override
        public java.lang.String getDestNode() {
            return _destNode;
        }
        
        @Override
        public java.lang.String getDestNodePort() {
            return _destNodePort;
        }
        
        @Override
        public java.lang.Integer getDestPort() {
            return _destPort;
        }
        
        @Override
        public java.lang.String getDestinationIP() {
            return _destinationIP;
        }
        
        @Override
        public java.lang.Integer getHardtimeout() {
            return _hardtimeout;
        }
        
        @Override
        public java.lang.Integer getIdletimeout() {
            return _idletimeout;
        }
        
        @Override
        public List<Meters> getMeters() {
            return _meters;
        }
        
        @Override
        public List<PhysicalPathLinks> getPhysicalPathLinks() {
            return _physicalPathLinks;
        }
        
        @Override
        public java.lang.Integer getProtocol() {
            return _protocol;
        }
        
        @Override
        public java.lang.String getSourceIP() {
            return _sourceIP;
        }
        
        @Override
        public java.lang.Integer getSourcePort() {
            return _sourcePort;
        }
        
        @Override
        public java.lang.String getSrcNode() {
            return _srcNode;
        }
        
        @Override
        public java.lang.String getSrcNodePort() {
            return _srcNodePort;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.BestEmbededFlowInput>> E getAugmentation(java.lang.Class<E> augmentationType) {
            if (augmentationType == null) {
                throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
            }
            return (E) augmentation.get(augmentationType);
        }

        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int prime = 31;
            int result = 1;
            result = prime * result + Objects.hashCode(_destNode);
            result = prime * result + Objects.hashCode(_destNodePort);
            result = prime * result + Objects.hashCode(_destPort);
            result = prime * result + Objects.hashCode(_destinationIP);
            result = prime * result + Objects.hashCode(_hardtimeout);
            result = prime * result + Objects.hashCode(_idletimeout);
            result = prime * result + Objects.hashCode(_meters);
            result = prime * result + Objects.hashCode(_physicalPathLinks);
            result = prime * result + Objects.hashCode(_protocol);
            result = prime * result + Objects.hashCode(_sourceIP);
            result = prime * result + Objects.hashCode(_sourcePort);
            result = prime * result + Objects.hashCode(_srcNode);
            result = prime * result + Objects.hashCode(_srcNodePort);
            result = prime * result + Objects.hashCode(augmentation);
        
            hash = result;
            hashValid = true;
            return result;
        }

        @Override
        public boolean equals(java.lang.Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DataObject)) {
                return false;
            }
            if (!org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.BestEmbededFlowInput.class.equals(((DataObject)obj).getImplementedInterface())) {
                return false;
            }
            org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.BestEmbededFlowInput other = (org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.BestEmbededFlowInput)obj;
            if (!Objects.equals(_destNode, other.getDestNode())) {
                return false;
            }
            if (!Objects.equals(_destNodePort, other.getDestNodePort())) {
                return false;
            }
            if (!Objects.equals(_destPort, other.getDestPort())) {
                return false;
            }
            if (!Objects.equals(_destinationIP, other.getDestinationIP())) {
                return false;
            }
            if (!Objects.equals(_hardtimeout, other.getHardtimeout())) {
                return false;
            }
            if (!Objects.equals(_idletimeout, other.getIdletimeout())) {
                return false;
            }
            if (!Objects.equals(_meters, other.getMeters())) {
                return false;
            }
            if (!Objects.equals(_physicalPathLinks, other.getPhysicalPathLinks())) {
                return false;
            }
            if (!Objects.equals(_protocol, other.getProtocol())) {
                return false;
            }
            if (!Objects.equals(_sourceIP, other.getSourceIP())) {
                return false;
            }
            if (!Objects.equals(_sourcePort, other.getSourcePort())) {
                return false;
            }
            if (!Objects.equals(_srcNode, other.getSrcNode())) {
                return false;
            }
            if (!Objects.equals(_srcNodePort, other.getSrcNodePort())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                BestEmbededFlowInputImpl otherImpl = (BestEmbededFlowInputImpl) obj;
                if (!Objects.equals(augmentation, otherImpl.augmentation)) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.BestEmbededFlowInput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.BestEmbededFlowInput>> e : augmentation.entrySet()) {
                    if (!e.getValue().equals(other.getAugmentation(e.getKey()))) {
                        return false;
                    }
                }
                // .. and give the other one the chance to do the same
                if (!obj.equals(this)) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public java.lang.String toString() {
            java.lang.StringBuilder builder = new java.lang.StringBuilder ("BestEmbededFlowInput [");
            boolean first = true;
        
            if (_destNode != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_destNode=");
                builder.append(_destNode);
             }
            if (_destNodePort != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_destNodePort=");
                builder.append(_destNodePort);
             }
            if (_destPort != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_destPort=");
                builder.append(_destPort);
             }
            if (_destinationIP != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_destinationIP=");
                builder.append(_destinationIP);
             }
            if (_hardtimeout != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_hardtimeout=");
                builder.append(_hardtimeout);
             }
            if (_idletimeout != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_idletimeout=");
                builder.append(_idletimeout);
             }
            if (_meters != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_meters=");
                builder.append(_meters);
             }
            if (_physicalPathLinks != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_physicalPathLinks=");
                builder.append(_physicalPathLinks);
             }
            if (_protocol != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_protocol=");
                builder.append(_protocol);
             }
            if (_sourceIP != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_sourceIP=");
                builder.append(_sourceIP);
             }
            if (_sourcePort != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_sourcePort=");
                builder.append(_sourcePort);
             }
            if (_srcNode != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_srcNode=");
                builder.append(_srcNode);
             }
            if (_srcNodePort != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_srcNodePort=");
                builder.append(_srcNodePort);
             }
            if (first) {
                first = false;
            } else {
                builder.append(", ");
            }
            builder.append("augmentation=");
            builder.append(augmentation.values());
            return builder.append(']').toString();
        }
    }

}
