package com.cn.changhong.zhidao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/* Database  connection method  */
public class Util {
	
	public static  String dbClassName = "com.mysql.jdbc.Driver";
	public static String dbUrl = "jdbc:mysql://10.3.55.148:3306/mydev";
	public static String dbUser="root";
	public static String dbPsd="changhong";
	/* ��ȡ���Դ����*/
	public static Connection getConnection()
	{
		Connection conn = null;
		try{
		Class.forName(dbClassName);
		conn = DriverManager.getConnection(dbUrl,dbUser,dbPsd);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return conn;
	}

	
	
	
}
