/**
 * Created by gc on 2017/4/7 0007.
 */
require(['jquery', 'yaya', 'datatables.net'], function ($, yaya) {

    var roles=[];


    var $JhistoryApprList = $('#historyApproval');

    var table = $JhistoryApprList.DataTable({
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
               d.history = 1;
            }
        },
        'columns': [
            {'data': 'activity.activityName',
              /*  'render':function(data,type, full, meta){
                    return '<a data-id="' + full.id + '" class="J_edit">'+data+'</a>'
                }*/
            },
            {'data': 'activity.customer.customerName'},
            {'data': 'responsiblePerson.nickname'},
            {'data': 'applicant.nickname'},
            {'data': 'applicationTime'},
            {'data': 'approvalTime'},
            {'data': 'applicationStatus',
                'render':function(data,type, full, meta){
                    if(data == 4){
                        return "审批通过";
                    }else if(data == 9){
                        return "审批退回";
                    }
                    else if(data == 10){
                        return "活动取消";
                    }
                    else if(data == 11){
                        return "活动删除";
                    }
            }
            },

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
})
