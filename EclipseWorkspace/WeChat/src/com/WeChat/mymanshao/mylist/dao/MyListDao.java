package com.WeChat.mymanshao.mylist.dao;

import java.util.List;

import com.WeChat.entity.OrderForm;

public interface MyListDao {
	
	//根据ID得到用户订单

	public List<OrderForm> getOrderInfo(int user_id);

	public List<OrderForm> getNotOrderInfo(int user_id);
		
		
}
