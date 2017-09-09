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
        'lengthMenu': [10, 20, 50, 100],
        'ajax':{
            'url':ctx + '/consult-order/query?type=all',
			'method': 'get',
            'data': function (d) {
                d.extra_search = $('#keywords').val();
            }
        },
    	'columns': [
    			{'data' : 'customerName'},
    			{'data' : 'customerPhone'},
                {'data' : 'wechatNo'},
                {'data' : 'firstCategoryName'},
                {'data' : 'bookShopName'},
                {'data' : 'repairCommands'},
                {'data' : 'status',
                    'render':function (data) {
                        if(data == 2) {
                            return '进行中';
                        } if(data == 1) {
                            return '已接单';
                        } else {
                            return '未接单';
                        }
                    }
    			},
    			{'data' : 'createUserName'},
    			{'data' : 'createAt'},
    			{'data' : 'id', 'render' : function(data, type,full, meta) {
    				return '<a href='+ctx+'/consult-order/detail?id='+data+' "data-id="' + data + '" class="J_detail"><i class="fa fa-edit" aria-hidden="true"></i>编辑</a>&nbsp;&nbsp;'
    				+ '<a href="javascript:;;" data-id="' + data + '" class="J_check"><i class="fa fa-edit" aria-hidden="true"></i>受理处理</a>&nbsp;&nbsp;' +
                        '<a href="javascript:;;" data-id="' + data + '" class="J_logs"><i class="fa fa-edit" aria-hidden="true"></i>操作日志</a>' ;
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

    $JOrderList.on('click', '.J_logs', function () {
        console.log("logs,,,");

        $.ajax({
            url:'/consult-order/logs',
            method:'post',
            dataType:'html',
            data:{
                orderId:$(this).data("id"),
            },
            success: function (str) {
                yaya.layer.open({
                    type: 1,
                    title: '操作日志',
                    content: str, //注意，如果str是object，那么需要字符拼接。
                    area: '500px',
                    shadeClose: true,
                    success: function (layero, index) {


                    },
                    yes: function (index) {


                    }

                });
            }

        });


    });



});
