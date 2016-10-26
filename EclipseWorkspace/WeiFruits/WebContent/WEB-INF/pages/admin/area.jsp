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
<title>校区管理</title>
<link rel="stylesheet" href="<%=path %>/res/css/admin/demo.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/easyui.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/icon.css"/>
<script src="<%=path %>/res/js/jQuery/jquery.min.js"></script>
<script src="<%=path %>/res/js/jQuery/jquery.easyui.min.js"></script>
<style>
body{
  padding: 0;
}
</style>
</head>
<body>
  <div class="easyui-tabs" style="height:545px;" id="aa">

  </div>
  
  <script>
    $(document).ready(function(){
      var url = window.location.href;
      var webroot = "/" + url.split("/")[3];
      var finalUrl = webroot + "/admin/";
      
      var addCollege = finalUrl + "toAddCollege"    //添加大学
      var addBuilding = finalUrl + "toAddBuilding"    //添加宿舍楼
      var addCollege_build = finalUrl + "toAddCollegeBuilding"    //添加大学宿舍楼
      
      $('#aa').tabs('add',{
        title: '添加大学宿舍楼',
        content: '<iframe scrolling="auto" frameborder="0" src="'+addCollege_build+'" style="width:100%;height:100%" id="iframe"></iframe>'
      });
      
      $('#aa').tabs('add', {
        title: '添加宿舍',
        content: '<iframe scrolling="auto" frameborder="0" src="'+addBuilding+'" style="width:100%;height:100%" id="iframe"></iframe>'
      });
      
      $('#aa').tabs('add', {
        title: '添加大学',
        content: '<iframe scrolling="auto" frameborder="0" src="'+addCollege+'" style="width:100%;height:100%" id="iframe"></iframe>'
      });
    });
  </script>
</body>
</html>