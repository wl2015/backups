package com.cn.fruits.bean;

/***
 * 用户信息表
 * */
public class UserBean {
  public int userId;//1、用户ID
  
  public int loginUserId;//2、login_user表的ID
  
  public String userName;//3、用户登录账户，此为电话号码
  
  public String passWord;//4、为以后开发做的保留
  
  public String name;//5、用户名

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getLoginUserId() {
    return loginUserId;
  }

  public void setLoginUserId(int loginUserId) {
    this.loginUserId = loginUserId;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
