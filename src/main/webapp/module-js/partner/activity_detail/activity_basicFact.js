/**
 * Created by lenovo on 2017/4/19.
 */
require(['jquery', 'yaya','webuploader', 'datatables.net','editor','layui'], function ($, yaya,WebUploader) {
  /*  $('#edity').editable({
        inlineMode: false, alwaysBlank: true,
        language: "zh_cn",
        imageUploadURL: 'lib/imgupload.php',//上传到本地服务器
        imageUploadParams: {id: "edit"},
        imageDeleteURL: 'lib/delete_image.php',//删除图片
        imagesLoadURL: 'lib/load_images.php'//管理图片
    });*/
     var layedit;
     var bulidIndex;
     var activityId=$('#activityId').val();

    $.ajax({
        url:ctx+'/activity/followUp-content',
        data:{activityId:activityId,followUp: null},
        success:function (data) {
         $("#follow_up_content").html(data)
        }
    })

    layui.use('layedit', function(){
         layedit = layui.layedit;
        bulidIndex = layedit.build('demo',{
            height: 180, //设置编辑器高度
            tool: ['left', 'center', 'right', '|', 'face']
        }); //建立编辑器
    });

    $("#comment").click(function () {
        var followUp = $("#commentType").val();
        var content = layedit.getContent(bulidIndex);
        if(content == "" || content == null){
            yaya.layer.msg("请填写内容")
        }
        else {
            $.ajax({
                url: ctx + '/activity/save-followUp',
                data: {activityId: activityId, followUp: followUp, content: content},
                success: function (data) {
                    if (data.code) {
                        $.ajax({
                            url: ctx + '/activity/followUp-content',
                            data: {activityId: activityId},
                            success: function (data) {
                                $("#follow_up_content").html(data)
                            }
                        })
                    }
                }
            })
        }
    })

   /* $("#reply").click(function () {
       $.ajax({
           url:ctx+'/activity/reply-content',
           success:function (data) {
               layui.use('layedit', function(){
                   replyLayedit = layui.layedit;
                   reolyBulidIndex = replyLayedit.build('replyTextarea',{
                       height: 100, //设置编辑器高度
                       tool: ['left', 'center', 'right', '|', 'face']
                   }); //建立编辑器
               });
               $("#buttonReply").show();
               $("#buttonCancel").show();
               $(".replay-item-list").html(data);
           }
       })
    })
*/

    $('.over_left').fadeIn(1500);
    $('.over_right').fadeIn(2500);
    var geturl = "";
    var count = $("#activityFileSize").val();
    $("#annex").text("附件资料（"+count+"）");

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
                data:{activityId:activityId,crmFileId:crmFileId},
                success: function (data) {
                    $('.overview').html(data);
                },
                error: function () {
                    yaya.layer.msg("请求超时");
                }

            })
        }
    )

    $("#activityFollowUpChange").change(function () {
        var followUp = $("#activityFollowUpChange").val();
        $.ajax({
            url:ctx+'/activity/followUp-content',
            data:{activityId:activityId,followUp: followUp},
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
        myUploader.options.formData = {crmId: crmFileId,state:3};
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

})