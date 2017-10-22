<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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

		<div id="page-wrapper" class="wrapper wrapper-content cover_banner " style="height: 88%; overflow-y: auto;">
			<div class="container top_con" style="width: 100%; min-width: 1000px">
				<h6>
					<span class="glyphicon glyphicon-briefcase work "></span>客户信息<i class="pull-right iconfont ">&#xe658;</i>
					<div class="clearfix"></div>
				</h6>
				<div class="row top_row col-md-12 b-r">
					<div>
						<h4>选择客户</h4>
					</div>

					<form class="form-inline bottom10" role="form">
						<div class="form-group">
							<input type="text" class="form-control" id="phone" name="phone" placeholder="请输入手机号" /> <input id="customerSearchBtn" type="button"
								class="form-control btn-group" value="查询" /><input id="linkToConsultOrder" type="button"
								class="form-control btn-group" value="关联咨询单" />
							&nbsp;&nbsp;&nbsp;
							<label>咨询单号：</label><input type="text" id="consultOrderNo" class="form-control" style="width: 250px" disabled="disabled"/>
						</div>
					</form>
					<hr/>

					<div id="customerContainer" class="col-md-12 b-r">
						<div class="row bottom10">
							<h3 class="m-t-none m-b">基础信息</h3>
							<input type="hidden" id="customerId" />
							<input type="hidden" id="consultOrderId" />
							<div class="row bottom10">
								<div class="col-md-2">
									<label><span style="color: red"></span>用户名</label> <input type="text" placeholder="请输入用户名" class="form-control" name="name" id="J_name"
										autocomplete="off" readonly>
								</div>
	
								<div class="col-md-2">
									<label><span style="color: red"></span>手机号</label> <input type="text" placeholder="请输入手机号" class="form-control" name="phone" id="J_phone"
										readonly>
								</div>
	
								<div class="col-md-4">
									<label>其他联系方式</label><input type="text" placeholder="请输入其他联系方式" class="form-control" name="otherPhone" id="J_otherPhone">
								</div>
	
								<div class="col-md-4">
									<label>客户来源</label>
									<select class="form-control" id="channelSource" name="channelSource">
		                                <option value="1">udesk</option>
		                                <option value="2">北京7860</option>
		                                <option value="3">上海5588</option>
		                                <option value="4">总机400</option>
		                                <option value="5">杭州3123</option>
		                                <option value="6">上门</option>
		                                <option value="7">微博</option>
		                                <option value="8">微信</option>
		                                <option value="9">淘宝C店</option>
		                                <option value="10">淘宝B店</option>
		                                <option value="11">大众点评</option>
		                                <option value="12">老客介绍</option>
		                                <option value="13">品牌介绍</option>
		                                <option value="14">员工介绍</option>
		                                <option value="15">老板介绍</option>
		                                <option value="16">官网留言</option>
		                                <option value="17">论坛、博客</option>
		                                <option value="18">其他</option>
	                                </select>
								</div>
							</div>
							
							<div class="row">
								<div class="col-md-2">
									<label>省市</label>
									<div id="provCity">
										<!-- <label id="province"></label>/<label id="city"></label> -->
										<select class="prov" name="province" id="J_province"></select>
										<select class="city" disabled="disabled" name="city" id="J_city"></select>
									</div>
								</div>
								
								<div class="col-md-6">
									<label>详细地址</label> <input type="text" placeholder="请输入详细地址" class="form-control" name="address" id="J_address" value="${bean.address}">
								</div>
	
								<div class="col-md-1">
									<input type="button" class="btn btn-success" style="width: 120px;margin-top: 10px;" id="saveCustomerBtn" value="保存" />
								</div>
							</div>
						</div>

					</div>

					<div id="customerTip">
						<div>
							<h5>
								<%--该客户不存在，创建一个客户，请点击<a href="${ctx}/customer/index">创建客户</a>--%>
								该客户不存在，创建一个客户，请点击<a href="javascript:;;" id="addCustomer">创建客户</a>
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

						<div id="category" class="row bottom10 left20">
							<label class="left20">分类:</label>
							<select class="prov" name="firstCategory" id="firstCategory"></select>
							<select class="city" disabled="disabled" name="secondCategory" id="secondCategory"></select>
							<label class="left20">品牌：</label> <span>
								<select id="brandId" name="brandId">
									<c:forEach items="${brandList}" var="item">
										<option value="${item.id}">${item.name}</option>
									</c:forEach>
							</select>
							</span>
						</div>
						<div id="attributes" class="bottom10"></div>

						<hr/>
						<label class="left20">服务项:</label> <select id="serviceItem" name="serviceItem" multiple class="left20 col-md-6">
						</select>
						<br>
						<br>
						<label class="left20">关联件单号:</label> <select id="refOrderIds" name="serviceItem" multiple class="left20 col-md-6">
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
					<div id="image-desc" class="row col-md-9 left20">
						<label class="">外观描述:</label> <input type="text" id="imageDesc" class="form-control bottom10" placeholder="" />

					</div>
				</div>
			</div>

			<div class="container top_con" style="width: 100%; min-width: 1000px">
				<h6>
					<span class="glyphicon glyphicon-folder-open work"></span>维修详情<i class="pull-right iconfont ">&#xe658;</i>
					<div class="clearfix"></div>
				</h6>
				<div class="row">

					<div id="repairContent" class="row">
						<div class="row bottom10 left20 col-md-9">
							<label class="">维修内容:</label> <input type="text" class="form-control" id="repairDesc">
						</div>
					</div>
					<div class="row bottom10 left20">
						<div class="col-md-1">
							<label>类别:</label><br/>
							<select name="typeName" id="typeName" >
								<option value="维修单">维修单</option>
								<option value="内部单">内部单</option>
								<%--<option value="返修单">返修单</option> 不是创建的时候产生--%>
								<option value="评估单">评估单</option>
							</select>
						</div>
						<div class="col-md-2">
							<label>预付款:</label> <input type="number"  class="form-control" id="advancePayment">
						</div>
						<div class="col-md-2">
							<label>工费：</label>
							<select id="labourPaymentFlag">
								<option value="0">待定</option>
								<option value="1" selected="selected">确定</option>
							</select> 
							<input type="number" class="form-control" id="labourPayment">
						</div>
						<div class="col-md-2">
							<label>材料费：</label>
							<select id="materialPaymentFlag">
								<option value="0">待定</option>
								<option value="1" selected="selected">确定</option>
							</select> 
							 <input type="number" class="form-control" id="materialPayment">
						</div>
						<div class="col-md-2">
							<label>优惠金额：</label>
							<select id="discountAmountPaymentFlag">
								<option value="0">待定</option>
								<option value="1" selected="selected">确定</option>
							</select>
							<input type="number" class="form-control" id="discountAmountPayment">
						</div>
						<div class="col-md-2"><label>预计完成时间：</label> <input type="text" class="form-control" id="pickupDate"></div>
					</div>
					
					<div class="row">
						<div class="row bottom10 left20 col-md-9">
							<label class="">优惠备注:</label> <input type="text" class="form-control" id="discountDesc">
						</div>
					</div>
				</div>

			</div>
			<div class="container top_con" style="width:100%;min-width: 1000px;">
			<div class="bottom10" style="float:right">
				<input type="button" class="btn btn-success" style="width: 120px;margin-top: 10px;" id="saveBtn" value="保存" />
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

<script src="${ctx}/module-js/order/repair_order_add.js?random=Math.random()"></script>
<script src="${ctx}/module-js/order/webuploader_app.js"></script>

<script>
    require(['jquery','yaya','cityselect','dateTimePicker'], function ($, yaya,cityselect) {

        var jsonObj = eval(${categoryJson});//转化为json 对象
        $("#category").citySelect({
            url:jsonObj,
            prov:'${firstCategory}',
            city:'${secondCategory}',
            nodata:"none"
        });

        $('#pickupDate').datetimepicker({
            lang: 'ch',
            format: 'Y-m-d',
            timepicker:false
        });
    });
</script>
</html>
