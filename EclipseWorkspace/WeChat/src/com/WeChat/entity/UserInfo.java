package com.WeChat.entity;

public class UserInfo {
	private int userinfo_id;//1.userinfo_id//INTEGER
	private String frontaddress;//2.送餐点（frontaddress）//MEDIUMTEXT
	private String add_address;//3.补充地址（add_address）//MEDIUMTEXT
	private String name;//4.食客姓名（name）//VARCHAR(45)
	private int sex;//5.性别(sex)//VARCHAR(4)
	private String link_phone;//6.联系电话(link_phone)//VARCHAR(45)
	private int user_id;//7.用户id(user_id)//INTEGER
	private String user_name;
	
	
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String userName) {
		user_name = userName;
	}
	public int getUserinfo_id() {
		return userinfo_id;
	}
	public void setUserinfo_id(int userinfo_id) {
		this.userinfo_id = userinfo_id;
	}

	public String getFrontaddress() {
		return frontaddress;
	}
	public void setFrontaddress(String frontaddress) {
		this.frontaddress = frontaddress;
	}
	public String getAdd_address() {
		return add_address;
	}
	public void setAdd_address(String add_address) {
		this.add_address = add_address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getLink_phone() {
		return link_phone;
	}
	public void setLink_phone(String link_phone) {
		this.link_phone = link_phone;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
}
