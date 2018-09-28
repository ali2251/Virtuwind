package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.resourcemanager.impl.RpcRegistry;
import org.opendaylight.yangtools.yang.binding.DataObject;
import java.util.HashMap;
import org.opendaylight.yangtools.concepts.Builder;
import java.util.Objects;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.resourcemanager.impl.NotificationService;
import org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.resourcemanager.impl.DataBroker;
import java.util.Collections;
import java.util.Map;


/**
 * Class that builds {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.ResourcemanagerImpl} instances.
 *
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.ResourcemanagerImpl
 *
 */
public class ResourcemanagerImplBuilder implements Builder <org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.ResourcemanagerImpl> {

    private DataBroker _dataBroker;
    private NotificationService _notificationService;
    private RpcRegistry _rpcRegistry;

    Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.ResourcemanagerImpl>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.ResourcemanagerImpl>> augmentation = Collections.emptyMap();

    public ResourcemanagerImplBuilder() {
    }

    public ResourcemanagerImplBuilder(ResourcemanagerImpl base) {
        this._dataBroker = base.getDataBroker();
        this._notificationService = base.getNotificationService();
        this._rpcRegistry = base.getRpcRegistry();
        if (base instanceof ResourcemanagerImplImpl) {
            ResourcemanagerImplImpl impl = (ResourcemanagerImplImpl) base;
            if (!impl.augmentation.isEmpty()) {
                this.augmentation = new HashMap<>(impl.augmentation);
            }
        } else if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.ResourcemanagerImpl> casted =(AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.ResourcemanagerImpl>) base;
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
    public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.ResourcemanagerImpl>> E getAugmentation(java.lang.Class<E> augmentationType) {
        if (augmentationType == null) {
            throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
        }
        return (E) augmentation.get(augmentationType);
    }

     
    public ResourcemanagerImplBuilder setDataBroker(final DataBroker value) {
        this._dataBroker = value;
        return this;
    }
    
     
    public ResourcemanagerImplBuilder setNotificationService(final NotificationService value) {
        this._notificationService = value;
        return this;
    }
    
     
    public ResourcemanagerImplBuilder setRpcRegistry(final RpcRegistry value) {
        this._rpcRegistry = value;
        return this;
    }
    
    public ResourcemanagerImplBuilder addAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.ResourcemanagerImpl>> augmentationType, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.ResourcemanagerImpl> augmentation) {
        if (augmentation == null) {
            return removeAugmentation(augmentationType);
        }
    
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }
    
    public ResourcemanagerImplBuilder removeAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.ResourcemanagerImpl>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    public ResourcemanagerImpl build() {
        return new ResourcemanagerImplImpl(this);
    }

    private static final class ResourcemanagerImplImpl implements ResourcemanagerImpl {

        public java.lang.Class<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.ResourcemanagerImpl> getImplementedInterface() {
            return org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.ResourcemanagerImpl.class;
        }

        private final DataBroker _dataBroker;
        private final NotificationService _notificationService;
        private final RpcRegistry _rpcRegistry;

        private Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.ResourcemanagerImpl>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.ResourcemanagerImpl>> augmentation = Collections.emptyMap();

        private ResourcemanagerImplImpl(ResourcemanagerImplBuilder base) {
            this._dataBroker = base.getDataBroker();
            this._notificationService = base.getNotificationService();
            this._rpcRegistry = base.getRpcRegistry();
            switch (base.augmentation.size()) {
            case 0:
                this.augmentation = Collections.emptyMap();
                break;
            case 1:
                final Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.ResourcemanagerImpl>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.ResourcemanagerImpl>> e = base.augmentation.entrySet().iterator().next();
                this.augmentation = Collections.<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.ResourcemanagerImpl>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.ResourcemanagerImpl>>singletonMap(e.getKey(), e.getValue());
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
        public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.ResourcemanagerImpl>> E getAugmentation(java.lang.Class<E> augmentationType) {
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
            if (!org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.ResourcemanagerImpl.class.equals(((DataObject)obj).getImplementedInterface())) {
                return false;
            }
            org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.ResourcemanagerImpl other = (org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.ResourcemanagerImpl)obj;
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
                ResourcemanagerImplImpl otherImpl = (ResourcemanagerImplImpl) obj;
                if (!Objects.equals(augmentation, otherImpl.augmentation)) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.ResourcemanagerImpl>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.impl.rev161017.modules.module.configuration.ResourcemanagerImpl>> e : augmentation.entrySet()) {
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
            java.lang.StringBuilder builder = new java.lang.StringBuilder ("ResourcemanagerImpl [");
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
