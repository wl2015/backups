<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加宿舍楼</title>
<link rel="stylesheet" href="<%=path %>/res/css/admin/demo.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/easyui.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/icon.css"/>
<script src="<%=path %>/res/js/jQuery/jquery.min.js"></script>
<script src="<%=path %>/res/js/jQuery/jquery.easyui.min.js"></script>
<script src="<%=path %>/res/js/admin/college.js"></script>
</head>
<body>
  <span>需先选择学校才能添加宿舍</span>
  <hr>
  <form action="<%=path %>/admin/addNewDormitory" id="dormitoryForm" method="post">
  <select id="select_college" name="campusId" style="width:300px;float:left">
    <c:forEach items="${campusBeans }" var="campus" varStatus="state">
    <option value="${campus.campusId }">${campus.campusName }</option>
    </c:forEach>
  </select>
  
  <span style="float:left;padding-left:10px">宿舍楼：</span>
  <input type="text" name="dormitoryName" style="width:10%;float:left" id="floor"/>（如：X 栋/楼）
  <button class="easyui-linkbutton" id="sure" onclick="document.getElementById("dormitoryForm").submit();" iconCls="icon-ok">确认</button>
  </form>
  <!-- <div id="tt" class="easyui-tabs" style="width:auto;height:0;padding-top:10px"> 
  </div>-->
</body>
</html>