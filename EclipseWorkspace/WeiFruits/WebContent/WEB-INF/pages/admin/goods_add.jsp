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
<link rel="stylesheet" href="<%=path %>/res/css/admin/demo.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/easyui.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/icon.css"/>
<link rel="stylesheet" href="<%=path%>/res/css/admin/goods.css"/>
<script src="<%=path %>/res/js/jQuery/jquery.min.js"></script>
<script src="<%=path %>/res/js/jQuery/jquery.easyui.min.js"></script>
<script src="<%=path %>/res/js/admin/datagrid-detailview.js"></script>
<script src="<%=path %>/res/js/jQuery/jquery.form.js"></script>
<script src="<%=path %>/res/js/admin/goods.js"></script>
<title>上架商品</title>
</head>
<body>
<h4 class="addnew">添加新商品</h4>
      <div style="float:left;width:auto">
      <form method="post" id="addForm" action="<%=path%>/admin/addNewGood">
        <table class="addtable">
          <tr>
            <td>水果名称：</td>
            <td><input name="fruitName" type="text" id="fruitname"/></td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
          </tr>
          <tr>
            <td>水果简介：</td>
            <td><input name="fruitIntro" type="text" id="fruitinfo"/></td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
          </tr>
          <tr>
            <td>价格：</td>
            <td>
              <input name="price" type="text" style="width:40px" id="fruitprice"/>
              <span class="unit">元</span>
              <span>每</span>
              <span id="fen">份</span>
              <span style="color:red;font-size:11px;display:none" id="babble">
                   （只能输入数字和小数点）
               </span>
            </td>
          </tr>
          <tr>
           <td>详细内容：</td>
           <td><textarea name="fruitDetail" rows="5" cols="22" style="resize:none;" maxlength="50" id="info"></textarea></td>
          </tr>
          <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td>
              <span id="word">您还可输入<span id="number">50</span>字</span>
            </td>
          </tr>
        </table>
        <!-- 获取上传图片路径的input隐藏框 -->
        <input type="text" name="hiddeUrl" style="width:600px;display:none" value="0"/>
        </form>
        <a class="easyui-linkbutton" onclick="document.getElementById('addForm').submit();"
          iconCls="icon-ok" id="upload" style="margin-left:300px">
          上传
         </a>
      </div>
      
      <!-- 图片上传 -->
      <div style="padding-left:420px">
        <ul style="list-style-type: none;margin:0;width:150px" id="photo">
          <li><img alt="商品图片" src="" style="width:80px;height:80px"></li>
        </ul>
        <span id="word">*仅支持jpg和png格式的图片</span>
        <form action="" method="post" id="Imguploader" enctype="multipart/form-data">
          <input id='fileUp' type="file" name='fileUp' style="width:160px;display:none"/>
          <input id="Imgupload" type="button" value="点击上传图片"/>
       </form>
      </div>
</body>
</html>