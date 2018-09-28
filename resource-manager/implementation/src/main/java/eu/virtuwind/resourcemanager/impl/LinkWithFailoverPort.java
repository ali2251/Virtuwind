package eu.virtuwind.resourcemanager.impl;

import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.Link;
/**
 * Created by Ali on 19/08/2017.
 */
public class LinkWithFailoverPort {

    private Link link;
    private String linkId;
    private Integer failoverPort;

    public LinkWithFailoverPort(Link link, String linkId, Integer failoverPort) {
        this.link = link;
        this.linkId = linkId;
        this.failoverPort = failoverPort;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public Integer getFailoverPort() {
        return failoverPort;
    }

    public void setFailoverPort(Integer failoverPort) {
        this.failoverPort = failoverPort;
    }

    @Override
    public String toString() {
        return "LinkWithFailoverPort{" +
                "link=" + link +
                ", linkId='" + linkId + '\'' +
                ", failoverPort=" + failoverPort +
                '}';
    }
}
