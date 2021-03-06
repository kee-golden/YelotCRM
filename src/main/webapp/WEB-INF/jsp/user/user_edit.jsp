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
                            <select name="shopId" id="shopId" class="form-control">
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
                        <div class="form-group">
                            <label>用户类别</label>
                            <select name="user_type" id="user_type" class="from-control">
                                    <option value="1">门店客服</option>
                                    <option value="2">在线客服</option>
                                    <option value="3">维修人员</option>
                                    <option value="4">管理人员</option>
                                    <option value="5">质检人员</option>
                                    <option value="6">仓管人员</option>
                                    <option value="7">其他</option>
                            </select>
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
                        <div class="form-group">
                            <label>用户类别</label>
                            <select name="user_type" id="user_type" class="from-control">
                                <option value="1" <c:if test="${bean.user_type == 1}">selected="selected"</c:if>>门店客服</option>
                                <option value="2" <c:if test="${bean.user_type == 2}">selected="selected"</c:if>>在线客服</option>
                                <option value="3" <c:if test="${bean.user_type == 3}">selected="selected"</c:if>>维修人员</option>
                                <option value="4" <c:if test="${bean.user_type == 4}">selected="selected"</c:if>>管理人员</option>
                                <option value="4" <c:if test="${bean.user_type == 5}">selected="selected"</c:if>>质检人员</option>
                                <option value="4" <c:if test="${bean.user_type == 6}">selected="selected"</c:if>>仓管人员</option>
                                <option value="5" <c:if test="${bean.user_type == 7}">selected="selected"</c:if>>其他</option>
                            </select>
                        </div>
                        <%--<div class="check-box">--%>
                                <%--&lt;%&ndash;<label>是否为维修人员</label>&ndash;%&gt;--%>
                            <%--<label>--%>
                                <%--<input type="checkbox" name="is_repair_man"  value="1" <c:if test="${bean.is_repair_man eq 1}">checked="checked"</c:if>--%>
                                <%-->维修人员--%>
                            <%--</label>--%>
                        <%--</div>--%>
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