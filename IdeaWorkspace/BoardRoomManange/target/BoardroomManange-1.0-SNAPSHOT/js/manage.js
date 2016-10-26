$(function(){
    $('#morning').show();
    $('#afternoon').show();
    getback();

    $("#room").find("li").remove();

    var htmlContent = '<li onclick="aaa();">第一会议室</li>';
    $("#room").append(htmlContent);

    $(".room"+0).click(function(){
        alert("success");
    });
    getMYRooms();
});
function aaa(){
    alert("aa");
}
/**
 * 获取绵阳的会议室列表
 * */
function getMYRooms(){
    $.ajax({
        url:'../common/rooms',
        type:'post',
        data:{
            city:"绵阳"
        },
        success:function(res){
            alert("aaaaaaaa");
        }
    });
}

/***
 *初始化表格
 * */
function getback(){
    //上午
    $('#83').attr("style","background-color:green");
    $('#83').html("空闲");
    $('#84').attr("style","background-color:green");
    $('#84').html("空闲");
    $('#91').attr("style","background-color:green");
    $('#91').html("空闲");
    $('#92').attr("style","background-color:green");
    $('#92').html("空闲");
    $('#93').attr("style","background-color:green");
    $('#93').html("空闲");
    $('#94').attr("style","background-color:green");
    $('#94').html("空闲");
    $('#101').attr("style","background-color:green");
    $('#101').html("空闲");
    $('#102').attr("style","background-color:green");
    $('#102').html("空闲");
    $('#103').attr("style","background-color:green");
    $('#103').html("空闲");
    $('#104').attr("style","background-color:green");
    $('#104').html("空闲");
    $('#111').attr("style","background-color:green");
    $('#111').html("空闲");
    $('#112').attr("style","background-color:green");
    $('#112').html("空闲");
    $('#113').attr("style","background-color:green");
    $('#113').html("空闲");
    $('#114').attr("style","background-color:green");
    $('#114').html("空闲");

    //下午
    $('#131').attr("style","background-color:green");
    $('#131').html("空闲");
    $('#132').attr("style","background-color:green");
    $('#132').html("空闲");
    $('#133').attr("style","background-color:green");
    $('#133').html("空闲");
    $('#134').attr("style","background-color:green");
    $('#134').html("空闲");
    $('#141').attr("style","background-color:green");
    $('#141').html("空闲");
    $('#142').attr("style","background-color:green");
    $('#142').html("空闲");
    $('#143').attr("style","background-color:green");
    $('#143').html("空闲");
    $('#144').attr("style","background-color:green");
    $('#144').html("空闲");
    $('#151').attr("style","background-color:green");
    $('#151').html("空闲");
    $('#152').attr("style","background-color:green");
    $('#152').html("空闲");
    $('#153').attr("style","background-color:green");
    $('#153').html("空闲");
    $('#154').attr("style","background-color:green");
    $('#154').html("空闲");
    $('#161').attr("style","background-color:green");
    $('#161').html("空闲");
    $('#162').attr("style","background-color:green");
    $('#162').html("空闲");
    $('#163').attr("style","background-color:green");
    $('#163').html("空闲");
    $('#164').attr("style","background-color:green");
    $('#164').html("空闲");
    $('#171').attr("style","background-color:green");
    $('#171').html("空闲");
    $('#172').attr("style","background-color:green");
    $('#172').html("空闲");
}