package com.WeChat.admin.Frontaddress.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.WeChat.db.BaseDao;
import com.WeChat.entity.FrontAddress;

public class AddressDaoImpl extends BaseDao implements AddressDao{
	/**
	 * 锟斤拷取全锟斤拷指锟斤拷锟斤拷址
	 * */
	public List<FrontAddress> getFrontAddresslist(int pageCount){
		List<FrontAddress> frontaddresslist = new ArrayList<FrontAddress>();
		String sql = "select frontaddress_id, front_address from frontaddress"+" limit "+10*pageCount+","+10;;
		ResultSet rs = super.query(sql, null);
		try {
			while(rs.next()){
				FrontAddress f = new FrontAddress();
				f.setFrontaddress_id(rs.getInt(1));
				f.setFront_address(rs.getString(2));
				frontaddresslist.add(f);
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return frontaddresslist;
	}
	/**
	 * 锟睫革拷指锟斤拷锟斤拷址
	 * */
	public void updateFrontAddress(int frontaddressId, String frontaddress){
		String sql="update frontaddress set front_address=? where frontaddress_id=?";
		super.update(sql, new Object[]{
				frontaddress,
				frontaddressId
		});
		super.close();
	}
	/**
	 * 锟斤拷锟街革拷锟斤拷锟街�
	 * */
	public void addFrontAddress(String frontaddress){
		String sql="insert into frontaddress values(0,?)";
		super.update(sql, new Object[]{
				frontaddress
		});
		super.close();
	}
	/**
	 * 删锟斤拷指锟斤拷锟斤拷址
	 * */
	public void deleteFrontAddressById(int frontaddressId){
		String sql="delete from frontaddress where frontaddress_id='"+frontaddressId+"'";
		super.update(sql, null);
		super.close();
	}
	/**
	 * 模锟斤拷锟窖革拷锟斤拷锟街�
	 * */
	public List<FrontAddress> getFrontAddressesByKeyword(String keyword, int pageCount){
		List<FrontAddress> frontAddresslist = new ArrayList<FrontAddress>();
		String sql = "select frontaddress_id, front_address from frontaddress where front_address like ?"+" limit "+10*pageCount+","+10;;
		ResultSet rs = super.query(sql, new Object[]{
			"%"+keyword+"%"
		});
		try {
			while(rs.next()){
				FrontAddress f = new FrontAddress();
				f.setFrontaddress_id(rs.getInt(1));
				f.setFront_address(rs.getString(2));
				frontAddresslist.add(f);
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return frontAddresslist;
	}
	/**
	 * 锟斤拷取全锟斤拷指锟斤拷锟斤拷址锟斤拷页锟斤拷
	 * */
	public int getFrontAddressPageNum(){
		int Count = 0;
		int pageNum = 0;
		String sql="select count(0) from frontaddress";
		ResultSet rs = super.query(sql, null);
		try {
			while(rs.next()){
				Count=rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		pageNum = Count/10;
		float i =(float)Count/10;
		if(i>pageNum){
			pageNum = pageNum+1;
		}
		return pageNum;
	}
	/**
	 * 锟斤拷取模锟斤拷锟窖革拷锟斤拷锟街凤拷锟揭筹拷锟�
	 * */
	public int getFrontAddressesByKeywordPageNum(String keyword){
		int Count = 0;
		int pageNum = 0;
		String sql="select count(0) from frontaddress where front_address like ?";
		ResultSet rs = super.query(sql, new Object[]{
				"%"+keyword+"%"
			});
		try {
			while(rs.next()){
				Count=rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		pageNum = Count/10;
		float i =(float)Count/10;
		if(i>pageNum){
			pageNum = pageNum+1;
		}
		return pageNum;
	}
}
