<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" 
      + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path %>/res/css/user/userBuy.css">
<link rel="stylesheet" href="<%=path %>/res/css/user/btn.css">
<link rel="stylesheet" href="<%=path %>/res/css/bootstrap.min.css"/>
<script src="<%=path %>/res/js/jQuery/jquery.min.js"></script>
<script src="<%=path %>/res/js/constants.min.js"></script>
<script type="text/javascript" src="<%=path %>/res/js/user/getMessageCount.js"></script>
<%-- <link rel="stylesheet" type="text/css" charset="utf-8" href="<%=path %>/res/css/user/userTop.css"> --%>
<title>买商品</title>
<style>
* {
  padding: 0;
  margin: 0;
  list-style-type: none;
  border: none;
  z-index: 0;
}

body {
  width: 100%;
  font-family: '微软雅黑';
  background-color:#F2F1EF;
}
</style>
</head>
<body>
  <nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
      <div class="navbar-header">
        <a class="navbar-brand">FreeMall</a>
      </div>
      <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav" id="leftul">
          <li class="active" id="all_goods">
            <a href="<%=path %>/user/toBuy">全部商品</a>
          </li>
          <li id="up_goods">
            <a href="<%=path %>/user/toAddGoods">上架商品</a>
          </li>
          <li id="manage_goods">
            <a href="<%=path %>/user/toManageGoods">商品管理</a>
          </li>
          <li id="message">
            <a href="<%=path %>/user/toUnReadedMessages">留言查看</a>
          </li>
          <li id="user_center">
            <a href="<%=path %>/user/toUserCenter">个人中心</a>
          </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li><a><div id="messageCount">0未读</div></a></li>
          <li><a>用户名：${loginBean.name }</a></li>
          <li><a href="<%=path %>/user/exitLogin">退出</a></li>
        </ul>
      </div>
    </div>
  </nav>

  <div class="left">
    <button class="left_btn btn-1" onclick="window.location.href='<%=path %>/user/getProductsBytype?typeId=1'">数码类</button>
    <button class="left_btn btn-2" onclick="window.location.href='<%=path %>/user/getProductsBytype?typeId=2'">体育类</button>
    <button class="left_btn btn-3" onclick="window.location.href='<%=path %>/user/getProductsBytype?typeId=3'">虚拟类</button>
    <button class="left_btn btn-4" onclick="window.location.href='<%=path %>/user/getProductsBytype?typeId=4'">服务类</button>
    <button class="left_btn btn-6" onclick="window.location.href='<%=path %>/user/getProductsBytype?typeId=5'">其他类</button>
  </div>
  
  <!-- 中间 -->
  <div class="content" style="padding-top: 80px;padding-bottom:26px">
  
  <c:forEach items="${productBeans }" var="product">
  <table>
    <tr>
      <td rowspan="3" style="width:10%">
        <img alt="图片" src="${product.pic }" style="width:90px;height:90px;">
      </td>
      <td>商品：<span>${product.productName }</span></td>
      <td >卖家：<span>${product.userBean.name }</span></td>
      <td >地点：<span>${product.place }</span></td>
      <td >价格：<span>${product.price }</span></td>
      <td >上架时间：<span>${product.time }</span></td>
    </tr>
    <tr>
      <td>简介：</td>
    </tr>
    <tr>
      <td rowspan="3" colspan="5">
        <textarea readonly="readonly">${product.intro }</textarea>
      </td>
      <td rowspan="3" colspan="1">
        <div>
          <button class="btn btn-xs btn-success" onclick="alert('联系人电话：'+'${product.userBean.phoneNumber}')">联系方式</button>
        </div>
        <div style="margin-top: 5px;">
          <button class="btn btn-xs btn-primary" onclick="window.location.href='<%=path %>/user/toSendMessage?userId='+${product.userBean.userId}">留言</button>
        </div>
      </td>
    </tr>
  </table>
  </c:forEach>
  </div>
</body>
</html>