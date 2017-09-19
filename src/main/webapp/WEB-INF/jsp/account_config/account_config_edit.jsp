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
						<div class="col-md-2">
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

            var customerName = $("#customerName").val();
            var customerPhone = $('#customerPhone').val();
            var customerSex = $('#customerSex').val();
            var customerAddress = $('#customerAddress').val();
            var wechatNo = $('#wechatNo').val();
            var wechatNickname = $('#wechatNickname').val();
            var channelSource = $('#channelSource').val();
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
                    channelSource:channelSource,
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
                        },1000);

                    }

                }

            });

        });




        });



</script>


</html>
