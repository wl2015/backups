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
<title>添加大学</title>
<link rel="stylesheet" href="<%=path %>/res/css/admin/demo.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/easyui.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/icon.css"/>
<script src="<%=path %>/res/js/jQuery/jquery.min.js"></script>
<script src="<%=path %>/res/js/jQuery/jquery.easyui.min.js"></script>
<script src="<%=path %>/res/js/admin/college.js"></script>
<script type="text/javascript" src="<%=path %>/res/js/constants.min.js"></script>
</head>
<body>
  <span>
  在下方输入框内输入学校名称后，点击“添加”按钮即可将新学校添加进表格中，双击学校名可修改
  </span>
  <hr>
  <span style="float:left">学校名称：</span>
  <input type="text" style="width:30%;float:left" id="campusName" name="college_name"/>（如：XX大学XX校区）
  <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="addCampus();">添加</a>
  <div style="padding-left:0;width:100%">
    <table id="addCollege" style="width:100%;height:415px" class="easyui-datagrid">
      <thead>
        <tr>
          <th field="collegeId" align="center" width="20%">编号</th>
          <th field="collegeName" align="center" width="50%">学校</th>
          <th field="action" align="center" width="30%">操作</th>
        </tr>
      </thead>
      
      <c:forEach items="${campusBeans }" var="campus" varStatus="state">
      <tr>
        <td>${state.index+1}</td>
        <td class="name">${campus.campusName}</td>
        <td>
           <%-- <a href="#" class="easyui-linkbutton" iconCls="icon-edit" 
              onclick="Edit('${campus.campusId}')" class="edit">
              编辑
           </a> --%>
          <input type="text" style="display:none;" class="campusId" value="${campus.campusId}"/>
          <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="deleteCampus('${campus.campusId }')">删除</a>
        </td>
      </tr>
      </c:forEach>
      
    </table>
  </div>
  
   <div id="dd" class="easyui-dialog" style="padding:5px;width:300px;height:200px;"
          buttons="#dlg-buttons" resizable="false" closed="true">
      <input type="text" id="college_id" style="display: none;" value=""/>
      <input type="text" style="width:250px" value="" id="college_name"/>
  </div>
  <div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="updateCampusName();">确认</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dd').dialog('close')">取消</a>
  </div>
  
  <script>
    $(document).ready(function(){
      $('#addCollege').datagrid({
        onClickRow: function(rowIndex, rowData){
          $(this).datagrid('unselectRow', rowIndex);
        },
        onDblClickRow: function(rowIndex, rowData){
          //console.log("rowIndex:"+rowIndex);
          var campusId = document.getElementsByClassName('campusId')[rowIndex].value;
          $('#dd').dialog('open');
          var name = document.getElementsByClassName('name')[rowIndex].innerHTML;
          $('#college_name').attr('value', name);
          //console.log("campusId:"+campusId);
          $('#college_id').attr('value', campusId);
        }
      });
      
      
    });
    

  </script>
</body>
</html>