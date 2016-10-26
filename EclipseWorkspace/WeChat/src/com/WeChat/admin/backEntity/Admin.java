package com.WeChat.admin.backEntity;

public class Admin {
	
	private int admin_id;
	private String admin_name;
	private String nickName;
	private String password;
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int adminId) {
		admin_id = adminId;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String adminName) {
		admin_name = adminName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}