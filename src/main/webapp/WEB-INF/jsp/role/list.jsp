<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>ITSS管理后台</title>

    <%@include file="/WEB-INF/common/static.jsp" %>

    <c:set var="PARENT_MENU_CODE" value="User_Manage"/>
    <c:set var="CHILD_MENU_CODE" value="Role_List"/>

    <script>
        var ctx = '${ctx}';
    </script>

</head>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<%--<div id="wrapper">--%>
    <%@include file="/WEB-INF/common/top_logo_nav.jsp" %>
    <%@include file="/WEB-INF/common/slideBar.jsp" %>

    <!--右侧部分开始-->
    <div id="page-wrapper" class="wrapper wrapper-content ">

        <div class="row animated fadeInRight" id="content-main">
            <div class="col-xs-12">

                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>角色
                        </h5>
                        <div class="ibox-tools">
                            <a class="collapse-link" id="J_roleAdd">
                                <i class="fa fa-plus"></i>添加
                            </a>
                            <%--<a class="collapse-link">--%>
                                <%--<i class="fa fa-close"></i>返回--%>
                            <%--</a>--%>
                        </div>
                    </div>
                    <div class="ibox-content">

                        <table id="J_roleList" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>角色</th>
                                <th>编码</th>
                                <th>修改时间</th>
                                <th>编辑</th>
                            </tr>
                            </thead>
                        </table>

                    </div>
                </div>

            </div>
        </div>
        <div class="footer">
            <div class="pull-right">
                &copy; 2015-2016 <a href="javascript:void(0);" target="_blank">无锡新世纪信息有限公司</a>
            </div>
        </div>
        <!--右侧部分结束-->

    </div>

    <script src="${ctx}/static/require/require.js"></script>
    <script src="${ctx}/static/require/require.config.js"></script>
    <script src="${ctx}/module-js/role/role.js"></script>



</body>

</html>
