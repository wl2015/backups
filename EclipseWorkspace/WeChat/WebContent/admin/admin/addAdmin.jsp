<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="../../css/right.css">
	</head>
	<body>
	<table width="100%" cellspacing="2" cellpadding="4"bgcolor="#fbead0">
		<tr>
			<td colspan="15" class="header">添加管理员</td>
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
				<input type="submit" value="添加" class="mr" onclick="javascript:location.href='admin.html'"/>
				<input type="reset" value="重置"/>
			</td>
		</tr>
	</table>
	</body>
</html>