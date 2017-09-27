<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>会员专享</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<link rel="stylesheet" href="${ctx}/css/weixin-index.css">
	
</head>
<body>
	<div class="member-wrap">	
		<div class="member-banner-wrap" id='member-banner-wrap'>
			<div class="slideBox">
				<ul class="member-banner-ul">
					<li><img src="/images/jewelry.jpg" alt="" class="my-card-banner-pic"></li>
					<li><img src="/images/made.jpg" alt="" class="my-card-banner-pic"></li>
					<li><img src="/images/jewelry.jpg" alt="" class="my-card-banner-pic"></li>
					<li><img src="/images/other.jpg" alt="" class="my-card-banner-pic"></li>
				</ul>
			</div>
			<div class="focusBtn"></div>
		</div>
		<div class="member-select-wrap">
			<div class="member-user menber-select-card">
				<img src="/images/use.png">
				使用规则
			</div>
			<div class="member-download menber-select-card">
				<img src="/images/download.png">
				下载优惠券
			</div>
		</div>
		
	</div>
	<div class="use-arcticle">
		<p class="use-arcticle-title">这是标题</p>
		<div class="use-arcticle-content">这是内容这是内容这是内容这是内容这是内容</div>
		<c:forEach items="${cardList}" var="card">
			cardId:${card.cash.baseInfo.id},${card.cash.baseInfo.title}
		</c:forEach>
	</div>
	<div class="download-input-wrap">
		<div class="download-input">
			<p>*请确认已仔细阅读使用规则。</p>
			<p>请输入您的账户密码下载优惠券</p>
			<form class="download-input-form">
				<span>账户密码</span>
				<input type="password" name="" class="download-input-password">
				<button class="download-input-submit">提交</button>
			</form>
			<div class="download-close"></div>
		</div>
	</div>
</body>
<script src="https://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
<script src='/js/TouchSlide.1.1.source.js'></script>
<script type="text/javascript">
	$('.member-user').click(function () {
		if(!$(this).hasClass('open')){
			$('.use-arcticle').css('top','50vh');
			$('.member-wrap').css('bottom','50vh');
			$(this).addClass('open');
		}
		else{
			$('.use-arcticle').css('top','100vh');
			$('.member-wrap').css('bottom','0');
			$(this).removeClass('open');
		}
	})
	$('.member-download').click(function(){
		$('.download-input-wrap').show();
	})
	$('.download-close').click(function(){
		$('.download-input-wrap').hide();
	})

	TouchSlide({ slideCell:"#member-banner-wrap",titCell:'.focusBtn',mainCell:'.member-banner-ul',autoPage:'<span></span>',effect:'leftLoop',delayTime:600,interTime:5000,autoPlay:true});
</script>
</html>