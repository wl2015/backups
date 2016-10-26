$(document).ready(function(){
  
  /**
   * 关键词查询商家按钮
   */
  $('#serachMerchantByKeywords').click(function(){
    
    var keywords = $('#keywords').val();
    //alert(keywords);

    if (!isEmpty(keywords)) {
      //关键词查询商家
      contentSet("manageMer/queryMerchantsByKeyWords.do?keywords="+keywords+"");
    } else {
        alert("关键词不能为空！");
    }
    
  });

    
    //驳回申请框
    $('.disagree').click(function(){
      $('.reason').show();
    //添加遮罩层
      var maskWidth = $(document).width();
      var maskHeight = $(document).height();
      $('<div class="mask"></div>').appendTo($('body'));
      $('.mask').css({
        "opacity":"0.4",
        "background":"grey",
        "position":"absolute",
        "left":0,
        "top":0,
        "width":maskWidth,
        "height":maskHeight,
        "z-index":1
      });
    });
    //取消提交
    $('.cancelReason').on('click',function(){
    //移除遮罩层
      $('.mask').remove();
      $('.reason').hide();
    });
    
    
    
    /**
     * 查询未审核商家按钮
     *//*
    $("#verifyMerchantList").find(".temp").remove();
      showVerifyMerchants();*/
    
    
    /**
     * 驳回商家申请
     */
    $('#verifyReject').click(function(){
       
       var r=confirm("确认驳回审核吗？"); //删除提示框
       if (r==true)
       {
         var merchantId =  $("#merchantId").val();
         var linkPhone =  $("#linkPhone").val();
         var rejectReason = $("#rejectReson").val();
         
         //alert("merchantId"+merchantId);
         //alert("rejectReason"+rejectReason);
         
         if (!isEmpty(rejectReason)) {
           $.ajax({
             url : WEBROOT + "/manageMer/verifyReject.do",
             type: "post",
             dataType: "json",
             data:{
               merchantId:merchantId,
               linkPhone:linkPhone,
               rejectReason:rejectReason
             },
             success: function(res) {

                 alert("驳回审核成功!");
               //移除遮罩层
                 $('.mask').remove();
                 $('.reason').hide();
                 contentSet('manageMer/showVerify.do');
             },
             error:function(){
               alert("系统繁忙，请稍后操作");
             }
           });
        } else {
          alert("驳回理由不能为空！");
          return false;
        }
       }
    });
    
    
    
    /**
     * 商家模式选择
     */
    $('#saveMode').click(function(){
      
      var modeType = $('input:radio[name="mode"]:checked').val();
      alert(modeType);
          $.ajax({
            url : WEBROOT + "/manageMer/modifyMode.do",
            type: "post",
            dataType: "json",
            data:{
              modeType:modeType,
            },
            success: function(res) {
              if (res.code == "SUCCESS") {
                alert("模式修改成功！");
                contentSet('manageMer/getMerchantMode.do');
                //window.location = WEBROOT + '/admin/adminIndex.do';
              }/*else if (res.code == "FAILE") {
                alert("模式修改失败！");
                window.location = WEBROOT + '/admin/login.do';
                return false;
              }*/
            },
            error:function(){
              alert("系统繁忙，请稍后操作");
            }
          });    
        
    });
    
});




/**
 * ajax删除商家
 */
function deleteMerchant(id){
 
  var r=confirm("确认注销该商家吗？"); //删除提示框
  if (r==true)
  {
      $.ajax({
        url : WEBROOT + "/manageMer/deleteMerchant.do",
        type: "post",
        dataType: "json",
        data:{id:id},
        success: function(res) {
          alert("商家注销成功!");
          if(res.data == "0"){
            $("#merchantList").find(".temp").remove();
            //showMerchants();
            contentSet('manageMer/show.do');
          }
          else{
            alert("跳转页面失败"); 
          }    
        },
  
        error:function(){
          alert("系统繁忙，请稍后操作");
        }
      });
  }


}



/**
 * 通过审核，提交审核表单
 */
function verifyPass(){
  
  var r=confirm("确认通过审核吗？"); //审核提示框
  if (r==true)
  {
    var merchantId = $('.merchantInfo').find('#id').val();
    
    var shopName = $('.merchantInfo').find('#shopName').val();
    var merchantIntro = $('.merchantInfo').find('#merchantIntro').val();
    var merchantAddress = $('.merchantInfo').find('#merchantAddress').val();
    var linkPhone = $('.merchantInfo').find('#linkPhone').val();
    var merchantMail = $('.merchantInfo').find('#merchantMail').val();
    var bossName = $('.merchantInfo').find('#bossName').val();
    var idCard = $('.merchantInfo').find('#idCard').val();
    var bankCard = $('.merchantInfo').find('#bankCard').val();
    
    //alert(shopName);
    //alert(merchantId);
    $.ajax({
      url : WEBROOT + "/manageMer/verifyMerchant.do",
      type: "post",
      dataType: "json",
      data:{merchantId:merchantId},
      success: function(res) {

        alert("审核通过！");
        contentSet('manageMer/showVerify.do');

      },
      error:function(){
        alert("系统繁忙，请稍后操作");
      }
    });

  }
}


