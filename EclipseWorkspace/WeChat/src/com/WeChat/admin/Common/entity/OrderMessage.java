package com.WeChat.admin.Common.entity;


public class OrderMessage {
	private int orderformId;//订单号
	private String name;//食客
	private String linkPhone;//食客号码
	private String frontaddress;//地址前缀
	private String sendTime;//送达时间
	public int getOrderformId() {
		return orderformId;
	}
	public void setOrderformId(int orderformId) {
		this.orderformId = orderformId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLinkPhone() {
		return linkPhone;
	}
	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}
	public String getFrontaddress() {
		return frontaddress;
	}
	public void setFrontaddress(String frontaddress) {
		this.frontaddress = frontaddress;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
}
