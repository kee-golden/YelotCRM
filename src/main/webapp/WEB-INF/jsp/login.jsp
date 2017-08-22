<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=1.0"/>
    <title></title>
    <!-- Mobile support -->
   <meta name="viewport" content="width=device-width, initial-scale=1">
 <%@include file="/WEB-INF/common/static.jsp" %>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/layui.css"/>
    <title>登录</title>
   <script>
        var ctx = '${ctx}';
        var status = '${status}';

    </script>
</head>

<style>
    * {
        font-family: "微软雅黑";
    }

    body, html {
        margin: 0;
        padding: 0;
    }

    .logo {
        float: right;
        margin-right: 20%;
        overflow: hidden;
    }

    #cont {
        max-width: 1200px;
        height: 500px;
        clear: both;
        margin: 10% auto;
        overflow: hidden;
    }

    .banner {
        float: left;
        width: 50%;
        margin-right: 0;
        margin-top: 5%;
        margin-left: 5%;
    }

    .user {
        float: right;
        width: 35%;
        margin-left: 0;
        margin-top: 5%;
        margin-right: 10%;
    }

    .titl {
        display: inline-block;
        text-align: right;
    }

    .form-horizontal {
        width: 100%;
        margin-top: 5%;
    }

    .login_in button {
        display: inline-block;
        cursor: pointer;
        color: white;
        background: url(${ctx}/img/002.png);
        padding: 4px 8px;
        font-size: 12px;
        margin-right: 30px;
        text-decoration: none;
        border: none;
    }

    #bom {
        width: 1200px;
        margin: 0 auto;
        color: #c1c0c0;
        text-align: center;
    }

    .layui-this {
        color: #01AAED !important;
    }

    .layui-this:after {
        border-bottom: 3px solid #01AAED !important;
    }

    .layui-tab-title {
        border-bottom: 1px solid #cbcbcb;
    }

    .col-sm-10 {
        padding-left: 10px;
        width: 75%;
        padding-right: 0;
        float: left;
    }

    .form-control {
        width: 100%;
        float: left;
    }

    .input_left {
        width: 65%;
        float: left;
    }

    .input_right {
        width: 30%;
        float: right;
    }

    .form-group label {
        padding: 0;
        float: left;
        margin-left: 4%;
    }

    .form-horizontal .form-group {
        margin-right: 0;
        width: 100%;
    }

    .navbar p {
        display: inline-block;
        width: 100%;
        text-align: center;
        color: #a7b1ba !important;
    }

    .navbar-inverse {
        background: #2f4050 !important;
    }
    .modal-dialog{ z-index: 2500 !important;}
    .modal-body h3{ font-size: 24px; text-align: center; margin-bottom: 20px; }
    .modal-body p{ font-size: 14px; margin-bottom: 20px; border-bottom: 1px solid #e5e5e5; padding-bottom: 10px}
    .modal-body h4{ font-size: 18px; margin-bottom:10px; margin-top: 20px}
    .modal-body ul li{ margin-bottom: 8px}
    .modal-content{ height: 600px !important; overflow:auto;overflow-y: hidden}
    .modal-body{overflow: auto;height: 530px}
    #forget ul{ border:1px solid #428bca}
    #forget ul li a{ padding:5px ; }
    .nav > li.active{ border: none !important;}
    #page1 label,#page2 label{ margin-top: 10px; margin-bottom: 5px}
    #page1 label span,#page2 label span{ margin-bottom: 10px; display: inline-block}
    .user_margin,#form2 { margin-top: 10px}
    .mobile_numb{ width: 60%; margin-right: 20px;}
    .next_ues{ width: 100%; text-align: center;}
    .user_margin label,#page2 label{ width:100%}
    #forget{ width: 300px}
    .btn-group{ margin-top: 15px}
    #form2 label{ width: 100%}
    #forget,#form2{ display: none}
    #page2{ margin-top: 10px}
    #receiveMsgBtn{position: absolute; right: 12px; top:8px; color: #00a0e9; cursor: pointer}
    .help_center{ margin-top: 8px;}
    .help_center  a{ font-size: 13px; color: #737373}
    .help_center a:hover{ color: #1cb4f9; text-decoration:underline}
    .layui-tab{ overflow: inherit!important;}
    .help>p:hover{color: white!important;}

</style>
<body style=" background: #e5e5e5;border-top:7px solid #3c72a8">

<div class="logo">
    <img src="${ctx}/img/logo-001.png"/>
</div>
<div id="cont">
    <div class=" banner"><img src="${ctx}/img/banner.png" style="max-width: 100%;"></div>
    <div class="user" >
        <!--layer插件-->
        <div class="layui-tab layui-tab-brief" >
            <ul class="layui-tab-title">
                <li class="layui-this">用户登陆</li>
                <%--<li class="group-register">组织注册</li>--%>
            </ul>
           <div class="layui-tab-content" style="height: 100px;">
                <div id="login-div" class="layui-tab-item layui-show">  <!--内容1-->
                    <c:if test="${status eq 500 or status eq 600}">
                        <div class="text-danger">${errorMsg}</div>
                    </c:if>
                    <form class="form-horizontal" id="login-form" role="form" action="${ctx}/login" method="post"> <!--登陆页面-->
                        <div class="form-group">
                            <label for="username" class="col-sm-2 control-label "><span class="titl">用户名:</span></label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="username" id="username"
                                       placeholder="请输入用户名或手机号" autocomplete="off"
                                <c:if test="${not empty username}"> value="${username}" </c:if>
                                >
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="form-group" style=" margin-bottom:5px;">
                            <label for="password" class="col-sm-2 control-label "><span class="titl">密&nbsp;&nbsp;&nbsp;&nbsp;码:</span></label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" name="password" id="password"
                                       placeholder="请输入密码" autocomplete="off">
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <div class="checkbox pull-left" style=" width: 100%; margin-left:25px;">
                                    <label style=" margin-left: 20px;">
                                        <input type="checkbox" value="rememberMe" name="remember" style=" margin-top: 4px">自动登录

                                    </label>
                                    <a class="pull-right" id="forget_a">忘记密码?</a>
                                    <div class="clearfix"></div>
                                </div>
                            </div>
                        </div>
                        <div class="pull-right login_in">
                            <button type="submit" class="">登陆</button>

                        </div>
                        <div class="clearfix"></div>

                    </form>

                </div>

            </div>
        </div>
        <!--layer插件结束-->
        <div id="forget">
            <ul class="nav nav-pills nav-justified" role="tablist">
                <li class="active"><a href="#page1" data-toggle="tab">企业账户</a></li>
                <li class=""><a href="#page2"  data-toggle="tab">企业用户</a></li>
            </ul>
            <div class="tab-content">
                <div  class="tab-pane fade in active" id="page1">
                    <div class="container-fluid">
                        <div class="row">
                            <form  class="user_margin" id="J_forgotForm">
                                <label >
                                    <span>企业账号</span>
                                    <input type="text" placeholder="请输入手机号" class=" form-control" id="forgotPhone" name="forgotPhone">
                                </label>

                                <label style="position: relative;">
                                    <span style=" display: block">验证码</span>
                                    <input type="text" placeholder="请输入验证码" class=" form-control " id="forgotVerifyCode" name="forgotVerifyCode"/>
                                    <span id="sendForgotBtn" style="position: absolute;right: 12px; top:36px; font-weight: normal; color: #00a0e9;cursor: pointer">获取验证码</span>
                                    <div class=" clearfix"></div>
                                </label>
                                <div>
                                    <div class="btn-group btn-group-justified">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-primary " id="next_ues">下一步</button>
                                        </div>
                                    </div>
                                    <div class="help_center">
                                        <a href="" class="pull-right">返回登陆页</a>
                                        <div class="clearfix"></div>
                                    </div>
                                </div>
                            </form>
                            <form id="form2">
                                <label >
                                    <span>请输入新密码</span>
                                    <input type="password" placeholder="新密码" class=" form-control" id="resetPsd" name="resetPsd">
                                </label>
                                <label >
                                    <span>请再次输入密码</span>
                                    <input type="password" placeholder="确认密码" class=" form-control" id="confirmPsd" name="confirmPsd">
                                </label>
                                <div class="btn-group btn-group-justified">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-primary " id="next_ok">确认</button>
                                    </div>
                                </div>
                                <div class="help_center">
                                    <a href="" class="pull-left"></a>
                                    <a href="" class="pull-right">返回登陆页</a>
                                    <div class="clearfix"></div>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
                <div class="tab-pane fade" id="page2">
                    <label>
                        <span>企业内部员工，找回密码请与组织的管理员联系！</span>
                        <%--<input type="text" class=" form-control">--%>
                    </label>


                </div>

            </div>
        </div>
    </div>

</div>
<nav class="navbar navbar-inverse navbar-fixed-bottom">

    <div class=" col-xs-3">
        <p class="navbar-text ">版权所有 ©上海御金匠实业有限公司</p>
    </div>
    <div class=" col-xs-2">
        <p class="navbar-text "></p>
    </div>
    <div class=" col-xs-3">
        <p class="navbar-text">ICP备案号：沪ICP备15047698号</p>
    </div>
    <div class="col-xs-2">
        <%--<a  href="${ctx}/help" class="help"><p class="navbar-text">帮助中心</p></a>--%>
    </div>
    <div class="col-xs-2">
        <p class="navbar-text"> 售后电话：021-62895588</p>
       </div>

</nav>

</body>

<script src="${ctx}/static/require/require.js"></script>
<script src="${ctx}/static/require/require.config.js"></script>
<script src="${ctx}/module-js/login/login.js"></script>


</html>
<script>
    require(['jquery', 'yaya', 'validate'], function ($, yaya,validate) {
        $("#forget").hide();
        $("#from2").hide();
        $("#forget_a").click(
            function () {
                $(".layui-tab").hide();
                $("#forget").show();
                $(".user_margin").show();

            }
        );
        
//        $.ajax({
//            url:ctx+"/admin/check-group-count",
//            type:"post",
//            success:function (data) {
//                if(data.code){
//
//                }else{
//                    $('.group-register').hide();
//                }
//            }
//        });

        $("#J_forgotForm").validate({
            rules:{
                forgotPhone:{
                    required:true,
                    isMobile:true,
                    remote:{
                        url:ctx+"/admin/check-super-admin",
                        type:"post",
                        data:{
                            forgotPhone:function () {
                                return $("#forgotPhone").val();
                            }
                        }

                    }
                },
                forgotVerifyCode:{
                    required:true,
                    remote:{
                        url:ctx+"/admin/check-verify-code",
                        type:"post",
                        data:{
                            phone:function() {
                                return $("#forgotPhone").val();
                            },
                            verifyCode:function(){
                                return $("#forgotVerifyCode").val();
                            }
                        }
                    },
                    maxlength:4

                },

            } ,
            messages:{
                forgotPhone:{
                    required:"请正确填写手机号码",
                    remote:"该手机号不是企业管理员账号！"
                },
                forgotVerifyCode:{
                    required:"请正确填写验证码（4位数字）",
                    remote:"请重新获取验证码，验证码错误或过期！"
                }

            }

        });

        $("#form2").validate({
            rules:{
                resetPsd:{
                    required:true,
                    minlength:6,
                    maxlength:30


                },
                confirmPsd:{
                    required:true,
                    equalTo:'#resetPsd',
                    minlength:6,
                    maxlength:30
                }

            } ,
            messages:{
                resetPsd:{
                    required:"密码为必填",
                    minlength:"最少长度为6！"
                },
                confirmPsd:{
                    required:"确认密码为必填",
                    minlength:"最少长度为6！",
                    equalTo:"两次输入的密码不一致！"

                }

            }

        });


        $("#next_ues").click(
            function () {
                var isValid = $("#J_forgotForm").valid();
                if(!isValid){
                    return;
                }
                //验证验证码




                $(".user_margin").hide();
                $("#form2").show();

            }
        );
        $("#next_ok").click(function () {

            var isValid = $("#form2").valid();
            if(!isValid){
                return;
            }

            $.ajax({
                    url: ctx + "/admin/reset-super-psd",
                    method: "post",
                    dataType: "json",
                    data: {
                        phone: $("#forgotPhone").val(),
                        password: $("#resetPsd").val()
                    },

                    success: function (data) {
                        if (data.code) {
                            yaya.layer.msg("重置密码成功！");
                        window.location.href = ctx+"/login";
                        }

                    },
                    error: function (data) {

                    }
                }
            )
        });
    })
</script>