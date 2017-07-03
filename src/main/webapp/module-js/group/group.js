/**
 * Created by kee on 16/12/10.
 */


require(['jquery', 'yaya', 'datatables.net', 'validate'], function ($, yaya,validate) {

    $(function(){



                $('#J_groupSave').click(function(){

                    console.log("group save click")

                    var code = $('#code').val();
                    var name = $('#name').val();
                    if(name ==''){
                        yaya.layer.msg("名称不能为空值！")
                        return;

                    }
                    if(code ==''){
                        yaya.layer.msg("组织简称不能为空值！")
                        return;

                    }


                    $.ajax({
                        url: ctx + '/group/save',
                        data: {
                            id: $('#id').val(),
                            code: $('#code').val(),
                            name: $('#name').val(),

                        },
                        method: 'post',
                        dataType: 'json',
                        success: function (result) {
                            if(result.code){
                                yaya.layer.msg("保存成功!");

                            }else{
                                yaya.layer.msg("全称或简称已存在!");

                            }

                        }
                    });

        });
    });


});
