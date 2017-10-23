<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

	<script>
		var ctx = '${ctx}';
        var imagesPath = '${imagesPath}'
        var imagesJson = eval('${imagesJson}');
	    var attributesJson = eval('${attributesJson}');
	    var categoryServiceJson = eval('${categoryServiceJson}');
	</script>


<div class="ibox float-e-margins">
	<div class="ibox-content">


		<input type="hidden" id="orderId" value="${repairOrder.id}" />

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
								<label>用户名</label>
								<input type="text" placeholder="请输入用户名" class="form-control" name="name" id="J_name" value="${repairOrder.customerName}"
									autocomplete="off" readonly>
							</div>

							<div class="col-md-3">
								<label>手机号</label>
								<input type="text" placeholder="请输入手机号" class="form-control" name="phone" id="J_phone" value="${repairOrder.customerPhone}"
									readonly>
							</div>

							<div class="col-md-2">
								<label>省市</label>
								<div id="provCity">
									<label id="province">${customer.province}</label>/<label id="city">${customer.city}</label>

								</div>

							</div>
							<div class="col-md-5">
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
							<label class="col-xs-1">分类:</label>
							<select class="prov col-xs-1" name="firstCategory" id="firstCategory"></select>
							<select class="city col-xs-1" disabled="disabled" name="secondCategory" id="secondCategory"></select>
							<label class="col-xs-1">品牌：</label> <span>
								<select id="brandId" name="brandId">

									<c:forEach items="${brandList}" var="item">
										<option value="${item.id}" <c:if test="${item.id == repairOrder.brandId}">selected="selected"</c:if>>${item.name}</option>
									</c:forEach>
							</select>
							</span>
						</div>
						<div id="attributes" class="bottom10"></div>

						<hr />
						<label>服务项:</label>
							<select id="serviceItem" name="serviceItem" multiple class="col-md-6">


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
						<label class="col-md-2">外观描述:</label>
						<input type="text" id="imageDesc" class="col-md-6 bottom10" placeholder="" value="${repairOrder.imageDesc}"/>

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
							<label class="col-md-2">维修内容:</label>
							<input type="text" class="col-md-8" id="repairDesc" value="${repairOrder.repairDesc}">
						</div>
						<div class="row bottom10">
							<div class="col-md-2">
							<label>维修单类别:</label><br/>
							<select name="typeName" id="typeName" >
								<option value="维修单" <c:if test="${repairOrder.typeName == '维修单'}">selected="selected"</c:if>>维修单</option>
								<option value="内部单" <c:if test="${repairOrder.typeName == '内部单'}">selected="selected"</c:if>>内部单</option>
								<%--<option value="返修单" <c:if test="${repairOrder.typeName == '返修单'}">selected="selected"</c:if>>返修单</option>--%>
								<option value="评估单" <c:if test="${repairOrder.typeName == '评估单'}">selected="selected"</c:if>>评估单</option>
							</select>
							</div>
							<div class="col-md-2">
							<label>预付款:</label>
								<input type="text"  class="form-control" id="advancePayment" value="${repairOrder.advancePayment}">
							</div>
							<div class="col-md-2"><label>工费:</label>
							<select id="labourPaymentFlag">
								<option value="0" <c:if test="${repairOrder.labourPayment == -1}">selected="selected"</c:if>>待定</option>
								<option value="1" <c:if test="${repairOrder.labourPayment != -1}">selected="selected"</c:if>>确定</option>
							</select> 
								<c:if test="${repairOrder.labourPayment == -1}"><input type="text" class="form-control" id="labourPayment" value="待定" readonly="readonly"></c:if>
								<c:if test="${repairOrder.labourPayment != -1}"><input type="text" class="form-control" id="labourPayment" value="${repairOrder.labourPayment}"></c:if>
							</div>
							<div class="col-md-2"><label>材料费:</label>
							<select id="materialPaymentFlag">
								<option value="0" <c:if test="${repairOrder.materialPayment == -1}">selected="selected"</c:if>>待定</option>
								<option value="1" <c:if test="${repairOrder.materialPayment != -1}">selected="selected"</c:if>>确定</option>
							</select> 
							<c:if test="${repairOrder.materialPayment == -1}"><input type="text" class="form-control" id="materialPayment" value="待定" readonly="readonly"></c:if>
							<c:if test="${repairOrder.materialPayment != -1}"><input type="text" class="form-control" id="materialPayment" value="${repairOrder.materialPayment}"></c:if>
							</div>
							<div class="col-md-2"><label>预计完成时间:</label>
								<input type="text" class="form-control" id="pickupDate" value="<fmt:formatDate value="${repairOrder.pickupAt}" pattern="yyyy-MM-dd"/>">
							</div>
						</div>


					</div>
					<%--<div class="col-lg-6  col-md-12"></div>--%>
					<%--<div class="col-lg-6 col-md-12"></div>--%>
				</div>
				<%--<div class="col-md-2 bottom10">--%>
					<%--<input type="button" class="form-control col-md-2 " id="saveBtn" value="保存" />--%>
				<%--</div>--%>
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





