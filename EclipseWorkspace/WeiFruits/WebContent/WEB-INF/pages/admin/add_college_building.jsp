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
<title>添加大学宿舍楼</title>
<link rel="stylesheet" href="<%=path %>/res/css/admin/demo.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/easyui.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/icon.css"/>
<script src="<%=path %>/res/js/jQuery/jquery.min.js"></script>
<script src="<%=path %>/res/js/jQuery/jquery.easyui.min.js"></script>
<script src="<%=path %>/res/js/admin/college.js"></script>
<script src="<%=path %>/res/js/constants.min.js"></script>
</head>
<body>
  <span>双击宿舍可进行修改</span>
  <hr>
  <div style="padding-left:0;width:100%">
    <table id="add_college_building" style="width:100%;height:380px" 
        class="easyui-datagrid">
      <thead>
        <tr>
          <th field="collegeId" align="center" width="11%">编号</th>
          <th field="collegeName" align="center" width="40%">学校</th>
          <th field="collegeBuilding" align="center" width="20%">宿舍</th>
          <th field="action" align="center" width="25%">操作</th>
        </tr>
      </thead>
      
      <c:forEach items="${dormitoryBeans }" var="dormitory" varStatus="state">
      <tr>
        <td>${state.index+1 }</td>
        <td class="name">${dormitory.campusName }</td>
        <td class="building_name">${dormitory.dormitoryName }</td>
        <td>
          <input type="text" class="dormitoryId" style="display:none;" value="${dormitory.dormitoryId}"/>
          <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="deleteDormitory('${dormitory.dormitoryId}')">删除</a>
        </td>
      </tr>
      </c:forEach>
    </table>
  </div>
  
  <div id="db" buttons="#dlg-buttons"></div>
  <input type="text" id="building_id" style="display: none;">
  <div id="dlg-buttons" style="display:none">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="updateDormitoryName();">确认</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#db').dialog('close')">取消</a>
  </div>
  
  <script>
    $(document).ready(function(){
      $('#add_college_building').datagrid({
        onClickRow: function(rowIndex, rowData){
          $(this).datagrid('unselectRow', rowIndex);
        },
        onDblClickRow: function(rowIndex, rowData){
          var collegeName = document.getElementsByClassName('name')[rowIndex].innerHTML;
          var buildingName = document.getElementsByClassName('building_name')[rowIndex].innerHTML;
          var dormitoryId = document.getElementsByClassName('dormitoryId')[rowIndex].value;
          $('#db').dialog({
            title: '修改宿舍',
            left: 400,
            top: 20,
            width: 320,
            height: 200,
            closed: false,
            content: '<span id="college_name"></span><input type="text" style="width:50px;" value="" id="building_name"/>'
          });
          $('#college_name').html(collegeName);
          $('#building_name').attr('value', buildingName);
          $('#building_name').attr("onblur", "AntiSqlValid(this)");
          document.getElementById("building_id").value = dormitoryId;
        }
      });
    });
  </script>
</body>
</html>