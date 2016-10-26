package com.cn.fruits.bean;

/**
 * 管理员账户 
 * */
public class AdminBean {
  public int adminId;//1、admin的ID
  
  public String adminAccount;//2、管理者登录账户
  
  public String adminName;//3、管理者姓名
  
  public String adminPassword;//4、管理者密码
  
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
