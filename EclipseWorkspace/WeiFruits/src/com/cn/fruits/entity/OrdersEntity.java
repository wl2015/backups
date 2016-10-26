package com.cn.fruits.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


/**
 * 订单信息表
 * */
@Entity
@Table(name="orders")
@DynamicInsert
@DynamicUpdate
public class OrdersEntity {
  @Id
  @GeneratedValue
  @Column(name="order_id")
  public int orderId;//1、orders的主键
  
  @Column(name="user_id")
  public int userId;//2、user表的ID
  
  @Column(name="receive_name")
  public String receiveName;//3、收货人姓名
  
  @Column(name="receive_phone")
  public String receivePhone;//4、收货人联系电话
  
  @Column(name="campus_name")
  public String campusName;//5、校区名称
  
  @Column(name="dormitory_name")
  public String dormitoryName;//6、宿舍楼名称
  
  @Column(name="address")
  public String address;//7、地址补充
  
  @Column(name="money")
  public Double money;//8、订单总额
  
  @Column(name="order_time")
  public String orderTime;//9、下单时间
  
  @Column(name="pay_way")
  public int payWay;//10、 0表示未支付，1表示微信支付，2表示支付宝支付
  
  @Column(name="receive_way")
  public int receiveWay;//11、 0表示送货上门，1表示自取
  
  @Column(name="pay_status")
  public int payStatus;//12、0表示未支付，1表示已支付
  
  @Column(name="order_status")
  public int orderStatus;//13、 0表示未完成，1表示接受订单或者开始配送，2表示订单已完成
  
  @Column(name="message")
  public String messge;//14、留言

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

  
}
