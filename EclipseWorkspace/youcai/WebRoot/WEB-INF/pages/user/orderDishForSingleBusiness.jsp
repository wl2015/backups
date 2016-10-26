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
<!-- 设置屏幕按1：1的尺寸显示，在 iPhone 和其他智能手机的浏览器提供网站全视图浏览，并禁止用户缩放页面 -->
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>点菜</title>
<link rel="stylesheet" type="text/css" href="res/css/user/orderDishForSingleBusiness.css"/>
<link rel="stylesheet" type="text/css" href="res/css/user/allSets2.css"/>
<script charset="utf-8" src="res/js/jQuery/jquery.min.js"></script>
<script charset="utf-8" src="res/js/user/orderDishForSingleBusiness.js"></script>
<script type="text/javascript" charset="utf-8" src="res/js/constants.min.js"></script>
</head>
<body>
  <div class="orderDish">
    <!-- 点菜页面  返回导航 -->
    <div class="backImg"><img src="res/img/user/1-1.png"/><span>点菜</span></div>
    <!-- 点菜导航 -->
    <ul class="orderNav clearfix">
      <li class="oneSelect navActive" id="allDishes">全部菜品</li>
      <li class="oneSelect">
        <span class="bigTitle">菜品分类</span>
        <span class="trangle"></span>
        <ul class="dishSelect select">
          <c:forEach items="${dishTypeList }" var="dishType">
            <li id="${dishType.typeId }">${dishType.typeName }</li>
          </c:forEach>
        </ul>
      </li>
      <li class="oneSelect" id="AlldishGroups">全部套餐</li>
      <li class="oneSelect" onclick="allDishesSet('setfenlei2.jsp');">
        <span class="bigTitle">套餐分类</span>
        <span class="trangle"></span>
        <ul class="setSelect select">
          <c:forEach items="${dishGroupTypeList }" var="dishGroupType">
            <li id="${dishGroupType.typeId }">${dishGroupType.typeName }</li>
          </c:forEach>
        </ul>
      </li>
    </ul>
    <!-- 全部菜品 -->
    <div class="allDishes">
      <!-- 显示菜品 -->
      <ul class="allDishesUl">
      <c:forEach items="${dishList }" var="dish">
        <li class="oneDish">
          <div class="oneDishTop clearfix">
            <div class="oneDishTopLeft"><a href="#"><img src="res/img/food.jpg"/></a></div>
            <div class="oneDishTopRight">
              
              <p class="dishTitle"><a href="oneDishDetail.jsp" class="dishName">
                ${dish.dishName }</a>
                <span class="dishTaste">(${dish.dishTaste })</span>
              </p>
              <p class="inventory">库存[<span class="inventoryNum" 
              id="${dish.dishId}">${dish.count }</span>]</p>
              <p class="dishIntro">${dish.dishIntro }</p>
            </div>
          </div>
          <div class="oneDishBottom clearfix">
            <div class="dishPrice">
              <p>原价：￥<span>${dish.originalPrice }</span></p>
              <p>现价：￥<span id="price${dish.dishId }">${dish.retailPrice }</span></p>
            </div>
            <div class="dishOperate clearfix">
              <span class="reduce" onclick="jian('${dish.dishId}')">-</span>
              <span class="copies" id="num${dish.dishId}">0</span>
              <span class="add" onclick="add('${dish.dishId}')">+</span>
            </div>
          </div>
          <!-- 菜品类别标记 -->
          <ul class="mark clearfix">
            <c:forEach items="${dish.typeList }" var="type">
              <li>${type.typeName }</li>
            </c:forEach>
          </ul>
        </li>
      </c:forEach>
      </ul>
      <!-- 显示套餐 -->
      <ul class="allSets">
      </ul>
    </div>
    
    <!-- 显示购物车 -->
    <div class="myCar clearfix">
      <span class="allMoney">共￥<span id="totalCost">0</span>元</span>
      <span class="clearCar">清空购物车</span>
      <span class="goOrder">去下单</span>
    </div>
  </div>
</body>
</html>