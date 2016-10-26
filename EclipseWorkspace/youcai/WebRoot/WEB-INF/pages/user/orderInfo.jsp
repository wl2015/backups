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
<title>订单详情</title>
<link rel="stylesheet" type="text/css" href="res/css/user/orderInfo.css">
<script type="text/javascript" charset="utf-8" src="res/js/jQuery/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8" src="res/js/user/orderInfo.js"></script>
<script type="text/javascript">
  function payOrder(){
    $("#payForm").submit();
  }

</script>
</head>

<body>
  <div class="backIndex">
  </div>
  <div class="orderInfo">
    <c:if test="${orderState ==1}">
      <div style="color: red">订单一</div>
    </c:if>
    <c:if test="${orderState ==2}">
      <div style="color: red">订单二</div>
    </c:if>
    <ul class="orderInfoList">
      <li><strong>订单号：</strong><span>${order.oId }</span></li>
      <li><strong>下单时间：</strong><span>${order.orderTime }</span></li>
      <li><strong>送餐地址：</strong><span>:${order.address }</span></li>
      <li><strong>预花费时间：</strong><span>${order.spendTime }</span></li>
      <li><strong>店名：</strong><span>${order.merchant.shopName }</span></li>
      <li><strong>联系方式：</strong><span>${order.merchant.linkPhone }</span></li>
      <li><strong>店家地址：</strong><span>${order.merchant.merchantAddress }</span></li>
      <li>
        <strong>菜品详情：</strong>
        <span>
          <ul class="dishesInfo">
            <li class="line">
              <span class="name">菜品名</span>
              <span>单价(元)</span>
              <span>数量(份)</span>
            </li>
            <c:forEach items="${order.dishlist }" var="dishlist">
            <li class="line">
              <span class="name">${dishlist.dish.dishName }</span>
              <span>${dishlist.price }</span>
              <span>${dishlist.number }</span>
            </li>
            </c:forEach>
            <li>总价：<span>${order.money }</span>元</li>
          </ul>
        </span>
        <!--支付信息提交表单  (暂时屏蔽，不管)-->
        <form id="payForm" action="wxpay/mainServlet.do" method="get">
        <input type="hidden" name="orderId" value="${order.oId }">
        <input type="hidden" name="money" value="${order.money  }">
        <input type="hidden" name="WIDbody" value="${order.merchant.shopName }">
        <input type="hidden" name="orderState" value="${orderState}">
  
        
        </form>
      </li>
      <li>
        <c:if test="${orderState == 0}">
          
          <!-- <div class="toPay" onclick="window.location=
          '<%=path %>/user/payForOrder.do'">去支付</div>  -->
          <div class="toPay" onclick="payOrder()">去支付</div>
        </c:if>
        <c:if test="${orderState == 1}">
          <div class="toPay" onclick="window.location=
          '<%=path %>/user/payForOrderOne.do'">去支付</div> 
          <!-- <div class="toPay" onclick="payOrder()">去支付</div> -->
        </c:if>
        <c:if test="${orderState == 2}">
           <div class="toPay" onclick="window.location=
          '<%=path %>/user/payForOrderTwo.do'">去支付</div> 
          <!-- <div class="toPay" onclick="payOrder()">去支付</div> -->
        </c:if>
      </li>
    </ul>
  </div>
  <!-- 显示返回主页按钮 -->
  <div class="backIndex" onclick="window.location=
      '<%=path%>/user/toUserMainPage.do'">返回主页</div>
</body>
</html>