package com.cn.fruits.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 校区
 * */
@Entity
@Table(name="campus")
@DynamicInsert
@DynamicUpdate
public class CampusEntity {
  @Id
  @GeneratedValue
  @Column(name="campus_id")
  public int campusId;//1、campus的ID
  
  @Column(name="campus_name")
  public String campusName;//2、校区名称

  public int getCampusId() {
    return campusId;
  }

  public void setCampusId(int campusId) {
    this.campusId = campusId;
  }

  public String getCampusName() {
    return campusName;
  }

  public void setCampusName(String campusName) {
    this.campusName = campusName;
  }
  
}
