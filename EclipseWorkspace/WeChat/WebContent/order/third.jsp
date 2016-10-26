<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE  html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
 <link rel="stylesheet" type="text/css" href="../css/menuList.css" />
  <link rel="stylesheet" type="text/css" href="../css/layout.css" />
<title>开始点餐</title>
</head>
<body style="Font-size:63%" leftmargin="0">

<div class="head">
<iframe src="head.html" width="100%" frameborder="0"></iframe>
</div>

<div class="margin">
	<div class="pt-1 float-1 pb-1 margin-b-2"><img src="../picture/y3.png" style="width:3.5em"/></div>
	<div class="pt-3 float-1 color-3 font-s-3 pb-1 margin-b-2">确认信息，开始点餐</div>
	<div style="clear:both"></div>
	<div class="border-12">食客姓名：<s:property value="userInfo.name"/></div>
	<div class="border-12">联系电话：<s:property value="userInfo.link_phone"/></div>
	<div class="border-12">详细地址：<s:property value="userInfo.frontaddress"/>
  									  <s:property value="userInfo.add_address"/></div>
	<div class="border-12">送餐时间：${param.riqi}   ${param.time}</div>
	<div class="border-8" onclick="location.href='ListAction_orderList.do'">开始点餐</div>
	<div class="border-8-2" onclick="location.href='first.jsp'">重新选择送餐信息</div>
</div>
<div class="footer">
<iframe src="footer.html" width="100%" height="250px" frameborder="0" scrolling=no></iframe>
</div>

</body>
</html>