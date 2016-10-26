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
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>预付款处理</title>
<link rel="stylesheet" type="text/css" href="res/css/admin/advance.css">

  <script type="text/javascript" src="res/js/jQuery/jquery.min.js"></script> 
  <script type="text/javascript" src="res/js/admin/advance.js?verson=2"></script> 
  <script type="text/javascript" src="res/js/common.min.js"></script>     
  <script type="text/javascript" src="res/js/constants.min.js"></script>  

</head>
<body>
  <div class="advance">
    <div class="tools">
      月份选择：<input id="timeSolt" type="text" style="padding-left:5px;" id="txtDate" onclick="SetDate(this)" readonly="readonly" value="2015-08"/>  
            <button type="button" id="queryAdvance">预付款查询</button>
    </div>
    <div class="advanceTable">
      <table id="advanceList">
        <thead>
          <tr>
            <th>商家编号</th>
            <th>商家名</th>
            <th>预付款数量</th>
            <th>操作</th>
          </tr>
        </thead>
        
      </table>
    </div>
  </div>
</body>
</html>