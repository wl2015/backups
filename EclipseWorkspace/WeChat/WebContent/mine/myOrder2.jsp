<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'myOderS.jsp' starting page</title>
    
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
 <link rel="stylesheet" type="text/css" href="css/menuList_1.css" />
 <link rel="stylesheet" type="text/css" href="css/layout_1.css" />
  </head>
  
  <body style="Font-size:63%" leftmargin="0">

<div class="head">
	<div class="float_left pt-1"><img src="img/home-page.png" style="width:7em"/></div>
	<div class="float_left color-2 font-s-3 pl-1">我的订单</div>
	<!-- <div class="float_left color-1 font-s-3 pl-1" onclick="location.replace('MyValueAction_NotDiscounted')">优惠券</div> -->
	<div class="float_left color-1 font-s-3 pl-1" onclick="location.replace('IntegralAction_MyIntegral')">积分</div>
	<div class="float_left color-1 font-s-3 pl-1" onclick="location.replace('UserInfoAction_UserInfo')">我的资料</div>
<!-- <iframe src="Head/head.jsp" width="100%" frameborder="0"></iframe>-->
</div>
<div class="body">
<center>
	
		<div class="pt-2"><img class="img" src="img/order.png"/></div>
		<div class="color-1 font-s-4 pt-1 pb-1">您的订单已成功提交</div>
		<div class="body11">
		<div class="body22 font-s-2">您点的食物将在<font class="color-1">2014-11-04 17:10</font>左右送到。</div>	
		</div>
<div class="body11">
		<div class="body22 font-s-2">为了方便为您服务，请保持手机畅通。</div>	
		</div>
<div class="body11">
		<div class="body22 font-s-2">在线付款的客户，食物最快能在<font class="color-1">付款后三十分钟</font>内送到，为了我们能够在您希望的时间送餐，请及时付款。</div>	
		</div>
<div class="body11">
		<div class="body22 font-s-2">付款过晚，会导致送餐的延迟或者订单的取消，请谅解。</div>	
		</div>
<div class="border0"></div>
<div class="pt-4 pb-1">
	<input type="button" value="立即付款" name="" class="button1" onclick="location.replace('#')"/>
		</div>
<div class="border0"></div>
<div class="pt-4 pb-1">
	<input type="button" value="继续订餐" name="" class="button" onclick="location.replace('menuList.jsp')"/>
	<input type="button" value="查看订单" name="" class="button2"onclick="location.replace('myOrder1.jsp')"/>
</div>
</center>		
	<div style="clear:both"></div>
</div>
<div class="border"></div>
<div>
<iframe src="footer.jsp" width="100%" height="200px" frameborder="0" scrolling=no style="margin:0px;padding:0px"></iframe>
</div>

</html>
