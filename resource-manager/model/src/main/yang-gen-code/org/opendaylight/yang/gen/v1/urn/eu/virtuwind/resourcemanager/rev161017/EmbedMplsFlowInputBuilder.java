package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.DataObject;
import java.util.HashMap;
import org.opendaylight.yangtools.concepts.Builder;
import java.util.Objects;
import java.util.Collections;
import java.util.Map;


/**
 * Class that builds {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowInput} instances.
 *
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowInput
 *
 */
public class EmbedMplsFlowInputBuilder implements Builder <org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowInput> {

    private java.lang.String _dstMac;
    private java.lang.String _macToModify;
    private java.lang.Integer _mplsLabel;
    private java.lang.Integer _queueId;

    Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowInput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowInput>> augmentation = Collections.emptyMap();

    public EmbedMplsFlowInputBuilder() {
    }

    public EmbedMplsFlowInputBuilder(EmbedMplsFlowInput base) {
        this._dstMac = base.getDstMac();
        this._macToModify = base.getMacToModify();
        this._mplsLabel = base.getMplsLabel();
        this._queueId = base.getQueueId();
        if (base instanceof EmbedMplsFlowInputImpl) {
            EmbedMplsFlowInputImpl impl = (EmbedMplsFlowInputImpl) base;
            if (!impl.augmentation.isEmpty()) {
                this.augmentation = new HashMap<>(impl.augmentation);
            }
        } else if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowInput> casted =(AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowInput>) base;
            if (!casted.augmentations().isEmpty()) {
                this.augmentation = new HashMap<>(casted.augmentations());
            }
        }
    }


    public java.lang.String getDstMac() {
        return _dstMac;
    }
    
    public java.lang.String getMacToModify() {
        return _macToModify;
    }
    
    public java.lang.Integer getMplsLabel() {
        return _mplsLabel;
    }
    
    public java.lang.Integer getQueueId() {
        return _queueId;
    }
    
    @SuppressWarnings("unchecked")
    public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowInput>> E getAugmentation(java.lang.Class<E> augmentationType) {
        if (augmentationType == null) {
            throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
        }
        return (E) augmentation.get(augmentationType);
    }

     
    public EmbedMplsFlowInputBuilder setDstMac(final java.lang.String value) {
        this._dstMac = value;
        return this;
    }
    
     
    public EmbedMplsFlowInputBuilder setMacToModify(final java.lang.String value) {
        this._macToModify = value;
        return this;
    }
    
     
    public EmbedMplsFlowInputBuilder setMplsLabel(final java.lang.Integer value) {
        this._mplsLabel = value;
        return this;
    }
    
     
    public EmbedMplsFlowInputBuilder setQueueId(final java.lang.Integer value) {
        this._queueId = value;
        return this;
    }
    
    public EmbedMplsFlowInputBuilder addAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowInput>> augmentationType, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowInput> augmentation) {
        if (augmentation == null) {
            return removeAugmentation(augmentationType);
        }
    
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }
    
    public EmbedMplsFlowInputBuilder removeAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowInput>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    public EmbedMplsFlowInput build() {
        return new EmbedMplsFlowInputImpl(this);
    }

    private static final class EmbedMplsFlowInputImpl implements EmbedMplsFlowInput {

        public java.lang.Class<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowInput> getImplementedInterface() {
            return org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowInput.class;
        }

        private final java.lang.String _dstMac;
        private final java.lang.String _macToModify;
        private final java.lang.Integer _mplsLabel;
        private final java.lang.Integer _queueId;

        private Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowInput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowInput>> augmentation = Collections.emptyMap();

        private EmbedMplsFlowInputImpl(EmbedMplsFlowInputBuilder base) {
            this._dstMac = base.getDstMac();
            this._macToModify = base.getMacToModify();
            this._mplsLabel = base.getMplsLabel();
            this._queueId = base.getQueueId();
            switch (base.augmentation.size()) {
            case 0:
                this.augmentation = Collections.emptyMap();
                break;
            case 1:
                final Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowInput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowInput>> e = base.augmentation.entrySet().iterator().next();
                this.augmentation = Collections.<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowInput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowInput>>singletonMap(e.getKey(), e.getValue());
                break;
            default :
                this.augmentation = new HashMap<>(base.augmentation);
            }
        }

        @Override
        public java.lang.String getDstMac() {
            return _dstMac;
        }
        
        @Override
        public java.lang.String getMacToModify() {
            return _macToModify;
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
        public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowInput>> E getAugmentation(java.lang.Class<E> augmentationType) {
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
            result = prime * result + Objects.hashCode(_dstMac);
            result = prime * result + Objects.hashCode(_macToModify);
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
            if (!org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowInput.class.equals(((DataObject)obj).getImplementedInterface())) {
                return false;
            }
            org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowInput other = (org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowInput)obj;
            if (!Objects.equals(_dstMac, other.getDstMac())) {
                return false;
            }
            if (!Objects.equals(_macToModify, other.getMacToModify())) {
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
                EmbedMplsFlowInputImpl otherImpl = (EmbedMplsFlowInputImpl) obj;
                if (!Objects.equals(augmentation, otherImpl.augmentation)) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowInput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowInput>> e : augmentation.entrySet()) {
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
            java.lang.StringBuilder builder = new java.lang.StringBuilder ("EmbedMplsFlowInput [");
            boolean first = true;
        
            if (_dstMac != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_dstMac=");
                builder.append(_dstMac);
             }
            if (_macToModify != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_macToModify=");
                builder.append(_macToModify);
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
