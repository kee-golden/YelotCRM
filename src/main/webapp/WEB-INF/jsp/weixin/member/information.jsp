<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>个人资料</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<link rel="stylesheet" href="/css/weixin-index.css">
	
</head>
<body>
	<div class="information-wrap">
		<div class="information-banner">
			<img src="/images/information.jpg">
			<div class="information-user">
				<p class="information-name">小王</p>
				<p class="information-time">1990-01-01</p>
			</div>
		</div>
		<div class="information-content-wrap">
			<div class="information-phone-wrap">
				<div class="information-password-label">
					<div>手机号码：</div>
					<input type="text" name="" id="phone" value="${sessionScope.account.phone}" disabled="disabled" class="information-content-hidden" style="background: #fff;">
				</div>
				<%--<span class="information-password-again">重置</span>--%>
			</div>
			<div class="information-password-wrap">
				<div class="information-password-label">
					<div>账户姓名：</div>
					<input type="text" name="" id="fullName" value="${sessionScope.account.fullName}" style="background: #f3f1f2;" class="information-content-hidden" >
				</div>
				<span id="fullNameBtn" class="information-password-again information-password-new">重置</span>
			</div>

			<div class="information-email-wrap">
				<div class="information-password-label">
					<div>电邮：</div>
					<input type="email" name="" id="email" value="${sessionScope.account.email}" class="information-email-input">
				</div>
				<span id="emailBtn" class="information-password-again">保存</span>
			</div>
			<div class="information-interest-wrap">
				<div class="information-password-label">
					<div class="information-password-thing">我关注的奢侈品：<span class="information-interest-tip">*可多选</span></div>
					<div class="information-interest-select">
						<label>
							<input type="checkbox" name="" class="first-catagory" value="珠宝">珠宝
						</label>
						<label>
							<input type="checkbox" name="" class="first-catagory" value="腕表">腕表
						</label>
						<label>
							<input type="checkbox" name="" class="first-catagory" value="奢包">奢包
						</label>
						<label>
							<input type="checkbox" name="" class="first-catagory" value="鞋履">鞋履
						</label>
						<label>
							<input type="checkbox" name="" class="first-catagory" value="腰带">腰带
						</label>
						<label>
							<input type="checkbox" name="" class="first-catagory" value="其他">其他
						</label>
					</div>
				</div>
				<span id="hobbyBtn" class="information-password-again">保存</span>
			</div>
			<div class="information-city-wrap">
				<div class="information-password-label">
					<label>居住城市：</label>
					<select class="information-city-select" name="city" id="city">
						<option>请选择</option>
						<option value="北京市" <c:if test="${sessionScope.account.city = '北京市'}">selected="selected"</c:if>>北京市</option>
						<option value="上海市" <c:if test="${sessionScope.account.city = '上海市'}">selected="selected"</c:if>>上海市</option>
						<option value="杭州市" <c:if test="${sessionScope.account.city = '杭州市'}">selected="selected"</c:if>>杭州市</option>
						<option value="成都市" <c:if test="${sessionScope.account.city = '成都市'}">selected="selected"</c:if>>成都市</option>
						<option value="广州市" <c:if test="${sessionScope.account.city = '广州市'}">selected="selected"</c:if>>广州市</option>
						<option value="深圳市" <c:if test="${sessionScope.account.city = '深圳市'}">selected="selected"</c:if>>深圳市</option>
						<option value="南京市" <c:if test="${sessionScope.account.city = '南京市'}">selected="selected"</c:if>>南京市</option>
						<option value="苏州市" <c:if test="${sessionScope.account.city = '苏州市'}">selected="selected"</c:if>>苏州市</option>
						<option value="无锡市" <c:if test="${sessionScope.account.city = '无锡市'}">selected="selected"</c:if>>无锡市</option>
						<option value="其他" <c:if test="${sessionScope.account.city = '其他'}">selected="selected"</c:if>>其他</option>
					</select>
				</div>
				<span id="cityBtn" class="information-password-again" style="top:12px;">保存</span>
			</div>
		</div>
	</div>
	<div class="download-input-wrap">
		<form class="download-input">
			<div class="new-password">
				<label>旧的账户密码</label>
				<input type="password" name="">
			</div>
			<div class="new-password">
				<label>新的账户密码</label>
				<input type="password" name="">
			</div>
			<div class="new-password">
				<label>短信验证码</label>
				<input type="text" name="">
			</div>
			<div class="password-btn-wrap new-password">
				<label></label>
				<div class="password-btn">
					<input value="获取短信验证码" disabled="disabled" type="text">
					<button class="password-btn-submit" value="">提交</button>
				</div>
			</div>
			<div class="download-close"></div>
		</form>

	</div>
</body>
<script src="https://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
//	$('.information-password-new').click(function () {
////		$('.download-input-wrap').show();
//	});
//	$('.download-close').click(function () {
//		$('.download-input-wrap').hide();
//	});
	$(function () {
		$("#fullNameBtn").click(function(){
		    if($("#fullName").val() == ''){
		        alert('姓名不能为空');
		        return;
			}
			$.ajax({
				url:'/wx/update-fullname',
				method:'post',
				type:'json',
				data:{
				    phone:$("#phone").val(),
					fullName:$("#fullName").val(),
				},
				success:function (data) {
				    if(data.code == 1200){
				        alert("更新成功");
					}
					
                }
			});

		});

		$("#emailBtn").click(function () {
		    if($("#email").val() == ''){
				alsert("邮箱不能为空");
				return;
			}
			$.ajax({
				url:"/wx/update-email",
				method:'post',
				type:'json',
				data:{
				    phone:$("#phone").val(),
					email:$("#email").val(),
				},
				success:function (data) {
                    if(data.code == 1200){
                        alert("更新成功");
                    }
                }
			});
			
        });

		$("#hobbyBtn").click(function(){


		});

		$("#cityBtn").click(function(){
		    $.ajax({
				url:'/wx/update-city',
				method:'post',
				type:'json',
				data:{
				    phone:$('#phone').val(),
					city:$('#city').val(),
				},
				success:function (data) {
                    if(data.code == 1200){
                        alert("更新成功");
                    }
                }
			});
		});



		
    });
</script>
</html>