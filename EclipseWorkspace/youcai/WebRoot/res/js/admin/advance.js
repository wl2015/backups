$(document).ready(function(){
  
  /**
   * 查询预付款统计按钮
   */
  $('#queryAdvance').click(function(){

    $("#advanceList").find(".temp").remove();
    var timeSlot = $('#timeSolt').val();
    showAdvanceList(timeSlot);
  });
  

  /**
   * 生成预付款单到数据库按钮
   */
  $('#addCreateAdvance').click(function(){



    var merchantId = $('#merchantId').val();
    var shopName = $('#shopName').val();
    var totalMoney = $('#totalMoney').val();
    var timeSlot = $('#timeSlot').val();

    
    $.ajax({
      url : WEBROOT + "/admin/addCreateAdvance.do",
      type: "post",
      dataType: "json",
      data:{
        merchantId:merchantId,
        shopName:shopName,
        totalMoney:totalMoney,
        timeSlot:timeSlot
      },
      success: function(res) {
        if (res.code == "SUCCESS") {
          alert("添加付款单成功！");
          contentSet('admin/createAdvanceList.do');
        }else if (res.code == "FAILE") {
          alert("没有可生成预付款记录！");
          return false;
        }
      },

      error:function(){
        alert("系统繁忙，请稍后操作");
      }
    });
  });

  
  
});


/**
 * ajax显示预付款记录列表
 */
function showAdvanceList(timeSlot) {
  alert("显示预付款统计");
  $.ajax({
    url : WEBROOT + "/admin/queryAdvance.do",
    type: "post",
    dataType: "json",
    data:{
      timeSlot:timeSlot
    },
    success: function(res) {

      //alert("ajax返回成功！");
      loadInfo(res.merWithCountList,timeSlot);
      
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
function loadInfo(data,timeSlot){
  $(data).each(function(index){

    var merchantId = this.merchant.merchantId;
    var shopName = this.merchant.shopName;
    var advanceCount = this.advanceCount;
      
    var PREVIEW_CONTENT= ' <tr class=temp>'
      + '                                <td>'
      + '                                   '+merchantId
      + '                                 </td>'
      + '                                <td>'
      + '                                   '+shopName
      + '                                 </td>'
      + '                                <td>'
      + '                                  ('+advanceCount+')'
      + '                                 </td>'
      + '                                <td>'
      + '                                   <a href="javascript:void(0)" onclick=contentSet("admin/queryAdvanceDetail.do?id='+merchantId+'&month='+timeSlot+'")>处理</a>'
      + '                                 </td>'
      + '                  </tr>';

    $("#advanceList").append(PREVIEW_CONTENT);
    
   
  });
}




/**
 * 判断为空
 */
var isEmpty = function(obj) {
  if (obj == null || obj == undefined || jQuery.trim(obj).length == 0 || obj == "" || obj === "null") {
    return true;
  }
  return false;
};