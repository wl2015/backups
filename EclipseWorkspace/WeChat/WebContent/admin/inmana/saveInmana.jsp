<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="../../css/right.css">
		<link rel="stylesheet" type="text/css" href="../css/right.css">
		
	<script type="text/javascript">
		function Check(){
			var li = document.getElementById("limit").value;
			
			if (isNaN(li)) {
				alert("当日上限必须为数字！");
				return false;
			}
			else {
				alert("上限修改成功！");
			}
			
			return true;
		}
	</script>
	</head>
	<body>
	<form action="${pageContext.request.contextPath }/KucunAction_modify" method="post" onSubmit="return Check()">
	<table width="100%" cellspacing="2" cellpadding="4" bgcolor="#fbead0">
		<tr>
			<td colspan="15" class="header">
			<div style="float:left">库存管理</div>
			<div style="float:right"><input type="button" value="返回" class="border2"  onclick="location.href='${pageContext.request.contextPath }/KucunAction_execute.do'"/></div>
			</td>
		</tr>
		<tr>
			<td class="td3">库存ID-菜品名:</td>
			<td class="td4"><input type="text" readOnly  size="30" maxlength="30" name="kc.kucun_id" value="${param.kucunId}" /></td>
			
			
		</tr>
		<tr>
			<td class="td3">当日上限:</td>
			<td class="td4"><input type="text" size="30" maxlength="30" name="kc.limitTop" value="${param.limit}" id="limit"/></td>
		</tr>
		<tr>
			<td class="td3"></td>
			<td class="td4">
				<input type="submit" value="修改" class="mr" />
				<input type="reset" value="重置"/>
			</td>
		</tr>
	</table>
	</form>
	</body>
</html>