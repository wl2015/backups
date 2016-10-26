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
    
    <title>My JSP 'myGrade.jsp' starting page</title>
    
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
  <body style="Font-size:63%" leftmargin="0">


<%-- <div class="head">
		<div class="float_left pt-1"><img src="img/home-page.png" style="width:7em"/></div>
		<div class="float_left color-1 font-s-3 pl-1" onclick="location.replace('MyListAction_MyListDisplay')">我的订单</div>
		<!-- <div class="float_left color-1 font-s-3 pl-1" onclick="location.replace('MyValueAction_NotDiscounted')">优惠券</div> -->
		<div class="float_left color-2 font-s-3 pl-1" >积分</div>
		<div class="float_left color-1 font-s-3 pl-1" onclick="location.replace('UserInfoAction_UserInfo')">我的资料</div>
		
</div>

<div class="body">
<center> 
<c:forEach var="integral" items="${requestScope.myintegral }" varStatus="status">
	<div class="font-s-4 pt-4">当前积分<font class="color-1 font-s-5"> ${integral.integralCount } </font></div>
</c:forEach>
	<div class="border0"></div>
		<div class="pt-2"><img src="img/jifen.png"/></div>
	<div class="pt-4 pb-2">此券30天内有效 ，已经兑换，不可退换积分</div>
	<div class="pt-4 pb-1">
	<input type="button" value="立即兑换" name="" class="button1" />
		</div>	
</center>		
	<div style="clear:both"></div>
</div>
<div class="border"></div>
<div>
<iframe src="footer.jsp" width="100%" height="200px" frameborder="0" scrolling=no style="margin:0px;padding:0px"></iframe>
</div> --%>

<body style="Font-size:63%" leftmargin="0">
<div class="head">
		<div class="float_left pt-1 shouye"><a href="../order/shouye.html" target="_parent">首页</a></div>
		<div class="float-1 color-1 font-s-3 pl-2 pt-6" onclick="location.replace('MyListAction_MyListDisplay')">订单</div>
		<div class="float-1 color-2 font-s-3 pl-2 pt-6">积分</div>
		<div class="float-1 color-1 font-s-3 pl-2 pt-6" onclick="location.replace('UserInfoAction_UserInfo')">资料</div>
</div>
<div class="body">
<center>
	<div class="font-s-4 pt-4">当前积分<font class="color-1 font-s-5"> 0 </font></div>
	<div class="border0"></div>
	<div class="pt-2"><img src="img/jifen.png"/></div>
	<div class="pt-4 pb-2 color-2">此券30天内有效 ，已经兑换，不可退换积分</div>
	<div class="pt-4 pb-1">
		<input type="button" value="立即兑换" name="" class="button1"/>
	</div>	
</center>		
	<div style="clear:both"></div>
</div>

<div class="footer">
	<iframe src="footer.jsp" width="100%" height="200px" frameborder="0" scrolling=no style="margin:0px;padding:0px"></iframe>
</div>
  </body>
</html>
