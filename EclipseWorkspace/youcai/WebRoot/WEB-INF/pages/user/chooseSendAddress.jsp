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
<title>送餐选址</title>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link rel="stylesheet" type="text/css" href="res/css/user/chooseSendAddress.css">
<script type="text/javascript" charset="utf-8" src="res/js/jQuery/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8" src="http://api.map.baidu.com/api?v=2.0&ak=mbLgt6OQ49mT8is4yTtzhgFK"></script>
<script type="text/javascript" charset="utf-8" src="res/js/user/chooseSendAddress.js"></script>
<script type="text/javascript" charset="utf-8" src="res/js/constants.min.js"></script>
</head>

<body>
  <div class="content">
    <div id="r-result">
                请输入地址:
      <input type="text" id="suggestId"
      value="百度"/>
      <input type="button" id="jumpMap" value="搜索"/>
    </div>
    <div id="searchResultPanel"></div>
    <div id="allmap"></div>
    <div style="display: none;" id="isChoose">notChoose</div>
    <div class="beginOrder" id="goOrder">开始点餐</div>
  </div>
</body>
</html>
