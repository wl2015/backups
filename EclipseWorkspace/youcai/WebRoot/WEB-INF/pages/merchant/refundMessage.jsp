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
<title>退单消息</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>res/css/merchant/refundMessage.css"/>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/jQuery/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>res/js/merchant/refundMessage.js" ></script>
<script type="text/javascript" src="<%=basePath %>res/js/constants.min.js" ></script>
</head>
<body>
<div id="refundMessage">
					<div class="back">
						<a href="<%=basePath %>merchant/toMerchantIndex.do">返回主页</a>
					</div>
					<table id="tt">
						<caption>退单消息表</caption>
						<thead>
							<tr>
								<th>查看详情</th>
								<th>退单号</th>
								<th>订单ID</th>
								<th>用户名</th>
								<th>用户联系电话</th>
								<th>交易额(元)</th>
								<th>罚金(元)</th>
								<th>操作
									<input type="checkbox" id="selectAll" />(全选)
								</th>
							</tr>
							
						</thead>
						<tbody>
						</tbody>												
					</table>
					<div class="signRead">
						<input type="button" value="标为已读" id="SURE_BTN">
					</div>
				</div>
</body>
</html>