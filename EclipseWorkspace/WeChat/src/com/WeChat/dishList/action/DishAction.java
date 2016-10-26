package com.WeChat.dishList.action;

import java.util.ArrayList;

import com.WeChat.dishList.service.DishService;
import com.WeChat.dishList.service.DishServiceImpl;
import com.WeChat.entity.Dish;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;

import com.WeChat.entity.Dish;
import com.opensymphony.xwork2.ActionSupport;

public class DishAction extends ActionSupport{
	//DishList对象
	ArrayList<Dish> dishlist;
	private DishService dishservice=new DishServiceImpl();

	public ArrayList<Dish> getDishlist() {
		return dishlist;
	}
	
	//设置dishlist对象
	public String CheckDish(){		
		dishlist=dishservice.getAllDish();
		return "dishlist";
	}
}