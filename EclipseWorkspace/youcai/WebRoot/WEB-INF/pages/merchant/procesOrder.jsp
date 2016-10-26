<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商家未完成订单列表</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>res/css/merchant/procesOrder.css"/>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/jQuery/jquery.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/merchant/procesOrder.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/constants.min.js"></script>
</head>
<body>
<div id="procesOrder">
					<div class="back">
						<a href="<%=basePath %>merchant/toMerchantIndex.do">返回主页</a>
					</div>
					<table id="tt">
						<caption>商家未完成订单表</caption>
						<thead>
							<tr>
								<th width="6%">订餐列表</th>
								<th width="6%">订单号</th>
								<th width="10%">订餐人</th>
								<th width="15%">联系电话</th>
								<th>订餐时间</th>
								<th width="6%">送餐时间</th>
								<th width="30%">送餐地址</th>
								<th width="7%">支付总额(元)</th>								
							</tr>
							
						</thead>
						<tbody>
						</tbody>
					</table>					
				</div>
</body>
</html>