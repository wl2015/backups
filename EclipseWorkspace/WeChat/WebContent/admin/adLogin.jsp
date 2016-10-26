<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'adLogin.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="../css/right.css"/>
	<script  language="javascript" type="text/javascript" src="../js/hover.js"></script>
	<script src="../js/jquery-1.4.2.min.js"></script>
  </head>
  
  <body>
  <font color="red"><s:property value="loginMsg"/></font> 
		<div class="border3 box-shadow">
			<div class="f1"><img src="../picture/youcai2.png" class="img2"/></div>
			<form action="AdminLoginAction_isLogined" method="post">
			<div class="f1 ml">
				<div class="pt co mb">有菜-管理员登录</div>
				<div class="c2 mt" style="position:relative">管理员名称:</div>
				<div class="mt"><input type="text" name="admin.admin_name" size="30" maxlength="30" class="input" id="name" onBlur="test()"/></div>
				<div class="c2 mt">密码:</div>
				<div class="mt"><input type="password" name="admin.password" size="30" maxlength="30" class="input" id="password" onBlur="test()"/></div>
				<div class="mt2"><input type="submit" value="登 录" style="width:230px" class="input button" /></div>
				<div class="pt c1 mb" id="message"></div>
			</div>
			</form>
		</div>
	</body>
</html>
