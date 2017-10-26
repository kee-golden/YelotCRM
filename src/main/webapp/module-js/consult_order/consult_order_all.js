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
            'url':ctx + '/consult-order/query',
			'method': 'get',
            'data': function (d) {
                d.startDate = $('#startDate').val();
                d.endDate = $('#endDate').val();
                d.createUserName = $('#createUserName').val();
                d.extra_search = $('#keywords').val();
            }
        },
    	'columns': [
    			{'data' : 'orderNo'},
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
    			{'data' : 'createAt'},
    			{'data' : 'id', 'render' : function(data, type,full, meta) {
    				return '<a href="javascript:;;" data-id="' + data + '" class="J_edit"><i class="fa fa-edit" aria-hidden="true"></i>编辑</a>&nbsp;&nbsp;'
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

    //<a href='+ctx+'/consult-order/detail?id='+data+

    $JOrderList.on('click', '.J_edit', function () {
        $.ajax({
            url:ctx+'/consult-order/detail',
            method:'post',
            dataType:'html',
            data:{
                id:$(this).data("id"),
            },
            success: function (str) {
                yaya.layer.open({
                    type: 1,
                    title: '咨询单编辑',
                    content: str, //注意，如果str是object，那么需要字符拼接。
                    area: '1100px',
                    shadeClose: true,
                    btn: ['保存','取消'],
                    success: function (layero, index) {



                    },
                    yes: function (index) {// 保存按钮
                    	
                    	var customerName = $("#customerName").val();
                        var customerPhone = $('#customerPhone').val();
                        var customerSex = $('#customerSex').val();
                        var customerAddress = $('#customerAddress').val();
                        var wechatNo = $('#wechatNo').val();
                        var wechatNickname = $('#wechatNickname').val();
                        var channelSource = $('#channelSource').val();
                        var repairCommands = $('#repairCommands').val();
                        var province = $('#province').val();
                        var city = $('#city').val();
                        var brandId = $('#brand').val();
                        var firstCategory = $('#firstCategory').val();
                        var secondCategory = $('#secondCategory').val();
                        var channelUrl = $('#channelUrl').val();
                        var keywords = $('#keywords').val();
                        var priceLimit = $('#priceLimit').val();
                        var timeLimit = $('#timeLimit').val();
                        var qulityLimit = $('#qulityLimit').val();
                        var specialCommands = $('#specialCommands').val();
                        var vistorDate = $('#vistorAt').val();
                        var imagesPath = $('.filelist').data('path');
                        var expressNo = $('#expressNo').val();
                        var deliverType = $('#deliverType').val();
                        var comment = $('#commentId').val();
                        var bookShopId = $('#bookShopId').val();
                        var aliNo = $('#aliNo').val();
                        var aliNickname = $('#aliNickname').val();
                        var blogNo = $('#blogNo').val();
                        var blogNickname = $('#blogNickname').val();
                        var QQNo = $('#QQNo').val();
                        var QQNickname = $('#QQNickname').val();

            			if(customerPhone == '' && wechatNo == '' && wechatNickname == '' && aliNo == '' && aliNickname == '' && blogNo == '' && blogNickname == '' 
            					&& QQNo == '' && QQNickname == ''){
                            yaya.layer.msg('手机号、微信号、微信昵称、阿里旺旺账号、阿里旺旺昵称、微博账号、微博昵称、QQ账号、QQ昵称，至少要填写一项');
                            return;
            			}else if(customerPhone != '' && !checkIsMobile(customerPhone)){
            			    yaya.layer.msg("手机号输入不正确");
            			    return;
            			}
                    	
                        $.ajax({
                            url:'/consult-order/update',
                            method: 'post',
                            dataType: 'json',
                            data: {
                            	id:$('#orderId').data("id"),
            				    customerName: customerName,
                                customerPhone: customerPhone,
                                customerSex: customerSex,
                                customerAddress: customerAddress,
                                wechatNo:wechatNo,
                                wechatNickname:wechatNickname,
                                channelSource:channelSource,
                                repairCommands:repairCommands,
                                province:province,
                                city:city,
                                brandId:brandId,
                                firstCategoryName:firstCategory,
                                secondCategoryName:secondCategory,
                                channelUrl:channelUrl,
                                keywords:keywords,
                                priceLimit:priceLimit,
                                timeLimit:timeLimit,
                                qulityLimit:qulityLimit,
                                specialCommands:specialCommands,
                                imagesPath:imagesPath,
                                vistorDate:vistorDate,
                                expressNo:expressNo,
                                bookShopId:bookShopId,
                                deliverType:deliverType,
                                comment:comment,
                                aliNo:aliNo,
                                aliNickname:aliNickname,
                                blogNo:blogNo,
                                blogNickname:blogNickname,
                                QQNo:QQNo,
                                QQNickname:QQNickname

                            },
                            success: function (data) {
                                if(data.code == 1200){
                                    yaya.layer.msg("修改成功");
                                    setTimeout(function () {
                                        window.location.href = ctx+'/consult-order/alllist';
                                    },1000);

                                }

                            }
                        });

                    }

                });
            }

        });

    });

    $JOrderList.on('click', '.J_logs', function () {

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

    $JOrderList.on('click', '.J_check', function () {
        $.ajax({
            url:'/consult-order/to-check',
            method:'post',
            dataType:'html',
            data:{
                orderId:$(this).data("id"),
            },
            success: function (str) {
                yaya.layer.open({
                    type: 1,
                    title: '订单处理',
                    content: str, //注意，如果str是object，那么需要字符拼接。
                    area: '500px',
                    shadeClose: true,
                    btn: ['保存','取消'],
                    success: function (layero, index) {

                    },
                    yes: function (index) {
                        var isFlag = false;//检查是否电话号码没有填写，返回标志
                        if($('#orderStatus').val() == 3){// 已接收订单，电话号码为必填
                            $.ajax({
                                url:ctx+'/consult-order/check-phone',
                                method:'post',
                                dataType:'json',
                                async:false,// 必须要同步
                                data:{
                                    id:$('#checkOrderId').val(),
                                },
                                success:function (data) {
                                    if(data.code != 1200){
                                     yaya.layer.msg('已接收订单，客户手机号为必填项,请编辑中添加手机号！');
                                     isFlag = true;
                                    }
                                }
                            });
                        }
                        if(isFlag){//同步才可以获取该值
                            return;
                        }
                        $.ajax({
                            url:ctx+'/consult-order/update-status',
                            method:'post',
                            dataType:'json',
                            data:{
                                id:$("#checkOrderId").val(),
                                status:$("#orderStatus").val()
                            },
                            success:function (data) {
                                if(data.code == 1200){
                                    yaya.layer.msg("修改成功");
                                    setTimeout(function () {
                                        window.location.href = ctx+'/consult-order/alllist';
                                    },500);

                                }
                                yaya.layer.close(index);


                            }
                        })


                    }

                });
            }

        });

    });

    function checkIsMobile(value){
        var length = value.length;
        var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
        return (length == 11 && mobile.test(value));
    }


    });
