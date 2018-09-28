package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.DataObject;
import java.util.HashMap;
import org.opendaylight.yangtools.concepts.Builder;
import java.util.Objects;
import java.util.Collections;
import java.util.Map;
import java.math.BigDecimal;


/**
 * Class that builds {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsOutput} instances.
 *
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsOutput
 *
 */
public class GetNumberOfPacketsOutputBuilder implements Builder <org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsOutput> {

    private BigDecimal _received;
    private BigDecimal _transmitted;

    Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsOutput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsOutput>> augmentation = Collections.emptyMap();

    public GetNumberOfPacketsOutputBuilder() {
    }

    public GetNumberOfPacketsOutputBuilder(GetNumberOfPacketsOutput base) {
        this._received = base.getReceived();
        this._transmitted = base.getTransmitted();
        if (base instanceof GetNumberOfPacketsOutputImpl) {
            GetNumberOfPacketsOutputImpl impl = (GetNumberOfPacketsOutputImpl) base;
            if (!impl.augmentation.isEmpty()) {
                this.augmentation = new HashMap<>(impl.augmentation);
            }
        } else if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsOutput> casted =(AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsOutput>) base;
            if (!casted.augmentations().isEmpty()) {
                this.augmentation = new HashMap<>(casted.augmentations());
            }
        }
    }


    public BigDecimal getReceived() {
        return _received;
    }
    
    public BigDecimal getTransmitted() {
        return _transmitted;
    }
    
    @SuppressWarnings("unchecked")
    public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsOutput>> E getAugmentation(java.lang.Class<E> augmentationType) {
        if (augmentationType == null) {
            throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
        }
        return (E) augmentation.get(augmentationType);
    }

     
    public GetNumberOfPacketsOutputBuilder setReceived(final BigDecimal value) {
        this._received = value;
        return this;
    }
    
     
    public GetNumberOfPacketsOutputBuilder setTransmitted(final BigDecimal value) {
        this._transmitted = value;
        return this;
    }
    
    public GetNumberOfPacketsOutputBuilder addAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsOutput>> augmentationType, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsOutput> augmentation) {
        if (augmentation == null) {
            return removeAugmentation(augmentationType);
        }
    
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }
    
    public GetNumberOfPacketsOutputBuilder removeAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsOutput>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    public GetNumberOfPacketsOutput build() {
        return new GetNumberOfPacketsOutputImpl(this);
    }

    private static final class GetNumberOfPacketsOutputImpl implements GetNumberOfPacketsOutput {

        public java.lang.Class<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsOutput> getImplementedInterface() {
            return org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsOutput.class;
        }

        private final BigDecimal _received;
        private final BigDecimal _transmitted;

        private Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsOutput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsOutput>> augmentation = Collections.emptyMap();

        private GetNumberOfPacketsOutputImpl(GetNumberOfPacketsOutputBuilder base) {
            this._received = base.getReceived();
            this._transmitted = base.getTransmitted();
            switch (base.augmentation.size()) {
            case 0:
                this.augmentation = Collections.emptyMap();
                break;
            case 1:
                final Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsOutput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsOutput>> e = base.augmentation.entrySet().iterator().next();
                this.augmentation = Collections.<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsOutput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsOutput>>singletonMap(e.getKey(), e.getValue());
                break;
            default :
                this.augmentation = new HashMap<>(base.augmentation);
            }
        }

        @Override
        public BigDecimal getReceived() {
            return _received;
        }
        
        @Override
        public BigDecimal getTransmitted() {
            return _transmitted;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsOutput>> E getAugmentation(java.lang.Class<E> augmentationType) {
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
            result = prime * result + Objects.hashCode(_received);
            result = prime * result + Objects.hashCode(_transmitted);
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
            if (!org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsOutput.class.equals(((DataObject)obj).getImplementedInterface())) {
                return false;
            }
            org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsOutput other = (org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsOutput)obj;
            if (!Objects.equals(_received, other.getReceived())) {
                return false;
            }
            if (!Objects.equals(_transmitted, other.getTransmitted())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                GetNumberOfPacketsOutputImpl otherImpl = (GetNumberOfPacketsOutputImpl) obj;
                if (!Objects.equals(augmentation, otherImpl.augmentation)) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsOutput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.GetNumberOfPacketsOutput>> e : augmentation.entrySet()) {
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
            java.lang.StringBuilder builder = new java.lang.StringBuilder ("GetNumberOfPacketsOutput [");
            boolean first = true;
        
            if (_received != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_received=");
                builder.append(_received);
             }
            if (_transmitted != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_transmitted=");
                builder.append(_transmitted);
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
