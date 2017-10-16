/**
 * Created by kee on 16/12/12.
 */
require(['jquery', 'yaya', 'datatables.net'], function ($, yaya) {

    // 手机号码验证
    jQuery.validator.addMethod("isMobile", function(value, element) {
        var length = value.length;
        var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, "请正确填写手机号码");

    var $JUserList = $('#J_userList');


    var table = $JUserList.DataTable({
        "scrollX": true,
        'processing': true,
        'searching': false,
        'lengthChange': false,
        'sort': false,
        'serverSide': true,
        'serverSide': true,
        'lengthMenu': [10, 20, 50, 100],
        'ajax':{
            'url':ctx + '/express/list-data',
            'data': function (d) {
                d.extra_search = $('#keywords').val();
            }
        },
        'columns': [
            {'data': 'expressName'},
            {'data': 'expressNo'},
            {'data': 'acceptPersonName'},
            // {'data': 'acceptPersonPhone'},
            {'data': 'sendPersonName'},
            // {'data': 'sendPersonPhone'},
            {'data': 'sendType',
                'render':function(data){
                    if(data == 1){
                        return "寄件";
                    }else if(data == 2){
                        return "收件";
                    }
                }
            },
            {'data': 'payType',
                'render':function(data){
                    if(data == 1){
                        return "寄付";
                    }else if(data == 2){
                        return "到付";
                    }
                }
            },
            {'data': 'payAmount'},
            {'data': 'insuranceNo'},
            {'data': 'insuranceAmount'},
            {'data': 'createUserName'},
            {'data': 'createAt'},
            {'data': 'id',
                'render': function (data, type, full, meta) {
                    return '<a href="javascript:;;" data-id="' + data + '" class="J_edit"><i class="fa fa-edit" aria-hidden="true"></i>编辑</a>&nbsp;&nbsp;' +
                        '<a href="javascript:;;" data-id="' + data + '" class="J_delete"><i class="fa fa-trash" aria-hidden="true"></i>删除</a>';
                }
            }
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
            },
            'search': '查询',
            searchPlaceholder: '请输入用户姓名'

        }

    });

    $('#keywords').on('keyup click', function () {
        table.draw();
    });

    $('#J_userAdd').click(function () {
        $.ajax({
            url: ctx + '/express/add',
            method: 'get',
            dataType: 'html',
            success: function (str) {
                yaya.layer.open({
                    type: 1,
                    title: '新建物流单',
                    content: str, //注意，如果str是object，那么需要字符拼接。
                    area: '550px',
                    shadeClose: true,
                    btn: ['保存'],
                    success: function (layer, index) {
                    	checkExpressForm();
                    },
                    yes: function (index) {
                        var isValid = $("#J_expressForm").valid();
                        if (isValid) {

                            $.ajax({
                                url: ctx + '/express/save',
                                data: $('#J_expressForm').serialize(),
                                method: 'post',
                                dataType: 'json',
                                success: function (data) {
                                	debugger
                                    console.log(data.code);
                                    if (data.code==1200) {
                                        table.draw();
                                        yaya.layer.close(index);

                                    }
                                    else {
                                        yaya.layer.msg(data.data.data);

                                    }
                                },
                                error: function (data) {
                                    yaya.layer.msg(data.data.data);

                                }
                            });
                        }

                    }

                });
            },
            error: function () {

            }
        });
    })
    ;

    function checkExpressForm(expressName) {
        yaya.validate({
            el: '#J_expressForm',
            rules: {
                expressName:{
                    required: true
                },
                expressNo:{
                    required: true
                }
            },
            messages: {
            	expressName: {
                    required:"快递名称不能为空",
                },
                expressNo:{
                    required:"快递单号不能为空",
                }
            }
        });

    }

    $JUserList.on('click', '.J_edit', function () {
        $.ajax({
            url: ctx + '/express/edit',
            data: {
                id: $(this).data('id')
            },
            method: 'get',
            dataType: 'html',
            success: function (str) {
              yaya.layer.open({
                    type: 1,
                    title: '物流编辑',
                    content: str, //注意，如果str是object，那么需要字符拼接。
                    area: '550px',
                    shadeClose: true,
                    btn: ['保存'],
                    success: function (layero, index) {
                    	checkExpressForm();
                    },
                    yes: function (index) {
                         var isValid = $("#J_expressForm").valid();
                         if(!isValid){
                             return;
                         }
                        $.ajax({
                            url: ctx + '/express/save',
                            data: $('#J_expressForm').serialize(),
                            method: 'post',
                            dataType: 'json',
                            success: function (data) {

                                if (data.code == 1200) {
                                    yaya.layer.close(index);
                                    table.draw();
                                    yaya.layer.msg("保存成功")

                                }
                            },
                            error: function () {

                            }
                        });
                    }

                });
            },
            error: function () {

            }
        });
    });

    /**
     * 删除
     */
    $JUserList.on('click', '.J_delete', function () {
        var id = $(this).data('id');
        console.log("id:"+id);
        yaya.layer.confirm('确认删除用户？', {
                btn: ['确定', '取消'] //按钮
            },
            function (index) {
                $.ajax({
                    url: ctx + '/express/delete',
                    data: {
                        id: id
                    },
                    method: 'get',
                    dataType: 'json',
                    success: function (data) {
                        if (data.code == 1200) {
                            yaya.layer.msg("删除成功")
                            setTimeout(function () {
                                table.draw();
                            }, 50)
                        } else {
                            yaya.layer.msg("删除失败");//出错信息

                        }
                        // yaya.layer.msg("success:"+data.code);
                    },
                    error: function () {

                        yaya.layer.msg("删除失败");

                    }

                });
            })

    });

});
