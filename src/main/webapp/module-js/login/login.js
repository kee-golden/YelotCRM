/**
 * Created by kee on 16/12/25.
 */
require(['jquery', 'yaya', 'element', 'layui', 'validate'], function ($, yaya, element, layui, validate) {

    var loginDiv = $("#login-div");
    var registerDiv = $("#register-div");
    //初始化输入框为空/., *-/*
    // $("#username").val("");
    $("#password").val("");
    $("#groupName").val("");
    $("#g_password").val("");

    //倒计时
    var wait = 60;

    //验证注册的时候号码是否存在
    var hasExist = false;

    // 手机号码验证
    jQuery.validator.addMethod("isMobile", function (value, element) {
        var length = value.length;
        var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, "请正确填写手机号码");

    if (status == 1) {
        yaya.layer.msg("注册成功，请登录！")
    }

    /**
     * 发送短信
     */
    $("#receiveMsgBtn").click(function () {

        // console.log("receiveMsgBtn click,,,")
        var phone = $("#phone").val();
        if (phone == ""){
            yaya.layer.msg("请填写手机号");
            return;
        }
        if(hasExist == false){
            return;
        }

        $.ajax({
            url: ctx + "/admin/send-verify-code",
            method: "post",
            dataType: "json",
            data: {
                phone: $("#phone").val()
            },

            success: function (data) {
                if (data.code) {
                    // $("#receiveMsgBtn").attr("disable",true);

                    // console.log("click send msg");
                    var msg = document.getElementById("receiveMsgBtn");
                    time(msg);

                }

            },
            error: function (data) {

            }
        });

    });

    $("#sendForgotBtn").click(function () {




        $.ajax({
            url: ctx + "/admin/send-verify-code",
            method: "post",
            dataType: "json",
            data: {
                phone: $("#forgotPhone").val()
            },

            success: function (data) {
                if (data.code) {
                    // $("#receiveMsgBtn").attr("disable",true);

                    var sendMsg = document.getElementById("sendForgotBtn");
                    time(sendMsg);

                }

            },
            error: function (data) {

            }
        });

    });


    /**
     * 倒计时
     * @param o
     */
    function time(o) {
        if (wait == 0) {
            o.removeAttribute("disabled");
            o.innerHTML = "获取验证码";
            o.style.color = "#00a0e9";
            wait = 60;
            UnMaskIt(o);
        } else {
            o.setAttribute("disabled", true);
            o.innerHTML = "重新发送(" + wait + ")";
            o.style.color="#002232";
            if(wait == 60){
                MaskIt(o);
            }
            wait--;
            setTimeout(function () {
                    time(o)
                },
                1000);
        }
    }

    function MaskIt(obj) {
        var hoverdiv = '<div class="divMask" style="position: absolute; width: 120px; height: 100%; right: 0px; top: 0px; background: #fff; opacity: 0; filter: alpha(opacity=0);z-index:5;"></div>';
        $(obj).wrap('<div class="position:relative;"></div>');
        $(obj).before(hoverdiv);
        $(obj).data("mask", true);
    }

    function UnMaskIt(obj) {
        if ($(obj).data("mask") == true) {
            $(obj).parent().find(".divMask").remove();
            $(obj).unwrap();
            $(obj).data("mask", false);
        }
        $(obj).data("mask", false);
    }


    // $("#submitBtn").click(function () {
    //
    //     var phone = $("#phone").val();
    //     var verifyCode = $("#verifyCode").val();
    //
    //
    // });


    $(".company").click(
        function () {
            $(".user").hide("direction")
            $(".div").show()
        }
    )

    //validate
    $("#login-form").validate({
        rules: {
            username: {
                required: true,
            },
            password: "required"
        },
        messages: {
            username: {
                required: "请输入用户名",
                remote: "用户名不存在"
            },
            password: "请输入密码"
        }

    });

    $("#register-form").validate({
        rules: {
            phone: {
                required: true,
                isMobile: true,
                remote: {
                    url: ctx + "/admin/check-username",
                    type: "post",
                    data: {
                        username: function () {
                            return $("#phone").val();
                        }
                    },
                    dataFilter: function (data, type) {
                        console.log("register,,,:"+data);

                        if(data == "false"){
                            hasExist = false;//号码已存在
                            console.log("register:"+hasExist);

                        }else {
                            hasExist = true;
                        }
                        return data;
                    }

                }
            },
            verifyCode: {
                required: true,
                remote: {
                    url: ctx + "/admin/check-verify-code",
                    type: "post",
                    data: {
                        phone: function () {
                            return $("#phone").val();
                        },
                        verifyCode: function () {
                            return $("#verifyCode").val();
                        }
                    }
                },
                minlength: 4,
                maxlength: 4

            },
            nickname: {
                required: true,
                minlength: 2,
                maxlength: 20
            },
            groupName: {
                required: true,
                minlength: 4,
                maxlength: 30,
                remote: {
                    url: ctx + "/admin/check-group",
                    type: "post",
                    data: {
                        groupName: function () {
                            return $("#groupName").val();
                        }
                    },
                    dataFilter: function (data, type) {
                        return data;
                    }

                }
            },
            password: {
                required: true,
                minlength: 6,
                maxlength: 30
            }

        },
        messages: {
            phone: {
                required: "请正确填写您的手机号码",
                remote: "手机号已经被注册！"
            },
            verifyCode: {
                required: "请正确填写验证码（4位数字）",
                remote: "验证码错误或过期！",
                minlength:"最少4为数字！",
                maxlength:"最多4为数字！"
            },
            nickname: "请正确填写名字（2~20位字符）",
            groupName: {
                required: "请正确填写组织名称（4~30位字符）",
                minlength:"请正确填写组织名称（4~30位字符）",
                maxlength:"请正确填写组织名称（4~30位字符）",
                remote: "组织已经被注册"
            },
            password: "请正确填写密码（6~30位字符）"

        }

    });
});
