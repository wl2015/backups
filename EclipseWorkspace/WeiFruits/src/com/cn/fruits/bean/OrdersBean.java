package com.cn.fruits.bean;

import java.util.List;

/**
 * 订单信息表
 * */
public class OrdersBean {
  public int orderId;//1、orders的主键
  
  public int userId;//2、login_user表的ID
  
  public String receiveName;//3、收货人姓名
  
  public String receivePhone;//4、收货人联系电话
  
  public String campusName;//5、校区名称
  
  public String dormitoryName;//6、宿舍楼名称
  
  public String address;//7、地址补充
  
  public Double money;//8、订单总额
  
  public String orderTime;//9、下单时间
  
  public int payWay;//10、 0表示未支付，1表示微信支付，2表示支付宝支付
  
  public int receiveWay;//11、 0表示送货上门，1表示自取
  
  public int payStatus;//12、0表示未支付，1表示已支付
  
  public int orderStatus;//13、 0表示未完成，1表示接受订单或者开始配送，2表示订单已完成
  
  public String messge;//14、留言
  
  public List<FruitBean> fruitBeans;

  public int getOrderId() {
    return orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getReceiveName() {
    return receiveName;
  }

  public void setReceiveName(String receiveName) {
    this.receiveName = receiveName;
  }

  public String getReceivePhone() {
    return receivePhone;
  }

  public void setReceivePhone(String receivePhone) {
    this.receivePhone = receivePhone;
  }

  public String getCampusName() {
    return campusName;
  }

  public void setCampusName(String campusName) {
    this.campusName = campusName;
  }

  public String getDormitoryName() {
    return dormitoryName;
  }

  public void setDormitoryName(String dormitoryName) {
    this.dormitoryName = dormitoryName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Double getMoney() {
    return money;
  }

  public void setMoney(Double money) {
    this.money = money;
  }

  public String getOrderTime() {
    return orderTime;
  }

  public void setOrderTime(String orderTime) {
    this.orderTime = orderTime;
  }

  public int getPayWay() {
    return payWay;
  }

  public void setPayWay(int payWay) {
    this.payWay = payWay;
  }

  public int getReceiveWay() {
    return receiveWay;
  }

  public void setReceiveWay(int receiveWay) {
    this.receiveWay = receiveWay;
  }

  public int getPayStatus() {
    return payStatus;
  }

  public void setPayStatus(int payStatus) {
    this.payStatus = payStatus;
  }

  public int getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(int orderStatus) {
    this.orderStatus = orderStatus;
  }

  public String getMessge() {
    return messge;
  }

  public void setMessge(String messge) {
    this.messge = messge;
  }

  public List<FruitBean> getFruitBeans() {
    return fruitBeans;
  }

  public void setFruitBeans(List<FruitBean> fruitBeans) {
    this.fruitBeans = fruitBeans;
  }

}
