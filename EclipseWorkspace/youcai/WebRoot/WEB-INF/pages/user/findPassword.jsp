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
<title>密码修改</title>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link rel="stylesheet" type="text/css" href="res/css/user/passModify.css">
<script type="text/javascript" charset="utf-8" src="res/js/jQuery/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8" src="res/js/user/userCommon.js"></script>
<script type="text/javascript" charset="utf-8" src="res/js/user/findPassword.js"></script>
</head>
<body>
<div class="content">
  <form action="<%=path %>/user/saveNewPassword.do" method="post" name="change">
  <input type="text" style="display: none;" name="phoneNum" value="${phoneNum }">
  <ul>
    <li>
      <span class="left">
        新密码:
      </span>
      <span class="right">
        <input type="password" name="password" id="psd" maxlength="20" placeholder="请输入新密码"/>
      </span>
    </li>
    <li>
      <span class="left">
        确认密码:
      </span>
      <span class="right">
        <input type="password" name="repeatPassword" id="repeatpsd" maxlength="20" placeholder="再次输入密码"/>
      </span>
    </li>
    <li>
      <span class="sureModify" id="modify">
        确认提交
      </span>
      <span class="turnPrev" onclick="window.location=
      '<%=path %>/user/toLogin.do'">
        返回登录页面
      </span>
    </li>
  </ul>
</form>
</div>
</body>
</html>