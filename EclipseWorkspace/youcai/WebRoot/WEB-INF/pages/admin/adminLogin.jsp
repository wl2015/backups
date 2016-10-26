<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <base href="<%=basePath%>">
<title>管理员登录</title>
<link rel="stylesheet" type="text/css" href="res/css/admin/adminLogin.css"/>

  <script type="text/javascript" src="res/js/jQuery/jquery.min.js"></script>
  <script type="text/javascript" src="res/js/admin/adminLogin.min.js?verson=2"></script> 
  <script type="text/javascript" src="res/js/common.min.js"></script>     
  <script type="text/javascript" src="res/js/constants.min.js"></script>
</head>
<body>
  <div id="box">
    <div class="title">
      
        管理员登录
    </div>
    <div  id="login">
      <div id="form">
        <div id="input-body">
	        <label for="userName">用户名：</label><input type="text"  name="account"  value="18011404619" maxlength="11" id="userName" placeholder="请输入用户名"/>
	        <span id="AcountMsg"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>${LOGIN_ERROR }</span><br/><br/>
	        <label for="password">密&nbsp;&nbsp;&nbsp;码：</label><input type="password"  name="password" id="password"  placeholder="请输入密码" maxlength="12"/>
	        <span id="PassMsg"></span><br/>
        </div>
        <div class="footer">  
	        <div class="loginWay">
	          <input type="radio" name="type" id="phone"  value="手机" checked="true"/>手机登录
	          
	          <input type="radio" name="type" value="账号" id="acountNum"/>账号登录<br/>
	        </div>
        <button type="button" id="loginSubmit">登录</button>
        </div>   
      </div>
      <div class="forgetPass">
        <a href="admin/findPassPage.do" target="_blank">忘记密码？</a>
      </div>
      
    </div>
  </div>
  <div id="imgArea">
    <img src="res/img/logo.png"/>
  </div>
  
</body>
</html>
