<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>咨询单统计</title>

    <%@include file="/WEB-INF/common/static.jsp" %>
    <link rel="stylesheet" type="text/css" href="${ctx}/module-css/daterangepicker.css">

    <c:set var="PARENT_MENU_CODE" value="RptRepairOrder_Manage" />
    <c:set var="CHILD_MENU_CODE" value="DataReport"/>
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
.chartContainer{ height:520px; margin-top: 10px}
.iconfont{ margin-right: 10px; font-size: 24px; cursor: pointer}
.container h6{ overflow: hidden}
#page-wrapper,.wrapper,.wrapper-content{ padding: 0!important;}

.header{background: #fff;display: block;}
.title{font-size:14px;}
.condition{margin-top:5px;margin-bottom: 5px;height:20px;}
    .line{height: 5px;background: grey;}

</style>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
    <%@include file="/WEB-INF/common/top_logo_nav.jsp" %>
    <%@include file="/WEB-INF/common/slideBar.jsp" %>

    <!--右侧部分开始-->


    <div id="page-wrapper" class="wrapper wrapper-content cover_banner "
        style="height: 88%;overflow-y: auto; ">
        <div class="container top_con" style="width: 100%;min-width: 1200px">
            <h6><span class="glyphicon glyphicon-folder-open work"></span>数据报表统计<i class="pull-right iconfont ">&#xe658;</i><div class="clearfix"></div></h6>
            <div class="row chart-div">
                <div class="col-md-6">
                    <div class="header">
                        <div class="title">咨询来源数量</div>
                        <div class="condition">
                            <input type="text" style="float: left; width: 150px;" id="consultOrderDate" class="dateRange"/>
                            <select name="type" id="consultOrderType" class="" style="float:right;">
                                <option value="Day">按天</option>
                                <option value="Week">按周</option>
                                <option value="Month">按月</option>
                            </select>
                        </div>
                    </div>
                    <div class="chartContainer" id="consultChannelAmountChart"></div>
                </div>
                <div class="col-md-6">
                    <div class="header">
                        <div class="title">成交来源数量</div>
                        <div class="condition">
                            <input type="text" style="float: left; width: 150px;" id="repairOrderDate" class="dateRange">
                            <select name="type" id="repairOrderType" class="" style="float:right;">
                                <option value="Day">按天</option>
                                <option value="Week">按周</option>
                                <option value="Month">按月</option>
                            </select>
                        </div>

                    </div>
                    <div class="chartContainer" id="repairChannelAmountChart"></div>

                </div>
            </div>
            <div class="line"></div>
            <div class="row chart-div">
                <div class="col-md-6">
                    <div class="header">
                        <div class="title">全部成交方式占比(件数)</div>
                        <div class="condition">
                            <input type="text" style="float: left; width: 150px;" id="repairOrderAllRatioDate" class="dateRange"/>
                            <select name="type" id="repairOrderAllRatioType" class="" style="float:right;">
                                <option value="TotalAmount">总件数</option>
                                <option value="TotalPayment">总金额</option>
                            </select>
                        </div>
                    </div>
                    <div class="chartContainer" id="repairOrderAllRatioChart"></div>

                </div>
                <div class="col-md-6">
                    <div class="header">
                        <div class="title">门店成交方式占比</div>
                        <div class="condition">
                            <input type="text" style="float: left; width: 150px;" id="repairOrderShopRatioDate" class="dateRange">
                            <span style="float:right;">
                            <select name="repairOrderShopId" id="repairOrderShopId">
                                <c:forEach items="${shopList}" var="shop">
                                    <option value="${shop.id}"
                                    >${shop.name}</option>
                                </c:forEach>

                            </select>
                            <select name="type" id="repairOrderShopRatioType"  style="margin-left: 5px;">
                                <option value="TotalAmount">总件数</option>
                                <option value="TotalPayment">总金额</option>
                            </select>
                            </span>
                        </div>

                    </div>
                    <div class="chartContainer" id="repairOrderShopRatioChart"></div>

                </div>
            </div>
            <!-- 5,6 -->
            <div class="line"></div>
            <div class="row chart-div">
                <div class="col-md-6">
                    <div class="header">
                        <div class="title">成交方式占比(件数)</div>
                        <div class="condition">
                            <input type="text" style="float: left; width: 150px;" id="repairOrderBySendTypeRatioDate" class="dateRange"/>
                            <select name="type" id="repairOrderSendType" class="" style="float:right;">
                                <option value="Day">按天</option>
                                <option value="Week">按周</option>
                                <option value="Month">按月</option>
                            </select>
                        </div>
                    </div>
                    <div class="chartContainer" id="repairOrderSendTypeRatioChart"></div>

                </div>
                <div class="col-md-6">
                    <div class="header">
                        <div class="title">线上咨询地域占比</div>
                        <div class="condition">
                            <input type="text" style="float: left; width: 150px;" id="consultOrderProvinceRatioDate" class="dateRange">
                            <%--<span style="float:right;">--%>
                            <%--<select name="type" id="consultOrderProvinceType" class="" style="float:right;">--%>
                                <%--<option value="Day">按天</option>--%>
                                <%--<option value="Week">按周</option>--%>
                                <%--<option value="Month">按月</option>--%>
                            <%--</select>--%>
                            <%--</span>--%>
                        </div>

                    </div>
                    <div class="chartContainer" id="consultOrderProvinceRatioChart"></div>

                </div>
            </div>
            <!-- 7,8 -->
            <div class="line"></div>
            <div class="row chart-div">
                <div class="col-md-6">
                    <div class="header">
                        <div class="title">成交方式占比(件数)</div>
                        <div class="condition">
                            <input type="text" style="float: left; width: 150px;" id="repairOrderByCategoryTypeRatioDate" class="dateRange"/>
                            <span style="float:right;">
                                <div id="repairCategory">
                                <select class="prov col-xs-1" name="firstCategory" id="firstCategory"></select>
                                <select class="city col-xs-1" disabled="disabled"
                                        name="secondCategory" id="secondCategory"></select>
                                    </div>
                                <select name="type" id="repairOrderCategoryType" class="">
                                    <option value="Day">按天</option>
                                    <option value="Week">按周</option>
                                    <option value="Month">按月</option>
                                </select>
                            </span>
                        </div>
                    </div>
                    <div class="chartContainer" id="repairOrderCategoryTypeRatioChart"></div>

                </div>
                <div class="col-md-6">
                    <div class="header">
                        <div class="title">成交方式占比(件数)</div>
                        <div class="condition">
                            <input type="text" style="float: left; width: 150px;" id="consultOrderByCategoryTypeRatioDate" class="dateRange"/>
                            <span style="float:right;">
                                <div id="consultCategory">
                                    <select class="prov col-xs-1" name="firstCategory" id="firstCategory2"></select>
                                    <select class="city col-xs-1" disabled="disabled"
                                            name="secondCategory" id="secondCategory2"></select>
                                    </div>
                                <select name="type" id="consultOrderCategoryType" class="">
                                    <option value="Day">按天</option>
                                    <option value="Week">按周</option>
                                    <option value="Month">按月</option>
                                </select>
                            </span>
                        </div>
                    </div>
                    <div class="chartContainer" id="consultOrderCategoryTypeRatioChart"></div>

                </div>
            </div>
        </div>
    </div>
</div>
</body>

<script src="${ctx}/static/require/require.js"></script>
<script src="${ctx}/static/require/require.config.js"></script>
<script>
    require(['jquery','yaya','cityselect','daterangepicker'], function ($, yaya,cityselect,daterangepicker) {

        var jsonObj = eval(${categoryJson});//转化为json 对象
        $("#repairCategory").citySelect({
            url:jsonObj,
            prov:'${firstCategory}',
            city:'${secondCategory}',
            nodata:"none"
        });

        $("#consultCategory").citySelect({
            url:jsonObj,
            prov:'${firstCategory}',
            city:'${secondCategory}',
            nodata:"none"
        });


    });
</script>
<script src="${ctx}/module-js/report/data_report.js"></script>
</html>
