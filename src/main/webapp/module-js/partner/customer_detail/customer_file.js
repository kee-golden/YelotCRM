require(['jquery', 'yaya','webuploader', 'datatables.net', 'ztree'], function ($, yaya,WebUploader) {
    var myUploader;
    var loadIndex;
    $('.tab_content').fadeIn(1000);
    var customerId = $("#customerId").val();
    var crmFileId = $("#crmFileId").val();
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
        $.ajax({
            url:ctx+'/customer/annex',
            data:{customerId:customerId,crmFileId:crmFileId},
            success:function (data) {
                $('.overview').html(data);
                yaya.layer.close(loadIndex);
            },
            error:function () {
                yaya.layer.msg("请求超时");
            }
        })
    });

    // 当有文件被添加进队列的时候
    myUploader.on('fileQueued', function (file) {

            myUploader.options.formData = {crmId: crmFileId,state:1};
        loadIndex = yaya.layer.load(2);

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


    //删除文件功能
    $('.delete').click(function(){
        var id = $(this).attr('id');
        yaya.layer.confirm('请确认是否删除？',{
            btn:['确定','取消']//按钮
        },function (index) {
            $.ajax({
                method: 'post',
                url: ctx + '/customer/file-delete',
                dataType: 'json',
                data:{id:id},
                success: function (msg) {
                    if (msg.code) {
                        yaya.layer.msg("删除成功");
                        $.ajax({
                            url:ctx+'/customer/annex',
                            data:{customerId:customerId,crmFileId:crmFileId},
                            success:function (data) {
                                $('.overview').html(data);
                                yaya.layer.close(loadIndex);
                            },
                            error:function () {
                                yaya.layer.msg("请求超时");
                            }
                        })
                    }
                }
            });
        })
    })



})
