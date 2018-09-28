package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.DataObject;
import java.util.HashMap;
import org.opendaylight.yangtools.concepts.Builder;
import java.util.Objects;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.embedmplspath.input.Links;
import java.util.List;
import java.util.Collections;
import java.util.Map;


/**
 * Class that builds {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsPathInput} instances.
 *
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsPathInput
 *
 */
public class EmbedMplsPathInputBuilder implements Builder <org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsPathInput> {

    private List<Links> _links;
    private java.lang.Integer _mplsLabel;
    private java.lang.Integer _queueId;

    Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsPathInput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsPathInput>> augmentation = Collections.emptyMap();

    public EmbedMplsPathInputBuilder() {
    }

    public EmbedMplsPathInputBuilder(EmbedMplsPathInput base) {
        this._links = base.getLinks();
        this._mplsLabel = base.getMplsLabel();
        this._queueId = base.getQueueId();
        if (base instanceof EmbedMplsPathInputImpl) {
            EmbedMplsPathInputImpl impl = (EmbedMplsPathInputImpl) base;
            if (!impl.augmentation.isEmpty()) {
                this.augmentation = new HashMap<>(impl.augmentation);
            }
        } else if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsPathInput> casted =(AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsPathInput>) base;
            if (!casted.augmentations().isEmpty()) {
                this.augmentation = new HashMap<>(casted.augmentations());
            }
        }
    }


    public List<Links> getLinks() {
        return _links;
    }
    
    public java.lang.Integer getMplsLabel() {
        return _mplsLabel;
    }
    
    public java.lang.Integer getQueueId() {
        return _queueId;
    }
    
    @SuppressWarnings("unchecked")
    public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsPathInput>> E getAugmentation(java.lang.Class<E> augmentationType) {
        if (augmentationType == null) {
            throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
        }
        return (E) augmentation.get(augmentationType);
    }

     
    public EmbedMplsPathInputBuilder setLinks(final List<Links> value) {
        this._links = value;
        return this;
    }
    
     
    public EmbedMplsPathInputBuilder setMplsLabel(final java.lang.Integer value) {
        this._mplsLabel = value;
        return this;
    }
    
     
    public EmbedMplsPathInputBuilder setQueueId(final java.lang.Integer value) {
        this._queueId = value;
        return this;
    }
    
    public EmbedMplsPathInputBuilder addAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsPathInput>> augmentationType, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsPathInput> augmentation) {
        if (augmentation == null) {
            return removeAugmentation(augmentationType);
        }
    
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }
    
    public EmbedMplsPathInputBuilder removeAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsPathInput>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    public EmbedMplsPathInput build() {
        return new EmbedMplsPathInputImpl(this);
    }

    private static final class EmbedMplsPathInputImpl implements EmbedMplsPathInput {

        public java.lang.Class<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsPathInput> getImplementedInterface() {
            return org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsPathInput.class;
        }

        private final List<Links> _links;
        private final java.lang.Integer _mplsLabel;
        private final java.lang.Integer _queueId;

        private Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsPathInput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsPathInput>> augmentation = Collections.emptyMap();

        private EmbedMplsPathInputImpl(EmbedMplsPathInputBuilder base) {
            this._links = base.getLinks();
            this._mplsLabel = base.getMplsLabel();
            this._queueId = base.getQueueId();
            switch (base.augmentation.size()) {
            case 0:
                this.augmentation = Collections.emptyMap();
                break;
            case 1:
                final Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsPathInput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsPathInput>> e = base.augmentation.entrySet().iterator().next();
                this.augmentation = Collections.<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsPathInput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsPathInput>>singletonMap(e.getKey(), e.getValue());
                break;
            default :
                this.augmentation = new HashMap<>(base.augmentation);
            }
        }

        @Override
        public List<Links> getLinks() {
            return _links;
        }
        
        @Override
        public java.lang.Integer getMplsLabel() {
            return _mplsLabel;
        }
        
        @Override
        public java.lang.Integer getQueueId() {
            return _queueId;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsPathInput>> E getAugmentation(java.lang.Class<E> augmentationType) {
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
            result = prime * result + Objects.hashCode(_links);
            result = prime * result + Objects.hashCode(_mplsLabel);
            result = prime * result + Objects.hashCode(_queueId);
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
            if (!org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsPathInput.class.equals(((DataObject)obj).getImplementedInterface())) {
                return false;
            }
            org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsPathInput other = (org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsPathInput)obj;
            if (!Objects.equals(_links, other.getLinks())) {
                return false;
            }
            if (!Objects.equals(_mplsLabel, other.getMplsLabel())) {
                return false;
            }
            if (!Objects.equals(_queueId, other.getQueueId())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                EmbedMplsPathInputImpl otherImpl = (EmbedMplsPathInputImpl) obj;
                if (!Objects.equals(augmentation, otherImpl.augmentation)) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsPathInput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsPathInput>> e : augmentation.entrySet()) {
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
            java.lang.StringBuilder builder = new java.lang.StringBuilder ("EmbedMplsPathInput [");
            boolean first = true;
        
            if (_links != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_links=");
                builder.append(_links);
             }
            if (_mplsLabel != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_mplsLabel=");
                builder.append(_mplsLabel);
             }
            if (_queueId != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_queueId=");
                builder.append(_queueId);
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
