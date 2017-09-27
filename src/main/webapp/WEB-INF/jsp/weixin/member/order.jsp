<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>我的订单</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<link rel="stylesheet" href="css/index.css">
</head>
<body>
	<div class="order-wrap">
		<div class="personal-center-top">
			<img src="http://crm.rojewel.com/img/logo.png" class="personal-center-logo">
			<span>个人中心</span>
		</div>
		<div class="personal-center-menu">
			<div class="active">未完成</div>
			<div>已完成</div>
			<div>全部订单</div>
		</div>
		<div class="personal-center-contont">
			<div class="personal-center-no">未完成的</div>
			<div class="personal-center-yes hide">已完成</div>
			<div class="personal-center-no hide">全部订单</div>
		</div>
	</div>
</body>
<script src="https://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
<script>
	$('.personal-center-menu>div').each(function(index){
		$(this).click(function(){
			$(this).addClass('active').siblings().removeClass('active');
			$('.personal-center-contont>div').eq(index).show().siblings().hide();
		})
	})
</script>
</html>