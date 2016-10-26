package com.WeChat.dishList.service;

import java.util.ArrayList;

import com.WeChat.dishList.dao.DishDao;
import com.WeChat.dishList.dao.DishDaoImpl;
import com.WeChat.entity.Dish;


public class DishServiceImpl implements DishService {
	private DishDao dishdao;
	public void setDishDao(DishDao dishdao) {
		this.dishdao = dishdao;
	}
	public DishServiceImpl(){
		this.setDishDao(new DishDaoImpl());
	}
	

	
	//得到所有的菜单
	public ArrayList<Dish> getAllDish() {
		// TODO Auto-generated method stub		
		return dishdao.getAllDish();
	}
	
}
