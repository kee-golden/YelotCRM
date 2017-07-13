<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/common/taglibs.jsp"%>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>ITSS管理后台</title>

<%@include file="/WEB-INF/common/static.jsp"%>

<c:set var="PARENT_MENU_CODE" value="RepairOrder_Manage" />
<c:set var="CHILD_MENU_CODE" value="RepairOrder_Add" />

<script>
	var ctx = '${ctx}';
</script>


</head>
<style>
.btn-default {
	color: #333;
	background-color: #fff;
	border-color: #ccc;
}

.top_row {
	padding: 15px;
}

.top_con {
	box-shadow: 0 1px 5px 0 #e5e5e5;
	background: #FFFFFF;
}

.tab-content {
	background: #FFFFFF
}

#myTab {
	position: relative;
}

#myTab li {
	margin-right: 20px
}

#myTab span {
	display: inline-block;
	font-size: 11px;
	color: #FFFFFF;
	background: #c46f82;
	text-align: center;
	border-radius: 50%;
	width: 23px;
	height: 23px;
	line-height: 23px;
	position: absolute;
	right: -12px;
	top: -12px;
}

.top_con h6 {
	height: 28px;
	background: #f1efef;
	font-size: 14px;
	line-height: 28px;
	font-weight: 600;
	padding-left: 10px;
	margin-bottom: 20px
}

.work {
	margin-right: 10px;
}

#box01, #box02 {
	height: 350px;
	padding-top: 10px
}

#box02 {
	padding-left: 50px;
}

.iconfont {
	margin-right: 10px;
	font-size: 24px;
	cursor: pointer
}

.container h6 {
	overflow: hidden
}

#page-wrapper, .wrapper, .wrapper-content {
	padding: 0 !important;
}

#customer_table{
	border-collapse:separate; 
	border-spacing:5px;
}

.customer_table_td_lable4{
	width: 20%;
    text-align:right;
}

.customer_table_td_input4{
	width: 20%;
    text-align:left;
}

.customer_table_td_lable8{
	width: 8%;
    text-align:right;
}

.customer_table_td_input8{
	width: 8%;
    text-align:left;
}

