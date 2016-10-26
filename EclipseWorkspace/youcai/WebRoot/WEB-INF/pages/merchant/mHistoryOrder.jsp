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
<title>商家获取历史订单</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>res/css/merchant/mHistoryOrder.css"/>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/jQuery/jquery.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/merchant/mHistoryOrder.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>res/js/constants.min.js"></script>
</head>
<body>
<div id="mHistoryOrder">
					<div class="back">
						<a href="<%=basePath %>merchant/toMerchantIndex.do">返回主页</a>
					</div>
					<table id="tt">
						<caption>商家历史订单表</caption>
						<thead>
							<tr>
								<th>查看详情</th>
								<th>订单号</th>
								<th>订餐者</th>
								<th>订餐时间</th>
								<th>支付总额(元)</th>
								<th>打分</th>
								<th>操作
									<input type="checkbox" id="selectAll" />(全选)
								</th>
							</tr>
							
						</thead>
						<tbody>							
						</tbody>
							
						
					</table>
					<div class="divPage" style="margin-left:20px;">
					
					<a href="javascript:void(0)" onclick="loadData('first')" class="first">首页</a>
					<a href="javascript:void(0)" onclick="loadData('pre')" class="pre">上一页</a>
					<input type="text" class="pageNow" style="width:25px" onblur="loadData('now')" value="1">/<span class="pageTotal">1</span>页
					<a href="javascript:void(0)" onclick="loadData('next')" class="next">下一页</a>
					<a href="javascript:void(0)" onclick="loadData('last')" class="last">尾页</a>
					<!-- <input type="hidden" value="2" class="pageTotal"/> -->
					</div>
					<div class="signRead">
						<input type="button" value="批量打分" class="evalue-BTN">
						<input type="button" value="标为已读" class="dele-BTN">
					</div>
				</div>
</body>
</html>