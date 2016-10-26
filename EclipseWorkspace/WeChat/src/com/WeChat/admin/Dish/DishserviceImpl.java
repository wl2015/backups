package com.WeChat.admin.Dish;

import java.util.ArrayList;
import java.util.HashMap;

import com.WeChat.entity.Dish;




public class DishserviceImpl implements Dishservice {
	private DishDao dishdao;
	public void setDishDao(DishDao dishDao) {
		this.dishdao = dishDao;
	}
	public DishserviceImpl(){
		this.setDishDao(new DishDaoImpl());
	}
	//获取所有的菜品名
	public HashMap<String,String> getDishNameList(){
		return dishdao.getDishNameList();
	}
	//查询一条dish信息通过id
	public Dish getDishByid(Dish d){
		return dishdao.getDishByid(d);
	}
	//向dish表里添加一条dish数据
	public boolean setDish(Dish d) {
		return dishdao.setDish(d);
	}

	//得到菜单数目
	public int getDishNum(){
		return dishdao.getDishNum();
	}
	//得到所有的菜单
	public ArrayList<Dish> getAllDish(int pageNum) {
		// TODO Auto-generated method stub		
		return dishdao.getAllDish(pageNum);
	}
	
	//根据dishid删除该条菜单信息
	public boolean deleteDish(Dish d){
		
		return dishdao.deleteDish(d);
	}
	
	//修改一条菜单信息
	public boolean modifyDish(Dish d){
		
		return dishdao.modifyDish(d);
	}
}
