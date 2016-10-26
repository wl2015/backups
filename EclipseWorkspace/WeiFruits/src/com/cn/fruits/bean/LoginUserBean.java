package com.cn.fruits.bean;

/***
 * 用于微信直接登录
 * */
public class LoginUserBean {
  
  public int loginUserId;//1、login_user_id的ID
  
  public String loginUserName;//2、微信关注公众号生成的openedId

  public int getLoginUserId() {
    return loginUserId;
  }

  public void setLoginUserId(int loginUserId) {
    this.loginUserId = loginUserId;
  }

  public String getLoginUserName() {
    return loginUserName;
  }

  public void setLoginUserName(String loginUserName) {
    this.loginUserName = loginUserName;
  }
  
}
