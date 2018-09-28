package eu.virtuwind.resourcemonitor.impl;

import java.math.BigInteger;

/**
 * Created by Ali on 08/02/2018.
 */
public class NumberOfPackets {

    private BigInteger packetsTransmitted;
    private BigInteger packetsReceived;

    public NumberOfPackets(BigInteger packetsTransmitted, BigInteger packetsReceived) {
        this.packetsTransmitted = packetsTransmitted;
        this.packetsReceived = packetsReceived;
    }


    public BigInteger getPacketsTransmitted() {
        return packetsTransmitted;
    }

    public BigInteger getPacketsReceived() {
        return packetsReceived;
    }

    @Override
    public String toString() {
        return "NumberOfPackets{" +
                "packetsTransmitted=" + packetsTransmitted +
                ", packetsReceived=" + packetsReceived +
                '}';
    }
}
