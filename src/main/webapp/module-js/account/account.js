/**
 * Created by kee on 16/12/12.
 */
require(['jquery', 'yaya', 'datatables.net','dateTimePicker'], function ($, yaya) {


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
            'url':ctx + '/account/list-data',
            'data': function (d) {
                d.extra_search = $('#keywords').val();
            }
        },
        'columns': [
            {'data': 'phone'},
            {'data': 'wxNickname'},
            {'data': 'accountNo'},
            {'data': 'fullName'},
            {'data': 'email'},
            {'data': 'hobbyJson'},
            {'data': 'city'},
            {'data': 'expressAddress'},
            {'data': 'createAt'},
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

    ;




});
