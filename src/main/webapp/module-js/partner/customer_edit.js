/**
 * Created by lenovo on 2017/4/11.
 */
require(['jquery', 'yaya', 'datatables.net'], function ($, yaya) {

    $("#cancelUpLoad").click(function () {
        $(".state").each(function () {
            $(this).text('上传成功').css({"color":"#5FB878"});
            $("#thelist .removeFile").remove();
            $("#thelist .unitem").removeClass('unitem').addClass('item');
        })
    })

    $('#perman,#apprman').click(function () {
              var choose=$(this);
              var chooseClass="";
            if(choose.is($('#perman'))){
                chooseClass="responsiblePersonId";
            }else if(choose.is($('#apprman'))){
                chooseClass="approverId";
            }
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
                           var y = $("#selecttree2 .coloritem_right").length;
                            if($("#selecttree2 .coloritem_right").length > 0){
                                choose.empty();
                                var text=$("#selecttree2 .coloritem_right").text();
                                var id=$("#selecttree2 .coloritem_right").attr('id').replace("-","");
                                choose.append('<div class='+chooseClass+'  id='+id+' >'+text+'</div>')
                                yaya.layer.close(index)
                            }else if($("#selecttree2 .coloritem_right").length == 0){
                                yaya.layer.msg("请选择负责人");
                            }
                        }
                    })
                }
            })

    })

    //省选择区域
    $("#cp").change(function(){
        $("#cc").empty();
        var pId=$("#cp").val();
        //在下拉框最前添加一个选项
        $("#cc").prepend("<option value=''>请选择市</option>");
        $("#ca").empty();
        var cId=$("#cc").val();
        //在下拉框最前添加一个选项
        $("#ca").prepend("<option value=''>请选择县/区</option>");
        $.ajax({
            url:ctx+'/customer/city',
            data:{provinceId:pId},
            success:function(data){
                for(var i=0;i<data.data.length;i++){
                    $("#cc").append("<option value="+data.data[i].cityId+">"+data.data[i].cityName+"</option>");
                }
            },
            error:function(){
                layer.msg("系统异常");
            }
        })
    })

//市选择区域
    $("#cc").change(function(){
        $("#ca").empty();
        var cId=$("#cc").val();
        console.log(cId);
        //在下拉框最前添加一个选项
        $("#ca").prepend("<option value=''>请选择县/区</option>");
        $.ajax({
            url:ctx+'/customer/area',
            data:{cityId:cId},
            success:function(data){
                for(var i=0;i<data.data.length;i++){
                    $("#ca").append("<option value="+data.data[i].areaId+">"+data.data[i].areaName+"</option>");
                }
            },
            error:function(){
                layer.msg("系统异常");
            }
        })
    })

})
