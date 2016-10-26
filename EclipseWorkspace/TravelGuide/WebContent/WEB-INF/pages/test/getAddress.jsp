<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" 
      + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
    body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";font-size:14px;}
    #allmap {width:100%;height:600px;}
</style>
<script type="text/javascript" src="<%=path%>/res/js/test/getAddress.js"></script>
<script type="text/javascript" charset="utf-8" src="http://api.map.baidu.com/api?v=2.0&ak=LxHmn7k5chQLjG5gYsTdGDZQLZAShAyY"></script>
<script type="text/javascript" src="<%=path %>/res/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/res/js/constants.min.js"></script>
<title>获取地址</title>
</head>
<body>
  <div id="allmap"></div>
  <div id="r-result">横坐标:
    <select name="address" id="address" onchange="getAddress();">
      <c:forEach items="${sightBeans }" var="sight">
      <option value="${sight.sightId }*${sight.sightLng}*${sight.sightLat}">${sight.sightName }</option>
      </c:forEach>
    </select>
    <input type="button" onclick="getAddress();" value="获取"/>
    <div id="content"></div>
  </div>
</body>
</html>