package com.cn.travel.bean;

/***
 * 用户信息表
 * */
public class UserBean {
  
  public int userId;//1、用户ID
  
  public String userName;//2、用户名
  
  public String passWord;//3、密码
  
  public String phoneNumber;//4、电话号码
  
  public String name;//5、用户的姓名

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassWord() {
    return passWord;
  }

  public void setPassWord(String passWord) {
    this.passWord = passWord;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
}
