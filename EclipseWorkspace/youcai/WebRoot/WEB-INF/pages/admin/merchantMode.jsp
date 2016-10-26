<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'merchantMode.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <script type="text/javascript" src="res/js/jQuery/jquery.min.js"></script>
  <script type="text/javascript" src="res/js/admin/merchant.js?verson=2"></script> 
  <script type="text/javascript" src="res/js/common.min.js"></script>     
  <script type="text/javascript" src="res/js/constants.min.js"></script> 
  
  </head>
  
  <body> 商家模式选择<br>
    <div align="center">
        <div class="loginWay">
            当前模式：<c:if test="${modeType == 1}">单商家</c:if><c:if test="${modeType == 2}">多商家</c:if>
            <br>
            <input type="radio" name="mode" id="singleMer"  value="1" <c:if test="${modeType == 1}">checked</c:if> />单商家模式
            <br>
            <input type="radio" name="mode" id="manyMer" value="2" <c:if test="${modeType == 2}">checked</c:if> />多商家模式<br/>
          </div>
          <br>
          <button type="button" id="saveMode">保存模式
          </button>
    </div>
    
  </body>
</html>
