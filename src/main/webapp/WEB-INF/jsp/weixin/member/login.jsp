<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>登录</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<link rel="stylesheet" href="${ctx}/css/index-login.css">
</head>
<body>
	<div class="login-wrap">
		<div class="login-pic"></div>
		<input type="hidden" id="openid" value="${openid}">
		<input type="hidden" id="menu" value="${menu}">
		<input type="hidden" id="code" value="${code}">
		<input type="hidden" id="accessToken" value="${accessToken}">
		<p class="login-go">1秒登录会员系统</p>
		<p class="login-tip">微信和手机是两个独立账号，账号信息共通</p>
		<%--<div class="login-btn">--%>
			<%--<a href="javascript:void(0)" class="login-wechat"></a>--%>
			<%--<a href="javascript:void(0)" class="login-qq"></a>--%>
		<%--</div>--%>
		<div class="login-btn">
			<a href="javascript:void(0)" class="login-wechat">
				<img src="/images/wechat2.png" alt="">微信登录
			</a>
			<a href="javascript:void(0)" class="login-qq" >
				<img src="/images/mobile.png" alt="" style="width: 35px;">手机登录
			</a>
		</div>
	</div>
</body>
<script src="https://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
</html>
<script type="text/javascript">

    var hostUrl = "http://crm.rojewel.com";

    $(function () {
        $('.login-wechat').click(function () {
            var openid = $('#openid').val();
            var menu = $('#menu').val();
            var code = $('#code').val();
            var accessToken = $('#accessToken').val();
            var param = 'openid='+openid+'&menu='+menu+'&code='+code+'&accessToken='+accessToken;
            window.location.href = hostUrl+"${ctx}/wx/account-wechat?"+param;

        });

        $('.login-qq').click(function () {
            var openid = $('#openid').val();
            var menu = $('#menu').val();
            var code = $('#code').val();
            var accessToken = $('#accessToken').val();

            var param = 'openid='+openid+'&menu='+menu+'&code='+code+'&accessToken='+accessToken;
//            alert(menu+","+code);
            window.location.href = hostUrl+"${ctx}/wx/to-phone-register?"+param;

        });


	});
</script>