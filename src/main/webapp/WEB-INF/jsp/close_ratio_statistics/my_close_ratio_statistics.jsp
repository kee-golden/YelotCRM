<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>咨询单统计</title>

    <%@include file="/WEB-INF/common/static.jsp" %>

    <c:set var="PARENT_MENU_CODE" value="CloseRatioStatistics" />
    <c:set var="CHILD_MENU_CODE" value="MyCloseRatioStatistics"/>
    <script>
        var ctx = '${ctx}';
    </script>


</head>
<style>


.btn-default{color: #333;background-color: #fff;border-color: #ccc;}
.top_row{ padding: 15px;}
.top_con{   box-shadow: 0 1px 5px 0 #e5e5e5; background: #FFFFFF;}
.tab-content{ background: #FFFFFF}
#myTab{ position: relative;}
#myTab li{ margin-right: 20px}
#myTab span{ display: inline-block; font-size: 11px;color: #FFFFFF; background: #c46f82;text-align: center;
     border-radius: 50%; width: 23px; height: 23px; line-height: 23px; position: absolute; right:-12px; top: -12px;}
    .top_con h6{ height:28px; background: #f1efef; font-size: 14px;  line-height: 28px; font-weight: 600; padding-left: 10px; margin-bottom: 20px}
    .work{ margin-right: 10px;}
#box01,#box02{ height:550px; padding-top: 10px}
.iconfont{ margin-right: 10px; font-size: 24px; cursor: pointer}
.container h6{ overflow: hidden}
    #page-wrapper,.wrapper,.wrapper-content{ padding: 0!important;}
</style>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
    <%@include file="/WEB-INF/common/top_logo_nav.jsp" %>
    <%@include file="/WEB-INF/common/slideBar.jsp" %>

    <!--右侧部分开始-->


    <div id="page-wrapper" class="wrapper wrapper-content cover_banner "
        style="height: 88%;overflow-y: auto; ">
        <div class="container top_con" style=" width: 100%;min-width: 1200px">
            <h6><span class="glyphicon glyphicon-folder-open work"></span>咨询单个人统计<i class="pull-right iconfont ">&#xe658;</i><div class="clearfix"></div></h6>
                <div class="col-md-12" id="box01">

                </div>
            <div class="col-md-12" id="box02">

            </div>
        </div>
    </div>
</div>
</body>

<script src="${ctx}/static/require/require.js"></script>
<script src="${ctx}/static/require/require.config.js"></script>
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
                    text: '个人成交率统计',
                    subtext: '成交率统计'
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['成交量']
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
                        name:'成交率',
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
                url:ctx+"/consult-statistics/my-month-data",
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
//                    {
//                        name:'皮具',
//                        type:'bar',
//                        data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
//                    },
//                    {
//                        name:'珠宝',
//                        type:'bar',
//                        data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
//                    },
//                    {
//                        name:'腕表',
//                        type:'bar',
//                        data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
//                    },
//                    {
//                        name:'玉石',
//                        type:'bar',
//                        data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
//                    }
                ]
            };


            $.ajax({
                url:ctx+"/consult-statistics/my-month-category-data",
                success:function (data) {
                    option2.series= data.data.series;
                    myChart2.hideLoading();
                    myChart2.setOption(option2);
                }

            });
        }


        

    });

</script>
</html>
