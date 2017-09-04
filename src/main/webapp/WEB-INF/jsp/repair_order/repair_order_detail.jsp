<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html lang="zh-cn">
<head>
</head>
<body>
<div id="a">
	<div id="titleDiv">
		<div class="col-xs-8" style="line-height: 60px;">
			<div class="pull-left" style="line-height: 60px;">
				<img src="${ctx}/img/logo.png">
			</div>
			<h4 style="float: left; margin-left: 20px; line-height: 20px;">
				御金匠皮具维修单登记表<br> 
				地址：上海市静安区铜仁路258号九安广场金座8D<br> 
				电话：4009611966&nbsp;&nbsp;&nbsp;021-62895588
			</h4>
		</div>
		<div class="col-xs-4" style="line-height: 60px;">
			<h4 style="float: right; margin-right:0px; line-height: 20px;">
				维修单号：${repairOrder.orderNo}<br> 
				开单日期：<fmt:formatDate value="${repairOrder.createAt}" pattern="yyyy-MM-dd HH:mm:ss" /><br> 
				经手人：${repairOrder.createUserName}
			</h4>
		</div>
	</div>
	<hr color="black" width="100%" style="height: 1px;margin: 0px">
	<div id="customerDiv" class="col-xs-4" style="width: 100%">
		<h3 style="float: left">客户信息</h3><br>
		<table id="customerTable" style="width: 100%">
			<tr>
				<td width="25%"><h4 style="float: right">客户姓名：</h4></td>
				<td width="25%"><h4 style="float: left">${repairOrder.customerName}</h4></td>
				<td width="25%"><h4 style="float: right">联系方式：</h4></td>
				<td width="25%"><h4 style="float: left">${repairOrder.customerPhone}</h4></td>
			</tr>
			<tr style="width: 25%">
				<td><h4 style="float: right">其他联系方式：</h4></td>
				<td><h4 style="float: left">${repairOrder.customerPhoneSecond}</h4></td>
				<td><h4 style="float: right">回寄地址：</h4></td>
				<td><h4 style="float: left">${repairOrder.customerAddress}</h4></td>
			</tr>
		</table>
	</div>
	<hr color="black" width="100%" style="height: 1px;margin: 0px">
	<div id="productDiv" class="col-xs-4" style="width: 100%">
		<h3 style="float: left">产品信息</h3><br>
		<table id="productTable" style="width: 100%">
			<tr>
				<td width="12%"><h4 style="float: right">分类：</h4></td>
				<td width="12%"><h4 style="float: left">${repairOrder.firstCategoryName}</h4></td>
				<td width="12%"><h4 style="float: right">类型：</h4></td>
				<td width="12%"><h4 style="float: left">${repairOrder.secondCategoryName}</h4></td>
				<td width="12%"><h4 style="float: right">品牌：</h4></td>
				<td width="12%"><h4 style="float: left">${repairOrder.brandName}</h4></td>
				<td width="12%"></td>
				<td width="12%"></td>
			</tr>
			<c:forEach items="${repairOrder.productInfoList}" var="item">
				<c:if test="${item.id%4==1}"><tr></c:if>
					<td width="12%"><h4 style="float: right">${item.attributeName}：</h4></td>
					<td width="12%"><h4 style="float: left">${item.selectionValues}</h4></td>
				<c:if test="${item.id%4==0}"></tr></c:if>
			</c:forEach>
		</table>
	</div>
	<%-- <div class="ibox-content">
		<div class="row">
			<form role="form" id="J_userForm">
				<input type="hidden" name="id" value="">


			</form>

			<table id="J_orderOperatorsList" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th class="col-md-2">订单号</th>
						<th class="col-md-2">操作人姓名</th>
						<th>操作备注</th>
						<th>操作方式</th>
						<th>创建时间</th>
					</tr>

				</thead>
				<tbody>
					<c:forEach items="${repairOrder}" var="item">
						<tr>
							<th>${item.orderNo}</th>
							<th>${item.approveUserName}</th>
							<th>${item.operator_comment}</th>
							<th><c:if test="${item.operator_status == 1}">
								提交
							</c:if> <c:if test="${item.operator_status == 2}">
								审核通过
							</c:if> <c:if test="${item.operator_status == 3}">
								审核拒绝
							</c:if></th>

							<th><fmt:formatDate value="${item.createAt}" pattern="yyyy-MM-dd HH:mm:ss" /></th>


						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div> --%>
