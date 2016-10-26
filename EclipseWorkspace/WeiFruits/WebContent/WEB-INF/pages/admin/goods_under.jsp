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
<link rel="stylesheet" href="<%=path %>/res/css/admin/demo.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/easyui.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/admin/icon.css"/>
<script src="<%=path %>/res/js/jQuery/jquery.min.js"></script>
<script src="<%=path %>/res/js/jQuery/jquery.easyui.min.js"></script>
<script src="<%=path %>/res/js/admin/datagrid-detailview.js"></script>
<script src="<%=path %>/res/js/admin/goods.js"></script>
<script src="<%=path %>/res/js/constants.min.js"></script>
<script type="text/javascript" src="<%=path %>/res/js/admin/adminCommon.js"></script>
<title>Insert title here</title>
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
<span>点击“下架”可撤销商品在商店里的展示</span>
      <hr>
      <div style="padding-left:0px;width:100%;">
        <table id="under" style="width:100%;height:380px" class="easyui-datagrid">
          <thead>
            <tr>
              <th field="productId" width="10%" align="center">商品编号</th>
              <th field="productName" width="10%" align="center">名称</th>
              <th field="productIntro" width="40%" align="center">介绍</th>
              <th field="productDate" width="16%" align="center">上架日期</th>
              <th field="action" width="20%" align="center">操作</th>
            </tr>
          </thead>
          <c:forEach items="${fruitBeans }" var="fruit" >
            <tr class="goods_count">
              <td class="id">${fruit.fruitId }</td>
              <td class="name">${fruit.fruitName }</td>
              <td class="intr">${fruit.fruitIntr }</td>
              <td class="time">${fruit.createTime }</td>
              <td class="under"><a href="#" onclick="deleteGood('${fruit.fruitId}')" class="easyui-linkbutton" iconCls="icon-cancel">下架</a></td>
            </tr>
          </c:forEach>
        </table>
        <div class="pageTest">
            <ul class="pageUI">
              <li><a href="#" id="pre" onclick="turnToBackPage();">上一页</a></li>
              <li>第<span id="page">${pageNum }</span>页</li>
              <li><a href="#" id="next" onclick="turnToNextPage();">下一页</a></li>
          </ul>
          <form action="<%=path %>/admin/toUnderGoods" method="post" id="pageForm">
            <input id="pageNum" name="pageNum" value="0" style="display: none;"/>
          </form>
        </div>
      </div>

  <script>
    $(document).ready(function(){

    });
    
  </script>
</body>
</html>