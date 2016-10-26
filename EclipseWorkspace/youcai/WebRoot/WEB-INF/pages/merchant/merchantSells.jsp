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
<title>商家销售额统计</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>res/css/merchant/merchantSells.css"/>
<script type="text/javascript" src="<%=basePath %>easyui/jquery-1.8.0.min.js"></script>
<%-- <link rel="stylesheet" type="text/css" href="<%=basePath %>easyui/themes/default/easyui.css">
<script type="text/javascript" src="<%=basePath %>easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>easyui/themes/icon.css">
<script type="text/javascript" src="<%=basePath %>easyui/jquery.easyui.min.js"></script> --%>
<%--  --%>
<script type="text/javascript" src="<%=basePath %>res/js/merchant/merchantSell.js" ></script>
<script type="text/javascript" src="<%=basePath %>res/js/constants.min.js" ></script>




</head>
<body>
	<div id="merchantSells">
					<div class="back">
						<a href="<%=basePath %>merchant/toMerchantIndex.do">返回主页</a>
					</div>

					<div class="lookfor">
								从:<input id="dateFrom"  type="text" readonly="readonly" value="2015-01-01" onclick="SetDate(this)" />
									<span style="width:100px"></span>		
								到:<input id="dateTo" type="text"  readonly="readonly" onclick="SetDate(this)"><br/><br/>

									<!-- 选择状态，可选项 -->
									<span >选择结算状态：</span>
									
									<select id="handelStatus" editable="fasle">  
    	 								<option value="all">全部</option>
		   								<option value="1">已结算</option>   
		   								<option value="0">未结算</option>   		    
									</select> 
									
									<!-- 选择退款状态，可选项 -->
									<span>退款状态：</span>
									
									<select id="refundStatus"  style="width:100px;display:inline;"  editable="fasle">   
										<option value="all">全部</option>
		  								<option value="0">未退款</option>   
		   								<option value="2">已退款</option>
		   								<option value="1">退款申请中</option>    		    
									</select>
									
									<a href="#" class="easyui-linkbutton"   onclick="doSearch()">查找</a>
						</div>
					<table id="tt">
						
						<thead>
							<tr>
								<th>订单号</th>
								<th>订单金额</th>
								<th>订单时间</th>
								<th>结算状态</th>
								<th>退款状态</th>
							</tr>
							
						</thead>
						<tbody>
							<!-- 一条信息 -->
							<!-- <tr>
								<td>44</td>
								<td>23</td>	
								<td>2015-07-01</td>	
								<td>0</td>	
								<td>1</td>		
							</tr>
							<tr>
								<td class="total" colspan="5">总计：
									销售额：<span>167885</span>
									订单总额：<span>185</span>
								</td>	
							</tr> -->	
						</tbody>													
					</table>
					<span style="margin-top:5px;margin-left:25px;font-size:15px;">备注：退款申请中的交易额不会被纳入到商家可得金额中</span>
						
	</div>
</body>
</html>