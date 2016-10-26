<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>My JSP 'detailD.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="../../css/right.css">
	<script src="../js/jquery-1.4.2.min.js"></script>
  </head>
  
  <body>
	<table width="100%" cellspacing="2" cellpadding="4" bgcolor="#fbead0">
		<tr>
			<td colspan="15" class="header">
			<div style="float:left">出货单-详情</div>
			<div style="float:right">
			<input type="button" value="返回" class="border2" onclick="javascript:location.href='deorder.html'"/></div>
			</td>
		</tr>
		<tr>
			<td class="td3">订单号:</td>
			<td class="td4">12312323213123</td>
		</tr>
		<tr>
			<td class="td3">食客姓名:</td>
			<td class="td4">张三</td>
		</tr>
		<tr>
			<td class="td3">联系方式:</td>
			<td class="td4">1880448909</td>
		</tr>
		<tr>
			<td class="td3">详细地址:</td>
			<td class="td4">成都市成都信息工程学院学府路一段</td>
		</tr>
		<tr>
			<td class="td3">生成时间:</td>
			<td class="td4">12月3日</td>
		</tr>
		<tr>
			<td class="td3">送达时间:</td>
			<td class="td4">12月3日</td>
		</tr>
		<tr>
			<td class="td3">配送员:</td>
			<td class="td4">一哥</td>
		</tr>
	</table>
	<table  width="100%" cellspacing="2" cellpadding="4">
		<tr class="header" align="center">
			<td style="width:30%;">菜品名</td>
			<td>口味</td>
			<td>单价(元)</td>
			<td>数量(份)</td>
			<td>合计(元)</td>	
		</tr>
		<tr class="td5">
			<td>青椒土豆</td>
			<td>微辣</td>
			<td>10</td>
			<td>3</td>
			<td>30</td>
		</tr>	
		<tr class="td5">
			<td>鱼香肉丝</td>
			<td>辣</td>
			<td>12</td>
			<td>4</td>
			<td>48</td>
		</tr>
		<tr>
			<td class="td6">送餐费（元）：</td>
			<td colspan="4" class="td6"></td>
		</tr>
		<tr>
			<td class="td6">总计（元）：</td>
			<td class="td6" colspan="4"></td>
		</tr>
		<tr>
			<td class="td6">是否已支付：</td>
			<td class="td6" colspan="4"></td>
		</tr>
	</table>
  </body>
</html>
