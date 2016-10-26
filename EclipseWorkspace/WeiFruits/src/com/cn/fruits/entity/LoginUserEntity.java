package com.cn.fruits.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/***
 * 用于微信直接登录
 * */
@Entity
@Table(name="login_user")
@DynamicInsert
@DynamicUpdate
public class LoginUserEntity {
  
  @Id
  @GeneratedValue
  @Column(name="login_user_id")
  public int loginUserId;//1、login_user_id的ID
  
  @Column(name="login_user_name")
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
