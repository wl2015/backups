<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE  html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
 <link rel="stylesheet" type="text/css" href="../css/menuList.css" />
  <link rel="stylesheet" type="text/css" href="../css/layout.css" />
  <script  language="javascript" type="text/javascript" src="../js/order.js"></script>
<title>开始点餐</title>
</head>
<body style="Font-size:63%" leftmargin="0">

<div class="head">
<iframe src="head.html" width="100%" frameborder="0"></iframe>
</div>
 ${param.user.user_id }
<div class="margin">
	<div class="font-s-5 margin-t-2">选择送餐地址</div>
	<div class="border-6"></div>
	<div class="color-7 font-s-2 pb-1">第一步:输入地址（街道、写字楼、小区、楼盘名）</div>
	<form action="addressAction_searchKey.do" method="post">	
	<div class="pb-1">
	<input type="text" value="" name="keyword" style="width:75%;height:1.6em;float:left" class="input1" maxlength="20"/>
	<input type="submit" value="搜索" name=""  class="border-7-1" onclick="location.href='addressAction_searchKey.do'"/>
	</div>
	</form>	
	<div style="" id="seAd">
		<div style="height:3em"></div>
		<div class="border-6"></div>
		<div class="color-7 font-s-2 pb-1">第二步:选择配送地址</div>
		<div class="color-8 font-s-2 pb-1">
		<s:iterator var="a" value="addressList">	
			<s:a href="addressAction_getAddress.do?fronAddress.frontaddress_id=%{#a.frontaddress_id}"><s:property value="#a.front_address"/>,  </s:a>
		</s:iterator>

		</div>
	</div>

	<div style="clear:both"></div>
	<div style="display:none" class="font-s-2" id="lastAd">
		你选择的地址是:春熙路北段
	</div>
	<div class="border-6"></div>

	<div style="height:12em;margin-top:-3em">	
		<div>
		<input type="button" value="提交" onclick="location.replace('first_information.html');"  class="tijiao"/>
		</div>
		<div class="fanhui"  onclick="location.replace('first_information.html');">返回</div>
	</div>
</div>
<div class="footer">
<iframe src="footer.html" width="100%" height="250px" frameborder="0" scrolling=no></iframe>
</div>
</html>