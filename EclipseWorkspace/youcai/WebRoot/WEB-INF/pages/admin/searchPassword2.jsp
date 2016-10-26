<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>找回密码第二步</title>
<link rel="stylesheet" type="text/css" href="res/css/admin/searchPassword2.css">
  <script type="text/javascript" src="res/js/jQuery/jquery.js"></script>
  <script type="text/javascript" src="res/js/admin/adminPassword.js?verson=2"></script> 
  <script type="text/javascript" src="res/js/common.min.js"></script>     
  <script type="text/javascript" src="res/js/constants.min.js"></script>
  <script type="text/javascript"  src="res/js/inputcheck.js"></script>
</head>
<body>
<div class="content">
  <div class="left">
      <div class="title">
        <span id="span1">1.获取验证码</span>
        <span>--></span>
        <span id="span2">2.设置新密码</span>
      </div>
      <div class="info">
        <div class="box">
        <span><label for="">新密码</label></span><input type="text" id="password" name="password"/>
        </div>
        <div class="box">
          <span><label for="">确认新密码</label></span><input type="text" id="repeatPassword"  name="repeatPassword"/>
        </div>
        <div class="box box1">
          <div class="finishedButton" id="finishedButton">完成重置</div>
        </div>
      </div>
      <span id="PassMsg"></span>
  </div>
  <div class="right">
    <img src="res/img/logo.png"/>
  </div>
</div>
</body>
</html>