.customer_table_td_input5{
	width: 20%;
    text-align:center;
}
</style>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow: hidden">
	<div id="wrapper">
		<%@include file="/WEB-INF/common/top_logo_nav.jsp"%>
		<%@include file="/WEB-INF/common/slideBar.jsp"%>

		<!--右侧部分开始-->


		<div id="page-wrapper" class="wrapper wrapper-content cover_banner " style="height: 82%; overflow-y: auto;">
			<div class="container top_con" style="width: 100%; min-width: 1000px">
				<h6>
					<span class="glyphicon glyphicon-briefcase work "></span>顾客信息<i class="pull-right iconfont ">&#xe658;</i>
					<div class="clearfix"></div>
				</h6>
				<div class="row top_row">
					<%-- tab组件--%>
					<%-- <ul class="nav nav-tabs" id="myTab">
						<c:if test="${power!=1}">
							<li class="active"><a sta="1">待处理 <span id="countAccepted">${countAccepted}</span></a></li>
						</c:if>
						<c:if test="${stausList=='2'}">
							<li><a sta="2">待审批<span id="count">${count}</span></a></li>
						</c:if>
						<li><a sta="3">我的请求<span id="countRequest">${countRequest}</span></a></li>
						<c:if test="${stausList1=='11'}">
							<li><a sta="4">待发布<span id="total">${total}</span></a></li>
						</c:if>
					</ul>
					<div class="clearfix"></div> --%>

					<%--tab页面内容--%>
					<!-- <div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="content"></div>
					</div> -->

					<form id="J_customerForm" role="form" class="form-inline">
						<table id="customer_table">
							<tr>
								<td class="customer_table_td_lable4"><label for="username" class="form-label"><span class="titl">顾客姓名:</span></label></td>
								<td class="customer_table_td_input4"><input type="text" class="form-control" name="username" id="username" placeholder="请输入顾客姓名" autocomplete="off"></td>
								<td class="customer_table_td_lable4"><label for="username" class="form-label"><span class="titl">联系方式:</span></label></td>
								<td class="customer_table_td_input4"><input type="text" class="form-control" name="username" id="username" placeholder="请输入联系方式" autocomplete="off"></td>
							</tr>
							<tr>
								<td class="customer_table_td_lable4"><label for="username" class="form-label"><span class="titl">其他联系方式:</span></label></td>
								<td class="customer_table_td_input4"><input type="text" class="form-control" name="username" id="username" placeholder="请输入其他联系方式" autocomplete="off"></td>
								<td class="customer_table_td_lable4"><label for="username" class="form-label"><span class="titl">回寄地址:</span></label></td>
								<td class="customer_table_td_input4"><input type="text" class="form-control" name="username" id="username" placeholder="请输入回寄地址" autocomplete="off"></td>
							</tr>
							<tr>
								<td class="customer_table_td_lable4"></td>
								<td colspan="2" style="text-align: center;">
									<input type="button" class="form-button" value="保存">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" class="form-button" value="重置">
								</td>
								<td class="customer_table_td_input4"></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div class="container top_con" style="width: 100%; min-width: 1000px">
				<h6>
					<span class="glyphicon glyphicon-folder-open work"></span>产品信息<i class="pull-right iconfont ">&#xe658;</i>
					<div class="clearfix"></div>
				</h6>
				<div class="row">
					<form id="J_customerForm" role="form" class="form-inline">
						<h3>《基本属性》：</h3>
						<table id="customer_table">
							<tr>
								<td class="customer_table_td_lable8"><label for="username" class="form-label"><span class="titl">品类:</span></label></td>
								<td class="customer_table_td_input8"><input type="text" class="form-control" name="username" id="username" autocomplete="off"></td>
								<td class="customer_table_td_lable8"><label for="username" class="form-label"><span class="titl">品牌:</span></label></td>
								<td class="customer_table_td_input8"><input type="text" class="form-control" name="username" id="username" autocomplete="off"></td>
								<td class="customer_table_td_lable8"><label for="username" class="form-label"><span class="titl">产品编号:</span></label></td>
								<td class="customer_table_td_input8"><input type="text" class="form-control" name="username" id="username" autocomplete="off"></td>
								<td class="customer_table_td_lable8"><label for="username" class="form-label"><span class="titl">尺寸:</span></label></td>
								<td class="customer_table_td_input8"><input type="text" class="form-control" name="username" id="username" autocomplete="off"></td>
							</tr>
							<tr>
								<td class="customer_table_td_lable8"><label for="username" class="form-label"><span class="titl">材质:</span></label></td>
								<td class="customer_table_td_input8"><input type="text" class="form-control" name="username" id="username" autocomplete="off"></td>
								<td class="customer_table_td_lable8"><label for="username" class="form-label"><span class="titl">主石:</span></label></td>
								<td class="customer_table_td_input8"><input type="text" class="form-control" name="username" id="username" autocomplete="off"></td>
								<td class="customer_table_td_lable8"><label for="username" class="form-label"><span class="titl">配石:</span></label></td>
								<td class="customer_table_td_input8"><input type="text" class="form-control" name="username" id="username" autocomplete="off"></td>
								<td class="customer_table_td_lable8"><label for="username" class="form-label"><span class="titl">长度:</span></label></td>
								<td class="customer_table_td_input8"><input type="text" class="form-control" name="username" id="username" autocomplete="off"></td>
							</tr>
							<tr>
								<td class="customer_table_td_lable8"><label for="username" class="form-label"><span class="titl">个性刻字:</span></label></td>
								<td class="customer_table_td_input8"><input type="text" class="form-control" name="username" id="username" autocomplete="off"></td>
								<td class="customer_table_td_lable8"><label for="username" class="form-label"><span class="titl">克重:</span></label></td>
								<td class="customer_table_td_input8"><input type="text" class="form-control" name="username" id="username" autocomplete="off"></td>
								<td class="customer_table_td_lable8"><label for="username" class="form-label"><span class="titl">自带包装:</span></label></td>
								<td class="customer_table_td_input8"><input type="text" class="form-control" name="username" id="username" autocomplete="off"></td>
								<td class="customer_table_td_lable8"><label for="username" class="form-label"><span class="titl">关联件:</span></label></td>
								<td class="customer_table_td_input8"><input type="text" class="form-control" name="username" id="username" autocomplete="off"></td>
							</tr>
						</table>
						<h3>《服务项》：</h3>
						<table id="customer_table">
							<tr>
								<td class="customer_table_td_input5"><input type="button" style="width: 200px" value="清洁保养"></td>
								<td class="customer_table_td_input5"><input type="button" style="width: 200px" value="补色"></td>
								<td class="customer_table_td_input5"><input type="button" style="width: 200px" value="补伤"></td>
								<td class="customer_table_td_input5"><input type="button" style="width: 200px" value="封边油"></td>
								<td class="customer_table_td_input5"><input type="button" style="width: 200px" value="淡化污染"></td>
							</tr>
							<tr>
								<td class="customer_table_td_input5"><input type="button" style="width: 200px" value="粘合加固"></td>
								<td class="customer_table_td_input5"><input type="button" style="width: 200px" value="更换拉链"></td>
								<td class="customer_table_td_input5"><input type="button" style="width: 200px" value="变形整形"></td>
								<td class="customer_table_td_input5"><input type="button" style="width: 200px" value="翻新五金"></td>
								<td class="customer_table_td_input5"><input type="button" style="width: 200px" value="修皮质配件"></td>
							</tr>
							<tr>
								<td class="customer_table_td_lable4"></td>
								<td colspan="3" style="text-align: center;">
									<input type="button" class="form-button" value="保存">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" class="form-button" value="重置">
								</td>
								<td class="customer_table_td_input4"></td>
							</tr>
						</table>
					</form>
					<!-- <div class="col-lg-6  col-md-12" id="box01"></div>
					<div class="col-lg-6 col-md-12" id="box02"></div> -->
				</div>
			</div>
			<div class="container top_con" style="width: 100%; min-width: 1000px">
				<h6>
					<span class="glyphicon glyphicon-folder-open work"></span>图片详情<i class="pull-right iconfont ">&#xe658;</i>
					<div class="clearfix"></div>
				</h6>
				<div class="row">
					<form id="J_customerForm" role="form" class="form-inline">
						<table id="customer_table">
							<tr>
								<td class="customer_table_td_input5"><img src="src/main/webapp/WEB-INF/image/A.png"></td>
								<td class="customer_table_td_input5"><img src="src/main/webapp/WEB-INF/image/A.png"></td>
								<td class="customer_table_td_input5"><img src="src/main/webapp/WEB-INF/image/A.png"></td>
								<td class="customer_table_td_input5"><img src="src/main/webapp/WEB-INF/image/A.png"></td>
								<td class="customer_table_td_input5"><input type="file"></td>
							</tr>
							<tr>
								<td class="customer_table_td_lable4"></td>
								<td colspan="3" style="text-align: center;">
									<input type="button" class="form-button" value="保存">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" class="form-button" value="重置">
								</td>
								<td class="customer_table_td_input4"></td>
							</tr>
						</table>
					</form>
					
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
								<td colspan="2" style="text-align: center;">
									<input type="button" class="form-button" value="保存">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" class="form-button" value="重置">
								</td>
								<td class="customer_table_td_input4"></td>
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
<script src="${ctx}/module-js/order/repair_order.js"></script>
</html>
