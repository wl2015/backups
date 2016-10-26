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
<title>找回密码第一步</title>
<link rel="stylesheet" type="text/css" href="res/css/admin/searchPassword1.css">

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
        <span><label for="vertify-phone">电话号码</label></span><input type="text" id="vertify-phone" name="vertify-phone"/>
        </div>
        <div class="box">
        <span><label for="">校验码：</label></span><input type="text" id="vertifyCode" name="vertifyCode" onKeyUp="this.value = this.value.replace(/[^0-9]/g,'');" placeholder="请输入右边图片上的数字"/>
        <img src ="res/img/num1.png" width="20px"  height="20px" class="vertifyPic" />
        </div>
        <div class="box">
        
          <span><label for="vertify-input">验证码</label></span><input type="text" id="vertify-input" name="vertify-input"/>
          <span id="span3"><input type="button" value="获取短信验证码" class="vertify-BTN"  style="width:120px;height:30px;"/></span>
        </div>
        <div class="box box1">
          <div class="nextBtn" id="next-BTN">下一步</div>
        </div>
      </div>
  </div>
  <div class="right">
    <img src="res/img/logo.png"/>
  </div>
</div>
</body>
</html>