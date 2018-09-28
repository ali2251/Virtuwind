package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Qos;
import java.util.HashMap;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged.TopologyUpdate;
import java.util.Objects;
import java.util.Collections;
import java.util.Map;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Link;


/**
 * Class that builds {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged} instances.
 *
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged
 *
 */
public class LinkChangedBuilder implements Builder <org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged> {

    private Link _link;
    private Qos _qos;
    private TopologyUpdate _topologyUpdate;

    Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged>> augmentation = Collections.emptyMap();

    public LinkChangedBuilder() {
    }

    public LinkChangedBuilder(LinkChanged base) {
        this._link = base.getLink();
        this._qos = base.getQos();
        this._topologyUpdate = base.getTopologyUpdate();
        if (base instanceof LinkChangedImpl) {
            LinkChangedImpl impl = (LinkChangedImpl) base;
            if (!impl.augmentation.isEmpty()) {
                this.augmentation = new HashMap<>(impl.augmentation);
            }
        } else if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged> casted =(AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged>) base;
            if (!casted.augmentations().isEmpty()) {
                this.augmentation = new HashMap<>(casted.augmentations());
            }
        }
    }


    public Link getLink() {
        return _link;
    }
    
    public Qos getQos() {
        return _qos;
    }
    
    public TopologyUpdate getTopologyUpdate() {
        return _topologyUpdate;
    }
    
    @SuppressWarnings("unchecked")
    public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged>> E getAugmentation(java.lang.Class<E> augmentationType) {
        if (augmentationType == null) {
            throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
        }
        return (E) augmentation.get(augmentationType);
    }

     
    public LinkChangedBuilder setLink(final Link value) {
        this._link = value;
        return this;
    }
    
     
    public LinkChangedBuilder setQos(final Qos value) {
        this._qos = value;
        return this;
    }
    
     
    public LinkChangedBuilder setTopologyUpdate(final TopologyUpdate value) {
        this._topologyUpdate = value;
        return this;
    }
    
    public LinkChangedBuilder addAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged>> augmentationType, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged> augmentation) {
        if (augmentation == null) {
            return removeAugmentation(augmentationType);
        }
    
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }
    
    public LinkChangedBuilder removeAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    public LinkChanged build() {
        return new LinkChangedImpl(this);
    }

    private static final class LinkChangedImpl implements LinkChanged {

        public java.lang.Class<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged> getImplementedInterface() {
            return org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged.class;
        }

        private final Link _link;
        private final Qos _qos;
        private final TopologyUpdate _topologyUpdate;

        private Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged>> augmentation = Collections.emptyMap();

        private LinkChangedImpl(LinkChangedBuilder base) {
            this._link = base.getLink();
            this._qos = base.getQos();
            this._topologyUpdate = base.getTopologyUpdate();
            switch (base.augmentation.size()) {
            case 0:
                this.augmentation = Collections.emptyMap();
                break;
            case 1:
                final Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged>> e = base.augmentation.entrySet().iterator().next();
                this.augmentation = Collections.<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged>>singletonMap(e.getKey(), e.getValue());
                break;
            default :
                this.augmentation = new HashMap<>(base.augmentation);
            }
        }

        @Override
        public Link getLink() {
            return _link;
        }
        
        @Override
        public Qos getQos() {
            return _qos;
        }
        
        @Override
        public TopologyUpdate getTopologyUpdate() {
            return _topologyUpdate;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged>> E getAugmentation(java.lang.Class<E> augmentationType) {
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
            result = prime * result + Objects.hashCode(_link);
            result = prime * result + Objects.hashCode(_qos);
            result = prime * result + Objects.hashCode(_topologyUpdate);
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
            if (!org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged.class.equals(((DataObject)obj).getImplementedInterface())) {
                return false;
            }
            org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged other = (org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged)obj;
            if (!Objects.equals(_link, other.getLink())) {
                return false;
            }
            if (!Objects.equals(_qos, other.getQos())) {
                return false;
            }
            if (!Objects.equals(_topologyUpdate, other.getTopologyUpdate())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                LinkChangedImpl otherImpl = (LinkChangedImpl) obj;
                if (!Objects.equals(augmentation, otherImpl.augmentation)) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkChanged>> e : augmentation.entrySet()) {
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
            java.lang.StringBuilder builder = new java.lang.StringBuilder ("LinkChanged [");
            boolean first = true;
        
            if (_link != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_link=");
                builder.append(_link);
             }
            if (_qos != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_qos=");
                builder.append(_qos);
             }
            if (_topologyUpdate != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_topologyUpdate=");
                builder.append(_topologyUpdate);
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
