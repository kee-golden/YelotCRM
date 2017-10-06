<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<style>
    .box-container{
        height:1200px;
    }
    #box01,#box02{ height:550px; padding-top: 10px}
</style>

<div class="box-container">
    <div class="col-md-12" id="box01">

    </div>
    <div class="col-md-12" id="box02">

    </div>
</div>

<script type="text/javascript">

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
                    text: '个人咨询统计',
                    subtext: '咨询统计'
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['蒸发量']
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
                        name:'咨询量',
                        type:'bar',
                        data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
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
                url:ctx+"/consult-statistics/total-month-data",
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
                    text: '咨询分类统计',
                    subtext: '咨询统计'
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['咨询量']
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

                ]
            };


            $.ajax({
                url:ctx+"/consult-statistics/total-month-category-data",
                success:function (data) {
                    option2.series= data.data.series;
                    myChart2.hideLoading();
                    myChart2.setOption(option2);
                }

            });
        }


        

    });

</script>
