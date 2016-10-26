package com.WeChat.admin.sured.service;

import java.util.List;

import com.WeChat.entity.OrderForm;
import com.WeChat.admin.sured.dao.SuredDao;
import com.WeChat.admin.sured.dao.SuredDaoImp;

public class SuredServiceImp implements SuredService{

	private SuredDao suredao;
	
	public SuredServiceImp(){
		setSuredao(new SuredDaoImp());
	}
	
	
	
	public void setSuredao(SuredDao suredao) {
		this.suredao = suredao;
	}


	public List<OrderForm> OrderFormTaked(int pageNum) {
		return suredao.getTakedOrderForm(pageNum);
	}



	public OrderForm getInfoById(String orderformId) {
		return suredao.getSuredInfoById(orderformId);
	}

}
