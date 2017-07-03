/**
 * Created by lenovo on 2017/4/19.
 */
require(['jquery', 'yaya','webuploader', 'datatables.net','editor'], function ($, yaya,WebUploader) {


    var layedit;
    var bulidIndex;
    var customerId=$('#customerId').val();
    $.ajax({
        url:ctx+'/customer/followUp-content',
        data:{customerId:customerId,followUp: null},
        success:function (data) {
            $("#follow_up_content").html(data)
        }
    })

    layui.use('layedit', function(){
        layedit = layui.layedit;
        bulidIndex = layedit.build('demoCustomer',{
            height: 180, //设置编辑器高度
            tool: ['left', 'center', 'right', '|', 'face']
        }); //建立编辑器
    });

    $("#comment").click(function () {
        var followUp = $("#commentTypeCustomer").val();
        var content = layedit.getContent(bulidIndex);
        if(content == "" || content == null){
            yaya.layer.msg("请填写内容")
        }
        else {
            $.ajax({
                url: ctx + '/customer/save-followUp',
                data: {customerId: customerId, followUp: followUp, content: content},
                success: function (data) {
                    if (data.code) {
                        $.ajax({
                            url: ctx + '/customer/followUp-content',
                            data: {customerId: customerId},
                            success: function (data) {
                                $("#follow_up_content").html(data)
                            }
                        })
                    }
                }
            })
        }
    })


    $('.over_left').fadeIn(1500);
    $('.over_right').fadeIn(2500);
    /*$('#editx').editable({inlineMode: false, alwaysBlank: true})*/

    var count = $("#customerFileCount").val();
    $("#annex").text("附件资料（"+count+"）");
    var geturl = ""
    var crmFileId = $("#crmFileId").val();
    $(".find_all").click(
        function () {
            var id = $(this).attr('id');
            if (id == "first_find") {
                geturl = '/customer/link-man';
                $("#linkMan").addClass('layui-this').siblings().removeClass('layui-this');

            } else if (id == "second_find") {
                geturl = '/customer/activity';
                $("#activity").addClass('layui-this').siblings().removeClass('layui-this');
            } else if (id == "third_find") {
                geturl = '/customer/sales-opportunity';
                $("#salesOpportunity").addClass('layui-this').siblings().removeClass('layui-this');
            } else if (id == "four_find") {
                geturl = '/customer/annex';
                $("#annex").addClass('layui-this').siblings().removeClass('layui-this');
            }

            $.ajax({
                url: ctx + geturl,
                data:{customerId:customerId,crmFileId:crmFileId},
                success: function (data) {
                    $('.overview').html(data);
                },
                error: function () {
                    yaya.layer.msg("请求超时");
                }

            })
        }
    )

    $('.customer_linkMan').click(function () {
        var nid=$(this).attr('id');
        var iTop = (window.screen.height-30-550)/2; //获得窗口的垂直位置;
        var iLeft = (window.screen.width-10-800)/2; //获得窗口的水平位置;
        var win= window.open(ctx + '/linkMan/detail-page?linkManId='+nid+'&&state=0','win3',
            'height=600,innerHeight=600,width=1000,innerWidth=1000,top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
    })

    $('.customer_activity').click(function () {
        var nid = $(this).attr('id');
        var iTop = (window.screen.height-30-550)/2; //获得窗口的垂直位置;
        var iLeft = (window.screen.width-10-800)/2; //获得窗口的水平位置;
        var win= window.open(ctx + '/activity/detail?activityId='+nid+'&&state=0','win3',
            'height=600,innerHeight=600,width=1000,innerWidth=1000,top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
    })

    $('#addShareMan').click(function () {
        var choose=$(this);
        $.ajax({
            url:ctx+'/customer/edit-responsePerson',
            success:function(str){
                yaya.layer.open({
                    type: 1,
                    title: '选择负责人',
                    content: str, //注意，如果str是object，那么需要字符拼接。
                    area: ['420px', '450px'],
                    shadeClose: true,
                    btn: ['保存'],
                    success: function (layer, index) {

                    },
                    yes: function (index) {
                        //先判定是否重复
                        var y=$("#selecttree2 .coloritem_right").length;
                        if($("#selecttree2 .coloritem_right").length > 0){
                            var id=$("#selecttree2 .coloritem_right").attr('id').replace("-","");
                            var adminName = $("#selecttree2 .coloritem_right").text();
                            if($("#creatorHiddenId").val() == id){
                                yaya.layer.msg("该成员为客户创建人!");
                            }else {
                                $.ajax({
                                    url: ctx + '/customer/shareMan-repeat',
                                    data: {customerId: customerId, adminId: id},
                                    success: function (data) {
                                        if (data.code) {
                                            var loadIndex = yaya.layer.load(2);
                                            $.ajax({
                                                url: ctx + '/customer/save-shareMan',
                                                data: {customerId: customerId, adminId: id,adminName:adminName},
                                                success: function (data) {
                                                    if (data.code) {
                                                        yaya.layer.close(loadIndex);
                                                        yaya.layer.msg("保存成功");
                                                        setTimeout(function () {
                                                            history.go(0);
                                                        }, 400);
                                                    }
                                                },
                                                error: function () {
                                                    yaya.layer.close(loadIndex);
                                                    yaya.layer.msg("请求超时");
                                                }
                                            })
                                        }
                                        else {
                                            yaya.layer.msg("已存在该共享人");
                                        }
                                    }
                                })
                            }
                        }else if($("#selecttree2 .coloritem_right").length == 0){
                            yaya.layer.msg("请选择负责人");
                        }

                    }
                })
            }
        })
    })

    var mydate = new Date();
    var otherLinkMancrmId = mydate.getTime();
    //客户中新建联系人功能
    $("#addCustomerLinkMan").click(function () {
        $.ajax({
            url: ctx + '/linkMan/add-page',
            method: 'post',
            data:{customerId:customerId},
            dataType: 'html',
            success: function (str) {
                yaya.layer.open({
                    type: 1,
                    title: '联系人新增',
                    content: str, //注意，如果str是object，那么需要字符拼接。
                    area: ['600px','400px'],
                    shadeClose: true,
                    btn: ['保存'],
                    success: function (layer, index) {
                        uploaderLinkMan()
                    },
                    yes: function (index) {
                        if ($.trim($('#name').val())=="") {
                            yaya.layer.msg("请填写联系人姓名");
                        }
                        else if ($('.responsiblePersonId').length == 0) {
                            yaya.layer.msg("请选择负责人");
                        }
                        else if ($('.relationCustomerId').length == 0) {
                            yaya.layer.msg("请选择关联客户");
                        }
                        else {
                            var loadIndex = yaya.layer.load(2);
                            $(".item").each(function () {
                                var id=$(this).attr("id");
                                setTimeout(function () {
                                    otherLinkUploader.upload(id);
                                },400)
                            })
                            $.ajax({
                                url: ctx + '/linkMan/save',
                                traditional: true,
                                data: {
                                    name: $('#name').val(),
                                    responsiblePersonId: $('.responsiblePersonId').attr('id'),
                                    relationCustomerId: $('.relationCustomerId').attr('id'),
                                    role: $('#role').val(),
                                    phone: $.trim($('#phone').val()),
                                    dept: $.trim($('#depart').val()),
                                    job: $('#job').val(),
                                    mobilePhone: $('#mobilePhone').val(),
                                    QQ: $('#QQ').val(),
                                    relationEmail: $('#relationEmail').val(),
                                    birthday: $.trim($('#birthday').val()),
                                    address: $('#address').val(),
                                    zipCode: $('#zipCode').val(),
                                    remarks: $('#remarks').val(),
                                    linkManFile:otherLinkMancrmId
                                },

                                method: 'post',
                                dataType: 'json',
                                success: function (data) {
                                    yaya.layer.close(loadIndex);
                                    yaya.layer.close(index);
                                    if (data.code) {
                                        yaya.layer.msg("保存成功");
                                        setTimeout(function () {
                                            history.go(0);
                                        },400);
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

    //点击新建活动功能
    var otherActivitycrmId = mydate.getTime();
    $('#addCustomerActivity').click(function () {
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
                        uploaderActivity();
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
                                    otherActivityUploader.upload(id);
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
                                    activityTypeAddDesc: $.trim($('#activityTypeAddDesc').val()),
                                    activityType: $('#activityType').val(),
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
                                    activityFile:otherActivitycrmId,
                                    approverName : $('.approverId').html(),
                                },

                                method: 'post',
                                dataType: 'json',
                                success: function (data) {
                                    yaya.layer.close(loadIndex);
                                    yaya.layer.close(index);
                                    if (data.code) {
                                        yaya.layer.msg("保存成功");
                                        setTimeout(function () {
                                            history.go(0);
                                        },400);
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

    $("#followUpChange").change(function () {

        var followUp = $("#followUpChange").val();
        $.ajax({
            url:ctx+'/customer/followUp-content',
            data:{customerId:customerId,followUp: followUp},
            success:function (data) {
                $("#follow_up_content").html(data)
            }
        })
    })

    var myUploader;

    myUploader = WebUploader.create({
        auto: true,
        swf: ctx + '/static/webuploader/Uploader.swf',
        server: ctx + '/file/crmFile-upload',
        pick: '#picker',
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
        yaya.layer.msg("保存成功");
        setTimeout(function () {
            history.go(0);
        },400);

    });

    // 当有文件被添加进队列的时候
    myUploader.on('fileQueued', function (file) {
            myUploader.options.formData = {crmId: crmFileId,state:1};
    });

    // 文件上传过程中创建进度条实时显示。
    myUploader.on('uploadProgress', function (file, percentage) {

    });


    myUploader.on('uploadError', function (file) {
        $('#' + file.id).find('p.state').text('上传出错').css({"color": "#FF5722"});
    });

    myUploader.on('uploadComplete', function (file) {
        $('#' + file.id).find('.progress').fadeOut();
    });




    var otherLinkUploader;
    function uploaderLinkMan() {
        otherLinkUploader = WebUploader.create({
            auto: false,
            swf: ctx + '/static/webuploader/Uploader.swf',
            server: ctx + '/file/crmFile-upload',
            pick: '#linkManPicker',
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

        otherLinkUploader.on('uploadSuccess', function (file, responseData) {
            $('#' + file.id).find('p.state').text('已上传').css({"color": "#5FB878"});
            console.log(responseData.data);//存储时间的路径
            $('#path').val(responseData.data);
            $('#docPath').html(responseData.message);
        });

        // 当有文件被添加进队列的时候
        otherLinkUploader.on('fileQueued', function (file) {

            $('.webuploader-pick').text('继续添加');
            $("#cancelUpLoad").show();
            var same = 0;
            $(".item").each(function () {
                var name = $(this).attr("name");

                if (name == file.name) {
                    same = 1;
                    otherLinkUploader.removeFile(file.id);
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
                otherLinkUploader.options.formData = {crmId: otherLinkMancrmId,state:2};
                $list.append('<div id="' + file.id + '" class="item" name=' + file.name + '>' +
                    '<h4 class="info"><a title=' + file.name + '  class="titlea">' + subName + '</a></h4>' +
                    '<span class="imgspan"><img src=' + ctx + '/img/closepm.png  class="removeFile" id=' + file.id + '></span>' +
                    '<div class="clearfix"></div>' +
                    '<div class="state" style="color: #1E9FFF">等待上传...</div>' +
                    '</div>');

                $('.removeFile').click(function () {
                    var id = this.id;
                    otherLinkUploader.removeFile(id);
                    $(this).parent().parent('.item').remove();
                })
            }

        });
        otherLinkUploader.on('uploadError', function (file) {
            $('#' + file.id).find('p.state').text('上传出错').css({"color": "#FF5722"});
        });

        otherLinkUploader.on('uploadComplete', function (file) {
            $('#' + file.id).find('.progress').fadeOut();
        });
        return otherLinkUploader;
    }


    var otherActivityUploader;
    function uploaderActivity() {
        otherActivityUploader = WebUploader.create({
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

        otherActivityUploader.on('uploadSuccess', function (file, responseData) {
            $('#' + file.id).find('p.state').text('已上传').css({"color": "#5FB878"});
            console.log(responseData.data);//存储时间的路径
            $('#path').val(responseData.data);
            $('#docPath').html(responseData.message);
        });

        // 当有文件被添加进队列的时候
        otherActivityUploader.on('fileQueued', function (file) {

            $('.webuploader-pick').text('继续添加');
            $("#cancelUpLoad").show();
            var same = 0;
            $(".item").each(function () {
                var name = $(this).attr("name");

                if (name == file.name) {
                    same = 1;
                    otherActivityUploader.removeFile(file.id);
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
                otherActivityUploader.options.formData = {crmId: otherLinkMancrmId,state:3};
                $list.append('<div id="' + file.id + '" class="item" name=' + file.name + '>' +
                    '<h4 class="info"><a title=' + file.name + '  class="titlea">' + subName + '</a></h4>' +
                    '<span class="imgspan"><img src=' + ctx + '/img/closepm.png  class="removeFile" id=' + file.id + '></span>' +
                    '<div class="clearfix"></div>' +
                    '<div class="state" style="color: #1E9FFF">等待上传...</div>' +
                    '</div>');

                $('.removeFile').click(function () {
                    var id = this.id;
                    otherActivityUploader.removeFile(id);
                    $(this).parent().parent('.item').remove();
                })
            }

        });
        otherActivityUploader.on('uploadError', function (file) {
            $('#' + file.id).find('p.state').text('上传出错').css({"color": "#FF5722"});
        });

        otherActivityUploader.on('uploadComplete', function (file) {
            $('#' + file.id).find('.progress').fadeOut();
        });
        return otherActivityUploader;
    }



})