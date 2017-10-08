/**
 * @author xyzloveabc
 * @2017年9月13日
 */
require([ 'jquery', 'yaya', 'selector2','datatables.net' ], function($, yaya, selector2) {

    $("#status").select2({
        tags: true,
    });
    
	var $JOrderList = $('#J_orderList');
	// 初始化
    var table = $JOrderList.DataTable({
        'scrollX': true,
        'processing': true,
        'searching': false,
        'lengthChange': false,
        'sort': false,
        'serverSide': true,
        'serverSide': true,
        'lengthMenu': [10, 20, 50, 100],
        'ajax':{
            'url':ctx + '/rpt-repair-order/query',
			'method': 'get',
            'data': function (d) {
                d.startDate = $('#startDate').val();
                d.endDate = $('#endDate').val();
                d.firstCategory = $('#firstCategory').val();
                d.secondCategory = $('#secondCategory').val();
                d.shopId = $('#shopId').val();
                d.customerType = $('#customerType').val();
                d.status = $('#status').val() == null ? '' : $('#status').val().toString();
                d.typeName = $('#typeName').val();
            }
        },
    	'columns': [
    			{'data' : 'orderNo'},
    			{'data' : 'customerName'},
    			{'data' : 'customerPhone'},
    			{'data' : 'firstCategoryName'},
    			{'data' : 'secondCategoryName'},
    			{'data' : 'shopName'},
    			{'data' : 'serviceItemNames'},
    			{'data' : 'statusName'},
    			{'data' : 'createUserName'},
    			{'data' : 'createAt'}],
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
		
		window.location = ctx + '/rpt-repair-order/exportExcel?' + params;
    });
})
