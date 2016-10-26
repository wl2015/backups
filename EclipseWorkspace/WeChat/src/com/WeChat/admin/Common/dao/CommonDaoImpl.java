package com.WeChat.admin.Common.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.WeChat.admin.Common.entity.ChooseSendPeople;
import com.WeChat.admin.Common.entity.DoneOrder;
import com.WeChat.admin.Common.entity.OrderMessage;
import com.WeChat.db.BaseDao;
import com.WeChat.db.TimeOperate;

public class CommonDaoImpl extends BaseDao implements CommonDao{
	/**
	 * 获取已确认订单列表（正在做，但没选择配送员）
	 * */
	public List<OrderMessage> getOrderMessageList(int pageCount){
		List<OrderMessage> orderMessageList = new ArrayList<OrderMessage>();
		SimpleDateFormat f =  new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		Date d = new Date();
		TimeOperate to = new TimeOperate();
		String beforetime = to.changeTimeFormat(d);
		String aftertime = to.addDaysbyNum(d, 1);
		String sql="select o.orderform_id, u.name, u.link_phone, u.frontaddress, o.send_time from orderform o, userinfo u where u.userinfo_id=o.userinfo_id and isdeal=1 and people_id='0' and o.send_time>='"+beforetime+"' and o.send_time<='"+aftertime+"'"+" limit "+2*pageCount+","+2;
		ResultSet rs = super.query(sql, null);
		try {
  			while(rs.next()){
				OrderMessage om = new OrderMessage();
				om.setOrderformId(rs.getInt(1));
				om.setName(rs.getString(2));
				om.setLinkPhone(rs.getString(3));
				om.setFrontaddress(rs.getString(4));
				d = rs.getTimestamp(5);
				om.setSendTime(f.format(d));
				orderMessageList.add(om);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderMessageList;
	}
	
	/***
	 * 获取下拉列表里面的配送员名单sendPeopleList
	 * */
	public List<ChooseSendPeople> getSendPeopleList(){
		List<ChooseSendPeople> choosesendpeoplelist = new ArrayList<ChooseSendPeople>();
		String sql = "select people_id, name from sendpeople";
		ResultSet rs = super.query(sql, null);
		try {
			while(rs.next()){
				ChooseSendPeople csp = new ChooseSendPeople();
				csp.setPeopleId(rs.getInt(1));
				csp.setName(rs.getString(2));
				choosesendpeoplelist.add(csp);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return choosesendpeoplelist;
	}
	/**
	 *	在订单里添加送餐员id
	 * */
	public void addPeopleId(int orderformId, int PeopleId){
		String sql = "update orderform set people_id='"+PeopleId+"' where orderform_id='"+orderformId+"'";
		super.update(sql, null);
	}
	/**
	 * 获取当天已配送订单
	 * */
	public List<DoneOrder> getTodayDoneOrderList(int pageCount){
		List<DoneOrder> doneorderlist = new ArrayList<DoneOrder>();
		SimpleDateFormat f =  new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		TimeOperate to = new TimeOperate();
		Date date = new Date();
		String beforetime = to.changeTimeFormat(date);
		String aftertime = to.addDaysbyNum(date, 1);
		String sql="select o.orderform_id, u.user_name, i.name, i.link_phone, i.frontaddress, o.order_time, o.send_time, s.name from orderform o, userinfo i, user u, sendpeople s where s.people_id=o.people_id and u.user_id=o.user_id and i.userinfo_id=o.userinfo_id and o.send_time>='"+beforetime+"' and o.send_time<='"+aftertime+"'"+" limit "+2*pageCount+","+2;
		ResultSet rs = super.query(sql, null);
		try {
			while(rs.next()){
				DoneOrder d = new DoneOrder();
				d.setOrderformId(rs.getInt(1));
				d.setUserName(rs.getString(2));
				d.setName(rs.getString(3));
				d.setLinkPhone(rs.getString(4));
				d.setFrontAddress(rs.getString(5));
				date = rs.getTimestamp(6);
				d.setOrderTime(f.format(date));
				date = rs.getTimestamp(7);
				d.setSendTime(f.format(date));
				d.setSendName(rs.getString(8));
				doneorderlist.add(d);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return doneorderlist;
	}
	/**
	 * 查询全部历史订单
	 * */
	public List<DoneOrder> getAllDoneOrderlist(int pageCount){
		List<DoneOrder> doneorderlist = new ArrayList<DoneOrder>();
		SimpleDateFormat f =  new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		Date date = new Date();
		String sql="select o.orderform_id, u.user_name, i.name, i.link_phone, i.frontaddress, o.order_time, o.send_time, s.name from orderform o, userinfo i, user u, sendpeople s where s.people_id=o.people_id and u.user_id=o.user_id and i.userinfo_id=o.userinfo_id"+" limit "+2*pageCount+","+2;
		ResultSet rs = super.query(sql, null);
		try {
			while(rs.next()){
				DoneOrder d = new DoneOrder();
				d.setOrderformId(rs.getInt(1));
				d.setUserName(rs.getString(2));
				d.setName(rs.getString(3));
				d.setLinkPhone(rs.getString(4));
				d.setFrontAddress(rs.getString(5));
				date = rs.getTimestamp(6);
				d.setOrderTime(f.format(date));
				date = rs.getTimestamp(7);
				d.setSendTime(f.format(date));
				d.setSendName(rs.getString(8));
				doneorderlist.add(d);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return doneorderlist;
	}
	/***
	 * 获取任意一天的历史订单信息
	 * */
	public List<DoneOrder> getAnydayDoneOrderList(int num, int pageCount){
		List<DoneOrder> doneorderlist = new ArrayList<DoneOrder>();
		SimpleDateFormat f =  new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		TimeOperate to = new TimeOperate();
		Date date = new Date();
		String beforetime = to.reduceDaysbyNum(date, num);
		String aftertime = to.reduceDaysbyNum(date, num-1);
		String sql="select o.orderform_id, u.user_name, i.name, i.link_phone, i.frontaddress, o.order_time, o.send_time, s.name from orderform o, userinfo i, user u, sendpeople s where s.people_id=o.people_id and u.user_id=o.user_id and i.userinfo_id=o.userinfo_id and o.send_time>='"+beforetime+"' and o.send_time<='"+aftertime+"'"+" limit "+2*pageCount+","+2;
		ResultSet rs = super.query(sql, null);
		try {
			while(rs.next()){
				DoneOrder d = new DoneOrder();
				d.setOrderformId(rs.getInt(1));
				d.setUserName(rs.getString(2));
				d.setName(rs.getString(3));
				d.setLinkPhone(rs.getString(4));
				d.setFrontAddress(rs.getString(5));
				date = rs.getTimestamp(6);
				d.setOrderTime(f.format(date));
				date = rs.getTimestamp(7);
				d.setSendTime(f.format(date));
				d.setSendName(rs.getString(8));
				doneorderlist.add(d);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return doneorderlist;
	}
	/**
	 * 根据用户名查询历史订单
	 * */
	public List<DoneOrder> getDoneOrderListbyUserName(String userName, int pageCount){
		List<DoneOrder> doneorderlist = new ArrayList<DoneOrder>();
		SimpleDateFormat f =  new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		Date date = new Date();
		String sql ="select o.orderform_id, u.user_name, i.name, i.link_phone, i.frontaddress, o.order_time, o.send_time, s.name from orderform o, userinfo i, user u, sendpeople s where s.people_id=o.people_id and u.user_id=o.user_id and i.userinfo_id=o.userinfo_id and u.user_name='"+userName+"'"+" limit "+2*pageCount+","+2;
		ResultSet rs = super.query(sql, null);
		try {
			while(rs.next()){
				DoneOrder d = new DoneOrder();
				d.setOrderformId(rs.getInt(1));
				d.setUserName(rs.getString(2));
				d.setName(rs.getString(3));
				d.setLinkPhone(rs.getString(4));
				d.setFrontAddress(rs.getString(5));
				date = rs.getTimestamp(6);
				d.setOrderTime(f.format(date));
				date = rs.getTimestamp(7);
				d.setSendTime(f.format(date));
				d.setSendName(rs.getString(8));
				doneorderlist.add(d);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return doneorderlist;
	}
	/**
	 * 得到已确认订单列表的页数(已在做，但还没有选择配送员)
	 * */
	public int getOrderMessagePageNum(){
		Date d = new Date();
		TimeOperate to = new TimeOperate();
		String beforetime = to.changeTimeFormat(d);
		String aftertime = to.addDaysbyNum(d, 1);
		String sql="select count(0) from orderform o, userinfo u where u.userinfo_id=o.userinfo_id and isdeal=1 and people_id='0' and o.send_time>='"+beforetime+"' and o.send_time<='"+aftertime+"'";
		ResultSet rs = super.query(sql, null);
		int Count = 0;
		int pageNum = 0;
		try {
			while(rs.next()){
				Count = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		pageNum = Count/2;
		float i =(float)Count/2;
		if(i>pageNum){
			pageNum = pageNum+1;
		}
		return pageNum;
	}
	/**
	 * 获取当天已配送订单的页数
	 * */
	public int getTodayDoneOrderPageNum(){
		Date d = new Date();
		TimeOperate to = new TimeOperate();
		String beforetime = to.changeTimeFormat(d);
		String aftertime = to.addDaysbyNum(d, 1);
		String sql="select count(0) from orderform o, userinfo i, user u, sendpeople s where s.people_id=o.people_id and u.user_id=o.user_id and i.userinfo_id=o.userinfo_id and o.send_time>='"+beforetime+"' and o.send_time<='"+aftertime+"'";
		ResultSet rs = super.query(sql, null);
		int Count = 0;
		int pageNum = 0;
		try {
			while(rs.next()){
				Count = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		pageNum = Count/2;
		float i =(float)Count/2;
		if(i>pageNum){
			pageNum = pageNum+1;
		}
		return pageNum;
	}
	/**
	 * 获取全部历史订单的页数
	 * */
	public int getAllDoneOrderPageNum(){
		String sql = "select count(0) from orderform o, userinfo i, user u, sendpeople s where s.people_id=o.people_id and u.user_id=o.user_id and i.userinfo_id=o.userinfo_id";
		ResultSet rs = super.query(sql, null);
		int Count = 0;
		int pageNum = 0;
		try {
			while(rs.next()){
				Count = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		pageNum = Count/2;
		float i =(float)Count/2;
		if(i>pageNum){
			pageNum = pageNum+1;
		}
		return pageNum;
	}
	/***
	 * 获取任意一天的历史订单信息的页数
	 * */
	public int getAnydayDoneOrderPageNum(int num){
		TimeOperate to = new TimeOperate();
		Date date = new Date();
		String beforetime = to.reduceDaysbyNum(date, num);
		String aftertime = to.reduceDaysbyNum(date, num-1);
		String sql = "select count(0) from orderform o, userinfo i, user u, sendpeople s where s.people_id=o.people_id and u.user_id=o.user_id and i.userinfo_id=o.userinfo_id and o.send_time>='"+beforetime+"' and o.send_time<='"+aftertime+"'";
		ResultSet rs = super.query(sql, null);
		int Count = 0;
		int pageNum = 0;
		try {
			while(rs.next()){
				Count = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		pageNum = Count/2;
		float i =(float)Count/2;
		if(i>pageNum){
			pageNum = pageNum+1;
		}
		return pageNum;
	}
	/**
	 * 根据用户名查询历史订单的页数
	 * */
	public int getDoneOrderListbyUserNamePageNum(String userName){
		String sql = "select count(0) from orderform o, userinfo i, user u, sendpeople s where s.people_id=o.people_id and u.user_id=o.user_id and i.userinfo_id=o.userinfo_id and u.user_name='"+userName+"'";
		ResultSet rs = super.query(sql, null);
		int Count = 0;
		int pageNum = 0;
		try {
			while(rs.next()){
				Count = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		pageNum = Count/2;
		float i =(float)Count/2;
		if(i>pageNum){
			pageNum = pageNum+1;
		}
		return pageNum;
	}
}