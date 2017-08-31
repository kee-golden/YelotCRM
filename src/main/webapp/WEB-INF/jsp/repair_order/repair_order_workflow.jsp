<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<div class="row">
			<form role="form" id="J_userForm">
				<input type="hidden" name="id" value="">
			</form>

			<div class="row">
				<label style="margin-left: 20px;">订单号：${orderNo}</label>
			</div>

			<table id="J_orderOperatorsList" class="table table-striped table-bordered table-hover">
				<thead>
				<tr>
					<th class="col-md-2">操作人姓名</th>
					<th class="col-md-6">操作备注</th>
					<th class="col-md-2">操作方式</th>
					<th class="col-md-2">创建时间</th>
				</tr>

				</thead>
				<tbody>
				<c:forEach items="${repairOrderOperatorsList}" var="item">
					<tr>
						<th>${item.approveUserName}</th>
						<th>${item.operator_comment}</th>
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
