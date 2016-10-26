<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>现有商品</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=path %>/res/css/admin/demo.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/easyui.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/icon.css"/>
<script src="<%=path %>/res/js/jQuery/jquery.min.js"></script>
<script src="<%=path %>/res/js/jQuery/jquery.form.js"></script>
<script src="<%=path %>/res/js/jQuery/jquery.easyui.min.js"></script>
<script src="<%=path %>/res/js/admin/datagrid-detailview.js"></script>
<script src="<%=path %>/res/js/admin/goods.js"></script>
<script src="<%=path %>/res/js/admin/adminCommon.js"></script>
<script src="<%=path %>/res/js/constants.min.js"></script>
<style>
ul li{
  list-style: none;
}
.pageTest {
  width: 1000px;
  height: 50px;
  margin-top: 5px;
}
.pageUI{
  float: right;
  padding: 0;
  margin: 0;
  list-style: none;
}
.pageUI li{
  float: left;
  width: 48px;
  height: 30px;
  list-style: 30px;
  text-align: center;
}
</style>
</head>
<body>
      <span>点击"+"可展开显示详细信息，点击“编辑”可修改商品信息</span>
      <hr>
      <div style="padding-left:0px;width:100%;" >
        <table id="dg" url="" sortName="itemid" style="width:100%;height:350px" 
                class="easyui-datagrid">
          <thead>
            <tr>
              <th field="productId" width="5%" align="center">商品编号</th>
              <th field="productName" width="11%" align="center">名称</th>
              <th field="productIntro" width="42%" align="center">介绍</th>
              <th field="productDate" width="18%" align="center">日期</th>
              <th field="action" width="20%" align="center">操作</th>
            </tr>
          </thead>
          
          <c:forEach items="${fruitBeans }" var="fruit" varStatus="state">
          <tr>
            <td id="index${state.index }">${fruit.fruitId }</td>
            <td>${fruit.fruitName }</td>
            <td>${fruit.fruitIntr }</td>
            <td>${fruit.createTime }</td>
            <td><a href="#" onclick="EditGoods('${fruit.fruitId }')" class="easyui-linkbutton" iconCls="icon-edit">编辑</a></td>
            <!-- 隐藏的信息 -->
            <input type="text" id="fruitName${fruit.fruitId }" style="display: none;" value="${fruit.fruitName }"/>
            <input type="text" id="fruitPic${fruit.fruitId }" style="display: none;" value="${fruit.fruitPic }"/>
            <input type="text" id="fruitIntr${fruit.fruitId }" style="display: none;" value="${fruit.fruitIntr }"/>
            <input type="text" id="fruitPrice${fruit.fruitId }" style="display: none;" value="${fruit.fruitPrice }"/>
            <input type="text" id="fruitDetail${fruit.fruitId }" style="display: none;" value="${fruit.fruitDetail }"/>
          </tr>
          </c:forEach>
          
        </table>
        <div class="pageTest">
            <ul class="pageUI">
              <li><a href="#" id="pre" onclick="turnToBackPage();">上一页</a></li>
              <li>第<span id="page">${pageNum }</span>页</li>
              <li><a href="#" id="next" onclick="turnToNextPage();">下一页</a></li>
          </ul>
          <form action="<%=path %>/admin/toNowGoods" method="post" id="pageForm">
            <input id="pageNum" name="pageNum" value="0" style="display: none;"/>
          </form>
        </div>
      </div>
    
      <div id="dd" class="easyui-dialog" style="padding:5px;width:700px;height:300px;left:300px"
       iconCls="icon-ok" resizable="false" closed="true" title="编辑"
       buttons="#dlg-buttons">
    
    <form method="post" action="<%=path %>/admin/updateFruitInfo" id="updateForm">
      <table style="margin-left:20px;float:left">
        <tr>
          <td>水果名称：</td>
          <td><input id="fruitname" name="fruitName" type="text"/></td>
          <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
          <td>商品图片：</td>
          <!-- <td><img alt="商品图片" src="" id="edit_img"></td> -->
           <!-- <td><span id="word">*仅支持jpg和png格式的图片</span></td> -->
        </tr>
        <tr>
          <td>水果简介：</td>
          <td><input id="fruitinfo" name="fruitIntro" type="text"/></td>
        </tr>
        <tr>
          <td>价格：</td>
          <td>
            <input id="fruitprice" name="fruitPrice" type="text" style="width:40px"/>
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
     <input type="text" id="hidPic" name="hiddeUrl" style="width:600px;display:none" value="0"/>
     <input type="text" id="fruitId" name="fruitId" style="display: none;"/>
    </form>
    
          <!-- 图片上传 -->
      <div style="padding-left:420px;">
        <ul style="list-style-type: none;margin:0;width:150px" id="photo">
          <li><img alt="商品图片" src="" style="width:80px;height:80px"></li>
        </ul>
        <span id="word">*仅支持jpg和png格式的图片</span>
        <form action="" method="post" id="Imguploader" enctype="multipart/form-data">
          <input id='fileUp' type="file" name='fileUp' style="width:160px;display:none"/>
          <input id="Imgupload" type="button" value="点击上传图片"/>
       </form>
      </div>
    <div id="dlg-buttons">
      <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="document.getElementById('updateForm').submit();">保存</a>
      <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dd').dialog('close')">取消</a>
    </div>
  </div>
  

</body>
</html>