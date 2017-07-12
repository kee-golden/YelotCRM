/**
 * Created by kee on 17/7/13.
 */

    require(['jquery','yaya','echarts','bootstrap'], function ($, yaya,echarts,DrawEChart) {

        var span=document.getElementById("myTab").querySelectorAll("span")
        for(var i=0;i<span.length;i++){
            console.log(span[i].innerHTML)
            if(span[i].innerHTML==0){
                span[i].style.display="none";
            }
        }
        /* 菜单下拉功能*/
        $("h6 i").click(
            function(){
                var div=( $(this).parent().parent().find(".row"));
                div.slideToggle();
            }
        );

        /* 图标功能*/
        $(function(){
            $.ajax({
                url:ctx+'/example1',
                success:function(data){

                    $('#content').html(data);
                }
            });
        });

        $('#myTab a:first').tab('show');//初始化显示哪个tab

        $('#myTab a').click(function (e) {
            e.preventDefault();//阻止a链接的跳转行为
            //var val= $(e).parent().attr('');
            var sta=$(this).attr('sta');
            var name='';
            if(sta=='1'){
                name='/example1';
            }else if(sta=='2'){
                name='/example2';
            }else if(sta=='3'){
                name='/example3';
            }else if(sta=='4'){
                name='/change/change-approvaleds';
            }
            $.ajax({
                url:ctx+name,
                success:function(data){
                    $('#content').html(data);
                }
            });
            $(this).parent().parent().children().removeClass();
            $(this).parent().addClass("active");
        });

        /*表格插件*/


        //创建ECharts图表方法
        $(function(){
            myChart = echarts.init(document.getElementById('box01'));

            // 指定图表的配置项和数据
            var dummy = [
                ['2017冬','1月',0,0],
                ['','2月',0,0],
                ['2017春','3月',0,0],
                ['','4月',0,0],
                ['','5月',0,0],
                ['2017夏','6月',0,0],
                ['','7月',0,0],
                ['','8月',0,0],
                ['2017秋','9月',0,0],
                ['','10月',0,0],
                ['','11月',0,0],
                ['2017冬','12月',0,0]
            ];

            // 元数据处理，e.g. metadata.init().xxx
            var metadata = {
                flag: true,
                quarter: [],
                month: [],
                data1: [],
                data2: [],
                data3: [],
                x_major_offset: dummy[0][1].length,
                init: function () {

                    // 首次初始化
                    if (metadata.flag) {
                        // 数据遍历

                        for (var i = 0; i < dummy.length; i++) {
                            //debugger;
                            if (i === 0) {
                                metadata.quarter.push(dummy[i][0]);
                            } else {
                                // 与子分类列匹配
                                metadata.quarter.push(dummy[i - 1][0] === dummy[i][0] ? '' : dummy[i][0]);
                            }
                            metadata.month.push(dummy[i][1]);
                            metadata.data1.push(dummy[i][2]);
                            metadata.data2.push(dummy[i][3]);
                            console.log("-----"+dummy[11][3]);
                            metadata.data3.push('');
                            // 计算子分类字符长度（按汉字计算，*12号字体）
                            metadata.x_major_offset = metadata.x_major_offset > dummy[i][1].length ? metadata.x_major_offset : dummy[i][1].length;
                        }
                        metadata.flag = false;
                    }
                    return metadata;
                }
            };


            option = {
                //气泡提示框
                tooltip: {
                    axisPointer: {
                        type: 'shadow'
                    },
                    trigger: 'axis'
                },
                //直角坐标系中除坐标轴外的绘图网格，用于定义直角系整体布局
                grid: {
                    bottom: metadata.init().x_major_offset * 12 + 30
                },
                //图例，表述数据和图形的关联
                legend: {
                    data: ['事件', '问题']
                },
                color: [ '#FF999A', '#86D560', '#59ADF3','#FFCC67','#836FFF', '#AF89D6'],
                xAxis: [
                    {
                        type: 'category',
                        /*  axisLine: {show: false},
                         axisTick: {show: false},
                         axisLabel: {
                         rotate: 90
                         },
                         splitArea: {show: false},*/
                        data: metadata.init().month
                    },

                    {
                        type: 'category',
                        position: 'bottom',
                        offset: metadata.init().x_major_offset * 12,
                        axisLine: {show: false},
                        axisTick: {
                            length: metadata.init().x_major_offset * 12 + 20,
                            lineStyle: {color: '#CCC'},
                            interval: function (index, value) {
                                return value !== '';
                            }
                        },
                        splitArea: {
                            show: true,
                            interval: function (index, value) {
                                return value !== '';
                            }
                        },
                        data: metadata.init().quarter
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: '解决率',
                        min: 0,
                        max: 100,
                        interval: 25,
                        axisLabel: {
                            formatter: '{value} %'
                        }
                    }
                ],
                series: [
                    {
                        name: '事件',
                        type: 'line',
                        z: 1,
                        data: metadata.init().data1
                    },
                    {
                        name: '问题',
                        type: 'line',
                        z: 1,
                        data: metadata.init().data2
                    },
                    {
                        type: 'line',
                        xAxisIndex: 1,
                        z: 0,
                        data: metadata.init().data3
                    }
                ]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            myChart.hideLoading();
            getChartData();
        });

        function getChartData() {
            //获得图表的options对象
            var options = myChart.getOption();
            //通过Ajax获取数据
            $.ajax({
                url: ctx + '/prob-summary',
                success: function (sum) {
                    for (var i = 0; i < sum.data.length; i++) {
                        for (var j = 0; j < 12; j++) {
                            if (sum.data[i].monthss == j+1) {
                                options.series[1].data[j] = sum.data[i].resoRate;
                            }
                            myChart.hideLoading();
                            myChart.setOption(options);
                        }
                    }

                }
            });

            $.ajax({
                url: ctx + '/evnt-summary',
                success: function (sum) {
                    for (var i = 0; i < sum.data.length; i++) {
                        for (var j = 0; j < 12; j++) {
                            if (sum.data[i].monthss == j+1) {
                                options.series[0].data[j] = sum.data[i].resoRate;
                            }
                            myChart.hideLoading();
                            myChart.setOption(options);
                        }
                    }
                }
            });


        }

        $(function() {
            /* 第二块开始饼图*/
            box2 = echarts.init(document.getElementById('box02'));
            option = {
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data: ['知识库', '问题', '事件', '服务目录'],
                    show:false
                },
                color: [ '#FF999A', '#86D560', '#59ADF3','#FFCC67','#836FFF', '#AF89D6'],
                series: [
                    {
                        name: '数量',
                        type: 'pie',
                        radius:['45%','80%'],
                        data: [
                            {value: 0, name: '知识库'},
                            {value: 0, name: '问题'},
                            {value: 0, name: '事件'},
                            {value: 0, name: '服务目录'},
                        ],
                        itemStyle: {
                            emphasis: {
                                //shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        },
                        /*label:{
                         normal:{
                         show:true,
                         position:'inside',
                         //formatter: '{b} :{d}%',
                         formatter: '{b}:{d}%',
                         textStyle:{
                         color:'#000'
                         }
                         }
                         },*/
                        /*labelLine :{
                         normal:{
                         show:true,
                         smooth: true,
                         lineStyle: {
                         color: '#000',
                         width: 1,
                         type: 'solid'
                         },
                         }
                         }*/
                    }
                ]
            };
            box2.setOption(option);
            box2.hideLoading();
            getChartData1();
        })
        function getChartData1() {
            //获得图表的options对象
            var options = box2.getOption();
            //通过Ajax获取数据
            $.ajax({
                url: ctx + '/summary-count',
                success: function (sum) {
                    console.log(sum.data.kbmCt);
                    console.log(sum.data.probCt);
                    console.log(sum.data.evntCt);
                    if(sum.data.kbmCt==0&&sum.data.probCt==0&&sum.data.evntCt==0&&sum.data.servCt==0){


                    }
                    options.series[0].data[0].value = sum.data.kbmCt;
                    options.series[0].data[1].value = sum.data.probCt;
                    options.series[0].data[2].value = sum.data.evntCt;
                    options.series[0].data[3].value = sum.data.servCt;
                    box2.hideLoading();
                    box2.setOption(options);
                }
            });
        }


    });

function table_PendingCount() {
    $.ajax({
        url:ctx+'/indexshow/index-PendingCount',
        async:false,
        success:function(result){
            var s=$("#countAccepted");
            s.html(result);
        }
    });
    var span=$("#countAccepted");
    var val=$(span).html();
    if(val!=0){
        $(span).show();
    }else if (val==0){
        $(span).hide();
    }
}

function table_AuditCount() {
    $.ajax({
        url: ctx + '/indexshow/index-AuditCount',
        async:false,
        success: function (result) {
            var s = $("#count");
            s.html(result);
        }
    });
    var span=$("#count");
    var val=$(span).html();
    if (val != 0) {
        $(span).show();
    }else if (val==0){
        $(span).hide();
    }
}
function table_RequestCount() {
    $.ajax({
        url: ctx + '/indexshow/index-RequestCount',
        async:false,
        success: function (result) {
            var s = $("#countRequest");
            s.html(result);
        }
    });
    var span=$("#countRequest");
    var val=$(span).html();
    if(val!=0){
        $(span).show();
    }else if (val==0){
        $(span).hide();
    }
}

function table_total() {
    $.ajax({
        url: ctx + '/indexshow/index-total',
        async:false,
        success: function (result) {
            var s = $("#total");
            s.html(result);
        }
    });
    var span=$("#total");
    var val=$(span).html();
    if(val!=0){
        $(span).show();
    }else if (val==0){
        $(span).hide();
    }
}

