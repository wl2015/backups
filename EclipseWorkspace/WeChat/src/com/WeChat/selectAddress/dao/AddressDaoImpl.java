package com.WeChat.selectAddress.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.WeChat.db.BaseDao;
import com.WeChat.entity.FrontAddress;
import com.WeChat.entity.UserInfo;

public class AddressDaoImpl extends BaseDao implements AddressDao{


	
	/**]
	 * 查询所有地址
	 * @return
	 */

	public List<FrontAddress> getAllAddress() {

		ResultSet rs = super.query("select * from frontaddress",null);
		List<FrontAddress> list = new ArrayList<FrontAddress>();
		try {
			while(rs.next()){
				FrontAddress address = new FrontAddress();
				address.setFrontaddress_id(rs.getInt(1));
				address.setFront_address(rs.getString(2));
				
				list.add(address);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.close();
		}
		return list;
	}

	/**
	 * 关键字查询地址
	 */
	public List<FrontAddress> getKeyAddress(String keyword) {
		ResultSet rs = super.query("select * from frontaddress where front_address like ?",new Object[]{"%"+keyword+"%"});
		List<FrontAddress> list = new ArrayList<FrontAddress>();
		try {
			while(rs.next()){
				FrontAddress address = new FrontAddress();
				address.setFrontaddress_id(rs.getInt(1));
				address.setFront_address(rs.getString(2));
				
				list.add(address);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.close();
		}
		return list;
	}

	/**
	 * 根据ID查询地址
	 */
	public FrontAddress getById(int addressId) {
		
		ResultSet rs = super.query("select * from frontAddress where frontaddress_id = ?",new Object[]{addressId});
		FrontAddress f = null;
		try {
			if(rs.next()){
				f = new FrontAddress();
				f.setFrontaddress_id(rs.getInt(1));
				f.setFront_address(rs.getString(2));
			}
			
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}finally{
			super.close();
		}
		return f;
	}

	
	/**
	 * 保存用户地址信息
	 */
	public int save(UserInfo user) {
		int rows = 0;
		try {
			super.update("insert into userinfo values(null,?,?,?,?,?,?)", new Object[]{
					user.getFrontaddress(),
					user.getAdd_address(),
					user.getName(),
					user.getSex(),
					user.getLink_phone(),
					user.getUser_id()
					
			});
		}finally{
			super.close();
		}
		
		return rows;
	}

	
	/**
	 * 查询用户地址信息
	 */
	public UserInfo getUserInfo(int userinfoId) {
		ResultSet rs = super.query("select * from userinfo where userinfo_id =?",new Object[]{userinfoId}); 
		UserInfo userinfo = new UserInfo();
		try {
			while(rs.next()){
				userinfo.setUserinfo_id(rs.getInt(1));
				userinfo.setFrontaddress(rs.getString(2));
				userinfo.setAdd_address(rs.getString(3));
				userinfo.setName(rs.getString(4));
				userinfo.setSex(rs.getInt(5));
				userinfo.setLink_phone(rs.getString(6));
				userinfo.setUser_id(rs.getInt(7));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.close();
		}
		return userinfo;
	}

	/**
	 * 查询某用户全部地址信息
	 */
	public List<UserInfo> getAllUserInfo(int userId) {
		ResultSet rs = super.query("select * from userinfo where user_id = ?",new Object[]{userId}); 
		List<UserInfo> list = new ArrayList<UserInfo>();
		 
		try {
			while(rs.next()){
				UserInfo userinfo = new UserInfo();
				userinfo.setUserinfo_id(rs.getInt(1));
				userinfo.setFrontaddress(rs.getString(2));
				userinfo.setAdd_address(rs.getString(3));
				
				userinfo.setName(rs.getString(4));
				userinfo.setSex(rs.getInt(5));
				userinfo.setLink_phone(rs.getString(6));
				userinfo.setUser_id(rs.getInt(7));
				
				list.add(userinfo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.close();
			
		}
		return list;
	}

	/**
	 * 删除用户某个地址信息
	 */
	public int delete(int userInfo_id) {
		int  rows = super.update("delete from userinfo where userInfo_id = ?"
				,new Object[]{
						userInfo_id
				});
		
		super.close();
		
		return rows;
	}

	
	/**
	 * 修改用户某个地址信息
	 */
	public int update(UserInfo userinfo) {
		int  rows = super.update("update userinfo set frontaddress = ? , add_address = ? , name = ? , sex = ? ,link_phone = ? " +
				"where userinfo_id = ?"
				,new Object[]{
				
					userinfo.getFrontaddress(),
					userinfo.getAdd_address(),
					userinfo.getName(),
					userinfo.getSex(),
					userinfo.getLink_phone(),
					userinfo.getUserinfo_id()
				
				});
				
				super.close();
				
				return rows;
	}
	
}