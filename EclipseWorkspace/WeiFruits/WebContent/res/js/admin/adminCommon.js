
  $(function(){
    checkNewOrderisExist();
    setInterval("checkNewOrderisExist()", 20*1000);
  });
//下一页
function turnToNextPage(){
  var pageNum = document.getElementById("page").innerHTML;
  document.getElementById("pageNum").value=parseInt(pageNum)+1;
  document.getElementById("pageForm").submit();
}
//上一页
function turnToBackPage(){
  var pageNum = document.getElementById("page").innerHTML;
  if(parseInt(pageNum)>1){
    document.getElementById("pageNum").value=parseInt(pageNum)-1;
    document.getElementById("pageForm").submit();
  }
}

//接收是否有新的订单
function checkNewOrderisExist(){
//alert("开始执行");
  $.ajax({
      url:WEBROOT+'/admin/checkNewOrderIsExist.do',
      type:'post',
      dataType:'json',
      success:function(res){
        if(res.count != 0){
          var alertOrder = "<span class='order-alert'>您现在有"+res.count+"份订单未接收，请尽快接收</span>";
          $('#north span').empty();
          $('#north').append(alertOrder);
        }
      }
    });
}