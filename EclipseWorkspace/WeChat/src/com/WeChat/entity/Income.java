package com.WeChat.entity;

import java.sql.Date;

public class Income {
	private int onlineIncome;
	private int offLineIncome;
	private int allIncome;
	private String periodType;
	private String period;
	private Date date;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getOnlineIncome() {
		return onlineIncome;
	}
	public void setOnlineIncome(int onlineIncome) {
		this.onlineIncome = onlineIncome;
	}
	public int getOffLineIncome() {
		return offLineIncome;
	}
	public void setOffLineIncome(int offLineIncome) {
		this.offLineIncome = offLineIncome;
	}
	public int getAllIncome() {
		return allIncome;
	}
	public void setAllIncome(int allIncome) {
		this.allIncome = allIncome;
	}
	public String getPeriodType() {
		return periodType;
	}
	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	
	
}
