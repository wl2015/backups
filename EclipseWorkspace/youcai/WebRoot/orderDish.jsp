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
<meta http-equiv="Refresh" content="0; URL=/youcai/index.do"><!-- 跳转到相应controller处理数据 -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<title>点餐</title>
<link rel="stylesheet" type="text/css" href="res/css/user/orderDish.css">
<script type="text/javascript" charset="utf-8" src="res/js/jQuery/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8" src="res/js/constants.min.js"></script>
<script type="text/javascript" charset="utf-8" src="res/js/user/orderDish.js"></script>
</head>
<body>
<ul class="content">
  <c:forEach items="${dishList }" var="dish">
  <li class="dishes">
    <span class="dishesPicture">
      <a>
        <img src="${dish.dishPic }">
      </a>
    </span>
    <ul class="dishesIntroduce">
      <li class="dishesName">
        <a href="#" class="name">${dish.dishName }</a>
        <a>(${dish.dishTaste })</a>
      </li>
      <li class="introduce">
        ${dish.dishIntro }
      </li>
      <li class="price" id="price${dish.dishId }">
        ${dish.retailPrice }
      </li>
      <li class="addReduce">
        <span class="reduce" onclick="jian('${dish.dishId}')">-</span>
        <span class="num" id="num${dish.dishId }">0</span>
        <span class="add" onclick="add('${dish.dishId}')">+</span>
      </li>
    </ul>
  </li>
  <li class="line"></li>
  </c:forEach>
</ul>

<!-- 结算框 -->
<div class="all">
  <div class="allMoney">
    <div id="total">共<strong><span id="totalCount">0</span></strong>份,合计：￥
      <span id="totalCost">0</span>
    </div>
    <div id="clearAll">清空购物车</div>
    <div id="goOrder">去下单</div>
  </div>
</div>
</body>
</html>