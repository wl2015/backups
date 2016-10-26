<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" 
    + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" charset="utf-8" href="<%=path %>/res/css/user/userLogin.css">
<script type="text/javascript" src="<%=path%>/res/js/user/userLogin.js"></script>
<title>用户登录</title>
</head>
<body>
<div class="content" id="content">
  <!-- 调节位置的div -->
  <div style="width: 100%;height: 40%"></div>
  <div class="login">
    <div style="height: 40px;"></div>
    <form action="<%=path %>/user/doLogin" id="loginForm" method="post">
      <div class="userName">&nbsp;&nbsp;&nbsp;用户名:
        <input type="text" name="userName" id="userName" maxlength="12"/>
        <font style="color: gray;font-size: 8px;">用户为6至12位</font>
      </div>
      <div class="passWord">&nbsp;&nbsp;&nbsp;密&nbsp;&nbsp;码:
        <input type="password" name="passWord" id="passWord" maxlength="20"/>
        <font style="color: gray;font-size: 8px;">密码为6至20位</font>
      </div>
      <div class="buttonDiv">
        <input class="button" type="button" value="登陆" onclick="login();"/>&nbsp;&nbsp;&nbsp;
        <input class="button" type="button" value="注册" onclick="window.location.href='<%=path %>/user/toRegist'"/>
      </div>
      <div class="result" id="result">${result }</div>
    </form>
  </div>
</div>
</body>
</html>