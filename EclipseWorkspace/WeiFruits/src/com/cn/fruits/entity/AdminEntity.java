package com.cn.fruits.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 管理员账户 
 * */
@Entity
@Table(name="admin")
@DynamicInsert
@DynamicUpdate
public class AdminEntity {
  @Id
  @GeneratedValue
  @Column(name="admin_id")
  public int adminId;//1、admin的ID
  
  @Column(name="admin_account")
  public String adminAccount;//2、管理者登录账户
  
  @Column(name="admin_name")
  public String adminName;//3、管理者姓名
  
  @Column(name="admin_password")
  public String adminPassword;//4、管理者密码
  
  @Column(name="admin_limit")
  public int adminLimit;//5、 1为超级权限

  public int getAdminId() {
    return adminId;
  }

  public void setAdminId(int adminId) {
    this.adminId = adminId;
  }

  public String getAdminAccount() {
    return adminAccount;
  }

  public void setAdminAccount(String adminAccount) {
    this.adminAccount = adminAccount;
  }

  public String getAdminName() {
    return adminName;
  }

  public void setAdminName(String adminName) {
    this.adminName = adminName;
  }

  public String getAdminPassword() {
    return adminPassword;
  }

  public void setAdminPassword(String adminPassword) {
    this.adminPassword = adminPassword;
  }

  public int getAdminLimit() {
    return adminLimit;
  }

  public void setAdminLimit(int adminLimit) {
    this.adminLimit = adminLimit;
  }
  
}
