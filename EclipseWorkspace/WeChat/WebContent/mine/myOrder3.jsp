<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'myOrder3.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/menuList.css" />
 <link rel="stylesheet" type="text/css" href="css/layout.css" />
 
 
  </head>
  
   <body style="Font-size:63%">


<div class="head">
		<div class="float_left pt-1 shouye"><a href="../order/shouye.html" target="_parent">首页</a></div>
		<div class="float-1 color-2 font-s-3 pl-2 pt-6">订单</div>
		<div class="float-1 color-1 font-s-3 pl-2 pt-6" onclick="location.replace('IntegralAction_MyIntegral')">积分</div>
		<div class="float-1 color-1 font-s-3 pl-2 pt-6" onclick="location.replace('UserInfoAction_UserInfo')">资料</div>
</div>
<div class="body">
<center>
	<div class="pt-4 pb-1">
		<input type="button" value="开始点餐" name="" class="button" onclick="location.replace('List/menuList.jsp')"/>
		<input type="button" value="历史订单" name="" class="button2" onclick="location.replace('MyListAction_isNotdealList')"/>
	</div>

	<div class="body9 box-shadow">
		<div class="border-15" id="Clock"></div>
		
		<div class="margin-6  font-s-3">
		<c:forEach var="no" items="${requestScope.isnot}" varStatus="status">
		
			<div><div class="float-1 color-1">送餐时间:</div>
				<div class="float-1 color-1" style="width:70%">${no.sendTime }</div>
			</div>
			<div style="clear:both"></div>
			<div><div class="float-1 color-1">送餐地址:</div>
				<div class="float-1 color-1" style="width:70%">${no.frontAddress }</div>
			</div>
			<div style="clear:both"></div>
			<div><div class="float-1 color-1">联系电话:</div>
				<div class="float-1 color-1" style="width:70%">${no.linkPhone }</div>
			</div>
			<div style="clear:both"></div>
			<div><div class="float-1 color-1">支付方式:</div>
				<div class="float-1 color-1" style="width:70%">${no.isdeal == 0 ? '现金支付': '在线支付' }</div>
			</div>
			<div style="clear:both"></div>
			<div><div class="float-1 color-1">订单状态:</div>
				<div class="float-1 color-1" style="width:70%">已确认</div>
			</div>
		</c:forEach>
		</div>
		
		<c:forEach var="no" items="${requestScope.isnot }" varStatus="status">
		<table bgcolor="#FAFAFA" class="font-s-3 color-14" align="left" width="86%" style="margin-left:7%">
			<tr style="font-weight:bold">
				<td>食物名称</td>
				<td>价格</td>
				<td>数量</td>
				<td>合计</td>
			</tr>
			<tr>
				<td>${no.dishName}</td>
				<td>${no.price}元</td>
				<td>${no.dishNum }</td>
				<td>${no.total }元</td>
			</tr>
			<tr>
				<td colspan="3">送餐费</td>
				<td colspan="3">${no.sendFee }元</td>
			</tr>
			<tr>
				<td colspan="3">应付款</td>
				<td colspan="3" class="font-s-3 color-15">${no.total }元</td>
			</tr>
		</table>
		</c:forEach>
	</div>
</center>
</div>
<div style="height:2em"></div>
<div class="footer">
	<iframe src="footer.jsp" width="100%" height="200px" frameborder="0" scrolling=no style="margin:0px;padding:0px"></iframe>
</div>
</body>
</html>
