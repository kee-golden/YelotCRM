<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/common/taglibs.jsp"%>


<script>
		var ctx = '${ctx}';
		var imagesPath = '${imagesPath}'
        var imagesJson = eval('${imagesJson}');
//	console.log(jsonObj);
        var province = '${bean.province}';
        var city = '${bean.city}';



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
						<label><span style="color: red"></span>用户名</label>
						<input type="text" placeholder="请输入用户名" class="form-control" name="customerName" id="customerName"
							   autocomplete="off" value="${bean.customerName}">
					</div>

					<div class="col-md-2">
						<label><span style="color: red"></span>电话号码</label>
						<input type="text" placeholder="请输入手机号或固话" class="form-control" name="customerPhone" id="customerPhone" value="${bean.customerPhone}">
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
							<select class="prov" id="province" name="province"></select>
							<select class="city" id="city"  name="city"></select>
						</div>

					</div>
					<div class="col-md-4">
						<label>详细地址</label> <input type="text" placeholder="请输入详细地址" class="form-control" name="customerAddress" id="customerAddress" value="${bean.customerAddress}"
					>
					</div>
				</div>
				<div class="row bottom10">
					<div class="col-md-2">
						<label>客户来源</label>
						<select class="form-control" id="channelSource" name="channelSource">
							<c:forEach items="${channelSourceList}" var="item">
								<option value="${item.id}" <c:if test="${bean.channelSource == item.id}">selected="selected"</c:if>>${item.name}</option>
							</c:forEach>
                            <%--<option value="1" <c:if test="${bean.channelSource == 1}">selected="selected"</c:if>>udesk</option>--%>
                            <%--<option value="2" <c:if test="${bean.channelSource == 2}">selected="selected"</c:if>>北京7860</option>--%>
                            <%--<option value="3" <c:if test="${bean.channelSource == 3}">selected="selected"</c:if>>上海5588</option>--%>
                            <%--<option value="4" <c:if test="${bean.channelSource == 4}">selected="selected"</c:if>>总机400</option>--%>
                            <%--<option value="5" <c:if test="${bean.channelSource == 5}">selected="selected"</c:if>>杭州3123</option>--%>
                            <%--<option value="6" <c:if test="${bean.channelSource == 6}">selected="selected"</c:if>>上门</option>--%>
                            <%--<option value="7" <c:if test="${bean.channelSource == 7}">selected="selected"</c:if>>微博</option>--%>
                            <%--<option value="8" <c:if test="${bean.channelSource == 8}">selected="selected"</c:if>>微信</option>--%>
                            <%--<option value="9" <c:if test="${bean.channelSource == 9}">selected="selected"</c:if>>淘宝C店</option>--%>
                            <%--<option value="10" <c:if test="${bean.channelSource == 10}">selected="selected"</c:if>>淘宝B店</option>--%>
                            <%--<option value="11" <c:if test="${bean.channelSource == 11}">selected="selected"</c:if>>大众点评</option>--%>
                            <%--<option value="12" <c:if test="${bean.channelSource == 12}">selected="selected"</c:if>>老客介绍</option>--%>
                            <%--<option value="13" <c:if test="${bean.channelSource == 13}">selected="selected"</c:if>>品牌介绍</option>--%>
                            <%--<option value="14" <c:if test="${bean.channelSource == 14}">selected="selected"</c:if>>员工介绍</option>--%>
                            <%--<option value="15" <c:if test="${bean.channelSource == 15}">selected="selected"</c:if>>老板介绍</option>--%>
                            <%--<option value="16" <c:if test="${bean.channelSource == 16}">selected="selected"</c:if>>官网留言</option>--%>
                            <%--<option value="17" <c:if test="${bean.channelSource == 17}">selected="selected"</c:if>>论坛、博客</option>--%>
                            <%--<option value="18" <c:if test="${bean.channelSource == 18}">selected="selected"</c:if>>其他</option>--%>
                        </select>
					</div>
				</div>
			</div>

		</div>
		<div class="container top_con" style="width: 100%; min-width: 1000px">
			<h6>
				<span class="glyphicon glyphicon-folder-open work"></span>客户更多信息<i class="pull-right iconfont ">&#xe658;</i>
				<div class="clearfix"></div>
			</h6>
			<div id="customerMoreContainer" class="col-md-12 b-r">
				<div class="row bottom10">
					<div class="col-md-2">
						<label>微信号</label>
						<input type="text" name="wechatNo" id="wechatNo" placeholder="微信ID号" class="form-control" value="${bean.wechatNo}">
					</div>
					<div class="col-md-2">
						<label>微信昵称</label>
						<input type="text" name="wechatNickname" id="wechatNickname" placeholder="微信昵称" class="form-control" value="${bean.wechatNickname}">
					</div>
					<div class="col-md-2">
						<label>设备号</label>
						<input type="text" name="deviceNo" id="deviceNo" placeholder="设备号" class="form-control" value="${bean.deviceNo}">
					</div>
					<div class="col-md-2">
						<label>阿里旺旺账号</label>
						<input type="text" name="aliNo" id="aliNo" placeholder="阿里旺旺账号" class="form-control" value="${bean.aliNo}">
					</div>
					<div class="col-md-2">
						<label>阿里旺旺昵称</label>
						<input type="text" name="aliNickname" id="aliNickname" placeholder="阿里旺旺昵称" class="form-control" value="${bean.aliNickname}">
					</div>
				</div>
				<div class="row bottom10">
					<div class="col-md-2">
						<label>微博账号</label>
						<input type="text" name="blogNo" id="blogNo" placeholder="微博账号" class="form-control" value="${bean.blogNo}">
					</div>
					<div class="col-md-2">
						<label>微博昵称</label>
						<input type="text" name="blogNickname" id="blogNickname" placeholder="微博昵称" class="form-control" value="${bean.blogNickname}">
					</div>
					<div class="col-md-2">
						<label>QQ账号</label>
						<input type="text" name="QQNo" id="QQNo" placeholder="QQ账号" class="form-control" value="${bean.QQNo}">
					</div>
					<div class="col-md-2">
						<label>QQ昵称</label>
						<input type="text" name="QQNickname" id="QQNickname" placeholder="QQ昵称" class="form-control" value="${bean.QQNickname}">
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
	</div>
</div>

<%--<script src="${ctx}/module-js/consult_order/consult_order_edit.js?t=123"></script>--%>
<script src="${ctx}/module-js/consult_order/webuploader_edit.js"></script>
<script>
    require(['jquery','yaya','cityselect','dateTimePicker'], function ($, yaya,cityselect) {

        var jsonObj = eval(${categoryJson});//转化为json 对象
        var firstCategory = '${bean.firstCategoryName}';
        var secondCategory = '${bean.secondCategoryName}';
        $("#category").citySelect({
            url:jsonObj,
            prov:firstCategory,
            city:secondCategory,
            nodata:"none"
        });

        $("#prov_city").citySelect({
            url:"/static/cityselect/js/city.min.js",
            prov:province,
            city:city,
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

    });

</script>




