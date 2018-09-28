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
 * Class that builds {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowOutput} instances.
 *
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowOutput
 *
 */
public class EmbedMplsFlowOutputBuilder implements Builder <org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowOutput> {

    private java.lang.String _response;

    Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowOutput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowOutput>> augmentation = Collections.emptyMap();

    public EmbedMplsFlowOutputBuilder() {
    }

    public EmbedMplsFlowOutputBuilder(EmbedMplsFlowOutput base) {
        this._response = base.getResponse();
        if (base instanceof EmbedMplsFlowOutputImpl) {
            EmbedMplsFlowOutputImpl impl = (EmbedMplsFlowOutputImpl) base;
            if (!impl.augmentation.isEmpty()) {
                this.augmentation = new HashMap<>(impl.augmentation);
            }
        } else if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowOutput> casted =(AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowOutput>) base;
            if (!casted.augmentations().isEmpty()) {
                this.augmentation = new HashMap<>(casted.augmentations());
            }
        }
    }


    public java.lang.String getResponse() {
        return _response;
    }
    
    @SuppressWarnings("unchecked")
    public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowOutput>> E getAugmentation(java.lang.Class<E> augmentationType) {
        if (augmentationType == null) {
            throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
        }
        return (E) augmentation.get(augmentationType);
    }

     
    public EmbedMplsFlowOutputBuilder setResponse(final java.lang.String value) {
        this._response = value;
        return this;
    }
    
    public EmbedMplsFlowOutputBuilder addAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowOutput>> augmentationType, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowOutput> augmentation) {
        if (augmentation == null) {
            return removeAugmentation(augmentationType);
        }
    
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }
    
    public EmbedMplsFlowOutputBuilder removeAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowOutput>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    public EmbedMplsFlowOutput build() {
        return new EmbedMplsFlowOutputImpl(this);
    }

    private static final class EmbedMplsFlowOutputImpl implements EmbedMplsFlowOutput {

        public java.lang.Class<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowOutput> getImplementedInterface() {
            return org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowOutput.class;
        }

        private final java.lang.String _response;

        private Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowOutput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowOutput>> augmentation = Collections.emptyMap();

        private EmbedMplsFlowOutputImpl(EmbedMplsFlowOutputBuilder base) {
            this._response = base.getResponse();
            switch (base.augmentation.size()) {
            case 0:
                this.augmentation = Collections.emptyMap();
                break;
            case 1:
                final Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowOutput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowOutput>> e = base.augmentation.entrySet().iterator().next();
                this.augmentation = Collections.<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowOutput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowOutput>>singletonMap(e.getKey(), e.getValue());
                break;
            default :
                this.augmentation = new HashMap<>(base.augmentation);
            }
        }

        @Override
        public java.lang.String getResponse() {
            return _response;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowOutput>> E getAugmentation(java.lang.Class<E> augmentationType) {
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
            result = prime * result + Objects.hashCode(_response);
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
            if (!org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowOutput.class.equals(((DataObject)obj).getImplementedInterface())) {
                return false;
            }
            org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowOutput other = (org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowOutput)obj;
            if (!Objects.equals(_response, other.getResponse())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                EmbedMplsFlowOutputImpl otherImpl = (EmbedMplsFlowOutputImpl) obj;
                if (!Objects.equals(augmentation, otherImpl.augmentation)) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowOutput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.EmbedMplsFlowOutput>> e : augmentation.entrySet()) {
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
            java.lang.StringBuilder builder = new java.lang.StringBuilder ("EmbedMplsFlowOutput [");
            boolean first = true;
        
            if (_response != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_response=");
                builder.append(_response);
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
