/**
 * Created by kee on 16/12/10.
 */

require(['jquery', 'yaya', 'datatables.net'], function ($, yaya) {

    // 手机号码验证
    jQuery.validator.addMethod("isMobile", function(value, element) {
        var length = value.length;
        var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
        var telephone = /^(?:(?:0\d{2,3})-)?(?:\d{7,8})(-(?:\d{3,}))?$/;
        return this.optional(element) || (length == 11 && mobile.test(value)) || telephone.test(value);
    }, "请正确填写电话号码");

    // 手机号码验证
    jQuery.validator.addMethod("isEmail", function(value, element) {
        var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
        return reg.test(value);
    }, "请正确填写邮箱");



    var $JPartnerList = $('#J_PartnerList');

    var table = $JPartnerList.DataTable({
        "scrollX": true,
        'processing': true,
        'searching': false,
        'lengthChange': false,
        'sort': false,
        'serverSide': true,
        'lengthMenu': [10, 20, 50, 100],
        'ajax': {
            'url': ctx + '/partner/list-data',
            'data': function (d) {
                d.extra_search = $('#keywords').val();
                d.partner_type = $('#type').val();

            }
        },
        'columns': [
            {'data': 'code'},
            {'data': 'fullname'},
            {'data': 'addressFirst'},
            {'data': 'contactFirst'},
            {'data': 'contactFirstTel'},
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
     * 搜索
     */
    $('#keywords').on('keyup click', function () {
        table.draw();
    });

    $('#type').change(function () {
        table.draw();
    });

    $('#J_PartnerAdd').click(function () {
        $.ajax({
            url: ctx + '/partner/add',
            method: 'get',
            dataType: 'html',
            success: function (str) {
                yaya.layer.open({
                    type: 1,
                    title: '新建',
                    content: str, //注意，如果str是object，那么需要字符拼接。
                    area: '600px',
                    shadeClose: true,
                    btn: ['保存'],
                    success: function (layer, index) {
                    },
                    yes: function (index) {
                        // var isValid = $("#J_partnerForm").valid();
                        // if(!isValid){
                        //     return;
                        // }

                        var success = checkPartnerForm();
                        if(!success){
                            return;
                        }

                        var loadIndex = yaya.layer.load(2);
                        $.ajax({
                            url: ctx + '/partner/save',
                            traditional: true,
                            data: {
                                id: $('#id').val(),
                                code: $('#code').val(),
                                fullname: $('#fullname').val(),
                                type: $('#partnerType').val(),
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
            }
        });//end ajax
    });



        /**
     * 删除
     */
    $JPartnerList.on('click', '.J_delete', function () {
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

    $JPartnerList.on('click', '.J_edit', function () {
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


                    },
                    yes: function (index) {

                        var success = checkPartnerForm();
                        // yaya.layer.msg(success);
                        if(!success){
                            return;
                        }

                        var loadIndex = yaya.layer.load(2);
                        $.ajax({
                            url: ctx + '/partner/save',
                            traditional: true,
                            data: {
                                id: $('#id').val(),
                                code: $('#code').val(),
                                fullname: $('#fullname').val(),
                                type: $('#partnerType').val(),
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
    
    function checkPartnerForm() {

        var code = $("#code").val();
        var fullname = $("#fullname").val();
        var addressFirst = $("#addressFirst").val();
        var contactFirst = $("#contactFirst").val();
        var contactFirstTel = $("#contactFirstTel").val();
        var email = $("#email").val();

        var isReturn = false;
        if(code == ""){
            yaya.layer.msg("公司代码不能为空");
            return false;
        }else{
            $.ajax({
                url:ctx+"/partner/check-code",
                data:{
                    partnerId:$("#id").val(),code:$("#code").val()
                },
                async:false,
                dataType:"json",
                success:function (result) {
                    if(!result.code){
                        isReturn = true;
                        yaya.layer.msg("公司代码已存在")
                    }
                }
            });
            if(isReturn){
                return false;
            }
        }
        if(fullname == ""){
            yaya.layer.msg("公司名称不能为空");
            return false;
        }
        if(addressFirst == ""){
            yaya.layer.msg("地址不能为空");
            return false;
        }
        if(contactFirst == ""){
            yaya.layer.msg("第一联系人不能为空");
            return false;
        }
        if(contactFirstTel == ""){
            yaya.layer.msg("第一联系人电话不能为空");
            return false;
        }else{

            var length = contactFirstTel.length;
            var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
            var telephone = /^(?:(?:0\d{2,3})-)?(?:\d{7,8})(-(?:\d{3,}))?$/;

            isReturn =  (length == 11 && mobile.test(contactFirstTel)) || telephone.test(contactFirstTel);
            if(!isReturn){
                yaya.layer.msg("第一联系人电话格式错误");
                return isReturn;
            }
        }
        if(email == ""){
            yaya.layer.msg("邮箱不能为空");
            return false;
        }else{
            var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
            isReturn = reg.test(email);
            if(!isReturn){
                yaya.layer.msg("邮箱格式错误");
                return false;
            }

        }

        return true;
        
    }

    //        yaya.validate({
//     el: '#J_partnerForm',
//         rules: {
//         code: {
//             required:true,
//                 remote:{
//                 url:ctx+"/partner/check-code",
//                     type:"post",
//                     data:{
//                     partnerId:function(){ return $("#id").val();},
//                     code:function(){return $("#code").val();}
//                 }
//             }
//         },
//         fullname: {
//             required: true
//         },
//         addressFirst:{
//             required: true
//         },
//         contactFirst:{
//             required:true
//         },
//         contactFirstTel:{
//             required:true,
//                 isMobile:true
//         },
//         email:{
//             required:false,
//                 isEmail:true
//         },
//         companyTelephone:{
//             isMobile:true
//         },
//         contactSecondTel:{
//             isMobile:true
//         }
//
//
//     },
//     messages: {
//         code: {
//             required:"公司代码不能为空",
//                 remote:"公司代码已存在"
//         },
//         fullname: {
//             required: "公司全称不能为空",
//         },
//         addressFirst:{
//             required:"地址不能为空"
//         },
//         contactFirst:{
//             required:"第一联系人不能为空"
//         },
//         contactFirstTel:{
//             required:"第一联系人电话不能为空"
//         },
//         email:{
//             isEmail:"邮件格式错误"
//         }
//
//     }
// });



});
