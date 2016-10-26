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
<title>等待商家接单</title>
<link rel="stylesheet" type="text/css" href="res/css/user/waiting.css">
<script type="text/javascript" charset="utf-8" src="res/js/jQuery/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8" src="res/js/user/waiting.js"></script>
</head>  
<body>
  <div class="waiting">
  	<div id="content">
    <span class="span1">倒计时:</span>
    <div class="circle"><span id="time">30</span>s</div>
  	</div>
 	 <input type="text" id="orderState" style="display: none;"
   	value="${ordersState }"/>
  	 <div class="button" onclick="window.
  	location='<%=path%>/user/toUserMainPage.do'">退出点餐</div>
  </div>
</body>
<script>
  function fun (){
    var num = parseInt(document.getElementById("time").innerHTML);
    if(num > 0){
      window.setTimeout("fun()",1000);
      document.getElementById("time").innerHTML = num - 1;
    }
    else{
      var orderState = document.getElementById("orderState").value;
      if(orderState==0){
        window.location='<%=path%>/user/toChooseMerchant.do'
      }
      else if(orderState==1){
        if(confirm("单个的商家满足条件，你愿意分单吗")){
          window.location='<%=path%>/user/toChooseMerchants.do';
         }
        else{
          window.location='<%=path %>/user/toUserMainPage.do';
        }
        
      }
    }
  }
  fun();
</script>
</html>
