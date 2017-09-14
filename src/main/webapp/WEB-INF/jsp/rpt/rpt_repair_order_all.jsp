<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>CRM管理后台</title>

<%@include file="/WEB-INF/common/static.jsp"%>

<c:set var="PARENT_MENU_CODE" value="RptRepairOrder_Manage" />
<c:set var="CHILD_MENU_CODE" value="RptRepairOrder_AllList" />

<script>
	var ctx = '${ctx}';
	var adminId = '${sessionScope.user.id}';
</script>

<style>
#content-main {
	position: relative
}

.xbb {
	margin-right: 2px
}

#search_Big {
	background: #FFFFFF;
	margin: 10px 0;
	padding: 8px 0px 0px 0px;
	font-weight: bold;
	margin-bottom: 5px
}

#search_Big ul, #search_Big ul li {
	padding: 0;
	margin: 0
}

#search_Big ul li {
	float: left;
	margin-right: 15px
}

#search_Big ul li span {
	font-size: 13px;
	font-family: 微软雅黑;
	font-weight: bold;
	margin-right: 15px;
	margin-left: 10px;
	vertical-align: middle;
	display: inline-block;
	width: 65px;
	text-align: right;
	float: left;
	line-height: 24px
}

#search_Big  input {
	border: 1px solid #e5e5e5;
	height: 24px;
	padding-left: 3px
}

.sear {
	line-height: 26px;
	font-size: 13px;
	margin-right: 15px
}

.line {
	width: 100%;
	margin-top: 10px;
	border: 1px solid #efefef
}

.lrx {
	display: none
}

.mwdth tr {
	border: 1px solid #FFFFFF !important;
}

.inpt_width {
	width: 165px;
	border: 1px solid #e5e5e5;
	height: 24px;
	font-weight: normal
}

#cont {
	margin-top: 10px;
	padding-bottom: 5px
}

#cont ul {
	margin-bottom: 10px
}

#cont ul li span {
	float: left
}

#cont select {
	float: left;
	padding: 0
}

#otherTab select {
	float: left;
	padding: 0
}

#otherTab ul {
	margin-bottom: 10px
}

#otherTab ul li span {
	float: left
}

.search_right {
	margin: 5px 15px 0 0
}

#search_Big {
	box-shadow: 0 1px 5px 0 #e5e5e5;
}

#searchFrom {
	margin-left: 15px;
}

.ibox {
	padding: 0
}

.sear_icon {
	font-size: 18px;
	color: #428bca;
	vertical-align: middle;
	padding: 0 3px;
	cursor: pointer;
	margin-top: -10px
}

#J_orderList tr td {
	position: relative;
	white-space: nowrap
}

#J_orderList tr td .span1 {
	padding: 0 15px;
	font-size: 12px;
	line-height: 18px;
	position: absolute;
	left: 50%;
	background: #FFFFFF;
	padding: 5px;
	z-index: 999;
	border-radius: 5px;
	border: 1px solid #e5e5e5;
	display: none
}

#J_orderList {
	width: 100% !important;
}

#J_orderList tr th {
	white-space: nowrap
}
</style>

</head>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow: hidden">
	<%--<div id="wrapper">--%>
	<%@include file="/WEB-INF/common/top_logo_nav.jsp"%>
	<%@include file="/WEB-INF/common/slideBar.jsp"%>

	<!--右侧部分开始-->
	<div id="page-wrapper" class="wrapper wrapper-content " style="height: 82%; overflow-y: auto; overflow-x: hidden">
		<div class="row animated fadeInRight" id="content-main">
			<div class="col-xs-12">

				<div class="ibox float-e-margins">
					<ul class="nav nav-tabs" id="myTab">
						<li class="active"><a>全部订单列表</a></li>
					</ul>

					<div id="content" class="ibox-content">
						<div class="co_all">
							<form id="searchFrom" style="margin: 5px;">
								<div class="col-md-4">
									<h4 style="float: left;">开始时间：</h4>
									<input type="text" style="float: left;" id="startDate" class="pickupDate">
								</div>
								<div class="col-md-4">
									<h4 style="float: left;">结束时间：</h4>
									<input type="text" style="float: left;" id="endDate" class="pickupDate">
								</div>
								<div id="category">
									<h4 style="float: left;">产品分类：</h4>
									<select class="prov col-xs-1" name="firstCategory" id="firstCategory"></select> <select class="city col-xs-1" disabled="disabled"
										name="secondCategory" id="secondCategory"></select>
								</div>
								<div class="col-md-4">
									<h4 style="float: left;">门店名称：</h4>
									<select name="shopId" id="shopId" class="from-control">
										<c:forEach items="${shopList}" var="shop">
											<option value="${shop.id}">${shop.name}</option>
										</c:forEach>
										<option value="">不限</option>
									</select>
								</div>
								<div class="col-md-4">
									<h4 style="float: left;">订单状态：</h4>
									<input type="text" style="float: left;" id="status" disabled="disabled" placeholder="暂未开放">
								</div>
								<div class="col-md-4">
									<h4 style="float: left;">订单类型：</h4>
									<input type="text" style="float: left;" id="typeName" disabled="disabled" placeholder="暂未开放">
								</div>
							</form>
							<div class="clearfix"></div>
							<br>
							<div align="center">
								<input type="submit" id="search" value="查询">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
								<input type="submit" id="exportExcel" value="EXCEL导出">
							</div>
						</div>
						<div class="clearfix"></div>

						<table id="J_orderList" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>订单号</th>
									<th>客户姓名</th>
									<th>客户电话</th>
									<th>产品分类</th>
									<th>类型</th>
									<th>门店</th>
									<th>服务项</th>
									<th>当前状态</th>
									<th>创建者</th>
									<th>创建时间</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>

			<!--右侧部分结束-->

		</div>

		<div style="margin-top: 10px;">
			<%@include file="/WEB-INF/common/bottom.jsp"%>
		</div>
	</div>
</body>

<script src="${ctx}/static/require/require.js"></script>
<script src="${ctx}/static/require/require.config.js"></script>
<script src="${ctx}/module-js/rpt/rpt_repair_order_all.js"></script>
<script>
	    require(['jquery','yaya','cityselect','dateTimePicker'], function ($, yaya,cityselect) {
	
	        var jsonObj = eval(${categoryJson});//转化为json 对象
	        $("#category").citySelect({
	            url:jsonObj,
	            prov:'${firstCategory}',
	            city:'${secondCategory}',
	            nodata:"none"
	        });
	
	        $('.pickupDate').datetimepicker({
	            lang: 'ch',
	            format: 'Y-m-d',
	            timepicker:false
	        });
	    });
	</script>

</html>