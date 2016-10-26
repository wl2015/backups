/**
 * 示例图表demo
 *
 * @author zhangyang
 * @date 2016年7月29日
 */

//echarts图表
function showLineChart() {
    var node = $('#line');

    var start = '';
    var end = '';
    //日期值去掉"-"
    function RegExp_start(){
        var _start = $('#setYear1').val();
        var _end = $('#setYear2').val();
        if(_start.indexOf("-")>=0){
            start ='';
            var ff = _start.split("-");
            for(var i in ff){
                start += ff[i];
            }
        }else {
            start = _start;
        }
        if(_end.indexOf("-")>=0){
            end = '';
            var en = _end.split("-");
            for(var k in en){
                end += en[k];
            }
        }else {
            end = _end;
        }

    }
    RegExp_start();

    var change = [];

    var data = {
        begin:start,
        end:end
    };
    //折线图
    $.ajax({
        type: "post",
        url: "/draw/drawbrokenline",
        data: data,
        success: function (result) {
            drawBarOrLine(node[0], "", result.data, [
                    '#f05050', '#e6b600', '#23b7e5', '#2b821d',
                    '#005eaa', '#339ca8', '#cda819', '#32a487'], [
                    "line", "line", "line"], {
                    x: {
                        unit: ''
                    },
                    y: [{
                        name: "积分值",
                        unit: ""
                    }],
                    unit: ["", "", ""]
                }, null, 0);
        },
        error: function () {
            drawBarOrLine(node[0], "", "", [
                '#f05050', '#e6b600', '#23b7e5', '#2b821d',
                '#005eaa', '#339ca8', '#cda819', '#32a487'], [
                "line", "line", "line"], {
                x: {
                    unit: ''
                },
                y: [{
                    name: "积分值",
                    unit: ""
                }],
                unit: ["", "", ""]
            }, null, 0);
        }
    });

}
function charts() {
    var node1 = $('#bar');
    var node2 = $('#pie');
    //柱状图
    $.ajax({
        type: "get",
        url: "/draw/drawpillarchart",
        success: function (res) {
            drawBarOrLine(node1[0], "", res.data, [
                '#f05050', '#e6b600', '#23b7e5', '#2b821d',
                '#005eaa', '#339ca8', '#cda819', '#32a487'], [
                "bar", "bar", "bar"], {
                x: {
                    unit: ''
                },
                y: [{
                    name: "积分值",
                    unit: ""
                }],
                unit: ["", "", ""]
            }, null, 0);
        },
        error: function (err) {
            drawBarOrLine(node1[0], "", "", [
                '#f05050', '#e6b600', '#23b7e5', '#2b821d',
                '#005eaa', '#339ca8', '#cda819', '#32a487'], [
                "bar", "bar", "bar"], {
                x: {
                    unit: ''
                },
                y: [{
                    name: "积分值",
                    unit: ""
                }],
                unit: ["", "", ""]
            }, null, 0);
        }
    });
    //饼图
    $.ajax({
        type: "get",
        url: "/draw/drawcircularchart",
        success: function (result) {
            drawPie(node2[0], null, result.data, "pie", "测试数据", null, "itemName", "itemCount",
                ['#f05050', '#e6b600', '#23b7e5', '#2b821d', '#005eaa', '#339ca8', '#cda819', '#32a487']);
        },
        error: function (err) {
            alert('请求不成功')
            drawPie(node2[0], null, "", "pie", "测试数据", null, "itemName", "itemCount",
                ['#f05050', '#e6b600', '#23b7e5', '#2b821d', '#005eaa', '#339ca8', '#cda819', '#32a487']);
        }
    });
    //热力地图
    $.ajax({
        type: "get",
        url: "/draw/drawheatchart",
        success: function (result) {
            drawHeatMap(result.data.listData);
        },
        error: function (err) {
            alert('热力地图数据请求不成功')
        }
    });
    //关系图
    $.ajax({
        type: "get",
        url: "/draw/drawrelationchart",
        success: function (res) {
            console.log(res);
            drawGraph(res.data);
        },
        error: function (err) {
            alert('关系图数据请求不成功')
        }
    });
}
//改变27号的数据
function change() {
    var add = $("#add").val();
    var reduce = $("#reduce").val();
    var data = {
        add: add,
        reduce: reduce
    };
    //改变数值
    $.ajax({
        type: "post",
        url: "/draw/changeintegral",
        data: data,
        success: function () {
            showLineChart()
        },
        error: function () {
            showLineChart()
        }
    });
}
$(function () {

    var dp1 = $("#dp1"),
        dp2 = $('#dp2');
    initSelect($(".btnSelect:eq(0)"), [
        {"name": "月", "value": "yyyy-mm"}, {"name": "周", "value": "yyyy-mm-dd"}, {"name": "天", "value": "yyyy-mm-dd"}
    ], function (data, i) {
        dp1.off("changeDate");
        dp2.off("changeDate");
        initDatePicker(dp1,dp2, data[i].value, data[i].name ,'startTime');//根据选择的 修改日期插件 的日期格式
        //initDatePicker(dp2, data[i].value, data[i].name,'endTime');//根据选择的 修改日期插件 的日期格式
        if (data[i].name == "周") {//按周来选的
            dp1.datepicker('setDaysOfWeekDisabled', [0, 1, 2, 3, 4, 5, 6]);//限制 只选择 周一
            dp2.datepicker('setDaysOfWeekDisabled', [0, 1, 2, 3, 4, 5, 6]);//限制 只选择 周一
        }
        dp1.datepicker('show');
        dp1.on("changeDate", function () {
            //console.log(dp1.children("input").val());
        });
    }, null, "月");
    initDatePicker(dp1,dp2, "yyyy-mm-dd", "天",'startTime');//初始化datePicker 按天 默认显示最近的星期一



    $("#btnQuery").click(function () {
        showLineChart();
    });
    $("#changeData").click(function () {
        change();
    });

    /**
     * 初始化图表
     */
    showLineChart();
    charts();
});