package com.cn.fruits.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


/***
 * 用户信息表
 * */
@Entity
@Table(name="user")
@DynamicInsert
@DynamicUpdate
public class UserEntity {
  
  @Id
  @GeneratedValue
  @Column(name="user_id")
  public int userId;//1、用户ID
  
  @Column(name="login_user_id")
  public int loginUserId;//2、login_user表的ID
  
  @Column(name="user_name")
  public String userName;//3、用户登录账户，此为电话号码
  
  @Column(name="password")
  public String passWord;//4、密码
  
  @Column(name="name")
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
