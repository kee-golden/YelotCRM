<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>会员专享</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<link rel="stylesheet" href="/css/coupon_wx.css">
	<script>
		var ctx = '${ctx}';
        <%--var wxJsapiConfig = eval('${wxJsapiConfig}');--%>
		<%--var wxConfigJson = JSON.parse('${wxConfigJson}')--%>
		var appid = '${wxConfig.appid}';
		var noncestr = '${wxConfig.noncestr}';
		var timestamp = '${wxConfig.timestamp}';
		var signature = '${wxConfig.signature}';
	</script>
</head>
<body>
	<%--<div class="card-head">--%>
		<%--<div class="my-card-head">--%>
			<%--<img src="/images/card_my.png" alt="">我--%>
		<%--</div>--%>
		<%--<button id="add">测试事件</button>--%>
		<%--卡包--%>
	<%--</div>--%>
	<div class="card-content-wrap">
		<c:forEach items="${cardList}" var="card">
			<%--cardId:${card.cash.baseInfo.id},${card.cash.baseInfo.title}--%>
			<div class="card-content-item">
				<div class="card-content-item-top">
					<img src="/images/coupon_logo.jpg" alt="" class="card-content-item-pic"/>
					<span>${card.cash.baseInfo.title}</span>
					<button style="float: right" class="addCardBtn btn btn-info"
							data-cardid="${card.cash.baseInfo.id}" data-signature="${card.signature}"
							data-timestamp="${card.timestamp}" data-noncestr="${card.noncestr}">领取</button>
				</div>
				<div class="card-content-item-bottom">
					<div>${card.cash.baseInfo.notice}</div>
					<%--<div>有效期至：<span>--%>
						<%--<fmt:formatDate value="${card.endDate}" pattern="yyyy-MM-dd"></fmt:formatDate>--%>
					<%--</span></div>--%>
				</div>
			</div>
		</c:forEach>

		<%--<div class="card-content-item">--%>
			<%--<div class="card-content-item-top">--%>
				<%--<img src="/images/card_pic.jpg" alt="" class="card-content-item-pic">--%>
				<%--<span>心花路放</span>--%>
			<%--</div>--%>
			<%--<div class="card-content-item-bottom">--%>
				<%--<div>微信电影票</div>--%>
				<%--<div>有效期至：<span>2014.1.1</span></div>--%>
			<%--</div>--%>
		<%--</div>--%>

	</div>
	
</body>
</html>
<script src="https://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<%--<script src="/static/require/require.js"></script>--%>
<%--<script src="/static/require/require.config.js"></script>--%>
<script>
	/*
	 * 注意：
	 * 1. 所有的JS接口只能在公众号绑定的域名下调用，公众号开发者需要先登录微信公众平台进入“公众号设置”的“功能设置”里填写“JS接口安全域名”。
	 * 2. 如果发现在 Android 不能分享自定义内容，请到官网下载最新的包覆盖安装，Android 自定义分享接口需升级至 6.0.2.58 版本及以上。
	 * 3. 常见问题及完整 JS-SDK 文档地址：http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html
	 *
	 * 开发中遇到问题详见文档“附录5-常见错误及解决办法”解决，如仍未能解决可通过以下渠道反馈：
	 * 邮箱地址：weixin-open@qq.com
	 * 邮件主题：【微信JS-SDK反馈】具体问题
	 * 邮件内容说明：用简明的语言描述问题所在，并交代清楚遇到该问题的场景，可附上截屏图片，微信团队会尽快处理你的反馈。
	 */
//    require(['jquery', 'weixin'], function ($, weixin) {

        wx.config({
            debug: false,
            appId: appid,
            timestamp: timestamp,
            nonceStr: noncestr,
            signature: signature,
            jsApiList: [
                'checkJsApi','onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ','onMenuShareWeibo','onMenuShareQZone',
                'hideMenuItems', 'showMenuItems','hideAllNonBaseMenuItem', 'showAllNonBaseMenuItem',
                'translateVoice', 'startRecord', 'stopRecord', 'onVoiceRecordEnd', 'playVoice',
                'onVoicePlayEnd', 'pauseVoice', 'stopVoice',
                'uploadVoice', 'downloadVoice', 'chooseImage', 'previewImage', 'uploadImage', 'downloadImage', 'getNetworkType', 'openLocation', 'getLocation', 'hideOptionMenu',
                'showOptionMenu', 'closeWindow', 'scanQRCode', 'chooseWXPay', 'openProductSpecificView',
                'addCard',
                'chooseCard',
                'openCard'
            ]
        });




        $(function () {


            $('.addCardBtn').click(function () {
                //添加到卡包
                var cardId2 = $(this).data('cardid');
                var timestamp2 = $(this).data('timestamp');
                var noncestr2 = $(this).data('noncestr');
                var signature2 = $(this).data('signature');
//                alert('cardId:' + cardId2+",  "+timestamp2+" , "+signature2+", "+noncestr2);
                addCard(cardId2,timestamp2,noncestr2,signature2);
            });

            function addCard(cardId,timestamp,noncestr,signature) {
                // var cardId = 'p4gAjwiTkBjLuMmMmtNfN-RKSAbk';
                var cardExt = '{"nonce_str":"' + noncestr +'","timestamp":"' + timestamp + '","signature":"' + signature + '"}';
                wx.addCard({
                    cardList: [{
                        cardId: cardId,
                        cardExt: cardExt
                    }], // 需要添加的卡券列表
                    success: function (res) {
                        var cardList = res.cardList; // 添加的卡券列表信息
//                        alert("添加成功:" + JSON.stringify(cardList));
                    },
					error:function (res) {
						alert(JSON.stringify(res));
                    }
                });

            }
        });
//    });
</script>
<%--<script src="${ctx}/js/weixin_config2.js"></script>--%>

