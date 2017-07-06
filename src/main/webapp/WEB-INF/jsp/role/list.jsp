<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>ITSS管理后台</title>

    <%@include file="/WEB-INF/common/static.jsp" %>

    <c:set var="PARENT_MENU_CODE" value="PLATFORM_MANAGE"/>
    <c:set var="CHILD_MENU_CODE" value="ROLE_MANAGE"/>

    <script>
        var ctx = '${ctx}';
    </script>

</head>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<%--<div id="wrapper">--%>
    <%@include file="/WEB-INF/common/top_logo_nav.jsp" %>
    <%@include file="/WEB-INF/common/slideBar.jsp" %>

    <!--右侧部分开始-->
    <div id="page-wrapper" class="wrapper wrapper-content ">
        <%--<div class="row border-bottom">--%>
            <%--<nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">--%>
                <%--<div class="navbar-header">--%>
                    <%--<h1>ITSS管理后台</h1>--%>
                <%--</div>--%>
            <%--</nav>--%>
        <%--</div>--%>
        <%--<div class="row content-tabs">--%>
            <%--<nav class="page-tabs J_menuTabs">--%>
                <%--<div class="page-tabs-content">--%>
                    <%--<div class="">角色列表</div>--%>
                <%--</div>--%>
            <%--</nav>--%>

            <%--<a href="${ctx}/logout" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>--%>
        <%--</div>--%>
        <div class="row animated fadeInRight" id="content-main">
            <div class="col-xs-12">

                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>角色
                        </h5>
                        <div class="ibox-tools">
                            <a class="collapse-link" id="J_roleAdd">
                                <i class="fa fa-plus"></i>添加
                            </a>
                            <%--<a class="collapse-link">--%>
                                <%--<i class="fa fa-close"></i>返回--%>
                            <%--</a>--%>
                        </div>
                    </div>
                    <div class="ibox-content">

                        <table id="J_roleList" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>角色</th>
                                <th>编码</th>
                                <th>修改时间</th>
                                <th>编辑</th>
                            </tr>
                            </thead>
                        </table>

                    </div>
                </div>

            </div>
        </div>
        <div class="footer">
            <div class="pull-right">
                &copy; 2015-2016 <a href="javascript:void(0);" target="_blank">无锡新世纪信息有限公司</a>
            </div>
        </div>
        <!--右侧部分结束-->

    </div>

    <script src="${ctx}/static/require/require.js"></script>
    <script src="${ctx}/static/require/require.config.js"></script>

    <script>
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
    </script>

</body>

</html>
