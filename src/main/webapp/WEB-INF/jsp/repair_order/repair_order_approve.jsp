<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<div class="row">
			<c:if test="${orderStatus == 2 || orderStatus == 4 || orderStatus == 12 || orderStatus == 16 || orderStatus==20 ||
			orderStatus == 24 || orderStatus == 28 || orderStatus == 32}">


			<form role="form" id="J_checkForm">
				<input type="hidden" name="id" id="orderId" value="${orderId}">
				<div class="row">
					<%--<label class="col-md-2">备注：</label>--%>
					<textarea id="comment" rows="3" cols="20" class="col-md-11" style="margin-left: 30px" placeholder="输入审批备注"></textarea>
					<%--<input type="textarea" class="col-md-10" name="comment" />--%>
				</div>

				<div class="layui-layer-btn"><a class="btn-group" id="approveBtn">通过</a><a class="btn-group" id="refuseBtn">拒绝</a></div>
			</form>

			</c:if>

			<table id="J_orderOperatorsList" class="table table-striped table-bordered table-hover">
				<thead>
				<tr>
					<th class="col-md-2">订单号</th>
					<th class="col-md-2">操作人姓名</th>
					<th>操作备注</th>
					<th>操作方式</th>
					<th>订单状态</th>
					<th>创建时间</th>
				</tr>

				</thead>
				<tbody>
				<c:forEach items="${repairOrderOperatorsList}" var="item">
					<tr>
						<th>${item.orderNo}</th>
						<th>${item.approveUserName}</th>
						<th>${item.operator_comment}</th>
						<th>${item.order_status}</th>
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

						<th><fmt:formatDate value="${item.createAt}" pattern="yyyy-MM-dd HH:mm:ss"/></th>


					</tr>
				</c:forEach>
				</tbody>
			</table>

		</div>
	</div>
</div>
<script>
    require([ 'jquery', 'yaya', 'datatables.net' ], function($, yaya) {
        $('#approveBtn').click(function () {

            $.ajax({
				url:ctx+"/repair-order-operators/approve",
				type:'json',
				method:'POST',
				data:{
				    orderId:$('#orderId').val(),
					comment:$('#comment').val(),

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
