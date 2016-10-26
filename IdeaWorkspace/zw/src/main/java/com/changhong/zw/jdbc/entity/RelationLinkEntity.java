package com.changhong.zw.jdbc.entity;

/**
 * Created by admin on 16-7-27.
 */
public class RelationLinkEntity {
    Long linkId;//id
    String source;
    String target;

    public Long getLinkId() {
        return linkId;
    }

    public void setLinkId(Long linkId) {
        this.linkId = linkId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
