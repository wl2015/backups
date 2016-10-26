package com.WeChat.admin.sured.dao;

import java.util.List;

import com.WeChat.entity.OrderForm;

public interface SuredDao {

	public List<OrderForm> getTakedOrderForm(int pageNum);

	public OrderForm getSuredInfoById(String orderformId);

}
