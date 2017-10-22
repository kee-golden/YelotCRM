<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<title>我的订单</title>
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

		.personal-center-menu>div.active {
			border-bottom: 2px solid #f3c647;
		}

		.personal-center-menu>div {
			float: left;
			width: 26%;
			margin-left: 5%;
			text-align: center;
			padding: 8px 0;
		}

		.personal-center-content {
			/*padding: 20px;*/
		}

		.hide {
			display: none;
		}

		.personal-center-no-item {
			background-color: #fff;
			/*padding: 20px;*/
			margin-top: 10px;
		}

		.personal-center-menu {
			background-color: #fff;
		}

		.personal-center-no-head {
			width: 100%;
			padding: 10px;
		}

		.personal-center-no-head span:last-child {
			float: right;
			color: #ff5000;
			margin-top: 5px;
		}

		.personal-center-no-content {
			display: block;
			background-color: #f8f8f8;
			padding: 5px 10px;
			color: #000;
		}

		.personal-center-no-content img {
			width: 75px;
			height: 75px;
			border: 3px solid #fff;
			border-radius: 4px;
			float: left;
		}

		.personal-center-no-content figcaption {
			float: left;
			margin-left: 20px;
		}

		.personal-center-no-content figure:after,
		.personal-center-no-footer:after {
			content: '';
			display: block;
			clear: both;
		}

		.personal-center-no-footer>a {
			display: block;
			float: right;
			width: 95px;
			height: 30px;
			border: 1px solid #ff5000;
			color: #ff5000;
			text-decoration: none;
			text-align: center;
			line-height: 30px;
			border-radius: 30px;
			margin: 10px;
		}
		.personal-center-no-content figcaption p{
			line-height: 23px;
		}
		.personal-center-no-logo {
			width: 20px;
			position: relative;
			top: 5px;
			margin-right: 6px;
		}
		.personal-center-time{
			color: #999;
			font-size: 12px;
		}
	</style>
</head>

