package com.WeChat.admin.waitesure.service;

import java.util.List;

import com.WeChat.entity.OrderForm;


public interface WaiteSureService {
	
	//鏄剧ず璁㈠崟
	public List<OrderForm> doOrderForm(int pageNum);
	
	//鏍规嵁璁㈠崟ID鏄剧ず鐢ㄦ埛璁㈠崟璇︽儏
	public OrderForm getInfoById(String orderformId);
	
	//鏍规嵁璁㈠崟ID鍒犻櫎璇ユ潯璁㈠崟淇℃伅
	public int DodeleteByorderId(int orderid);
	
	//淇敼鐘舵�鍊�
	public int Update(int orderid);
	
}
