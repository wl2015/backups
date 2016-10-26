package com.yc.bean;
/**
 * 商家库存
 * */
public class Inventory {
  //库存id
  private int iId;
  //商家id
  private int mId;
  private int dishId;
  //库存数量
  private int iNum;
  //可增加库存数量
  private int limitCount;
  //修改时间
  private String iTime;
  
  private String dishName;
  
  public int getiId() {
    return iId;
  }
  public void setiId(int iId) {
    this.iId = iId;
  }
  public int getmId() {
    return mId;
  }
  public void setmId(int mId) {
    this.mId = mId;
  }
  
  
  public int getDishId() {
    return dishId;
}
public void setDishId(int dishId) {
    this.dishId = dishId;
}
public String getDishName() {
    return dishName;
  }
  public void setDishName(String dishName) {
    this.dishName = dishName;
  }
  public int getiNum() {
    return iNum;
  }
  public void setiNum(int iNum) {
    this.iNum = iNum;
  }
  public int getLimitCount() {
    return limitCount;
  }
  public void setLimitCount(int limitCount) {
    this.limitCount = limitCount;
  }
  public String getiTime() {
    return iTime;
  }
  public void setiTime(String iTime) {
    this.iTime = iTime;
  }
}
