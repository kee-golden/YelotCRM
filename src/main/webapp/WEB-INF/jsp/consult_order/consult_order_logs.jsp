<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
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
					<th class="col-md-2">序号</th>
					<th class="col-md-2">操作人姓名</th>
					<th class="col-md-2">操作人电话</th>
					<th class="col-md-4">创建时间</th>
				</tr>

				</thead>
				<tbody>
				<c:forEach items="${consultOrderOperatorsList}" var="item" varStatus="status">
					<tr>
						<th>${status.index + 1}</th>
						<th>${item.userName}</th>
						<th>${item.userPhone}</th>
						<th><fmt:formatDate value="${item.createAt}" pattern="yyyy-MM-dd HH:mm"/></th>

					</tr>
				</c:forEach>
				</tbody>
			</table>

		</div>
	</div>
</div>
