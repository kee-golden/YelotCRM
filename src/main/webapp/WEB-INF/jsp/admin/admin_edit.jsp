<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<div class="ibox float-e-margins">
    <div class="ibox-content">
        <div class="row">
            <form role="form" id="J_adminForm">
                <input type="hidden" name="id" value="${bean.id}">

                <c:if test="${empty bean.id}">
                    <div class="col-sm-6 b-r">
                        <h3 class="m-t-none m-b">基础信息</h3>
                        <div class="form-group">
                            <label><span style="color: red">*</span>用户名</label>
                            <input type="text" placeholder="请输入用户名" class="form-control" name="username" id="J_username"
                                   value="${bean.username}" autocomplete="off">
                        </div>
                        <div class="form-group">
                            <label><span style="color: red">*</span>密码</label>
                            <input type="password" placeholder="请输入密码" class="form-control" name="password" id="J_password"
                                   value="${bean.password}" autocomplete="off">
                        </div>
                        <div class="form-group">
                            <label><span style="color: red">*</span>姓名</label>
                            <input type="text" placeholder="请输入姓名" class="form-control" name="nickname"
                                   value="${bean.nickname}">
                        </div>
                        <div class="form-group">
                            <label><span style="color: red">*</span>手机号</label>
                            <input type="text" placeholder="请输入手机号" class="form-control" name="phone"
                                   value="${bean.phone}">
                        </div>
                        <div class="form-group">
                            <label>部门</label>
                            <select name="deptId" id="deptId" class="from-control">
                                <option value="">不限</option>
                                <c:forEach items="${departmentList}" var="department">
                                    <option value="${department.id}"
                                            <c:if test="${bean.department.id == department.id}">
                                                selected="selected"
                                            </c:if>
                                    >${department.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </c:if>
                <c:if test="${not empty bean.id}">
                    <div class="col-sm-6 b-r">
                        <h3 class="m-t-none m-b">基础信息</h3>
                        <div class="form-group">
                            <label>用户名</label>
                            <input type="text" placeholder="请输入用户名" name="username" class="form-control" value="${bean.username}"
                                   readonly>
                        </div>
                        <div class="form-group">
                            <label>姓名</label>
                            <input type="text" placeholder="请输入姓名" name="nickname" class="form-control" value="${bean.nickname}"
                                   >
                        </div>
                        <div class="form-group">
                            <label>手机号</label>
                            <input type="text" placeholder="请输入手机号" name="phone" class="form-control" value="${bean.phone}"
                                   >
                        </div>
                        <div class="form-group">
                            <label>部门</label>
                            <select name="deptId" class="from-control">
                                <option value="">不限</option>
                                <c:forEach items="${departmentList}" var="department">
                                    <option value="${department.id}"
                                            <c:if test="${bean.department.id == department.id}">
                                                selected="selected"
                                            </c:if>
                                    >${department.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </c:if>

                <div class="col-sm-6">
                    <h3 class="m-t-none m-b">角色选择</h3>
                    <div style="height: 300px;overflow-y: auto;">
                        <c:forEach items="${roles}" var="item" varStatus="s">
                            <div class="check-box">
                                <label>
                                    <input type="checkbox" name="role" value="${item.id}" <c:if test="${item.name eq '企业管理员'}">disabled="true"</c:if>
                                           <c:if test="${adminRoleMap[item.code]}">checked="checked"</c:if>> ${item.name}
                                </label>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </form>

        </div>
    </div>
</div>
