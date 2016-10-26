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
<title>用户退款申请</title>
<link rel="stylesheet" type="text/css" href="res/css/admin/userRefund.css">
  <script type="text/javascript" src="res/js/jQuery/jquery.min.js"></script> 
  <script type="text/javascript" src="res/js/admin/finance.js?verson=2"></script> 
  <script type="text/javascript" src="res/js/common.min.js"></script>     
  <script type="text/javascript" src="res/js/constants.min.js"></script>  

</head>
<body>
<div class="userRefund">
  <table>
    <caption align="top">用户退款申请</caption>  
    <thead>
      <tr>
        <th>订单编号</th>
        <th>申请用户</th>
        <th>状态</th>
        <th>申请时间</th>
        <th>操作</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${refundOrderList }" var="item" varStatus="status">
      <tr>
        <td>${item.orderId}</td>
        <td>${item.userName}</td>
        <td>
            <c:if test="${item.refund == 1}">
                <font style="color:#FF0F0F">待处理</font>
            </c:if>
            <c:if test="${item.refund == 2}">
                <font style="background-color:#C0C0C0">退款成功</font>
            </c:if>
            <c:if test="${item.refund == 3}">
                                   驳回退款
            </c:if>
        </td>
        <td>${item.time}</td>
        <td>
            <c:if test="${item.refund == 1}">
               <a href="javascript:void(0)" onclick="contentSet('admin/getRefundOrder.do?orderId=${item.orderId}')">处理</a>
            </c:if>
            <c:if test="${item.refund == 2}">
                <a href="javascript:void(0)" onclick="contentSet('admin/getRefundOrder.do?orderId=${item.orderId}')">详情</a>
            </c:if>
            <c:if test="${item.refund == 3}">
                <a href="javascript:void(0)" onclick="contentSet('admin/getRefundOrder.do?orderId=${item.orderId}')">详情</a>
            </c:if>
        </td>
      </tr>
      </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>