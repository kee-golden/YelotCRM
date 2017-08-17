/**
 * Created by kee on 17/7/13.
 */

require(['jquery','yaya','selector2','cityselect','dateTimePicker'], function ($, yaya,selector2,cityselect) {



    //获取当前默认的属性json对象,attributeJson,categoryServiceJson,两个对象定义在jsp中
    generateAttributes(attributesJson);
    generateCategroyServices(categoryServiceJson);


    var prifixAttribute = 'attId_';// 属性前缀

    $("#serviceItem").select2({
        tags: true,
    });

    $("#saveBtn").click(function () {

        var customerId = $('#customerId').data("id");
        console.log("customerId:"+customerId);
        if(customerId == ""){
            yaya.layer.msg("你还没有选中客户，请先通过电话号码，查询客户！")
            return;
        }

        var firstCategory = $('#firstCategory').val();
        var secondeCategory = $('#secondCategory').val();
        console.log(firstCategory+","+secondeCategory);

        var valuesAttributeJson =  getAttributeValues(attributesJson);
        var serviceItemJson = $('#serviceItem').val();
        console.log("serviceItemJson:"+serviceItemJson);



    });

    function getAttributeValues(attributesJson) {
        //
        var attrValuesJson = [];
        // var attr1 = {};
        // var attr2 = {};
        // attr1.id = 1;
        // attr1.attributeName="AA";
        // attr1.selectionValues="A1";
        // attr2.id=2;
        // attr2.attributeName="BB";
        // attr2.selectionValues="B1";
        //
        // attrValuesJson.push(attr1);
        // attrValuesJson.push(attr2);

        for(var i = 0;i<attributesJson.length;i++){
            var objJson = {};
            objJson["id"] = attributesJson[i].id;
            objJson["attributeName"] = attributesJson[i].attributeName;
            objJson["selectionValues"] = $('#attId_'+attributesJson[i].id).val();
            objJson["type"] = attributesJson[i].type;
            attrValuesJson.push(objJson);
        }

        console.log(JSON.stringify(attrValuesJson));

        return attrValuesJson;
    }


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
            ajaxAttributeAndService(firstCategory,secondCategory);

        });
    $('.prov').change(function () {

        //延迟执行，获取secondCategory 否则无法正确获取值.
        setTimeout(provChangeDelay,1000);

    });

    function provChangeDelay() {

        var firstCategory=$(".prov").children('option:selected').val();//这就是selected的值
        var secondCategory=$(".city").children('option:selected').val();//这就是selected的值

        console.log(firstCategory+","+secondCategory);
        ajaxAttributeAndService(firstCategory,secondCategory);



    }

    /**
     * 获取属性，和服务项
     * @param firstCategory
     * @param secondCategory
     */
    function ajaxAttributeAndService(firstCategory,secondCategory) {

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
                categoryServiceJson = data.data.categoryServiceItemList;
                generateCategroyServices(categoryServiceJson);

            }


        });

    }


    /**
     *
     * @param attributesJson
     */
    function generateAttributes(attributesJson) {
        var prifixAttribute = 'attId_';// 属性前缀
        //var attributesDiv = $("#attributes");
        $("#attributes").empty();//清空
        	if (attributesJson.length != 0) {
                $("#attributes").append('<table style="width: 100%" border="1">');
			}
            for(var i=0;i<attributesJson.length;i++){
            	if (i % 4 == 0) {
                    $("#attributes").append('<tr>');
				}
                var labelStr = '<td align="right" style="width: 8%"><label>'+attributesJson[i].attributeName+':</label></td>';
                $("#attributes").append(labelStr);
                if(attributesJson[i].type == 1){
                    var selectStr = '<td align="left" style="width: 15%"><select name=\"'+attributesJson[i].attributeName+'\" id=\"'+prifixAttribute+attributesJson[i].id+'\">';
                    var optionStr = '';
                    var valuesArray = attributesJson[i].selectionValues.split(',');
                    for(var j = 0;j<valuesArray.length;j++){
                        optionStr = optionStr + '<option value=\"'+valuesArray[j]+'\">'+valuesArray[j]+'</option>';
                    }
                    $("#attributes").append(selectStr+optionStr);
                    $("#attributes").append('</select></td>');

                }else if(attributesJson[i].type == 2){
                    var selectStr2 = '<td align="left"><input type=\"text\" name = \"'+attributesJson[i].attributeName+'\" id=\"'+prifixAttribute+attributesJson[i].id+"\"/></td>";
                    $("#attributes").append(selectStr2);

                }
            	if (i % 4 == 0) {
                    $("#attributes").append('</tr>');
				}
            }
        	if (attributesJson.length != 0) {
                $("#attributes").append('</table>');
			}

    }

    function generateCategroyServices(categoryServiceJson) {
        $("#serviceItem").empty();
        for(var i = 0;i < categoryServiceJson.length;i++){
            var str = '<option value=\"'+categoryServiceJson[i].serviceItemId+'\">'+categoryServiceJson[i].serviceName+'</option>';
            $("#serviceItem").append(str);

        }

    }




});






