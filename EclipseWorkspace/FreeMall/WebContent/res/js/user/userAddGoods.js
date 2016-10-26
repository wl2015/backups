$(document).ready(function(){
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
    	console.log(res);
    	console.log("daozheleh ");
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

/**
* 实时动态强制更改用户录入
* arg1 inputObject
**/
function amount(th){
    var regStrs = [
        ['^0(\\d+)$', '$1'], //禁止录入整数部分两位以上，但首位为0
        ['[^\\d\\.]+$', ''], //禁止录入任何非数字和点
        ['\\.(\\d?)\\.+', '.$1'], //禁止录入两个以上的点
        ['^(\\d+\\.\\d{2}).+', '$1'] //禁止录入小数点后两位以上
    ];
    for(i=0; i<regStrs.length; i++){
        var reg = new RegExp(regStrs[i][0]);
        th.value = th.value.replace(reg, regStrs[i][1]);
    }
}

function doSubmit(){
  var productName = document.getElementById("productName").value;
  var place = document.getElementById("place").value;
  var price = document.getElementById("price").value;
  var intro = document.getElementById("intro").value;
  var hiddeUrl = document.getElementById("hiddeUrl").value;
  if(productName.length > 0){
    if(place.length > 0){
      if(price.length > 0){
        if(intro.length > 0){
          if(hiddeUrl.length > 1){
            document.getElementById('doForm').submit();
          }
        }
      }
    }
  }
}