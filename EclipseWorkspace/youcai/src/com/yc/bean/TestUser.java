package com.yc.bean;

/**
 * 用户实体类
 * @author 
 *
 */
public class TestUser {

  //用户id
  private int u_id;
  
  //用户手机号
  private String u_phone;
  
  //用户密码
  private String u_password;
  
  //用户姓名
  private String u_name;
  
  //性别
  private String u_sex;
  
  //用户星级
  private int star;
  
  //绑定邮箱
  private String u_mail;
  
  //注册时间
  private String regist_time;
  
  //是否会员
  private int vip;

  public int getU_id() {
    return u_id;
  }

  public void setU_id(int u_id) {
    this.u_id = u_id;
  }

  public String getU_phone() {
    return u_phone;
  }

  public void setU_phone(String u_phone) {
    this.u_phone = u_phone;
  }

  public String getU_password() {
    return u_password;
  }

  public void setU_password(String u_password) {
    this.u_password = u_password;
  }

  public String getU_name() {
    return u_name;
  }

  public void setU_name(String u_name) {
    this.u_name = u_name;
  }

  public String getU_sex() {
    return u_sex;
  }

  public void setU_sex(String u_sex) {
    this.u_sex = u_sex;
  }

  public int getStar() {
    return star;
  }

  public void setStar(int star) {
    this.star = star;
  }

  public String getU_mail() {
    return u_mail;
  }

  public void setU_mail(String u_mail) {
    this.u_mail = u_mail;
  }

  

  public String getRegist_time() {
    return regist_time;
  }

  public void setRegist_time(String regist_time) {
    this.regist_time = regist_time;
  }

  public int getVip() {
    return vip;
  }

  public void setVip(int vip) {
    this.vip = vip;
  }
  
  
}
