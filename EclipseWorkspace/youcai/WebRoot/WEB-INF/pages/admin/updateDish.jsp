<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改菜品</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  <script type="text/javascript" src="res/js/jQuery/jquery.min.js"></script>
  <script type="text/javascript" src="res/js/admin/dishManage.js?verson=2"></script> 
    <script type="text/javascript" src="res/js/jQuery/jquery.form.js"></script>
  <script type="text/javascript" src="res/js/common.min.js"></script>     
  <script type="text/javascript" src="res/js/constants.min.js"></script>  
  </head>

  <body>
  <div align="right"><a href="admin/back.do">返回主页</a></div>
    <div align="center">
 
      <table>
    <tr>
      <td colspan="15" class="header">修改菜品</td>
    </tr>
    <tr>
      
      <td class="td3">菜品名:</td>
      <td class="td4"><input type="text" id="name" name="name" size="20" value="${dish.dishName}"/></td>
    </tr>
    <tr>
      <td class="td3">菜品简介：</td>
      <td class="td4"> <textarea cols="20" rows="5" id="intro" name="intro" >${dish.dishIntro}</textarea></td>
    </tr>
    <tr>
      <td class="td3">图片:</td>
      <td class="td4">
        <div id="preview">  
             <img alt="图片预览" id="imghead" width=100 height=100 border=0 src="${dish.dishPic}">  
          </div>  
          <br>
          <form action="" method="post" id="uploader" enctype="multipart/form-data">
            <input onchange="previewImage(this)" id="uploadField" type="file" name="file"/>
            <input type="hidden" name="dir" value="media"/>
          </form> 
          
      
      </td>
    </tr>
    <tr>
      <td class="td3">成本价:</td>
      <td class="td4"> <input type="text" id="costPrice" name="costPrice" size="20" value="${dish.costPrice}"/></td>
      
    </tr>
    <tr>
      <td class="td3">零售价:</td>
      <td class="td4"> <input type="text" id="retailPrice" name="retailPrice" size="20" value="${dish.retailPrice}"/></td>
      
    </tr>
    
    <tr>
      <td class="td3">口味:</td>
      <td class="td4">
         <input type="text" id="taste" name="taste" size="20" value="${dish.dishTaste}"/>
      </td>
    </tr>
    <tr>
      <td class="td3"></td>
      <td class="td4">
      <button id="updateDish">修改</button>
      <input type="hidden" value="${dish.dishId}" id="id"/>   
      </td>
    </tr>
  </table>

  </div>
    
    
   
  </body>
</html>
