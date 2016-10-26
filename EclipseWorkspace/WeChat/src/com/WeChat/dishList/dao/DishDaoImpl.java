package com.WeChat.dishList.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.WeChat.db.BaseDao;
import com.WeChat.entity.Dish;

public class DishDaoImpl extends BaseDao implements DishDao {
	//锟矫碉拷锟斤拷锟叫的菜碉拷
	public ArrayList<Dish> getAllDish() {
		ArrayList<Dish> dishList=new ArrayList<Dish>();
		// TODO Auto-generated method stub
		ResultSet rs = super.query("select * from dish ", null);
		try {
			while(rs.next()){
				Dish di=new Dish();				
				di.setPic(rs.getString("pic"));
				di.setDish_id(rs.getInt("dish_id"));
				di.setDish_intro(rs.getString("dish_intro"));
				di.setDish_name(rs.getString("dish_name"));
				di.setPrice(rs.getFloat("price"));
				di.setDish_taste(rs.getString("dish_taste"));
				dishList.add(di);
			}
			return dishList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally{
			 
			super.close();
		}
		return null;
	}
}
