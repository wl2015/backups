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
<title>管理员账户</title>
<link rel="stylesheet" href="<%=path %>/res/css/admin/demo.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/easyui.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/icon.css"/>
<script src="<%=path %>/res/js/jQuery/jquery.min.js"></script>
<script src="<%=path %>/res/js/jQuery/jquery.easyui.min.js"></script>
<style type="text/css">
body{
  padding: 0;
}
</style>
</head>
<body>
  <div class="easyui-tabs" style="height:545px;" id="account">

  </div>
  
  <script>
    $(document).ready(function(){
      var url = window.location.href;
      var webroot = "/" + url.split("/")[3];
      var finalUrl = webroot + "/admin/";
      
      var add_account = finalUrl + "toAccountAdd";    //添加管理员
      var edit_account = finalUrl + "toAccountEdit";    //修改管理员
      
      $('#account').tabs('add', {
        title: '添加管理员',
        content: '<iframe scrolling="auto" frameborder="0" src="'+add_account+'" style="width:100%;height:100%"></iframe>'
      });
      
      $('#account').tabs('add', {
        title: '修改管理员',
        content: '<iframe scrolling="auto" frameborder="0" src="'+edit_account+'" style="width:100%;height:100%"></iframe>'
      });
    });
  </script>
</body>
</html>