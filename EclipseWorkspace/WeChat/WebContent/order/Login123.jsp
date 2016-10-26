<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/other.css" />
   <link rel="stylesheet" type="text/css" href="css/menuList.css" />

	<style type="text/css">
	
	#x1 img{
		width:100%;
		height:5em;
	}
	</style>
	
  </head>
  
 <body style="Font-size:63%" leftmargin="0">
	<div class="head2">
	
	<iframe src="Head/head4.jsp" width="100%" frameborder="0" scrolling=no ></iframe>
	</div>
	
	<s:form action="LoginAction123_login.do" method="post" >
	<div class="bodys-1">
		<div class="name">手机</div>
		<div><input type="text" class="input-name" name="user.user_name"/></div>
		<div class="name">密码</div>
		<div><input type="password" class="input-name" name="user.password"/></div>
		<div class="regist" style="float:left">注册</div>
		<div style="float:left"><input  type="submit" value="登录" class="denglu"></div>
	</div>
	
	</s:form>
	
	<div align="center" style="color:red">${param.errorMsg }</div>
</body>
</html>
