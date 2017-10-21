<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>数据分析</title>

    <%@include file="/WEB-INF/common/static.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx}/module-css/daterangepicker.css">

    <c:set var="PARENT_MENU_CODE" value="RptRepairOrder_Manage" />
    <c:set var="CHILD_MENU_CODE" value="DataAnalysisStatistics"/>
    <script>
        var ctx = '${ctx}';
    </script>
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
	    .sear_icon{ font-size: 18px; color: #428bca; vertical-align: middle; padding: 0 3px; cursor: pointer;margin-top: -10px}
	    #J_statisticsList tr td{ position: relative; white-space: nowrap}
	    #J_statisticsList tr td .span1{ padding:0 15px; font-size: 12px; line-height: 18px; position: absolute; left: 50%; background: #FFFFFF;
	        padding: 5px;z-index: 999; border-radius: 5px; border: 1px solid #e5e5e5; display: none}
	    #J_statisticsList{ width: 100%!important;}
	    #J_statisticsList tr th{ white-space: nowrap}
	</style>
</head>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
    <%@include file="/WEB-INF/common/top_logo_nav.jsp" %>
    <%@include file="/WEB-INF/common/slideBar.jsp" %>

    <!--右侧部分开始-->

    <div id="page-wrapper" class="wrapper wrapper-content cover_banner" style="height: 88%;overflow-y: auto; ">
        <div class="container top_con" style=" width: 100%;min-width: 1200px">
            <h6><span class="glyphicon glyphicon-folder-open work"></span>数据分析<i class="pull-right iconfont ">&#xe658;</i><div class="clearfix"></div></h6>
            <div class="co_all">
		        <form id="searchFrom" style="margin: 5px;">
		        	<div class="row">
						<div class="col-md-4" id="date1">
				            <h4 style="float: left;">时间区间一：</h4>
				            <input type="text" style="float: left; width: 60%" id="dateArea">
			          	</div>
			           
						<div class="col-md-1">
				            <h4 style="float: left;">对比：</h4>
				            <input type="checkbox" id="compare" style="zoom:150%;">
			          	</div>
			           
						<div class="col-md-4" id="date2" style="display: none;">
				            <h4 style="float: left;">时间区间二：</h4>
				            <input type="text" style="float: left; width: 60%" id="dateArea2">
			          	</div>
		          	</div>
		          	<div class="row">
			            <div class="col-md-2">
			                <h4 style="float: left;">门店：</h4>
			                <select name="shopId" id="shopId" class="from-control">
			                    <option value="">全部</option>
			                    <c:forEach items="${shopList}" var="shop">
			                        <option value="${shop.id}">${shop.name}</option>
			                    </c:forEach>
			                </select>
			            </div>
			            <div class="col-md-2">
			                <h4 style="float: left;">统计方式：</h4>
			                <select name="type" id="type" class="from-control">
			                    <option value="Day">天</option>
			                    <option value="Week">周</option>
			                    <option value="Month">月</option>
			                </select>
			            </div>
			            <div class="col-md-2">
			                <h4 style="float: left;">分类：</h4>
			                <select name="category" id="categoryId" class="from-control">
			                    <option value="">全部</option>
			                    <c:forEach items="${categoryList}" var="category">
			                        <option value="${category.id}">${category.name}</option>
			                    </c:forEach>
			
			                </select>
			            </div>
						<div class="col-md-2">
							<label>接单方式：</label>
							<select id="deliverType">
			                    <option value="">全部</option>
								<option value="客户上门">客户上门</option>
								<option value="快递">快递</option>
								<option value="上门取件">上门取件</option>
							</select>
						</div>
						<div class="col-md-2">
							<label>对比项：</label>
							<select id="compareType">
								<option value="0">成交金额</option>
								<option value="1">成交件数</option>
							</select>
						</div>
					</div>
		        </form>
		        <div class="clearfix"></div>
		        <br>
		        <div align="center">
		            <input type="submit" id="search" value="查询">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		        </div>
		    </div>
            <div class="col-md-12" id="box01">

            </div>
            
			<div class="clearfix"></div>
			<table id="J_statisticsList" class="table table-striped table-bordered table-hover" style="display: none;">
				<thead>
					<tr>
						<th>对比时间</th>
						<th>第一个时间对应值</th>
						<th>第二个时间对应值</th>
						<th>变化量</th>
						<th>变化率</th>
					</tr>
				</thead>
			</table>
        </div>
    </div>
</div>
</body>

<script src="${ctx}/static/require/require.js"></script>
<script src="${ctx}/static/require/require.config.js"></script>
<script src="${ctx}/module-js/rpt/data_analysis_statistics.js"></script>
</html>
