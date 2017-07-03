/**
 * Created by gc on 2017/4/7 0007.
 */

require(['jquery', 'yaya','webuploader', 'datatables.net'], function ($, yaya,WebUploader) {
    var customerId=$('#customerId').val();
    $('.tab_content').fadeIn(1000);
    var $JCustomerActivityList = $('#customer_activity');

    var table = $JCustomerActivityList.DataTable({
        "scrollX": true,
        'processing': true,
        'searching': false,
        'lengthChange': false,
        'sort': false,
        'serverSide': true,
        'lengthMenu': [10, 20, 50, 100],
        'ajax': {
            'url': ctx + '/customer/activity-list',
            'traditional':true,
            'data': function (d) {
                d.customerId=customerId;
            }
        },
        'columns': [
            {'data': 'activityName',
                'render':function(data,type, full, meta){
                    return '<a data-id="' + full.id + '" class="J_edit">'+data+'</a>'
                }
            },
            {'data': 'responsiblePerson.nickname'},
            {'data': 'approver.nickname'},
            {'data': 'customer.customerName'},
            {'data': 'activityTypeDesc'},
            {'data': 'activityStatusDesc'},
            {'data': 'planCost'},
            {'data': 'approvalStatusDesc'},
            {'data': 'startTime'},
            {'data': 'endTime'}

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

    $JCustomerActivityList.on('click', '.J_edit', function () {
        var nid = $(this).data('id');
        var iTop = (window.screen.height-30-550)/2; //获得窗口的垂直位置;
        var iLeft = (window.screen.width-10-800)/2; //获得窗口的水平位置;
        var win= window.open(ctx + '/activity/detail?activityId='+nid+'&&state=0','win3',
            'height=600,innerHeight=600,width=1000,innerWidth=1000,top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
    });

    $('#customer_activityAdd').click(function () {

        $.ajax({
            url: ctx + '/activity/add-page',
            method: 'post',
            dataType: 'html',
            data:{customerId:customerId},
            success: function (str) {
                yaya.layer.open({
                    type: 1,
                    title: '活动新增',
                    content: str, //注意，如果str是object，那么需要字符拼接。
                    area: ['600px','400px'],
                    shadeClose: true,
                    btn: ['保存'],
                    success: function (layer, index) {
                        uploader();
                    },
                    yes: function (index) {
                        if ($.trim($('#activityName').val()) == "") {
                            yaya.layer.msg("请填写活动名称");
                        } else if ($('.responsiblePersonId').length == 0) {
                            yaya.layer.msg("请选择负责人");
                        } else if ($('.customerId').length == 0) {
                            yaya.layer.msg("请选择关联客户");
                        } else if ($('#judgeAuthority').val()==1 && $('.approverId').length == 0){
                            yaya.layer.msg("请选择审批人");
                        } else if ($('#startTime').val() == "" || $('#startTime').val() == null){
                            yaya.layer.msg("请选择开始时间");
                        }else if ($('#endTime').val() == "" || $('#endTime').val() == null){
                            yaya.layer.msg("请选择结束时间");
                        }  else if($('#startTime').val()>$('#endTime').val() && $('#startTime').val() != null && $('#endTime').val() != null && $('#startTime').val() != '' && $('#endTime').val() != ''){
                            yaya.layer.msg("活动开始时间不能大于结束时间");
                        }
                        else if ($.trim($('#activityAddress').val()) == "" || $.trim($('#activityAddress').val()) == null){
                            yaya.layer.msg("请填写活动地址");
                        } else if ($.trim($('#planCost').text()) == "" || $.trim($('#planCost').text()) == null){
                            yaya.layer.msg("请填写预计花费");
                        }
                        else {
                            var loadIndex = yaya.layer.load(2);
                            $(".item").each(function () {
                                var id=$(this).attr("id");
                                setTimeout(function () {
                                    myUploader.upload(id);
                                },400)
                            })
                            $.ajax({
                                url: ctx + '/activity/save',
                                traditional: true,
                                data: {
                                    activityName: $('#activityName').val(),
                                    responsiblePersonId: $('.responsiblePersonId').attr('id'),
                                    customerId: $('.customerId').attr('id'),
                                    customerName: $('.customerId').text(),
                                    startTime: $('#startTime').val(),
                                    endTime: $.trim($('#endTime').val()),
                                    activityAddress: $.trim($('#activityAddress').val()),
                                    activityType: $('#activityType').val(),
                                    activityTypeAddDesc: $.trim($('#activityTypeAddDesc').val()),
                                    activityStatus: $('#activityStatus').val(),
                                    travelExpense: $.trim($('#travelExpenseBackup').val()),
                                    entertainExpense: $.trim($('#entertainExpenseBackup').val()),
                                    otherExpense: $.trim($('#otherExpenseBackup').val()),
                                    planCost: $('#planCost').text(),
                                    actualCost: $('#actualCost').val(),
                                    planIncome: $.trim($('#planIncome').val()),
                                    realIncome: $('#realIncome').val(),
                                    activityPlan: $('#activityPlan').val(),
                                    activityResult: $('#activityResult').val(),
                                    approvalStatus:$('#judgeAuthority').val(),
                                    approverId:$('.approverId').attr('id'),
                                    approverName : $('.approverId').html(),
                                    activityFile:crmId
                                },

                                method: 'post',
                                dataType: 'json',
                                success: function (data) {
                                    yaya.layer.close(loadIndex);
                                    yaya.layer.close(index);
                                    if (data.code) {
                                        yaya.layer.msg("保存成功");
                                        table.draw();
                                        var size=table.page.info().recordsTotal+1;
                                        $("#activity").text("活动（"+size+"）");
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
    })

    var myUploader;
    var mydate = new Date();
    var crmId = mydate.getTime();
    function uploader() {
        myUploader = WebUploader.create({
            auto: false,
            swf: ctx + '/static/webuploader/Uploader.swf',
            server: ctx + '/file/crmFile-upload',
            pick: '#activityPicker',
            fileVal: "fileName",
            duplicate: true,
            compress: false,
            accept: {
                title: 'doc',
                extensions: 'doc,pdf,docx,xlsx,xls',
                mimeTypes: 'application/pdf,application/msword,' +
                'application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' +
                ',application/excel'
            }
        });

        myUploader.on('uploadSuccess', function (file, responseData) {
            $('#' + file.id).find('p.state').text('已上传').css({"color": "#5FB878"});
            console.log(responseData.data);//存储时间的路径
            $('#path').val(responseData.data);
            $('#docPath').html(responseData.message);
        });

        // 当有文件被添加进队列的时候
        myUploader.on('fileQueued', function (file) {

            $('.webuploader-pick').text('继续添加');
            $("#cancelUpLoad").show();
            var same = 0;
            $(".item").each(function () {
                var name = $(this).attr("name");

                if (name == file.name) {
                    same = 1;
                    myUploader.removeFile(file.id);
                }
            })

            if (same == 0) {
                var $list = $("#thelist");
                //每次只上传一个文件,需要重新初始化该组件的内容,
                /*$list.empty();*/
                var subName;
                if (file.name.length > 5) {
                    subName = file.name.substring(0, 5) + '...';
                } else {
                    subName = file.name;
                }
                myUploader.options.formData = {crmId: crmId,state:3};
                $list.append('<div id="' + file.id + '" class="unitem" name=' + file.name + '>' +
                    '<h4 class="info"><a title=' + file.name + '  class="titlea">' + subName + '</a></h4>' +
                    '<span class="imgspan"><img src=' + ctx + '/img/closepm.png  class="removeFile" id=' + file.id + '></span>' +
                    '<div class="clearfix"></div>' +
                    '<div class="state" style="color: #1E9FFF">等待上传...</div>' +
                    '</div>');

                $('.removeFile').click(function () {
                    var id = this.id;
                    myUploader.removeFile(id);
                    $(this).parent().parent('.item').remove();
                })
            }

        });

        // 文件上传过程中创建进度条实时显示。
        myUploader.on('uploadProgress', function (file, percentage) {
            /*var $li = $( '#'+file.id ),
             $percent = $li.find('.progress .progress-bar');

             // 避免重复创建
             if ( !$percent.length ) {
             $percent = $('<div class="progress progress-striped active">' +
             '<div class="progress-bar" role="progressbar" style="width: 0%">' +
             '</div>' +
             '</div>').appendTo( $li ).find('.progress-bar');
             }*/

            /*  $li.find('p.state').text('上传中');*/

            /*  $percent.css( 'width', percentage * 100 + '%' );*/
        });


        myUploader.on('uploadError', function (file) {
            $('#' + file.id).find('p.state').text('上传出错').css({"color": "#FF5722"});
        });

        myUploader.on('uploadComplete', function (file) {
            $('#' + file.id).find('.progress').fadeOut();
        });
        return myUploader;
    }

})
