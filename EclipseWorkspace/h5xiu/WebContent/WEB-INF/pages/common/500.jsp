<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path=request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>500|上网定婚礼平台</title>
<link rel="stylesheet" href="<%=path%>/res/css/static/error.css">
<script type="text/javascript" charset="utf-8" src="<%= path%>/res/js/jQuery/jquery.js"></script>
<script type="text/javascript" charset="utf-8" src="<%= path%>/res/js/static/error.js"></script>
</head>
<body>
	<div id="page-error">
		<h1>
			<a href="<%=path %>/home.html" title="上网定婚礼平台">
				<img class="logo" alt="上网定婚礼平台" title="上网定婚礼平台" src="<%=path %>/res/img/htmledition/s/shangwangding.png" />
			</a>
		</h1>
		<p>
			<span class="back">
				<a id="errorFlag" href="javascript:;"><span id="time">5</span>秒后，返回上网定首页</a>
			</span>
		</p>
		<div class="img-500">500</div>
		<h2 class="msg-error">500 服务器异常</h2>
	</div>
</body>
</html>