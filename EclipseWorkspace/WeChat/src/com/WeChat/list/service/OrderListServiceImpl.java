package com.WeChat.list.service;

import java.util.List;

import com.WeChat.entity.Dish;
import com.WeChat.list.dao.OrderListDao;
import com.WeChat.list.dao.OrderListDaoImpl;

public class OrderListServiceImpl implements OrderListService{
	
	private OrderListDao orderlistdao;
	
	public OrderListServiceImpl(){
		setOrderlistdao(new OrderListDaoImpl());
	}
	public void setOrderlistdao(OrderListDao orderlistdao) {
		this.orderlistdao = orderlistdao;
	}

	//获取菜单列表
	public List<Dish> getDishList() {
		return orderlistdao.getDishList();
	}
	public Dish getDishByDishId(int dish_id) {
		return orderlistdao.getDishByDishId(dish_id);
	}
}
