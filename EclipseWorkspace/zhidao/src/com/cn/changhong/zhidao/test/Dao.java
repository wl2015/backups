package com.cn.changhong.zhidao.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.cn.changhong.zhidao.util.ObjectTypeCity;
import com.cn.changhong.zhidao.util.ObjectTypeRoom;
import com.cn.changhong.zhidao.util.ObjectTypeRoomData;
import com.cn.changhong.zhidao.util.TimeConvert;
import com.cn.changhong.zhidao.util.Util;

public class Dao {
	/*��ѯuser���� �����û�*/
	public static Connection conn = Util.getConnection();
	
	public static void setectUser()
	{
		try{
		Statement st= conn.createStatement();
		ResultSet res= st.executeQuery("select * from d_user");
		while(res.next())
		{
			for (int i=1;i<7;i++)
			{
				System.out.print(res.getString(i)+"  ");
			}
			System.out.println();
		}
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	/*��ݳ������ѯ�������ҵ��б�*/
	
	public static JSONArray selectBoardroomListByCity(String city)
	{
		ArrayList<ObjectTypeCity> objectList = new ArrayList<ObjectTypeCity>();
		 JSONArray jsonArray= null;
		String sql="select room_id,name from d_boardroom where city =?";
		try {
			PreparedStatement prst= conn.prepareStatement(sql);
			prst.setString(1, city);
			ResultSet rs= prst.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+ "   "+rs.getString(2));
				ObjectTypeCity tempCity= new ObjectTypeCity(rs.getInt(1),rs.getString(2));
				objectList.add(tempCity);	
			}
			
			JSONObject jsonObject = new JSONObject();  
			 jsonObject.put("rooms", objectList); 
			 jsonArray = new JSONArray();  
			 jsonArray.add(jsonObject); 
			 
//			 System.out.println(jsonArray);
//		     PrintWriter out = response.getWriter();  
//		     out.write(jsonArray.toString());  
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray;
		
	}

	public static void selectBordroomDetailById(int room_id)
	{
		ArrayList<ObjectTypeRoom> roomlist = new ArrayList<ObjectTypeRoom>();
		String sql = "select * from d_boardroom where room_id=?";
		try {
			PreparedStatement prst= conn.prepareStatement(sql);
			prst.setInt(1, room_id);
			ResultSet rs= prst.executeQuery();
			while(rs.next())
			{
				String name = rs.getString(2);
				String address = rs.getString(3);
				String type = rs.getString(4);
				ArrayList<ObjectTypeRoomData> tempdata = selectBoardroomuseById(room_id);
				ObjectTypeRoom temproomdata = new ObjectTypeRoom(name, address, type, tempdata);
				roomlist.add(temproomdata);
			}
			
			JSONObject jsonObject = new JSONObject();  
			 jsonObject.put("rooms", roomlist); 
			 JSONArray jsonArray = new JSONArray();  
			 jsonArray.add(jsonObject);  
			 System.out.println(jsonArray);
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	public static ArrayList<ObjectTypeRoomData> selectBoardroomuseById(int room_id)
	{
		ArrayList<ObjectTypeRoomData> roomlist = new ArrayList<ObjectTypeRoomData>();
		String sql = "select * from d_boardroom_use where room_id=?";
		try {
			PreparedStatement prst= conn.prepareStatement(sql);
			prst.setInt(1, room_id);
			ResultSet rs= prst.executeQuery();
			while(rs.next())
			{
				int user_id = rs.getInt(3);
				String userName = selectUserNameByID(user_id);
				
				
				Timestamp starttime = rs.getTimestamp(4);
				Timestamp endtime = rs.getTimestamp(5);
				String[] times = TimeConvert.convertMinutes(starttime, endtime);
				
				
				
				ObjectTypeRoomData tempdata= new ObjectTypeRoomData(times, userName);
				roomlist.add(tempdata);
				
			}
		}catch(Exception e)
		{
			e.printStackTrace( );
		}
		return roomlist;
	}
	
	public static String selectUserNameByID(int user_id) throws Exception
	{
		String sql = "select name from d_user where user_id=?";
		PreparedStatement prst= conn.prepareStatement(sql);
		prst.setInt(1, user_id);
		ResultSet rs= prst.executeQuery();
		while(rs.next())
		{
			return rs.getString(1);
			
		}
		return null;
	}
}
