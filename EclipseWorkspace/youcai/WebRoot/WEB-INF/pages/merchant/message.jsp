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
<title>系统消息</title>
<script type="text/javascript" src="<%=basePath%>res/js/jQuery/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>res/css/merchant/message.css"/>
<script type="text/javascript" src="<%=basePath%>res/js/merchant/message.js" ></script>
<script type="text/javascript" src="<%=basePath%>res/js/constants.min.js" ></script>
</head>
<body>
<div id="message">
					<div class="back">
						<a href="<%=basePath %>merchant/toMerchantIndex.do">返回主页</a>
					</div>
					<table id="tt">
						<caption>系统消息表</caption>
						<thead>
							<tr>
								<th>编号</th>
								<th>信息</th>
								<th>时间</th>
								<th>操作
									<input type="checkbox" id="selectAll" />(全选)							
								</th>
							</tr>
						</thead>
						<tbody>
							
						</tbody>
							
						
					</table>
					<div class="delete">
						<input type="button" value="标为已读" id="delete-BTN">
					</div>
				</div>
</body>
</html>