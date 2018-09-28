package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged;
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
 * Class that builds {@link org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Qos} instances.
 *
 * @see org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Qos
 *
 */
public class QosBuilder implements Builder <org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Qos> {

    private BigDecimal _bandwidth;
    private BigDecimal _jitter;
    private java.lang.Integer _numQueues;
    private BigDecimal _packetDelay;
    private BigDecimal _packetLoss;
    private java.lang.Long _queueSizes;

    Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Qos>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Qos>> augmentation = Collections.emptyMap();

    public QosBuilder() {
    }
    public QosBuilder(org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkQos arg) {
        this._bandwidth = arg.getBandwidth();
        this._packetLoss = arg.getPacketLoss();
        this._packetDelay = arg.getPacketDelay();
        this._jitter = arg.getJitter();
        this._numQueues = arg.getNumQueues();
        this._queueSizes = arg.getQueueSizes();
    }

    public QosBuilder(Qos base) {
        this._bandwidth = base.getBandwidth();
        this._jitter = base.getJitter();
        this._numQueues = base.getNumQueues();
        this._packetDelay = base.getPacketDelay();
        this._packetLoss = base.getPacketLoss();
        this._queueSizes = base.getQueueSizes();
        if (base instanceof QosImpl) {
            QosImpl impl = (QosImpl) base;
            if (!impl.augmentation.isEmpty()) {
                this.augmentation = new HashMap<>(impl.augmentation);
            }
        } else if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Qos> casted =(AugmentationHolder<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Qos>) base;
            if (!casted.augmentations().isEmpty()) {
                this.augmentation = new HashMap<>(casted.augmentations());
            }
        }
    }

    /**
     *Set fields from given grouping argument. Valid argument is instance of one of following types:
     * <ul>
     * <li>org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkQos</li>
     * </ul>
     *
     * @param arg grouping object
     * @throws IllegalArgumentException if given argument is none of valid types
    */
    public void fieldsFrom(DataObject arg) {
        boolean isValidArg = false;
        if (arg instanceof org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkQos) {
            this._bandwidth = ((org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkQos)arg).getBandwidth();
            this._packetLoss = ((org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkQos)arg).getPacketLoss();
            this._packetDelay = ((org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkQos)arg).getPacketDelay();
            this._jitter = ((org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkQos)arg).getJitter();
            this._numQueues = ((org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkQos)arg).getNumQueues();
            this._queueSizes = ((org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkQos)arg).getQueueSizes();
            isValidArg = true;
        }
        if (!isValidArg) {
            throw new IllegalArgumentException(
              "expected one of: [org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.LinkQos] \n" +
              "but was: " + arg
            );
        }
    }

    public BigDecimal getBandwidth() {
        return _bandwidth;
    }
    
    public BigDecimal getJitter() {
        return _jitter;
    }
    
    public java.lang.Integer getNumQueues() {
        return _numQueues;
    }
    
    public BigDecimal getPacketDelay() {
        return _packetDelay;
    }
    
    public BigDecimal getPacketLoss() {
        return _packetLoss;
    }
    
    public java.lang.Long getQueueSizes() {
        return _queueSizes;
    }
    
    @SuppressWarnings("unchecked")
    public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Qos>> E getAugmentation(java.lang.Class<E> augmentationType) {
        if (augmentationType == null) {
            throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
        }
        return (E) augmentation.get(augmentationType);
    }

     
    public QosBuilder setBandwidth(final BigDecimal value) {
        this._bandwidth = value;
        return this;
    }
    
     
    public QosBuilder setJitter(final BigDecimal value) {
        this._jitter = value;
        return this;
    }
    
     
    public QosBuilder setNumQueues(final java.lang.Integer value) {
        this._numQueues = value;
        return this;
    }
    
     
    public QosBuilder setPacketDelay(final BigDecimal value) {
        this._packetDelay = value;
        return this;
    }
    
     
    public QosBuilder setPacketLoss(final BigDecimal value) {
        this._packetLoss = value;
        return this;
    }
    
     
    public QosBuilder setQueueSizes(final java.lang.Long value) {
        this._queueSizes = value;
        return this;
    }
    
    public QosBuilder addAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Qos>> augmentationType, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Qos> augmentation) {
        if (augmentation == null) {
            return removeAugmentation(augmentationType);
        }
    
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }
    
    public QosBuilder removeAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Qos>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    public Qos build() {
        return new QosImpl(this);
    }

    private static final class QosImpl implements Qos {

        public java.lang.Class<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Qos> getImplementedInterface() {
            return org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Qos.class;
        }

        private final BigDecimal _bandwidth;
        private final BigDecimal _jitter;
        private final java.lang.Integer _numQueues;
        private final BigDecimal _packetDelay;
        private final BigDecimal _packetLoss;
        private final java.lang.Long _queueSizes;

        private Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Qos>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Qos>> augmentation = Collections.emptyMap();

        private QosImpl(QosBuilder base) {
            this._bandwidth = base.getBandwidth();
            this._jitter = base.getJitter();
            this._numQueues = base.getNumQueues();
            this._packetDelay = base.getPacketDelay();
            this._packetLoss = base.getPacketLoss();
            this._queueSizes = base.getQueueSizes();
            switch (base.augmentation.size()) {
            case 0:
                this.augmentation = Collections.emptyMap();
                break;
            case 1:
                final Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Qos>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Qos>> e = base.augmentation.entrySet().iterator().next();
                this.augmentation = Collections.<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Qos>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Qos>>singletonMap(e.getKey(), e.getValue());
                break;
            default :
                this.augmentation = new HashMap<>(base.augmentation);
            }
        }

        @Override
        public BigDecimal getBandwidth() {
            return _bandwidth;
        }
        
        @Override
        public BigDecimal getJitter() {
            return _jitter;
        }
        
        @Override
        public java.lang.Integer getNumQueues() {
            return _numQueues;
        }
        
        @Override
        public BigDecimal getPacketDelay() {
            return _packetDelay;
        }
        
        @Override
        public BigDecimal getPacketLoss() {
            return _packetLoss;
        }
        
        @Override
        public java.lang.Long getQueueSizes() {
            return _queueSizes;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Qos>> E getAugmentation(java.lang.Class<E> augmentationType) {
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
            result = prime * result + Objects.hashCode(_bandwidth);
            result = prime * result + Objects.hashCode(_jitter);
            result = prime * result + Objects.hashCode(_numQueues);
            result = prime * result + Objects.hashCode(_packetDelay);
            result = prime * result + Objects.hashCode(_packetLoss);
            result = prime * result + Objects.hashCode(_queueSizes);
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
            if (!org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Qos.class.equals(((DataObject)obj).getImplementedInterface())) {
                return false;
            }
            org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Qos other = (org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Qos)obj;
            if (!Objects.equals(_bandwidth, other.getBandwidth())) {
                return false;
            }
            if (!Objects.equals(_jitter, other.getJitter())) {
                return false;
            }
            if (!Objects.equals(_numQueues, other.getNumQueues())) {
                return false;
            }
            if (!Objects.equals(_packetDelay, other.getPacketDelay())) {
                return false;
            }
            if (!Objects.equals(_packetLoss, other.getPacketLoss())) {
                return false;
            }
            if (!Objects.equals(_queueSizes, other.getQueueSizes())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                QosImpl otherImpl = (QosImpl) obj;
                if (!Objects.equals(augmentation, otherImpl.augmentation)) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Qos>>, Augmentation<org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017.linkchanged.Qos>> e : augmentation.entrySet()) {
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
            java.lang.StringBuilder builder = new java.lang.StringBuilder ("Qos [");
            boolean first = true;
        
            if (_bandwidth != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_bandwidth=");
                builder.append(_bandwidth);
             }
            if (_jitter != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_jitter=");
                builder.append(_jitter);
             }
            if (_numQueues != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_numQueues=");
                builder.append(_numQueues);
             }
            if (_packetDelay != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_packetDelay=");
                builder.append(_packetDelay);
             }
            if (_packetLoss != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_packetLoss=");
                builder.append(_packetLoss);
             }
            if (_queueSizes != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_queueSizes=");
                builder.append(_queueSizes);
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
