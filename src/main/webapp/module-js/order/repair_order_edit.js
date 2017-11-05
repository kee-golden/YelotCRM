/**
 * Created by kee on 17/7/13.
 */

require(['jquery','yaya','selector2','cityselect','dateTimePicker'], function ($, yaya,selector2,cityselect) {


    //获取当前默认的属性json对象,attributeJson,categoryServiceJson,两个对象定义在jsp中
    generateAttributes(attributesJson);
    generateCategroyServices(categoryServiceJson);
    generateRefOrderIdsJson(refOrderIdsJson);


    var prifixAttribute = 'attId_';// 属性前缀

    $("#serviceItem").select2({
        tags: true,
    });

    $("#refOrderIds").select2({
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
        var secondCategory = $('#secondCategory').val();
        var brandId = $('#brandId').val();

        var valuesAttributeJson =  getAttributeValues(attributesJson);
        var serviceItemJson = $('#serviceItem').val();

        var imagePaths = $('.filelist').data('path');
        var imageDesc = $('#imageDesc').val();
        var repairDesc = $('#repairDesc').val();
        var typeName = $('#typeName').val();
        var advancePayment = $('#advancePayment').val();
        var labourPayment = $('#labourPayment').val() == "待定" ? -1 : $('#labourPayment').val();
        var materialPayment = $('#materialPayment').val() == "待定" ? -1 : $('#materialPayment').val();
        var pickupDate = $('#pickupDate').val();
        console.log(labourPayment+","+labourPayment+","+materialPayment);
        $.ajax({
            url: ctx + '/repair-order/save',
            method: 'post',
            dataType: 'json',
            data: {
                customerId: customerId,
                firstCategory: firstCategory,
                secondCategory: secondCategory,
                brandId:brandId,
                valuesAttributeJson:JSON.stringify(valuesAttributeJson),
                serviceItemJson:JSON.stringify(serviceItemJson),
                imagePaths:imagePaths,
                imageDesc:imageDesc,
                repairDesc:repairDesc,
                typeName:typeName,
                advancePayment:advancePayment,
                labourPayment:labourPayment,
                materialPayment:materialPayment,
                pickupDate:pickupDate

            },
            success: function (data) {
                if(data.code == 1200){
                    yaya.layer.msg("提交成功");
                    setTimeout(function () {
                        window.location.href = ctx+'/repair-order/mylist';
                    },1000);

                }

            }

        });

    });

    function getAttributeValues(attributesJson) {
        //
        var attrValuesJson = [];

        for(var i = 0;i<attributesJson.length;i++){
            var objJson = {};
            objJson["id"] = attributesJson[i].id;
            objJson["attributeName"] = attributesJson[i].attributeName;
            objJson["selectionValues"] = $('#attId_'+attributesJson[i].id).val();
            objJson["type"] = attributesJson[i].type;
            attrValuesJson.push(objJson);
        }

        // console.log(JSON.stringify(attrValuesJson));

        return attrValuesJson;
    }


    /* 菜单下拉功能*/
        $("h6 i").click(
            function(){
                var div=( $(this).parent().parent().find(".row"));
                div.slideToggle();
            }
        );


        $('#secondCategory').change(function () {

            var secondCategory=$("#secondCategory").children('option:selected').val();//这就是selected的值
            var firstCategory=$("#firstCategory").children('option:selected').val();//这就是selected的值
            ajaxAttributeAndService(firstCategory,secondCategory);

        });
    $('#firstCategory').change(function () {

        //延迟执行，获取secondCategory 否则无法正确获取值.
        setTimeout(provChangeDelay,1000);

    });

    function provChangeDelay() {

        var firstCategory=$("#firstCategory").children('option:selected').val();//这就是selected的值
        var secondCategory=$("#secondCategory").children('option:selected').val();//这就是selected的值

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
                id:$('#orderId').val(),
                firstCategory:firstCategory,
                secondCategory:secondCategory,
                isEdit:true,
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
     * 生成html
     * @param attributesJson
     */
    function generateAttributes(attributesJson) {
        console.log('kee:'+attributesJson);
        if(attributesJson == ''){
            return;
        }
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
                var labelStr = '<td align="right" style="width: 8%"><label>'+attributesJson[i].attributeName+'：</label></td>';
                $("#attributes").append(labelStr);
                if(attributesJson[i].type == 1){
                    var selectStr = '<td align="left" style="width: 15%"><select name=\"'+attributesJson[i].attributeName+'\" id=\"'+prifixAttribute+attributesJson[i].id+'\">';
                    var optionStr = '';
                    var valuesArray = attributesJson[i].selectionValues.split(',');
                    for(var j = 0;j<valuesArray.length;j++){
                        if(valuesArray[j] == attributesJson[i].realValue){
                            optionStr = optionStr + '<option value=\"'+valuesArray[j]+'\" selected=\"selected\">'+valuesArray[j]+'</option>';
                        }else {
                            optionStr = optionStr + '<option value=\"'+valuesArray[j]+'\">'+valuesArray[j]+'</option>';
                        }
                    }
                    $("#attributes").append(selectStr+optionStr);
                    $("#attributes").append('</select></td>');

                }else if(attributesJson[i].type == 2){
                    var selectStr2 = '<td align="left">' +
                        '<input type=\"text\" name = \"'+attributesJson[i].attributeName+'\" id=\"'+prifixAttribute+attributesJson[i].id+'\" value=\"'+attributesJson[i].realValue+'\"/></td>\"';
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
            var selected = categoryServiceJson[i].selectedStatus;
            var selectedStr = '';
            if(selected){
                selectedStr = 'selected=\"selected\"';
                //var tempStr = '<li class="select2-selection__choice" title="清洁保养"><span class="select2-selection__choice__remove" role="presentation">×</span>清洁保养</li>';
                //$("#serviceItem").val(categoryServiceJson[i].serviceItemId);

            }else {
                selectedStr = '';
            }
            var str = '<option value=\"'+categoryServiceJson[i].serviceItemId+'\"' + selectedStr+ '>'+categoryServiceJson[i].serviceName+'</option>';
            $("#serviceItem").append(str);


        }

    }

    function generateRefOrderIdsJson(refOrderIdsJson) {
        $("#refOrderIds").empty();
        if(refOrderIdsJson != null){
            for(var i = 0;i < refOrderIdsJson.length;i++){
                var selected = refOrderIdsJson[i].selectedStatus;
                var selectedStr = '';
                if(selected){
                    selectedStr = 'selected=\"selected\"';
                }else {
                    selectedStr = '';
                }
                var str = '<option value=\"'+refOrderIdsJson[i].orderNo+'\"' + selectedStr+ '>'+refOrderIdsJson[i].orderNo+'</option>';
                $("#refOrderIds").append(str);
            }
        }
    }

    $("#labourPaymentFlag").change(function() {
    	if($("#labourPaymentFlag").val() == "0"){
    		$("#labourPayment").val("待定");
    		$("#labourPayment").attr("readonly", true);
    	} else {
    		$("#labourPayment").val("");
    		$("#labourPayment").attr("readonly", false);
    	}
	});

    $("#materialPaymentFlag").change(function() {
    	if($("#materialPaymentFlag").val() == "0"){
    		$("#materialPayment").val("待定");
    		$("#materialPayment").attr("readonly", true);
    	} else {
    		$("#materialPayment").val("");
    		$("#materialPayment").attr("readonly", false);
    	}
	});

});






