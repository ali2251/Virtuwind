package eu.virtuwind.resourcemanager.impl;

import java.math.BigInteger;

/**
 * Created by Ali on 20/05/2017.
 */
public class EmbeddingFlowRequest {

    private String node_id;

    private BigInteger flowCookie;

    private Long meterId;

    private Long burst;

    private Long bandwidth;


    public EmbeddingFlowRequest(String node_id, BigInteger flowCookie, Long meterId, Long burst, Long bandwidth) {
        this.node_id = node_id;
        this.flowCookie = flowCookie;
        this.meterId = meterId;
        this.burst = burst;
        this.bandwidth = bandwidth;
    }

    public EmbeddingFlowRequest(String node_id, BigInteger flowCookie) {
        this.node_id = node_id;
        this.flowCookie = flowCookie;
    }

    public BigInteger getFlowCookie() {
        return flowCookie;
    }

    public String getNode_id() {
        return node_id;
    }

    public Long getMeterId() {
        return meterId;
    }

    public Long getBurst() {
        return burst;
    }

    public Long getBandwidth() {
        return bandwidth;
    }
}
