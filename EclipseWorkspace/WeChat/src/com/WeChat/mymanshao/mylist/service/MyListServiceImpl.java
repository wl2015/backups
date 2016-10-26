package com.WeChat.mymanshao.mylist.service;

import java.util.List;

import com.WeChat.entity.OrderForm;
import com.WeChat.entity.User;
import com.WeChat.mymanshao.mylist.dao.MyListDao;
import com.WeChat.mymanshao.mylist.dao.MyListDaoImp;

public class MyListServiceImpl implements MyListService {
	
	public List<OrderForm> doGetOderForm(User u) {
		MyListDao myListDao= new MyListDaoImp();
		return myListDao.getOrderInfo(u.getUser_id());
	}

	public List<OrderForm> doGetNotOrderForm(User u) {
		MyListDao myListDaoN = new MyListDaoImp();
		
		return myListDaoN.getNotOrderInfo(u.getUser_id());
	}
}
