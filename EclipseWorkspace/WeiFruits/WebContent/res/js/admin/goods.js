/**
 * 商品管理
 */
$(document).ready(function(){
  $('#fruitname').attr("onblur", "AntiSqlValid(this, 0)");
  $('#fruitinfo').attr("onblur", "AntiSqlValid(this, 0)");
  $('#fruitprice').attr("onblur", "AntiSqlValid(this, 1)");
  $('#info').attr("onblur", "AntiSqlValid(this, 0)");
  
  //动态获取textarea内容字数
  $('#info').bind('keyup', function(e){
    if(e.which == 13){
      var textLen = $('#info').val().replace(/[\r\n]/g, "");
      $('#number').html(50 - textLen.length);
    }else{
      var con = $('#info').val().replace(/[\r\n]/g,"");
      console.log("内容长度："+con.length);
      $('#number').html(50 - con.length);
    }
  });
  
  var url = window.location.href;
  var webroot = "/" +url.split("/")[3];
  var finalUrl = webroot + "/admin/";
  
  $('#dg').datagrid({
    view: detailview,
    detailFormatter: function(index, row){
      return '<div class="ddv" style="padding:5px 0"></div>';
    },
    onExpandRow: function(index, row){
     var fruitId = document.getElementById("index"+index).innerHTML;
      var ddv = $(this).datagrid('getRowDetail', index).find('div.ddv');
      ddv.panel({
        border:false,
        cache: false,
        href: finalUrl + "toOldGoods?fruitId="+fruitId,
        onLoad: function(){
          $('#dg').datagrid('fixDetailRowHeight', index);
        }
      });
      $('#dg').datagrid('fixDetailRowHeight', index);
    }
  });
  
  //弹出窗口动态获取textarea内容字数
  $('#dialogInfo').bind('keyup', function(e){
    if(e.which == 13){
      var textLen = $('#dialogInfo').val().replace(/[\r\n]/g, "");
      $('#dialogNumber').html(50 - textLen.length);
    }else{
      var con = $('#dialogInfo').val().replace(/[\r\n]/g,"");
      console.log("内容长度："+con.length);
      $('#dialogNumber').html(50 - con.length);
    }
  });
  
  //上传图片
  $('#Imgupload').click(function(){
    $('#fileUp').click();
  });
  
  $('#fileUp').change(function(){
    //判断图片
    var file = document.getElementById('fileUp').files[0];
    console.log("文件名："+file.name);
    if (!/image\/\w+/.test(file.type)) {
      alert("您选择的不是图片文件");
      return false;
    }else {
      if (file.size > (1024*1024)){
        alert('图片过大，请重新选择1M以内的文件');
        return false;
      }else{
        doimgUpload();
      }
    }
  });
});
  
function doimgUpload(){
  var imgUploadUrl = '/wedding321_uploads/upload_json.jsp'; //上传图片地址
  $('#Imguploader').ajaxSubmit({
    dataType: 'json',
    url: imgUploadUrl,
    type: 'post',
    success:function(res){
      console.log("路径："+res.url);
      $("input[name='hiddeUrl']").val(res.url);
      $('img').attr('src', res.url);
      console.log(res.url);
    },
    error: function(res) {
      alert("上传失败！请重新上传");
      console.log("error:" + res);
    }
  });
}

  function EditGoods(fruitId){
    var fruitName = document.getElementById("fruitName"+fruitId);
    var fruitPic = document.getElementById("fruitPic"+fruitId);
    var fruitIntr = document.getElementById("fruitIntr"+fruitId);
    var fruitPrice = document.getElementById("fruitPrice"+fruitId);
    var fruitDetail = document.getElementById("fruitDetail"+fruitId);
    document.getElementById("fruitname").value=fruitName.value;
    document.getElementById("fruitinfo").value=fruitIntr.value;
    document.getElementById("fruitprice").value=fruitPrice.value;
    document.getElementById("info").value=fruitDetail.value;
    document.getElementById("fruitId").value=fruitId;
    //把老的图片地址写入要提交的组件里
    document.getElementById("hidPic").value=fruitPic.value;
    $("#dd").dialog('open');
  }
  
  //防SQL注入
  function AntiSqlValid(field, flag){
    var re = /select|update|delete|exec|count|’|"|=|;|>|<|%/i;
    var res = /^\d*\.{0,1}\d{0,1}$/;
    if(flag == 0){
      if(re.test(field.value)){
        alert("请您不要在参数中输入特殊字符和SQL关键字！");
        field.value = "";
        field.focus();
        return false;
      }
    }
    if(flag == 1){
      //单价框只能输入数字和小数点
      if(!res.test(field.value)){
        $('#babble').css({'display':''});
        field.value = "";
        field.focus();
        return false;
      }else{
        $('#babble').css({'display':'none'});
      }
    }
  }
  
  
function deleteGood(fruitId){
	//alert("aaa")
  $.ajax({
        url:WEBROOT+'/admin/deleteFruit.do',
        type:'post',
        dataType:'json',
        data:{
          fruitId:fruitId
        },
        success:function(res){
          if(res.result == AJAX_SUCCESS_CODE){
            window.location.reload();
          }
          else if(res.result == AJAX_FAIL_CODE){
            alert("数据出错！！！");
          }
        }
  });
}
