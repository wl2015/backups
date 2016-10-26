package com.WeChat.dishList.dao;

import java.util.ArrayList;

import com.WeChat.entity.Dish;

public interface DishDao {
	//得到所有的菜单
	public ArrayList<Dish> getAllDish();
}
