<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>找回密码</title>
<link rel="stylesheet" type="text/css" href="res/css/user/forgetPassword.css">
<script type="text/javascript" charset="utf-8" src="res/js/jQuery/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8" src="res/js/user/userCommon.js"></script>
<script type="text/javascript" charset="utf-8" src="res/js/constants.min.js"></script>
<script type="text/javascript" charset="utf-8" src="res/js/user/forgetPassword.js"></script>
</head>
<body>
  <div class="box">
  <form action="<%=path %>/user/toFindPassword.do" name="prove" method="post">
    <ul>
      <li>
        <span class="left">手机号:</span>
        <span class="middle">
          <input type="text" placeholder="请输入注册手机号" name="phoneNum" 
          maxlength="11" id="phoneNum"/>
        </span>
      </li>
      <li>
        <span class="left">验证码:</span>
        <span class="middle">
          <input type="text" id="inputCode" name="messageCode" placeholder="请输入验证码"/>
        </span>
        <div style="display: none;" id="messageCode">${messageCode }</div>
        <span class="right" id="getMessageCode">获取验证码</span>
      </li>
      <li><span class="sure">下一步</span></li>
    </ul>
  </form>
  </div>
</body>
</html>