<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>个人中心</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<link rel="stylesheet" href="/css/weixin-index.css">
</head>
<body>
	<div class="personal_center-wrap">
		<div class="personal-center-top">
			<img src="http://crm.rojewel.com/img/logo.png" class="personal-center-logo">
			<span>个人中心</span>
		</div>
		<div class="personal-center-banner">
			<img src="/images/information.jpg" class="my-card-banner-pic">
			<div class="personal-center-msg">
				<p>嘿<span>小明</span></p>
				<p>让我们一起健康生活</p>
			</div>
		</div>
		<div class="personal-center-content">
			<a class="personal-center-content-item" href="/wx/my-order">
				
				<div class="">
					<img src="/images/order.png" class="personal-center-item-logo">
				</div>
				<div>我的订单</div>
				<div><img src="/images/right.png" class="personal-center-right"></div>

			</a>
			<%--<a class="personal-center-content-item" href="/wx/my-information">--%>
				<%--<div class="">--%>
					<%--<img src="/images/personal.png" class="personal-center-item-logo">--%>
				<%--</div>--%>
				<%--<div>个人信息</div>--%>
				<%--<div><img src="/images/right.png" class="personal-center-right"></div>--%>
				<%----%>
			<%--</a>--%>
			<a class="personal-center-content-item" href="/wx/my-address">
				<div class="">
					<img src="/images/address.png" class="personal-center-item-logo">
				</div>
				<div>快递地址</div>
				<div><img src="/images/right.png" class="personal-center-right"></div>
				
			</a>
		</div>
	</div>
</body>
<script src="https://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
</html>