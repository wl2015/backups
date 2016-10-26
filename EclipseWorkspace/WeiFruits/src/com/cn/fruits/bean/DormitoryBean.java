package com.cn.fruits.bean;

/**
 * 宿舍楼
 * */
public class DormitoryBean {
  public int dormitoryId;//1、dormitory的ID
  
  public String dormitoryName;//2、宿舍楼名称
  
  public int campusId;//3、campus表的ID
  
  public String campusName;//4、campus表的campusName

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

  public String getCampusName() {
    return campusName;
  }

  public void setCampusName(String campusName) {
    this.campusName = campusName;
  }
}
