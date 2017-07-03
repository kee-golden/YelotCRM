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
        margin: 0 auto;
        margin-top: 10%;
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
    .modal-content{ height: 800px !important; overflow:auto}
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
</style>
<body style=" background: #e5e5e5;border-top:7px solid #3c72a8">
<div class="logo">
    <img src="${ctx}/img/logo-001.png"/>
</div>
<div id="cont">
    <div class=" banner"><img src="${ctx}/img/banner.png" style="max-width: 100%;"></div>
    <div class="user" >
        <!--layer插件-->

        <!--layer插件结束-->
        <div id="forget">

            <div class="tab-content">
                <div  class="tab-pane fade in active" id="page1">
                    <div class="container-fluid">
                        <div class="row">

                            <form id="form2">
                                <label >
                                    <span>邮箱验证通过，请输入新密码</span>
                                    <input type="text" class=" form-control">
                                </label>
                                <label >
                                    <span>请再次输入密码</span>
                                    <input type="text" class=" form-control">
                                </label>
                                <div class="btn-group btn-group-justified">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-primary " id="next_ok">确认</button>
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>


            </div>
        </div>
    </div>

</div>

<nav class="navbar navbar-inverse navbar-fixed-bottom">
    <div class=" col-xs-4">
        <p class="navbar-text ">无锡云惠软件有限公司</p>
    </div>
    <div class=" col-xs-4">
        <p class="navbar-text ">江苏新世纪信息科技有限公司旗下品牌</p>
    </div>
    <div class=" col-xs-4">
        <p class="navbar-text">ICP备案号：苏ICP备15003321号-1</p>
    </div>
</nav>
<%--<div id="bom">
    <span>CopyRight © 中国ITSS云教育平台 2016</span>
    <span style=" margin: 0 10%;">江苏新世纪信息科技有限公司旗下品牌</span>
    <span >ICP备案号：苏ICP备15003321号-1</span>
</div>--%>
</body>

<script src="${ctx}/static/require/require.js"></script>
<script src="${ctx}/static/require/require.config.js"></script>
<script src="${ctx}/module-js/login/login.js"></script>


</html>
