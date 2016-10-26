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
    //allDishesSet('singleDishSelect.jsp');
  });
  /**********************************************************/
  //清空购物车
  $('#clearAll').click(function(){
    if($('#totalCount').html()!=0){
      window.location=WEBROOT+'/user/clearShoppingCar.do'
    }
  });
  
  //选菜完成
  $('#goOrder').click(function(){
	  if($('#totalCount').html()!=0){
	        window.location=WEBROOT+'/user/toPushOrderListToMerchant.do';
	      }
	      else{
	        alert("还没选菜了");
	      }
  });
});

/**
 * 添加菜品数量，点击后数量加1
 * */
function add(dishId){
  var price = parseFloat($('#price'+dishId).html());
  var num = parseInt($('#num'+dishId).html());  
  document.getElementById("num"+dishId).innerHTML = num + 1;
  updatecar(dishId, price,num+1);
  var totalCount = parseInt($('#totalCount').html()) ;
  $('#totalCount').html(totalCount + 1 );
  var totalCost = parseFloat($('#totalCost').html());
  $('#totalCost').html(totalCost + price);
}
/**
 * 减少菜品数量，点击数量减1（当数量减到0时，就不会再减少）
 * */
function jian(dishId){
  var price = parseFloat($('#price'+dishId).html());
  var num = parseInt(document.getElementById("num"+dishId).innerHTML);
  if(num > 0){
    document.getElementById("num"+dishId).innerHTML = num - 1;
    updatecar(dishId, price, num-1);
    var totalCount = parseInt($('#totalCount').html()) ;
    $('#totalCount').html(totalCount - 1 );
    var totalCost = parseFloat($('#totalCost').html());
    $('#totalCost').html(totalCost - price);
  }
}
	/**
	 * 更新购物车数据
	 * */
	function updatecar(dishId, price, number){
	  $.ajax({
	    url:WEBROOT+'/user/updateShoppingCar.do',
	    type:'post',
	    dataType:'json',
	    data:{
	      dishId:dishId,
	      price:price,
	      number:number
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