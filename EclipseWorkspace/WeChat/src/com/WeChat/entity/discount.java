package com.WeChat.entity;

import java.sql.Timestamp;

public class discount {
	
	private int discount_id;//1.优惠券id（discount_id）//INTEGER
	private int user_id;//2.用户id（user_id）//INTEGER
	private float reduce;//3.优惠数额(reduce)//FLOAT
	private Timestamp valid_day;//4.有效期(valid_day)//DATETIME
	private int is_use;//5.使用情况(is_use)//INTEGER
	public int getDiscount_id() {
		return discount_id;
	}
	public void setDiscount_id(int discount_id) {
		this.discount_id = discount_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public float getReduce() {
		return reduce;
	}
	public void setReduce(float reduce) {
		this.reduce = reduce;
	}
	public Timestamp getValid_day() {
		return valid_day;
	}
	public void setValid_day(Timestamp valid_day) {
		this.valid_day = valid_day;
	}
	public int getIs_use() {
		return is_use;
	}
	public void setIs_use(int is_use) {
		this.is_use = is_use;
	}
	
}
