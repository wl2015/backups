package com.WeChat.mymanshao.backtologin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.WeChat.db.BaseDao;
import com.WeChat.entity.User;

public class LoginDaoImp extends BaseDao implements LoginDao{

	/**
	 * 查找用户名和密码
	 */
	public User isLogin(User u){
		User loginuser=null;
		ResultSet rs = super.query("select * from user where user_name=? and password=?", 
				new Object[]{
				
				u.getUser_name(),
				u.getPassword(),
		});
			try {
				if(rs.next()){
					loginuser =	new User();
					loginuser.setUser_name(rs.getString("user_name"));
					loginuser.setPassword(rs.getString("password"));
					loginuser.setUser_id(rs.getInt("user_id"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				super.close();
			}
		
		return loginuser;
	}

}

