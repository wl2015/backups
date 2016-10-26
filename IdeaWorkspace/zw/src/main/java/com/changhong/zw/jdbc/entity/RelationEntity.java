package com.changhong.zw.jdbc.entity;

/**
 * Created by admin on 16-7-27.
 */
public class RelationEntity {
    Long relationId;//id
    String name;//节点名称
    int left;//左坐标
    int right;//右坐标

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }
}
