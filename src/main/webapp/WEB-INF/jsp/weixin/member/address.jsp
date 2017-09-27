<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="${ctx}/css/weixin-index.css">
</head>

<body>
    <div class="order-wrap">
        <div class="personal-center-top">
            <img src="http://crm.rojewel.com/img/logo.png" class="personal-center-logo">
            <span>个人中心</span>
        </div>
        <form class="address-wrap">
            <div class="address-name-wrap">
                <p class="address-name-title">联系人</p>
                <div class="contact-personal-wrap">
                    <label class="contact-personal-last">姓：</label>
                    <div class="contact-personal-name">
                        <input type="text" value="123" class="contact-personal-name-input"> 
                        <span>名：</span>
                        <input type="text" value="lxj" class="contact-personal-name-input">
                        <div class="contact-personal-sex">
                            <input type="radio" name="sex">先生
                            <input type="radio" name="sex">女士
                        </div>
                    </div>
                </div>
            </div>
            <div class="contact-personal-wrap">
            	<label>电话：</label><input type="" name="" value="123456784" class="contact-personal-input-big">
            </div>
            <p class="address-name-title">收货地址</p>
            <div class="contact-personal-wrap">
            	<label>地址：</label><input type="" name="" class="contact-personal-input-big">
            </div>
            <div class="contact-personal-wrap">
            	<label>门牌号</label><input type="" name="" placeholder="例:5号楼101室" class="contact-personal-input-big">
            </div>
        </form>

    </div>
</body>
<script src="https://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>

</html>