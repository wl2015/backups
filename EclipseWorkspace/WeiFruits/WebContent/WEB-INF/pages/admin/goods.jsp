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
<title>商品管理</title>
<link rel="stylesheet" href="<%=path %>/res/css/admin/demo.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/easyui.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/icon.css"/>
<%-- <link rel="stylesheet" href="<%=path%>/res/css/admin/goods.css"/> --%>
<script src="<%=path %>/res/js/jQuery/jquery.min.js"></script>
<script src="<%=path %>/res/js/jQuery/jquery.easyui.min.js"></script>
<%-- <script src="<%=path %>/res/js/jQuery/jquery.form.js"></script>
<script src="<%=path %>/res/js/admin/datagrid-detailview.js"></script>
<script src="<%=path %>/res/js/admin/goods.js"></script> --%>
<style>
  body{
    padding: 0;
    font-family: font-family: "Arial","Microsoft YaHei","Lucida Grande","宋体",sans-serif;
  }
</style>
</head>
<body>
  <div class="easyui-tabs" style="height:545px" id="tt">

  
  </div>
  
  <script>
    $(document).ready(function(){
      var url = window.location.href;
      var webroot = "/" +url.split("/")[3];
      var finalUrl = webroot + '/admin/';
      
      var add = finalUrl + "toAddGoods";      //添加商品
      var uu = finalUrl + "toUnderGoods";     //下架商品
      var nn = finalUrl + "toNowGoods";       //现有商品
      
      //现有商品
      $('#tt').tabs('add', {
        title: '现有商品',
        content: '<iframe scrolling="auto" frameborder="0" src="'+nn+'" style="width:100%;height:100%"></iframe>'
      });
      
      //下架商品
      $('#tt').tabs('add', {
        title: '下架商品',
        content: '<iframe scrolling="auto" frameborder="0" src="'+uu+'" style="width:100%;height:100%"></iframe>'
      });
      
      //添加商品
      $('#tt').tabs('add', {
        title: '上架商品',
        content: '<iframe scrolling="auto" frameborder="0" src="'+add+'" style="width:100%;height:100%"></iframe>',
/*         selected: 'true' */
      });
    });
    

    
  </script>
</body>
</html>