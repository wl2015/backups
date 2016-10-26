package com.cn.freemall.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="user")
@DynamicInsert
@DynamicUpdate
public class UserEntity {
  @Id
  @GeneratedValue
  @Column(name="user_id")
  public int userId;//1、用户ID
  
  @Column(name="user_name")
  public String userName;//2、用户名
  
  @Column(name="password")
  public String passWord;//3、密码
  
  @Column(name="phone_number")
  public String phoneNumber;//4、电话号码
  
  @Column(name="name")
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
