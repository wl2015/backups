package com.WeChat.admin.timeout.service;

import java.util.List;

import com.WeChat.entity.OrderForm;

public interface TimeOutService {

	//显示已过期订单
	public List<OrderForm> doTimeOutForm(int pageNum);
	
	//根据ID显示用户详细信息
	public OrderForm getInfoById(String orderformId);
	
	//根据订单ID删除该条订单信息
	public int DodeleteByorderId(int orderid);
}
