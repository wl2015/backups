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
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<title>未完成订单</title>
<link rel="stylesheet" type="text/css" href="res/css/user/unfinishedOrders.css">
<script type="text/javascript" charset="utf-8" src="res/js/jQuery/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8" src="res/js/user/unfinishedOrders.js"></script>
</head>
<body>
  <div class="title">
    <span class="unfinished">未完成订单</span>
    <span class="finished" onclick="window.location=
    '<%=path %>/user/toGetFinishedOrders.do'">已完成订单</span>
  </div>
  <div class="content">
  
    <!-- 订单 -->
    <c:forEach items="${orderslist }" var="order">
    <form action="<%=path %>/user/toEvaluateOrder.do" name="sure${order.oId }" method="post">
      <input type="text" style="display: none;" name="orderId" value="${order.oId }"/>
    </form>
    <div class="oneOrder">
      <ul class="list">
        <li class="list1">
          <div>订单号：<span>${order.oId }</span></div>
          <div>${order.orderTime }</div>
        </li>
        <li class="list2"><span>${order.merchant.shopName }</span></li>
        <li class="list3">电话：<span>${order.merchant.linkPhone }</span></li>
        <li class="list4">送餐地址：<span>${order.address }</span></li>
        <li class="detail"><a href="javascript:void(0)" class="detailButton">订单详情</a>
          <div class="detailList">
            <ul>
              <li>
                <span>菜品名</span>
                <span>价格(元)</span>
                <span>数量</span>
              </li>
              <c:forEach items="${order.dishlist }" var="dishlist">
              <li>
                <span>${dishlist.dish.dishName }</span>
                <span>${dishlist.price }</span>
                <span>${dishlist.number }</span>
              </li>
              </c:forEach>
            </ul>
            <div class="divTotal">总价：<span>${order.money }</span>元</div>
          </div>
        </li>
        <li class="list5">
          <c:if test="${order.refund==0 }">
          <div class="divRefund" onclick="window.location='<%=path %>/user/toApplyRefund.do?orderId=${order.oId }'">申请退款</div>
          <div class="divSure" onclick="document.sure${order.oId }.submit()">确认收货</div>
          </c:if>
          <c:if test="${order.refund!=0 }">
          <div class="divHadRefund">正在退款</div>
          </c:if>
        </li>
      </ul>
    </div>
    </c:forEach>
    
  </div>
  
  <!-- 显示返回主页按钮 -->
  <div class="backIndex" onclick="window.location=
      '<%=path%>/user/toUserMainPage.do'">返回主页</div>
</body>
</html>