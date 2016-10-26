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
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<title>注册</title>
<link rel="stylesheet" type="text/css" href="res/css/user/userRegist.css">
<script type="text/javascript" charset="utf-8" src="res/js/jQuery/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8" src="res/js/user/userCommon.js"></script>
<script type="text/javascript" charset="utf-8" src="res/js/constants.min.js"></script>
<script type="text/javascript" charset="utf-8" src="res/js/user/userRegist.js"></script>
</head>
<body>
<form action="<%=path %>/user/userRegist.do" 
    name="regist" method="post">
<ul class="content">
  <li class="phone">
    <input type="text" name="phone" id="phoneNum" maxlength="11"
    placeholder="请输入电话号码"/>
    <div style="color:red;display: none;" id="phoneNumIsNull">请输入正确的手机号</div>
    <div style="color: red;display: none;" id="phoneNumIsRegist">该手机号已经注册</div>
  </li>
  <li class="name">
    <input type="text" name="name" id="userName" placeholder="请输入姓名" maxlength="6"/>
    <div style="display: none;color: red;" id="NameIsNull">名字不能为空</div>
  </li>
  <li class="password">
    <input type="password" maxlength="20" name="passWord" id="psd"
     placeholder="请输入密码"/>
     <div style="color: red;display: none;" id="passwordRule">密码不能少于六位</div>
  </li>
  <li class="passAgin">
    <input type="password" name="repeatPassWord" id="repeatpsd" maxlength="20"
       placeholder="请再次输入密码"/>
   <div style="color: red;display: none;" id="different">两次密码输入不相同</div>
  </li>
  <li class="code">
    <div style="display: none;" id="messageCode">${messageCode }</div>
    <input type="text" id="inputCode" placeholder="请输入验证码"/>
    <span id="getMessageCode">
      获取验证码
    </span>
  </li>
  <li class="sex">
    <span>
      <input type="radio" value="0" name="sex" checked/>男
    </span>
    <span> 
      <input type="radio" value="1" name="sex"/>女
    </span>
  </li>
  <li class="registButton">
    注册
  </li>
  <li class="otherLink">
    <a href="<%=path %>/user/toLogin.do" >
      返回
    </a>
  </li>
</ul>
</form>
</body>
</html>
