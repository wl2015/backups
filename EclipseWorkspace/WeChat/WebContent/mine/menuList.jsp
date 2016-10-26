<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'menuList.jsp' starting page</title>
    
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
<div class="order"><input type="button" value="购物车"/></div>
<div class="newOrder"><input type="button" value="重新点餐"/></div>
<div class="Head/head">
<iframe src="Head/head.jsp" width="100%" frameborder="0"></iframe>
</div>
<div class="body">
		<div class="body2"><img src="img/food1.jpg"/></div>
		<div  style="float:left" class="pl-1">
			<div class="body3 font-s-2" style="float:left">美食</div>
			<div class="color-2 font-s-1 pt-2" style="float:left;">(辣★★)</div>
			<div class="clear body3">
				<div  class="color-1 font-s-3 float-1 pt-4">￥<font size="5">48</font>.00</div>
				<div class="float-1 pl-1 pt-4"><input type="button" value="开始点餐" name="" class="button"/></div>
			</div>
		</div>
	<div style="clear:both"></div>
</div>
<div class="border"></div>
<div>
<iframe src="footer.jsp" width="100%" height="200px" frameborder="0" scrolling=no style="margin:0px;padding:0px"></iframe>
</div>


</html>
