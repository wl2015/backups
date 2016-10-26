package com.WeChat.list.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.WeChat.db.BaseDao;
import com.WeChat.entity.Dish;

public class OrderListDaoImpl extends BaseDao implements OrderListDao{
	
	/**
	 * 获取菜单列表
	 * */
	public List<Dish> getDishList() {
		List<Dish> dishlist = new ArrayList<Dish>();
		Dish di = null;
		String sql = "select d.dish_id, d.dish_name, d.price, d.pic, d.dish_intro, d.dish_taste, k.limitTop, k.orderNum from dish d, kucun k where d.dish_name=k.dish_name";
		ResultSet rs = super.query(sql, null);
		try {
			while(rs.next()){
				di = new Dish();
				di.setDish_id(rs.getInt(1));
				di.setDish_name(rs.getString(2));
				di.setPrice(rs.getFloat(3));
				di.setPic(rs.getString("pic"));
				di.setDish_intro(rs.getString("dish_intro"));
				di.setDish_taste(rs.getString("dish_taste"));
				di.setLeftNum(rs.getInt("limitTop")-rs.getInt("orderNum"));
				dishlist.add(di);
			}
		} catch (Exception e) {
			// TODO: handle exception	
		}finally{		
			super.close();
		}
		return dishlist;
	}
	/**
	 * 根据dish_id查询菜品dish
	 * */
	public Dish	getDishByDishId(int dish_id){
		Dish dish = new Dish(); 
		String sql="select * from dish where dish_id="+dish_id+";";
		ResultSet rs = super.query(sql, null);
		try {
			while(rs.next()){
				dish.setDish_id(rs.getInt(1));
				dish.setDish_name(rs.getString(2));
				dish.setPrice(rs.getFloat(3));
				dish.setPic(rs.getString("pic"));
				dish.setDish_intro(rs.getString("dish_intro"));
				dish.setDish_taste(rs.getString("dish_taste"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dish;
	}
}
