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
<title>生成预付款统计单</title>
<link rel="stylesheet" type="text/css" href="res/css/admin/createAdvance.css">

  <script type="text/javascript" src="res/js/jQuery/jquery.min.js"></script> 
  <script type="text/javascript" src="res/js/admin/advance.js?verson=2"></script> 
  <script type="text/javascript" src="res/js/common.min.js"></script>     
  <script type="text/javascript" src="res/js/constants.min.js"></script>  
</head>
<body>
  <div class="generateAdvance">
    <div class="generateTable">
      <table>
        <thead>
          <tr>
            <th>商家编号</th>
            <th>商家名</th>
            <th>时间段</th>
            <th>总金额(元)</th>
            <th>付款单生成时间</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>${createAdvance.merchantId }<input type="hidden" id="merchantId" value="${createAdvance.merchantId }"/></td>
            <td>${createAdvance.shopName}<input type="hidden" id="shopName" value="${createAdvance.shopName }"/></td>
            <td>${createAdvance.timeSlot}<input type="hidden" id="timeSlot" value="${createAdvance.timeSlot }"/></td>
            <td>${createAdvance.totalMoney}<input type="hidden" id="totalMoney" value="${createAdvance.totalMoney }"/></td>
            <td>${createAdvance.createTime}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="backHandleButton" onclick="contentSet('admin/advance.do')">返回</div>
    <div class="transferButton" id="addCreateAdvance">添加款单</div>
  </div>
</body>
</html>