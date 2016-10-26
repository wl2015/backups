package com.yc.bean;

import java.util.List;

/**
 * 管理员实体类
 * @author Administrator
 *
 */
public class Admin {
    //管理员id
    private  int adminId;
    //账号
    private  String adminAccount;
    //密码
    private  String adminPassword;
    //姓名
    private  String adminName;
    //手机号
    private  String adminPhone;
    //管理职位
    private  String position;
    //邮箱
    private  String adminMail;
    //上一次登录时间
    private  String lastLoginTime;
    //上一次登录ip
    private  String lastLoginIp;
    //创建时间
    private  String createTime;
    
    //权限列表（职位）
    private List<Limits> limitsList;
    
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
    public String getAdminPassword() {
      return adminPassword;
    }
    public void setAdminPassword(String adminPassword) {
      this.adminPassword = adminPassword;
    }
    public String getAdminName() {
      return adminName;
    }
    public void setAdminName(String adminName) {
      this.adminName = adminName;
    }
    public String getAdminPhone() {
      return adminPhone;
    }
    public void setAdminPhone(String adminPhone) {
      this.adminPhone = adminPhone;
    }
    public String getPosition() {
      return position;
    }
    public void setPosition(String position) {
      this.position = position;
    }
    public String getAdminMail() {
      return adminMail;
    }
    public void setAdminMail(String adminMail) {
      this.adminMail = adminMail;
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
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public List<Limits> getLimitsList() {
        return limitsList;
    }
    public void setLimitsList(List<Limits> limitsList) {
        this.limitsList = limitsList;
    }
    
    
    
}
