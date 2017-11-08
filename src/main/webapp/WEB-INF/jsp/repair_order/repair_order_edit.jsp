<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>

<style>
	#filePicker2 div:nth-child(2){width:100%!important;height:100%!important;}

.select2-container {
	z-index: 99999999999;
}

.xdsoft_datetimepicker {
	z-index: 99999999999;
}
</style>
<script>
		var ctx = '${ctx}';
        var imagesPath = '${imagesPath}'
        var imagesJson = eval('${imagesJson}');
	    var attributesJson = eval('${attributesJson}');
	    var categoryServiceJson = eval('${categoryServiceJson}');
	    var refOrderIdsJson = eval('${refOrderIdsJson}');
	</script>
<style>
	#filePicker2 div:nth-child(2){width:100%!important;height:100%!important;}
</style>

<link rel="stylesheet" type="text/css" href="${ctx}/module-css//webuploader.css">
<link rel="stylesheet" type="text/css" href="${ctx}/module-css/webuploader_style.css">

<div class="ibox float-e-margins">
	<div class="ibox-content">


		<input type="hidden" id="orderId" value="${repairOrder.id}" />
		<input type="hidden" id="orderNo" value="${repairOrder.orderNo}" />

		<div class="container top_con" style="width: 100%; min-width: 1000px">
			<h6>
				<span class="glyphicon glyphicon-briefcase work "></span>客户信息<i class="pull-right iconfont ">&#xe658;</i>
				<div class="clearfix"></div>
			</h6>
			<div class="row top_row col-md-12 b-r">


				<div id="customerContainer" class="col-md-12 b-r">
					<div class="row bottom10">
						<h3 class="m-t-none m-b">基础信息</h3>
						<input type="hidden" id="customerId" data-id="${repairOrder.customerId}" />
						<div class="col-md-2">
							<label>用户名</label> <input type="text" placeholder="请输入用户名" class="form-control" name="name" id="J_name" value="${repairOrder.customerName}"
								autocomplete="off" readonly>
						</div>

						<div class="col-md-2">
							<label>手机号</label> <input type="text" placeholder="请输入手机号" class="form-control" name="phone" id="J_phone" value="${repairOrder.customerPhone}"
								readonly>
						</div>

						<div class="col-md-4">
							<label>其他联系方式</label> <input type="text" placeholder="请输入其他联系方式" class="form-control" name="otherPhone" id="J_otherPhone"
								value="${repairOrder.customerPhoneSecond}" readonly>
						</div>

						<div class="col-md-4">
							<label>客户来源</label> <select class="form-control" id="channelSource" name="channelSource" disabled="disabled">
								<option value="1" <c:if test="${repairOrder.channelSource == 1}">selected="selected"</c:if>>udesk</option>
								<option value="2" <c:if test="${repairOrder.channelSource == 2}">selected="selected"</c:if>>北京7860</option>
								<option value="3" <c:if test="${repairOrder.channelSource == 3}">selected="selected"</c:if>>上海5588</option>
								<option value="4" <c:if test="${repairOrder.channelSource == 4}">selected="selected"</c:if>>总机400</option>
								<option value="5" <c:if test="${repairOrder.channelSource == 5}">selected="selected"</c:if>>杭州3123</option>
								<option value="6" <c:if test="${repairOrder.channelSource == 6}">selected="selected"</c:if>>上门</option>
								<option value="7" <c:if test="${repairOrder.channelSource == 7}">selected="selected"</c:if>>微博</option>
								<option value="8" <c:if test="${repairOrder.channelSource == 8}">selected="selected"</c:if>>微信</option>
								<option value="9" <c:if test="${repairOrder.channelSource == 9}">selected="selected"</c:if>>淘宝C店</option>
								<option value="10" <c:if test="${repairOrder.channelSource == 10}">selected="selected"</c:if>>淘宝B店</option>
								<option value="11" <c:if test="${repairOrder.channelSource == 11}">selected="selected"</c:if>>大众点评</option>
								<option value="12" <c:if test="${repairOrder.channelSource == 12}">selected="selected"</c:if>>老客介绍</option>
								<option value="13" <c:if test="${repairOrder.channelSource == 13}">selected="selected"</c:if>>品牌介绍</option>
								<option value="14" <c:if test="${repairOrder.channelSource == 14}">selected="selected"</c:if>>员工介绍</option>
								<option value="15" <c:if test="${repairOrder.channelSource == 15}">selected="selected"</c:if>>老板介绍</option>
								<option value="16" <c:if test="${repairOrder.channelSource == 16}">selected="selected"</c:if>>官网留言</option>
								<option value="17" <c:if test="${repairOrder.channelSource == 17}">selected="selected"</c:if>>论坛、博客</option>
								<option value="18" <c:if test="${repairOrder.channelSource == 18}">selected="selected"</c:if>>其他</option>
							</select>
						</div>

						<div class="col-md-2">
							<label>省市</label>
							<div id="provCity">
								<label id="province">${customer.province}</label>/<label id="city">${customer.city}</label>
							</div>
						</div>

						<div class="col-md-6">
							<label>详细地址</label> <input type="text" placeholder="请输入详细地址" class="form-control" name="address" id="J_address" value="${customer.address}"
								readonly>

						</div>
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
					<%--<h3 class="m-t-none m-b">基础信息</h3>--%>

					<div id="category" class="row bottom10">
						<label class="col-xs-1">分类:</label> <select class="prov col-xs-1" name="firstCategory" id="firstCategory"></select> <select
							class="city col-xs-1" disabled="disabled" name="secondCategory" id="secondCategory"></select> <label class="col-xs-1">品牌：</label> <span>
							<select id="brandId" name="brandId">

								<c:forEach items="${brandList}" var="item">
									<option value="${item.id}" <c:if test="${item.id == repairOrder.brandId}">selected="selected"</c:if>>${item.name}</option>
								</c:forEach>
						</select>
						</span>
					</div>
					<div id="attributes" class="bottom10"></div>

					<hr />
					<label>服务项:</label> <select id="serviceItem" name="serviceItem" multiple class="col-md-6">
					</select>
					<br>
					<br>
					<label>关联件单号:</label> <select id="refOrderIds" name="refOrderIds" multiple class="col-md-6">
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
					<label class="col-md-2">外观描述:</label> <input type="text" id="imageDesc" class="form-control bottom10" placeholder="" value="${repairOrder.imageDesc}" />

				</div>
			</div>
		</div>

		<div class="container top_con" style="width: 100%; min-width: 1000px">
			<h6>
				<span class="glyphicon glyphicon-folder-open work"></span>维修详情<i class="pull-right iconfont ">&#xe658;</i>
				<div class="clearfix"></div>
			</h6>
			<div class="row bottom10 left20">
				<div class="col-md-1">
					<label>类别:</label><br /> <select name="typeName" id="typeName">
						<option value="维修单" <c:if test="${repairOrder.typeName == '维修单'}">selected="selected"</c:if>>维修单</option>
						<option value="内部单" <c:if test="${repairOrder.typeName == '内部单'}">selected="selected"</c:if>>内部单</option>
						<%--<option value="返修单" <c:if test="${repairOrder.typeName == '返修单'}">selected="selected"</c:if>>返修单</option>--%>
						<option value="评估单" <c:if test="${repairOrder.typeName == '评估单'}">selected="selected"</c:if>>评估单</option>
					</select>
				</div>
				<div id="repairContent" class="row">
					<div class="row  col-md-9">
						<label>维修内容:</label> <input type="text" class="form-control" id="repairDesc" value="${repairOrder.repairDesc}">
					</div>
				</div>
			</div>

			<div class="row bottom10 left20">
				<div class="col-md-2">
					<label>预付款:</label> <input type="text" class="form-control" id="advancePayment" value="${repairOrder.advancePayment}">
				</div>
				<div class="col-md-2">
					<label>工费:</label> <select id="labourPaymentFlag">
						<option value="0" <c:if test="${repairOrder.labourPayment == -1}">selected="selected"</c:if>>待定</option>
						<option value="1" <c:if test="${repairOrder.labourPayment != -1}">selected="selected"</c:if>>确定</option>
					</select>
					<c:if test="${repairOrder.labourPayment == -1}">
						<input type="text" class="form-control" id="labourPayment" value="待定" readonly="readonly">
					</c:if>
					<c:if test="${repairOrder.labourPayment != -1}">
						<input type="text" class="form-control" id="labourPayment" value="${repairOrder.labourPayment}">
					</c:if>
				</div>
				<div class="col-md-2">
					<label>材料费:</label> <select id="materialPaymentFlag">
						<option value="0" <c:if test="${repairOrder.materialPayment == -1}">selected="selected"</c:if>>待定</option>
						<option value="1" <c:if test="${repairOrder.materialPayment != -1}">selected="selected"</c:if>>确定</option>
					</select>
					<c:if test="${repairOrder.materialPayment == -1}">
						<input type="text" class="form-control" id="materialPayment" value="待定" readonly="readonly">
					</c:if>
					<c:if test="${repairOrder.materialPayment != -1}">
						<input type="text" class="form-control" id="materialPayment" value="${repairOrder.materialPayment}">
					</c:if>
				</div>
				<div class="col-md-4">
					<label>材料费备注：</label> <input type="text" class="form-control" id="materialDesc" value="${repairOrder.materialDesc}">
				</div>
			</div>
			
			<div class="row bottom10 left20">
				<div class="col-md-2">
					<label>优惠金额:</label> <select id="discountAmountPaymentFlag">
						<option value="0" <c:if test="${repairOrder.discountAmountPayment == -1}">selected="selected"</c:if>>待定</option>
						<option value="1" <c:if test="${repairOrder.discountAmountPayment != -1}">selected="selected"</c:if>>确定</option>
					</select>
					<c:if test="${repairOrder.discountAmountPayment == -1}">
						<input type="text" class="form-control" id="discountAmountPayment" value="待定" readonly="readonly">
					</c:if>
					<c:if test="${repairOrder.discountAmountPayment != -1}">
						<input type="text" class="form-control" id="discountAmountPayment" value="${repairOrder.discountAmountPayment}">
					</c:if>
				</div>
				<div class="col-md-4">
					<label>优惠备注：</label> <input type="text" class="form-control" id="discountDesc" value="${repairOrder.discountDesc}">
				</div>
				<div class="col-md-2">
					<label>预计完成时间:</label> <input type="text" class="form-control" id="pickupDate"
								value="<fmt:formatDate value="${repairOrder.pickupAt}" pattern="yyyy-MM-dd"/>">
				</div>
			</div>
		</div>
	</div>
</div>

<script src="${ctx}/static/require/require.js"></script>
<script src="${ctx}/static/require/require.config.js"></script>
<script src="${ctx}/module-js/order/repair_order_edit.js"></script>
<script src="${ctx}/module-js/order/webuploader_edit.js"></script>

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





