/**
 * Created by gc on 2017/4/18 0018.
 */

require(['jquery', 'yaya', 'datatables.net'], function ($, yaya) {
    $(".resname").click(
        function () {
            $.ajax({
                url:ctx+'/customer/edit-responsePerson',
                success:function(str){
                    yaya.layer.open({
                        type: 1,
                        title: '关联客户',
                        content: str, //注意，如果str是object，那么需要字符拼接。
                        area: ['420px', '450px'],
                        shadeClose: true,
                        btn: ['保存'],
                        success: function (layer, index) {

                        },
                        yes: function (index) {
                            var y=$("#selecttree2 .coloritem_right").length;
                            if($("#selecttree2 .coloritem_right").length){
                                $(".resname span").empty();
                                var text=$("#selecttree2 .coloritem_right").text();
                                var id=$("#selecttree2 .coloritem_right").attr('id').replace("-","");
                                $(".resname span").append('<div class=responsiblePersonId  id='+id+' >'+text+'</div>')
                                yaya.layer.close(index)
                            }


                        }
                    })
                }
            })
        }
    )





})