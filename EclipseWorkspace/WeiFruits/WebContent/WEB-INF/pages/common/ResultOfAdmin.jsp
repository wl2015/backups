<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" 
      + request.getServerPort() + path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="<%=path %>/res/js/jQuery/jquery.min.js"></script>
</head>
<body>
  <h3 id="error_info">${result}，<a href="" onclick="AdminError();">点击这里返回</a></h3>
  <span id="error_note" style="color:#CF000F"></span>
  <script>
  $(document).ready(function(){
    var error_info = document.querySelector('#error_info');
    var error_note = document.querySelector('#error_note');
    if(error_info.innerHTML == "传值失败！！！"){
      error_note.innerHTML = "可能是商品信息未填写完整，请重新填写或服务器出现问题";
    }
    else if(error_info.innerHTML == "数据丢失"){
      error_note.innerHTML = "可能原因：<br/>"+
                                            "1、确保您的设备已联网"+
                                            "2、服务器出现问题，请联系网站维护人员";
    }
  });
  function AdminError(){
    var getIframe = window.parent.document.getElementById('iframe');
    $('a').attr("href", getIframe.src);
  }
  </script>
</body>
</html>