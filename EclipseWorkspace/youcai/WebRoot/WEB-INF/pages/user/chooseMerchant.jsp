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
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<title>选择商家</title>
<link rel="stylesheet" type="text/css" href="res/css/user/chooseMerchant.css">
<script type="text/javascript" charset="utf-8" src="http://api.map.baidu.com/api?v=2.0&ak=mbLgt6OQ49mT8is4yTtzhgFK"></script>
<script type="text/javascript" charset="utf-8" src="res/js/jQuery/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8" src="res/js/constants.min.js"></script>
<script type="text/javascript" charset="utf-8" src="res/js/user/chooseMerchant.js"></script>
</head>
  
<body>
  <div class="box">
    <div class="merchantMap" id="allmap"></div>
    <div class="prevNext">
      <div class="prev" onclick="getBackMerchant();">上一家</div>
      <div class="next" onclick="nextMerchant();">下一家</div>
    </div>
    
    <!-- 推送商家信息 -->
    <form action="<%=path %>/user/recordChoseMerchant.do" name="choose" 
      method="post">
      <div class="merchantInfo" id="merchantContent"></div>
    </form>
    
    <div class="sureAndExit">
      <div class="button2" onclick="document.choose.submit()">确认</div>
      <div class="button2" onclick="window.location=
      '<%=path %>/user/toUserMainPage.do'">退出点餐</div>
    </div>
    
    
  </div>
</body>
</html>
