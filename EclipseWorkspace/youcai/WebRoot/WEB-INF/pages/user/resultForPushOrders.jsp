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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<title>范围内木有商家</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>res/css/user/resultForPushOrders.css">
</head>
<body>
  <div class="box">
    <c:if test="${isHaveAllDishes == null }">
      <c:if test="${isMerchantAccpet == null }">
        <div class="tip">该范围内暂时木有商家哦~</div>
      </c:if>
      <c:if test="${isMerchantAccpet == 'no' }">
        <div class="tip">商家没有接受订单</div>
      </c:if>
    </c:if>
    <c:if test="${isHaveAllDishes == 'yes' }">
      <div class="tip">在范围内必须要三个或以上才能满足全部订单</div>
    </c:if>
    <c:if test="${isHaveAllDishes == 'no' }">
      <div class="tip">
        <c:forEach items="${dishlist}" var="dish">
          ${dish.dishName }
        </c:forEach>
        这些菜品该地区没有
      </div>
    </c:if>
  </div>
  <div class="backIndex" onclick="window.location=
      '${pageContext.request.contextPath }/user/toUserMainPage.do'">返回主页</div>
  
</body>
</html>