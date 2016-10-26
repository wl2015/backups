<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" 
      + request.getServerPort() + path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查看留言</title>
<link rel="stylesheet" href="<%=path %>/res/css/bootstrap.min.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/user/btn.css">
<script src="<%=path %>/res/js/jQuery/jquery.min.js"></script>
<script src="<%=path %>/res/js/constants.min.js"></script>
<script type="text/javascript" src="<%=path %>/res/js/user/getMessageCount.js"></script>
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
}
.left{
  width: 10%;
  height: 400px;
  top:120px;
  left:2%;
  position: fixed;
}
.content{
  width: 80%;
}
table{
  width: 70%;
  height: 100px;
  margin-left: 20%;
  background-color:white;
  margin-top: 15px;
  text-align: center;
}
thead tr th{
  text-align: center;
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
          <li id="all_goods">
            <a href="<%=path %>/user/toBuy">全部商品</a>
          </li>
          <li id="up_goods">
            <a href="<%=path %>/user/toAddGoods">上架商品</a>
          </li>
          <li id="manage_goods">
            <a href="<%=path %>/user/toManageGoods">商品管理</a>
          </li>
          <li class="active" id="message">
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
    <button class="left_btn btn-1" onclick="window.location.href='<%=path %>/user/toUnReadedMessages'">未读消息</button>
    <button class="left_btn btn-2" onclick="window.location.href='<%=path %>/user/toReadedMessages'">已读消息</button>
    <button class="left_btn btn-3" style="background-color: #E74C3C" onclick="window.location.href='<%=path %>/user/toSendedMessages'">发送消息</button>
  </div>
  
  <div class="content" style="padding-top: 80px;padding-bottom:26px">
    <table class="table table-striped">
      <thead>
        <tr>
          <th>收件人</th>
          <th>日期</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
       <c:forEach items="${messageBeans }" var="message">
        <tr>
          <td>${message.receiveUser.name }</td>
          <td>${message.time }</td>
          <td>
            <button class="btn btn-ms btn-primary" onclick="window.location.href='<%=path %>/user/toMessageDetails?messageId='+${message.messageId}">查看</button>
          </td>
        </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>

</body>
</html>