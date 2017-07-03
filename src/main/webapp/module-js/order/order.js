/**
 * Created by kee on 16/12/10.
 */

require(['jquery', 'yaya', 'datatables.net', 'ztree'], function ($, yaya) {

    var $JOrderList = $('#J_orderList');

    var table = $JOrderList.DataTable({
        'processing': true,
        'searching': false,
        'lengthChange': false,
        'sort': false,
        'serverSide': true,
        // 'scrollY': 550,
        'lengthMenu': [10, 20, 50, 100],
        'ajax': {
            'url': ctx + '/order/list-data',
            'data': function (d) {
                d.extra_search = $('#keywords').val();
            }
        },
        'columns': [
            {'data': 'orderSerial'},
            {'data': 'serviceName'},
            {'data': 'servicePrice',
                'render':function (data) {
                    return data/100 + '元';
                }

            },
            {'data': 'createTm'},
            {'data': 'status',
                'render':function (data, type, full, meta) {
                    var contentHtml;
                    if(data == 1){
                        contentHtml="已付款";
                    }else if(data == 2){
                        contentHtml = "未付款";
                    }
                    return contentHtml;
                }
            }
            // {
            //     'data': 'id',
            //     'render': function (data, type, full, meta) {
            //         return '<a href="javascript:;;" data-id="' + data + '" class="J_edit"><i class="fa fa-edit" aria-hidden="true"></i>编辑</a>&nbsp;&nbsp;' +
            //             '<a href="javascript:;;" data-id="' + data + '" class="J_delete"><i class="fa fa-trash" aria-hidden="true"></i>删除</a>';
            //
            //     }
            // }
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


    /**
     * 删除
     */
    $JOrderList.on('click', '.J_delete', function () {
        var id = $(this).data('id');
        yaya.layer.confirm('您是确定要删除？', {
            btn: ['确定', '取消'] //按钮
        }, function (index) {
            $.ajax({
                url: ctx + '/partner/delete',
                data: {
                    id: id
                },
                method: 'post',
                dataType: 'json',
                success: function (result) {
                    if (result.code) {
                        table.draw();
                        yaya.layer.close(index);
                        yaya.layer.msg('删除成功!');

                    } else {
                        yaya.layer.msg('删除失败!');
                    }

                },
                error: function () {
                    yaya.layer.msg('系统异常');
                }
            })


        }, function () {
        })
    });

    $JOrderList.on('click', '.J_edit', function () {
        var editBtn = $(this);
        $.ajax({
            url: ctx + '/partner/edit',
            method: 'get',
            data: {
                id: editBtn.data('id')
            },
            dataType: 'html',
            success: function (str) {
                yaya.layer.open({
                    type: 1,
                    title: '编辑',
                    content: str, //注意，如果str是object，那么需要字符拼接。
                    area: '780px',
                    shadeClose: true,
                    btn: ['保存'],
                    success: function (layer, index) {
                        yaya.validate({
                            el: '#J_partnerForm',
                            rules: {
                                name: "required",
                                code: {
                                    required: true
                                }
                            },
                            messages: {
                                name: "名称不能为空",
                                code: {
                                    required: "编码不能为空"
                                }
                            }
                        });

                    },
                    yes: function (index) {

                        var loadIndex = yaya.layer.load(2);
                        $.ajax({
                            url: ctx + '/partner/save',
                            traditional: true,
                            data: {
                                id: $('#id').val(),
                                code: $('#code').val(),
                                fullname: $('#fullname').val(),
                                type: $('#type').val(),
                                companyTelephone: $('#companyTelephone').val(),
                                fax: $('#fax').val(),
                                email: $('#email').val(),
                                websiteUrl: $('#websiteUrl').val(),
                                addressFirst: $('#addressFirst').val(),
                                addressSecond: $('#addressSecond').val(),
                                email: $('#email').val(),
                                contactFirst: $('#contactFirst').val(),
                                contactFirstTel: $('#contactFirstTel').val(),
                                contactSecond: $('#contactSecond').val(),
                                contactSecondTel: $('#contactSecondTel').val()
                            },

                            method: 'post',
                            dataType: 'json',
                            success: function (data) {
                                yaya.layer.close(loadIndex);
                                yaya.layer.close(index);
                                if (data.code) {
                                    table.draw();
                                }
                                else {

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



});
