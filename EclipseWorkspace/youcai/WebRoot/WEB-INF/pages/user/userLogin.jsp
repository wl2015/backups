<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href=<%=basePath %>>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<script type="text/javascript" charset="utf-8" src="res/js/jQuery/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8" src="res/js/user/userCommon.js"></script>
<link rel="stylesheet" type="text/css" charset="utf-8" href="res/css/user/login.css">
<script type="text/javascript" charset="utf-8" src="res/js/user/userLogin.js"></script>
<script type="text/javascript" charset="utf-8" src="res/js/constants.min.js"></script>
</head>
<body>
  <span class="logo">
    <img src="res/img/logo.png">
  </span>
  <div class="content">
  <form action="<%=path %>/user/userLogin.do" 
         name="login" method="post">
    <ul class="loginMessage">
      
      <li class="phone">
        <input type="text" name="phone" placeholder="请输入手机号" maxlength="11" id="phoneNum"/>
      </li>
      <li class="password">
        <input type="password" name="password" id="psd" placeholder="请输入密码" maxlength="20">
      </li>
      
      <li class="loginButton" id="goSubmit">
        登录
      </li>
      <div style="text-align: center;color: red">${error }</div>
      <li class="otherLink">
        <a id="forgetPass">
          忘记密码
        </a>
        <a id="goRegist">
          去注册
        </a>
      </li>
    </ul>
  </form>
  </div>
</body>
</html>
