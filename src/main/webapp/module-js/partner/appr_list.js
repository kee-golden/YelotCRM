/**
 * Created by gc on 2017/4/7 0007.
 */
require(['jquery', 'yaya', 'datatables.net'], function ($, yaya) {

    var roles=[];


    var $JApprList = $('#apprList');

    var table = $JApprList.DataTable({
        "scrollX": true,
        'processing': true,
        'searching': false,
        'lengthChange': false,
        'sort': false,
        'serverSide': true,
        'lengthMenu': [10, 20, 50, 100],
        'ajax': {
            'url': ctx + '/apprPartner/appr-list',
            'traditional':true,
            'data': function (d) {

            }
        },
        'columns': [
            {'data': 'activity.activityName',
                'render':function(data,type, full, meta){
                    return '<a data-id="' + full.id + '" class="J_edit">'+data+'</a>'
                }
            },
            {'data': 'activity.customer.customerName'},
            {'data': 'responsiblePerson.nickname'},
            {'data': 'applicant.nickname'},
            {'data': 'applicationTime'},

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


    $JApprList.on('click', '.J_edit', function () {
        var id = $(this).data('id');
        $.ajax({
            url: ctx + '/apprPartner/appr-edit',
            data:{id:id},
            method: 'post',
            dataType: 'html',
            success: function (str) {
                yaya.layer.open({
                    type: 1,
                    title: '活动详情',
                    content: str, //注意，如果str是object，那么需要字符拼接。
                    area: ['600px','400px'],
                    shadeClose: true,
                    btn: ['批准','退回'],
                    success: function (layer, index) {
                    },
                    yes: function (index) {
                        if ($.trim($('#approvalReason').val())=="") {
                            yaya.layer.msg("请填写审批内容");
                        }
                        else {
                            var loadIndex = yaya.layer.load(2);
                            $.ajax({
                                url: ctx + '/apprPartner/appr-update',
                                traditional: true,
                                data: {
                                    applicationStatus: 4,
                                    approvalReason: $.trim($('#approvalReason').val()),
                                    id:$('#apprId').val(),
                                    activityId:$("#activityId").val()
                                },
                                method: 'post',
                                dataType: 'json',
                                success: function (data) {
                                    yaya.layer.close(loadIndex);
                                    yaya.layer.close(index);
                                    if (data.code) {
                                        yaya.layer.msg("保存成功");
                                        table.draw();
                                    }
                                },
                                error: function () {
                                    yaya.layer.msg("系统异常");
                                }
                            });
                        }
                    },
                    btn2:function (index) {
                        if ($.trim($('#approvalReason').val())=="") {
                            yaya.layer.msg("请填写审批内容");
                            return false;
                        }
                        else {
                            var loadIndex = yaya.layer.load(2);
                            $.ajax({
                                url: ctx + '/apprPartner/appr-update',
                                traditional: true,
                                data: {
                                    applicationStatus: 9,
                                    approvalReason: $.trim($('#approvalReason').val()),
                                    id:$('#apprId').val(),
                                    activityId:$("#activityId").val()
                                },
                                method: 'post',
                                dataType: 'json',
                                success: function (data) {
                                    yaya.layer.close(loadIndex);
                                    yaya.layer.close(index);
                                    if (data.code) {
                                        yaya.layer.msg("保存成功");
                                        table.draw();
                                    }
                                },
                                error: function () {
                                    yaya.layer.msg("系统异常");
                                }
                            });
                        }
                    }

                });
            }
        });//end ajax
    });
})
