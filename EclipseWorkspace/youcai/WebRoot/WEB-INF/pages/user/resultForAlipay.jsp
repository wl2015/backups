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
<title>支付成功</title>
<link rel="stylesheet" type="text/css" href="res/css/user/resultForAlipay.css">
</head>
<body>
<div class="box">
  <div class="logo"><img src="res/img/logo.png"></div>
  <div class="text">支付成功！</div>
  <c:if test="${payState == 1}">
    <div class="backIndex" onclick="window.location=
      '<%=path%>/user/showOrderTwo.do'">支付下一单</div>
  </c:if>
  <c:if test="${payState != 1}">
    <div class="backIndex" onclick="window.location=
      '<%=path%>/user/toUserMainPage.do'">返回主页</div>
  </c:if>
</div>
</body>
</html>