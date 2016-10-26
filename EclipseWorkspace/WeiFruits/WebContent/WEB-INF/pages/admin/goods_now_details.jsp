<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>现有商品详细信息</title>

</head>
<body>
  <table class="dv-table" border="0" style="width:100%">
    <tr>
      <td rowspan="3" style="width:60px">
        <img alt="图片" src="${fruitBean.fruitPic }" style="width:80px;height:80px;margin-right:20px">
      </td>
      <td class="dv-label" style="color: #EF4836">编号：</td>
      <td>${fruitBean.fruitId }</td>
      <td class="dv-label" style="color: #EF4836">名称：</td>
      <td>${fruitBean.fruitName }</td>
    </tr>
    <tr>
      <td class="dv-label" style="color: #EF4836">价格：</td>
      <td>${fruitBean.fruitPrice }元1份</td>
      <td class="dv-label" style="color: #EF4836">上架日期：</td>
      <td>${fruitBean.createTime }</td>
    </tr>
    <tr>
      <td class="dv-label" style="color: #EF4836">介绍：</td>
      <td>${fruitBean.fruitIntr }</td>
      <td class=dv-label style="color: #EF4836">详细信息：</td>
      <td><input type="text" readonly="readonly" style="width:200px" value="${fruitBean.fruitDetail}"/></td>
    </tr>
  </table>
</body>
</html>