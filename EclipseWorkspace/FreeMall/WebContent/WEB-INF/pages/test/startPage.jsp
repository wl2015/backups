<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" 
      + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="../res/js/jQuery/jquery.min.js"></script>
<script src="../res/js/constants.min.js"></script>
<script type="text/javascript">
  function test(){
    $.ajax({
      url:'../test/ajaxtest.do',
      type:'post',
      dataType:'json',
      data:{
    	  state:0,
    	  name:'wla'
      },
      success:function(res){
    	  alert("aaaa");
    	  alert(res.name);
      }
    });
  }
</script>
</head>
<body>
  <input type="button" onclick="test();" value="测试"/>
</body>
</html>