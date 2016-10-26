package com.WeChat.list.service;

import java.util.List;

import com.WeChat.entity.Dish;

public interface OrderListService {

	public List<Dish> getDishList();
	public Dish	getDishByDishId(int dish_id);
}
