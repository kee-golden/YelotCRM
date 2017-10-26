<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<style>
    .sear_icon{ font-size: 18px; color: #428bca; vertical-align: middle; padding: 0 3px; cursor: pointer;margin-top: -10px}
    #J_customerList tr td{ position: relative; white-space: nowrap}
    #J_customerList tr td .span1{ padding:0 15px; font-size: 12px; line-height: 18px; position: absolute; left: 50%; background: #FFFFFF;
        padding: 5px;z-index: 999; border-radius: 5px; border: 1px solid #e5e5e5; display: none}
    #J_customerList{ width: 100%!important;}
    #J_customerList tr th{ white-space: nowrap}
    .bottom10{margin-bottom: 10px;}
</style>

<div class="ibox-content">

    <div class="co_all">
        <div style="float: left">
            <a class="collapse-link" id="J_customerAdd">
                <i class="fa fa-plus xbb"></i>添加
            </a>
        </div>
        <form id="searchFrom"style="margin-top: -20px">
            <div id="search_Big">
                <ul class="pull-left" id="otherTab">
                    <li><span >搜索：</span>
                        <input type="text" id="keywords" class="inpt_width" placeholder="输入用户名">
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

    <table id="J_customerList" class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <th>手机号</th>
            <th>微信昵称</th>
            <th>会员号</th>
            <th>姓名</th>
            <th>兴趣爱好</th>
            <th>城市</th>
            <th>绑定时间</th>
        </tr>
        </thead>
    </table>

</div>


<script src="${ctx}/static/require/require.js"></script>
<script src="${ctx}/static/require/require.config.js"></script>
<script src="${ctx}/module-js/account/account.js"></script>
