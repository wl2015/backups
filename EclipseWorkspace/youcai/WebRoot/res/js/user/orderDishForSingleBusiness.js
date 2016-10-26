$(function(){
  //点击nav切换样式
  $('.orderNav .oneSelect').click(function(){
    $(this).addClass('navActive').siblings('li').removeClass('navActive');
    $(this).siblings('li').find('.select').hide();
  });
  //点击三角形显示select框
  $('.oneSelect .trangle').click(function(e){
    e.stopPropagation();  //阻止冒泡事件
    $(this).siblings('.select').show();
  });
  //点击下拉框的下拉选项进行选择
  $('.select li').click(function(e){
    e.stopPropagation();  //阻止冒泡事件
    var liValue = $(this).text();
    $(this).parents('.oneSelect').find('.bigTitle').text(liValue);
    $(this).parent('.select').hide();
    dishesOrDishGroupsSet(liValue);
    //allDishesSet('singleDishSelect.jsp');
  });
  
  $('#allDishes').click(function(){
    $.ajax({
        url:WEBROOT+'/user/getAllDishes.do',
        type:'post',
        data:{
        },
        success:function(res){
          showDishes(res);
        }
      });
  });
  
  $('#AlldishGroups').click(function(){
    $.ajax({
        url:WEBROOT+'/user/getAllDishGroups.do',
        type:'post',
        data:{
       },
        success:function(res){
         showDishGroups(res);
        }
      });
  });
  
  $('.goOrder').click(function(){
    window.location=WEBROOT+'/user/querySingleOrManyToOrder.do';
  });
  
  $('.clearCar').click(function(){
    $('#totalCost').html("0");
    window.location=WEBROOT+'/user/clearShoppingCar.do'
  });
});

function jian(dishId){
  var invent =  parseInt($('#'+dishId).html());
  var num = parseInt($('#num'+dishId).html());
  var price = parseFloat($('#price'+dishId).html());
  if(num>0){
    $('#num'+dishId).html(num-1);
    var totalCost = parseFloat($('#totalCost').html());
    $('#totalCost').html(totalCost - price);
    updatecar(dishId, price, num-1);
  }
}
function add(dishId){
  var invent =  parseInt($('#'+dishId).html());
  var num = parseInt($('#num'+dishId).html());
  var price = parseFloat($('#price'+dishId).html());
  if(num<invent){
    $('#num'+dishId).html(num+1);
    var totalCost = parseFloat($('#totalCost').html());
    $('#totalCost').html(totalCost + price);
    updatecar(dishId, price, num+1);
  }
}
function groupJian(groupId){
    var invent =  parseInt($('#'+groupId).html());
    var num = parseInt($('#num'+groupId).html());
    var price = parseFloat($('#price'+groupId).html());
    if(num>0){
      $('#num'+groupId).html(num-1);
      var totalCost = parseFloat($('#totalCost').html());
      $('#totalCost').html(totalCost - price);
      updatecarForDishGroup(groupId, price, num-1);
    }
}
function groupAdd(groupId){
    var invent =  parseInt($('#'+groupId).html());
    var num = parseInt($('#num'+groupId).html());
    var price = parseFloat($('#price'+groupId).html());
    if(num<invent){
      $('#num'+groupId).html(num+1);
      var totalCost = parseFloat($('#totalCost').html());
      $('#totalCost').html(totalCost + price);
      updatecarForDishGroup(groupId, price, num+1);
    }
}
function dishesOrDishGroupsSet(typeName){
  $.ajax({
    url:WEBROOT+'/user/getDishOrDishGroupListByTypeName.do',
    type:'post',
    data:{
      typeName:typeName
    },
    success:function(res){
      if(res.resultCode == AJAX_CODE_SUCCESS){
        if(res.target == TARGET_DISH){
          showDishes(res);
        }
        else if(res.target == TARGET_DISHGROUP){
          showDishGroups(res);
        }
      }
      else if(res.resultCode == AJAX_CODE_FAIL){
        alert("获取菜品失败");
      }
    }
  });
}

function showDishes(res){
  $('.allDishesUl').html("");
  $('.allSets').html("");
  for(var i=0;i<res.dishList.length;i++){
    dish = res.dishList[i];
    var htmlContent = '<li class="oneDish">'
      + '<div class="oneDishTop clearfix">'
      + '<div class="oneDishTopLeft"><a href="#"><img src="res/img/food.jpg"/></a></div>'
      + '<div class="oneDishTopRight">'
      + '<p class="dishTitle"><a href="oneDishDetail.jsp" class="dishName">'
      + dish.dishName +'</a>'
      + '<span class="dishTaste">('+dish.dishTaste +')</span></p>'
      + '<p class="inventory">库存[<span class="inventoryNum" id="'+dish.dishId+'">'+dish.count +'</span>]</p>'
      + '<p class="dishIntro">'+dish.dishIntro +'</p>'
      + '</div>'
      + '</div>'
      + '<div class="oneDishBottom clearfix">'
      + '<div class="dishPrice">'
      + '<p>原价：￥<span>'+dish.originalPrice +'</span></p>'
      + '<p>现价：￥<span id="price'+dish.dishId +'">'+dish.retailPrice +'</span></p>'
      + '</div>'
      + '<div class="dishOperate clearfix">'
      + '<span class="reduce" onclick="jian('+dish.dishId+')">-</span>'
      + '<span class="copies" id="num'+dish.dishId+'">0</span>'
      + '<span class="add" onclick="add('+dish.dishId+')">+</span>'
      + '</div>'
      + '</div>'
      + '<ul class="mark clearfix">';
    for(var j=0; j<dish.typeList.length;j++){
      var type = dish.typeList[j];
      htmlContent = htmlContent + '<li>'+type.typeName +'</li>'
    }
    htmlContent = htmlContent + '</ul>' + '</li>';
    htmlContent = $(htmlContent);
    $('.allDishesUl').append(htmlContent);
  }
}

