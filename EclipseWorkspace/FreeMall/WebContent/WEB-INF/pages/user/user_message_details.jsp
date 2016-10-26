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
<title>查看信息详情</title>
<link rel="stylesheet" href="<%=path %>/res/css/bootstrap.min.css"/>
<script src="<%=path %>/res/js/jQuery/jquery.min.js"></script>
<script src="<%=path %>/res/js/constants.min.js"></script>
<script type="text/javascript" src="<%=path %>/res/js/user/getMessageCount.js"></script>
<style>
* {
  padding: 0;
  margin: 0;
}

.content{
  width: 60%;
  margin: 100px 250px;
}

table{
  width: 50%;
  height: 100px;
}

textarea{
  width: 100%;
  resize: none;
}
.buttons{
  height: auto;
  width: 20%;
  margin-left: 80%;
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
  
  <div class="content" style="margin-top: 100px">
    <form action="<%=path %>/user/toSendMessage" method="post" id="doForm">
      <c:if test="${messageBean.receiveId == loginBean.userId }">
        <input type="text" name="userId" value="${messageBean.sendId }" style="display: none;"/>
      </c:if>
      <c:if test="${messageBean.receiveId != loginBean.userId }">
        <input type="text" name="userId" value="${messageBean.receiveId }" style="display: none;"/>
      </c:if>
    <table class="table">
      <tr>
        <td>发送人：<span>${messageBean.sendUser.name}</span></td>
      </tr>
      <tr>
        <td>收信人：<span>${messageBean.receiveUser.name}</span></td>
      </tr>
      <tr>
        <td>
          <textarea  readonly="readonly" rows="4" cols="8">${messageBean.content }</textarea>
        </td>
      </tr>
    </table>
    </form>
    <div class="buttons">
      <c:if test="${messageBean.receiveId == loginBean.userId }">
        <button class="btn btn-ms btn-success" onclick="document.getElementById('doForm').submit();">回复</button>
      </c:if>
      <c:if test="${messageBean.receiveId != loginBean.userId }">
        <button class="btn btn-ms btn-success" onclick="document.getElementById('doForm').submit();">再发</button>
      </c:if>
      <button class="btn btn-ms btn-primary" onclick="history.go(-1);">返回上一页</button>
    </div>
  </div>
</body>
</html>