package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.resourcemonitor.impl.RpcRegistry;
import org.opendaylight.yangtools.yang.binding.DataObject;
import java.util.HashMap;
import org.opendaylight.yangtools.concepts.Builder;
import java.util.Objects;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.resourcemonitor.impl.NotificationService;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.resourcemonitor.impl.DataBroker;
import java.util.Collections;
import java.util.Map;


/**
 * Class that builds {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.ResourcemonitorImpl} instances.
 *
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.ResourcemonitorImpl
 *
 */
public class ResourcemonitorImplBuilder implements Builder <org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.ResourcemonitorImpl> {

    private DataBroker _dataBroker;
    private NotificationService _notificationService;
    private RpcRegistry _rpcRegistry;

    Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.ResourcemonitorImpl>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.ResourcemonitorImpl>> augmentation = Collections.emptyMap();

    public ResourcemonitorImplBuilder() {
    }

    public ResourcemonitorImplBuilder(ResourcemonitorImpl base) {
        this._dataBroker = base.getDataBroker();
        this._notificationService = base.getNotificationService();
        this._rpcRegistry = base.getRpcRegistry();
        if (base instanceof ResourcemonitorImplImpl) {
            ResourcemonitorImplImpl impl = (ResourcemonitorImplImpl) base;
            if (!impl.augmentation.isEmpty()) {
                this.augmentation = new HashMap<>(impl.augmentation);
            }
        } else if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.ResourcemonitorImpl> casted =(AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.ResourcemonitorImpl>) base;
            if (!casted.augmentations().isEmpty()) {
                this.augmentation = new HashMap<>(casted.augmentations());
            }
        }
    }


    public DataBroker getDataBroker() {
        return _dataBroker;
    }
    
    public NotificationService getNotificationService() {
        return _notificationService;
    }
    
    public RpcRegistry getRpcRegistry() {
        return _rpcRegistry;
    }
    
    @SuppressWarnings("unchecked")
    public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.ResourcemonitorImpl>> E getAugmentation(java.lang.Class<E> augmentationType) {
        if (augmentationType == null) {
            throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
        }
        return (E) augmentation.get(augmentationType);
    }

     
    public ResourcemonitorImplBuilder setDataBroker(final DataBroker value) {
        this._dataBroker = value;
        return this;
    }
    
     
    public ResourcemonitorImplBuilder setNotificationService(final NotificationService value) {
        this._notificationService = value;
        return this;
    }
    
     
    public ResourcemonitorImplBuilder setRpcRegistry(final RpcRegistry value) {
        this._rpcRegistry = value;
        return this;
    }
    
    public ResourcemonitorImplBuilder addAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.ResourcemonitorImpl>> augmentationType, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.ResourcemonitorImpl> augmentation) {
        if (augmentation == null) {
            return removeAugmentation(augmentationType);
        }
    
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }
    
    public ResourcemonitorImplBuilder removeAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.ResourcemonitorImpl>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    public ResourcemonitorImpl build() {
        return new ResourcemonitorImplImpl(this);
    }

    private static final class ResourcemonitorImplImpl implements ResourcemonitorImpl {

        public java.lang.Class<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.ResourcemonitorImpl> getImplementedInterface() {
            return org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.ResourcemonitorImpl.class;
        }

        private final DataBroker _dataBroker;
        private final NotificationService _notificationService;
        private final RpcRegistry _rpcRegistry;

        private Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.ResourcemonitorImpl>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.ResourcemonitorImpl>> augmentation = Collections.emptyMap();

        private ResourcemonitorImplImpl(ResourcemonitorImplBuilder base) {
            this._dataBroker = base.getDataBroker();
            this._notificationService = base.getNotificationService();
            this._rpcRegistry = base.getRpcRegistry();
            switch (base.augmentation.size()) {
            case 0:
                this.augmentation = Collections.emptyMap();
                break;
            case 1:
                final Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.ResourcemonitorImpl>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.ResourcemonitorImpl>> e = base.augmentation.entrySet().iterator().next();
                this.augmentation = Collections.<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.ResourcemonitorImpl>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.ResourcemonitorImpl>>singletonMap(e.getKey(), e.getValue());
                break;
            default :
                this.augmentation = new HashMap<>(base.augmentation);
            }
        }

        @Override
        public DataBroker getDataBroker() {
            return _dataBroker;
        }
        
        @Override
        public NotificationService getNotificationService() {
            return _notificationService;
        }
        
        @Override
        public RpcRegistry getRpcRegistry() {
            return _rpcRegistry;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.ResourcemonitorImpl>> E getAugmentation(java.lang.Class<E> augmentationType) {
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
            result = prime * result + Objects.hashCode(_dataBroker);
            result = prime * result + Objects.hashCode(_notificationService);
            result = prime * result + Objects.hashCode(_rpcRegistry);
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
            if (!org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.ResourcemonitorImpl.class.equals(((DataObject)obj).getImplementedInterface())) {
                return false;
            }
            org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.ResourcemonitorImpl other = (org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.ResourcemonitorImpl)obj;
            if (!Objects.equals(_dataBroker, other.getDataBroker())) {
                return false;
            }
            if (!Objects.equals(_notificationService, other.getNotificationService())) {
                return false;
            }
            if (!Objects.equals(_rpcRegistry, other.getRpcRegistry())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                ResourcemonitorImplImpl otherImpl = (ResourcemonitorImplImpl) obj;
                if (!Objects.equals(augmentation, otherImpl.augmentation)) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.ResourcemonitorImpl>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.impl.rev161017.modules.module.configuration.ResourcemonitorImpl>> e : augmentation.entrySet()) {
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
            java.lang.StringBuilder builder = new java.lang.StringBuilder ("ResourcemonitorImpl [");
            boolean first = true;
        
            if (_dataBroker != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_dataBroker=");
                builder.append(_dataBroker);
             }
            if (_notificationService != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_notificationService=");
                builder.append(_notificationService);
             }
            if (_rpcRegistry != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_rpcRegistry=");
                builder.append(_rpcRegistry);
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
