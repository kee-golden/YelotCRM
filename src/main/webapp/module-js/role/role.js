/**
 * Created by kee on 17/7/8.
 */
require(['jquery', 'yaya', 'datatables.net', 'ztree'], function ($, yaya) {

    var $JRoleList = $('#J_roleList');

    var zTree;


    /**
     * 菜单树
     */
    var $JPrivilegeTree;


    var table = $JRoleList.DataTable({
        'processing': true,
        'serverSide': true,
        'searching': false,
        'lengthChange': false,
        'scrollY': 480,
        'lengthMenu': [20, 20, 50, 100],
        'ajax': ctx + '/role/list-data',
        'columns': [
            {'data': 'name'},
            {'data': 'code'},
            {'data': 'createAt'},
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
    $('#J_roleAdd').click(function () {
        $.ajax({
            url: ctx + '/role/add',
            method: 'get',
            dataType: 'html',
            success: function (str) {
                yaya.layer.open({
                    type: 1,
                    title: '角色编辑',
                    content: str, //注意，如果str是object，那么需要字符拼接。
                    area: '550px',
                    shadeClose: true,
                    shade : [0.7 , '#000' , true],
                    btn: ['保存'],
                    success: function (layer, index) {
                        yaya.validate({
                            el: '#J_roleForm',
                            rules: {
                                name: "required",
                                code: {
                                    required: true
                                }
                            },
                            messages: {
                                name: "角色名称不能为空",
                                code: {
                                    required: "角色编码不能为空"
                                }
                            }
                        });

                        var curStatus;

                        function expandNodes(nodes) {
                            if (!nodes) return;
                            curStatus = "expand";
                            for (var i = 0, l = nodes.length; i < l; i++) {
                                zTree.expandNode(nodes[i], true, false, false);
                                if (nodes[i].isParent && nodes[i].zAsync) {
                                    expandNodes(nodes[i].children);
                                }
                            }
                        }

                        $JPrivilegeTree = $('#J_privilegeTree');
                        var treeSetting = {
                            async: {
                                enable: true,
                                url: ctx + '/role/privilege/tree-data',
                                autoParam: ['id'],
                                otherParam: ['roleId', null]

                            },
                            callback: {
                                onAsyncSuccess: function (event, treeId, treeNode, msg) {
                                    if (!treeNode) {
                                        expandNodes(zTree.getNodes());
                                    }

                                }
                            },
                            check: {
                                enable: true,
                                autoCheckTrigger: true
                            }

                        };

                        $.fn.zTree.init($JPrivilegeTree, treeSetting);

                        zTree = $.fn.zTree.getZTreeObj("J_privilegeTree");

                    },
                    yes: function (index) {
                        var isValid = $("#J_roleForm").valid();
                        if (isValid) {

                            var checkedNodes = zTree.getCheckedNodes();

                            var checkPrivileges = [];

                            for (var i = 0; i < checkedNodes.length; i++) {
                                var node = checkedNodes[i];
                                if (node.id != '#') {
                                    checkPrivileges.push(node.id);
                                }

                            }

                            var loadIndex = yaya.layer.load(2);
                            $.ajax({
                                url: ctx + '/role/save',
                                traditional: true,
                                data: {
                                    id: $('#id').val(),
                                    name: $('#name').val(),
                                    code: $('#code').val(),
                                    privilege: checkPrivileges
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
                    }

                });
            },
            error: function () {

            }
        });
    });

    /**
     * 角色删除
     */
    $JRoleList.on('click', '.J_delete', function () {
        var id = $(this).data('id');
        yaya.layer.confirm('您是如何要删除该角色？', {
            btn: ['确定', '取消'] //按钮
        }, function (index) {
            $.ajax({
                url: ctx + '/role/delete',
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


    /**
     * 角色编辑
     */
    $JRoleList.on('click', '.J_edit', function () {
        var editBtn = $(this);
        $.ajax({
            url: ctx + '/role/edit',
            method: 'get',
            data: {
                id: editBtn.data('id')
            },
            dataType: 'html',
            success: function (str) {
                yaya.layer.open({
                    type: 1,
                    title: '角色编辑',
                    content: str, //注意，如果str是object，那么需要字符拼接。
                    area: '800px',
                    shadeClose: true,
                    shade : [0.7 , '#000' , true],
                    btn: ['保存'],
                    success: function (layer, index) {
                        yaya.validate({
                            el: '#J_roleForm',
                            rules: {
                                name: "required",
                                code: {
                                    required: true
                                }
                            },
                            messages: {
                                name: "角色名称不能为空",
                                code: {
                                    required: "角色编码不能为空"
                                }
                            }
                        });
                        var curStatus;

                        function expandNodes(nodes) {
                            if (!nodes) return;
                            curStatus = "expand";
                            for (var i = 0, l = nodes.length; i < l; i++) {
                                zTree.expandNode(nodes[i], true, false, false);
                                if (nodes[i].isParent && nodes[i].zAsync) {
                                    expandNodes(nodes[i].children);
                                }
                            }
                        }

                        $JPrivilegeTree = $('#J_privilegeTree');
                        var treeSetting = {
                            async: {
                                enable: true,
                                url: ctx + '/role/privilege/tree-data',
                                autoParam: ['id'],
                                otherParam: ['roleId', editBtn.data('id')]

                            },
                            callback: {
                                onAsyncSuccess: function (event, treeId, treeNode, msg) {
                                    if (!treeNode) {
                                        expandNodes(zTree.getNodes());
                                    }

                                }
                            },
                            check: {
                                enable: true,
                                autoCheckTrigger: true
                            }

                        };

                        $.fn.zTree.init($JPrivilegeTree, treeSetting);

                        zTree = $.fn.zTree.getZTreeObj("J_privilegeTree");


                    },
                    yes: function (index) {
                        var isValid = $("#J_roleForm").valid();
                        if (isValid) {


                            var checkedNodes = zTree.getCheckedNodes();

                            var checkPrivileges = [];

                            for (var i = 0; i < checkedNodes.length; i++) {
                                var node = checkedNodes[i];
                                if (node.id != '#') {
                                    checkPrivileges.push(node.id);
                                }

                            }

                            var loadIndex = yaya.layer.load(2);
                            $.ajax({
                                url: ctx + '/role/save',
                                traditional: true,
                                data: {
                                    id: $('#id').val(),
                                    name: $('#name').val(),
                                    code: $('#code').val(),
                                    privilege: checkPrivileges
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
                    }

                });
            },
            error: function () {

            }
        });
    });


})

