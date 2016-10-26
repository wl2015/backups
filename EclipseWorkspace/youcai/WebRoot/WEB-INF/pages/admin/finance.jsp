<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>财务统计</title>
<link rel="stylesheet" type="text/css" href="res/css/admin/finance.css">

  <script type="text/javascript" src="res/js/jQuery/jquery.min.js"></script> 
  <script type="text/javascript" src="res/js/admin/finance.js?verson=2"></script>
  <script type="text/javascript" src="res/js/common.min.js"></script>     
  <script type="text/javascript" src="res/js/constants.min.js"></script> 
</head>
<body>
  <div class="finance">
    <div class="tools">
      <span>FROM:</span> <input name="txtDate2" type="text" value="2015-1-1" style="padding-left:5px;" id="dateFrom" onclick="SetDate(this)" readonly="readonly" />   
      <span>TO:</span> <input name="txtDate2" type="text" value="2015-1-1" style="padding-left:5px;" id="dateTo" onclick="SetDate(this)" readonly="readonly" />   
      <button type="button" id="queryFinance">财务统计</button>
      <span id="totalMoney"></span>
    </div>
    <div class="tb">
      <table  id="financeList">
        <caption align="top">财务统计</caption>  
        <thead>
          <tr>
            <th>菜名</th>
            <th>中心销售价</th>
            <th>销售总量</th>
            <th>销售总额</th>
            <th>时间段</th>
          </tr>
        </thead>
        
      </table>
      
    </div>
  </div>
</body>
</html>