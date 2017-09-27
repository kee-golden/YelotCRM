<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>我的虚拟卡</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<link rel="stylesheet" href="/css/weixin-index.css">
	
</head>
<body>
	<div class="my-card-wrap">
		<div class="my-card-banner">
			<img src="/images/card_banner.jpg" class="my-card-banner-pic">
		</div>
		<div class="my-card-content">
			<p class="my-card-intro">请在结账前扫描您的以下专属会员卡二维码。每次在**购买的东西均可积分，每次消费一元积一分。积分可兑换会员专属优惠券。积分有效期为1年，请及时兑换使用。</p>
			<p class="my-card-number">${sessionScope.account.accountNo}</p>
			<div class="my-card-code" id="qrcode">
				<%--<img src="" alt="二维码">--%>
					<%--<div id="qrcode"></div>--%>
			</div>
		</div>
	</div>
</body>
<script src="https://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
<script src="/js/qrcode.min.js">

</script>
<script>
    var accountNo = "${sessionScope.account.accountNo}";

    $(function () {
        var qrcode = new QRCode('qrcode', {
            text: accountNo,
            width: 128,
            height: 128,
            colorDark : '#000000',
            colorLight : '#ffffff',
            correctLevel : QRCode.CorrectLevel.H
        });
    });

</script>
</html>