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
<style type="text/css">
    body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";font-size:14px;}
    #l-map{height:90%;width:100%;}
    #r-result{width:100%;}
  </style>
<script type="text/javascript" charset="utf-8" src="http://api.map.baidu.com/api?v=2.0&ak=LxHmn7k5chQLjG5gYsTdGDZQLZAShAyY"></script>
<script type="text/javascript" src="<%=path %>/res/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/res/js/constants.min.js"></script>
<script type="text/javascript" src="<%=path%>/res/js/test.js"></script>
<title>获取地图信息</title>
</head>
<body>
<div id="r-result">请输入:<input type="text" id="suggestId" size="20" value="百度" style="width:700px;" /></div>
  <div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto;"></div>
  <div id="l-map"></div>
</body>
</html>