package com.WeChat.admin.backEntity;

import java.sql.Date;


public class kucun {
	
	private int kucun_id;
	private String dish_name;
	private int limitTop;
	private int orderNum;
	private Date dataTime;
	
	public int getKucun_id() {
		return kucun_id;
	}
	public void setKucun_id(int kucunId) {
		kucun_id = kucunId;
	}
	
	public String getDish_name() {
		return dish_name;
	}
	public void setDish_name(String dishName) {
		dish_name = dishName;
	}
	public int getLimitTop() {
		return limitTop;
	}
	public void setLimitTop(int limitTop) {
		this.limitTop = limitTop;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public Date getDataTime() {
		return dataTime;
	}
	public void setDataTime(Date dataTime) {
		this.dataTime = dataTime;
	}

	
}