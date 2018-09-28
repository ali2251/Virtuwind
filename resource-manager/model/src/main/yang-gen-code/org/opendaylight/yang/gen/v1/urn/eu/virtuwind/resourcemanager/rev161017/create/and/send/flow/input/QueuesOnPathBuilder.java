package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.DataObject;
import java.util.HashMap;
import org.opendaylight.yangtools.concepts.Builder;
import java.util.Objects;
import java.util.Collections;
import java.util.Map;


/**
 * Class that builds {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input.QueuesOnPath} instances.
 *
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input.QueuesOnPath
 *
 */
public class QueuesOnPathBuilder implements Builder <org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input.QueuesOnPath> {

    private java.lang.Integer _queue;

    Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input.QueuesOnPath>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input.QueuesOnPath>> augmentation = Collections.emptyMap();

    public QueuesOnPathBuilder() {
    }

    public QueuesOnPathBuilder(QueuesOnPath base) {
        this._queue = base.getQueue();
        if (base instanceof QueuesOnPathImpl) {
            QueuesOnPathImpl impl = (QueuesOnPathImpl) base;
            if (!impl.augmentation.isEmpty()) {
                this.augmentation = new HashMap<>(impl.augmentation);
            }
        } else if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input.QueuesOnPath> casted =(AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input.QueuesOnPath>) base;
            if (!casted.augmentations().isEmpty()) {
                this.augmentation = new HashMap<>(casted.augmentations());
            }
        }
    }


    public java.lang.Integer getQueue() {
        return _queue;
    }
    
    @SuppressWarnings("unchecked")
    public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input.QueuesOnPath>> E getAugmentation(java.lang.Class<E> augmentationType) {
        if (augmentationType == null) {
            throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
        }
        return (E) augmentation.get(augmentationType);
    }

     
    public QueuesOnPathBuilder setQueue(final java.lang.Integer value) {
        this._queue = value;
        return this;
    }
    
    public QueuesOnPathBuilder addAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input.QueuesOnPath>> augmentationType, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input.QueuesOnPath> augmentation) {
        if (augmentation == null) {
            return removeAugmentation(augmentationType);
        }
    
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }
    
    public QueuesOnPathBuilder removeAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input.QueuesOnPath>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    public QueuesOnPath build() {
        return new QueuesOnPathImpl(this);
    }

    private static final class QueuesOnPathImpl implements QueuesOnPath {

        public java.lang.Class<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input.QueuesOnPath> getImplementedInterface() {
            return org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input.QueuesOnPath.class;
        }

        private final java.lang.Integer _queue;

        private Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input.QueuesOnPath>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input.QueuesOnPath>> augmentation = Collections.emptyMap();

        private QueuesOnPathImpl(QueuesOnPathBuilder base) {
            this._queue = base.getQueue();
            switch (base.augmentation.size()) {
            case 0:
                this.augmentation = Collections.emptyMap();
                break;
            case 1:
                final Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input.QueuesOnPath>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input.QueuesOnPath>> e = base.augmentation.entrySet().iterator().next();
                this.augmentation = Collections.<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input.QueuesOnPath>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input.QueuesOnPath>>singletonMap(e.getKey(), e.getValue());
                break;
            default :
                this.augmentation = new HashMap<>(base.augmentation);
            }
        }

        @Override
        public java.lang.Integer getQueue() {
            return _queue;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input.QueuesOnPath>> E getAugmentation(java.lang.Class<E> augmentationType) {
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
            result = prime * result + Objects.hashCode(_queue);
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
            if (!org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input.QueuesOnPath.class.equals(((DataObject)obj).getImplementedInterface())) {
                return false;
            }
            org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input.QueuesOnPath other = (org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input.QueuesOnPath)obj;
            if (!Objects.equals(_queue, other.getQueue())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                QueuesOnPathImpl otherImpl = (QueuesOnPathImpl) obj;
                if (!Objects.equals(augmentation, otherImpl.augmentation)) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input.QueuesOnPath>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.create.and.send.flow.input.QueuesOnPath>> e : augmentation.entrySet()) {
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
            java.lang.StringBuilder builder = new java.lang.StringBuilder ("QueuesOnPath [");
            boolean first = true;
        
            if (_queue != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_queue=");
                builder.append(_queue);
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
