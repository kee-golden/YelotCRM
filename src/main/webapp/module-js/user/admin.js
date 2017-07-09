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

    var $JAdminList = $('#J_adminList');


    var table = $JAdminList.DataTable({
        "scrollX": true,
        'processing': true,
        'searching': false,
        'lengthChange': false,
        'sort': false,
        'serverSide': true,
        'serverSide': true,
        'lengthMenu': [10, 20, 50, 100],
        'ajax':{
            'url':ctx + '/group/admin-list-data',
            'data': function (d) {
                d.extra_search = $('#keywords').val();
            }
        },
        'columns': [
            {'data': 'username'},
            {'data': 'nickname'},
            {'data': 'phone'},
            {'data': 'deptName'},
            {'data': 'roleNames'/*,
                'render': function (data, type, full, meta) {
                    var dataedit=data;
                   if(data.length>=10){
                       data=data.substring(0,10)+"..."
                   }
                   return data+'<span data-cont="'+dataedit+'" class="span1"></span>'
                }*/
            },
            {'data': 'createAt'},
            {'data': 'id',
                'render': function (data, type, full, meta) {

                    if(data == adminId){ //user_index.jsp 中定义了adminId变量
                        console.log("adminId"+adminId);
                        return "";
                    }
                    return '<a href="javascript:;;" data-id="' + data + '" class="J_edit"><i class="fa fa-edit" aria-hidden="true"></i>编辑</a>&nbsp;&nbsp;' +
                        '<a href="javascript:;;" data-id="' + data + '" class="J_changePassword"><i class="fa fa-lock" aria-hidden="true"></i>重置密码</a>&nbsp;&nbsp;' +
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

    $('#J_adminAdd').click(function () {
        $.ajax({
            url: ctx + '/admin/add',
            method: 'get',
            dataType: 'html',
            success: function (str) {
                yaya.layer.open({
                    type: 1,
                    title: '新建用户',
                    content: str, //注意，如果str是object，那么需要字符拼接。
                    area: '550px',
                    shadeClose: true,
                    btn: ['保存'],
                    success: function (layer, index) {
                        //初始化值
                        //if(${username})
                        $("#J_username").val("");
                        $("#J_password").val("");
                        checkAdminFrom();
                    },
                    yes: function (index) {
                        var isValid = $("#J_adminForm").valid();

                        if (isValid) {

                            $.ajax({
                                url: ctx + '/admin/save',
                                data: $('#J_adminForm').serialize(),
                                method: 'post',
                                dataType: 'json',
                                success: function (data) {
                                    if (data.code) {
                                        table.draw();
                                        yaya.layer.close(index);

                                    }
                                    else {
                                        yaya.layer.msg(data.data);

                                    }
                                },
                                error: function () {

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

    function checkAdminFrom() {
        yaya.validate({
            el: '#J_adminForm',
            rules: {
                username:{
                    required:true,
                    remote:{
                        url:ctx+"/admin/check-username",
                        type:"post",
                        data:{
                            username:function () {
                                return $("#J_username").val();
                            }
                        }
                    }

                },
                password: {
                    required: true
                },
                nickname:{
                    required: true
                },
                phone:{
                    required: true,
                    isMobile:true
                }
            },
            messages: {
                username: {
                    required:"用户名不能为空",
                    remote:"用户名已存在"
                },
                password: {
                    required: "密码不能为空"
                },
                nickname:{
                    required:"姓名不能为空"
                },
                phone:{
                    required:"手机号不能为空",
                    isMobile:"请输入正确的手机号"
                }
            }
        });

    }

    $JAdminList.on('click', '.J_edit', function () {
        $.ajax({
            url: ctx + '/admin/edit',
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

                        checkAdminFrom();

                    },
                    yes: function (index) {
                        var isValid = $("#J_adminForm").valid();
                        if(!isValid){
                            return;
                        }
                        $.ajax({
                            url: ctx + '/admin/save',
                            data: $('#J_adminForm').serialize(),
                            method: 'post',
                            dataType: 'json',
                            success: function (data) {
                                if (data.code) {
                                    table.draw();
                                    yaya.layer.close(index);
                                }
                                else {
                                    yaya.layer.msg(data.data);//出错信息

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
     * 删除用户
     */
    $JAdminList.on('click', '.J_delete', function () {
        var nid = $(this).data('id');
        console.log("1111"+nid);
        yaya.layer.confirm('确认删除用户？', {
                btn: ['确定', '取消'] //按钮
            },
            function (index) {
                $.ajax({
                    url: ctx + '/admin/admin-delete',
                    data: {
                        nid: nid
                    },
                    method: 'get',
                    dataType: 'json',
                    success: function (data) {
                        if (data.code == 200) {
                            yaya.layer.msg("删除成功")
                            setTimeout(function () {
                                table.draw();
                            }, 50)
                        }
                        else if (data.code == 201) {
                            yaya.layer.msg("删除失败");//出错信息

                        }
                    },
                    error: function () {

                    }

                });
            })

    });


    $JAdminList.on('click', '.J_changePassword', function () {
        $.ajax({
            url: ctx + '/admin/reset-password',
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

    /*$JAdminList.on('mouseover', 'tr', function () {

             var cont=$(this).find(".span1").attr("data-cont");
            $(this).find(".span1").html(cont).stop(true,true).animate({opacity: "show", top: "0"}, "slow");
    });

    $JAdminList.on('mouseleave', 'tr', function () {
        $(this).find(".span1").stop(true,true).animate({opacity: "hide", top: "-30"}, "fast")
    });*/
});
