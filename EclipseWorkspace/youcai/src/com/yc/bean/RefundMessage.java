package com.yc.bean;

import java.util.List;

public class RefundMessage {
private int refundId;//退单消息id

public int getRefundId() {
  return refundId;
}
public void setRefundId(int refundId) {
  this.refundId = refundId;
}
public int getOrderId() {
  return orderId;
}
public void setOrderId(int orderId) {
  this.orderId = orderId;
}
public char getReadFlag() {
  return readFlag;
}
public void setReadFlag(char readFlag) {
  this.readFlag = readFlag;
}

public double getTotalmoney() {
  return totalmoney;
}
public void setTotalmoney(double totalmoney) {
  this.totalmoney = totalmoney;
}
public String getUserName() {
  return userName;
}
public void setUserName(String userName) {
  this.userName = userName;
}
public String getUserPhone() {
  return userPhone;
}
public void setUserPhone(String userPhone) {
  this.userPhone = userPhone;
}
public List<DishList> getDishlist() {
  return dishlist;
}
public void setDishlist(List<DishList> dishlist) {
  this.dishlist = dishlist;
}
public float getPenalty() {
  return penalty;
}
public void setPenalty(float penalty) {
  this.penalty = penalty;
}
private int orderId;//订单号
private char readFlag;//读取状态
private double totalmoney;//总额
private String userName;//用户名
private String userPhone; //用户联系电话
private List<DishList> dishlist;//DishList里只需要有dishName，dishNumber两个属性
private float  penalty;//违约金 
}
