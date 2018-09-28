package eu.virtuwind.resourcemonitor.impl;

import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.Link;

import java.util.List;

/**
 * Created by Ali on 15/05/2017.
 */
public class LinkStats {



    private static List<ResMonitorLink> linksWithStats;

    public LinkStats(Link link, Long linkSpeed) {
// TODO: implement other qos except for bandwidth-linkSpeed!!! Following is just a TEMPORARY workaround
        //linksWithStats.add( new ResMonitorLink(linkSpeed,-1L,-1L,-1L, link));

    }

    public static List<ResMonitorLink> getLinksWithStats() {
        return linksWithStats;
    }
}
