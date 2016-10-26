<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.WeChat.admin.timeout.dao.TimeOutDaoImp" %>
<%@ page import="com.WeChat.entity.OrderForm" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String order = request.getParameter("orderformId");
	TimeOutDaoImp orderdao = new TimeOutDaoImp();
	OrderForm orderforminfo = orderdao.getTimeOutInfoById(order);

%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'detailNOS.jsp' starting page</title>
    
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
			<div style="float:left">已过期订单-详情</div>
			<div style="float:right">
				<a href="TimeOutAction_execute?pageNum=<%=Integer.parseInt(request.getParameter("pageNum"))%>">
					<input type="button" value="返回" class="border2" />
				</a>
			</div>
			</td>
		</tr>
		<tr>
			<td class="td3">订单号:</td>
			<td class="td4"><%=orderforminfo.getOrderformId() %></td>
		</tr>
		<tr>
			<td class="td3">食客姓名:</td>
			<td class="td4"><%=orderforminfo.getName() %></td>
		</tr>
		<tr>
			<td class="td3">联系方式:</td>
			<td class="td4"><%=orderforminfo.getLinkPhone() %></td>
		</tr>
		<tr>
			<td class="td3">详细地址:</td>
			<td class="td4"><%=orderforminfo.getAddAddress() %></td>
		</tr>
		<tr>
			<td class="td3">生成时间:</td>
			<td class="td4"><%=orderforminfo.getOrderTime() %></td>
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
			<td><%=orderforminfo.getDishName() %></td>
			<td><%=orderforminfo.getDishNum() %></td>
			<td><%=orderforminfo.getDishTaste() %></td>
			<td><%=orderforminfo.getPrice() %></td>
		</tr>
		
		<tr>
			<td class="td6">送餐费（元）：</td>
			<td colspan="3" class="td6"><%=orderforminfo.getSendFee() %></td>
		</tr>
		<tr>
			<td class="td6">总计（元）：</td>
			<td colspan="3" class="td6"><%=orderforminfo.getTotal() %></td>
		</tr>
		<tr>
			<td class="td6">是否已支付：</td>
			<td colspan="3" class="td6"><%=orderforminfo.getIsPay()==1 ? "已支付":"未支付" %></td>
		</tr>
	</table>
	</body>
</html>
