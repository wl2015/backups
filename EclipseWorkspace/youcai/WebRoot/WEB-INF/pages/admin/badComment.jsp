<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评论管理</title>
<link rel="stylesheet" type="text/css" href="res/css/admin/badComment.css">

  <script type="text/javascript" src="res/js/jQuery/jquery.min.js"></script>
  <script type="text/javascript" src="res/js/admin/comment.js?verson=2"></script> 
  <script type="text/javascript" src="res/js/common.min.js"></script>     
  <script type="text/javascript" src="res/js/constants.min.js"></script>  
</head>
<body>

  <div class="commentManage">
    <div id="selectType" align="center">
  <a id="good" href="javascript:void(0)" onclick="contentSet('comment/goodShow.do')">好评</a>
  <a id="bad" href="javascript:void(0)" onclick="contentSet('comment/badShow.do')">差评</a>
  </div>

  <div id="addCommentBtn" onclick="contentSet('comment/addComment.do')">新增评论</div>
    
  
    
    <table>
      <thead>
        <tr>
          <th>评论编号</th>
          <th class="commentContent">评论内容</th>
          <th>评论类型</th>
          <th>操作</th>
          <th><a class="deleteMore" href="javascript:void(0)">批量删除</a></th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="item" items="${commentList}" varStatus="status">
        <tr>
          <td>${item.commentId }</td>
          <td class="commentContent2" id="${item.commentId }">${item.commentIntro }</td>
          <td>${item.commentType == 0 ? '好评' : '差评'}</td>
          <td><a href="javascript:void(0)" class="editComment">编辑</a><a href="javascript:void(0)" class="saveComment" onclick="saveBadComment('${item.commentId }')">保存</a></td>
          <td><a href="javascript:void(0)" onclick="deleteBadComment('${item.commentId}')">删除</a></td>
          <!-- <td><input type="checkbox"/></td> -->
        </tr>
        </c:forEach>
      </tbody>
    </table>
    
   </div>
</body>
</html>