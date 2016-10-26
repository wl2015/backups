package com.yc.bean;

import java.util.List;

public class PushOrder {
  private int goId;
  private int oId;
  private int mId;
  private int mSure;
  private int uSure;
  private int spendTime;
  private int range;
  private List<DishList> dishlist;
  private User user;
 
  public PushOrder(){
  }

  public PushOrder(int oId,int mId,int range){
    this.oId = oId;
    this.mId = mId;
    this.range=range;
  }

  public int getGoId() {
    return goId;
  }

  public void setGoId(int goId) {
    this.goId = goId;
  }

  public int getoId() {
    return oId;
  }

  public void setoId(int oId) {
    this.oId = oId;
  }

  public int getmId() {
    return mId;
  }

  public void setmId(int mId) {
    this.mId = mId;
  }

  public int getmSure() {
    return mSure;
  }

  public void setmSure(int mSure) {
    this.mSure = mSure;
  }

  public int getuSure() {
    return uSure;
  }

  public void setuSure(int uSure) {
    this.uSure = uSure;
  }

  public int getSpendTime() {
    return spendTime;
  }

  public void setSpendTime(int spendTime) {
    this.spendTime = spendTime;
  }

  public int getRange() {
    return range;
  }

  public void setRange(int range) {
    this.range = range;
  }

  public List<DishList> getDishlist() {
    return dishlist;
  }

  public void setDishlist(List<DishList> dishlist) {
    this.dishlist = dishlist;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
