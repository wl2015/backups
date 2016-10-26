/**
 * 订单
 */
$(document).ready(function(){
  
  var url = window.location.href;
  var webroot = "/" +url.split("/")[3];
  var finalUrl = webroot + "/admin/";
  
  //未完成订单详情
  $('#unfinish').datagrid({
    view: detailview,
    detailFormatter: function(index, row){
      return '<div class="ddv" style="padding:5px 0"></div>';
    },
    onExpandRow: function(index, row){
      var orderId = document.getElementById("index"+index).innerHTML;
      var ddv = $(this).datagrid('getRowDetail', index).find('div.ddv');
      ddv.panel({
        border:false,
        cache: false,
        href: finalUrl + "toOrderUnDetails?orderId="+orderId,
        onLoad: function(){
          $('#unfinish').datagrid('fixDetailRowHeight', index);
        }
      });
      $('#unfinish').datagrid('fixDetailRowHeight', index);
    }
  });
  
  //已完成订单详情
  $('#finished').datagrid({
    view: detailview,
    detailFormatter: function(index, row){
      return '<div class="ddv" style="padding:5px 0"></div>';
    },
    onExpandRow: function(index, row){
      var orderId = document.getElementById("index"+index).innerHTML;
      var ddv = $(this).datagrid('getRowDetail', index).find('div.ddv');
      ddv.panel({
        border:false,
        cache: false,
        href: finalUrl + "toOrderEdDetails?orderId="+orderId,
        onLoad: function(){
          $('#finished').datagrid('fixDetailRowHeight', index);
        }
      });
      $('#finished').datagrid('fixDetailRowHeight', index);
    }
  });
});

/**
 * 删除订单
 * */
function DeleteOrder(orderId){
  $.ajax({
        url:WEBROOT+'/admin/doDeleteOrder.do',
        type:'post',
        dataType:'json',
        data:{
          orderId:orderId,
        },
        success:function(res){
          if(res.result == AJAX_SUCCESS_CODE){
            window.location.reload();
          }
          else if(res.result == AJAX_FAIL_CODE){
            alert("删除失败请重试！！！");
          }
        }
  });
}
/***
 * 接受订单
 * */
function AcceptOrder(orderId){
    $.ajax({
          url:WEBROOT+'/admin/doAcceptOrder.do',
          type:'post',
          dataType:'json',
          data:{
            orderId:orderId,
          },
          success:function(res){
            if(res.result == AJAX_SUCCESS_CODE){
              window.location.reload();
            }
            else if(res.result == AJAX_FAIL_CODE){
              alert("接受失败请重试！！！");
            }
          }
    });
}
/***
 * 完成订单
 * */
function FinishOrder(orderId){
    $.ajax({
          url:WEBROOT+'/admin/doFinishOrder.do',
          type:'post',
          dataType:'json',
          data:{
            orderId:orderId,
          },
          success:function(res){
            if(res.result == AJAX_SUCCESS_CODE){
              window.location.reload();
            }
            else if(res.result == AJAX_FAIL_CODE){
              alert("完成失败请重试！！！");
            }
          }
    });
}