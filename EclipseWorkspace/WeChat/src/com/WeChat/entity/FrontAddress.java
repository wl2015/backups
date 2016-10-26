package com.WeChat.entity;

public class FrontAddress {
	
	private int frontaddress_id;//1、address_id
	private String front_address;//2、送餐点(front_address)
	
	public int getFrontaddress_id() {
		return frontaddress_id;
	}
	public void setFrontaddress_id(int frontaddress_id) {
		this.frontaddress_id = frontaddress_id;
	}
	public String getFront_address() {
		return front_address;
	}
	public void setFront_address(String front_address) {
		this.front_address = front_address;
	}

}