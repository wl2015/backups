package com.yc.bean;

/**
 * 用户实体类
 * @author 
 *
 */
public class User {

  //用户id
  private int userId;
  
  //用户手机号
  private String userPhone;
  
  //用户密码
  private String userPassword;
  
  //用户姓名
  private String userName;
  
  //性别
  private String userSex;
  
  //用户星级
  private float star;
  
  //绑定邮箱
  private String userMail;
  
  //注册时间
  private String registTime;
  
  //是否会员
  private int vip;
  
  public User(){
    
  }
  
  public User(String userPhone, String userPassword){
    this.userPhone=userPhone;
    this.userPassword=userPassword;
  }
  
  public User(int userId, String userPhone, String userName, String userSex,
      float star, String userMail, String registTime, int vip){
    this.userId = userId;
    this.userPhone = userPhone;
    this.userName = userName;
    this.userSex = userSex;
    this.star = star;
    this.userMail = userMail;
    this.registTime = registTime;
    this.vip = vip;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getUserPhone() {
    return userPhone;
  }

  public void setUserPhone(String userPhone) {
    this.userPhone = userPhone;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserSex() {
    return userSex;
  }

  public void setUserSex(String userSex) {
    this.userSex = userSex;
  }

  public float getStar() {
    return star;
  }

  public void setStar(float star) {
    this.star = star;
  }

  public String getUserMail() {
    return userMail;
  }

  public void setUserMail(String userMail) {
    this.userMail = userMail;
  }

  public String getRegistTime() {
    return registTime;
  }

  public void setRegistTime(String registTime) {
    this.registTime = registTime;
  }

  public int getVip() {
    return vip;
  }

  public void setVip(int vip) {
    this.vip = vip;
  }

}
