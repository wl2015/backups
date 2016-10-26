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
<link rel="stylesheet" href="<%=path %>/res/css/user/userCenter.css">
<link rel="stylesheet" href="<%=path %>/res/css/user/btn.css">
<link rel="stylesheet" href="<%=path %>/res/css/bootstrap.min.css"/>
<script type="text/javascript" src="<%=path %>/res/js/user/userCenter.js"></script>
<script src="<%=path %>/res/js/jQuery/jquery.min.js"></script>
<script src="<%=path %>/res/js/constants.min.js"></script>
<script type="text/javascript" src="<%=path %>/res/js/user/getMessageCount.js"></script>
<%-- <link rel="stylesheet" type="text/css" charset="utf-8" href="<%=path %>/res/css/user/userTop.css"> --%>
<title>个人中心</title>
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
          <li id="all_goods">
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
          <li class="active" id="user_center">
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

  <!-- 中间 -->
  <div class="content" style="padding-top: 80px;padding-bottom:26px">
  
  <table>
    <tr>
      <td>用户名：</td>
      <td>${loginBean.userName }</td>
    </tr>
  </table>
  <table>
    <tr>
      <td>姓名：</td>
      <td>
        <div id="name">${loginBean.name }</div>
        <div id="editName" style="display: none;"><input type="text"  
        value="${loginBean.name }" maxlength="10" id="nameValue"/></div>
      </td>
    </tr>
  </table>
  <table>
    <tr>
      <td>电话号码：</td>
      <td>
        <div id="phoneNumber">${loginBean.phoneNumber }</div>
        <div id="editPhoneNumber" style="display: none;"><input type="text"
        value="${loginBean.phoneNumber }" maxlength="11" id="phoneNumberValue"/></div>
      </td>
    </tr>
  </table>
  <table id="oldPSW" style="display: none;">
    <tr>
      <td>旧密码：</td>
      <td><input type="password" maxlength="20" id="oldPSWValue"/></td>
    </tr>
  </table>
  <table id="newPSW" style="display: none;">
    <tr>
      <td>新密码：</td>
      <td><input type="password" maxlength="20" id="newPSWValue"/></td>
    </tr>
  </table>
  <table id="copyPSW" style="display: none;">
    <tr>
      <td>重复密码：</td>
      <td><input type="password" id="copyPSWValue" maxlength="20"/></td>
    </tr>
  </table>
  <div class="buttonDiv">
    <input class="button" id="edit" type="button" value="编&nbsp;&nbsp;&nbsp;辑" 
    onclick="edit();"/>
    <input class="button" id="save" type="button" value="保&nbsp;&nbsp;&nbsp;存"
    style="display: none;" onclick="update('${loginBean.userId}','${loginBean.userName }');"/>
    <input class="button" id="cancel" type="button" value="取&nbsp;&nbsp;&nbsp;消"
    style="display: none;" onclick="cancel();"/>
  </div>
  </div>
</body>
</html>