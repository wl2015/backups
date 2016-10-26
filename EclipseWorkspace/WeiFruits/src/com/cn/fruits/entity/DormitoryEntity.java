package com.cn.fruits.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 宿舍楼
 * */
@Entity
@Table(name="dormitory")
@DynamicInsert
@DynamicUpdate
public class DormitoryEntity {
  @Id
  @GeneratedValue
  @Column(name="dormitory_id")
  public int dormitoryId;//1、dormitory的ID
  
  @Column(name="dormitory_name")
  public String dormitoryName;//2、宿舍楼名称
  
  @Column(name="campus_id")
  public int campusId;//3、campus表的ID

  public int getDormitoryId() {
    return dormitoryId;
  }

  public void setDormitoryId(int dormitoryId) {
    this.dormitoryId = dormitoryId;
  }

  public String getDormitoryName() {
    return dormitoryName;
  }

  public void setDormitoryName(String dormitoryName) {
    this.dormitoryName = dormitoryName;
  }

  public int getCampusId() {
    return campusId;
  }

  public void setCampusId(int campusId) {
    this.campusId = campusId;
  }
  
}
