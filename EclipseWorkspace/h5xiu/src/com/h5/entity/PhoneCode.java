package com.h5.entity;

public class PhoneCode {

	private String phone;
	
	private int code;
	
	private int createTime;
	
	public PhoneCode(String phone, int code, int createTime) {
		this.phone = phone;
		this.code = code;
		this.createTime = createTime;
	}

	public String getPhone() {
		return phone;
	}

	public int getCode() {
		return code;
	}

	public int getCreateTime() {
		return createTime;
	}

	@Override
	public String toString() {
		return "PhoneCode [phone=" + phone + ", code=" + code + ", createTime="
				+ createTime + "]";
	}
	
}
