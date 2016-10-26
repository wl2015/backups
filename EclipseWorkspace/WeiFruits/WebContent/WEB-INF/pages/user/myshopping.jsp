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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的购物车</title>
<link rel="stylesheet" href="<%=path %>/res/css/bootstrap.min.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/user/shopping.css"/>
<script src="<%=path %>/res/js/jQuery/jquery.min.js"></script>
<script src="<%=path %>/res/js/bootstrap.min.js"></script>
<script src="<%=path %>/res/js/user/shopping.js"></script>
<script src="<%=path %>/res/js/constants.min.js"></script>
<style>
*{
  padding: 0;
  margin: 0;
}
body{
  font-family: "Arial","Microsoft YaHei","Lucida Grande","宋体",sans-serif;
  background: #FDE3A7;
}

</style>
</head>
<body>
    <h4>我的购物车</h4>
    <hr>
    
    <ul class="product">
    
      <!-- 第一个商品 -->
      <c:forEach items="${orderList }" var="fruit">
      <li data-product-id="20" class="parent">
        <div class="icon">
          <img src="${fruit.fruitPic }" 
                style="display:inline;width:80px;height:80px"/>
        </div>
        <div class="details">
          <div class="name">${fruit.fruitName }</div>
          <ul class="rule">
            <li>
              <span class="price" style="color:#87D37C">${fruit.fruitPrice }</span>
              <span class="priceunit">元</span>
              <span class="amount" style="color:#87D37C">1</span>
              <span class="amountunit">份</span>
              <div class="order_input">
                <div class="numberbox">
                  <button class="glyphicon glyphicon-plus add" onclick="Fruits('${fruit.fruitId}','${fruit.fruitName}','${fruit.fruitPrice}','${fruit.fruitIntr}','${fruit.fruitDetail}','${fruit.fruitPic}','0','${fruit.sellCount}',this);"></button>
                  <input class="number" type="text" id="fruit${fruit.fruitId }" value="${fruit.count }"/>
                  <button class="glyphicon glyphicon-minus min" onclick="Fruits('${fruit.fruitId}','${fruit.fruitName}','${fruit.fruitPrice}','${fruit.fruitIntr}','${fruit.fruitDetail}','${fruit.fruitPic}','0','${fruit.sellCount}',this);"></button>
                </div>
              </div>
            </li>
          </ul>
          <div class="subtotal">
            <div class="money_icon">￥</div>
            小计：
            <span class="totalnumber" id="singlfruit${fruit.fruitId }">${fruit.fruitPrice * fruit.count }</span>
            <span class="unit">元</span>
          </div>
        </div>
        <button class="remove" onclick="Fruits('${fruit.fruitId}','${fruit.fruitName}','${fruit.fruitPrice}','${fruit.fruitIntr}','${fruit.fruitDetail}','${fruit.fruitPic}','0','${fruit.sellCount}',this);"></button>
      </li>
      </c:forEach>
     
    </ul>
    
    <!-- 总计 -->
    <div class="total">
      <div class="icon"></div>总计：
      <div class="prices">
        <span id="total_price"></span>
        <span class="unit">元</span>
      </div>
    </div>
    
    <form method="post" action="<%=path %>/user/saveOrdersAndTurnToPayPage" target='_parent'>
      <div class="messagebox" style="border-bottom:#FCBDBC 1px solid">
        <h5>购物留言</h5>
        <input type="text" class="form-control" name="message" placeholder="有什么想要告诉我们的吗？" style="margin-bottom:5px">
      </div>
      <div class="paybox" style="border-bottom:#FCBDBC 1px solid">
        <h5>支付方式</h5>
        <div class="paycontent" style="margin:0 10px">
          <ul class="select">
            <li class="checked">
              <input checked="checked" type="radio" value="0" name="payWay"/>货到付款
            </li>
            <li>
              <input type="radio" value="2" name="payWay" disabled="disabled"/>支付宝
            </li>
            <li><input type="radio" value="1" name="payWay" disabled="disabled"/>微信支付</li>
          </ul>
        </div>
      </div>
      
      <div class="addressbox" style="border-bottom:#FCBDBC 1px solid">
        <h5>收货地址</h5>
        <div class="addresscontent" style="margin:0 10px">
           <ul class="select">
            <li class="checked">
              <c:if test="${ordersBean!=null}">
	              <input type="radio" value="checkOld" name="chooseCode" checked="checked"/>
	              <!-- 成都信息工程大学航空港 6栋 4012 王雷（18380448820） -->
	              <!-- 隐藏的起来老的地址信息 -->
	              <input type="text" value="${ordersBean.campusName }" name="oldCampusName" style="display: none;"/>
	              <input type="text" value="${ordersBean.dormitoryName }" name="oldDormitoryName" style="display: none;"/>
	              <input type="text" value="${ordersBean.address }" name="oldAddress" style="display: none;"/>
	              <input type="text" value="${ordersBean.receiveName }" name="oldReceiveName" style="display: none;"/>
	              <input type="text" value="${ordersBean.receivePhone }" name="oldReceivePhone" style="display: none;"/>
              </c:if>
              <c:if test="${ordersBean==null}">
                <input type="radio" value="0" name="chooseCode" disabled="disabled"/>没有历史地址
              </c:if>
            </li>
            <li>
              <input type="radio" value="checkNew" name="chooseCode" id="newAddress"/>使用新地址
            </li>
           </ul>
           <p></p>
           <table style="width:100%;display:none" id="new_address">
             <tbody>
               <tr>
                 <th><label>地址：</label></th>
                 <td>
                   <span>
                     <select id="college" name="newCampusId">
                       <c:forEach items="${campusBeans }" var="campus" >
                         <option value="${campus.campusId }">${campus.campusName }</option>
                       </c:forEach>
                     </select>
                     <select id="dormitory" name="newDormitoryName">
                       <c:forEach items="${dormitoryBeans }" var="dormitory">
                         <option value="${dormitory.dormitoryName }">${dormitory.dormitoryName }</option>
                       </c:forEach>
                     </select>
                     <input type="text" class="ext" name="newAddress" id="dormitory_id"
                              placeholder="更详细的单元及寝室号"/>
                   </span>
                 </td>
               </tr>
               <tr>
                 <th><label for="name">收货人：</label></th>
                 <td><input class="ext" placeholder="收货人姓名" type="text" 
                        id="user_name" name="newReceiveName"/></td>
               </tr>
               <tr>
                 <th><label for="mobile">联系电话：</label></th>
                 <td><input class="ext" placeholder="收货人联系电话" type="text"
                        id="user_phone" name="newReceivePhone" maxlength="11"/></td>
               </tr>
             </tbody>
           </table>
        </div>
      </div>
      
      <div class="arrivebox" style="border-bottom:#FCBDBC 1px solid">
        <h5>配送方式</h5>
        <div class="content" style="margin:0 10px">
          <p>运费：<span id="free">0</span>元</p>
          <ul class="select">
            <li class="checked">
              <input checked="checked" type="radio" value="1" name="receiveWay"/>自提点提货
             </li>
             <li>
              <input type="radio" value="0" name="receiveWay"/>送货上门
             </li>
          </ul>
        </div>
      </div>
    
    <div class="post_order" style="margin-top:20px;text-align:center">
      <button type="submit" class="btn btn-danger btn-lg">提交</button>
      <div style="padding-top:20px">
        <p id="note"></p>
      </div>
    </div>
    </form>
  <!-- </div> -->
  
  <script type="text/javascript">
    $(document).ready(function(){
      $('.number').attr({"readonly":true});
      $('#notes').attr("onblur", "AntiSqlValid(this)");
      $('#dormitory_id').attr("onblur", "AntiSqlValid(this)");
      $('#user_name').attr("onblur", "AntiSqlValid(this)");
      $('#user_phone').attr("onblur", "AntiSqlValid(this)");
    });
    
    //新地址输入验证
    function Sub(){
      var username = $('input[name="username"]').val();
      var mobile = $('input[name="usermobile"]').val();
      if(username == "" || mobile == ""){
        $('#note').html("用户名和电话不能为空！");
        return false;
      }
      if(mobile != "" && !(/^1[3|4|5|8][0-9]\d{4,8}$/).test(mobile)){
        alert("手机号码格式不正确，请重新输入");
        return false;
      }
    }
    
    //防SQL注入
    function AntiSqlValid(field){
      var re = /select|update|delete|exec|count|’|"|=|;|>|<|%/i;
      if(re.test(field.value)){
        $('#note').html("请您不要在参数中输入特殊字符和SQL关键字！");
        field.value = "";
        field.focus();
        return false;
      }
    }
  </script>
</body>
</html>