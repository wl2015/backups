package com.WeChat.admin.timeout.dao;

import java.util.List;

import com.WeChat.entity.OrderForm;

public interface TimeOutDao {

	 public List<OrderForm> getTimeOutOrder(int pageNum);

	public OrderForm getTimeOutInfoById(String orderformId);

	public int deleteTimeOutById(int orderid);

}
