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
        <form id="searchFrom" style="margin: 5px;">
            <div class="col-md-3">
                <h4 style="float: left;">开始时间：</h4>
                <input type="text" style="float: left;" id="startDate" class="pickupDate">
            </div>
            <div class="col-md-3">
                <h4 style="float: left;">结束时间：</h4>
                <input type="text" style="float: left;" id="endDate" class="pickupDate">
            </div>
            <div class="col-md-2">
                <h4 style="float: left;">门店：</h4>
                <select name="shopId" id="shopId" class="from-control">
                    <option value="">全部</option>
                    <c:forEach items="${shopList}" var="shop">
                        <option value="${shop.id}">${shop.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-2">
                <h4 style="float: left;">分类：</h4>
                <select name="category" id="category" class="from-control">
                    <option value="">全部</option>
                    <c:forEach items="${categoryList}" var="category">
                        <option value="${category.name}">${category.name}</option>
                    </c:forEach>

                </select>
            </div>
            <div class="col-md-2">
                <h4 style="float: left;">方式：</h4>
                <select name="type" id="type" class="from-control">
                    <option value="Day">天</option>
                    <option value="Week">周</option>
                    <option value="Month">月</option>
                </select>
            </div>

        </form>
        <div class="clearfix"></div>
        <br>
        <div align="center">
            <input type="submit" id="search" value="查询">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="submit" id="exportExcel" value="EXCEL导出">
        </div>
    </div>
    <div class="clearfix"></div>
    <table id="J_userList" class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <th>用户名</th>
            <th>姓名</th>
            <th>手机号</th>
            <th>门店</th>
            <th>有效咨询总量</th>
        </tr>
        </thead>
    </table>

</div>
<script>
    require(['jquery','yaya','dateTimePicker'], function ($, yaya,dateTimePicker) {
        $('.pickupDate').datetimepicker({
            lang: 'ch',
            format: 'Y-m-d H:m',
            timepicker:true
        });


        // 初始化
        var $JOrderList = $('#J_userList');

        var table = $JOrderList.DataTable({
            'scrollX': true,
            'processing': true,
            'searching': false,
            'lengthChange': false,
            'sort': false,
            'serverSide': true,
            'lengthMenu': [10, 20, 50, 100],
            'ajax':{
                'url':ctx + '/consult-statistics/person-query',
                'method': 'get',
                'data': function (d) {
                    d.startDate = $('#startDate').val();
                    d.endDate = $('#endDate').val();
                    d.shopId = $('#shopId').val();
                    d.categoryName = $('#category').val();

                }
            },
            'columns': [
                {'data' : 'userName'},
                {'data' : 'realname'},
                {'data' : 'phone'},
                {'data' : 'shopName'},
                {'data' : 'totalCount'}],
            'language': {
                'lengthMenu': '每页显示 _MENU_ 条记录',
                'zeroRecords': '没有检索到数据',
                'info': '第 _START_ 至 _END_ 条数据 共 _TOTAL_ 条记录',
                'infoEmpty': '没有数据',
                'processing': '正在加载数据...',
                'paginate': {
                    'first': '首页',
                    'previous': '前页',
                    'next': '后页',
                    'last': '尾页'
                }
            }
        });

        $("#search").click(function(){
            table.draw();
        });

        $("#exportExcel").click(function(){
            var params = "";
            params += "&startDate=" + $('#startDate').val();
            params += "&endDate=" + $('#endDate').val();
            params += "&firstCategory=" + $('#firstCategory').val();
            params += "&secondCategory=" + $('#secondCategory').val();
            params += "&shopId=" + $('#shopId').val();
            params += "&status=" + $('#status').val();
            params += "&typeName=" + $('#typeName').val();

//            window.location = ctx + '/rpt-repair-order/exportExcel?' + params;
        });



    });
</script>

