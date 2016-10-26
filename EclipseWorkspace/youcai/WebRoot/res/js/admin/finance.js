$(document).ready(function(){
  
  //滚动视图
  setInterval(function(){
    var newLi = $('#ulScroll').children().first().clone(true);
    $('#ulScroll').append(newLi);
    $('#ulScroll').children().first().remove();
  },2000);
  
  
  
  
  /**
   * 给出货数量框输入框只能输入数字
   */
  $("#salesNum").keyup(function(){     
    var tmptxt=$(this).val();     
    $(this).val(tmptxt.replace(/[^\d.]/g,''));     
      }).bind("paste",function(){     
          var tmptxt=$(this).val();     
      $(this).val(tmptxt.replace(/\D|^0/g,'')); 
   }).css("ime-mode", "disabled");    
  
  
  
  
  //提交出货单
  $('#addSales').click(function(){
    //$("#salesForm").submit();  
    
    var dishId = $("select[name='dishId']").val();
    var merchantId = $("input[name='merId']").val();
    var salesNum = $("input[name='salesNum']").val();
    var salesTime = $("input[name='salesTime']").val();
    
    alert(dishId + merchantId + salesNum + salesTime);
    if (!isEmpty(dishId) && !isEmpty(merchantId) && !isEmpty(salesNum) 
                         &&!isEmpty(dishId) &&!isEmpty(salesTime)) {
      
      //ajax提交一条出货单
      $.ajax({
        url : WEBROOT + "/admin/addSales.do",
        type: "post",
        data:{
          dishId:dishId,
          merId:merchantId,
          salesNum:salesNum,
          salesTime:salesTime
        },
        dataType: "json", 
        success: function(res) {

          alert("出货单创建成功！");//做一个数据回显
          contentSet('admin/sales.do');
          
        },

        error:function(){
          alert("系统繁忙，请稍后操作");
        }
      });
      
    } else {
        alert("出货信息必须完整！");
        return false;
    }

  });
  
  
  /**
   * 关键字寻找商家
   */
  $('#serachMerchant').click(function(){
    
    $("#showMerList").find(".temp").remove();
    var searchKey = $('#searchKey').val();
    alert(searchKey);
    
    //ajax查询商家
    $.ajax({
      url : WEBROOT + "/admin/searchMerList.do",
      type: "post",
      dataType: "json",
      data:{keywords:searchKey},
      success: function(res) {

        //alert("ajax返回成功！");
        alert(res.merchantList[0].shopName);
        loadMerchantListInfo(res.merchantList);
        
      },

      error:function(){
        alert("系统繁忙，请稍后操作");
      }
    });
    
    
    //如果有符合条件的搜索结果
    $('.showResult>table').show();
  });


  /**
   * 查询财务统计按钮
   */
  $('#queryFinance').click(function(){
    
    var dateFrom = $('#dateFrom').val();
    var dateTo = $('#dateTo').val();
   
    //alert(dateFrom);
    $("#financeList").find(".temp").remove();
    showfinanceList(dateFrom,dateTo);
  });
  

});


/**
 * 做循环的函数,循环商家列表
 * @param data
 */
function loadMerchantListInfo(data){
  $(data).each(function(index){
    
    var merchantId = this.merchantId;
    var shopName = this.shopName;
    var registerTime = this.registerTime;


    var PREVIEW_CONTENT= ' <tr class=temp>'
      + '                                <td>'
      + '                                   '+merchantId
      + '                                </td>'
      + '                                <td>'
      + '                                   <a href="javascript:void(0);" onclick=selectMerchant('+merchantId+',"'+shopName+'")>'+shopName
      + '                                   </a>'
      + '                                </td>'
      + '                                <td>'
      + '                                  '+registerTime
      + '                                </td>'
      + '                  </tr>';

    $("#showMerList").append(PREVIEW_CONTENT);
    
   
  });
}

//点击商家名获取id
function selectMerchant(id,shopName){
  //alert(id+shopName);
  $('#merchantId').html(shopName);
  $('#searchKey').val(shopName);
  $('#merId').val(id);
 }


/**
 * ajax显示菜品列表
 */
function showfinanceList(dateFrom,dateTo) {
  alert("显示财务统计");
  $.ajax({
    url : WEBROOT + "/admin/queryFinance.do",
    type: "post",
    data:{
      dateFrom:dateFrom,
      dateTo:dateTo},
    dataType: "json",
    success: function(res) {

      //alert("ajax返回成功！");
      //alert(res.totalMoney);
      $('#totalMoney').html("时间段总销售额："+res.totalMoney+"元");
      loadInfo(res.salesTotalList,dateFrom,dateTo);
      
    },

    error:function(){
      alert("系统繁忙，请稍后操作");
    }
  });
}

/**
 * 做循环的函数
 * @param data
 */
function loadInfo(data,timeFrom,timeTo){
  $(data).each(function(index){

    var dishName = this.dishName;
    var costSales = this.costSales;
    var numTotal = this.numTotal;
    var costTotal = this.costTotal;


    
    var PREVIEW_CONTENT= ' <tr class=temp>'
      + '                                <td>'
      + '                                   '+dishName
      + '                                 </td>'
      + '                                <td>'
      + '                                   '+costSales
      + '                                 </td>'
      + '                                <td>'
      + '                                  '+numTotal
      + '                                 </td>'
      + '                                <td>'
      + '                                   '+costTotal
      + '                                 </td>'
      + '                                <td>'
      + '                                   '+timeFrom+'到'+timeTo
      + '                                 </td>'
      + '                  </tr>';

    $("#financeList").append(PREVIEW_CONTENT);
    
   
  });
}





/**
 * 验证输入框是否有非法字符
 */ 
var valiInput = function(obj){
  
  var patrn=/[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;  
  
  if (patrn.test(obj)) {
    
    $("#input-body").find("#errorMsg").html("含有非法字符");
    return false;
  } else {
    $("#input-body").find("#errorMsg").html("通过");
    return true;  
  }
};

/**
 * 判断为空
 */
var isEmpty = function(obj) {
  if (obj == null || obj == undefined || jQuery.trim(obj).length == 0 || obj == "" || obj === "null") {
    return true;
  }
  return false;
};