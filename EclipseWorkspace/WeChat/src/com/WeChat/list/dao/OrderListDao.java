package com.WeChat.list.dao;

import java.util.List;

import com.WeChat.entity.Dish;

public interface OrderListDao {
	
	public List<Dish> getDishList();
	public Dish	getDishByDishId(int dish_id);
}
