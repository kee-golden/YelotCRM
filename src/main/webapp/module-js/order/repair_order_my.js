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

    // 查看详情
    $JOrderList.on('click', '.J_orderDetail', function () {
        $.ajax({
            url: ctx + '/repair-order/detail',
            data: {
                orderId: $(this).data('id')
            },
            method: 'get',
            dataType: 'html',
            success: function (str) {
                yaya.layer.open({
                    type: 1,
                    title: '订单详情',
                    content: str, //注意，如果str是object，那么需要字符拼接。
                    area: ['800px','600px'],
                    scrollbar:true,
                    shadeClose: true,
                    btn: ['客户单打印','内部单打印'],
                    success: function (layero, index) {


                    },
                    yes: function (index) {
//                    	alert(1);
	                    /*var newstr = document.all.item("repairOrderDetail").innerHTML;//document.getElementById('repairOrderDetail').innerHTML;;
	                    var oldstr = document.body.innerHTML;
	                    document.body.innerHTML = newstr;
	                    document.onload = function(){
	                    	window.print();
	                    	document.body.innerHTML = oldstr;
		                    window.location.reload();
	                    };*/
//	                    window.print();
//	                    document.body.innerHTML = oldstr;
//	                    window.location.reload();
//	                    return false;
	                    
	                    /*var t_img; // 定时器
	                    var isLoad = true; // 控制变量（判断图片是否 加载完成）
	                    isImgLoad(function(){//判断全部打印图片加载完成
	                    	var newstr = document.all.item("repairOrderDetail").innerHTML;
		                    document.body.innerHTML = newstr;
		                    window.print(); 
		                    window.location.reload();
	                                // 加载完成
	                            });
	                    //判断图片加载的函数
	                    function isImgLoad(callback){
	                        // 查找所有打印图，迭代处理
	                        $('.image').each(function(){
	                            // 找到为0就将isLoad设为false，并退出each
	                            if(this.height === 0){
	                                isLoad = false;
	                                return false;
	                            }
	                        });
	                        // 为true，没有发现为0的。加载完毕
	                        if(isLoad){
	                            clearTimeout(t_img); // 清除定时器
	                            // 回调函数
	                            callback();
	                        // 为false，因为找到了没有加载完成的图，将调用定时器递归
	                        }else{
	                            isLoad = true;
	                            t_img = setTimeout(function(){
	                                isImgLoad(callback); // 递归扫描
	                            },500); // 我这里设置的是500毫秒就扫描一次，可以自己调整
	                        }
	                    }*/
                    	
                    	
                    	/*<link href='/static/font-awesome/font-awesome.min.css' rel='stylesheet'>
                    	<link href='/static/dataTables/css/dataTables.bootstrap.min.css' rel='stylesheet'>
                    	<link href='/static/animate/animate.css' rel='stylesheet'>
                    	<link rel='stylesheet' href='/static/ztree/css/yayaStyle/zTreeStyle.css' type='text/css'>
                    	
                    	
                    	<link href='/static/dateTimePicker/css/jquery.datetimepicker.css' rel='stylesheet'>
                    	<link href='/static/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css' rel='stylesheet'>
                    	<link rel='stylesheet' href='/static/css/iconfont.css'/>
                    	<link href='/static/webuploader/webuploader.css' rel='stylesheet'>
                    	<link href='/static/customer/customer.css' rel='stylesheet'>
                    	<link href='/module-css/nav.css' rel='stylesheet'>*/
	                    
                    	var headstr = "<html><head><link href='/static/bootstrap/css/bootstrap.min.css' rel='stylesheet'><link href='/static/yaya/css/style.css' rel='stylesheet'><link href='/module-css/basic.css' rel='stylesheet'></head><body>";
                    	var footstr = "</body></html>";  
                    	var newstr = document.all.item("repairOrderDetail").innerHTML;
                        printWindow = window.open();
                        printWindow.document.write(headstr + newstr + footstr);
                        return false;
                    }

                });
            },
            error: function () {

            }
        });
    });
    
    // 审批流程
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
