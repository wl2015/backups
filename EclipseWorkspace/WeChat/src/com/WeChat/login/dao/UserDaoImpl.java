package com.WeChat.login.dao;

import com.WeChat.db.BaseDao;
import com.WeChat.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl  extends BaseDao implements UserDao {
	/***
	 * 是否登录成功,成功返回user对象，否则返回空。
	 * @param userName
	 * @param pwd
	 * @return
	 */
	public User isLogined(User u) {
		
		ResultSet rs = super.query("select * from user where user_name = ? and password = ? ",new Object[]{
				u.getUser_name(),
				u.getPassword()
				
		});
		System.out.println(u.getUser_name()+u.getPassword()+"................userdaoimpl");
		try {
			if(rs.next()){
				User uu=new User();
				uu.setEmail(rs.getString("email"));
				uu.setPassword(rs.getString("password"));
				uu.setUser_id(rs.getInt("user_id"));
				uu.setUser_name(rs.getString("user_name"));
				System.out.println(uu.getUser_name()+uu.getPassword()+"...................userdaoimpl");
				return uu;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			 
			super.close();
		}
		return null;
	}
}
