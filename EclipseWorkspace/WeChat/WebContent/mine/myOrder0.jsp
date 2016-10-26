<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyOrder0.jsp' starting page</title>
    
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

<div class="head">
<div class="float_left pt-1"><img src="img/home-page.png" style="width:7em"/></div>
		<div class="float_left color-2 font-s-3 pl-1">我的订单</div>
		<!-- <div class="float_left color-1 font-s-3 pl-1" >优惠券</div> -->
		<div class="float_left color-1 font-s-3 pl-1" onclick="location.replace('Integral/myGrade.jsp')">积分<div class="float_left color-1 font-s-3 pl-1" onclick="location.replace('Information/myIF.jsp')">我的资料</div></div>
		
</div>
<div class="body">
<center>
	<div class="color-1 font-s-4 pt-4">您还没有订单</div>
		<div class="pt-2"><img src="img/cook.png"/></div>
	<div class="pt-4 pb-2"><a href="List/menuList.jsp" style="color:blue;font-size:16px">点这里，挑一份中意的慢烧吧^_^</a></div>
		
</center>		
	<div style="clear:both"></div>
</div>
<div class="border"></div>
<div>
<iframe src="footer.jsp" width="100%" height="200px" frameborder="0" scrolling=no style="margin:0px;padding:0px"></iframe>
</div>

</body>
</html>
