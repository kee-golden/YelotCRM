<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/common/taglibs.jsp"%>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<title>CRM管理后台</title>

	<%@include file="/WEB-INF/common/static.jsp"%>
	<link href="${ctx}/module-css/consult-order.css" rel="stylesheet">

	<c:set var="PARENT_MENU_CODE" value="ConsultOrder_Manage" />
	<c:set var="CHILD_MENU_CODE" value="ConsultOrder_List" />

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
				<a href='${ctx}/consult-order/alllist' ><span class="label label-primary" style="float: right">返回</span></a>

			</div>
			<div class="container top_con" style="width: 100%; min-width: 1000px">
				<h6>
					<span class="glyphicon glyphicon-folder-open work"></span>基本信息<i class="pull-right iconfont ">&#xe658;</i>
					<div class="clearfix"></div>
				</h6>


					<div id="customerContainer" class="col-md-12 b-r">
						<div class="row bottom10">
							<input type="hidden" id="customerId" data-id="" />
							<div class="col-md-2">
								<label><span style="color: red">*</span>用户名</label>
								<input type="text" placeholder="请输入用户名" class="form-control" value="${bean.customerName}"
																						  autocomplete="off" >
							</div>

							<div class="col-md-2">
								<label>性别</label><br/>
								<label class="text-info">
									<c:if test="${bean.customerSex == 0}">女</c:if>
									<c:if test="${bean.customerSex == 1}">男</c:if>

								</label>

							</div>

							<div class="col-md-2">
								<label><span style="color: red">*</span>手机号</label>
								<input type="text" placeholder="请输入手机号" class="form-control" value="${bean.customerPhone}">
							</div>

							<div class="col-md-2">
								<label>省市</label>
								<div id="prov_city">
									<label class="text-info">${bean.province}/</label>
									<label class="text-info">${bean.city}</label>

								</div>

							</div>
							<div class="col-md-4">
								<label>详细地址</label>
								<input type="text" placeholder="请输入详细地址" class="form-control"  value="${bean.customerAddress}">

							</div>
						</div>
						<div id="category" class="row bottom10">
							<div class="col-md-2">
								<label>分类</label><br/>
								<label class="text-info">${bean.firstCategoryName}/</label>
								<label class="text-info">${bean.secondCategoryName}</label>


							</div>
							<div class="col-md-2">


								<label>品牌</label><br/>
								<label class="text-info">${bean.brandName}</label>

							</div>
							<div class="col-md-2">
								<label>微信号</label>
								<input type="text" name="wechatNo" id="wechatNo" value="${bean.wechatNo}" class="form-control" readonly>
							</div>
							<div class="col-md-2">
								<label>来源页面</label>
								<input type="text" name="channelUrl" id="channelUrl" value="${bean.channelUrl}" class="form-control" readonly>
							</div>
							<div class="col-md-4">
								<label>维修需求</label>
								<input type="text" name="repairCommands" id="repairCommands" value="${bean.repairCommands}" class="form-control" readonly>
							</div>
						</div>

						<div class="row bottom10">
							<div class="col-md-2">
								<label>关键词</label>
								<input type="text" id="keywords" name="keywords" class="form-control"  value="${bean.keywords}" readonly>
							</div>
							<div class="col-md-2">
								<label>价格区间</label><input type="text" id="priceLimit" name="priceLimit" class="form-control"  value="${bean.priceLimit}" readonly>
							</div>
							<div class="col-md-2">
								<label>时间要求</label><input type="text" id="timeLimit" name="timeLimit" class="form-control" value="${bean.timeLimit}" readonly>
							</div>
							<div class="col-md-2">
								<label>质量要求</label><input type="text" id="qulityLimit" name="qulityLimit" class="form-control" value="${bean.qulityLimit}" readonly>
							</div>
							<div class="col-md-4">
								<label>特殊要求</label>
								<input type="text" id="specialCommands" name="specialCommands" class="form-control" value="${bean.specialCommands}" readonly/>
							</div>


						</div>

						<div class="row bottom10">
							<div class="col-md-2">
							<label>预约上门时间</label>
							<input type="text" id="vistorAt" name="vistorAt" value="${bean.vistorAt}" class="form-control" readonly>
							</div>
							<div class="col-md-2">
								<label>快递单号</label>
								<input type="text" id="expressNo" name="expressNo" value="${bean.expressNo}" class="form-control" readonly>
							</div>

						</div>

						</div>
				<%--</div>--%>

			</div>
			<div class="container top_con" style="width: 100%; min-width: 1000px">
				<h6>
					<span class="glyphicon glyphicon-folder-open work"></span>图片详情<i class="pull-right iconfont ">&#xe658;</i>
					<div class="clearfix"></div>
				</h6>
				<div class="row">
					<div id="images">
						<c:forEach items="${images}" var="imagePath" varStatus="status">
							<c:if test="${status.index gt 0 and status.index % 5 eq 0}">
								<br/>
							</c:if>
							<img src="${ctx}/${imagePath}" style="width:200px;height:240px;"/>

						</c:forEach>

					</div>
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

<script>
    require(['jquery','yaya','selector2','cityselect','dateTimePicker'], function ($, yaya,selector2,cityselect) {





        $('#vistorAt').datetimepicker({
            lang: 'ch',
            format: 'Y-m-d',
            timepicker:false
        });



        function checkIsMobile(value){
            var length = value.length;
            var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
            return (length == 11 && mobile.test(value));
        }

        });



</script>


</html>
