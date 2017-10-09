/**
 * Created by kee on 17/7/13.
 */

require(['jquery','yaya','selector2','cityselect','dateTimePicker'], function ($, yaya,selector2,cityselect) {

// 手机号码验证,自定义函数
    jQuery.validator.addMethod("isMobile", function(value, element) {
        var length = value.length;
        var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, "请正确填写手机号码");

    //获取当前默认的属性json对象,attributeJson,categoryServiceJson,两个对象定义在jsp中
    generateAttributes(attributesJson);
    generateCategroyServices(categoryServiceJson);


    var prifixAttribute = 'attId_';// 属性前缀

    $("#serviceItem").select2({
        tags: true,
    });

    $('#addCustomer').click(function () {
        console.log("addCustomer invoke");
        $.ajax({
            url: ctx + '/repair-order/add-customer',
            method: 'get',
            dataType: 'html',
            success: function (str) {
                yaya.layer.open({
                    type: 1,
                    title: '新建客户',
                    content: str, //注意，如果str是object，那么需要字符拼接。
                    area: '550px',
                    shadeClose: true,
                    btn: ['保存'],
                    success: function (layer, index) {
                        //初始化值
                        $("#J_name").val("");
                        checkCustomerFrom();
                    },
                    yes: function (index) {
                        var isValid = $("#J_customerForm").valid();

                        if (isValid) {

                            $.ajax({
                                url: ctx + '/customer/save',
                                data: $('#J_customerForm').serialize(),
                                method: 'post',
                                dataType: 'json',
                                success: function (data) {

                                    if(data.code == 1200){
                                        $("#customerId").data("id",data.data.customer.id);//复制customer id
                                        $("#customerContainer").css("display","block");
                                        $("#customerTip").css("display","none");
                                        $("#J_name").val(data.data.customer.name);
                                        $("#J_phone").val(data.data.customer.phone);
                                        $("#phone").val(data.data.customer.phone);//同步查询的手机号
                                        $("#J_address").val(data.data.customer.address);
                                        $("#province").html(data.data.customer.province);
                                        $("#city").html(data.data.customer.city);

                                    }else {
                                        $("#customerTip").css("display","block");
                                        $("#customerContainer").css("display","none");
                                    }

                                    if (data.code == 1200) {
                                        yaya.layer.close(index);

                                    }
                                },
                                error: function (data) {
                                    console.log(data);

                                }
                            });
                        }

                    }

                });
            },
            error: function () {

            }
        });

    });

    function checkCustomerFrom() {
        yaya.validate({
            el: '#J_customerForm',
            rules: {
                phone:{
                    required:true,
                    isMobile:true,
                    remote:{
                        url:ctx+"/customer/check-phone",
                        type:"post",
                        data:{
                            phone:function () {
                                return $("#J_customerPhone").val();
                            }
                        }
                    }

                },
                name:{
                    required: true
                }
            },
            messages: {
                phone: {
                    required:"手机号不能为空",
                    remote:"手机号已存在"
                },
                name:{
                    required:"姓名不能为空"
                }
            }
        });

    }




    $("#saveBtn").click(function () {

        var customerId = $('#customerId').data("id");
        console.log("customerId:"+customerId);
        if(customerId == ""){
            yaya.layer.msg("你还没有选中客户，请先通过电话号码，查询客户！")
            return;
        }

        var consultOrderId = $('#consultOrderId').val();
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
        var labourPayment = $('#labourPaymentFlag').val() == "0" ? -1 : $('#labourPayment').val();
        var materialPayment = $('#materialPaymentFlag').val() == "0" ? -1 : $('#materialPayment').val();
        var discountAmountPayment = $('#discountAmountPaymentFlag').val() == "0" ? -1 : $('#discountAmountPayment').val();
        var pickupDate = $('#pickupDate').val();
        var discountDesc = $('#discountDesc').val();
        console.log(labourPayment+","+labourPayment+","+materialPayment);
        $.ajax({
            url: ctx + '/repair-order/save',
            method: 'post',
            dataType: 'json',
            data: {
                customerId: customerId,
                consultOrderId: consultOrderId,
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
                discountAmountPayment:discountAmountPayment,
                pickupDate:pickupDate,
                discountDesc:discountDesc
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
                        $("#province").html(data.data.customer.province);
                        $("#city").html(data.data.customer.city);

                    }else {
                        $("#customerTip").css("display","block");
                        $("#customerContainer").css("display","none");
                    }


                }
            });

        });

        $('#linkToConsultOrder').click(function () {
        	var phone = $("#phone").val();
        	if (phone == null || phone == "") {
				alert("请输入手机号！");
			} else {
				yaya.layer.open({
	                type: 2,
	                title: '咨询单列表',
	                content: ctx + '/repair-order/consultOrderList?phone=' + phone, //注意，如果str是object，那么需要字符拼接。
	                area: ['1000px','600px'],
	                shadeClose: true,
	                btn: ['确定'],
	                success: function (layer, index) {
	                	
	                },
	                yes: function (index) {
	                	var consultOrderId = window["layui-layer-iframe" + index].callbackdata();
	                	$("#consultOrderId").val(consultOrderId);
                        yaya.layer.close(index);
	                }
	            });
			}
        });

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
                firstCategory:firstCategory,
                secondCategory:secondCategory,
                isEdit:false,
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
                var labelStr = '<td align="right" style="width: 8%"><label>'+attributesJson[i].attributeName+'：</label></td>';
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

    $("#labourPaymentFlag").change(function() {
    	if($("#labourPaymentFlag").val() == "0"){
    		// $("#labourPayment").val("待定");
    		$("#labourPayment").attr("readonly", true);
    	} else {
    		// $("#labourPayment").val("");
    		$("#labourPayment").attr("readonly", false);
    	}
	});

    $("#materialPaymentFlag").change(function() {
    	if($("#materialPaymentFlag").val() == "0"){
    		// $("#materialPayment").val("待定");
    		$("#materialPayment").attr("readonly", true);
    	} else {
    		// $("#materialPayment").val("");
    		$("#materialPayment").attr("readonly", false);
    	}
	});

    $("#discountAmountPaymentFlag").change(function() {
        console.log("discountAmount");
        if($("#discountAmountPaymentFlag").val() == "0"){
            console.log("discountAmount 0");

            // $("#discountAmountPayment").val("待定");
            $("#discountAmountPayment").attr("readonly", true);
        } else {
            console.log("discountAmount 1");

            // $("#discountAmountPayment").val("");
            $("#discountAmountPayment").attr("readonly", false);
        }
    });

});






