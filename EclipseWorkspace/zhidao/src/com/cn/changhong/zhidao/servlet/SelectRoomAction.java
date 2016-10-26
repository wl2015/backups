package com.cn.changhong.zhidao.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import com.cn.changhong.zhidao.test.Dao;

@WebServlet("/SelectRoomAction")
public class SelectRoomAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
   /*�����doget��ʽ���һ�㣬���Է�������*/

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String city = request.getParameter("city");
		JSONArray jsonArray=Dao.selectBoardroomListByCity(city);
		 PrintWriter out = response.getWriter();  
	     out.write(jsonArray.toString());  
	}

}
