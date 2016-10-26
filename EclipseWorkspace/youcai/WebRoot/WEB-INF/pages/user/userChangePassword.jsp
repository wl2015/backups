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
<script type="text/javascript" charset="utf-8" src="res/js/user/userChangePassword.js"></script>
</head>
<body>
<div class="content">
<form action="<%=path %>/user/changeUserPassword.do"
     name="change">
  <ul>
    <li>
      <span class="left">
        原密码:
      </span>
      <span class="right">
        <input type="password" name="oldPassword" id="oldpsd"
         maxlength="20" placeholder="请输入原密码"/>
      </span>
    </li>
    <li>
      <span class="left">
        新密码:
      </span>
      <span class="right">
        <input type="password" maxlength="20" name="password" id="psd" placeholder="请输入新密码"/>
      </span>
    </li>
    <li>
      <span class="left">
        确认密码:
      </span>
      <span class="right">
        <input type="password" name="repeatPassword" maxlength="20" id="repeatpsd" placeholder="再次输入密码"/>
      </span>
    </li>
    <div style="color: red;margin-left: 100px">${error }</div>
    <li>
      <span class="sureModify" id="modify">
        确认提交
      </span>
      <span class="turnPrev" onclick="window.location=
      '<%=path %>/user/toUserInfoManage.do'">
        返回上一页
      </span>
    </li>
  </ul>
</form>
</div>
</body>
</html>