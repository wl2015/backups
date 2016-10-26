package com.cn.freemall.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="relation")
@DynamicInsert
@DynamicUpdate
public class RelationEntity {
  @Id
  @GeneratedValue
  @Column(name="relation_id")
  public int relationId;//1、关系ID
  
  @Column(name="product_id")
  public int productId;//2、产品ID
  
  @Column(name="type_id")
  public int typeId;//3、类型ID

  public int getRelationId() {
    return relationId;
  }

  public void setRelationId(int relationId) {
    this.relationId = relationId;
  }

  public int getProductId() {
    return productId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public int getTypeId() {
    return typeId;
  }

  public void setTypeId(int typeId) {
    this.typeId = typeId;
  }

}
