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
<title>个人中心</title>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link rel="stylesheet" type="text/css" href="res/css/user/userMainPage.css">
<script src="res/js/jQuery/jquery.min.js"></script>
</head>
<body>
  <ul class="content">
    <li class="button" onclick="window.location=
    '<%=path %>/user/toChooseSendAddress.do'">
      开始订餐
    </li>
    <li class="button" onclick="window.location=
    '<%=path %>/user/toUserInfoManage.do'">
      个人信息
    </li>
    <li class="button" onclick="window.location=
    '<%=path %>/user/toGetUnfinishedOrders.do'">
      未完成订单
    </li>
    <li class="button" onclick="window.location=
    '<%=path %>/user/toGetFinishedOrders.do'">
      历史订单
    </li>
    <li class="button" onclick="window.location=
    '<%=path %>/user/outLogin.do'">
      退出登录
    </li>
  </ul>
</body>
</html>