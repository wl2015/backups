<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=path %>/res/css/admin/demo.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/easyui.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/icon.css"/>
<script src="<%=path %>/res/js/jQuery/jquery.min.js"></script>
<script src="<%=path %>/res/js/jQuery/jquery.easyui.min.js"></script>
<script src="<%=path %>/res/js/constants.min.js"></script>
<style>
  body{
    padding: 0;
  }
</style>
</head>
<body>
  <div class="easyui-tabs" style="height:530px" id="tabs">
    <div title="发布记录" style="padding:10px">
    <span>双击“发布内容”可现实详细内容</span>
    <hr>
      <table id="tt" title="历史公告" class="easyui-datagrid" 
            style="width:100%;height:450px">
        <thead>
          <tr>
            <th field="PublishId" width="80px" align="center">序号</th>
            <th field="PublishDate" width="150px" align="center">发布时间</th>
            <th field="PublishContent" width="770px" align="center">发布内容</th>
            <th field="Action" width="120px" align="center">删除</th>
          </tr>
        </thead>
        <c:forEach items="${noticeBeans}" var="notice" varStatus="state">
        <tr>
          <td>${state.index + 1 }</td>
          <td>${notice.createTime }</td>
          <td>${notice.noticeContent }</td>
          <td><div id="notice${notice.noticeId }"><a href="#" onclick="DeleteOrder('${notice.noticeId }')" class="easyui-linkbutton" iconCls="icon-cancel">删除</a></div></td>
        </tr>
        </c:forEach>
      </table>
    </div>
  </div>
  
  <div id="dd" class="easyui-dialog" style="padding:5px;width:500px;height:300px;left:300px"
       resizable="false" closed="true" title="详细内容"
      toolbar="#dlg-toolbar" buttons="#dlg-buttons">
    <span id="content" style="font-size:15px"></span>
  </div>
  
  
<script>
  $(document).ready(function(){
     $('#tt').datagrid( {
       onDblClickCell: function(index, field, value){
         OpenDetails(index, value);
       }
    });
  });
  
  function OpenDetails(index, value){
    $("#dd").dialog('open');
    $('#content').html(value);
  }
  //删除公告
  function DeleteOrder(noticeId){
    $.ajax({
        url:WEBROOT+'/admin/doDeleteNotice',
        type:'post',
        dataType:'json',
        data:{
          noticeId:noticeId
        },
        success:function(res){
          if(res.result == AJAX_SUCCESS_CODE){
            document.getElementById("notice"+res.noticeId).innerHTML="已删除";
          }
          else if(res.result == AJAX_FAIL_CODE){
            alert("删除失败，请重试");
          }
        }
      });
  }
</script>
</body>
</html>