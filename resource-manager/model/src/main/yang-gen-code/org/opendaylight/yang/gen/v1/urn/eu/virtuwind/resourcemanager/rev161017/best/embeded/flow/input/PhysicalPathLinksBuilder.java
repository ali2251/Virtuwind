package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.DataObject;
import java.util.HashMap;
import org.opendaylight.yangtools.concepts.Builder;
import java.util.Objects;
import java.util.Collections;
import java.util.Map;


/**
 * Class that builds {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinks} instances.
 *
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinks
 *
 */
public class PhysicalPathLinksBuilder implements Builder <org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinks> {

    private java.lang.String _link;

    Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinks>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinks>> augmentation = Collections.emptyMap();

    public PhysicalPathLinksBuilder() {
    }

    public PhysicalPathLinksBuilder(PhysicalPathLinks base) {
        this._link = base.getLink();
        if (base instanceof PhysicalPathLinksImpl) {
            PhysicalPathLinksImpl impl = (PhysicalPathLinksImpl) base;
            if (!impl.augmentation.isEmpty()) {
                this.augmentation = new HashMap<>(impl.augmentation);
            }
        } else if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinks> casted =(AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinks>) base;
            if (!casted.augmentations().isEmpty()) {
                this.augmentation = new HashMap<>(casted.augmentations());
            }
        }
    }


    public java.lang.String getLink() {
        return _link;
    }
    
    @SuppressWarnings("unchecked")
    public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinks>> E getAugmentation(java.lang.Class<E> augmentationType) {
        if (augmentationType == null) {
            throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
        }
        return (E) augmentation.get(augmentationType);
    }

     
    public PhysicalPathLinksBuilder setLink(final java.lang.String value) {
        this._link = value;
        return this;
    }
    
    public PhysicalPathLinksBuilder addAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinks>> augmentationType, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinks> augmentation) {
        if (augmentation == null) {
            return removeAugmentation(augmentationType);
        }
    
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }
    
    public PhysicalPathLinksBuilder removeAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinks>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    public PhysicalPathLinks build() {
        return new PhysicalPathLinksImpl(this);
    }

    private static final class PhysicalPathLinksImpl implements PhysicalPathLinks {

        public java.lang.Class<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinks> getImplementedInterface() {
            return org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinks.class;
        }

        private final java.lang.String _link;

        private Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinks>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinks>> augmentation = Collections.emptyMap();

        private PhysicalPathLinksImpl(PhysicalPathLinksBuilder base) {
            this._link = base.getLink();
            switch (base.augmentation.size()) {
            case 0:
                this.augmentation = Collections.emptyMap();
                break;
            case 1:
                final Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinks>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinks>> e = base.augmentation.entrySet().iterator().next();
                this.augmentation = Collections.<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinks>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinks>>singletonMap(e.getKey(), e.getValue());
                break;
            default :
                this.augmentation = new HashMap<>(base.augmentation);
            }
        }

        @Override
        public java.lang.String getLink() {
            return _link;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinks>> E getAugmentation(java.lang.Class<E> augmentationType) {
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
            if (!org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinks.class.equals(((DataObject)obj).getImplementedInterface())) {
                return false;
            }
            org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinks other = (org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinks)obj;
            if (!Objects.equals(_link, other.getLink())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                PhysicalPathLinksImpl otherImpl = (PhysicalPathLinksImpl) obj;
                if (!Objects.equals(augmentation, otherImpl.augmentation)) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinks>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.best.embeded.flow.input.PhysicalPathLinks>> e : augmentation.entrySet()) {
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
            java.lang.StringBuilder builder = new java.lang.StringBuilder ("PhysicalPathLinks [");
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
