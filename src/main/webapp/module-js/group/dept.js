/**
 * Created by kee on 16/12/29.
 */
require(['jquery', 'yaya', 'datatables.net', 'ztree'], function ($, yaya) {
    //J_DeptmentList databable
    var $JDepartmentList = $('#J_DepartmentList');


    var table = $JDepartmentList.DataTable({
        "scrollX": true,
        'processing': true,
        'searching': false,
        'lengthChange': false,
        'sort': false,
        'serverSide': true,
        'lengthMenu': [10, 20, 50, 100],
        'ajax': {
            'url': ctx + '/group/department-list-data',
            'data': function (d) {
                d.extra_search = $('#keywords').val();
            }
        },
        'columns': [
            {'data': 'name'},
            {'data': 'createTm'},
            {'data': 'updateTm'},
            {
                'data': 'id',
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

    /**
     * 删除
     */
    $JDepartmentList.on('click', '.J_delete', function () {
        var id = $(this).data('id');
        yaya.layer.confirm('您是确定要删除？', {
            btn: ['确定', '取消'] //按钮
        }, function (index) {
            $.ajax({
                url: ctx + '/group/dept-delete',
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

    //增加
    $('#J_DepartmentAdd').click(function(){
        $.ajax({
            url: ctx + '/group/dept-edit',
            method: 'get',
            dataType: 'html',
            success: function (str) {
                yaya.layer.open({
                    type: 1,
                    title: '部门添加',
                    content: str, //注意，如果str是object，那么需要字符拼接。
                    area: '300px',
                    shadeClose: true,
                    shade : [0.3 , '#000' , true],
                    btn: ['保存'],
                    success: function (layer, index) {
                        yaya.validate({
                            el: '#J_groupForm',
                            rules: {
                                name: {
                                    required:true,
                                    remote:{
                                        url:ctx+"/group/check-dept",
                                        type:"post",
                                        data:{
                                            name:function () {
                                                return $("#J_name").val();
                                            }
                                        }
                                    }
                                },
                            },
                            messages: {
                                name: {
                                    required:"名称不能为空",
                                    remote:"部门名称已经存在"
                                },
                            }
                        });
                    },
                    yes:function(index){
                        var isValid = $("#J_groupForm").valid();
                        if(!isValid){
                            return;
                        }

                        var loadIndex = yaya.layer.load(2);
                        $.ajax({
                            url: ctx + '/group/dept-save',
                            traditional: true,
                            data: {
                                id: $('#J_id').val(),
                                name: $('#J_name').val()
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
            }
        });//end ajax

    });

    /**
     * 编辑
     */
    $JDepartmentList.on('click', '.J_edit', function () {
        var editBtn = $(this);
        $.ajax({
            url: ctx + '/group/dept-edit',
            method: 'get',
            data: {
                id: editBtn.data('id')
            },
            dataType: 'html',
            success: function (str) {
                yaya.layer.open({
                    type: 1,
                    title: '部门编辑',
                    content: str, //注意，如果str是object，那么需要字符拼接。
                    area: '300px',
                    shadeClose: true,
                    shade : [0.3 , '#000' , true],
                    btn: ['保存'],
                    success: function (layer, index) {

                        yaya.validate({
                            el: '#J_groupForm',
                            rules: {
                                name: {
                                    required:true,
                                    remote:{
                                        url:ctx+"/group/check-dept",
                                        type:"post",
                                        data:{
                                            id:function () {
                                                return $("#J_id").val();
                                            },
                                            name:function () {
                                                return $("#J_name").val();
                                            }
                                        }
                                    }
                                },
                            },
                            messages: {
                                name: {
                                    required:"名称不能为空",
                                    remote:"部门名称已经存在"
                                },
                            }
                        });
                    },
                    yes: function (index) {

                        var isValid = $("#J_groupForm").valid();

                        if(!isValid){
                            return;
                        }
                        var loadIndex = yaya.layer.load(2);
                        $.ajax({
                            url: ctx + '/group/dept-save',
                            traditional: true,
                            data: {
                                id: $('#J_id').val(),
                                name: $('#J_name').val()
                            },

                            method: 'post',
                            dataType: 'json',
                            success: function (data) {
                                if(!data.code){
                                    yaya.layer.msg("名称已存在！");
                                    yaya.layer.close(loadIndex);
                                    return;
                                }
                                yaya.layer.close(loadIndex);
                                yaya.layer.close(index);
                                if (data.code) {
                                    table.draw();
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
