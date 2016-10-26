package com.yc.bean;

public class msureOrder {
	private int id;//对应goId
	private int spendTime;//商家确定的送餐时间
	private int mid;//商家id
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSpendTime() {
		return spendTime;
	}
	public void setSpendTime(int spendTime) {
		this.spendTime = spendTime;
	}
}
