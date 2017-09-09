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

	<c:set var="PARENT_MENU_CODE" value="ConsultOrder_Manage" />
	<c:set var="CHILD_MENU_CODE" value="ConsultOrder_Add" />

	<script>
		var ctx = '${ctx}';
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

			</div>
			<div class="container top_con" style="width: 100%; min-width: 1000px">
				<h6>
					<span class="glyphicon glyphicon-folder-open work"></span>客户信息<i class="pull-right iconfont ">&#xe658;</i>
					<div class="clearfix"></div>
				</h6>
				<div id="customerContainer" class="col-md-12 b-r">
					<div class="row bottom10">
							<input type="hidden" id="customerId" data-id="" />
							<div class="col-md-2">
								<label><span style="color: red">*</span>用户名</label> <input type="text" placeholder="请输入用户名" class="form-control" name="customerName" id="customerName"
																						  autocomplete="off" >
							</div>

							<div class="col-md-2">
								<label><span style="color: red"></span>手机号</label> <input type="text" placeholder="请输入手机号" class="form-control" name="customerPhone" id="customerPhone">
							</div>

							<div class="col-md-2">
								<label>性别</label><br/>
									<select name="customerSex" id="customerSex">
										<option value="0">女</option>
										<option value="1">男</option>
									</select>

							</div>

							<div class="col-md-2">
								<label>省市</label>
								<div id="prov_city">
									<select class="prov" id="province" name="province"></select> <select class="city" id="city" disabled="disabled" name="city"></select>
								</div>

							</div>
							<div class="col-md-4">
								<label>详细地址</label> <input type="text" placeholder="请输入详细地址" class="form-control" name="customerAddress" id="customerAddress" value="${bean.address}"
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
								<option value="">无</option>

								<c:forEach items="${brandList}" var="item">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-2">
							<label>预约门店</label><br/>
							<select id="bookShopId">
								<option value="0">无</option>
								<c:forEach items="${shopList}" var="item">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-2">
							<label>预约上门时间</label>
							<input type="text" id="vistorAt" name="vistorAt" placeholder="上门时间" class="form-control" >
						</div>

						<div class="col-md-4">
							<label>维修需求</label>
							<input type="text" name="repairCommands" id="repairCommands" placeholder="维修需求" class="form-control">
						</div>
					</div>

					<div class="row bottom10">
						<div class="col-md-2">
							<label>关键词</label><input type="text" id="keywords" name="keywords" class="form-control"  placeholder="关键词">
						</div>
						<div class="col-md-2">
							<label>价格区间</label><input type="text" id="priceLimit" name="priceLimit" class="form-control"  placeholder="价格区间">
						</div>
						<div class="col-md-2">
							<label>时间要求</label><input type="text" id="timeLimit" name="timeLimit" class="form-control" placeholder="时间要求">
						</div>
						<div class="col-md-2">
							<label>质量要求</label><input type="text" id="qulityLimit" name="qulityLimit" class="form-control" placeholder="质量要求">
						</div>
						<div class="col-md-4">
							<label>特殊要求</label>
							<input type="text" id="specialCommands" name="specialCommands" class="form-control" placeholder="特殊要求"/>
						</div>


					</div>

					<div class="row bottom10">
						<div class="col-md-2">
							<label>来源页面</label>
							<input type="text" name="channelUrl" id="channelUrl" placeholder="来源页面网页地址" class="form-control">
						</div>
						<div class="col-md-2">
							<label>物品送达方式</label><br/>
							<select id="deliverType">
								<option value="客户上门">客户上门</option>
								<option value="快递">快递</option>
								<option value="上门取件">上门取件</option>
							</select>
						</div>

						<div class="col-md-2">
							<label>快递单号</label>
							<input type="text" id="expressNo" name="expressNo" placeholder="快递单号" class="form-control" >
						</div>
						<div class="col-md-6">
							<label>备注</label>
							<input type="text" id="commentId" name="comment" placeholder="备注" class="form-control" >
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
    require(['jquery','yaya','selector2','cityselect','dateTimePicker'], function ($, yaya,selector2,cityselect) {

        var jsonObj = eval(${categoryJson});//转化为json 对象
        $("#category").citySelect({
            url:jsonObj,
            prov:'${firstCategory}',
            city:'${secondCategory}',
            nodata:"none"
        });

        $("#prov_city").citySelect({
            url:"/static/cityselect/js/city.min.js",
            prov:'上海',
            city:'黄浦区',
            nodata:"none",
            required:false
        });

        $('#vistorAt').datetimepicker({
            lang: 'ch',
            format: 'Y-m-d',
            timepicker:false
        });

        $("#saveBtn").click(function () {

            var customerName = $("#customerName").val();
            var customerPhone = $('#customerPhone').val();
            var customerSex = $('#customerSex').val();
            var customerAddress = $('#customerAddress').val();
            var wechatNo = $('#wechatNo').val();
            var wechatNickname = $('#wechatNickname').val();
            var repairCommands = $('#repairCommands').val();
            var province = $('#province').val();
            var city = $('#city').val();
            var brandId = $('#brand').val();
            var firstCategory = $('#firstCategory').val();
            var secondCategory = $('#secondCategory').val();
            var channelUrl = $('#channelUrl').val();
            var keywords = $('#keywords').val();
            var priceLimit = $('#priceLimit').val();
            var timeLimit = $('#timeLimit').val();
            var qulityLimit = $('#qulityLimit').val();
            var specialCommands = $('#specialCommands').val();
            var vistorDate = $('#vistorAt').val();
            var imagesPath = $('.filelist').data('path');
            var expressNo = $('#expressNo').val();
            var deliverType = $('#deliverType').val();
            var comment = $('#commentId').val();
            var bookShopId = $('#bookShopId').val();

            if(customerName == ''){
                yaya.layer.msg('用户名不能为空');
                return;
			}
			if(customerPhone == ''  && wechatNo == ''){
                yaya.layer.msg('用户手机号和微信号至少要填写一项');
                return;
			}else if(customerPhone != '' && !checkIsMobile(customerPhone)){
			    yaya.layer.msg("手机号输入不正确");
			    return;
			}


            $.ajax({
                url: ctx + '/consult-order/save',
                method: 'post',
                dataType: 'json',
                data: {
                    customerName: customerName,
                    customerPhone: customerPhone,
                    customerSex: customerSex,
                    customerAddress: customerAddress,
                    wechatNo:wechatNo,
                    wechatNickname:wechatNickname,
                    repairCommands:repairCommands,
                    province:province,
                    city:city,
                    brandId:brandId,
                    firstCategoryName:firstCategory,
                    secondCategoryName:secondCategory,
                    channelUrl:channelUrl,
                    keywords:keywords,
                    priceLimit:priceLimit,
                    timeLimit:timeLimit,
                    qulityLimit:qulityLimit,
                    specialCommands:specialCommands,
                    imagesPath:imagesPath,
                    vistorDate:vistorDate,
                    expressNo:expressNo,
                    bookShopId:bookShopId,
                    deliverType:deliverType,
                    comment:comment,

                },
                success: function (data) {
                    if(data.code == 1200){
                        yaya.layer.msg("提交成功");
                        setTimeout(function () {
                            window.location.href = ctx+'/consult-order/alllist';
                        },2000);

                    }

                }

            });

        });


        function checkIsMobile(value){
            var length = value.length;
            var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
            return (length == 11 && mobile.test(value));
        }

        });



</script>


</html>
