require(['jquery','yaya','echarts','bootstrap'], function ($, yaya,echarts,DrawEChart) {

   /*表格插件*/


    //创建ECharts图表方法
    $(function(){
        getChartData();//获取随机数据
        getChartData2();//获取随机数据
    });

    function getChartData() {
        //获得图表的options对象
        var myChart = echarts.init(document.getElementById('box01'));

        var option = {
            title : {
                text: '个人业绩统计',
                subtext: '业绩统计'
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['交易额']
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'交易额',
                    type:'bar',
                    data:[],
                    markPoint : {
                        data : [
                            {type : 'max', name: '最大值'},
                            {type : 'min', name: '最小值'}
                        ]
                    },
                    markLine : {
                        data : [
                            {type : 'average', name: '平均值'}
                        ]
                    }
                }
            ]
        };
        $.ajax({
            url:ctx+"/money-statistics/my-month-data",
            success:function (data) {
                option.series[0].data = data.data.sum;
                myChart.hideLoading();
                myChart.setOption(option);
            }

        });
    }

    function getChartData2() {
        //获得图表的options对象
        var myChart2 = echarts.init(document.getElementById('box02'));

        var option2 = {
            title : {
                text: '个人业绩分类统计',
                subtext: '业绩统计'
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:[]
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : []
        };


        $.ajax({
            url:ctx+"/money-statistics/my-month-category-data",
            success:function (data) {
            	option2.legend.data = data.data.legend;
                option2.series= data.data.series;
                myChart2.hideLoading();
                myChart2.setOption(option2);
            }

        });
    }
});