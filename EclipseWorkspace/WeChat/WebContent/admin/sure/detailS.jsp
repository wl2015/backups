<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ page import="com.WeChat.admin.sured.dao.*" %>
<%@ page import="com.WeChat.entity.OrderForm" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			String pageNum2 = request.getParameter("pageNum");
			String sureddetail = request.getParameter("orderformId");
			SuredDaoImp sured = new SuredDaoImp();
			OrderForm suredinfo = sured.getSuredInfoById(sureddetail);
			int pageNum=Integer.parseInt(pageNum2);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'detailS.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/right.css">

  </head>
  
  <body>
	<table width="100%" cellspacing="2" cellpadding="4" bgcolor="#fbead0">
		<tr>
			<td colspan="15" class="header">
			<div style="float:left">已确认订单-详情</div>
			<div style="float:right">
			<a href="SuredAction_getTakedOrderForm?pageNum=<%= pageNum %>">
				<input type="button" value="返回" class="border2"/>
			</a></div>
			</td>
		</tr>
		<tr>
			<td class="td3">订单号:</td>
			<td class="td4"><%=suredinfo.getOrderformId() %></td>
		</tr>
		<tr>
			<td class="td3">食客姓名:</td>
			<td class="td4"><%=suredinfo.getName() %></td>
		</tr>
		<tr>
			<td class="td3">联系方式:</td>
			<td class="td4"><%=suredinfo.getLinkPhone() %></td>
		</tr>
		<tr>
			<td class="td3">详细地址:</td>
			<td class="td4"><%=suredinfo.getAddAddress() %></td>
		</tr>
		<tr>
			<td class="td3">生成时间:</td>
			<td class="td4"><%=suredinfo.getOrderTime() %></td>
		</tr>
	</table>
	<table  width="100%" cellspacing="2" cellpadding="4">
		<tr class="header" align="center">
			<td style="width:30%;">菜品名</td>
			<td>数量(份)</td>
			<td>口味</td>
			<td>单价(元)</td>
		</tr>
		
		<tr class="td5">
			<td><%=suredinfo.getDishName() %></td>
			<td><%=suredinfo.getDishNum() %></td>
			<td><%=suredinfo.getDishTaste() %></td>
			<td><%=suredinfo.getPrice() %></td>
		</tr>
		<tr>
			<td class="td6">送餐费（元）：</td>
			<td colspan="3" class="td6"><%=suredinfo.getSendFee() %></td>
		</tr>
		<tr>
			<td class="td6">总计（元）：</td>
			<td colspan="3" class="td6"><%=suredinfo.getTotal() %></td>
		</tr>
		<tr>
			<td class="td6">是否已支付：</td>
			<td colspan="3" class="td6"><%=suredinfo.getIsDeal() %></td>
		</tr>
	</table>
	</body>
</html>
