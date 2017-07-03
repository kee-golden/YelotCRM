/**
 * Created by lenovo on 2017/5/1.
 */
require(['jquery', 'yaya','webuploader', 'datatables.net','editor','layui'], function ($, yaya,WebUploader) {
    var linkManId=$('#linkManId').val();
    var replyLayedit;
    var replyBulidIndex;
    $(".comment_area").fadeIn(600);
    $(".reply").click(function () {
        $(this).parent().parent().parent().find(".reply-list").toggleClass('reply-list-show')
        var c = $(this);
        var id = $(this).attr('name');
        layui.use('layedit', function(){
            replyLayedit = layui.layedit;
            replyBulidIndex = replyLayedit.build(id,{
                height: 100, //设置编辑器高度
                tool: ['left', 'center', 'right', '|', 'face']
            }); //建立编辑器
        });
        $("#"+id).attr('name',replyBulidIndex);
        $("#"+id).siblings('.btncover').find('.btn').show();
        $.ajax({
            url:ctx+'/linkMan/reply-content',
            data:{linkManRecoId:id},
            success:function (data) {
                c.parent().parent().siblings('.reply-list').find('.replay-item-list').html(data);
            }
        })
    })

    $(".deleteReply").click(function () {
        var id = $(this).attr('name');
        var followUp = $("#linkManFollowUpChange").val();
        $.ajax({
            url:ctx+'/linkMan/delete-comment',
            data:{linkManRecoId:id},
            success:function (data) {
                if(data.code){
                    $.ajax({
                        url:ctx+'/linkMan/followUp-content',
                        data:{linkManId:linkManId,followUp: followUp},
                        success:function (data) {
                            yaya.layer.msg("删除成功")
                            $("#follow_up_content").html(data)
                        }
                    })
                }
            },
            error:function () {
                yaya.layer.msg("请求超时")
            }
        })
    })



    $(".buttonReply").click(function () {
        var index = $(this).parent().siblings(".reply_reply_textarea").attr('name');
        var a = $(this);
        var id = $(this).attr('name');
        var content = replyLayedit.getContent(index);
        if(content == "" || content == null){
            yaya.layer.msg("请填写内容")
        }else {
            $.ajax({
                url:ctx+'/linkMan/save-reply',
                data:{linkManRecoId:id,content:content},
                success:function (data) {
                    if(data.code){
                        $.ajax({
                            url:ctx+'/linkMan/reply-content',
                            data:{linkManRecoId:id},
                            success:function (data) {
                                a.parent().parent().parent().siblings('.replay-item-list').html(data);
                            }
                        })
                    }
                }
            })
        }
    })

})
