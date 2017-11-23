/**
 * Created by kee on 17/11/12.
 */
require(['jquery','yaya','echarts','bootstrap','daterangepicker', 'datatables.net'], function ($, yaya,echarts,DrawEChart,daterangepicker) {

    var locale = {
        "format": 'YYYY/MM/DD',
        "separator": "-",
        "applyLabel": "确定",
        "cancelLabel": "取消",
        "fromLabel": "起始时间",
        "toLabel": "结束时间'",
        "customRangeLabel": "自定义",
        "weekLabel": "W",
        "daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
        "monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
        "firstDay": 1
    };
    var myData = new Date();
    var endDate = myData.toLocaleDateString();
    myData.setDate(myData.getDate() - 6);
    var startDate = myData.toLocaleDateString();
    $('#consultOrderDate').daterangepicker({
        "autoApply": true,
        "startDate": startDate,
        "endDate": endDate,
        "locale": locale
    });
    $('#repairOrderDate').daterangepicker({
        "autoApply": true,
        "startDate": startDate,
        "endDate": endDate,
        "locale": locale
    });
    $('#repairOrderAllRatioDate').daterangepicker({
        "autoApply": true,
        "startDate": startDate,
        "endDate": endDate,
        "locale": locale
    });
    //4,
    $('#repairOrderShopRatioDate').daterangepicker({
        "autoApply": true,
        "startDate": startDate,
        "endDate": endDate,
        "locale": locale
    });
//首次进入统计的报表
    //1,咨询来源数量
    getConsultChannelAmountChart();
    //2,成交来源数量
    getRepairChannelAmountChart();
    //3,
    repairOrderAllProvinceRatioChart();
    //4,
    repairOrderShopRatioChart();

    //创建ECharts图表方法
    $(function(){

        //1,咨询来源数量 change 事件重新刷新组件，
        $('#consultOrderDate').on('apply.daterangepicker',function(ev, picker) {
            getConsultChannelAmountChart();
        });
        $('#consultOrderType').change(function () {
            getConsultChannelAmountChart();
        });

        //2,成交来源数量
        $('#repairOrderDate').on('apply.daterangepicker',function(ev, picker) {
            getRepairChannelAmountChart();
        });
        $('#repairOrderType').change(function () {
            getRepairChannelAmountChart();
        });

        //3,
        $('#repairOrderAllRatioDate').on('apply.daterangepicker',function(ev, picker) {
            repairOrderAllProvinceRatioChart();
        });
        $('#repairOrderAllRatioType').change(function () {
            repairOrderAllProvinceRatioChart();
        });

        //4,repairOrderShopRatioDate
        $('#repairOrderShopRatioDate').on('apply.daterangepicker',function(ev, picker) {
            repairOrderShopRatioChart();
        });
        $('#repairOrderShopRatioType').change(function () {
            repairOrderShopRatioChart();
        });
        $('#repairOrderShopId').change(function () {
            repairOrderShopRatioChart();
        });


    });

//1,咨询来源数量
    function getConsultChannelAmountChart() {
        //获得图表的options对象
        var myChart = echarts.init(document.getElementById('consultChannelAmountChart'));

        var option = {
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data:['北京7860']
            },
            toolbox: {
                show : true,
                orient: 'vertical',
                x: 'right',
                y: 'center',
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    data : ['周一','周二','周三','周四','周五','周六','周日']
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : []
        };

        //1,咨询来源数量
        $.ajax({
            url:ctx+"/data-report/consult-channel-query",
            data:{
                dateArea: $("#consultOrderDate").val(),
                type: $("#consultOrderType").val()
            },
            success:function (data) {
                option.xAxis[0].data = data.data.xAxis;
                option.series= data.data.series;
                option.legend.data=data.data.legend;
                myChart.hideLoading();
                myChart.setOption(option);
            }

        });



    };

    //2,成交来源数量
    function getRepairChannelAmountChart() {
        var repairChannelAmountChart = echarts.init(document.getElementById('repairChannelAmountChart'));
        var option = {
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data:[]
            },
            toolbox: {
                show : true,
                orient: 'vertical',
                x: 'right',
                y: 'center',
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    data : ['周一','周二','周三','周四','周五','周六','周日']
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
            url:ctx+"/data-report/repair-channel-query",
            data:{
                dateArea: $("#repairOrderDate").val(),
                type: $("#repairOrderType").val()
            },
            success:function (data) {
                option.xAxis[0].data = data.data.xAxis;
                option.series= data.data.series;
                option.legend.data=data.data.legend;
                repairChannelAmountChart.hideLoading();
                repairChannelAmountChart.setOption(option);
            }

        });
    }

    //3.全部成交地域占比--件数，金额：全国范围（到省，直辖市级）地图比例图，复合饼图，表格
    function repairOrderAllProvinceRatioChart() {
        var chart3 = echarts.init(document.getElementById('repairOrderAllRatioChart'));

        var option3 = {
            title : {
                text: '全部成交地域占比',
                subtext: '全部成交地域',
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient : 'vertical',
                x : 'left',
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {
                        show: true,
                        type: ['pie', 'funnel'],
                        option: {
                            funnel: {
                                x: '25%',
                                width: '50%',
                                funnelAlign: 'left',
                                max: 1548
                            }
                        }
                    },
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            series : [
                {
                    name:'全部成交地域',
                    type:'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data:[
                        {value:335, name:'客户上门'},
                        {value:310, name:'快递'},
                        {value:234, name:'上门取件'},
                    ]
                }
            ]
        };

        $.ajax({
            url:ctx+"/data-report/repair-all-province-ratio",
            data:{
                dateArea: $("#repairOrderAllRatioDate").val(),
                type: $("#repairOrderAllRatioType").val()
            },
            success:function (data) {
                console.log(data.data.chartPieVoList);
                console.log(data.data);
                // var tempList = [{value:10,name:'上海'}];
                option3.series[0].data=data.data.chartPieVoList;
                chart3.hideLoading();
                chart3.setOption(option3);

            }

        });

    }
    
    //4
    function repairOrderShopRatioChart() {
        var chart4 = echarts.init(document.getElementById('repairOrderShopRatioChart'));

        var option4 = {
            title : {
                text: '全部成交地域占比',
                subtext: '全部成交地域',
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient : 'vertical',
                x : 'left',
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {
                        show: true,
                        type: ['pie', 'funnel'],
                        option: {
                            funnel: {
                                x: '25%',
                                width: '50%',
                                funnelAlign: 'left',
                                max: 1548
                            }
                        }
                    },
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            series : [
                {
                    name:'全部成交地域',
                    type:'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data:[
                        {value:335, name:'客户上门'},
                        {value:310, name:'快递'},
                        {value:234, name:'上门取件'},
                    ]
                }
            ]
        };

        $.ajax({
            url:ctx+"/data-report/repair-shop-province-ratio",
            data:{
                dateArea: $("#repairOrderShopRatioDate").val(),
                type: $("#repairOrderShopRatioType").val(),
                shopId:$("#repairOrderShopId").val()
            },
            success:function (data) {
                console.log(data.data.chartPieVoList);
                console.log(data.data);
                // var tempList = [{value:10,name:'上海'}];
                option4.series[0].data=data.data.chartPieVoList;
                chart4.hideLoading();
                chart4.setOption(option4);

            }

        });

        
    }


});