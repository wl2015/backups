<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增评论</title>
   
    
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">    
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <!--
  <link rel="stylesheet" type="text/css" href="styles.css">
  -->
 <link rel="stylesheet" type="text/css" href="res/css/admin/addComment.css">
  <script type="text/javascript" src="res/js/jQuery/jquery.min.js"></script>
  <script type="text/javascript" src="res/js/admin/comment.js?verson=2"></script>  
  <script type="text/javascript" src="res/js/common.min.js"></script>     
  <script type="text/javascript" src="res/js/constants.min.js"></script>  
  </head>

  <body>
  <div class="addComment">
        <div class="back" onclick="contentSet('comment/goodShow.do')">返回</div>
        <div class="commentContent">
                <span>新增评论内容：</span><textArea id="commentIntro" name="commentIntro"></textArea>
        </div>
        <div class="commentType"><span>类型：</span>
          <select name="commentType">  
                   <optgroup label="类型">  
                       <option value="0" selected="selected">好评</option>  
                       <option value="1">差评</option>  
                   </optgroup>  
         </select> 
          
        </div>
        <div class="sureAdd" id="doAddComment">确定新增</div>
        
    <!-- <div align="center">
  

  <table>
    <tr>
      <td colspan="15" class="header">新增评论</td>
    </tr>

    <tr>
      <td class="td3">短语内容：</td>
      <td class="td4"> <textarea cols="20" rows="5" id="commentIntro" name="commentIntro" ></textarea></td>
    </tr>
     <tr>
      <td class="td3">类型:</td>
      <td class="td4">
         <select name="commentType">  
                   <optgroup label="类型">  
                       <option value="0" selected="selected">好评</option>  
                       <option value="1">差评</option>  
                   </optgroup>  
         </select> 
      </td>
    </tr>
    <tr>
      <td class="td3"></td>
      <td class="td4">
      <button id="doAddComment" type="button">新增</button>
      
      </td>
    </tr>
  </table>

  </div> -->
  
  
  
  
  </div>
    
    
   
  </body>
</html>
