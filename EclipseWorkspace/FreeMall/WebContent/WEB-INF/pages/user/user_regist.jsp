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
<link rel="stylesheet" type="text/css" charset="utf-8" href="<%=path %>/res/css/user/userRegist.css">
<script type="text/javascript" src="<%=path%>/res/js/user/userRegist.js"></script>
<script type="text/javascript" src="<%=path%>/res/js/constants.min.js"></script>
<title>用户注册</title>
</head>
<body>
<div class="content" id="content">
  <!-- 调节位置的div -->
  <div style="height: 120px;text-align: center;"><b><font style="color: #22DD22;font-size: 100px">用户注册</font></b></div>
    
  <div class="regist">
    <!-- 调节位置的div -->
    <div style="height: 30px;"></div>
    
    <form action="<%=path %>/user/doRegist" id="registForm" method="post">
      <div class="inputDiv">用户名:&nbsp;&nbsp;
        <input type="text" id="userName" name="userName" maxlength="12" value="${userName }"/>
        <font style="color: gray;font-size: 14px;">用户名为6至12位，只能由字母和数字组成</font>
      </div>
      <div class="inputDiv">密&nbsp;&nbsp;码:&nbsp;&nbsp;
        <input type="password" id="passWord" name="passWord" maxlength="20"/>
        <font style="color: gray;font-size: 14px;">密码为6至20位</font>
      </div>
      <div class="inputDiv">重复密码:
        <input type="password" id="psd" maxlength="20"/>
        <font style="color: gray;font-size: 14px;">两次密码输入要一致</font>
      </div>
      <div class="inputDiv">姓&nbsp;&nbsp;名:&nbsp;&nbsp;
        <input type="text" id="name" name="name" maxlength="10" value="${name }"/>
        <font style="color: gray;font-size: 14px;">姓名最长十个字符</font>
      </div>
      <div class="inputDiv">电&nbsp;&nbsp;话:&nbsp;&nbsp;
        <input type="text" name="phone" id="phone" maxlength="11" value="${phone }"/>
        <font style="color: gray;font-size: 14px;">请输入正确的电话号码</font>
      </div>
      <div class="buttonDiv">
        <input class="button" type="button" value="注册" onclick="Regist();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input class="button" type="button" value="返回登陆" onclick="window.location.href='<%=path %>/user/toLogin'"/>
      </div>
    </form>
    <div class="result" id="result">${result }</div>
  </div>
</div>
</body>
</html>