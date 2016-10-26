package com.cn.travel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="sight_type_relation")
@DynamicInsert
@DynamicUpdate
public class SightTypeRelationEntity {
  @Id
  @GeneratedValue
  @Column(name="relation_id")
  public int relationId;//关系表Id
  
  @Column(name="sight_id")
  public int sightId;//景点Id
  
  @Column(name="type_id")
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
