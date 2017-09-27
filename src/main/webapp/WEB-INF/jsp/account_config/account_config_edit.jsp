<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/common/taglibs.jsp"%>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<title>CRM管理后台</title>

	<%@include file="/WEB-INF/common/static.jsp"%>
	<link href="${ctx}/module-css/consult-order.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="${ctx}/module-css//webuploader.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/module-css/webuploader_style.css">

	<c:set var="PARENT_MENU_CODE" value="WeiXin_Manage" />
	<c:set var="CHILD_MENU_CODE" value="AccountConfigManage" />

	<script>
		var ctx = '${ctx}';
	</script>
	
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow: hidden">
	<div id="wrapper">
		<%@include file="/WEB-INF/common/top_logo_nav.jsp"%>
		<%@include file="/WEB-INF/common/slideBar.jsp"%>

		<!--右侧部分开始-->

		<div id="page-wrapper" class="wrapper wrapper-content cover_banner " style="height: 88%; overflow-y: auto;">
			<div class="container top_con" style="width: 100%; min-width: 1000px">

			</div>
			<div class="container top_con" style="width: 100%; min-width: 1000px">
				<h6>
					<span class="glyphicon glyphicon-folder-open work"></span>配置信息<i class="pull-right iconfont ">&#xe658;</i>
					<div class="clearfix"></div>
				</h6>
				<div id="customerContainer" class="col-md-12 b-r">
					<div class="row bottom10">
							<input type="hidden" id="customerId" data-id="" />
							<div class="col-md-2">
								<label><span style="color: red"></span>积分规则</label> <input type="number" placeholder="多少元一个积分" class="form-control" name="customerName" id="customerName"
																						  autocomplete="off" >
							</div>

							<div class="col-md-2">
								<label><span style="color: red"></span>手机号</label> <input type="text" placeholder="请输入手机号" class="form-control" name="customerPhone" id="customerPhone">
							</div>
						<div class="col-md-2">
							<label><span style="color: red"></span>手机号</label> <input type="text" placeholder="请输入手机号" class="form-control" name="customerPhone" id="customerPhone">
						</div>
						<div class="col-md-2">
							<label><span style="color: red"></span>手机号</label> <input type="text" placeholder="请输入手机号" class="form-control" name="customerPhone" id="customerPhone">
						</div>




							<div class="col-md-4">
								<label>详细地址</label> <input type="text" placeholder="请输入详细地址" class="form-control" name="customerAddress" id="customerAddress" value=""
														   >
							</div>
						</div>
					<div class="row bottom10">
						<div class="col-md-2">
							<label>微信号</label>
							<input type="text" name="wechatNo" id="wechatNo" placeholder="微信ID号" class="form-control">
						</div>
						<div class="col-md-2">
							<label>微信昵称</label>
							<input type="text" name="wechatNickname" id="wechatNickname" placeholder="微信昵称" class="form-control">
						</div>

					</div>
				</div>

			</div>

			<div class="container top_con" style="width: 100%; min-width: 1000px">
				<div class="col-md-2 bottom10">
					<input type="button" class="form-control col-md-2 btn" id="saveBtn" value="保存" />
				</div>
			</div>
		</div>
	</div>
	<div style="margin-top: -30px">
		<%@include file="/WEB-INF/common/bottom.jsp"%>
	</div>
</body>
<script src="${ctx}/static/require/require.js"></script>
<script src="${ctx}/static/require/require.config.js"></script>
<script src="${ctx}/module-js/order/webuploader_app.js"></script>

<script>
    require(['jquery','yaya','cityselect','dateTimePicker'], function ($, yaya,cityselect) {


        $("#saveBtn").click(function () {


        });

	});



</script>


</html>
