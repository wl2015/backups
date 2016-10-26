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
<title>Insert title here</title>
<link rel="stylesheet" href="<%=path %>/res/css/admin/demo.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/easyui.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/icon.css"/>
<script src="<%=path %>/res/js/jQuery/jquery.min.js"></script>
<script src="<%=path %>/res/js/jQuery/jquery.easyui.min.js"></script>
<style>
  body{
    padding: 0;
  }
  #dlg-buttons #upload{
    margin-top: 10px;
    margin-left: 200px;
    
  }
  #word{
    margin-top: 8px;
    float: left;
  }
</style>
</head>
<body>
  <form action="<%=path %>/admin/doSaveNewNotes" id="noticeForm" method="post">
  <div class="easyui-tabs" style="height:535px" id="tabs">
    <div title="创建新的公告" style="padding:10px">
      <textarea rows="14" cols="58" style="resize:none;" maxlength="150" id="info" name="noticeContent"></textarea>
      <div id="dlg-buttons">
        <span id="word">您还可输入<span id="number">150</span>字</span>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="document.getElementById('noticeForm').submit();" 
          iconCls="icon-ok" id="upload">
          发布
         </a>
      </div>
    </div>
  </div>
  </form>
<script>
  $(document).ready(function(){
    //动态获取textarea内容字数
    $('#info').bind('keyup', function(e){
      if(e.which == 13){
        var textLen = $('#info').val().replace(/[\r\n]/g, "");
        $('#number').html(150 - textLen.length);
      }else{
        var con = $('#info').val().replace(/[\r\n]/g,"");
        //console.log("内容长度："+con.length);
        $('#number').html(150 - con.length);
      }
    });
    $('#info').attr("onblur", "AntiSqlValid(this)");
  });
  
//防SQL注入
  function AntiSqlValid(field){
    var re = /select|update|delete|exec|count|’|"|=|;|>|<|%/i;
    if(re.test(field.value)){
      alert("请您不要在参数中输入特殊字符和SQL关键字！");
      field.value = "";
      field.focus();
      return false;
    }
  }
</script>
</body>
</html>