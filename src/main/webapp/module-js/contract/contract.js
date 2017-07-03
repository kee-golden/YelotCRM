/**
 * Created by kee on 16/12/8.
 */

require(['jquery', 'yaya','webuploader','bootstrapDateTimePicker','validate','datatables.net', 'ztree'],
    function ($, yaya,WebUploader,bootstrapDateTimePicker,validate) {

        // 手机号码验证
        jQuery.validator.addMethod("isMobile", function(value, element) {
            var length = value.length;
            var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
            var telephone = /^(?:(?:0\d{2,3})-)?(?:\d{7,8})(-(?:\d{3,}))?$/;
            return this.optional(element) || (length == 11 && mobile.test(value)) || telephone.test(value);
        }, "请正确填写电话号码");

        // initMyTab();//初始化第一个Tab

    var $JContractList = $('#J_ContractList');


    var table = $JContractList.DataTable({
        "scrollX": true,
        'processing': true,
        'searching': false,
        'lengthChange': false,
        'sort': false,
        'serverSide': true,
        'lengthMenu': [10, 20, 50, 100],
        'ajax': {
            'url': ctx + '/contract/list-data',
            'data': function (d) {
                d.extra_search = $('#keywords').val();
            }
        },
        'columns': [
            {'data': 'code'},
            {'data': 'desc'},
            {'data': 'partner.fullname'},
            {'data': 'startTm'},
            {'data': 'endTm'},
            {'data': 'path',
                'render': function (data, type, full, meta) {
                    if(data == null || data == ''){
                        return '<a href="#" ><i class="fa fa-edit" aria-hidden="true"></i>下载文件</a>&nbsp;&nbsp;';
                    }else{
                        return '<a href="'+ctx+data+'" ><i class="fa fa-edit" aria-hidden="true"></i>下载文件</a>&nbsp;&nbsp;';
                    }

                }

            },
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


    $('#keywords').on('keyup click', function () {
        table.draw();
    });

    $('#J_ContractAdd').click(function(){
        $.ajax({
            url: ctx + '/contract/add',
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

                        //init webupload
                        uploader();


                    },

                    yes:function(index){

                        // var isValid = $("#J_contractForm").valid();
                        // if(!isValid){
                        //     return;
                        // }



                        // if($("#servInfos").val() == null || $("#servInfos").val() == ""){
                        //     yaya.layer.msg("服务目录不能为空！");
                        //     return;
                        // }
                       var success = checkContractForm();
                       if(!success){
                           return;
                       }

                        var loadIndex = yaya.layer.load(2);
                        $.ajax({
                            url: ctx + '/contract/save',
                            traditional: true,
                            data: {
                                id: $('#id').val(),
                                code: $('#code').val(),
                                partnerId: $('#partnerId').val(),
                                desc: $('#desc').val(),
                                path: $('#path').val(),
                                servInfos: $('#servInfos').val(),
                                startTime: $('#startTime').val(),
                                endTime: $('#endTime').val()
                            },

                            method: 'post',
                            dataType: 'json',
                            success: function (data) {
                                yaya.layer.close(loadIndex);
                                yaya.layer.close(index);
                                if (data.code) {
                                    table.draw();
                                    yaya.layer.msg("添加成功！")
                                }
                                else {

                                }
                            },
                            error: function () {

                            }
                        });


                        //
                    }
                });
            }
        });//end ajax

    });

    /**
     * 删除
     */
    $JContractList.on('click', '.J_delete', function () {
        var id = $(this).data('id');
        yaya.layer.confirm('您是确定要删除？', {
            btn: ['确定', '取消'] //按钮
        }, function (index) {
            $.ajax({
                url: ctx + '/contract/delete',
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
            });


        }, function () {
        })
    });

   // var myUploader = uploader();

    $JContractList.on('click', '.J_edit', function () {
        var editBtn = $(this);
        $.ajax({
            url: ctx + '/contract/edit',
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
                    area: '700px',
                    shadeClose: true,
                    btn: ['保存'],
                    success: function (layer, index) {//打开模态框


                        // $(".datepicker").datetimepicker({
                        //     minView: "month", //选择日期后，不会再跳转去选择时分秒
                        //     language:  'zh-CN',
                        //     format: 'yyyy-mm-dd',
                        //     todayBtn:  1,
                        //     autoclose: 1,
                        // });

                         var   myUploader = uploader();

                        // checkContractForm();

                    },
                    yes: function (index) {



                        var success = checkContractForm();
                        if(!success){
                            return;
                        }


                        var loadIndex = yaya.layer.load(2);
                        $.ajax({
                            url: ctx + '/contract/save',
                            traditional: true,
                            data: {
                                id: $('#id').val(),
                                code: $('#code').val(),
                                partnerId: $('#partnerId').val(),
                                desc: $('#desc').val(),
                                path: $('#path').val(),
                                servInfos: $('#servInfos').val(),
                                startTime: $('#startTime').val(),
                                endTime: $('#endTime').val()
                            },

                            method: 'post',
                            dataType: 'json',
                            success: function (data) {
                                yaya.layer.close(loadIndex);
                                yaya.layer.close(index);
                                if (data.code) {
                                    table.draw();
                                    yaya.layer.msg("修改成功！")
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

    /*
     * 表单验证
     */
    function checkContractForm() {
        var code = $("#code").val();
        var id = $("#id").val();
        var partnerId = $("#partnerId").val();
        var desc = $("#desc").val();
        var path = $("#path").val();
        var servInfos = $("#servInfos").val();
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();

        var isReturn = false;

        if(code == ""){
            yaya.layer.msg("合同代码不能为空");
            return false;
        }else {
            $.ajax({
                url:ctx+"/contract/check-code",
                data:{
                    id:$("#id").val(),code:$("#code").val()
                },
                async:false,
                dataType:"json",
                success:function (result) {
                    if(!result.code){
                        isReturn = true;
                        yaya.layer.msg("合同代码已存在")
                    }
                }
            });
            if(isReturn){
                return false;
            }
        }

        if(partnerId == ""){
            yaya.layer.msg("请选择签订方");
            return false;
        }
        if(startTime == ""){
            yaya.layer.msg("开始时间不能为空");
            return false;
        }
        if(endTime == ""){
            yaya.layer.msg("结束时间不能为空");
            return false;
        }
        if(desc == ""){
            yaya.layer.msg("描述不能为空");
            return false;
        }
       /* if(path == ""){
            yaya.layer.msg("合同附件不能为空");
            return false;
        }*/
        if($("#servInfos").val() == null || $("#servInfos").val() == ""){
            yaya.layer.msg("服务目录不能为空！");
            return false;
        }

        return true;

    }

        // yaya.validate({
        //     el: '#J_contractForm',
        //     rules: {
        //         code: {
        //             required: true,
        //             remote:{
        //                 url:ctx+"/contract/check-code",
        //                 type:"post",
        //                 data:{
        //                     code:function () {
        //                         return $("#code").val();
        //                     },
        //                     id:function () {
        //                         return $("#id").val();
        //                     }
        //                 }
        //
        //             }
        //         },
        //         partnerId:{
        //             required:true
        //         },
        //         startTime:{
        //             required:true
        //         },
        //         endTime:{
        //             required:true
        //         },
        //         desc:{
        //             required:true
        //         },
        //         path:{
        //             required:true
        //         }
        //
        //     },
        //     messages:{
        //         code: {
        //             required: "编码不能为空",
        //             remote:"编码已存在，请重新填写！"
        //         },
        //         partnerId:{
        //             required:"签订方不能为空"
        //         },
        //         startTime:{
        //             required:"开始时间不能为空"
        //         },
        //         endTime:{
        //             required:"结束时间不能为空"
        //         },
        //         desc:{
        //             required:"描述不能为空"
        //         },
        //         path:{
        //             required:"合同附件不能为空"
        //         }
        //     }
        // });




        /**
     * 初始化上传组件,如果是相同的文件,会过滤
     */
    function uploader() {

        var myUploader = WebUploader.create({
            auto: true,
            swf: ctx + '/static/webuploader/Uploader.swf',
            server: ctx + '/file/doc/upload',
            pick: '#picker',
            fileVal: "fileName",
            duplicate:true,
            compress: false
        });

        myUploader.on('uploadSuccess', function (file, responseData) {
            $( '#'+file.id ).find('p.state').text('已上传');
            console.log(responseData.data);//存储时间的路径
            $('#path').val(responseData.data);
            //$('#docPath').html(responseData.message);
        });

        // 当有文件被添加进队列的时候
        myUploader.on( 'fileQueued', function( file ) {
            var $list = $("#thelist");
            //每次只上传一个文件,需要重新初始化该组件的内容,
            $list.empty();
            $list.append( '<div id="' + file.id + '" class="item">' +
                '<h4 class="info">' + file.name + '</h4>' +
                '<p class="state">等待上传...</p>' +
                '</div>' );
        });

        // 文件上传过程中创建进度条实时显示。
        myUploader.on( 'uploadProgress', function( file, percentage ) {
            var $li = $( '#'+file.id ),
                $percent = $li.find('.progress .progress-bar');

            // 避免重复创建
            if ( !$percent.length ) {
                $percent = $('<div class="progress progress-striped active">' +
                    '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                    '</div>' +
                    '</div>').appendTo( $li ).find('.progress-bar');
            }

            $li.find('p.state').text('上传中');

            $percent.css( 'width', percentage * 100 + '%' );
        });


        myUploader.on( 'uploadError', function( file ) {
            $( '#'+file.id ).find('p.state').text('上传出错');
        });

        myUploader.on( 'uploadComplete', function( file ) {
            $( '#'+file.id ).find('.progress').fadeOut();
        });
        return myUploader;
    }

    function initMyTab() {


            $.ajax({
                url: ctx + '/contract/list',
                success: function (data) {
                    $('#content').html(data);
                }
            });

        $('#myTab a:first').tab('show');//初始化显示哪个tab


        $('#myTab a').click(function (e) {

            e.preventDefault();//阻止a链接的跳转行为

            var sta = $(this).attr('sta');
            var name = '';
            if (sta == '1') {
                name = '/list';

            }
            $.ajax({
                url: ctx + '/contract/' + name,
                success: function (data) {
                    $('#content').html(data);
                }
            });

            $(this).parent().parent().children().removeClass();
            $(this).parent().addClass("active");
        });

    }

});
