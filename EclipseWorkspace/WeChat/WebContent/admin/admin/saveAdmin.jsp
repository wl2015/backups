<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="../../css/right.css">
	</head>
	<body>
	<table width="100%" cellspacing="2" cellpadding="4"bgcolor="#fbead0">
		<tr>
			<td colspan="15" class="header">
			<div style="float:left">管理员信息</div>
			<div style="float:right"><input type="button" value="返回" class="border2" onclick="javascript:location.href='admin.html'"/></div>
			</td>
		</tr>
		<tr>
			<td class="td3">管理员名称:</td>
			<td class="td4"><input type="text" size="30" maxlength="30"/></td>
		</tr>
		<tr>
			<td class="td3">密码:</td>
			<td class="td4"><input type="password" size="32" maxlength="30"/></td>
		</tr>
		<tr>
			<td class="td3">确认密码:</td>
			<td class="td4"><input type="password" size="32" maxlength="30"/></td>
		</tr>
		<tr>
			<td class="td3"></td>
			<td class="td4">
				<input type="submit" value="保存" class="mr" onclick="javascript:location.href='admin.html'"/>
				<input type="reset" value="重置"/>
			</td>
		</tr>
	</table>
	</body>
</html>