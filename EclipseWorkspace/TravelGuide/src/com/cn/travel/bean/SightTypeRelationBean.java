package com.cn.travel.bean;


public class SightTypeRelationBean {
  public int relationId;//关系表Id
  
  public int sightId;//景点Id
  
  public int typeId;//景点类型Id

  public int getRelationId() {
    return relationId;
  }

  public void setRelationId(int relationId) {
    this.relationId = relationId;
  }

  public int getSightId() {
    return sightId;
  }

  public void setSightId(int sightId) {
    this.sightId = sightId;
  }

  public int getTypeId() {
    return typeId;
  }

  public void setTypeId(int typeId) {
    this.typeId = typeId;
  }
  
}
