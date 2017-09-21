<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>CRM管理后台</title>

	<link href="${ctx}/module-css/consult-order.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="${ctx}/module-css/webuploader_style.css">
	<link href="${ctx}/static/select2/css/select2.css" rel="stylesheet">
	
	<link href="${ctx}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	
	
	<link href="${ctx}/static/font-awesome/font-awesome.min.css" rel="stylesheet">
	
	
	<link href="${ctx}/static/dataTables/css/dataTables.bootstrap.min.css" rel="stylesheet">
	
	<link href="${ctx}/static/animate/animate.css" rel="stylesheet">
	
	<link rel="stylesheet" href="${ctx}/static/ztree/css/yayaStyle/zTreeStyle.css" type="text/css">
	
	<link href="${ctx}/static/dateTimePicker/css/jquery.datetimepicker.css" rel="stylesheet">
	
	<link href="${ctx}/static/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" rel="stylesheet">
	
	<link rel="stylesheet" href="${ctx}/static/css/iconfont.css"/>
	
	<link href="${ctx}/static/webuploader/webuploader.css" rel="stylesheet">
	
	<link href="${ctx}/static/customer/customer.css" rel="stylesheet">

<c:set var="PARENT_MENU_CODE" value="ConsultOrder_Manage" />
<c:set var="CHILD_MENU_CODE" value="ConsultOrder_List" />

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
	float: right
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
<!-- datepicker  -->
.xdsoft_datetimepicker{
	z-index: 9999999999;
}
</style>

</head>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow: hidden">
	<%--<div id="wrapper">--%>

	<!--右侧部分开始-->
	<div id="page-wrapper" class="wrapper wrapper-content " style="height: 82%; overflow-y: auto; overflow-x: hidden">
		<div class="row animated fadeInRight" id="content-main">
			<div class="col-xs-12">
				<div class="ibox float-e-margins">
					<div id="content" class="ibox-content">
					<input id="phone" type="hidden" value="${phone}">
						<table id="J_orderList" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th></th>
									<th>咨询单号</th>
									<th>客户姓名</th>
									<th>客户电话</th>
									<th>微信号</th>
									<th>分类</th>
									<th>预约门店</th>
									<th>维修需求</th>
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
	</div>
	<script src="${ctx}/static/require/require.js"></script>
	<script src="${ctx}/static/require/require.config.js"></script>
	<script src="${ctx}/module-js/order/consult_order_all.js"></script>

</body>

</html>