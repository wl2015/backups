package com.WeChat.mymanshao.mylist.service;

import java.util.List;

import com.WeChat.entity.OrderForm;
import com.WeChat.entity.User;

public interface MyListService {
	
	//查询还未处理的订单
	public List<OrderForm> doGetOderForm(User u);
	
	//查询历史订单
	public List<OrderForm> doGetNotOrderForm(User u);
	
}
