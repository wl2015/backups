<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="../../res/js/jquery.min.js"></script>
<script type="text/javascript">
  function test(){
	  alert("aa");
    $.ajax({
      url:'../test/ajaxtest',
      type:'post',
      data:{
    	  state:0,
    	  name:'wla'
      }
      success:function(res){
    	  alert("aaa");
    	  alert(res.userBean.userName);
      }
    });
  }
</script>
</head>
<body>
  <input type="button" onclick="test();" value="test"/>
</body>
</html>