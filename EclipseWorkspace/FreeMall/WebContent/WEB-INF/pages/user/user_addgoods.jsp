<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" 
      + request.getServerPort() + path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>上架商品</title>
<link rel="stylesheet" href="<%=path %>/res/css/bootstrap.min.css"/>
<script src="<%=path %>/res/js/jQuery/jquery.min.js"></script>
<script src="<%=path %>/res/js/constants.min.js"></script>
<script src="<%=path %>/res/js/jQuery/jquery.form.js"></script>
<script src="<%=path %>/res/js/user/userAddGoods.js"></script>
<script type="text/javascript" src="<%=path %>/res/js/user/getMessageCount.js"></script>
<style>
* {
  padding: 0;
  margin: 0;
}
.content{
  width: 60%;
  margin: 100px 350px;
}
</style>
</head>
<body>
  <nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
      <div class="navbar-header">
        <a class="navbar-brand">FreeMall</a>
      </div>
      <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav" id="leftul">
          <li id="all_goods">
            <a href="<%=path %>/user/toBuy">全部商品</a>
          </li>
          <li class="active" id="up_goods">
            <a href="<%=path %>/user/toAddGoods">上架商品</a>
          </li>
          <li id="manage_goods">
            <a href="<%=path %>/user/toManageGoods">商品管理</a>
          </li>
          <li id="message">
            <a href="<%=path %>/user/toUnReadedMessages">留言查看</a>
          </li>
          <li id="user_center">
            <a href="<%=path %>/user/toUserCenter">个人中心</a>
          </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li><a><div id="messageCount">0未读</div></a></li>
          <li><a>用户名：${loginBean.name }</a></li>
          <li><a href="<%=path %>/user/exitLogin">退出</a></li>
        </ul>
      </div>
    </div>
  </nav>
  
  <div class="content" style="margin-top: 100px">
    <div class="row">
      <form action="<%=path%>/user/doAddProduct" method="post" id="doForm">
        <div class="col-md-5">
          <label>商品：</label>
          <input type="text" id="productName" name="productName" class="form-control" maxlength="6" style="width:200px">
          <br>
          <label>出售点：</label>
          <input type="text" id="place" name="place" class="form-control" style="width:200px" maxlength="4" value="全国">
          <br>
          <label>价格：</label>
          <input type="text" id="price" name="price" class="form-control" style="width:200px" maxlength="10" placeholder="单位为元" onkeyup="amount(this);">
          <br>
          <label>简介：</label>
          <br>
          <textarea id="intro" name="intro" rows="5" cols="35" maxlength="100" style="resize:none"></textarea>
                    <br>
          
          <label>分类：</label>
          <select id="select" name="typeId" style="background: #ECF0F1;width:80px">
            <option value="1">数码类</option>
            <option value="2">体育类</option>
            <option value="3">虚拟类</option>
            <option value="4">服务类</option>
            <option value="5">其它</option>
          </select>
        </div>
        <!-- 获取上传图片路径的input隐藏框 -->
        <input type="text" id="hiddeUrl" name="hiddeUrl" style="width:600px;display:none" value="0"/>
      </form>
       <div class="col-md-5">
         <label>图片上传：</label>
          <ul style="list-style-type: none;margin:0;width:150px" id="photo">
            <li><img alt="商品图片" src="" style="width:80px;height:80px"></li>
          </ul>
          <span id="word">*仅支持jpg和png格式的图片</span>
          <form action="" method="post" id="Imguploader" enctype="multipart/form-data">
            <input id='fileUp' type="file" name='fileUp' style="width:160px;display:none"/>
            <input id="Imgupload" type="button" value="点击上传图片"/>
          </form>
        </div>
    </div>
    
    <div id="btn" style="margin-left:50%">
      <button class="btn btn-md btn-success" onclick="doSubmit();">提交</button>
    </div>
  </div>
</body>
</html>