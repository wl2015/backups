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
<title>首页</title>
<link rel="stylesheet" href="<%=path %>/res/css/bootstrap.min.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/onepage-scroll.css" />
<script src="<%=path %>/res/js/jquery.min.js"></script>
<script src="<%=path %>/res/js/jquery.onepage-scroll.min.js"></script>
<script src="<%=path %>/res/js/bootstrap.min.js"></script>
<script src="<%=path%>/res/js/constants.min.js"></script>
<script src="<%=path %>/res/js/usermap/login_sign.js"></script>
<script src="<%=path %>/res/js/base64.js"></script>    <!-- 加密算法 -->
<style>
  .page1{
    background-image: url(../res/img/home3.jpg ); 
    background-size: cover;
  }
  .page2{
    background-image: url(../res/img/home5.jpg );
    background-size: cover;
  }
  .page3{
    background-image: url(../res/img/home4.jpg ); 
    background-size: cover;
  }
  .row{
    margin: 250px 250px;
    z-index: 1;
  }
  #visitor{
    float: left;
    position: relative;
    left: 300px;
  }
  #add_scrolling{
    width: 128px;
    height: 54px;
    position: absolute;
    left: 50%;
    bottom: 10px;
    margin-left: -64px;
    background: url(../res/img/scrolling.png);
  }
</style>
</head>

<body>
<div class="container">
      <div class="page page1" style="width:100%">
        <div class="row">
          <div class="col-md-12 text-center">
            <h2>个性化旅游推荐网站</h2>
            <br/>
            <h3>让旅游更随心</h3>
            <br/>
            <button id="visitor" class="btn btn-lg btn-info" onclick="window.open('<%=path %>/user/showVisitorMap')">我是访客
            </button>
            <button id="account" class="btn btn-lg btn-success" 
              data-toggle="modal" data-target="#account_login">账户登录</button>
          </div>
        </div>
        <div id="add_scrolling" style="opacity:1; display:block"></div>
      </div>
      <div class="page page2">
        <div class="row" style="margin-top:100px;">
          <div class="col-md-12 text-center">
            <h2>不要畏惧眼前的山</h2><br>
            <h2>想想山后面是什么</h2>
          </div>
        </div>
      </div>
      <div class="page page3">
        <div class="row">
          <div class="col-md-12 text-center">
            <h2>因为，外面的世界很精彩</h2>
          </div>
        </div>
      </div>
    </div>
    
    /**
     * 登录
     */
    <div class="modal fade" id="account_login" role="dialog" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button class="close" data-dismiss="modal" aria-hidden="true">
              &times;
            </button>
            <h4 class="modal-title" style="text-align: center;">
              登录
            </h4>
          </div>
          <%-- <form action="<%=path %>/user/doLogin" id="loginForm" method="post"> --%>
          <div class="modal-body">
            <div class="form-group">
              <lable>用户名：</lable>
              <input type="text" name="username" id="username" class="form-control"/>
            </div>
            <div class="form-group">
              <lable>密码：</lable>
              <input type="password" name="pass" id="pass" class="form-control"/>
            </div>
          </div>
          <div class="modal-footer" id="llogin">
            <input type="button" class="btn btn-primary" value="登录" onclick="Login()"/>
            <input type="button" class="btn btn-success" value="注册" onclick="Regist()"/>
          </div>
          <!-- </form> -->
        </div>
      </div>
    </div>
    
    /**
     * 注册
     */
    <div class="modal fade" id="account_sign" role="dialog" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button class="close" data-dismiss="modal" aria-hidden="true">
              &times;
            </button>
            <h4 class="modal-title" style="text-align: center;">注册</h4>
          </div>
          <%-- <form action="<%=path%>/user/doRegistUser" id="registForm" method="post"> --%>
          <div class="modal-body">
            <div class="form-group" id="user">
              <label>用户名：</label>
              <input type="text" name="userName" id="Susername" class="form-control" 
                  placeholder="包含汉字、字母、数字和下划线，且不能已下划线开头和结尾"/>
            </div>
            <br/>
            <div class="form-group" id="pass1">
              <label>密码：</label>
              <input type="password" id="Spass" name="passWord" class="form-control"
                placeholder="必须是6至18位数字、字母和特殊符号的组合"/>
            </div>
            <br/>
            <div class="form-group" id="pass2">
              <label>确认密码：</label>
              <input type="password" id="SSpass" class="form-control"/>
            </div>
          </div>
          <div class="modal-footer" id="ssign">
            <input type="button" class="btn btn-primary" value="注册" onclick="Sign()"/>
            <input type="button" class="btn btn-success" value="已有账户，去登录" onclick="goLogin()" />
          </div>
          <!-- </form> -->
        </div>
      </div>
    </div>
    
    <script>
      $(document).ready(function(){
        $('.container').onepage_scroll({
          sectionContainer: '.page',
          loop: false,
          afterMove: function(index){
            
          }
        });
      });
      function Regist(){
        $('#account_login').modal('hide');
        $('#account_sign').modal('show');
      }
      function goLogin(){
        $('#account_sign').modal('hide');
        $('#account_login').modal('show');
      }
    </script>
</body>
</html>