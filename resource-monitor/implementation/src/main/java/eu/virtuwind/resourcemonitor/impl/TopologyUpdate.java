package eu.virtuwind.resourcemonitor.impl;

import java.util.List;

/**
 * Created by Ali on 16/05/2017.
 */
public class TopologyUpdate {

    List<ResMonitorLink> updatedLinks;
    List<ResMonitorLink> removedLinks;
    List<ResMonitorLink> addedLinks;

    public TopologyUpdate(List<ResMonitorLink> updatedLinks, List<ResMonitorLink> removedLinks, List<ResMonitorLink> addedLinks) {
        this.updatedLinks = updatedLinks;
        this.removedLinks = removedLinks;
        this.addedLinks = addedLinks;
    }

    public List<ResMonitorLink> getUpdatedLinks() {
        return updatedLinks;
    }

    public void setUpdatedLinks(List<ResMonitorLink> updatedLinks) {
        this.updatedLinks = updatedLinks;
    }

    public List<ResMonitorLink> getRemovedLinks() {
        return removedLinks;
    }

    public void setRemovedLinks(List<ResMonitorLink> removedLinks) {
        this.removedLinks = removedLinks;
    }

    public List<ResMonitorLink> getAddedLinks() {
        return addedLinks;
    }

    public void setAddedLinks(List<ResMonitorLink> addedLinks) {
        this.addedLinks = addedLinks;
    }
}
