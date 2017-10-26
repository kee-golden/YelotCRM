<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html lang="zh-cn">
<head>
	<link href='/static/bootstrap/css/bootstrap.min.css' rel='stylesheet'>
	<link href='/static/yaya/css/style.css' rel='stylesheet'>
	<link href='/module-css/basic.css' rel='stylesheet'>
	<link rel="stylesheet" href="/static/css/iconfont.css">
	<link rel="stylesheet" type="text/css" href="/module-css//webuploader.css">
	<link rel="stylesheet" type="text/css" href="/module-css/webuploader_style.css">
</head>
<body>
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
							   autocomplete="off" value="${bean.customerName}" disabled="disabled">
					</div>

					<div class="col-md-2">
						<label><span style="color: red"></span>手机号</label>
						<input type="text" placeholder="请输入手机号" class="form-control" name="customerPhone" id="customerPhone" value="${bean.customerPhone}" disabled="disabled">
					</div>

					<div class="col-md-1">
						<label>性别</label><br/>
						<select name="customerSex" id="customerSex" disabled="disabled">
							<option value="0" <c:if test="${bean.customerSex == 0}">selected="selected"</c:if>>女</option>
							<option value="1" <c:if test="${bean.customerSex == 1}">selected="selected"</c:if>>男</option>
						</select>
					</div>

					<div class="col-md-3">
						<label>省市</label>
						<div id="prov_city">
							${bean.province}/${bean.city}
							<!-- <select class="prov" id="province" name="province" disabled="disabled"></select> 
							<select class="city" id="city" name="city" disabled="disabled" ></select> -->
						</div>

					</div>
					<div class="col-md-4">
						<label>详细地址</label> <input type="text" placeholder="请输入详细地址" class="form-control" name="customerAddress" id="customerAddress" value="${bean.customerAddress}" disabled="disabled">
					</div>
				</div>
				<div class="row bottom10">
					<div class="col-md-2">
						<label>微信号</label>
						<input type="text" name="wechatNo" id="wechatNo" placeholder="微信ID号" class="form-control" value="${bean.wechatNo}" disabled="disabled">
					</div>
					<div class="col-md-2">
						<label>微信昵称</label>
						<input type="text" name="wechatNickname" id="wechatNickname" placeholder="微信昵称" class="form-control" value="${bean.wechatNickname}" disabled="disabled">
					</div>
					<div class="col-md-2">
						<label>阿里旺旺账号</label>
						<input type="text" name="aliNo" id="aliNo" placeholder="阿里旺旺账号" class="form-control" value="${bean.aliNo}" disabled="disabled">
					</div>
					<div class="col-md-2">
						<label>阿里旺旺昵称</label>
						<input type="text" name="aliNickname" id="aliNickname" placeholder="阿里旺旺昵称" class="form-control" value="${bean.aliNickname}" disabled="disabled">
					</div>
					<div class="col-md-2">
						<label>微博账号</label>
						<input type="text" name="blogNo" id="blogNo" placeholder="微博账号" class="form-control" value="${bean.blogNo}" disabled="disabled">
					</div>
					<div class="col-md-2">
						<label>微博昵称</label>
						<input type="text" name="blogNickname" id="blogNickname" placeholder="微博昵称" class="form-control" value="${bean.blogNickname}" disabled="disabled">
					</div>
					<div class="col-md-2">
						<label>QQ账号</label>
						<input type="text" name="QQNo" id="QQNo" placeholder="QQ账号" class="form-control" value="${bean.QQNo}" disabled="disabled">
					</div>
					<div class="col-md-2">
						<label>QQ昵称</label>
						<input type="text" name="QQNickname" id="QQNickname" placeholder="QQ昵称" class="form-control" value="${bean.QQNickname}" disabled="disabled">
					</div>
					<div class="col-md-2">
						<label>客户来源</label>
						<select class="form-control" id="channelSource" name="channelSource" disabled="disabled">
                            <option value="1" <c:if test="${bean.channelSource == 1}">selected="selected"</c:if>>udesk</option>
                            <option value="2" <c:if test="${bean.channelSource == 2}">selected="selected"</c:if>>北京7860</option>
                            <option value="3" <c:if test="${bean.channelSource == 3}">selected="selected"</c:if>>上海5588</option>
                            <option value="4" <c:if test="${bean.channelSource == 4}">selected="selected"</c:if>>总机400</option>
                            <option value="5" <c:if test="${bean.channelSource == 5}">selected="selected"</c:if>>杭州3123</option>
                            <option value="6" <c:if test="${bean.channelSource == 6}">selected="selected"</c:if>>上门</option>
                            <option value="7" <c:if test="${bean.channelSource == 7}">selected="selected"</c:if>>微博</option>
                            <option value="8" <c:if test="${bean.channelSource == 8}">selected="selected"</c:if>>微信</option>
                            <option value="9" <c:if test="${bean.channelSource == 9}">selected="selected"</c:if>>淘宝C店</option>
                            <option value="10" <c:if test="${bean.channelSource == 10}">selected="selected"</c:if>>淘宝B店</option>
                            <option value="11" <c:if test="${bean.channelSource == 11}">selected="selected"</c:if>>大众点评</option>
                            <option value="12" <c:if test="${bean.channelSource == 12}">selected="selected"</c:if>>老客介绍</option>
                            <option value="13" <c:if test="${bean.channelSource == 13}">selected="selected"</c:if>>品牌介绍</option>
                            <option value="14" <c:if test="${bean.channelSource == 14}">selected="selected"</c:if>>员工介绍</option>
                            <option value="15" <c:if test="${bean.channelSource == 15}">selected="selected"</c:if>>老板介绍</option>
                            <option value="16" <c:if test="${bean.channelSource == 16}">selected="selected"</c:if>>官网留言</option>
                            <option value="17" <c:if test="${bean.channelSource == 17}">selected="selected"</c:if>>论坛、博客</option>
                            <option value="18" <c:if test="${bean.channelSource == 18}">selected="selected"</c:if>>其他</option>
                        </select>
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
						${bean.firstCategoryName}/${bean.secondCategoryName}
						<!-- <select class="prov" name="firstCategory" id="firstCategory"></select>
						<select class="city" disabled="disabled" name="secondCategory" id="secondCategory"></select> -->
					</div>
					<div class="col-md-2">
						<label>品牌</label><br/>
						<select id="brand" name="brand" disabled="disabled">
							<option value="0">无</option>
							<c:forEach items="${brandList}" var="item">
								<option value="${item.id}" <c:if test="${bean.brandId == item.id}">selected="selected"</c:if>>${item.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-md-2">
						<label>预约门店</label><br/>
						<select id="bookShopId" disabled="disabled">
							<option value="0">无</option>
							<c:forEach items="${shopList}" var="item">
								<option value="${item.id}" <c:if test="${item.id == bean.bookShopId}">selected="selected"</c:if>> ${item.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-md-2">
						<label>预约上门时间</label>
						<input type="text" id="vistorAt" name="vistorAt" placeholder="上门时间" class="form-control"
							   value='<fmt:formatDate value="${bean.vistorAt}" pattern="yyyy-MM-dd"/>' disabled="disabled">
					</div>

					<div class="col-md-4">
						<label>维修需求</label>
						<input type="text" name="repairCommands" id="repairCommands" placeholder="维修需求" class="form-control" value="${bean.repairCommands}" disabled="disabled">
					</div>
				</div>

				<div class="row bottom10">
					<div class="col-md-2">
						<label>关键词</label><input type="text" id="keywords" name="keywords" class="form-control"  placeholder="关键词" value="${bean.keywords}" disabled="disabled">
					</div>
					<div class="col-md-2">
						<label>价格区间</label><input type="text" id="priceLimit" name="priceLimit" class="form-control"  placeholder="价格区间" value="${bean.priceLimit}" disabled="disabled">
					</div>
					<div class="col-md-2">
						<label>时间要求</label><input type="text" id="timeLimit" name="timeLimit" class="form-control" placeholder="时间要求" value="${bean.timeLimit}" disabled="disabled">
					</div>
					<div class="col-md-2">
						<label>质量要求</label><input type="text" id="qulityLimit" name="qulityLimit" class="form-control" placeholder="质量要求" value="${bean.qulityLimit}" disabled="disabled">
					</div>
					<div class="col-md-4">
						<label>特殊要求</label>
						<input type="text" id="specialCommands" name="specialCommands" class="form-control" placeholder="特殊要求" value="${bean.specialCommands}" disabled="disabled"/>
					</div>


				</div>

				<div class="row bottom10">
					<div class="col-md-2">
						<label>来源页面</label>
						<input type="text" name="channelUrl" id="channelUrl" placeholder="来源页面网页地址" class="form-control" value="${bean.channelUrl}" disabled="disabled">
					</div>
					<div class="col-md-2">
						<label>物品送达方式</label><br/>
						<select id="deliverType" disabled="disabled">
							<option value="客户上门" <c:if test="${bean.deliverType == '客户上门'}">selected="selected"</c:if>>客户上门</option>
							<option value="快递" <c:if test="${bean.deliverType == '快递'}">selected="selected"</c:if>>快递</option>
							<option value="上门取件" <c:if test="${bean.deliverType == '上门取件'}">selected="selected"</c:if>>上门取件</option>
						</select>
					</div>

					<div class="col-md-2">
						<label>快递单号</label>
						<input type="text" id="expressNo" name="expressNo" placeholder="快递单号" class="form-control" value="${bean.expressNo}" disabled="disabled">
					</div>
					<div class="col-md-6">
						<label>备注</label>
						<input type="text" id="commentId" name="comment" placeholder="备注" class="form-control" value="${bean.comment}" disabled="disabled">
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
				</div>
			</div>
		</div>
	</div>
</div>

</body>
<script src="${ctx}/static/require/require.js"></script>
<script src="${ctx}/static/require/require.config.js"></script>
<script src="${ctx}/module-js/order/webuploader_edit.js"></script>
</html>