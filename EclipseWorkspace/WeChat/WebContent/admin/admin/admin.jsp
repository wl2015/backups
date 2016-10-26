<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="../../css/right.css">
	</head>
	<body>
	<table width="100%" cellspacing="2" cellpadding="4" bgcolor="#fbead0">
		<tr>
			<td colspan="15" class="header">管理员信息</td>
		</tr>
		<tr>
			<td class="td1">管理员名</td>
			<td class="td1">密码</td>
			<td class="td1">订餐热线</td>
			<td class="td1">修改</td>
			<td class="td1">删除</td>
		</tr>
		<tr>
			<td class="td2">aaa</td>
			<td class="td2">111</td>
			<td class="td2">234</td>
			<td class="td2">
				<INPUT type="submit" value="修改" onclick="javascript:location.href='saveAdmin.html'"/>
			</td>
			<td class="td2">
				<INPUT onClick="return window.confirm('确定要删除吗？')" type="submit" value="删除"/>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="15"  height="80">
			<div>
				<div style="float:right;margin-top:1px">
					<select> 
						<option value="t1"selected>第1页</option> 
						<option value="t2">第2页</option>
						<option value="t2">第3页</option> 
						<option value="t3">第4页</option> </select>
				</div>
				<div class="turn">转到：</div>
				<div class="front"><a href="#">下一页</a></div>
				<div class="numPage">1</div>
				<div class="front"><a href="#">上一页</a></div>
			</div>
			</td>
		</tr>
	</table>
	</body>
</html>