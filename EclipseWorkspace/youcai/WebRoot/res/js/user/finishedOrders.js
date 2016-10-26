$(function(){
  
  
  //alert($('.divSure').width());
  
  $('.detailButton').click(function(){
    $(this).next().slideToggle();
  });
});



//加载更多的已完成订单
function addMoreFinishedOrderShow(){
  var pageNum = $('#pageNum').html();
  $.ajax({
    url:WEBROOT+'/user/getMoreFinishedOrderToShow.do',
    type:'post',
    data:{
      pageNum:pageNum
    },
    success:function(res){
      if(res.resultCode == AJAX_CODE_SUCCESS){
        if(res.noResult==0){
          showFinishedOrder(res);
          $('#pageNum').html(parseInt($('#pageNum').html())+1);
        }
        if(res.noResult==1){
          $('#add').hide();
          $('#noResult').show();
        }
      }
      else if(res.resultCode == AJAX_CODE_FAIL){
        alert("操作出错，请重试");
      }
      
    }
  });
}

//隐藏订单
function hideOrder(orderId){
  $.ajax({
    url:WEBROOT+'/user/hideFinishedOrder.do',
    type:'post',
    data:{
      orderId:orderId
    },
    success:function(res){
      if(res.resultCode == AJAX_CODE_SUCCESS){
        $('#order'+orderId).hide();
      }
      else{
        alert("隐藏失败");
      }
    }
  });
}

//显示获取到的已完成订单
function showFinishedOrder(res){
  for(var i=0;i<res.orderslist.length;i++){
    order = res.orderslist[i];
    var htmlContent =  '<div class="oneOrder" id="order'+order.oId +'">'
    + '  <ul class="list">'
    + '    <li class="list1">'
    + '       <div>订单号：<span>'+order.oId +'</span></div>'
    + '     <div>'+order.orderTime +'</div>'
    + '    </li>'
    + '    <li class="list2"><span>'+order.merchant.shopName+'</span></li>'
    + '    <li class="list3">电话：<span>'+order.merchant.linkPhone+'</span></li>'
    + '    <li class="list4">送餐地址：<span>'+order.address +'</span></li>'
    + '    <li class="detail"><a href="javascript:void(0)" class="detailButton">订单详情</a>'
    + '      <div class="detailList">'
    + '        <ul>'
    + '          <li>'
    + '            <span>菜品名</span>'
    + '            <span>价格(元)</span>'
    + '            <span>数量</span>'
    + '          </li>';
    for(var j=0;j<order.dishlist.length;j++){
      dishlist = order.dishlist[j];
      htmlContent = htmlContent + 
        '          <li>'
      + '            <span>'+dishlist.dish.dishName+'</span>'
      + '            <span>'+dishlist.price+'</span>'
      + '            <span>'+dishlist.number+'</span>'
      + '          </li>';
    }
    htmlContent = htmlContent + 
      '        </ul>'
    + '        <div class="divTotal">总价：<span>'+order.money +'</span>元</div>'
    + '      </div>'
    + '    </li>'
    + '    <li class="list5">'
    + '      <div id="hideOrder" class="divDisplay" onclick="hideOrder('+order.oId+')">隐藏</div>'
    + '    </li>'
    + '  </ul>'
    + '</div>';
    htmlContent = $(htmlContent);
    $('#showOrder').append(htmlContent);
    htmlContent.find('.detailButton').click(function(){
      $(this).next().slideToggle();
    });
  }
  
//list5
  var list5H = getHeight($('.list5'),0.1);
  $('.list5').css({"height":list5H,"line-height":list5H});
  
 
}