function showDishGroups(res){
  $('.allDishesUl').html("");
  $('.allSets').html("");
  for(var i=0; res.dishGroupList.length;i++){
    dishGroup = res.dishGroupList[i];
    var htmlContent = '<li class="oneKindSet">'
      + '<p class="oneKindSetName">'+dishGroup.groupName+'</span>'
      + '<strong>库存[<span class="inventoryNum" id="'+dishGroup.groupId+'">'+dishGroup.count+'</span>]</strong></p>'
      + '<div class="group clearfix">'
      //套餐中第一份菜
      + '<div class="oneDishOfGroup">'
      + '<div class="oneDishOfGroupTop clearfix">'
      + '<div class="oneDishOfGroupTopLeft"><a href="#"><img src="res/img/food.jpg"/></a></div>'
      + '<div class="oneDishOfGroupTopRight">'
      + '<p class="dishTitle"><a href="#" class="dishName">'+dishGroup.dishList[0].dishName+'</a></p>'
      + '<p class="dishTaste">(麻辣)</p>'
      + '</div>'
      + '</div>'
      + '<div class="oneDishOfGroupBottom">'
      + '<p>原价：￥<span>'+dishGroup.dishList[0].originalPrice+'</span></p>'
      + '<p>现价：￥<span>'+dishGroup.dishList[0].retailPrice+'</span></p>'
      + '</div>'
      + '</div>'
      + '<div class="multiply">X</div>'
      + '<div class="dishNum"><span>'+dishGroup.dishList[0].num+'</span>份</div>'
    for(var j=1;j<dishGroup.dishList.length;j++){
      dish = dishGroup.dishList[j];
      htmlContent = htmlContent + '<div class="dishAdd">+</div>'
      + '<div class="oneDishOfGroup">'
      + '<div class="oneDishOfGroupTop clearfix">'
      + '<div class="oneDishOfGroupTopLeft"><a href="#"><img src="res/img/food.jpg"/></a></div>'
      + '<div class="oneDishOfGroupTopRight">'
      + '<p class="dishTitle"><a href="#" class="dishName">'+dish.dishName+'</a></p>'
      + '<p class="dishTaste">(麻辣)</p>'
      + '</div>'
      + '</div>'
      + '<div class="oneDishOfGroupBottom">'
      + '<p>原价：￥<span>'+dish.originalPrice+'</span></p>'
      + '<p>现价：￥<span>'+dish.retailPrice+'</span></p>'
      + '</div>'
      + '</div>'
      + '<div class="multiply">X</div>'
      + '<div class="dishNum"><span>'+dish.num+'</span>份</div>'
    }
    htmlContent = htmlContent + '</div>'
    + '<div class="reduceAdnAddButton clearfix">'
    + '<span class="reduce" onclick="groupJian('+dishGroup.groupId+')">-</span>'
    + '<span class="copies" id="num'+dishGroup.groupId+'">0</span>'
    + '<span class="add" onclick="groupAdd('+dishGroup.groupId+')">+</span>'
    + '</div>'
    + '<div class="setAllMoney clearfix">'
    + '<p><del>套餐价：￥<span>'+dishGroup.originalPrice+'0</span></del></p>'
    + '<p>惊喜价：￥<span id="price'+dishGroup.groupId +'">'+dishGroup.retailPrice+'</span></p>'
    + '</div>'
    + '<ul class="mark clearfix">'
    for(var m=0;m<dishGroup.typeList.length;m++){
      type = dishGroup.typeList[m];
      htmlContent = htmlContent + '<li>'
      + type.typeName + '</li>'
    }
    htmlContent = htmlContent + '</ul>'
    + '</li>'
    htmlContent = $(htmlContent);
    $('.allSets').append(htmlContent);
  }
}

/**
 * 更新购物车数据
 * */
function updatecar(targetId, price, number){
  $.ajax({
    url:WEBROOT+'/user/updateShoppingCar.do',
    type:'post',
    dataType:'json',
    data:{
      targetId:targetId,
      price:price,
      number:number,
      targetType:1
    },
    success:function(res){
      if(res.resultCode == AJAX_CODE_SUCCESS){
      }
      else if(res.resultCode == AJAX_CODE_FAIL){
        alert("菜品修改失败，请清空个购物车重新点菜");
      }
    }
  });
}
/**
 * 更新购物车数据
 * */
function updatecarForDishGroup(targetId, price, number){
  $.ajax({
    url:WEBROOT+'/user/updateShoppingCar.do',
    type:'post',
    dataType:'json',
    data:{
      targetId:targetId,
      price:price,
      number:number,
      targetType:2
    },
    success:function(res){
      if(res.resultCode == AJAX_CODE_SUCCESS){
        if(res.canNotMeet==0){
          
        }
        else if(res.canNotMeet==1){
          var num = parseInt($('#num'+res.groupId).html());
          var price = parseFloat($('#price'+res.groupId).html());
          $('#num'+res.groupId).html(num-1);
          var totalCost = parseFloat($('#totalCost').html());
          $('#totalCost').html(totalCost - price);
          alert("库存不足");
        }
      }
      else if(res.resultCode == AJAX_CODE_FAIL){
        alert("菜品修改失败，请清空个购物车重新点菜");
      }
    }
  });
}