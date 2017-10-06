<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<div class="ibox float-e-margins">
    <div class="ibox-content">
        <div class="row">
            <form role="form" id="J_userForm">
                <input type="hidden" name="id" value="${bean.id}">

                <c:if test="${empty bean.id}">
                    <div class="col-sm-6 b-r">
                        <h3 class="m-t-none m-b">基础信息</h3>
                        <div class="form-group">
                            <label><span style="color: red">*</span>用户名</label>
                            <input type="text" placeholder="请输入用户名" class="form-control" name="name" id="J_name"
                                   value="${bean.name}" autocomplete="off">
                        </div>
                        <%--<div class="form-group">--%>
                            <%--<label><span style="color: red">*</span>密码</label>--%>
                            <%--<input type="password" placeholder="请输入密码" class="form-control" name="password" id="J_password"--%>
                                   <%--value="${bean.password}" autocomplete="off">--%>
                        <%--</div>--%>
                        <div class="form-group">
                            <label><span style="color: red">*</span>姓名</label>
                            <input type="text" placeholder="请输入姓名" class="form-control" name="realname"
                                   value="${bean.realname}">
                        </div>
                        <div class="form-group">
                            <label><span style="color: red">*</span>手机号</label>
                            <input type="text" placeholder="请输入手机号" class="form-control" name="phone"
                                   value="${bean.phone}">
                        </div>
                        <div class="form-group">
                            <label>门店</label>
                            <select name="shopId" id="shopId" class="from-control">
                                <c:forEach items="${shopList}" var="shop">
                                    <option value="${shop.id}"
                                            <c:if test="${bean.shop_id == shop.id}">
                                                selected="selected"
                                            </c:if>
                                    >${shop.name}</option>
                                </c:forEach>
                                <%--<option value="">不限</option>--%>
                            </select>
                        </div>
                        <div class="check-box">
                            <label>
                            <input type="checkbox" name="is_repair_man" value="1">维修人员
                            </label>
                        </div>
                        <div class="check-box">
                            <label>
                            <input type="checkbox"  name="is_readonly"  value="1">只读权限
                            </label>
                        </div>
                    </div>
                </c:if>
                <c:if test="${not empty bean.id}">
                    <div class="col-sm-6 b-r">
                        <h3 class="m-t-none m-b">基础信息</h3>
                        <div class="form-group">
                            <label>用户名</label>
                            <input type="text" placeholder="请输入用户名" name="name" class="form-control" value="${bean.name}"
                                   readonly>
                        </div>
                        <div class="form-group">
                            <label>姓名</label>
                            <input type="text" placeholder="请输入姓名" name="realname" class="form-control" value="${bean.realname}"
                                   >
                        </div>
                        <div class="form-group">
                            <label>手机号</label>
                            <input type="text" placeholder="请输入手机号" name="phone" class="form-control" value="${bean.phone}"
                                   >
                        </div>
                        <div class="form-group">
                            <label>门店</label>
                            <select name="shopId" class="from-control">
                                <c:forEach items="${shopList}" var="shop">
                                    <option value="${shop.id}"
                                            <c:if test="${bean.shop_id == shop.id}">
                                                selected="selected"
                                            </c:if>
                                    >${shop.name}</option>
                                </c:forEach>
                                <%--<option value="">不限</option>--%>
                            </select>
                        </div>
                        <div class="check-box">
                                <%--<label>是否为维修人员</label>--%>
                            <label>
                                <input type="checkbox" name="is_repair_man"  value="1" <c:if test="${bean.is_repair_man eq 1}">checked="checked"</c:if>
                                >维修人员
                            </label>
                        </div>
                        <div class="check-box">
                            <label>
                                <input type="checkbox"  name="is_readonly"  value="1" <c:if test="${bean.is_readonly eq 1}">checked="checked"</c:if>
                                >只读权限
                            </label>
                        </div>
                    </div>
                </c:if>

                <div class="col-sm-6">
                    <h3 class="m-t-none m-b">角色选择</h3>
                    <div style="height: 300px;overflow-y: auto;">
                        <c:forEach items="${roles}" var="item" varStatus="s">
                            <div class="check-box">
                                <label>
                                    <input type="checkbox" name="role" value="${item.id}"
                                           <c:if test="${not empty item.userId}">checked="checked"</c:if>> ${item.name}
                                </label>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </form>

        </div>
    </div>
</div>