</div>
</body>
</html>

<%-- <%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/common/taglibs.jsp"%>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<title>CRM管理后台</title>

	<%@include file="/WEB-INF/common/static.jsp"%>
	<link href="${ctx}/module-css/repair-order.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="${ctx}/module-css//webuploader.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/module-css/webuploader_style.css">

	<c:set var="PARENT_MENU_CODE" value="RepairOrder_Manage" />
	<c:set var="CHILD_MENU_CODE" value="RepairOrder_Add" />

	<script>
		var ctx = '${ctx}';
	    var attributesJson = eval('${attributesJson}');
	    var categoryServiceJson = eval('${categoryServiceJson}');
	</script>
	
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow: hidden">
	<div id="wrapper">
		<%@include file="/WEB-INF/common/top_logo_nav.jsp"%>
		<%@include file="/WEB-INF/common/slideBar.jsp"%>

		<!--右侧部分开始-->


		<div id="page-wrapper" class="wrapper wrapper-content cover_banner " style="height: 82%; overflow-y: auto;">
			<div class="container top_con" style="width: 100%; min-width: 1000px">
				<h6>
					<span class="glyphicon glyphicon-briefcase work "></span>客户信息<i class="pull-right iconfont ">&#xe658;</i>
					<div class="clearfix"></div>
				</h6>
				<div class="row top_row col-md-12 b-r">
					<div>
						<h5>查询该客户是否已存在，不存在需要到客户管理中，创建一个客户</h5>
					</div>

					<form class="form-inline bottom10" role="form">
						<div class="form-group">
							<input type="text" class="form-control" id="phone" name="phone" placeholder="请输入手机号" /> <input id="customerSearchBtn" type="button"
								class="form-control btn-group" value="查询" />
						</div>
					</form>
					<hr />

					<div id="customerContainer" class="col-md-12 b-r">
						<div class="row bottom10">
							<h3 class="m-t-none m-b">基础信息</h3>
							<input type="hidden" id="customerId" data-id="" />
							<div class="col-md-2">
								<label><span style="color: red"></span>用户名</label> <input type="text" placeholder="请输入用户名" class="form-control" name="name" id="J_name"
									autocomplete="off" readonly>
							</div>

							<div class="col-md-3">
								<label><span style="color: red"></span>手机号</label> <input type="text" placeholder="请输入手机号" class="form-control" name="phone" id="J_phone"
									readonly>
							</div>

							<div class="col-md-2">
								<label>省市</label>
								<div id="prov_city">
									<select class="prov" name="province" readonly=""></select> <select class="city" disabled="disabled" name="city" readonly=""></select>
								</div>

							</div>
							<div class="col-md-5">
								<label>详细地址</label> <input type="text" placeholder="请输入详细地址" class="form-control" name="address" id="J_address" value="${bean.address}"
									readonly>

							</div>
						</div>

					</div>

					<div id="customerTip">
						<div>
							<h5>
								该客户不存在，创建一个客户，请点击<a href="${ctx}/customer/index">创建客户</a>
							</h5>
						</div>
					</div>

				</div>
			</div>
			<div class="container top_con" style="width: 100%; min-width: 1000px">
				<h6>
					<span class="glyphicon glyphicon-folder-open work"></span>产品信息<i class="pull-right iconfont ">&#xe658;</i>
					<div class="clearfix"></div>
				</h6>
				<div class="row">
					<div>
						<h3 class="m-t-none m-b">基础信息</h3>

						<div id="category" class="row bottom10">
							<label class="col-xs-1">分类:</label>
							<select class="prov col-xs-1" name="firstCategory" id="firstCategory"></select>
							<select class="city col-xs-1" disabled="disabled" name="secondCategory" id="secondCategory"></select>
							<label class="col-xs-1">品牌：</label> <span>
								<select id="brand" name="brand">

									<c:forEach items="${brandList}" var="item">
										<option value="${item.id}">${item.name}</option>
									</c:forEach>
							</select>
							</span>
						</div>
						<div id="attributes" class="bottom10"></div>
						<!-- <table style="width: 100%" border="1">
							<tr style="height: 20px">
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</table> -->
						<hr />
						<label>服务项:</label> <select id="serviceItem" name="serviceItem" multiple class="col-md-6">
							<c:forEach items="${categoryServiceItemList}" var="item">
							<option value="${item.serviceItemId}">${item.serviceName}</option>
							</c:forEach>

						</select>
					</div>

				</div>
			</div>
			<div class="container top_con" style="width: 100%; min-width: 1000px">
				<h6>
					<span class="glyphicon glyphicon-folder-open work"></span>图片详情<i class="pull-right iconfont ">&#xe658;</i>
					<div class="clearfix"></div>
				</h6>
				<div class="row">
					<div id="uploader">
						<div class="queueList">
							<div id="dndArea" class="placeholder">
								<div id="filePicker"></div>
								<p>或将照片拖到这里，单次最多可选20张</p>
							</div>
						</div>
						<div class="statusBar" style="display: none;">
							<div class="progress">
								<span class="text">0%</span> <span class="percentage"></span>
							</div>
							<div class="info"></div>
							<div class="btns">
								<div id="filePicker2"></div>
								<div class="uploadBtn">开始上传</div>
							</div>
						</div>
					</div>
					<div id="image-desc">
						<label class="col-md-2">外观描述:</label> <input type="text" id="imageDesc" class="col-md-6 bottom10" placeholder="" />

					</div>
				</div>
			</div>

			<div class="container top_con" style="width: 100%; min-width: 1000px">
				<h6>
					<span class="glyphicon glyphicon-folder-open work"></span>维修详情<i class="pull-right iconfont ">&#xe658;</i>
					<div class="clearfix"></div>
				</h6>
				<div class="row">

					<div id="repairContent">
						<div class="row bottom10">
							<label class="col-md-2">维修内容:</label> <input type="text" class="col-md-8" id="repairDesc">
						</div>
						<div class="row bottom10">
							<label class="col-md-2">预付款:</label> <input type="text" class="col-md-2" name="pre-flee"> <label class="col-md-2">交货时间:</label> <input
								type="text" class="col-md-2" id="pickupDate">
						</div>


					</div>
					<div class="col-lg-6  col-md-12"></div>
					<div class="col-lg-6 col-md-12"></div>
				</div>
				<div class="col-md-2 bottom10">
					<input type="button" class="form-control col-md-2 " id="saveBtn" value="保存" />
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
<script src="${ctx}/static/jquery/jquery-2.2.4.min.js"></script>


<script type="text/javascript" src="${ctx}/static/cityselect/js/jquery.cityselect.js"></script>
<script src="${ctx}/module-js/order/repair_order_add.js"></script>
<script src="${ctx}/module-js/order/webuploader_app.js"></script>

<script>
    require(['jquery','yaya','selector2','cityselect','dateTimePicker'], function ($, yaya,selector2,cityselect) {

        var jsonObj = eval(${categoryJson});//转化为json 对象
        $("#category").citySelect({
            url:jsonObj,
            prov:'${firstCategory}',
            city:'${secondCategory}',
            nodata:"none"
        });
    });



</script>


</html>
 --%>