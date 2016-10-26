package com.WeChat.mymanshao.mylist.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.WeChat.db.BaseDao;
import com.WeChat.entity.OrderForm;

public class MyListDaoImp extends BaseDao implements MyListDao{


	/**
	 * 查询正在处理的用户订单
	 */
	public List<OrderForm> getOrderInfo(int user_id) {
		String sql = "SELECT i.frontaddress,d.dish_name,d.price,o.send_fee,o.send_time,o.total,u.user_name,od.dish_num,o.isdeal,o.ispay,i.link_phone FROM orderform o,userinfo i,dish d,user u,orderdish od WHERE o.user_id = u.user_id and i.userinfo_id=u.user_id and od.orderform_id=o.orderform_id and d.dish_id=od.dish_id and isdeal=0 and o.user_id=?";
		List<OrderForm> list = new ArrayList<OrderForm>();
		ResultSet rs = super.query(sql, new Object[]{user_id});
		
		try {
			while(rs.next()){
				OrderForm orderform = new OrderForm();
				orderform.setDishName(rs.getString("dish_name"));
				orderform.setPrice(rs.getFloat("price"));
				orderform.setDishNum(rs.getInt("dish_num"));
				orderform.setIsDeal(rs.getInt("isdeal"));
				orderform.setIsPay(rs.getInt("ispay"));
				orderform.setSendTime(rs.getTimestamp("send_time"));
				orderform.setSendFee(rs.getInt("send_fee"));
				orderform.setTotal(rs.getFloat("total"));
				orderform.setUserName(rs.getString("user_name"));
				orderform.setAddAddress(rs.getString("frontaddress"));
				orderform.setLinkPhone(rs.getString("link_phone"));
				list.add(orderform);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			super.close();
		}
		
		return list;
	}

	
	//查询历史订单
	public List<OrderForm> getNotOrderInfo(int user_id) {
		String sql = "SELECT i.frontaddress,d.dish_name,d.price,o.send_fee,o.send_time,o.total,u.user_name,od.dish_num,o.isdeal,o.ispay,i.link_phone FROM orderform o,userinfo i,dish d,user u,orderdish od WHERE o.user_id = u.user_id and i.userinfo_id=u.user_id and od.orderform_id=o.orderform_id and d.dish_id=od.dish_id and o.user_id=? and isdeal=1";
		List<OrderForm> list = new ArrayList<OrderForm>();
		ResultSet rs = super.query(sql, new Object[]{user_id});
		
		try {
			while(rs.next()){
				OrderForm orderform = new OrderForm();
				orderform.setDishName(rs.getString("dish_name"));
				orderform.setPrice(rs.getFloat("price"));
				orderform.setDishNum(rs.getInt("dish_num"));
				orderform.setIsDeal(rs.getInt("isdeal"));
				orderform.setIsPay(rs.getInt("ispay"));
				orderform.setSendTime(rs.getTimestamp("send_time"));
				orderform.setSendFee(rs.getInt("send_fee"));
				orderform.setTotal(rs.getFloat("total"));
				orderform.setUserName(rs.getString("user_name"));
				orderform.setAddAddress(rs.getString("frontaddress"));
				orderform.setLinkPhone(rs.getString("link_phone"));
				list.add(orderform);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			super.close();
		}
		return list;
	}
}
