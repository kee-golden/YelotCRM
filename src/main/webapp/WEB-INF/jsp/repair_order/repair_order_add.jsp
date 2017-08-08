<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/common/taglibs.jsp"%>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>CRM管理后台</title>

<%@include file="/WEB-INF/common/static.jsp"%>
<link href="${ctx}/module-css/repair-order.css" rel="stylesheet">

<c:set var="PARENT_MENU_CODE" value="RepairOrder_Manage" />
<c:set var="CHILD_MENU_CODE" value="RepairOrder_Add" />

<script>
	var ctx = '${ctx}';
    var attributesJson = eval('${attributesJson}');

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
					<div><h5>查询该客户是否已存在，不存在需要到客户管理中，创建一个客户</h5></div>

					<form class="form-inline bottom10" role="form">
						<div class="form-group">
							<input type="text" class="form-control" id="phone" name="phone" placeholder="请输入手机号"/>
							<input id="customerSearchBtn" type="button" class="form-control btn-group" value="查询"/>
						</div>
					</form>
					<hr/>

					<div id="customerContainer" class="col-md-12 b-r">

						<div class="row bottom10">
							<h3 class="m-t-none m-b">基础信息</h3>
							<input type="hidden" id="customerId" data-id=""/>
							<div class="col-md-2">
								<label><span style="color: red"></span>用户名</label>
								<input type="text" placeholder="请输入用户名" class="form-control" name="name" id="J_name"
									    autocomplete="off">
							</div>

							<div class="col-md-3">
								<label><span style="color: red"></span>手机号</label>
								<input type="text" placeholder="请输入手机号" class="form-control" name="phone" id="J_phone"
									   >
							</div>

							<div class="col-md-2">
								<label>省市</label>
								<div id="prov_city">
									<select class="prov" name="province" id="firstCategory"></select>
									<select class="city" disabled="disabled" name="city" id="secondCategory"></select>
								</div>

							</div>
							<div class="col-md-5">
								<label>详细地址</label>
								<input type="text" placeholder="请输入详细地址" class="form-control" name="address" id="J_address"
									   value="${bean.address}">

							</div>
						</div>

					</div>

					<div id="customerTip">
						<div><h5>该客户不存在，创建一个客户，请点击<a href="${ctx}/customer/index">创建客户</a></h5></div>
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
						<%--<h3 class="m-t-none m-b">基础信息</h3>--%>

						<div id="category" class="row">
							<label class="col-xs-1">分类:</label>
							<select class="prov col-xs-1" name="province"></select>
							<select class="city col-xs-1" disabled="disabled" name="city"></select>
							<label class="col-xs-1">品牌：</label>
							<span>
							<select id="brand" name="brand">
								<%--<option value="A">A</option>--%>
								<%--<option value="B">B</option>--%>
								<%--<option value="C">C</option>--%>
								<c:forEach items="${brandList}" var="item">
									<option value="${item.name}">${item.name}</option>
								</c:forEach>
							</select>
						</span>
						</div>
						<div id="attributes">

						</div>
					</div>

				</div>
			</div>
			<div class="container top_con" style="width: 100%; min-width: 1000px">
				<h6>
					<span class="glyphicon glyphicon-folder-open work"></span>图片详情<i class="pull-right iconfont ">&#xe658;</i>
					<div class="clearfix"></div>
				</h6>
				<div class="row">

					<div class="col-lg-6  col-md-12"></div>
					<div class="col-lg-6 col-md-12"></div>
				</div>
			</div>
			<div class="container top_con" style="width: 100%; min-width: 1000px">
				<h6>
					<span class="glyphicon glyphicon-folder-open work"></span>维修详情<i class="pull-right iconfont ">&#xe658;</i>
					<div class="clearfix"></div>
				</h6>
				<div class="row">
					<form id="J_customerForm" role="form" class="form-inline">
						<table id="customer_table">
							<tr>
								<td class="customer_table_td_lable4"><label for="username" class="form-label"><span class="titl">维修内容:</span></label></td>
								<td class="customer_table_td_input4"><input type="text" class="form-control" name="username" id="username" autocomplete="off"></td>
								<td class="customer_table_td_lable4"><label for="username" class="form-label"><span class="titl">工期时间:</span></label></td>
								<td class="customer_table_td_input4"><input type="text" class="form-control" name="username" id="username" autocomplete="off" style="width: 40%">&nbsp;至&nbsp;<input type="text" class="form-control" name="username" id="username" autocomplete="off" style="width: 40%"></td>
							</tr>
							<tr>
								<td class="customer_table_td_lable4"><label for="username" class="form-label"><span class="titl">维修预付款:</span></label></td>
								<td class="customer_table_td_input4"><input type="text" class="form-control" name="username" id="username" autocomplete="off"></td>
								<td class="customer_table_td_lable4"><label for="username" class="form-label"><span class="titl">维修总费用:</span></label></td>
								<td class="customer_table_td_input4"><input type="text" class="form-control" name="username" id="username" autocomplete="off"></td>
							</tr>
							<tr>
								<td class="customer_table_td_lable4"></td>
								<td colspan="4" style="text-align: right;">
									<input type="button" class="form-button" value="保存">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" class="form-button" value="重置">
								</td>
								<%--<td class="customer_table_td_input4"></td>--%>
							</tr>
						</table>
					</form>
					<div class="col-lg-6  col-md-12"></div>
					<div class="col-lg-6 col-md-12"></div>
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

<script>
	<%--var attrbutesJson = eval(${attributesJson});--%>
//	console.log("attrbutes:"+JSON.stringify(attrbutesJson));
	var jsonObj = eval(${categoryJson});//转化为json 对象
	 $("#category").citySelect({
        url:jsonObj,
		prov:'${firstCategory}',
        city:'${secondCategory}',
        nodata:"none"
    });

</script>
</html>
