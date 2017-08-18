<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<div class="row">
			<form role="form" id="J_userForm">
				<input type="hidden" name="id" value="">


			</form>

			<table id="J_orderOperatorsList" class="table table-striped table-bordered table-hover">
				<thead>
				<tr>
					<th class="col-md-2">订单号</th>
					<th class="col-md-2">提交人姓名</th>
					<th class="col-md-2">审核人姓名</th>
					<th>操作备注</th>
					<th>操作方式</th>
					<th>创建时间</th>
				</tr>

				</thead>
				<tbody>
				<c:forEach items="${repairOrderOperatorsList}" var="item">
					<tr>
						<th>${item.orderNo}</th>
						<th>${item.createUserName}</th>
						<th>${item.approveUserName}</th>
						<th>${item.operator_comment}</th>
						<th>${item.operator_status}</th>

						<th>${item.create_at}</th>


					</tr>
				</c:forEach>
				</tbody>
			</table>

		</div>
	</div>
</div>
