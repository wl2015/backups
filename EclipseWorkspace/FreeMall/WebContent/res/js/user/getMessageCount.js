window.onload=function(){
  getCount();
}

function getCount(){
 $.ajax({
      url:WEBROOT+'/user/getUnReadedMessage.do',
      type:'post',
      dataType:'json',
      data:{
      },
      success:function(res){
        document.getElementById("messageCount").innerHTML=res.count+"未读";
      }
    });
}