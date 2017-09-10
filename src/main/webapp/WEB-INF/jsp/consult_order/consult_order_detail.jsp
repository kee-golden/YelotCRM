<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/common/taglibs.jsp"%>


<script>
		var ctx = '${ctx}';
		var imagesPath = '${imagesPath}'
        var imagesJson = eval('${imagesJson}');

</script>

<div class="ibox float-e-margins">
	<div class="ibox-content">
		<div class="container top_con" style="width: 100%; min-width: 1000px">
			<h6>
				<span class="glyphicon glyphicon-folder-open work"></span>客户信息<i class="pull-right iconfont ">&#xe658;</i>
				<div class="clearfix"></div>
			</h6>
			<div id="customerContainer" class="col-md-12 b-r">
				<div class="row bottom10">
					<input type="hidden" id="orderId" data-id="${bean.id}" />
					<div class="col-md-2">
						<label><span style="color: red">*</span>用户名</label>
						<input type="text" placeholder="请输入用户名" class="form-control" name="customerName" id="customerName"
							   autocomplete="off" value="${bean.customerName}">
					</div>

					<div class="col-md-2">
						<label><span style="color: red"></span>手机号</label>
						<input type="text" placeholder="请输入手机号" class="form-control" name="customerPhone" id="customerPhone" value="${bean.customerPhone}">
					</div>

					<div class="col-md-1">
						<label>性别</label><br/>
						<select name="customerSex" id="customerSex">
							<option value="0" <c:if test="${bean.customerSex == 0}">selected="selected"</c:if>>女</option>
							<option value="1" <c:if test="${bean.customerSex == 1}">selected="selected"</c:if>>男</option>
						</select>
					</div>

					<div class="col-md-3">
						<label>省市</label>
						<div id="prov_city">
							<select class="prov" id="province" name="province"></select> <select class="city" id="city" disabled="disabled" name="city"></select>
						</div>

					</div>
					<div class="col-md-4">
						<label>详细地址</label> <input type="text" placeholder="请输入详细地址" class="form-control" name="customerAddress" id="customerAddress" value="${bean.customerAddress}"
					>
					</div>
				</div>
				<div class="row bottom10">
					<div class="col-md-2">
						<label>微信号</label>
						<input type="text" name="wechatNo" id="wechatNo" placeholder="微信ID号" class="form-control" value="${bean.wechatNo}">
					</div>
					<div class="col-md-2">
						<label>微信昵称</label>
						<input type="text" name="wechatNickname" id="wechatNickname" placeholder="微信昵称" class="form-control" value="${bean.wechatNickname}">
					</div>
				</div>
			</div>

		</div>
		<div class="container top_con" style="width: 100%; min-width: 1000px">
			<h6>
				<span class="glyphicon glyphicon-folder-open work"></span>咨询信息<i class="pull-right iconfont ">&#xe658;</i>
				<div class="clearfix"></div>
			</h6>
			<div id="consultContainer" class="col-md-12 b-r">
				<div id="category" class="row bottom10">
					<div class="col-md-2">
						<label>分类</label><br/>
						<select class="prov" name="firstCategory" id="firstCategory"></select>
						<select class="city" disabled="disabled" name="secondCategory" id="secondCategory">
						</select>
					</div>
					<div class="col-md-2">
						<label>品牌</label><br/>
						<select id="brand" name="brand">
							<option value="0">无</option>
							<c:forEach items="${brandList}" var="item">
								<option value="${item.id}" <c:if test="${bean.brandId == item.id}">selected="selected"</c:if>>${item.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-md-2">
						<label>预约门店</label><br/>
						<select id="bookShopId">
							<option value="0">无</option>
							<c:forEach items="${shopList}" var="item">
								<option value="${item.id}" <c:if test="${item.id == bean.bookShopId}">selected="selected"</c:if>> ${item.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-md-2">
						<label>预约上门时间</label>
						<input type="text" id="vistorAt" name="vistorAt" placeholder="上门时间" class="form-control"
							   value='<fmt:formatDate value="${bean.vistorAt}" pattern="yyyy-MM-dd"/>'>
					</div>

					<div class="col-md-4">
						<label>维修需求</label>
						<input type="text" name="repairCommands" id="repairCommands" placeholder="维修需求" class="form-control" value="${bean.repairCommands}">
					</div>
				</div>

				<div class="row bottom10">
					<div class="col-md-2">
						<label>关键词</label><input type="text" id="keywords" name="keywords" class="form-control"  placeholder="关键词" value="${bean.keywords}">
					</div>
					<div class="col-md-2">
						<label>价格区间</label><input type="text" id="priceLimit" name="priceLimit" class="form-control"  placeholder="价格区间" value="${bean.priceLimit}">
					</div>
					<div class="col-md-2">
						<label>时间要求</label><input type="text" id="timeLimit" name="timeLimit" class="form-control" placeholder="时间要求" value="${bean.timeLimit}">
					</div>
					<div class="col-md-2">
						<label>质量要求</label><input type="text" id="qulityLimit" name="qulityLimit" class="form-control" placeholder="质量要求" value="${bean.qulityLimit}">
					</div>
					<div class="col-md-4">
						<label>特殊要求</label>
						<input type="text" id="specialCommands" name="specialCommands" class="form-control" placeholder="特殊要求" value="${bean.specialCommands}"/>
					</div>


				</div>

				<div class="row bottom10">
					<div class="col-md-2">
						<label>来源页面</label>
						<input type="text" name="channelUrl" id="channelUrl" placeholder="来源页面网页地址" class="form-control" value="${bean.channelUrl}">
					</div>
					<div class="col-md-2">
						<label>物品送达方式</label><br/>
						<select id="deliverType">
							<option value="客户上门" <c:if test="${bean.deliverType == '客户上门'}">selected="selected"</c:if>>客户上门</option>
							<option value="快递" <c:if test="${bean.deliverType == '快递'}">selected="selected"</c:if>>快递</option>
							<option value="上门取件" <c:if test="${bean.deliverType == '上门取件'}">selected="selected"</c:if>>上门取件</option>
						</select>
					</div>

					<div class="col-md-2">
						<label>快递单号</label>
						<input type="text" id="expressNo" name="expressNo" placeholder="快递单号" class="form-control" value="${bean.expressNo}">
					</div>
					<div class="col-md-6">
						<label>备注</label>
						<input type="text" id="commentId" name="comment" placeholder="备注" class="form-control" value="${bean.comment}">
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
				<div id="uploader">
					<div class="queueList">
						<div id="dndArea" class="placeholder">
							<div id="filePicker"></div>
							<p>或将照片拖到这里，单次最多可选20张</p>
						</div>
					</div>
					<div class="statusBar" style="display: block;">
						<div class="progress">
							<span class="text">0%</span> <span class="percentage"></span>
						</div>
						<div class="info" style="display: none;"></div>
						<div class="btns">
							<div id="filePicker2"></div>
							<div class="uploadBtn">开始上传</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<%--<div class="container top_con" style="width: 100%; min-width: 1000px">--%>
			<%--<div class="col-md-2 bottom10">--%>
				<%--<input type="button" class="form-control col-md-2 btn" id="saveBtn" value="保存" />--%>
			<%--</div>--%>
		<%--</div>--%>
	</div>
</div>


<script src="${ctx}/module-js/consult_order/webuploader_edit.js"></script>

<script>
    require(['jquery','yaya','selector2','cityselect','dateTimePicker'], function ($, yaya,selector2,cityselect) {

        var jsonObj = eval(${categoryJson});//转化为json 对象
        $("#category").citySelect({
            url:jsonObj,
            prov:'${bean.firstCategoryName}',
            city:'${bean.secondCategoryName}',
            nodata:"none"
        });

        $("#prov_city").citySelect({
            url:"/static/cityselect/js/city.min.js",
            prov:'${bean.province}',
            city:'${bean.city}',
            nodata:"none",
            required:false
        });

        $('#vistorAt').datetimepicker({
            lang: 'ch',
            format: 'Y-m-d',
            timepicker:false,
            beforeShow: function () {
                setTimeout(function () {
                        $('.xdsoft_datetimepicker').css("z-index", 99999999);
                    }, 1000
                );
            }
        });

        function checkIsMobile(value){
            var length = value.length;
            var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
            return (length == 11 && mobile.test(value));
        }

        $('#saveBtn').click(function () {

            console.log($('.filelist').data("path"));
            $.ajax({
				url:'/consult-order/update',
                method: 'post',
                dataType: 'json',
                data: {
				    id:$('#orderId').data("id"),
                    customerName: $('#customerName').val(),
                    customerSex:$('#customerSex').val(),
                    customerPhone:$('#customerPhone').val(),
                    customerAddress: $('#customerAddress').val(),
                    province: $('#province').val(),
                    city: $('#city').val(),
                    wechatNo: $('#wechatNo').val(),
                    wechatNickname: $('#wechatNickname').val(),
                    firstCategoryName: $('#firstCategory').val(),
                    secondCategoryName: $('#secondCategory').val(),
                    brandId:$('#brand').val(),
                    bookShopId:$('#bookShopId').val(),
                    vistorDate:$('#vistorAt').val(),
                    repairCommands:$('#repairCommands').val(),
                    keywords:$('#keywords').val(),
                    channelUrl:$('#channelUrl').val(),
                    priceLimit:$('#priceLimit').val(),
                    timeLimit:$('#timeLimit').val(),
                    qulityLimit:$('#qulityLimit').val(),
                    specialCommands:$('#specialCommands').val(),
                    deliverType:$('#deliverType').val(),
                    expressNo:$('#expressNo').val(),
                    comment:$('#commentId').val(),
                    imagesPath:$('.filelist').data("path"),

                },
                success: function (data) {
                    if(data.code == 1200){
                        yaya.layer.msg("修改成功");
                        setTimeout(function () {
                            window.location.href = ctx+'/consult-order/alllist';
                        },1000);

                    }

                }
			});

        });

	});



</script>


