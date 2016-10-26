package com.yc.bean;

import java.util.List;

/**
 * 商家表
 * */
public class Merchant {
//商家id
private Integer merchantId;
//手机号
private String merchantPhone;
//密码
private String merchantPassword;
//店名
private String shopName;
//负责人姓名
private String bossName;
//负责人身份证
private String idCard;
//联系方式
private String linkPhone;
//银行卡号
private String bankCard;
//商家地址
private String merchantAddress;
//商店简介
private String merchantIntro;
//审核状态  0未审核，1通过审核，2驳回审核
private Integer merchantStatus;
//星级
private float merchantStar;
//评语
private String merchantComment;
//绑定邮箱
private String merchantMail;
//注册时间
private String registerTime;
//上一次登录时间
private String lastLoginTime;
//上一次登录ip
private String lastLoginIp;
//商家地址横坐标
private double merchantLng;
//商家地址纵坐标
private double merchantLat;

//商家图片
private String merchantLogo;

private PushOrder pushOrder;

private List<DishList> dishlist;


public Integer getMerchantId() {
  return merchantId;
}
public void setMerchantId(Integer merchantId) {
  this.merchantId = merchantId;
}
public String getMerchantPhone() {
  return merchantPhone;
}
public void setMerchantPhone(String merchantPhone) {
  this.merchantPhone = merchantPhone;
}
public String getMerchantPassword() {
  return merchantPassword;
}
public void setMerchantPassword(String merchantPassword) {
  this.merchantPassword = merchantPassword;
}
public String getShopName() {
  return shopName;
}
public void setShopName(String shopName) {
  this.shopName = shopName;
}
public String getBossName() {
  return bossName;
}
public void setBossName(String bossName) {
  this.bossName = bossName;
}
public String getIdCard() {
  return idCard;
}
public void setIdCard(String idCard) {
  this.idCard = idCard;
}
public String getLinkPhone() {
  return linkPhone;
}
public void setLinkPhone(String linkPhone) {
  this.linkPhone = linkPhone;
}
public String getBankCard() {
  return bankCard;
}
public void setBankCard(String bankCard) {
  this.bankCard = bankCard;
}
public String getMerchantAddress() {
  return merchantAddress;
}
public void setMerchantAddress(String merchantAddress) {
  this.merchantAddress = merchantAddress;
}
public String getMerchantIntro() {
  return merchantIntro;
}
public void setMerchantIntro(String merchantIntro) {
  this.merchantIntro = merchantIntro;
}
public Integer getMerchantStatus() {
  return merchantStatus;
}
public void setMerchantStatus(Integer merchantStatus) {
  this.merchantStatus = merchantStatus;
}
public float getMerchantStar() {
  return merchantStar;
}
public void setMerchantStar(float merchantStar) {
  this.merchantStar = merchantStar;
}
public String getMerchantComment() {
  return merchantComment;
}
public void setMerchantComment(String merchantComment) {
  this.merchantComment = merchantComment;
}
public String getMerchantMail() {
  return merchantMail;
}
public void setMerchantMail(String merchantMail) {
  this.merchantMail = merchantMail;
}
public String getRegisterTime() {
  return registerTime;
}
public void setRegisterTime(String registerTime) {
  this.registerTime = registerTime;
}
public String getLastLoginTime() {
  return lastLoginTime;
}
public void setLastLoginTime(String lastLoginTime) {
  this.lastLoginTime = lastLoginTime;
}
public String getLastLoginIp() {
  return lastLoginIp;
}
public void setLastLoginIp(String lastLoginIp) {
  this.lastLoginIp = lastLoginIp;
}

public double getMerchantLng() {
  return merchantLng;
}
public void setMerchantLng(double merchantLng) {
  this.merchantLng = merchantLng;
}
public double getMerchantLat() {
  return merchantLat;
}
public void setMerchantLat(double merchantLat) {
  this.merchantLat = merchantLat;
}
public void setMerchantLat(float merchantLat) {
  this.merchantLat = merchantLat;
}
public String getMerchantLogo() {
  return merchantLogo;
}
public void setMerchantLogo(String merchantLogo) {
  this.merchantLogo = merchantLogo;
}
public PushOrder getPushOrder() {
  return pushOrder;
}
public void setPushOrder(PushOrder pushOrder) {
  this.pushOrder = pushOrder;
}
public List<DishList> getDishlist() {
  return dishlist;
}
public void setDishlist(List<DishList> dishlist) {
  this.dishlist = dishlist;
}


}
