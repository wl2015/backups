<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'right.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="../css/right.css"/>
	<script src="../js/jquery-1.4.2.min.js"></script>
		<script>
		setTimeout(function(){
		location='WaiteSureAction_execute';
		},3000);
		</script>
  </head>
  
<body bgcolor="#F9F5EA">
	<div class="co">
		<div>欢迎进入有菜后台管理</div>
		<div>
			马上进入"<a href="sure/nosure.jsp">未确认订单</a>"
		</div>
	</div>
	<center>
		<div><img src="../img/load3.gif" style="width:60px;height:60px;margin-top:50px"/></div>
	</center>
</body>
</html>
