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
<title>选择商家</title>
<link rel="stylesheet" type="text/css" href="res/css/user/chooseMerchants.css">
<script type="text/javascript" charset="utf-8" src="http://api.map.baidu.com/api?v=2.0&ak=mbLgt6OQ49mT8is4yTtzhgFK"></script>
<script type="text/javascript" charset="utf-8" src="res/js/jQuery/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8" src="res/js/constants.min.js"></script>
<script type="text/javascript" charset="utf-8" src="res/js/user/chooseMerchants.js"></script>
</head>
  
<body onload="addMap(${pointLeft},${pointRight },${merchantOne.merchantLng },${merchantOne.merchantLat },${merchantTwo.merchantLng },${merchantTwo.merchantLat })">
  <div class="box">
    <div class="merchantMap" id="allmap"></div>

    <!-- 推送商家一信息 -->
    <div class="merchantInfo">
      <div class="leftPhoto">
        <img src="res/img/food.jpg"/>
      </div>
      <div class="rightInfo">
        <ul>
          <li>${merchantOne.shopName }</li>
          <li>星级:<span>${merchantOne.merchantStar }级</span></li>
          <li>联系方式：${merchantOne.linkPhone }</li>
          <li>地址：${merchantOne.merchantAddress }</li>
        </ul>
      </div>
      <!--店铺简介 -->
      <div class="storeIntro">
        <span>商家简介：</span><p>${merchantOne.merchantIntro }</p>
      </div>
    
      <!--历史最多评价 -->
      <div class="evaluate">
        <span>历史最多评价：</span>
          <p>${merchantOne.merchantComment }</p>
      </div>
      <!-- 菜品信息 -->
      <div class="detailList">
      <ul>
        <li>
          <span>菜品名</span>
          <span>价格(元)</span>
          <span>数量</span>
        </li>
        <c:forEach items="${merchantOne.dishlist }" var="dishlist">
        <li>
          <span>${dishlist.dish.dishName }</span>
          <span>${dishlist.price }</span>
          <span>${dishlist.number }</span>
        </li>
        </c:forEach>
      </ul>
      </div>
    </div>
    
    <!-- 推送商家二信息 -->
    <div class="merchantInfo">
      <div class="leftPhoto">
        <img src="res/img/food.jpg"/>
      </div>
      
      <div class="rightInfo">
        <ul>
          <li>${merchantTwo.shopName }</li>
          <li>星级:<span>${merchantTwo.merchantStar }级</span></li>
          <li>联系方式：${merchantTwo.linkPhone }</li>
          <li>地址：${merchantTwo.merchantAddress }</li>
        </ul>
      </div>
      <!--店铺简介 -->
      <div class="storeIntro">
        <span>商家简介：</span><p>${merchantTwo.merchantIntro }</p>
      </div>
    
      <!--历史最多评价 -->
      <div class="evaluate">
        <span>历史最多评价：</span>
          <p>${merchantTwo.merchantComment }</p>
      </div>
      
      <!-- 菜品信息 -->
      <div class="detailList">
	      <ul>
	        <li>
	          <span>菜品名</span>
	          <span>价格(元)</span>
	          <span>数量</span>
	        </li>
	        <c:forEach items="${merchantTwo.dishlist }" var="dishlist">
	        <li>
	          <span>${dishlist.dish.dishName }</span>
	          <span>${dishlist.price }</span>
	          <span>${dishlist.number }</span>
	        </li>
	        </c:forEach>
	      </ul>
      </div>
    </div>
    <form action="<%=path %>/user/recordChoseMerchants.do" name="choose" 
      method="post">
      <input type="text" style="display: none;" name="merchantOneId" value="${merchantOne.merchantId }"/>
      <input type="text" style="display: none;" name="merchantTwoId" value="${merchantTwo.merchantId }"/>
    </form>
    <div class="sureAndExit">
      <div class="button2" onclick="document.choose.submit()">确认</div>
      <div class="button2" onclick="window.location=
      '<%=path %>/user/toUserMainPage.do'">退出点餐</div>
    </div>
    
    
  </div>
</body>
</html>