/**
 * 驳回审核，发送驳回理由
 */
function verifyReject(){
  
  var r=confirm("确认驳回申请吗？"); //驳回审核提示框
  if (r==true)
  {
    var merchantId = $('.merchantInfo').find('#id').val();

    //alert(merchantId);
    $.ajax({
      url : WEBROOT + "/manageMer/verifyReject.do",
      type: "post",
      dataType: "json",
      data:{merchantId:merchantId},
      success: function(res) {
      //移除遮罩层
        $('.mask').remove();
        $('.reason').hide();
        alert("申请已驳回！");
        contentSet('manageMer/showVerify.do');

      },
      error:function(){
        alert("系统繁忙，请稍后操作");
      }
    });

  }
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






/**
 * ajax显示商家列表
 *//*
function showMerchants() {
  //alert("显示商家");
  $.ajax({
    url : WEBROOT + "/manageMer/queryMerchants.do",
    type: "post",
    dataType: "json",
    success: function(res) {

      //alert("ajax返回成功！");
      loadInfo(res.merchantList);
    },

    error:function(){
      alert("系统繁忙，请稍后操作");
    }
  });
}

*//**
 * 做循环的函数
 * @param data
 *//*
function loadInfo(data){
  $(data).each(function(index){
    var id = this.merchantId;
    var mName = this.shopName;
    var address = this.merchantAddress;
    var bName = this.bossName;
    var star = this.merchantStar;
    var linkphone = this.linkPhone;

    
    var PREVIEW_CONTENT= ' <tr class=temp>'
      + '                                <td>'
      + '                                   '+mName
      + '                                 </td>'
      + '                                <td>'
      + '                                   '+address
      + '                                 </td>'
      + '                                <td>'
      + '                                   '+bName
      + '                                 </td>'
      + '                                <td>'
      + '                                   '+star
      + '                                 </td>'
      + '                                <td>'
      + '                                   '+linkphone
      + '                                 </td>'
      + '                                <td>'
      + '                                   <a href=javascript:void(0); onclick=contentSet("manageMer/getMerchantById.do?id='+id+'")>详情</a>'
      + '                                 </td>'
      + '                                <td>'
      + '                                   <a href=javascript:void(0); onclick=deleteMerchant('+id+')>删除</a>'
      + '                                 </td>'
      + '                  </tr>';

    $("#merchantList").append(PREVIEW_CONTENT);
  });
}*/




/**
 * ajax显示未审核商家列表
 *//*
function showVerifyMerchants() {
  //alert("显示未审核商家");
  $.ajax({
    url : WEBROOT + "/manageMer/queryVerifyMerchants.do",
    type: "post",
    dataType: "json",
    success: function(res) {

      //alert("ajax返回成功！");
      loadVerifyInfo(res.verifyMerchantList);
    },

    error:function(){
      alert("系统繁忙，请稍后操作");
    }
  });
}

*//**
 * 未审核商家列表做循环的函数
 * @param data
 *//*
function loadVerifyInfo(data){
  $(data).each(function(index){
    
    var id = this.merchantId;
    var mName = this.shopName;
    var address = this.merchantAddress;
    var bName = this.bossName;
    var linkphone = this.linkPhone;
    var registerTime = this.registerTime;
    
    var PREVIEW_CONTENT= ' <tr class=temp>'
      + '                                <td>'
      + '                                   '+mName
      + '                                 </td>'
      + '                                <td>'
      + '                                   '+address
      + '                                 </td>'
      + '                                <td>'
      + '                                   '+bName
      + '                                 </td>'
      + '                                <td>'
      + '                                   '+linkphone
      + '                                 </td>'
      + '                                <td>'
      + '                                   '+registerTime
      + '                                 </td>'
      + '                                <td>'  
      + '                                   <a href=javascript:void(0); onclick=contentSet("manageMer/getVerifyMerchantById.do?id='+id+'") >审核</a>'
      + '                                 </td>'
      + '                  </tr>';

    $("#verifyMerchantList").append(PREVIEW_CONTENT);
  });
}*/


