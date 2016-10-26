package com.WeChat.entity;

import java.sql.Date;



public class SellStatus {
	private String dishName;
	private float cost;
	private float price;
	private String totalaccount;
	private float totalIncome;
	private float totalcost;
	private float totalprofit;
	private String periodType;
	private String period;
	private Date date;
	
	public Date getDate() {
		return date;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public float getCost() {
		return cost;
	}
	public String getPeriodType() {
		return periodType;
	}
	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public float getTotalIncome() {
		return totalIncome;
	}
	public String getTotalaccount() {
		return totalaccount;
	}
	public void setTotalaccount(String totalaccount) {
		this.totalaccount = totalaccount;
	}
	public void setTotalIncome(float totalIncome) {
		this.totalIncome = totalIncome;
	}
	public float getTotalcost() {
		return totalcost;
	}
	public void setTotalcost(float totalcost) {
		this.totalcost = totalcost;
	}
	public float getTotalprofit() {
		return totalprofit;
	}
	public void setTotalprofit(float totalprofit) {
		this.totalprofit = totalprofit;
	}

}
