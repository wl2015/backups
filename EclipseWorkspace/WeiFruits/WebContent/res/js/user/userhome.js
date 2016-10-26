/**
 *商店主页的操作
 *1、展开显示详细信息
 *2、选择水果数量，购物车自动+1（-1）
 */
$(document).ready(function(){
  //详细信息展示
  $('.more1').click(function(){
      if($('#hidden > li').is(':hidden')){
        $('#hidden > li').slideDown();
        $('.more1').html("点击收起");
      }else{
        $('#hidden > li').slideUp();
        $('.more1').html("点击展开并购买");
      }
  });
  
  /**
   * 更新购物车图片信息
   * */
  function updateShoppingCarImgInfo(){
    var totalCount = document.getElementById("totalCount2").value;
    window.parent.document.getElementById("totalCount").innerHTML=totalCount;
    if(totalCount != 0){
      window.parent.document.getElementById("totalCount").style.display='';
    }
    else{
      window.parent.document.getElementById("totalCount").style.display='none';
    }
  }
  updateShoppingCarImgInfo();
  
  
  $(".list-group a").click(function() {
    $(this).css("background","#F9690E");//给点击的a标签加背景
    $(this).siblings().css("background","#ECECEC");//被点击之外的a标签背景为白色
  });
});

/**
 * 水果对象
 */
function Fruits(fruitId,fruitName,fruitPrice,fruitIntr,fruitDetail,fruitPic,sellCount, ele){
  var fruit = {};
  fruit.fruitId = fruitId;
  fruit.fruitName = fruitName;
  fruit.fruitPrice = fruitPrice;
  fruit.fruitIntr = fruitIntr;
  fruit.fruitDetail = fruitDetail;
  fruit.fruitPic = fruitPic;
  fruit.sellCount = sellCount;
  var class_name = $(ele).attr('class');
  if(class_name == "glyphicon glyphicon-plus add"){
    addCount(fruit);
  }
  else if(class_name == "glyphicon glyphicon-minus min"){
    reduceCount(fruit);
  }
}

function addCount(obj){
  var oldCount = document.getElementById("fruit"+obj.fruitId).value;
  var count = parseInt(oldCount)+1;
  //updateShoppingCar(fruitId,fruitName,fruitPrice,fruitIntr,fruitDetail,fruitPic,count,sellCount);
  updateShoppingCar(obj, count);
}
function reduceCount(obj){
  var oldCount = document.getElementById("fruit"+obj.fruitId).value;
  if(parseInt(oldCount) >= 1){
    var count = parseInt(oldCount)-1;
    //updateShoppingCar(fruitId,fruitName,fruitPrice,fruitIntr,fruitDetail,fruitPic,count,sellCount);
    updateShoppingCar(obj, count);
  }
}
function updateShoppingCar(obj, count){
  $.ajax({
      url:WEBROOT+'/user/updateShoppingCar.do',
      type:'post',
      dataType:'json',
      data:{
        fruitId:obj.fruitId,
        fruitName:obj.fruitName,
        fruitPrice:obj.fruitPrice,
        fruitIntr:obj.fruitIntr,
        fruitDetail:obj.fruitDetail,
        fruitPic:obj.fruitPic,
        count:count,
        sellCount:obj.sellCount
      },
      success:function(res){
        if(res.result == AJAX_SUCCESS_CODE){
          document.getElementById("fruit"+res.fruitId).value=res.count;
          window.parent.document.getElementById("totalCount").innerHTML=res.totalCount;
          window.parent.document.getElementById("totalCount").style.display='';
          if(res.totalCount != 0){
            window.parent.document.getElementById("totalCount").style.display='';
          }
          else{
            window.parent.document.getElementById("totalCount").style.display='none';
          }
        }
        else if(res.result == AJAX_FAIL_CODE){
          alert("菜品修改失败");
        }
      }
    });
}