package com.WeChat.mymanshao.mydata.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.WeChat.db.BaseDao;
import com.WeChat.entity.User;

public class UserInfoDaoImp extends BaseDao implements UserInfoDao{
	
	/**
	 * 查找用户信息
	 */
	public List<User> getUserInfo(int userId){
		
		String sql = "SELECT u.user_id, u.user_name, u.email, u.regist_time, f.name FROM user u, userinfo f WHERE u.user_id=f.userinfo_id and u.user_id=?"; 
		List<User> list = new ArrayList<User>();
		ResultSet rs = super.query(sql, new Object[]{userId});
		
		try {
				while(rs.next()){
					User user = new User();
					user.setUser_id(rs.getInt("user_id"));
					user.setUser_name(rs.getString("user_name"));
					user.setEmail(rs.getString("email"));
					user.setRegist_time(rs.getLong("regist_time"));
					user.setName(rs.getString("name"));
					list.add(user);
					
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
	 * 修改用户邮箱
	 */

	public int update(User u) {
		// TODO Auto-generated method stub
		int row = super.update("update user set email=? where user_id=?",
				new Object[]{
					u.getEmail(),
					u.getUser_id(),	
				
				});
		
			super.close();
			return row;
	}

	
	/**
	 * 保存修改后的邮箱
	 */
	public int save(User u) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	/**
	 * 查找用户邮箱
	 */
	public User getById(int userId) {
		ResultSet rs = super.query("select email from user where user_id=?",
				new Object[]{userId});
		User u = null;	
		try {
			if(rs.next()){
					u = new User();
					u.setUser_id(userId);
					u.setEmail(rs.getString("email"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				super.close();
			}
		return u;
	}


	
	
	/**
	 * 修改用户密码
	 */
	public int updatepass(User u) {
		// TODO Auto-generated method stub
		
		int row = super.update("update user set password=? where user_id=?",
				new Object[]{
					u.getPassword(),
					u.getUser_id(),	
				
				});
		
			super.close(); 
			return row;
	}

	/**
	 * 保存修改后的密码
	 */
	public int savepass(User u) {
		// TODO Auto-generated method stub
		return 0;
	}
	

	/**
	 * 查找用户密码
	 */
	public User getByIdPass(int userId) {
		// TODO Auto-generated method stub
		ResultSet rs = super.query("select password from user where user_id=?",
				new Object[]{userId});
		User uu = null;	
		try {
			if(rs.next()){
					uu = new User();
					uu.setUser_id(userId);
					uu.setPassword(rs.getString("password"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				super.close();
			}
		return uu;
	}
	
	
	/**
	 * 查找用户名
	 */
	public User getByIdPhone(int userId){
		
		ResultSet rs = super.query("select user_name from user where user_id=?",
				new Object[]{userId});
		User uu = null;	
		try {
			if(rs.next()){
					uu = new User();
					uu.setUser_name(rs.getString("user_name"));
					uu.setUser_id(userId);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				super.close();
			}
		
		return uu;
	}
	
	
	/**
	 * 修改用户名
	 */
	public int updatephone(User u){
		
		int row = super.update("update user set user_name=? where user_id=?",
				new Object[]{
					u.getUser_name(),
					u.getUser_id(),	
				
				});
		
			super.close(); 
		
		return row;
	}
	
	
	/**
	 * 保存用户名
	 */
	
	public int savephone(User u){
		return 0;
		
	}


}

