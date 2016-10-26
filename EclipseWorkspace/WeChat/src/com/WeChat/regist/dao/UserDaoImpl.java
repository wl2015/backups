package com.WeChat.regist.dao;


import com.WeChat.db.BaseDao;
import com.WeChat.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl  extends BaseDao implements UserDao {
	//是否注册成功,成功返回true，否则返回false
	public boolean isRegist(User u){
		ResultSet rs = super.query("select * from user where user_name = ? ",new Object[]{u.getUser_name()});
		try {
			if(rs.next()){
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally{
			 
			super.close();
		}

		int rows = super.update("insert into user values(null,?,?,?,?)",new Object[]{
				u.getUser_name(),
				u.getPassword(),
				u.getUser_name()+"@ms.com",
				System.currentTimeMillis()
		});
		
		super.close();
		if(rows>0){
			return true;
		}
		return false;
	}
}
