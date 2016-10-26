package com.WeChat.admin.adLogin.dao;


import com.WeChat.admin.backEntity.Admin;
import com.WeChat.db.BaseDao;
import com.WeChat.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDaoImpl  extends BaseDao implements AdminDao {
	/***
	 * 管理员登录
	 * @return
	 */

	public Admin isLogined(Admin a) {
		ResultSet rs = super.query("select * from admin where admin_name = ? and password = ? ",new Object[]{
				a.getAdmin_name(),a.getPassword()	
		});
		System.out.println(a.getAdmin_name()+a.getPassword()	+"................admindaoimpl");
		try {
			if(rs.next()){
				Admin aa = new Admin();
				aa.setAdmin_id(rs.getInt("admin_id"));
				aa.setAdmin_name(rs.getString("admin_name"));
				aa.setPassword(rs.getString("password"));
				aa.setNickName(rs.getString("nickName"));
				System.out.println(a.getAdmin_name()+a.getPassword()	+"................admindaoimpl");
				return aa;
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
