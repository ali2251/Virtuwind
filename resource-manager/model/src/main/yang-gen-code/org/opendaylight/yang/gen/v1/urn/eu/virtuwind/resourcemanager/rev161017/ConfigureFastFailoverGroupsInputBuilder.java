package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.configure.fast.failover.groups.input.Path;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.DataObject;
import java.util.HashMap;
import org.opendaylight.yangtools.concepts.Builder;
import java.util.Objects;
import java.util.List;
import java.util.Collections;
import java.util.Map;


/**
 * Class that builds {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.ConfigureFastFailoverGroupsInput} instances.
 *
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.ConfigureFastFailoverGroupsInput
 *
 */
public class ConfigureFastFailoverGroupsInputBuilder implements Builder <org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.ConfigureFastFailoverGroupsInput> {

    private java.lang.Integer _ffport;
    private List<Path> _path;

    Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.ConfigureFastFailoverGroupsInput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.ConfigureFastFailoverGroupsInput>> augmentation = Collections.emptyMap();

    public ConfigureFastFailoverGroupsInputBuilder() {
    }

    public ConfigureFastFailoverGroupsInputBuilder(ConfigureFastFailoverGroupsInput base) {
        this._ffport = base.getFfport();
        this._path = base.getPath();
        if (base instanceof ConfigureFastFailoverGroupsInputImpl) {
            ConfigureFastFailoverGroupsInputImpl impl = (ConfigureFastFailoverGroupsInputImpl) base;
            if (!impl.augmentation.isEmpty()) {
                this.augmentation = new HashMap<>(impl.augmentation);
            }
        } else if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.ConfigureFastFailoverGroupsInput> casted =(AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.ConfigureFastFailoverGroupsInput>) base;
            if (!casted.augmentations().isEmpty()) {
                this.augmentation = new HashMap<>(casted.augmentations());
            }
        }
    }


    public java.lang.Integer getFfport() {
        return _ffport;
    }
    
    public List<Path> getPath() {
        return _path;
    }
    
    @SuppressWarnings("unchecked")
    public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.ConfigureFastFailoverGroupsInput>> E getAugmentation(java.lang.Class<E> augmentationType) {
        if (augmentationType == null) {
            throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
        }
        return (E) augmentation.get(augmentationType);
    }

     
    public ConfigureFastFailoverGroupsInputBuilder setFfport(final java.lang.Integer value) {
        this._ffport = value;
        return this;
    }
    
     
    public ConfigureFastFailoverGroupsInputBuilder setPath(final List<Path> value) {
        this._path = value;
        return this;
    }
    
    public ConfigureFastFailoverGroupsInputBuilder addAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.ConfigureFastFailoverGroupsInput>> augmentationType, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.ConfigureFastFailoverGroupsInput> augmentation) {
        if (augmentation == null) {
            return removeAugmentation(augmentationType);
        }
    
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }
    
    public ConfigureFastFailoverGroupsInputBuilder removeAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.ConfigureFastFailoverGroupsInput>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    public ConfigureFastFailoverGroupsInput build() {
        return new ConfigureFastFailoverGroupsInputImpl(this);
    }

    private static final class ConfigureFastFailoverGroupsInputImpl implements ConfigureFastFailoverGroupsInput {

        public java.lang.Class<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.ConfigureFastFailoverGroupsInput> getImplementedInterface() {
            return org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.ConfigureFastFailoverGroupsInput.class;
        }

        private final java.lang.Integer _ffport;
        private final List<Path> _path;

        private Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.ConfigureFastFailoverGroupsInput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.ConfigureFastFailoverGroupsInput>> augmentation = Collections.emptyMap();

        private ConfigureFastFailoverGroupsInputImpl(ConfigureFastFailoverGroupsInputBuilder base) {
            this._ffport = base.getFfport();
            this._path = base.getPath();
            switch (base.augmentation.size()) {
            case 0:
                this.augmentation = Collections.emptyMap();
                break;
            case 1:
                final Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.ConfigureFastFailoverGroupsInput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.ConfigureFastFailoverGroupsInput>> e = base.augmentation.entrySet().iterator().next();
                this.augmentation = Collections.<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.ConfigureFastFailoverGroupsInput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.ConfigureFastFailoverGroupsInput>>singletonMap(e.getKey(), e.getValue());
                break;
            default :
                this.augmentation = new HashMap<>(base.augmentation);
            }
        }

        @Override
        public java.lang.Integer getFfport() {
            return _ffport;
        }
        
        @Override
        public List<Path> getPath() {
            return _path;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.ConfigureFastFailoverGroupsInput>> E getAugmentation(java.lang.Class<E> augmentationType) {
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
            result = prime * result + Objects.hashCode(_ffport);
            result = prime * result + Objects.hashCode(_path);
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
            if (!org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.ConfigureFastFailoverGroupsInput.class.equals(((DataObject)obj).getImplementedInterface())) {
                return false;
            }
            org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.ConfigureFastFailoverGroupsInput other = (org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.ConfigureFastFailoverGroupsInput)obj;
            if (!Objects.equals(_ffport, other.getFfport())) {
                return false;
            }
            if (!Objects.equals(_path, other.getPath())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                ConfigureFastFailoverGroupsInputImpl otherImpl = (ConfigureFastFailoverGroupsInputImpl) obj;
                if (!Objects.equals(augmentation, otherImpl.augmentation)) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.ConfigureFastFailoverGroupsInput>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017.ConfigureFastFailoverGroupsInput>> e : augmentation.entrySet()) {
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
            java.lang.StringBuilder builder = new java.lang.StringBuilder ("ConfigureFastFailoverGroupsInput [");
            boolean first = true;
        
            if (_ffport != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_ffport=");
                builder.append(_ffport);
             }
            if (_path != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_path=");
                builder.append(_path);
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
