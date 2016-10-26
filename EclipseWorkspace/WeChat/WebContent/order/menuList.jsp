<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
 <link rel="stylesheet" type="text/css" href="css/menuList.css" />
 <link rel="stylesheet" type="text/css" href="css/layout.css" />
<title>开始点餐</title>
<script type="" src="js/jquery-1.4.2.min.js"></script>
<script  language="javascript" type="text/javascript" src="js/order.js"></script>
</head>
<body style="Font-size:63%;">
<div class="shop">
	<div ><img src="picture/order.png" class="order"></div>
	<div class="num">0</div>
</div>
<div class="newOrder"><img src="picture/new.png" class="new"></div>
	<div class="head">
	<iframe src="order/head.html" width="100%" frameborder="0" scrolling=no></iframe>
	</div>
	
	<s:iterator var="dish" value="dishlist">
	<div class="body">
			<div class="body2"><a href="javascript:ShowDiv('MyDiv+<s:property value="#dish.dish_id"/>','fade')">
			<img src="<%=path %>/<s:property value="#dish.pic"/>"></a></div>
			
			<div  style="float:right">
				<div class="body3 font-s-3" style="float:left"><s:property value="#dish.dish_name"/></div>
				<div class="color-2 font-s-2 pt-1" style="float:left;"><s:property value="#dish.dish_taste"/>(辣★★)</div>
				<div class="clear body3">
					<div class="float-2 pl-2 pt-4">
					<input type="button" value="开始点餐" name="" class="button" onclick="location.replace('order/login.jsp');"/></div>
					<div  class="color-1 font-s-3 float-2 pt-3">￥<font size="5"><s:property value="#dish.price"/></font></div>
				</div>
			</div>
		<div style="clear:both"></div>
	</div>
	<!--弹出层时背景层DIV-->
	<div id="fade" class="black_overlay">
	</div>
	<div id="MyDiv+<s:property value="#dish.dish_id"/>" class="white_content">
		<div class="border-7-3" onclick="CloseDiv('MyDiv+<s:property value="#dish.dish_id"/>','fade')">关闭</div>
		<div><img src="<%=path %>/<s:property value="#dish.pic"/>" width="100%" height="300px"/></div>
		<div style="color:snow;font-size:15px"><s:property value="#dish.dish_intro"/></div>
	</div>
	</s:iterator>
	
	<div class="border"></div>
	<div class="footer">
	<iframe src="order/footer.html" width="100%" height="250px" frameborder="0" scrolling=no></iframe>
	</div>
	

</html>
