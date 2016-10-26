package com.WeChat.admin.waitesure.dao;

import java.util.List;

import com.WeChat.entity.OrderForm;

public interface WaiteSureDao {

	public List<OrderForm> getUserOrderForm(int pageNum);

	public OrderForm getOrderInfoById(String orderformId);

	public int DeleteOrderform(int orderid);

	public int update(int orderid);

}
