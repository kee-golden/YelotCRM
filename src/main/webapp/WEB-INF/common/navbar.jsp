<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target=".navbar-inverse-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="javascript:void(0)">代码生成</a>
        </div>
        <div class="navbar-collapse collapse navbar-inverse-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="javascript:void(0)">项目</a></li>
                <li class=""><a href="${ctx}/admin/list">用户</a></li>
                <li class=""><a href="${ctx}/role/list">角色</a></li>
                <li class=""><a href="${ctx}/menu/list">菜单</a></li>
            </ul>
        </div>
    </div>
</div>
