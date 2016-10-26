<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示所有套餐的页面</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>res/css/admin/setPage.css">
<script type="text/javascript" src="<%=basePath %>res/js/jQuery/jquery.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/constants.min.js"></script>
<script type="text/javascript" src="<%=basePath %>res/js/admin/setPage.js" ></script>

</head>
<body>
  <div class="setPage">
    <div class="addSet">新增套餐</div>
    <ul class="setPageUl">
    <c:forEach var="item" items="${dishGroupList}" varStatus="status"> 
      <br>
      <li class="oneSet">
        <span class="setName">${item.groupName }</span>
        <c:forEach var="dish" items="${item.dishList}" varStatus="status">
          <span class="dishName">${dish.dishName }</span><span class="dishNum">(<strong>${dish.num }</strong>)份</span><span class="add">+</span>                    
        </c:forEach>
        <br>
        <span class="dishPrice">原价：${item.originalPrice }元</span>
        <span class="dishPrice">售价：${item.retailPrice }元</span>
        <a href="#" data="${item.groupId }" class="delete">删除</a>
      </li>
      <br>
    </c:forEach> 
      
    </ul>
  </div>
</body>
</html>