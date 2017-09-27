<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>我的积分</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<link rel="stylesheet" href="${ctx}/css/weixin-index.css?random=Math.random()">
	
</head>
<body>
	<div class="score-wrap">
		<div class="score-top">
			<div class="score-top-pic">
				<p>可用积分：<span>0</span></p>
				<div class="score-top-head">
					<img src='/images/leather.jpg'>
				</div>
			</div>
			<p class="score-history histroy-show">显示历史记录+</p>
		</div>
		<div class="score-bottom">
			<div class="score-intro">
				<div class="score-intro-center">
					<div class="score-intro-white"><span>白色</span></div>
					<div class="score-intro-orange"><span>橙色</span><span class="socre-zhi">2501分</span></div>
					<div class="score-intro-black"><span>黑色</span><span class="socre-zhi">10001分</span></div>
				</div>

			</div>
			<div class="score-record hide">
				<div class="score-record-item">
					<span>可用积分：</span>
					<span>0</span>
				</div>
				<div class="score-record-item">
					<span>累计积分：</span>
					<span>0</span>
				</div>
				<div class="score-record-item">
					<span>已用积分：</span>
					<span>0</span>
				</div>
				<div class="score-record-item">
					<span>本周即将过期的积分：<e class="score-record-add">+</e></span>
					<span>0</span>
				</div>
				<div class="score-record-item">
					<span>会员级别：</span>
					<span>白色<i class="score-record-item-color"></i></span>
				</div>
				<div class="score-record-item">
					<span>账户周年日：</span>
					<span>1/1</span>
				</div>
				<p class="score-record-tip">截止1/1之前，仅有2500分您就能升级为橙色会员！</p>
				<div class="score-record-table">
					<div class="score-record-thead score-record-content">
						<div class="score-record-first">日期</div>
						<div>获得积分</div>
						<div>使用积分</div>
						<div>详情</div>
					</div>
					<div class=" score-record-content">
						<div class="score-record-first">132</div>
						<div>321</div>
						<div>2131</div>
						<div>123</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="https://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
	$('.score-history').click(function () {
		if($(this).hasClass('histroy-show')){
			$(this).html('隐藏历史记录-');
			$(this).removeClass('histroy-show')
			$('.score-record').show();
			$('.score-intro').hide();
		}
		else{
			$(this).html('显示历史记录+');
			$(this).addClass('histroy-show')
			$('.score-record').hide();
			$('.score-intro').show();
		}
	})
</script>
</html>