<body>
<div class="order-wrap">
	<div class="personal-center-top">
		<img src="http://crm.rojewel.com/img/logo.png" class="personal-center-logo">
		<span>订单中心</span>
	</div>
	<div class="personal-center-menu">
		<div class="active" data-status="ongoing">正在进行</div>
		<div data-status="finished">已完成</div>
		<div data-status="all">全部订单</div>
	</div>
	<div class="personal-center-content">
		<div class="personal-center-no">
			<c:forEach items="${ongoingList}" var="item">
			<div class="personal-center-no-item">
				<div class="personal-center-no-head">
					<span><img src="/img/shop.png" alt="" class="personal-center-no-logo">${item.shopName}</span>
					<span>
						<c:if test="${item.status == 2 or item.status == 4 or item.status == 12}">门店审核中</c:if>
						<c:if test="${item.status >= 16 and item.status < 28}">维修中心正在维修</c:if>
						<c:if test="${item.status >= 28 and item.status < 40}">已维修完成，待发货</c:if>
						<c:if test="${item.status == 40}">已完成</c:if>
					</span>
				</div>
				<a class="personal-center-no-content" href="">
					<figure>
						<c:if test="${!empty item.imagesList}">
							<img src="${item.imagesList[0]}" alt="">
						</c:if>
						<c:if test="${empty item.imagesList}">
							<img src="/img/watch.jpg" alt="">
						</c:if>
						<figcaption>
							<p>${item.firstCategoryName}->${item.secondCategoryName}</p>
							<p class="personal-center-time">${item.serviceItemNames}</p>
							<p class="personal-center-time">
								<fmt:formatDate value="${item.createAt}" pattern="yyyy-MM-dd HH:mm"/>
							</p>
						</figcaption>
					</figure>
				</a>
				<c:if test="${item.status >= 36}"><!-- 已修好，并且已发货，才能查询物流订单详情 -->
				<div class="personal-center-no-footer">
					<a href="#">查看物流</a>
				</div>
				</c:if>

			</div>
			</c:forEach>

		</div>
		<div class="personal-center-yes hide">
			<div class="personal-center-no">
				<c:forEach items="${finishedList}" var="item">
					<div class="personal-center-no-item">
						<div class="personal-center-no-head">
							<span><img src="/img/shop.png" alt="" class="personal-center-no-logo">${item.shopName}</span>
							<span>
								<c:if test="${item.status == 2 or item.status == 4 or item.status == 12}">门店审核中</c:if>
								<c:if test="${item.status >= 16 and item.status < 40}">维修中心正在维修</c:if>
								<c:if test="${item.status >= 28 and item.status < 40}">已维修完成，待发货</c:if>
								<c:if test="${item.status == 40}">已完成</c:if>
								</span>
						</div>
						<a class="personal-center-no-content" href="">
							<figure>
								<c:if test="${!empty item.imagesList}">
									<img src="${item.imagesList[0]}" alt="">
								</c:if>
								<c:if test="${empty item.imagesList}">
									<img src="/img/watch.jpg" alt="">
								</c:if>
								<figcaption>
									<p>${item.firstCategoryName}->${item.secondCategoryName}</p>
									<p class="personal-center-time">${item.serviceItemNames}</p>
									<p class="personal-center-time">
										<fmt:formatDate value="${item.createAt}" pattern="yyyy-MM-dd HH:mm"/>
									</p>
								</figcaption>
							</figure>
						</a>
						<div class="personal-center-no-footer">
							<a href="#">查看物流</a>
						</div>
					</div>
				</c:forEach>
		</div>
		</div>
		<div class="personal-center-no hide">
			<c:forEach items="${allList}" var="item">

				<div class="personal-center-no-item">
					<div class="personal-center-no-head">
						<span><img src="/img/shop.png" alt="" class="personal-center-no-logo">${item.shopName}</span>
						<span>
							<c:if test="${item.status == 2 or item.status == 4 or item.status == 12}">门店审核中</c:if>
						<c:if test="${item.status >= 16 and item.status < 28}">维修中心正在维修</c:if>
						<c:if test="${item.status >= 28 and item.status < 40}">已维修完成，待发货</c:if>
						<c:if test="${item.status == 40}">已完成</c:if>
						</span>
					</div>
					<a class="personal-center-no-content" href="">
						<figure>
							<c:if test="${!empty item.imagesList}">
								<img src="${item.imagesList[0]}" alt="">
							</c:if>
							<c:if test="${empty item.imagesList}">
								<img src="/img/watch.jpg" alt="">
							</c:if>

							<figcaption>
								<p>${item.firstCategoryName}->${item.secondCategoryName}</p>
								<p class="personal-center-time"></p>
								<p class="personal-center-time">
									<fmt:formatDate value="${item.createAt}" pattern="yyyy-MM-dd HH:mm"/>
								</p>
								<!-- <p class="personal-center-time">1201710160</p> -->
							</figcaption>
						</figure>
					</a>
					<c:if test="${item.status >= 36}"><!-- 已修好，并且已发货，才能查询物流订单详情 -->
					<div class="personal-center-no-footer">
						<a href="#">查看物流</a>
					</div>
					</c:if>
				</div>
			</c:forEach>
		</div>

	</div>
</div>
</body>
<script src="https://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
<script>
    $('.personal-center-menu>div').each(function(index) {
        $(this).click(function() {
            $(this).addClass('active').siblings().removeClass('active');
            $('.personal-center-content>div').eq(index).show().siblings().hide();
            var status = $(this).data("status");
//            alert("status:"+status);
            if(status == "ongoing"){

			}
        });
    });
//    function convertStatus(orderId,status) {
//        var statusDesc;
//        if(status == 2){
//            statusDesc = '待客服主管审核';
//		}else if(status == 4){
//            statusDesc = '待分练人员审核';
//		}else if(status == 12){
//            statusDesc = '待预检人员审核';
//		}else if(status == 16){
//            statusDesc = '待QC审核';
//		}else if(status == 20){
//		    statusDesc = '待入库审核';
//		}else if(status == 24){
//            statusDesc = '待出库审核';
//        }else if(status == 28){
//            statusDesc = '待门店收货确认';
//        }else if(status == 32){
//            statusDesc = '待门店发货确认';
//        }else if(status == 36){
//            statusDesc = '待用户收货确认';
//        }else if(status == 40){
//            statusDesc = '已完成';
//        }
//        $('#'+orderId).html(statusDesc);
//
//    }
</script>

</html>