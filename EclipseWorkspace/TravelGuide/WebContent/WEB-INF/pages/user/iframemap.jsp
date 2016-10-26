<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" 
      + request.getServerPort() + path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<title>Insert title here</title>
<link rel="stylesheet" href="<%=path %>/res/css/bootstrap.min.css" />
<script src="<%=path %>/res/js/jquery.min.js"></script>
<script src="<%=path%>/res/js/constants.min.js"></script>
<script src="http://api.map.baidu.com/api?v=2.0&ak=aYXuG6vmdGEHOhu2zQvdETFgxGR7wQBK"></script>

<style>
  html{
    height: 100%;
  }
  body{
    height: 100%;
    padding: 0;
    margin: 0;
    overflow-y: hidden;
    font-family: "Arial","Microsoft YaHei","Lucida Grande","宋体",sans-serif;
  }
  #sight{
    overflow-y: auto; 
    margin-top: 5px;
  }
</style>
</head>
<body id="con">
  <table>
    <tbody class="table" >
    <c:forEach items="${sightBeans }" var="sight">
      <tr id="sight">
        <td id="sightname"><span>${sight.sightName }</span></td>
        <td id="address" style="display:none"><span>${sight.address }</span></td>
        <td id="lng" style="display:none"><span>${sight.sightLng }</span></td>
        <td id="lat" style="display:none"><span>${sight.sightLat }</span></td>
        <td>
          <button class="btn btn-sm btn-success" onclick="Select('${sight.address}', '${sight.sightLng}', '${sight.sightLat}')">查看</button>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <div >
  
  </div>
</body>
</html>