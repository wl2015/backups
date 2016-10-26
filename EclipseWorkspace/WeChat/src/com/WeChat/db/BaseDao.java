package com.WeChat.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库访问类
 * @author Mical
 *
 */
public class BaseDao {

	private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	
	private static final String URL = "jdbc:mysql://localhost:3306/weichat";
	
	private static final String USERNAME = "root";
	
	private static final String PASSWORD = "root";
	
	private Connection conn;
	
	private PreparedStatement pst;
	
	private ResultSet rs;
	
	
	//静态块
	//第一步 加载驱动
	static{
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//第二步 获取连接
	protected Connection getConn() throws SQLException{
		
		conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		return conn;
	}
	
	//第三步 （增，删，改）
	public int update(String sql,Object[] param){
		int rows = 0 ;
		try {
			conn = getConn();
			PreparedStatement pst = conn.prepareStatement(sql);
			for(int i = 0 ; (param != null && param.length > 0)&& i < param.length ; i++){
				pst.setObject(i+1, param[i]);
			}
			rows = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;
	}
	
	//第三步 （查询）
	public ResultSet query(String sql,Object[] param){
		
		try {
			conn = getConn();
			PreparedStatement pst = conn.prepareStatement(sql);
			for(int i = 0 ; (param != null && param.length > 0)&& i < param.length ; i++){
				pst.setObject(i+1, param[i]);
			}
			rs = pst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	//第四步 关闭释放资源
	public void close(){
		
		try {
			if(rs != null)
				rs.close();
			if(pst != null)
				pst.close();
			if(conn != null || !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
