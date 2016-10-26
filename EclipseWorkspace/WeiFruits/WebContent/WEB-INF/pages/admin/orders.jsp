<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>订单管理</title>
<link rel="stylesheet" href="<%=path %>/res/css/admin/demo.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/easyui.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/icon.css"/>
<script src="<%=path %>/res/js/jQuery/jquery.min.js"></script>
<script src="<%=path %>/res/js/jQuery/jquery.easyui.min.js"></script>
<%-- <script src="<%=path %>/res/js/admin/datagrid-detailview.js"></script>
<script src="<%=path %>/res/js/admin/orders.js"></script> --%>
<style>
  body{
    padding: 0;
  }
</style>
</head>
<body>
  <div class="easyui-tabs" style="height:545px;" id="tt">

  </div>

<script type="text/javascript">
  $(document).ready(function(){
    var url = window.location.href;
    var webroot = "/" +url.split("/")[3];
    var finalUrl = webroot + '/admin/';
    
    var un = finalUrl + "toOrderUn";    //未完成订单
    var ed = finalUrl + "toOrderEd";    //已完成订单
    
    //未完成
    $('#tt').tabs('add', {
      title: '未完成订单',
      content: '<iframe scrolling="auto" frameborder="0" src="'+un+'" style="width:100%;height:100%"></iframe>'
    });
    
    //已完成
    $('#tt').tabs('add', {
      title: '已完成订单',
      content: '<iframe scrolling="auto" frameborder="0" src="'+ed+'" style="width:100%;height:100%"></iframe>'
    });
  });
</script>
</body>
</html>