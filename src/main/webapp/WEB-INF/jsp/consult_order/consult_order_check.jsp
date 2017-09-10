<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<div class="row">
			<form role="form" id="J_userForm">
				<input type="hidden" name="id" id="checkOrderId" value="${bean.id}">
			</form>

			<div class="col-md-6">
				<label>咨询单状态变更</label><br/>
				<select id="orderStatus">
					<option value="1" <c:if test="${bean.status == 1}">selected="selected"</c:if>>正在进行</option>
					<option value="2" <c:if test="${bean.status == 2}">selected="selected"</c:if>>未接单</option>
					<option value="3" <c:if test="${bean.status == 3}">selected="selected"</c:if>>已接单</option>
				</select>
			</div>

		</div>
	</div>
</div>
