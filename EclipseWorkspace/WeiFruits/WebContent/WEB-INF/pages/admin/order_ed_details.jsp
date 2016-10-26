<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>已完成订单详细信息</title>
<style>
  th{
    background: #81CFE0;
  }
</style>
</head>
<body>
  <table class="easyui-datagrid" border="0" style="width:100%;height:auto;">
    <thead>
       <tr>
         <th field="productName" align="center" width="18%">水果名称</th>
         <th field="orderTime" align="center" width="20%">单价（元/份）</th>
         <th field="buyer" align="center" width="20%">数量（份）</th>
         <th field="status" align="center" width="23%">小计（元）</th>
       </tr>
    </thead>
    <c:forEach items="${ordersBean.fruitBeans }" var="fruit">
    <tr>
      <td>${fruit.fruitName }</td>
      <td>${fruit.fruitPrice }</td>
      <td>${fruit.count }</td>
      <td>${fruit.fruitPrice * fruit.count }</td>
    </tr>
    </c:forEach>
    <tr>
      <td class="dv-label" style="color:#EF4836">总计（元）：</td>
      <td>${ordersBean.money }</td>
      <td class="dv-label" style="color: #EF4836">地址：</td>
      <td>${ordersBean.campusName }${ordersBean.dormitoryName }${ordersBean.address }</td>
    </tr>
<!--     <tr>
      <td class="dv-label" style="color: #EF4836">编号：</td>
      <td>1</td>
      <td class="dv-label" style="color: #EF4836">名称：</td>
      <td>苹果</td>
    </tr>
    <tr>
      <td class="dv-label" style="color: #EF4836">单价：</td>
      <td>1.9元/份</td>
      <td class="dv-label" style="color: #EF4836">购买数量：</td>
      <td>2份</td>
    </tr>
    <tr>
      <td class="dv-label" style="color: #EF4836">总计：</td>
      <td>3.8元</td>
      <td class="dv-label" style="color: #EF4836">购买者电话：</td>
      <td>13112345678</td>
    </tr>
    <tr>
      <td class="dv-label" style="color:#EF4836">购买者留言：</td>
      <td>谢谢！</td>
      <td class="dv-label" style="color:#EF4836">送货地址：</td>
      <td>成都信息工程大学 6栋宿舍楼 4012</td>
      <td class="dv-label" style="color:#EF4836">是否为自提取货：</td>
      <td>否</td>
    </tr> -->
  </table>
</body>
</html>