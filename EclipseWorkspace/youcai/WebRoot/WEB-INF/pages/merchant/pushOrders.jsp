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
<title>商家抢单</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>res/css/merchant/pushOrders.css"/>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/jQuery/jquery.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/merchant/orderpush.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/constants.min.js"></script>
</head>
<body>
<div id="pushOrders">
					<div class="back">
						<a href="<%=basePath %>merchant/toMerchantIndex.do">返回主页</a>
					</div>
					<table id="tt">
						<caption>商家抢订单</caption>
						<thead>
							<tr>
								<th>订餐列表</th>
								<th>订单号</th>
								<th>选择配送时间</th>
								<th>抢单
									<input type="checkbox" id="selectAll" />(全选)
								</th>
							</tr>
							
						</thead>
						<tbody>
							 <!-- 一条信息 -->
						<!--	<tr>
								<td width="40%">
									<table>
										<tr>
											<td>菜名</td>
											<td>份数</td>
										</tr>
										<tr>
											<td>钵钵鸡</td>
											<td>1</td>
										</tr>
									</table>
								</td>
								<td>11</td>
								<td>
									<select>
										<option>20分钟</option>
										<option>30分钟</option>
										<option>40分钟</option>
									</select>
								</td>
								<td class="checkbox"><input type="checkbox"></td>	
							</tr>
							默认为展开的 -->
						</tbody>
							
						
					</table>
					<div class="signRead">
						<input type="button" value="确定抢单" class="sure-BTN">
					</div>
				</div>
</body>
</html>