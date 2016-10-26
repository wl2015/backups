package com.WeChat.admin.sured.service;

import java.util.List;

import com.WeChat.entity.OrderForm;

public interface SuredService {
	
	//显示已处理过的订单
	public List<OrderForm> OrderFormTaked(int pageNum);
	
	//根据订单ID显示已确认订单的详细信息
	public OrderForm getInfoById(String orderformId);
}
