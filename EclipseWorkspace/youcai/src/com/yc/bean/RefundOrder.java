package com.yc.bean;

import java.util.List;


public class RefundOrder {
  private int refundOrderId;
  private int orderId;
  private String userName;
  private String shopName;
  private String address;
  private String orderTime;
  private int refund;
  private String time;
  private String content;

  private List<DishList> dishlist;
  
  public List<DishList> getDishlist() {
    return dishlist;
  }
  public void setDishlist(List<DishList> dishlist) {
    this.dishlist = dishlist;
  }
public int getRefundOrderId() {
    return refundOrderId;
}
public void setRefundOrderId(int refundOrderId) {
    this.refundOrderId = refundOrderId;
}
public int getOrderId() {
    return orderId;
}
public void setOrderId(int orderId) {
    this.orderId = orderId;
}
public String getUserName() {
    return userName;
}
public void setUserName(String userName) {
    this.userName = userName;
}

public String getOrderTime() {
    return orderTime;
}
public void setOrderTime(String orderTime) {
    this.orderTime = orderTime;
}
public String getShopName() {
    return shopName;
}
public void setShopName(String shopName) {
    this.shopName = shopName;
}
public String getAddress() {
    return address;
}
public void setAddress(String address) {
    this.address = address;
}
public int getRefund() {
    return refund;
}
public void setRefund(int refund) {
    this.refund = refund;
}
public String getTime() {
    return time;
}
public void setTime(String time) {
    this.time = time;
}
public String getContent() {
    return content;
}
public void setContent(String content) {
    this.content = content;
}
 
  
  
}
