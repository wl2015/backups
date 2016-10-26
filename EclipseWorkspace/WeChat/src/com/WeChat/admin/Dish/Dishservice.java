package com.WeChat.admin.Dish;

import java.util.ArrayList;
import java.util.HashMap;

import com.WeChat.entity.Dish;



public interface Dishservice {
	//获取所有的菜品名
	public HashMap<String,String> getDishNameList();
	//得到菜单数目
	public int getDishNum();
	//查询一条dish信息通过id
	public Dish getDishByid(Dish d);
	//向dish表里添加一条dish数据
	public boolean setDish(Dish d);
	
	//得到所有的菜单
	public ArrayList<Dish> getAllDish(int pageNum);
	
	//根据dishid删除该条菜单信息
	public boolean deleteDish(Dish d);
	
	//修改一条菜单信息
	public boolean modifyDish(Dish d);

	
}
