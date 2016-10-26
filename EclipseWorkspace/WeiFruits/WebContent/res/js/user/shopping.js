/**
 * 购物车
 * 1、动态计算价格
 * 2、添加新地址
 * 3、移除商品
 */
$(document).ready(function(){
  //使用新地址
  $('input[name="chooseCode"]').click(function(){
    var address = $('input[name="chooseCode"]:checked').val();
    if(address === "checkNew"){
      $('#new_address').css({'display':''});
    }
  });
  
  //学校--宿舍联动
  $('#college').change(function(){
    var campusId = $(this).find('option:selected').val();
    $('#dormitory').html("");
    $.ajax({
      url: WEBROOT+'/user/getDormitoriesByCampusId.do',
      type: 'post',
      dataType: 'json',
      data: {campusId:campusId},
      success: function(res){
        var result = res.dormitoryBeans;
        for(var i=0; i<result.length; i++){
          var dormitory_name = result[i].dormitoryName;
          console.log(dormitory_name);
          $('#dormitory').append("<option value="+dormitory_name+">"+dormitory_name+"</option>");
        }
      },
      error: function(){
        alert("error");
      }
    });
  });
  
//是否送货上门
/*  $('input[name="arrive"]').click(function(){
    var aa = $('input[name="arrive"]:checked').val();
    var rr = $('#total_price').html();
    if(aa == 2 && rr < 30){
      $('#free').html(1);
    }
    else if(aa == 1){
      $('#free').html(0);
    }
  });*/
  TotalPrice();
});


//当用户已选择“送货上门”后，如果再增加或减少商品，运送费根据商品总价变化
function Rent(sum){
  if(sum < 30 && $('input[name="arrive"]:checked').val() == 2){
    $('#free').html(1);
  }else{
    $('#free').html(0);
  }
}

//总计
function TotalPrice(){
  var totalNumber = document.getElementsByClassName('totalnumber');
  var price = document.getElementsByClassName('price');
  var number = document.getElementsByClassName('number');
  var remove = document.getElementsByClassName('remove');
  var sum = 0;
  for(var i=0; i<totalNumber.length; i++){
   var nn = parseInt(parseFloat(price[i].innerHTML)*1000) * parseInt(number[i].value)/1000;
   totalNumber[i].innerHTML= nn;
   if(parseInt(number[i].value) == 0){
     remove[i].parentNode.remove();
   }
   sum += nn;
  }
  $('#total_price').html(Price(sum));
  Rent(sum);
}



//价格换算 保留一位小数
function Price(sum){
  var ss = Math.round(sum*Math.pow(10,3))/Math.pow(10,3);
  return ss;
}

/**
 * 水果对象
 */
function Fruits(fruitId,fruitName,fruitPrice,fruitIntr,fruitDetail,fruitPic,count,sellCount,ele){
  var fruit = {};
  fruit.fruitId = fruitId;
  fruit.fruitName = fruitName;
  fruit.fruitPrice = fruitPrice;
  fruit.fruitIntr = fruitIntr;
  fruit.fruitDetail = fruitDetail;
  fruit.fruitPic = fruitPic;
  fruit.sellCount = sellCount;
  fruit.count = count;
  var className = $(ele).attr('class');
  if(className == "glyphicon glyphicon-plus add"){
    addCount(fruit);
  }
  else if(className == "glyphicon glyphicon-minus min"){
    reduceCount(fruit);
  }
  else if(className == "remove"){
    removeGood(fruit, count);
  }
}

/***
 * 增加商品
 * */
function addCount(obj){
  var oldCount = document.getElementById("fruit"+obj.fruitId).value;
  var count = parseInt(oldCount)+1;
  //updateShoppingCar(obj.fruitId,obj.fruitName,obj.fruitPrice,obj.fruitIntr,obj.fruitDetail,obj.fruitPic,count,obj.sellCount);
  updateShoppingCar(obj, count);
}
/**
 * 减少商品
 * */
function reduceCount(obj){
  var oldCount = document.getElementById("fruit"+obj.fruitId).value;
  if(parseInt(oldCount) >= 1){
    var count = parseInt(oldCount)-1;
    //updateShoppingCar(fruitId,fruitName,fruitPrice,fruitIntr,fruitDetail,fruitPic,sellCount);
    updateShoppingCar(obj, count);
  }
}

/**
 * 删除商品
 * */
function removeGood(obj, count){
  updateShoppingCar(obj,count);
}
/**
 * 更新购物车
 * */
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
          TotalPrice();
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

/**
 * 新建地址栏，选中校区后，查询出该校区的所有宿舍楼
 */
/*function getDormitoriesByCampusId(){
  var collegeSelect = document.getElementById("college");
  var index = collegeSelect.selectedIndex; 
  var campusId = collegeSelect.options[index].value;
  $.ajax({
        url:WEBROOT+'/user/getDormitoriesByCampusId.do',
        type:'post',
        dataType:'json',
        data:{
          campusId:campusId
        },
        success:function(res){
          if(res.result == AJAX_SUCCESS_CODE){
            var cuitSelect = document.getElementById("dormitory");
            for(var j=0; j<cuitSelect.options.length;j++){
              cuitSelect.options.remove(j);
            }
            for(var i=0;i<res.dormitoryBeans.length;i++){
              dormitory = res.dormitoryBeans[i];
              cuitSelect.options.add(new Option(dormitory.dormitoryName,dormitory.dormitoryId));
            }
          }
          else if(res.result == AJAX_FAIL_CODE){
            alert("数据出错！！！");
          }
        }
    });
}*/