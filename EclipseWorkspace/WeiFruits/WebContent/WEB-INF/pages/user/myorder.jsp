<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" 
      + request.getServerPort() + path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<%=path %>/res/css/bootstrap.min.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/user/order.css"/>
<script src="<%=path %>/res/js/jQuery/jquery.min.js"></script>
<script src="<%=path %>/res/js/user/order.js"></script>
<title>我的订单</title>
<style>
  *{
    padding: 0;
    margin: 0;
  }
  body {
    font-family: "Arial","Microsoft YaHei","Lucida Grande","宋体",sans-serif;
    background: #FDE3A7;
  }
  .list-group{
    width: 100%;
    height: 33px;
    text-align: center;
    position: relative;
    left: 0;
    top: 0;
  }
  .list-group a {
    color: inherit;
    width: 24%;
    height: 33px;
    text-align: center;
    font-size: 12pt;
    padding-top: 8px;
    display: inline-block;
    transition: 0.2s linear;
    text-decoration: none;
    border: 1px solid #F89406;
  }
  a{
    text-decoration: none;
  }
</style>
</head>
<body>
    <h4>我的订单</h4>
    <hr>
    <div class="list-group" id="list">
      <a href="#" style="background:#F9690E">未完成订单</a>
      <a href="<%=path %>/user/FinishedOrder">已完成订单</a>
    </div>
    
    <div id="myTabcontent" class="tab-content">
    <!-- 未完成订单 -->
      <div class="tab-pane fade in active" id="nofinish">
        <div class="row">
          <div class="col-xs-12" id="col" style="background:#89C4F4">
            <h4>物品</h4>
          </div>
        </div>
      
      <c:if test="${ordersBeans == null}">没有未完成的订单</c:if>

      <c:forEach items="${ordersBeans}" var="order">
        <div class="row">
          <div class="col-xs-12" id="content">
            <ul>
              <c:forEach items="${order.fruitBeans }" var="fruit">
              <li class="goodname">${fruit.fruitName }${fruit.count }份</li>
              </c:forEach>
              <li class="details">订单号:${order.orderId }总金额:${order.money }元 
                <c:if test="${order.orderStatus == 0 }">（商家还未接受）</c:if>
                <c:if test="${order.orderStatus == 1 }">（已经开始派送）</c:if>
              </li>
              <li class="details">支付宝（等待付款）
                <a class="dd">详情</a>
              </li>
            </ul>
          </div>
        </div>
          <ul class="de" style="display:none;background:#ECF0F1;">
            <li class="details">收货人姓名：${order.receiveName }  电话：${order.receivePhone }</li>
            <li class="details">收获地址：${order.campusName }${order.dormitoryName }${order.address }</li>
            <li class="details">收获方式：
              <c:if test="${order.receiveWay == 0 }">送货上门</c:if>
              <c:if test="${order.receiveWay == 1 }">上门自取</c:if>
            </li>
          </ul>
       </c:forEach>
        
    </div>
</body>
</html>