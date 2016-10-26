package com.yc.bean;

import java.util.Date;

public class Advance {
  
 // 预付款记录id
 private int advanceId;
  //对应的订单id
  private int orderId;
  //订单金额
  private double money;
  public int getOrderId() {
    return orderId;
  }
  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }
  
  
  public double getMoney() {
    return money;
  }
  public void setMoney(double money) {
    this.money = money;
  }


  //生成时间
  private String advanceTime;
  public int getAdvanceId() {
    return advanceId;
  }
  public void setAdvanceId(int advanceId) {
    this.advanceId = advanceId;
  }
 
  public String getAdvanceTime() {
    return advanceTime;
  }
  public void setAdvanceTime(String advanceTime) {
    this.advanceTime = advanceTime;
  }
  public int getAdvanceStatus() {
    return advanceStatus;
  }
  public void setAdvanceStatus(int advanceStatus) {
    this.advanceStatus = advanceStatus;
  }
  public int getUserStatus() {
    return userStatus;
  }
  public void setUserStatus(int userStatus) {
    this.userStatus = userStatus;
  }
  public int getRefund() {
    return refund;
  }
  public void setRefund(int refund) {
    this.refund = refund;
  }
  // 预付款记录状态
  private int advanceStatus;
  //用户收货状态
  private int userStatus;
  // 用户退款状态
  private int refund;
}
