/**
 * Created by kee on 16/12/17.
 */
require(['jquery', 'yaya', 'datatables.net', 'ztree'], function ($, yaya) {

    var $JGroupList = $('#J_groupList');

    var table = $JGroupList.DataTable({
        'processing': true,
        'searching': false,
        'lengthChange': false,
        'sort': false,
        'serverSide': true,
        'scrollY': 380,
        'lengthMenu': [10, 20, 50, 100],
        'ajax': ctx + '/platform/group-list-data',
        'columns': [
            {'data': 'name'},
            {'data': 'admin.username'},
            {'data': 'createAt'},
            {'data': 'expiredAt'},
            {
                'data': 'id',
                'render': function (data, type, full, meta) {
                    console.log(data);
                    return '<a href="javascript:;;" data-id="' + data + '" class="J_edit"><i class="fa fa-edit" aria-hidden="true"></i>编辑</a>';
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

    $JGroupList.on('click', '.J_edit', function () {
        $.ajax({
            url: ctx + '/platform/group-edit',
            data: {
                id: $(this).data('id')
            },
            method: 'get',
            dataType: 'html',
            success: function (str) {
                yaya.layer.open({
                    type: 1,
                    title: '编辑',
                    content: str, //注意，如果str是object，那么需要字符拼接。
                    area: '550px',
                    shadeClose: true,
                    btn: ['保存'],
                    success: function (layero, index) {

                    },
                    yes: function (index) {
                        $.ajax({
                            url: ctx + '/platform/group-save',
                            data: {
                                id:$("#id").val(),
                                expiredAt:$("#expiredAt").val()
                            },
                            method: 'post',
                            dataType: 'json',
                            success: function (data) {
                                if (data.code) {
                                    table.draw();
                                    yaya.layer.close(index);
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