<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>My JSP 'total.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="../js/jquery-1.4.2.min.js"></script>
	<script  language="javascript" type="text/javascript" src="../js/hover.js"></script>
		 <style>
			.fofa{
				font-family:"Microsoft YaHei","微软雅黑","SimSun","宋体";}
		 </style>
  </head>
  
 <body scroll=no topmargin="0" leftmargin="0" style="overflow:hidden">
		<table width="100%" height="100%" border="0" align="center"
			cellpadding="0" cellspacing="0">
			<tr>
				<td colspan="3" width="100%" height="70">
					<iframe frameBorder="0" width="100%" height="70" src="top.jsp" scrolling="no"></iframe>
				</td>
			</tr>
			<tr>
				<td id='shang' width="172" height="100%">
					<iframe frameBorder="0" id="left" name="left" src="left.jsp"
						height="100%" width="172" target="main">
					</iframe>
				</td>
				<td align="center" class="unnamed1" onClick="mfk();" height="100%"
				id="c" onmouseover="shouZ('c');">
					<table width="100%" height="100%" border="0" cellpadding="0"
						cellspacing="0">
						<tr>
							<td bgcolor="#f7deb9">
								<div style="width:16px;color:#8b3c05" class="fofa">点击切换</div>
							</td>
						</tr>
					</table>
				</td>
				<td width="100%" height="100%">
					<iframe frameBorder="0" id="main" name="main" scrolling="yes"
						src="right.jsp" height="100%" width="100%"></iframe>
				</td>
			</tr>
		</table>
	</body>
</html>
