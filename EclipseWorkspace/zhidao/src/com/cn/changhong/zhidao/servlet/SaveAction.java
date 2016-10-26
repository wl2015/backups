package com.cn.changhong.zhidao.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

@WebServlet("/SaveAction")
public class SaveAction extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		System.out.println(request.getParameter("text"));
	
		System.out.println("doGet method");
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");  
        PrintWriter pw = response.getWriter();  
        request.setCharacterEncoding("utf-8");  
        String param1=request.getParameter("name");  
        String param2=request.getParameter("age");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", param1);
        jsonObject.put("age", param2);
        List<Object> list = new ArrayList<Object>();
//        list.add(param1);
//        list.add(param2);
        Map<String, Object> map = new HashMap<String, Object>();
//        pw.print("前台传来了参数：name="+param1+"，age="+param2);
        map.put("name", param1);
        map.put("age", param2);
        list.add(map);
        list.add(map);
        pw.print(jsonObject);
        pw.flush();  
        pw.close();  
	}
	
}
