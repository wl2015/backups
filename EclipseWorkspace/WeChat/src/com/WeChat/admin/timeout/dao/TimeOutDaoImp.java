package com.WeChat.admin.timeout.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.WeChat.db.BaseDao;
import com.WeChat.entity.OrderForm;

public class TimeOutDaoImp extends BaseDao implements TimeOutDao{

	Date dateTime = new Date();
	
	//送餐时间
	Date SendstartDate = new Date(dateTime.getYear(),dateTime.getMonth(),dateTime.getDate()-3,0,0,0);
	Date SendendDate = new Date(dateTime.getYear(),dateTime.getMonth(),dateTime.getDate(),0,0,0);
		
	//送餐时间转化格式类
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String sendStartDa = sdf2.format(SendstartDate);
	String sendEndDa = sdf2.format(SendendDate);
	   
	public int getTotalnum(){
		String sql="SELECT COUNT(orderform_id) FROM orderform WHERE isdeal=0 and send_time BETWEEN '"+sendStartDa+"'and '"+sendEndDa+"'";
		try {
			ResultSet rs=(ResultSet) super.query(sql, null);
			if(rs.next()){
			
				return rs.getInt(1);
		} 
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
		return 0;
	}
	
	/*
	 * 查询并显示已过期订单（状态值为0，送餐时间已过）
	 * @see com.pk.wechat.timeout.dao.TimeOutDao#getTimeOutOrder(int)
	 */
	int pageSize = 5;
	public List<OrderForm> getTimeOutOrder(int pageNum) {
		String sql = "SELECT o.orderform_id,u.name,u.link_phone,o.order_time,o.send_time,o.isdeal FROM orderform o,userinfo u WHERE o.isdeal=0 and o.user_id = u.userinfo_id and o.send_time BETWEEN '"+sendStartDa+"'and '"+sendEndDa+"' ORDER BY o.send_time DESC LIMIT "+pageSize*(pageNum-1)+","+pageSize;

		List<OrderForm> orderOutlist = new ArrayList<OrderForm>(); 
		ResultSet rs = (ResultSet) super.query(sql, null);
		
		try {
			while(rs.next()){
				OrderForm order = new OrderForm();
				order.setOrderformId(rs.getInt("orderform_id"));
				order.setName(rs.getString("name"));
				order.setLinkPhone(rs.getString("link_phone"));
				order.setOrderTime(rs.getTimestamp("order_time"));
				order.setSendTime(rs.getTimestamp("send_time"));
				order.setIsDeal(rs.getInt("isdeal"));
				orderOutlist.add(order);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.close();
		}
		return orderOutlist;
	}
	
	
	/*
	 * 根据用户ID查询并显示已过期订单的详细信息
	 * @see com.pk.wechat.timeout.dao.TimeOutDao#getTimeOutInfoById(java.lang.String)
	 */
	public OrderForm getTimeOutInfoById(String orderformId) {
		String sql = "SELECT o.orderform_id,u.name,u.link_phone,d.dish_taste,d.dish_name,od.dish_num,d.price,o.send_fee,o.total,o.ispay,u.add_address,o.order_time FROM orderform o,userinfo u,dish d,orderdish od WHERE o.user_id = u.user_id and od.orderform_id=o.orderform_id and od.dish_id=d.dish_id and o.orderform_id=?";
		ResultSet rs = (ResultSet) super.query(sql, new Object[]{orderformId});
		OrderForm order = null;
		try {
			while(rs.next()){
				order = new OrderForm();
				order.setOrderformId(rs.getInt("orderform_id"));
				order.setName(rs.getString("name"));
				order.setLinkPhone(rs.getString("link_phone"));
				order.setDishTaste(rs.getString("dish_taste"));
				order.setDishName(rs.getString("dish_name"));
				order.setDishNum(rs.getInt("dish_num"));
				order.setPrice(rs.getFloat("price"));
				order.setSendFee(rs.getInt("send_fee"));
				order.setTotal(rs.getFloat("total"));
				order.setIsPay(rs.getInt("ispay"));
				order.setAddAddress(rs.getString("add_address"));
				order.setOrderTime(rs.getTimestamp("order_time"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.close();
		}
		return order;
	}

	/*
	 * 根据订单ID删除订单信息
	 * @see com.pk.wechat.timeout.dao.TimeOutDao#deleteTimeOutById(int)
	 */
	public int deleteTimeOutById(int orderid) {
		int rows = super.update("DELETE FROM orderform WHERE isdeal=0 and send_time BETWEEN '"+sendStartDa+"'and '"+sendEndDa+"' and orderform_id = ?", 
				new Object[]{
				orderid,
		});
		super.close();
		return rows;
	}
}
