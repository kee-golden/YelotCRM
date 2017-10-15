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
                d.dateArea = $('#dateArea').val();
                d.shopId = $('#shopId').val();
                d.firstCategory = $('#firstCategory').val();
                d.secondCategory = $('#secondCategory').val();
                d.onLineUser = $('#onLineUser').val();
                d.shopUser = $('#shopUser').val();
                d.deliverType = $('#deliverType').val();
                d.customerType = $('#customerType').val();
                d.channelSource = $('#channelSource').val();
                d.status = $('#status').val() == null ? '' : $('#status').val().toString();
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
    
    $("#shopId").change(function(){
    	$.ajax({
	        url: ctx + '/rpt-repair-order/findUserByShopId',
	        method: 'get',
	        dataType: 'json',
	        data: {
	        	shopId: $("#shopId").val(),
	        },
	        success: function (data) {
	            $("#shopUser").empty();
	            $("#shopUser").append('<option value="">全部</option>');
	            for(var i = 0;i < data.length;i++){
	                var str = '<option value=\"'+data[i].id+'\">'+data[i].realname+'</option>';
	                $("#shopUser").append(str);
	            }
	        }
	    });
    });

    $("#exportExcel").click(function(){
    	var params = "";
		params += "&dateArea=" + $('#dateArea').val(); 
		params += "&shopId=" + $('#shopId').val(); 
		params += "&firstCategory=" + $('#firstCategory').val(); 
		params += "&secondCategory=" + $('#secondCategory').val(); 
		params += "&onLineUser=" + $('#onLineUser').val(); 
		params += "&shopUser=" + $('#shopUser').val(); 
		params += "&deliverType=" + $('#deliverType').val(); 
		params += "&customerType=" + $('#customerType').val(); 
		params += "&channelSource=" + $('#channelSource').val(); 
		params += "&status=" + ($('#status').val() == null ? '' : $('#status').val().toString()); 
		
		window.location = ctx + '/rpt-repair-order/exportExcel?' + params;
    });
})
