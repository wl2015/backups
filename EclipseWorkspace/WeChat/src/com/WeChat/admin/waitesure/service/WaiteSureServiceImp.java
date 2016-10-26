package com.WeChat.admin.waitesure.service;

import java.util.List;

import com.WeChat.entity.OrderForm;
import com.WeChat.admin.waitesure.dao.WaiteSureDao;
import com.WeChat.admin.waitesure.dao.WaiteSureDaoImp;

public class WaiteSureServiceImp implements WaiteSureService{
	
	private WaiteSureDao waitedao;
	
	public WaiteSureServiceImp(){
		setWaitedao(new WaiteSureDaoImp());
	}
	
	public void setWaitedao(WaiteSureDao waitedao) {
		this.waitedao = waitedao;
	}

	
	public List<OrderForm> doOrderForm(int pageNum) {
		return waitedao.getUserOrderForm(pageNum);
	}

	public OrderForm getInfoById(String orderformId) {
		return waitedao.getOrderInfoById(orderformId);
	}

	public int DodeleteByorderId(int orderid) {
		return waitedao.DeleteOrderform(orderid);
	}

	public int Update(int orderid) {
		return waitedao.update(orderid);
	}

	
}
