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
<title>未完成订单</title>
<link rel="stylesheet" href="<%=path %>/res/css/admin/demo.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/easyui.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/icon.css"/>
<script src="<%=path %>/res/js/jQuery/jquery.min.js"></script>
<script src="<%=path %>/res/js/jQuery/jquery.easyui.min.js"></script>
<script src="<%=path %>/res/js/admin/datagrid-detailview.js"></script>
<script src="<%=path %>/res/js/admin/orders.js"></script>
<script type="text/javascript" src="<%=path %>/res/js/admin/adminCommon.js"></script>
<script type="text/javascript" src="<%=path %>/res/js/constants.min.js"></script>
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
  <span>点击"+"可展开显示订单的详细信息</span>
  <hr>
    <div style="padding-left:0px;width:100%;">
    <table id="unfinish" style="width:100%;height:350px" 
          class="easyui-datagrid" sortName="itemid">
      <thead>
        <tr>
          <th field="productId" align="center" width="5%">商品编号</th>
          <th field="productName" align="center" width="20%">购买者</th>
          <th field="orderTime" align="center" width="20%">购买者电话</th>
          <th field="buyer" align="center" width="20%">配送方式</th>
          <th field="status" align="center" width="15%">状态</th>
          <th field="action" align="center" width="15%">操作</th>
        </tr>
      </thead>
      <c:forEach items="${ordersBeans }" var="order" varStatus="state">
      <tr>
        <td id="index${state.index }">${order.orderId }</td>
        <td class="user">${order.receiveName } </td>
        <td class="user_phone">${order.receivePhone }</td>
        <td class="method">
          <c:if test="${order.receiveWay==0 }">送货上门</c:if>
          <c:if test="${order.receiveWay==1 }">自取</c:if>
        </td>
        <td>
          <c:if test="${order.orderStatus==0 }">未接受</c:if>
          <c:if test="${order.orderStatus==1 }">已接受</c:if>
        </td>
        <td class="sure">
          <c:if test="${order.orderStatus==0 }">
          <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="AcceptOrder('${order.orderId }');">接受</a>
          </c:if>
          <c:if test="${order.orderStatus==1 }">
          <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="FinishOrder('${order.orderId }');">完成</a>
          </c:if>
        </td>
      </tr>
      </c:forEach>
    </table>
    <div class="pageTest">
            <ul class="pageUI">
              <li><a href="#" id="pre" onclick="turnToBackPage();">上一页</a></li>
              <li>第<span id="page">${pageNum }</span>页</li>
              <li><a href="#" id="next" onclick="turnToNextPage();">下一页</a></li>
          </ul>
          <form action="<%=path %>/admin/toOrderUn" method="post" id="pageForm">
            <input id="pageNum" name="pageNum" value="0" style="display: none;"/>
          </form>
          
        </div>
  </div>
<!--   <div id="db" buttons="#dlg-buttons"></div>
  <div id="dlg-buttons" style="display:none">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:alert('ok')">确认</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#db').dialog('close')">取消</a>
  </div> -->
</body>
</html>