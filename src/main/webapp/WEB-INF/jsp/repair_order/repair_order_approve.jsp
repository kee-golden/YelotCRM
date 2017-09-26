<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<div class="row" style="margin-left: 10px;">
			<label>订单号:${orderNo}</label>
			<label>订单状态:
				<c:if test="${orderStatus == 2}">客服提交，待客服主管审核</c:if>
				<c:if test="${orderStatus == 4}">客服主管已审核,待维修中心接收</c:if>
				<c:if test="${orderStatus == 12}">维修中心已接收，待初检</c:if>
				<c:if test="${orderStatus == 16}">初检已完成，待QC质检</c:if>
				<c:if test="${orderStatus == 17}">评估单初检已完成，待门店审核</c:if>
				<c:if test="${orderStatus == 20}">QC质检完成，待入库</c:if>
				<c:if test="${orderStatus == 24}">已入库，待出库</c:if>
				<c:if test="${orderStatus == 28}">已出库，待门店接收</c:if>
				<c:if test="${orderStatus == 32}">门店已接收，待客户收货</c:if>
				<c:if test="${orderStatus == 36}">门店已发货，客户收货确认</c:if>
				<c:if test="${orderStatus == 44}">评估单预检完成，待客服主管审核</c:if>
				<c:if test="${orderStatus == 48}">客服主管审核通过，待初检审核</c:if>
			</label>
		</div>
		<div class="row">
			<c:if test="${orderStatus == 2 || orderStatus == 4 || orderStatus == 12 || orderStatus == 16 || orderStatus == 17 || orderStatus==20 ||
			orderStatus == 24 || orderStatus == 28 || orderStatus == 32 || orderStatus== 36 || orderStatus == 44 || orderStatus==48}">

			<form role="form" id="J_checkForm">
				<input type="hidden" name="id" id="orderId" value="${orderId}">
				<c:if test="${orderStatus == 12}">
				<div class="row">
					<label style="margin-left: 30px">维修人员：
					</label><select id="repairUserId" name="repairUserId">
						<c:forEach items="${repairUserList}" var="item">
							<option value="${item.id}">${item.realname}</option>
						</c:forEach>
					</select>
					<label style="margin-left: 30px" >最迟完成时间：</label>
					<input type="text" id="repairLastAt">
				</div>
				</c:if>
				<div class="row">
					<textarea id="comment" rows="3" cols="20" class="col-md-11" style="margin-left: 30px" placeholder="输入审批备注"></textarea>
				</div>
				<div><span style="margin-left: 600px;"><a class="btn" id="approveBtn">通过</a><a class="btn" id="refuseBtn">拒绝</a></span></div>
			</form>

			</c:if>
			<c:if test="${orderStatus == 12 || orderStatus == 16}">
				<hr/>
				<div id="uploader">
					<div class="queueList">
						<div id="dndArea" class="placeholder">
							<div id="filePicker"></div>
							<p>或将照片拖到这里，单次最多可选10张</p>
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

			</c:if>

			<hr/>




			<table id="J_orderOperatorsList" class="table table-striped table-bordered table-hover">
				<thead>
				<tr>
					<%--<th class="col-md-2">订单号</th>--%>
					<th class="col-md-2">操作人姓名</th>
					<th class="col-md-2">操作备注</th>
					<th class="col-md-2">订单状态</th>
					<th class="col-md-2">操作方式</th>
					<th class="col-md-2">创建时间</th>
				</tr>

				</thead>
				<tbody>
				<c:forEach items="${repairOrderOperatorsList}" var="item">
					<tr>
						<%--<th>${item.orderNo}</th>--%>
						<th>${item.approveUserName}</th>
						<th>${item.operator_comment}</th>
						<th>
							<c:choose>
								<c:when test="${item.order_status == 2}">已提交</c:when>
								<c:when test="${item.order_status == 4}">门店已审核</c:when>
								<c:when test="${item.order_status == 12}">维修中心已分练</c:when>
								<c:when test="${item.order_status == 16}">维修中心已预检</c:when>
								<c:when test="${item.order_status == 20}">QC已审核</c:when>
								<c:when test="${item.order_status == 24}">入库已审核</c:when>
								<c:when test="${item.order_status == 28}">出库已审核</c:when>
								<c:when test="${item.order_status == 32}">门店已接收</c:when>
								<c:when test="${item.order_status == 36}">门店已发货</c:when>
								<c:when test="${item.order_status == 40}">订单已完成</c:when>
								<c:otherwise>订单已拒绝</c:otherwise>
							</c:choose>

						</th>
						<th>
							<c:if test="${item.operator_status == 1}">
								提交
							</c:if>
							<c:if test="${item.operator_status == 2}">
								审核通过
							</c:if>
							<c:if test="${item.operator_status == 3}">
								审核拒绝
							</c:if>
						</th>

						<th><fmt:formatDate value="${item.createAt}" pattern="yyyy-MM-dd HH:mm"/></th>
					</tr>
				</c:forEach>
				</tbody>
			</table>

		</div>
	</div>
</div>
<c:if test="${orderStatus == 12 || orderStatus == 16}">
	<script src="${ctx}/module-js/order/webuploader_app.js"></script>
</c:if>

<script>
    require([ 'jquery', 'yaya','webuploader', 'datatables.net','dateTimePicker' ], function($, yaya,WebUploader) {
    	
        $('#repairLastAt').datetimepicker({
            lang: 'ch',
            format: 'Y-m-d',
            timepicker:false
        });
    	
        // 设置显示位置
    	$(".xdsoft_datetimepicker").css("z-index", 20000000);
        
        $('#approveBtn').click(function () {

            var comment = $('#comment').val();
            if(comment == ''){
                yaya.layer.msg("备注不能为空！");
                return;
			}

            var path = $('.filelist').data("path");
            console.log(path);

            $.ajax({
				url:ctx+"/repair-order-operators/approve",
				type:'json',
				method:'POST',
				data:{
				    orderId:$('#orderId').val(),
					comment:$('#comment').val(),
					imagesPath:path,
					repairUserId:$('#repairUserId').val(),
					repairLastAt:$('#repairLastAt').val()
				},
				success:function (data) {
					if(data.code == 1200){
                        yaya.layer.msg("审核成功");
					    setTimeout(function () {
                            window.location.href = ctx+'/repair-order/checklist';
                        },1000);

					}
                }
			});


        });

        $('#refuseBtn').click(function () {

            yaya.layer.msg("refuse button click");

            var comment = $('#comment').val();
            if(comment == ''){
                yaya.layer.msg("备注不能为空！");
                return;
            }

            $.ajax({
                url:ctx+"/repair-order-operators/reject",
                type:'json',
                method:'POST',
                data:{
                    orderId:$('#orderId').val(),
                    comment:$('#comment').val(),

                },
                success:function (data) {
                    if(data.code == 1200){
                        yaya.layer.msg("审核拒绝成功");
                        setTimeout(function () {
                            window.location.href = ctx+'/repair-order/checklist';
                        },1000);

                    }
                }
            });


        });


    });
</script>
