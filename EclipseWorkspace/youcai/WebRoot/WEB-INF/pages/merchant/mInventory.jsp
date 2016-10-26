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
<title>商家查看与修改库存</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>res/css/merchant/mInventory.css"/>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/jQuery/jquery.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/merchant/mInventory.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/constants.min.js"></script>
</head>
<body>
<div id="mInventory">
					<div class="back">
						<a href="<%=basePath %>merchant/toMerchantIndex.do">返回主页</a>
					</div>
					<table id="tt">
						<caption>商家库存(注意：减少量不能大于余量，增加量不能大于可增加数量)</caption>
						<thead>
							<tr>
								<th>菜名</th>
								<th>余量</th>
								<th>需减少量(-)</th>
								<th>需增加量(+)</th>
								<th>可增加量</th>
								<th>最后修改时间</th>
							</tr>
							
						</thead>
						<tbody>
							<!-- 一条信息 -->
							<!-- <tr>
								<td>钵钵鸡</td>
								<td>11</td>
								<td>
									<input type="text" value="1">
								</td>
								<td>
									<input type="text" value="3">
								</td>
								<td>可增加量</td>
								<td>2014-10-12</td>	
							</tr> -->
							
							
							
							
						</tbody>
							
						
					</table>
					<div class="signRead">
						<input type="button" value="确认修改" class="Update-BTN">
					</div>
				</div>
</body>
</html>