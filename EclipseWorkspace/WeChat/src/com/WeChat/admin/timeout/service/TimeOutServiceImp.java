package com.WeChat.admin.timeout.service;

import java.util.List;

import com.WeChat.entity.OrderForm;
import com.WeChat.admin.timeout.dao.TimeOutDao;
import com.WeChat.admin.timeout.dao.TimeOutDaoImp;

public class TimeOutServiceImp implements TimeOutService{

	private TimeOutDao timeoutdao;
	
	public TimeOutServiceImp(){
		setTimeoutdao(new TimeOutDaoImp());
	}
	
	public TimeOutDao getTimeoutdao() {
		return timeoutdao;
	}

	public void setTimeoutdao(TimeOutDao timeoutdao) {
		this.timeoutdao = timeoutdao;
	}



	public List<OrderForm> doTimeOutForm(int pageNum) {
		return timeoutdao.getTimeOutOrder(pageNum);
	}

	public OrderForm getInfoById(String orderformId) {
		return timeoutdao.getTimeOutInfoById(orderformId);
	}

	public int DodeleteByorderId(int orderid) {
		return timeoutdao.deleteTimeOutById(orderid);
	}

}
