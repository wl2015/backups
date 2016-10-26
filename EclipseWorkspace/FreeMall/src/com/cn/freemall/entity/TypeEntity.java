package com.cn.freemall.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


/***
 * 景点的标签
 * */
@Entity
@Table(name="type")
@DynamicInsert
@DynamicUpdate
public class TypeEntity {
  @Id
  @GeneratedValue
  @Column(name="type_id")
  public int typeId;//景点类型Id
  
  @Column(name="type_name")
  public String typeName;//类型名

  public int getTypeId() {
    return typeId;
  }

  public void setTypeId(int typeId) {
    this.typeId = typeId;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }
}
