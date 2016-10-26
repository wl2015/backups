<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="../css/top.css" />
	<script src="../js/time.js"></script>
	<script src="../js/jquery-1.4.2.min.js"></script>
  </head>
  
  <body bgcolor="#BB825B" height="60" width="100%" topmargin="0" leftmargin="0">
		<div class="m1 f1">
			<img src="../img/a2.png" width="180" height="38">
		</div>
	<div class="f2">	
		<div class="m2" id="Clock"></div>
		<div>	
			<div class="f1"><img src="../img/exit.png" width="25"></div>
			<div class="m3 f1"><a href="#" onClick="return window.confirm('确定要退出吗?')">退出系统</a></div>
		</div>
	</div>
</body>
</html>
