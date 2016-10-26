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
<title>处理某商家预付款</title>
<link rel="stylesheet" type="text/css" href="res/css/admin/doAdvance.css">

  <script type="text/javascript" src="res/js/jQuery/jquery.min.js"></script> 
  <script type="text/javascript" src="res/js/admin/advance.js?verson=2"></script> 
  <script type="text/javascript" src="res/js/common.min.js"></script>     
  <script type="text/javascript" src="res/js/constants.min.js"></script>  
</head>
<body>
  <div class="handleAdvance">
    <div class="mark">
      <div><strong>商家名：</strong><span>${merchant.shopName }</span></div>
      <div><strong>时间段：</strong><span>${tSlot}</span></div>
    </div>
    <div class="oneMerchant">
      <table>
        <thead>
          <tr>
            <th>预付款编号</th>
            <th>订单号</th>
            <th>金额(元)</th>
            <th>用户收货状态</th>
            <th>退款状态</th>
            <th>用户支付时间</th>
            <th>处理情况</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="item" items="${advanceDetailList}" varStatus="status">
                  <tr>
                    <td>
                        ${item.advanceId}
                    </td>
                    <td>
                        ${item.orderId}
                    </td>
                    <td>
                        ${item.money}
                    </td>
                    <td>
                        <c:if test="${item.userStatus == 0}">
                                                          未收货
                        </c:if>
                        <c:if test="${item.userStatus == 1}">
                             <font style="color: red">确认收货</font>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${item.refund == 0}">
                                                          未退款
                        </c:if>
                        <c:if test="${item.refund == 1}">
                             <font style="background-color:#CCCCFF"> 申请退款</font>
                        </c:if>
                        <c:if test="${item.refund == 2}">
                              <font style="background-color:#C0C0C0">退款成功</font>
                        </c:if>
                    </td>
                   
                    <td>
                        ${item.advanceTime}
                    </td>
                    <td>
                    <c:choose>
                      <c:when test="${item.userStatus == 0}">
                          <c:if test="${item.refund  == 0}">
                                                         正在派送订单
                          </c:if>
                          <c:if test="${item.refund  == 1}">
                               <font style="background-color:#CCCCFF">正在处理退款</font>
                          </c:if>
                          <c:if test="${item.refund  == 2}">
                               <font style="background-color:#C0C0C0">已退款</font>
                          </c:if>
                      </c:when>
                      <c:when test="${item.userStatus  == 1}">
                          <font style="color: red">可生成付款单</font>
                      </c:when>
                    </c:choose>
                    </td>
                  </tr>
                </c:forEach> 
        </tbody>
      </table>
    </div>
    <div class="twoButton">
      <div class="backAdvanceButton"  onclick="contentSet('admin/advance.do');">返回</div>
      <div class="generateButton"  onclick="contentSet('admin/createAdvance.do?id=${merchant.merchantId}&month=${month}&year=${year}');">生成付款单</div>
    </div>
  </div>
  

</body>
</html>