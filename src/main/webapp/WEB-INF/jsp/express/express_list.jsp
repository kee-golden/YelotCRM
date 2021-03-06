<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<style>
    .sear_icon{ font-size: 18px; color: #428bca; vertical-align: middle; padding: 0 3px; cursor: pointer;margin-top: -10px}
    #J_userList tr td{ position: relative; white-space: nowrap}
    #J_userList tr td .span1{ padding:0 15px; font-size: 12px; line-height: 18px; position: absolute; left: 50%; background: #FFFFFF;
        padding: 5px;z-index: 999; border-radius: 5px; border: 1px solid #e5e5e5; display: none}
    #J_userList{ width: 100%!important;}
    #J_userList tr th{ white-space: nowrap}
</style>

<div class="ibox-content">

    <div class="co_all">
        <div style="float: left">
            <a class="collapse-link" id="J_userAdd">
                <i class="fa fa-plus xbb"></i>添加
            </a>
        </div>
        <form id="searchFrom"style="margin-top: -20px">
            <div id="search_Big">
                <ul class="pull-left" id="otherTab">
                    <li><span >搜索：</span>
                        <input type="text" id="keywords" class="inpt_width" placeholder="输入物流单号">
                        <div class="clearfix"></div>
                    </li>
                </ul>
                <div class="pull-right search_right"><a class="searfor"> <i class="glyphicon glyphicon-search sear_icon sear_icon"></i></a></div>
                <div class="clearfix" ></div>

                <div class="line"></div><%--线--%>

            </div>
        </form>

        <div class="clearfix"></div>
    </div>
    <div class="clearfix"></div>

    <table id="J_userList" class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <th>快递名称</th>
            <th>快递单号</th>
            <th>接收人</th>
            <%--<th>接收人电话</th>--%>
            <th>发送人</th>
            <%--<th>发送人电话</th>--%>
            <th>类别</th>
            <th>付款方式</th>
            <th>付款金额</th>
            <th>保单号</th>
            <th>保单金额</th>
            <th>创建人</th>
            <th>创建时间</th>
            <th>操作</th>
        </tr>
        </thead>
    </table>

</div>


<script src="${ctx}/static/require/require.js"></script>
<script src="${ctx}/static/require/require.config.js"></script>
<script src="${ctx}/module-js/express/express.js"></script>
