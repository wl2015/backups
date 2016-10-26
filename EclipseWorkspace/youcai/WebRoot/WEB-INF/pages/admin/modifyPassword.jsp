<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<link rel="stylesheet" type="text/css" href="res/css/admin/modifyPassword.css"/>

  <script type="text/javascript" src="res/js/jQuery/jquery.min.js"></script>
  <script type="text/javascript" src="res/js/admin/modifyPassword.js?verson=2"></script> 
  <script type="text/javascript" src="res/js/common.min.js"></script>     
  <script type="text/javascript" src="res/js/constants.min.js"></script> 
</head>
<body>
  <div class="modifyPassword">
    <div class="modifyLeft">
      <div class="topTitle">修改密码</div>
      <div class="modifyContent">
        <!-- 记得把label 中的 for加上对应的input中的id -->
        <span><label for="">原密码</label></span><input type="text" id="beforePass" placeholder="请输入原密码"/>
        <span><label for="">新密码</label></span><input type="password" id="afterPassOne" placeholder="请输入新密码"/>
        <span><label for="">确认新密码</label></span><input type="password" id="afterPassTwo" placeholder="请再次输入新密码"/>
      </div>
      <div class="sureModify" id="ModifyPass">确认修改</div>
      <span id="PassMsg"></span>
    </div>
    <div class="modifyRight">
      <img src="res/img/logo.png"/>
    </div>
  </div>
</body>
</html>