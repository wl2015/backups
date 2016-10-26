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
<title>修改账户信息</title>
<link rel="stylesheet" href="<%=path %>/res/css/admin/demo.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/easyui.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/icon.css"/>
<script src="<%=path %>/res/js/jQuery/jquery.min.js"></script>
<script src="<%=path %>/res/js/jQuery/jquery.easyui.min.js"></script>
<script src="<%=path%>/res/js/admin/account.js"></script>
<style>
.info{
  position: relative;
  top: 10px;
  left: 250px;
  width: 400px;
  height: 200px;
}
.con{
  padding-top: 10px;
}
.note{
  color: #19B5FE;
  font-size: 11px;
}
.tip{
  color: #D91E18;
  font-size: 11px;
  float: right;
}
</style>
</head>
<body>
  <div class="info">
	  <form id="edit_info" action="<%=path %>/admin/changePassWord" method="post">
	    <div class="con">
	      <label for="name">用户名：</label>
	      <input type="text" name="adminName" value="登录用户名" style="width:30%" id="adminname"/>
	    </div>
	    <div class="con">
	      <label for="old_pass">旧密码：</label>
	      <input type="password" name="old_pass" style="width:30%" id="oldpass"/>
	      <p class="tip tip1"></p>
	    </div>
	    <div class="con">
	      <label for="new_pass">新密码：</label>
	      <input  type="password" name="new_pass" style="width:30%" id="newpass" maxlength="20"/>
	      <p class="tip tip2"></p>
	      <p class="note">*必须是6至10位字母和数字的组合</p>
	    </div>
	    <div class="con">
	      <label for="re_new_pass">再次输入新密码：</label>
	      <input type="password" name="new_pass_re" style="width:30%" id="repass" maxlength="20"/>
	      <p class="tip tip3">${result }</p>
	    </div>
	  </form>
  </div>
  <button class="easyui-linkbutton" onclick="SureAccount();" iconCls="icon-ok" style="margin-left: 30%">确认</button>
  
  <script>
	  $(document).ready(function(){
	    $('#adminname').attr("onblur", "AntiSqlValid(this)");
	    $('#oldpass').attr("onblur", "AntiSqlValid(this)");
	    $('#newpass').attr("onblur", "AntiSqlValid(this)");
	    $('#newpass').attr("onblur", "AntiSqlValid(this)");
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