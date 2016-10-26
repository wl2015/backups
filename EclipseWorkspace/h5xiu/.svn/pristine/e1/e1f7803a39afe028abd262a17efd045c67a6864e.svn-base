package com.h5.entity;



import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 
 * @author Paul Iverson
 * 用户实体类
 */
@Entity

@Table(name="h5_user")
@DynamicInsert
@DynamicUpdate
public class User {
	
	@Id
	@GeneratedValue
	@Column(name="user_id")
	private String userId; //用户ID
	
	@Column(name="user_name")
	private String userName; //用户名（昵称）
	
	@Column(name="phone_num")
	private String phoneNum; //用户手机电话号码
	
	@Column(name="password")
	private String password; //密码
	
	@Column(name="account_money")
	private BigDecimal accountMoney; //用户账户余额

	@Column(name="register_time")
	private Integer registerTime; //用户注册时间

	@Column(name="register_ip")
	private String registerIp; //用户注册IP

	@Column(name="last_login_time")
	private Integer lastLoginTime; //用户最后一次登录时间

	@Column(name="last_login_ip")
	private String lastLoginIp; //用户最后一次登录IP

	@Column(name="user_head_img")
	private String userHeadImg; //用户头像

	
	@Column(name="user_status")
	private Integer userStatus; //用户状态（0：不正常， 1：普通用户， 2：vip用户  ）
	
	
	/*//这种是临时属性的注解
	@Transient
	private int sex;*/
	
	public User(){
		
	}
	
	public User(String userName, String phoneNum){
		this.userName = userName;
		this.phoneNum = phoneNum;
	}
	
	public User(String userId, String userName, String phoneNum,
			String password, BigDecimal accountMoney, int registerTime,
			String registerIp, int lastLoginTime, String lastLoginIp,
			String userHeadImg, int userStatus) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.phoneNum = phoneNum;
		this.password = password;
		this.accountMoney = accountMoney;
		this.registerTime = registerTime;
		this.registerIp = registerIp;
		this.lastLoginTime = lastLoginTime;
		this.lastLoginIp = lastLoginIp;
		this.userHeadImg = userHeadImg;
		this.userStatus = userStatus;
	}
	
	public User(String userId, String userName, 
			String phoneNum, Integer registerTime, String registerIp, 
			Integer lastLoginTime, String lastLoginIp, String userHeadImg, 
			Integer userStatus, BigDecimal accountMoney){
		this.userId = userId;
		this.userName = userName;
		this.phoneNum = phoneNum;
		this.registerTime = registerTime;
		this.registerIp = registerIp;
		this.lastLoginTime = lastLoginTime;
		this.lastLoginIp = lastLoginIp;
		this.userHeadImg = userHeadImg;
		this.userStatus = userStatus;
		this.accountMoney = accountMoney;
	}
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getAccountMoney() {
		return accountMoney;
	}

	public void setAccountMoney(BigDecimal accountMoney) {
		this.accountMoney = accountMoney;
	}

	public int getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(int registerTime) {
		this.registerTime = registerTime;
	}

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public int getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(int lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getUserHeadImg() {
		return userHeadImg;
	}

	public void setUserHeadImg(String userHeadImg) {
		this.userHeadImg = userHeadImg;
	}

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	/*public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}*/
	
	
	
	
}
