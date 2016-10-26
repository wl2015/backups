<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE  html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
 <link rel="stylesheet" type="text/css" href="css/menuList.css" />
  <link rel="stylesheet" type="text/css" href="css/layout.css" />
  <script  language="javascript" type="text/javascript" src="js/order.js"></script>
<title>开始点餐</title>
</head>
<body style="Font-size:63%" leftmargin="0">

<div class="head">
<iframe src="order/head.html" width="100%" frameborder="0"></iframe>
</div>

<div class="margin">
	<div class="pt-1 float-1 pb-1 margin-b-2"><img src="picture/y1.png" style="width:3.5em"/></div>
	<div class="pt-3 float-1 color-3 font-s-3 pb-1 margin-b-2">选择送餐地址和送餐信息</div>
	<div style="clear:both"></div>

	<s:iterator var="i" value="infoList" >
	
	<div class="border-4"  id="address" onFocus="this.style.borderColor='#BB825B'" onblur="this.style.borderColor='#DADADA'">
		<div style="float:right" class="padding-1">
			<div style="float:right" class="pl-1 color-10 font-s-1" ><s:a href="addressAction_delete.do?userInfo.userinfo_id=%{#i.userinfo_id}" onclick=" return confirm('确定要删除吗？')">删除</s:a></div>
			
		</div>
		<div class="clear"></div>
		<s:a href="order/second.jsp?idd=%{#i.userinfo_id}"><div>
		
		<div onclick="location.replace('second.jsp');">
		<div class="padding-2 font-s-1 color-6">食客姓名：<s:property value="#i.name"/>(<s:property value="#i.sex == 1 ? '先生' : '女士'"/>)</div>
		<div class="padding-2 font-s-1 color-6">联系电话:<s:property value="#i.link_phone"/></div>
		<div class="padding-2 font-s-1 color-6">详细地址:<s:property value="#i.frontaddress"/>
  									  <s:property value="#i.add_address"/>	</div>
		</div>
		</div></s:a>
	</div>
	
	</s:iterator>	
	<div class="border-2" onclick="location.href='order/first_information.jsp?user.user_id=${user.user_id}'">创建新的送餐地址</div>
	<div class="border-3"></div>
	<div><input type="button" value="返回" class="button-3" onclick="location.replace('order/menuList.html');"/></div>
	
</div>
<div class="footer">
<iframe src="order/footer.html" width="100%" height="250px" frameborder="0" scrolling=no></iframe>
</div>

</body>
</html>