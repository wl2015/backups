<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜品套餐管理----套餐分类</title>
<link rel="stylesheet" type="text/css" href="res/css/admin/setFenlei.css">
</head>
<body>
	<div class="dishOrSetManage">
		<ul class="dishOrSetManageUl">
			<li><span class="sp1">类别名称：</span><input type="text"/></li>
			<li><span class="sp1">所属类型：</span>
				<select>
					<option>菜品</option>
					<option>套餐</option>
				</select>
				<div class="button">确认添加</div>
			</li>
			<li><a href="dishOrSetManage.jsp">菜品分类</a><a href="#">套餐分类</a></li>
		</ul>
		<table>
			<thead>
				<tr>
					<th>类别名</th>
					<th>所属类型</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>甜品</td>
					<td>菜品</td>
					<td><a href="#">删除</a></td>
				</tr>
				<tr>
					<td>甜品</td>
					<td>菜品</td>
					<td><a href="#">删除</a></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>