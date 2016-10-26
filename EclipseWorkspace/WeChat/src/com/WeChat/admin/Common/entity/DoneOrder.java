package com.WeChat.admin.Common.entity;


public class DoneOrder {
	private int orderformId;//订单号
	private String userName;//用户名，注册的电话号码
	private String name;//食客
	private String linkPhone;//食客号码
	private String frontAddress;//地址前缀
	private String orderTime;//下单时间
	private String sendTime;//送达时间
	private String sendName;//配送员名字
	public int getOrderformId() {
		return orderformId;
	}
	public void setOrderformId(int orderformId) {
		this.orderformId = orderformId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getFrontAddress() {
		return frontAddress;
	}
	public void setFrontAddress(String frontAddress) {
		this.frontAddress = frontAddress;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getSendName() {
		return sendName;
	}
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
}
