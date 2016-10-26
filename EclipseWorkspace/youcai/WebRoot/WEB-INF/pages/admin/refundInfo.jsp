<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>退款详情</title>
<link rel="stylesheet" type="text/css" href="res/css/admin/refundInfo.css"/>

  <script type="text/javascript" src="res/js/jQuery/jquery.min.js"></script> 
  <script type="text/javascript" src="res/js/admin/finance.js?verson=2"></script> 
  <script type="text/javascript" src="res/js/common.min.js"></script>     
  <script type="text/javascript" src="res/js/constants.min.js"></script>  
</head>
<body>
<div class="refundInfo">
  <div class="back" onclick="contentSet('admin/queryRefundOrderList.do')">返回</div>
  <ul class="ul1">
    <li class="list1"><strong>订单号：</strong><span>${refundOrder.orderId}</span></li>
    <li class="list1"><strong>用户：</strong><span>${refundOrder.userName}</span></li>
    <li class="list1"><strong>商家：</strong><span>${refundOrder.shopName}</span></li>
    <li class="list1"><strong>送餐地址：</strong><span>${refundOrder.address}</span></li>
    <li class="list1"><strong>订单时间：</strong><span>${refundOrder.orderTime}</span></li>
    <li class="list1"><strong>申请退款时间：</strong><span>${refundOrder.time}</span></li>
    <li class="listDish"><strong>菜品详情：</strong>
      <div>
        <ul class="ul2">
          <li>
            <span>菜品名</span><!--  
            --><span>价格(元)</span><!-- 
             --><span>数量</span>
          </li>
          <c:forEach items="${refundOrder.dishlist }" var="item" varStatus="status">
          <li>
            <span>${item.dishName}</span><!--  
            --><span>${item.price}</span><!-- 
            --><span>${item.number}</span>
          </li>
          </c:forEach>
        </ul>
        <div class="divTotal">总价：<span>${item.totalMoney}</span>元</div>
      </div>
    </li>
    <li class="refundReason"><strong>申请退款理由：</strong>
      <div>${refundOrder.content}</div>
    </li>
    <li class="list5">
         <c:if test="${refundOrder.refund == 1}">
          <div class="divRefund" onclick="contentSet('admin/rejectRefundOrder.do?id=${refundOrder.orderId}')">驳回退款</div>
          <div class="divSure" onclick="contentSet('admin/payRefundOrder.do?id=${refundOrder.orderId}')">确认退款</div>
         </c:if>
         <c:if test="${refundOrder.refund == 2}">
          <a href="javascript:void(0)">删除此退款单</a>
         </c:if>
          
        </li>
  </ul>
  
</div>
</body>
</html>