<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <style>
    * {
        margin: 0;
        padding: 0;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
    }

    body {

        margin: 0 auto;
        border: 0;
        font: 16px/1.5 PingFangSC-Regular, "Helvetica Neue", tahoma, arial;
        width: 100%;
        height: 100%;
        font-size: 14px;
        background-color: #f3f3f3;
        overflow: hidden;
        overflow-y: auto;
    }

    @font-face {
        font-family: 'FontAwesome';
        src: url('/css/fontawesome/fontawesome-webfont.eot@v=4.3.0');
        src: url('/css/fontawesome/fontawesome-webfont.eot@#iefix&v=4.3.0') format('embedded-opentype'), url('/css/fontawesome/fontawesome-webfont.woff2@v=4.3.0')
        format('woff2'), url('/css/fontawesome/fontawesome-webfont.woff@v=4.3.0') format('woff'), url('/css/fontawesome/fontawesome-webfont.ttf@v=4.3.0') format('truetype'), url('/css/fontawesome/fontawesome-webfont.svg@v=4.3.0#fontawesomeregular') format('svg');
        font-weight: normal;
        font-style: normal;
    }

    .fa-angle-up:before {
        content: "\f106";
    }

    .fa {
        display: inline-block;
        font: normal normal normal 14px/1 FontAwesome;
        font-size: inherit;
        text-rendering: auto;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        transform: translate(0, 0);
    }

    html {
        width: 100%;
        height: 100%;
    }

    .personal-center-top {
        height: 45px;
        line-height: 45px;
        text-align: center;
        background-color: #fff;
        border-bottom: 1px solid #e7e7e7;
    }

    .personal-center-logo {
        float: left;
        display: table-cell;
        vertical-align: middle;
        margin: 8px 80px 0 13px;
        width: 100px;
        position: absolute;
    }

    .personal-center-top:after,
    .personal-center-content-item:after,
    .personal-center-menu:after {
        content: '';
        display: block;
        clear: both;
    }

    .order-detail {
        background-color: #fff;
        padding: 10px;
    }

    .order-status {
        color: #ff5555;
    }

    .order-detail p {
        /*font-weight: bold;*/
        font-size: 16px;
        line-height: 25px;
    }

    .order-detail .order-number {
        font-size: 14px;
        color: #999;
    }

    .order-up {
        width: 20px;
        height: 20px;
        background: #d2cdcd;
        text-align: center;
        border-radius: 50%;
        line-height: 18px;
        color: #fff;
        float: left;
        display: table-cell;
        vertical-align: middle;
    }

    .order-process {
        margin: 35px 55px;
        border-left: 1px solid #ccc;
        position: relative;
        width: 100%;
        min-height: 40px;
    }

    .order-process>div {
        /*position: absolute;*/
        /*left: -10px;*/
        margin-left: -11px;
        display: table;
    }

    .order-send-item {
        /*border-bottom: 1px solid #e7e7e7;*/
       
    }

    .order-content {
        float: right;
        color: #808080;
        margin-left: 20px;
        float: left;
    }

    .order-content p:first-child {
        font-size: 16px;
    }

    .order-send-item:after {
        content: '';
        display: block;
        clear: both;
    }

    .order-send-item {
        margin-bottom: 20px;
         display: table-cell;
         vertical-align: top;
    }
    .order-item1:after{
        content: '';
        display: block;
        clear: both;
    }
    .order-item1{
        margin-bottom: 20px;
    }
    .order-active{
        background-color: #f9871b;
    }
    </style>
</head>

<body>
    <div class="personal-center-top">
        <img src="http://crm.rojewel.com/img/logo.png" class="personal-center-logo">
        <span>个人中心</span>
    </div>
    <div class="order-detail">
        <p class="order-status">待客服主管审核</p>
        <p class="order-number">运单号：<span>1212121</span></p>
    </div>
    <div class="order-process">
        <div class="order-send">
            <i class="fa fa-angle-up order-up order-active"></i>
            <div class="order-send-item">
                <div class="order-item1">
                    <div class="order-content">
                        <p>发货</p>
                        <p>审核通过</p>
                        <p>2017-10-09 09:10</p>
                    </div>
                </div>
                <div class="order-item1">
                   
                    <div class="order-content">
                        <p>发货</p>
                        <p>审核通过</p>
                        <p>2017-10-09 09:10</p>
                    </div>
                </div>
                <div class="order-item1">
                   
                    <div class="order-content">
                        <p>发货</p>
                        <p>审核通过</p>
                        <p>2017-10-09 09:10</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="order-send">
            <i class="fa fa-angle-up order-up"></i>
            <div class="order-send-item">
                <div class="order-item1">
                    <div class="order-content">
                        <p>发货</p>
                        <p>审核通过</p>
                        <p>2017-10-09 09:10</p>
                    </div>
                </div>
                <div class="order-item1">
                   
                    <div class="order-content">
                        <p>发货</p>
                        <p>审核通过</p>
                        <p>2017-10-09 09:10</p>
                    </div>
                </div>
                <div class="order-item1">
                   
                    <div class="order-content">
                        <p>发货</p>
                        <p>审核通过</p>
                        <p>2017-10-09 09:10</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="order-send">
            <i class="fa fa-angle-up order-up"></i>
            <div class="order-send-item">
                <div class="order-item1">
                    <div class="order-content">
                        <p>发货</p>
                        <p>审核通过</p>
                        <p>2017-10-09 09:10</p>
                    </div>
                </div>
                <div class="order-item1">
                   
                    <div class="order-content">
                        <p>发货</p>
                        <p>审核通过</p>
                        <p>2017-10-09 09:10</p>
                    </div>
                </div>
                <div class="order-item1">
                   
                    <div class="order-content">
                        <p>发货</p>
                        <p>审核通过</p>
                        <p>2017-10-09 09:10</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="order-send">
            <i class="fa fa-angle-up order-up"></i>
            <div class="order-send-item">
                <div class="order-content">
                    <p>发货</p>
                    <p>审核通过</p>
                    <p>2017-10-09 09:10</p>
                </div>
            </div>
        </div>
    </div>
</body>

</html>