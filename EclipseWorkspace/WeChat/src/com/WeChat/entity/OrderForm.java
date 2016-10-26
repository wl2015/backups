package com.WeChat.entity;

import java.sql.Timestamp;


public class OrderForm {
	
	private int orderformId;
	private String name;
	private String linkPhone;
	private String addAddress;
	private Timestamp orderTime;
	private Timestamp sendTime;
	private int dishNum;
	private String dishName;
	private String dishTaste;
	private int sendFee;
	private Float price;
	private int isPay;
	private Float total;
	private int isDeal;
	private String userName;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

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

	public String getAddAddress() {
		return addAddress;
	}

	public void setAddAddress(String addAddress) {
		this.addAddress = addAddress;
	}

	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Timestamp date) {
		this.orderTime = date;
	}
	
	public Timestamp getSendTime() {
		return sendTime;
	}

	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}

	public int getDishNum() {
		return dishNum;
	}
	public void setDishNum(int dishNum) {
		this.dishNum = dishNum;
	}
	
	
	
	public String getDishTaste() {
		return dishTaste;
	}

	public void setDishTaste(String dishTaste) {
		this.dishTaste = dishTaste;
	}

	public int getSendFee() {
		return sendFee;
	}

	public void setSendFee(int sendFee) {
		this.sendFee = sendFee;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	
	
	public int getIsPay() {
		return isPay;
	}

	public void setIsPay(int isPay) {
		this.isPay = isPay;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public int getIsDeal() {
		return isDeal;
	}

	public void setIsDeal(int isDeal) {
		this.isDeal = isDeal;
	}
	
	
}
