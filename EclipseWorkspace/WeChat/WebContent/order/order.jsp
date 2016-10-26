<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE  html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
 <link rel="stylesheet" type="text/css" href="../css/menuList.css" />
 <link rel="stylesheet" type="text/css" href="../css/layout.css" />
<script	src="../js/jquery-1.4.2.min.js" ></script>
<script  language="javascript" type="text/javascript" src="../js/order.js"></script>

<title>开始点餐</title>
	<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
</head>
<body style="Font-size:63%;">
<div class="shop">
	<div ><img src="../picture/order.png" class="order"/></div>
	<div class="num">0</div>
</div>
<div class="newOrder"><img src="../picture/new.png" class="new" onclick="location.replace('first.html');"/></div>

<div class="head">
<iframe src="head.html" width="100%" frameborder="0" scrolling=no></iframe>
</div>
<div class="body">
	<form action="${pageContext.request.contextPath }/ListAction_lastshow">
	
		<s:iterator var="dish" value="dishlist">
			<div class="body2"><img src="../picture/food1.jpg"/></div>
			<div  style="float:right">
				<div class="body3 font-s-3" style="float:left">${dish.dish_name }</div>
				<div class="color-2 font-s-2 pt-1" style="float:left;">(${dish.dish_taste })</div>
				<div class="clear body3">
					<div class="float-2  pt-4">
					<input type="button" value="点菜" name="" class="button" id="order${dish.dish_id}" onclick="order('${dish.dish_id}')";/></div>
	
					<div class="float-2  margin-t-4 border-13 pl-1" style="display:none" id="over">售罄</div>
	
					<div class="float-2  margin-t-4 " style="display:none" id="as${dish.dish_id}">
						<div class="border-min-1 float-2 margin-l-3" id="add${dish.dish_id }" onclick="add('${dish.dish_id}');">+</div>
						<div  class="border-min-3 float-2 margin-l-3" id="num${dish.dish_id }" >1</div>
						<div class="border-min-2 float-2"  onclick="jian('${dish.dish_id}');">-</div>
					</div>
					<div  class="color-1 font-s-3 float-2 pt-4">￥<font size="5">${dish.price }</font>.00</div>
				</div>
				<div class="clear float-2 margin-r-2 font-s-6" style="color:gray">
				<div class="float-1">还有</div>
				<div class="float-1" id="left${dish.dish_id }">${dish.leftNum }</div>
				<div class="float-1">份</div>
				</div>
			</div>
			<div style="clear:both"></div>
			<div class="border"></div>
		</s:iterator>

	</form>

</div>

<div class="footer">
<iframe src="footer.html" width="100%" height="250px" frameborder="0" scrolling=no></iframe>
</div>
</div>
</html>

