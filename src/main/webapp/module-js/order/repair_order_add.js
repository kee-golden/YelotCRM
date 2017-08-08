/**
 * Created by kee on 17/7/13.
 */

require(['jquery','yaya','echarts','bootstrap'], function ($, yaya,echarts,DrawEChart) {

    //获取当前默认的属性json对象
    generateAttributes(attributesJson);



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

            var secondCategory=$(".city").children('option:selected').val();//这就是selected的值
            var firstCategory=$(".prov").children('option:selected').val();//这就是selected的值
            ajaxAttribute(firstCategory,secondCategory);



        });
    $('.prov').change(function () {

        var secondCategory=$(".city").children('option:selected').val();//这就是selected的值
        var firstCategory=$(".prov").children('option:selected').val();//这就是selected的值
        ajaxAttribute(firstCategory,secondCategory);
    });

    function ajaxAttribute(firstCategory,secondCategory) {

        $.ajax({
            url:'/repair-order/get-attributes',
            dataType:"json",
            method:'get',
            data:{
                firstCategory:firstCategory,
                secondCategory:secondCategory,
            },
            success:function (data) {

                attributesJson = data.data.attributeList;
                generateAttributes(attributesJson);

            }


        });

    }


    /**
     *
     * @param attributesJson
     */
    function generateAttributes(attributesJson) {
            //var attributesDiv = $("#attributes");
        $("#attributes").empty();//清空
            for(var i=0;i<attributesJson.length;i++){
                var labelStr = '<label>'+attributesJson[i].attributeName+':</label>';
                $("#attributes").append(labelStr);
                if(attributesJson[i].type == 1){
                    var selectStr = '<select name=\"'+attributesJson[i].attributeName+'\">';
                    var optionStr = '';
                    var valuesArray = attributesJson[i].selectionValues.split(',');
                    for(var j = 0;j<valuesArray.length;j++){
                        optionStr = optionStr + '<option value=\"'+valuesArray[j]+'\">'+valuesArray[j]+'</option>';
                    }
                    $("#attributes").append(selectStr+optionStr);
                    $("#attributes").append('</select>');

                }else if(attributesJson[i].type == 2){
                    var selectStr2 = '<input type=\"text\" name = \"'+attributesJson[i].attributeName+"\"/>";
                    $("#attributes").append(selectStr2);

                }
            }

        }




});






