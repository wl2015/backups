package com.yc.bean;

import java.util.List;


public class Order {
  private int oId;
  private int userId;
  private int merchantId;
  private String address;
  private double money;
  private String orderTime;
  private int spendTime;
  private int payStatus;
  private int userStatus;
  private int refund;
  private double oLng;
  private double oLat;
  private int uEvaluate;
  private int delete_u;
  private int delete_m;
  private double distance;
  private User user;
  private Merchant merchant;
  private List<DishList> dishlist;
  public List<DishList> getDishlist() {
    return dishlist;
  }
  public void setDishlist(List<DishList> dishlist) {
    this.dishlist = dishlist;
  }
  public int getoId() {
    return oId;
  }
  public void setoId(int oId) {
    this.oId = oId;
  }
  public int getUserId() {
    return userId;
  }
  public void setUserId(int userId) {
    this.userId = userId;
  }
  public int getMerchantId() {
    return merchantId;
  }
  public void setMerchantId(int merchantId) {
    this.merchantId = merchantId;
  }
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public double getMoney() {
    return money;
  }
  public void setMoney(double money) {
    this.money = money;
  }
  public String getOrderTime() {
    return orderTime;
  }
  public void setOrderTime(String orderTime) {
    this.orderTime = orderTime;
  }
  public int getSpendTime() {
    return spendTime;
  }
  public void setSpendTime(int spendTime) {
    this.spendTime = spendTime;
  }
  public int getPayStatus() {
    return payStatus;
  }
  public void setPayStatus(int payStatus) {
    this.payStatus = payStatus;
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
  public double getoLng() {
    return oLng;
  }
  public void setoLng(double oLng) {
    this.oLng = oLng;
  }
  public double getoLat() {
    return oLat;
  }
  public void setoLat(double oLat) {
    this.oLat = oLat;
  }
  public int getuEvaluate() {
    return uEvaluate;
  }
  public void setuEvaluate(int uEvaluate) {
    this.uEvaluate = uEvaluate;
  }
  public int getDelete_u() {
    return delete_u;
  }
  public void setDelete_u(int delete_u) {
    this.delete_u = delete_u;
  }
  public int getDelete_m() {
    return delete_m;
  }
  public void setDelete_m(int delete_m) {
    this.delete_m = delete_m;
  }
  public double getDistance() {
    return distance;
  }
  public void setDistance(double distance) {
    this.distance = distance;
  }
  public User getUser() {
    return user;
  }
  public void setUser(User user) {
    this.user = user;
  }
  public Merchant getMerchant() {
    return merchant;
  }
  public void setMerchant(Merchant merchant) {
    this.merchant = merchant;
  }
}
