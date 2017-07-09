<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<div class="ibox float-e-margins">
    <div class="ibox-content">
        <div class="row">
            <form class="form" id="J_passwordForm" style="margin-top: 10px;">
                <div class="col-sm-12">
                    <div class="form-group">
                        <input type="password" class="form-control" id="oldPsd" name="oldPsd"
                               placeholder="请输入原密码">
                    </div>

                </div>
                <div class="col-sm-12">
                    <div class="form-group">
                        <input type="password" class="form-control" id="newPsd" name="newPsd"
                               placeholder="请输入新密码">
                    </div>

                </div>
                <div class="col-sm-12">
                    <div class="form-group">
                        <input type="password" class="form-control" id="confirmPsd" name="confirmPsd"
                               placeholder="请确认新密码">
                    </div>

                </div>

            </form>

        </div>
    </div>
</div>
