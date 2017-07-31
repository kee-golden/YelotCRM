/**
 * Created by kee on 16/12/12.
 */
require(['jquery', 'yaya', 'datatables.net','city.min','cityselect'], function ($, yaya,datatables,citymin,cityselect) {

    $("#prov-city").citySelect({
        prov:"北京",
        nodata:"none"
    });

    // 手机号码验证
    jQuery.validator.addMethod("isMobile", function(value, element) {
        var length = value.length;
        var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, "请正确填写手机号码");

    var $JCustomerList = $('#J_customerList');


    var table = $JCustomerList.DataTable({
        "scrollX": true,
        'processing': true,
        'searching': false,
        'lengthChange': false,
        'sort': false,
        'serverSide': true,
        'serverSide': true,
        'lengthMenu': [10, 20, 50, 100],
        'ajax':{
            'url':ctx + '/customer/list-data',
            'data': function (d) {
                d.extra_search = $('#keywords').val();
            }
        },
        'columns': [
            {'data': 'name'},
            {'data': 'phone'},
            {'data': 'province'},
            {'data': 'city'},
            {'data': 'address'},
            {'data': 'createAt'},
            {'data': 'id'}
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

    $('#J_customerAdd').click(function () {
        $.ajax({
            url: ctx + '/customer/add',
            method: 'get',
            dataType: 'html',
            success: function (str) {
                yaya.layer.open({
                    type: 1,
                    title: '新建客户',
                    content: str, //注意，如果str是object，那么需要字符拼接。
                    area: '650px',
                    shadeClose: true,
                    btn: ['保存'],
                    success: function (layer, index) {
                        //初始化值
                        $("#J_name").val("");
                        checkCustomerFrom();
                    },
                    yes: function (index) {
                        var isValid = $("#J_customerForm").valid();

                        if (isValid) {

                            $.ajax({
                                url: ctx + '/customer/save',
                                data: $('#J_customerForm').serialize(),
                                method: 'post',
                                dataType: 'json',
                                success: function (data) {
                                    console.log(data.code);
                                    if (data.code) {
                                        table.draw();
                                        yaya.layer.close(index);

                                    }
                                    else {
                                        yaya.layer.msg(data.data);

                                    }
                                },
                                error: function (data) {
                                    console.log(data);

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

    function checkCustomerFrom() {
        yaya.validate({
            el: '#J_customerForm',
            rules: {
                phone:{
                    required:true,
                    isMobile:true,
                    remote:{
                        url:ctx+"/customer/check-phone",
                        type:"post",
                        data:{
                            phone:function () {
                                return $("#J_phone").val();
                            }
                        }
                    }

                },
                name:{
                    required: true
                }
            },
            messages: {
                phone: {
                    required:"手机号不能为空",
                    remote:"手机号已存在"
                },
                name:{
                    required:"姓名不能为空"
                }
            }
        });

    }

    $JCustomerList.on('click', '.J_edit', function () {
        $.ajax({
            url: ctx + '/user/edit',
            data: {
                id: $(this).data('id')
            },
            method: 'get',
            dataType: 'html',
            success: function (str) {
              yaya.layer.open({
                    type: 1,
                    title: '用户编辑',
                    content: str, //注意，如果str是object，那么需要字符拼接。
                    area: '550px',
                    shadeClose: true,
                    btn: ['保存'],
                    success: function (layero, index) {

                        checkCustomerFrom();

                    },
                    yes: function (index) {
                        var isValid = $("#J_userForm").valid();
                        if(!isValid){
                            return;
                        }
                        $.ajax({
                            url: ctx + '/user/save',
                            data: $('#J_userForm').serialize(),
                            method: 'post',
                            dataType: 'json',
                            success: function (data) {
                                // if (data.code) {
                                //     table.draw();
                                //     yaya.layer.close(index);
                                // }
                                // else {
                                //     yaya.layer.msg(data.data);//出错信息
                                //
                                // }
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
     * 删除用户
     */
    $JCustomerList.on('click', '.J_delete', function () {
        var id = $(this).data('id');
        console.log("id:"+id);
        yaya.layer.confirm('确认删除用户？', {
                btn: ['确定', '取消'] //按钮
            },
            function (index) {
                $.ajax({
                    url: ctx + '/user/user-delete',
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

                        yaya.layer.msg("error,,,");

                    }

                });
            })

    });


    $JCustomerList.on('click', '.J_changePassword', function () {
        $.ajax({
            url: ctx + '/user/reset-password',
            data: {
                id: $(this).data('id')
            },
            method: 'get',
            dataType: 'json',
            success: function (result) {
                yaya.layer.msg(result.message);
            },
            error: function () {

            }
        });

    });


});
