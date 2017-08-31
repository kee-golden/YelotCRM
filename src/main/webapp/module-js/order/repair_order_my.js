/**
 * Created by kee on 17/7/13.
 */
require([ 'jquery', 'yaya', 'datatables.net' ], function($, yaya) {
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
            'url':ctx + '/repair-order/query?type=my',
			'method': 'get',
            'data': function (d) {
                d.extra_search = $('#keywords').val();
            }
        },
    	'columns': [
    			{'data' : 'orderNo'},
    			{'data' : 'customerName'},
    			{'data' : 'customerPhone'},
    			{'data' : 'categoryName'},
    			{'data' : 'serviceItemNames'},
    			{'data' : 'status',
                    'render':function (data) {
                        if(data == 2) {
                            return '进行中';
                        } if(data == 1) {
                            return '已完成';
                        } else {
                            return '已取消'
                        }
                    }
    			},
    			{'data' : 'createAt'},
    			{'data' : 'id', 'render' : function(data, type,full, meta) {
    				return '<a href="javascript:;;" data-id="' + data + '" class="J_orderDetail"><i class="fa fa-edit" aria-hidden="true"></i>查看详情</a>&nbsp;&nbsp;'
    				+ '<a href="javascript:;;" data-id="' + data + '" class="J_workflow"><i class="fa fa-edit" aria-hidden="true"></i>审批流程</a>';
    			}
    			}],
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

    $JOrderList.on('click', '.J_workflow', function () {
        $.ajax({
            url: ctx + '/repair-order-operators/workflow',
            data: {
                orderId: $(this).data('id')
            },
            method: 'get',
            dataType: 'html',
            success: function (str) {
                yaya.layer.open({
                    type: 1,
                    title: '审批流程',
                    content: str, //注意，如果str是object，那么需要字符拼接。
                    area: '800px',
                    shadeClose: true,
                    success: function (layero, index) {


                    },
                    yes: function (index) {
                        var isValid = $("#J_userForm").valid();
                        if(!isValid){
                            return;
                        }

                    }

                });
            },
            error: function () {

            }
        });


    });


    });
