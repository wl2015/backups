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
<title>商家预付款单</title>
<link rel="stylesheet" type="text/css" href="res/css/admin/mAdvance.css">

  <script type="text/javascript" src="res/js/jQuery/jquery.min.js"></script> 
  <script type="text/javascript" src="res/js/admin/finance.js?verson=2"></script> 
  <script type="text/javascript" src="res/js/common.min.js"></script>     
  <script type="text/javascript" src="res/js/constants.min.js"></script>  
</head>
<body>
  <div class="mAdvance">
    <div class="generateTable">
      <table>
        <caption align="top">商家预付款单</caption>  
        <thead>
          <tr>
            <th>商家编号</th>
            <th>商家名</th>
            <th>时间段</th>
            <th>总金额(元)</th>
            <th>付款单生成时间</th>
            <th>状态</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="item" items="${createAdvanceList}" varStatus="status">
          <tr>
          
            <td>${item.merchantId }</td>
            <td>${item.shopName}</td>
            <td>${item.timeSlot}</td>
            <td>${item.totalMoney}</td>
            <td>${item.createTime}</td>
            <td>
              <c:if test="${item.createAdvanceStatus == 0}" >
                                  <span style="color:red">未打款</span> 
              </c:if>
              <c:if test="${item.createAdvanceStatus == 1}">
                                    已打款
              </c:if>
            </td>
             <td><a href="javascript:void(0)" onclick="contentSet('admin/payCreateAdvance.do?id=${item.createId}')">确认打款</a></td>
          </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
      <div class="backHandleButton" onclick="contentSet('admin/advance.do')">返回</div>
  </div>
</body>
</html>