require(['jquery', 'yaya','dateTimePicker'], function ($, yaya) {

    $("#cancelUpLoad").click(function () {
        $(".state").each(function () {
            $(this).text('上传成功').css({"color":"#5FB878"});
            $("#thelist .removeFile").remove();
            $("#thelist .unitem").removeClass('unitem').addClass('item');
        })
    })

    $('#birthday').datetimepicker({
        timepicker:false,
        format:'Y-m-d',
        lang: 'ch',
        step: 1
    });
    $('#startTime,#endTime').datetimepicker({
        timepicker:true,
        format:'Y-m-d H:i',
        lang: 'ch',
        step: 15
    });
    $(".layui-layer-shade").css('z-index','1500');
    $(".layui-layer").css('z-index','2000');


    $("#seleman").click(
        function(){
            $.ajax({
                url:ctx+'/linkMan/add-customer',
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
                         if($('.red').length == 0){
                             yaya.layer.msg("请选择关联客户")
                         }
                         if($('.red').length > 0){
                             $("#seleman").html("");
                             var id=$('.red td').attr('id');
                             var text=$('.red td').text();
                             $("#seleman").append("<div id="+id+" class=relationCustomerId>"+text+"</div> ");
                             yaya.layer.close(index);
                         }
                        }
                    })
                }
            })
        }
    )

    $("#planCost").click(function () {
        $.ajax({
            url:ctx+'/activity/activity-money',
            success:function (data) {
                yaya.layer.open({
                    type: 1,
                    title: '费用合计',
                    content: data, //注意，如果str是object，那么需要字符拼接。
                    area: ['420px'],
                    shadeClose: true,
                    btn: ['保存'],
                    success: function (layer, index) {
                        $("#travelExpense").val($("#travelExpenseBackup").val());
                        $("#entertainExpense").val($("#entertainExpenseBackup").val());
                        $("#otherExpense").val($("#otherExpenseBackup").val());
                    },
                    yes: function (index) {
                         var a =   Number($("#travelExpense").val());
                         var b =  Number($("#entertainExpense").val());
                         var c =  Number($("#otherExpense").val());
                         var d = a+b+c;
                         $("#planCost").text(d);
                         $("#travelExpenseBackup").val(a);
                         $("#entertainExpenseBackup").val(b);
                         $("#otherExpenseBackup").val(c);
                          yaya.layer.close(index);

                    }
                })
            }
        })
    })



})
