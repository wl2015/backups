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
    
    <title>My JSP 'myOrder1.jsp' starting page</title>
    
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
  
  <body style="Font-size:63%" >


<%-- <div class="head">
		<div class="float_left pt-1"><img src="img/home-page.png" style="width:7em"/></div>
		<div class="float_left color-2 font-s-3 pl-1">我的订单</div>
		<!-- <div class="float_left color-1 font-s-3 pl-1" onclick="location.replace('MyValueAction_NotDiscounted')">优惠券</div> -->
		<div class="float_left color-1 font-s-3 pl-1" onclick="location.replace('IntegralAction_MyIntegral')">积分</div>
		<div class="float_left color-1 font-s-3 pl-1" onclick="location.replace('UserInfoAction_UserInfo')">我的资料</div>
</div>

<div class="body">
<center>
	<div class="pt-4 pb-1">
	<input type="button" value="开始点餐" name="" class="button" onclick="location.replace('List/menuList.jsp')"/>
	<input type="button" value="历史订单" name="" class="button2" onclick="location.replace('MyListAction_isNotdealList')"/>
</div>
<div class="border0"></div>
<div class="body1">

<div class="body3 font-s-3"><%=new Date() %></div>
</div>
<div class="border0"></div>
<div class="body11">
<div class="body2 font-s-3">食品名称</div>
<div class="body3 font-s-3">应付款</div>
<div class="body3 font-s-3">送餐费</div>
<div class="body3 font-s-3">数量</div>
<div class="body3 font-s-3">价格</div>

</div>

<div class="border1"></div>
<c:forEach var="o" items="${requestScope.orderform }" varStatus="status">	
<br></br>
<div class="body11">
	
<div class="body2 font-s-2">${o.dishName}</div>
<!-- <div class="body3 font-s-2"></div> -->
<div class="body3 font-s-2">${o.total }元</div>
<div class="body3 font-s-2">${o.sendFee }元</div>
<div class="body3 font-s-2">${o.dishNum }</div>
<div class="body3 font-s-2">${o.price}元</div>
</div>

<div class="border0"></div>
<div class="body111">
<div class="body2 font-s-2">送餐时间：</div>
<div class="body22 font-s-2">${o.sendTime }</div>

</div>
	
<div class="body111">
<div class="body2 font-s-2">送餐地址：</div>
<div class="body22 font-s-2">${o.frontAddress }</div>
</div>
<div class="body111">
<div class="body2 font-s-2">联系电话：</div>
<div class="body22 font-s-2">${o.userName }</div>
</div>
	
<div class="body111">
<div class="body2 font-s-2">支付方式：</div>
<div class="body22 font-s-2">${o.isdeal == 0 ? '现金支付': '在线支付' }</div>
</div>
<div class="body111">
<div class="body2 font-s-2">订单状态：</div>
<div class="body22 font-s-2">已确认</div>
</div>
</c:forEach>
<div class="body111"></div>	
<div class="body111"></div>	
<div class="border0"></div>
<div class="body111"></div>
	
</center>		
	<div style="clear:both"></div>
</div>
<div class="border"></div>
<div style="height:5em"></div>
<div>
<iframe src="footer.jsp" width="100%" height="200px" frameborder="0" scrolling=no ></iframe>
</div> --%>
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
		<c:forEach var="o" items="${requestScope.orderform}" varStatus="status">
		
			<div><div class="float-1 color-1">送餐时间:</div>
				<div class="float-1 color-1" style="width:70%">${o.sendTime }</div>
			</div>
			<div style="clear:both"></div>
			<div><div class="float-1 color-1">送餐地址:</div>
				<div class="float-1 color-1" style="width:70%">${o.frontAddress }</div>
			</div>
			<div style="clear:both"></div>
			<div><div class="float-1 color-1">联系电话:</div>
				<div class="float-1 color-1" style="width:70%">${o.linkPhone }</div>
			</div>
			<div style="clear:both"></div>
			<div><div class="float-1 color-1">支付方式:</div>
				<div class="float-1 color-1" style="width:70%">${o.isdeal == 0 ? '现金支付': '在线支付' }</div>
			</div>
			<div style="clear:both"></div>
			<div><div class="float-1 color-1">订单状态:</div>
				<div class="float-1 color-1" style="width:70%">未确认</div>
			</div>
			</c:forEach>
		</div>
		
		<c:forEach var="o" items="${requestScope.orderform}" varStatus="status">
		<table bgcolor="#FAFAFA" class="font-s-3 color-14" align="left" width="86%" style="margin-left:7%">
			<tr style="font-weight:bold">
				<td>食物名称</td>
				<td>价格</td>
				<td>数量</td>
				<td>合计</td>
			</tr>
			<tr>
				<td>${o.dishName}</td>
				<td>${o.price}元</td>
				<td>${o.dishNum }</td>
				<td>${o.total }元</td>
			</tr>
			<tr>
				<td colspan="3">送餐费</td>
				<td colspan="3">${o.sendFee }元</td>
			</tr>
			<tr>
				<td colspan="3">应付款</td>
				<td colspan="3" class="font-s-3 color-15">${o.total }元</td>
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
