/**
 * Created by kee on 17/7/13.
 */
var orderNo = "";
require([ 'jquery', 'yaya', 'datatables.net' ], function($, yaya) {
	var $JOrderList = $('#J_orderList');
	var phone = $("#phone").val();
	// 初始化
    var table = $JOrderList.DataTable({
        'scrollX': true,
        'processing': true,
        'searching': false,
        'lengthChange': false,
        'sort': false,
        'serverSide': true,
        'lengthMenu': [10, 20, 50, 100],
        'ajax':{
            'url':ctx + '/repair-order/queryConsultOrderList',
			'method': 'get',
            'data': function (d) {
                d.extra_search = $('#keywords').val();
            }
        },
    	'columns': [
	            {
			    "sClass": "text-center",
			    "data": "id",//行单选框
			    "render": function (data, row) {
				      return '<input name="checkchild" type="radio" class="checkchild" value="' + data + '" /><input type="hidden" id="consultOrderNoTmp" value="'+row['orderNo']+'">';
				    },"bSortable": false
                },
    			{'data' : 'orderNo', 'render' : function(data){
    				if(data != null){
        				return '<a href="/repair-order/consultOrderdetail?consultOrderNo='  +data + '" target="_blank" data-id="' + data + '" class="J_consultOrderDetail">'+data+'</a>&nbsp;&nbsp;'
    				} else {
    					return null;
    				}
    			}},
    			{'data' : 'customerName'},
    			{'data' : 'customerPhone'},
                {'data' : 'wechatNo'},
                {'data' : 'firstCategoryName'},
                {'data' : 'bookShopName'},
                {'data' : 'repairCommands'},
                {'data' : 'status',
                    'render':function (data) {
                        if(data == 1) {
                            return '进行中';
                        }else if(data == 3) {
                            return '已接单';
                        }else{
                            return '未接单';
                        }
                    }
    			},
    			{'data' : 'createUserName'},
    			{'data' : 'createAt'}
    			],
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
    
    $("#J_orderSerch").click(function(){
        table.draw();
    });

    //选中行事件
    $("#J_orderList tbody").on("click","tr",function(){
        var check = $(this).find(".checkchild").prop("checked");
        orderNo = this.children[1].innerText;
        if(check && check==true){
            $(this).find('.checkchild').prop("checked",false);
        }else{
            $(this).find('.checkchild').prop("checked",true);
            return $(".checkchild:checked").val();
        }
    });
});

var callbackdata = function () {
    return $(".checkchild:checked").val() + "|" + orderNo;
};