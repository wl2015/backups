package com.yc.bean;

/**
 * 菜品类型链接表
 * @author Administrator
 *
 */
public class TargetTypeLink {
    
    private int linkId;
    private int targetId;
    private int typeId;
    private int target;
    public int getLinkId() {
        return linkId;
    }
    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }
    public int getTargetId() {
        return targetId;
    }
    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }
    public int getTypeId() {
        return typeId;
    }
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
    public int getTarget() {
        return target;
    }
    public void setTarget(int target) {
        this.target = target;
    }
    
    
}
