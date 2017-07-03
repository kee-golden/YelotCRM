/**
 * Created by lenovo on 2017/5/1.
 */
require(['jquery', 'yaya','webuploader', 'datatables.net','editor','layui'], function ($, yaya,WebUploader) {
    $(".replyFade").fadeIn(1000);
    $(".reply_delete").click(function () {
        var id =  $(this).attr('id');
        var a = $(this);
        $.ajax({
            url:ctx+'/customer/delete-reply',
            data:{replyId:id},
            success:function (data) {
                if(data.code){
                    a.parent().parent().parent().remove();
                    yaya.layer.msg("删除成功");
                }
            },
            error:function () {
                yaya.layer.msg("请求超时");
            }
        })
    })
    
})
