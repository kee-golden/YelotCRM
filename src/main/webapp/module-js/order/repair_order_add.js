/**
 * Created by kee on 17/7/13.
 */

    require(['jquery','yaya','echarts','bootstrap'], function ($, yaya,echarts,DrawEChart) {


        /* 菜单下拉功能*/
        $("h6 i").click(
            function(){
                var div=( $(this).parent().parent().find(".row"));
                div.slideToggle();
            }
        );

        $('#customerSearchBtn').click(function () {
            $.ajax({
                url: ctx + '/customer/search',
                method: 'get',
                dataType: 'json',
                data: {
                    phone: $("#phone").val(),
                },
                success: function (data) {

                    if(data.code == 1200){
                        $("#customerId").data("id",data.data.customer.id);//复制customer id
                        $("#customerContainer").css("display","block");
                        $("#customerTip").css("display","none");
                        $("#J_name").val(data.data.customer.name);
                        $("#J_phone").val(data.data.customer.phone);
                        $("#J_address").val(data.data.customer.address);
                        $("#prov_city").citySelect({
                            url:"/static/cityselect/js/city.min.js",
                            prov:data.data.customer.province,
                            city:data.data.customer.city,
                            nodata:"none",
                            required:false
                        });


                    }else {
                        $("#customerTip").css("display","block");
                        $("#customerContainer").css("display","none");
                    }


                }
            });

        });

        $('.city').change(function () {
            console.log("secondCategory change (),,,")

            var p1=$(this).children('option:selected').val();//这就是selected的值

            console.log(p1);

        });




    });

function table_PendingCount() {
    $.ajax({
        url:ctx+'/indexshow/index-PendingCount',
        async:false,
        success:function(result){
            var s=$("#countAccepted");
            s.html(result);
        }
    });
    var span=$("#countAccepted");
    var val=$(span).html();
    if(val!=0){
        $(span).show();
    }else if (val==0){
        $(span).hide();
    }
}

function table_AuditCount() {
    $.ajax({
        url: ctx + '/indexshow/index-AuditCount',
        async:false,
        success: function (result) {
            var s = $("#count");
            s.html(result);
        }
    });
    var span=$("#count");
    var val=$(span).html();
    if (val != 0) {
        $(span).show();
    }else if (val==0){
        $(span).hide();
    }
}
function table_RequestCount() {
    $.ajax({
        url: ctx + '/indexshow/index-RequestCount',
        async:false,
        success: function (result) {
            var s = $("#countRequest");
            s.html(result);
        }
    });
    var span=$("#countRequest");
    var val=$(span).html();
    if(val!=0){
        $(span).show();
    }else if (val==0){
        $(span).hide();
    }
}

function table_total() {
    $.ajax({
        url: ctx + '/indexshow/index-total',
        async:false,
        success: function (result) {
            var s = $("#total");
            s.html(result);
        }
    });
    var span=$("#total");
    var val=$(span).html();
    if(val!=0){
        $(span).show();
    }else if (val==0){
        $(span).hide();
    }
}

