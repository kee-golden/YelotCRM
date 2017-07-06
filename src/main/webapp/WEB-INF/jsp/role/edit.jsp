<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<div class="ibox float-e-margins">
    <div class="ibox-content">
        <div class="row">
            <form id="J_roleForm" role="form">
                <input type="hidden" name="id" id="id" value="${bean.id}">
                <div class="col-xs-6 ">
                    <h3 class="m-t-none m-b">基础信息</h3>
                    <div class="form-group">
                        <label>角色</label>
                        <input type="text" placeholder="请输入角色" name="name" id="name" class="form-control"
                               value="${bean.name}">
                    </div>
                    <div class="form-group">
                        <label>编码</label>
                        <input type="text" placeholder="请输入编码" name="code" id="code" class="form-control"
                               value="${bean.code}">
                    </div>
                </div>
                <div class="col-xs-6" >
                    <h3 class="m-t-none m-b">权限选择</h3>
                    <div class="ibox-content" style="height: 300px;overflow-y: auto;">
                        <ul id="J_privilegeTree" class="ztree"></ul>
                    </div>

                </div>
            </form>

        </div>
    </div>
</div>