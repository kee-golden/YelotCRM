<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>个人资料</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<link rel="stylesheet" href="/css/weixin-index.css?random=Math.random()">

</head>
<body>
	<div class="information-wrap">
		<div class="information-banner">
			<img src="/images/information.jpg">
			<div class="information-user">
				<p class="information-name">
					${account.fullName}
				</p>
				<p class="information-phone">
					${account.phone}
				</p>
			</div>
		</div>
		<div class="information-content-wrap">
			<%--<div class="information-phone-wrap">--%>
				<%--<div class="information-password-label">--%>
					<%--<div>手机号码：</div>--%>
					<%--<input type="text" name="" id="phone" value="${sessionScope.account.phone}" disabled="disabled" class="information-content-hidden" style="background: #fff;">--%>
				<%--</div>--%>
			<%--</div>--%>
				<input type="hidden" id="openid" value="${account.wxOpenid}">
				<input type="hidden" id="phone" value="${account.phone}">
			<div class="information-password-wrap">
				<div class="information-password-label">
					<div>账户姓名：</div>
					<input type="text" name="" id="fullName" value="${account.fullName}" style="background: #f3f1f2;" class="information-content-hidden" >
				</div>
				<span id="fullNameBtn" class="information-password-again information-password-new">重置</span>
			</div>

			<div class="information-email-wrap">
				<div class="information-password-label">
					<div>手机：</div>
					<input type="text" name="" id="myPhone" value="${account.phone}" class="information-email-input">
				</div>
				<span id="phoneBtn" class="information-password-again">保存</span>
			</div>
			<div class="information-interest-wrap">
				<div class="information-password-label">
					<div class="information-password-thing">我关注的奢侈品：<span class="information-interest-tip">*可多选</span></div>
					<div class="information-interest-select">
						<label>
							<input type="checkbox" name="interest" class="first-catagory" value="珠宝">珠宝
						</label>
						<label>
							<input type="checkbox" name="interest" class="first-catagory" value="腕表">腕表
						</label>
						<label>
							<input type="checkbox" name="interest" class="first-catagory" value="奢包">奢包
						</label>
						<label>
							<input type="checkbox" name="interest" class="first-catagory" value="鞋履">鞋履
						</label>
						<label>
							<input type="checkbox" name="interest" class="first-catagory" value="腰带">腰带
						</label>
						<label>
							<input type="checkbox" name="interest" class="first-catagory" value="其他">其他
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
						<option value="北京市" <c:if test="${account.city eq '北京市'}">selected="selected"</c:if>>北京市</option>
						<option value="上海市" <c:if test="${account.city eq '上海市'}">selected="selected"</c:if>>上海市</option>
						<option value="杭州市" <c:if test="${account.city eq '杭州市'}">selected="selected"</c:if>>杭州市</option>
						<option value="成都市" <c:if test="${account.city eq '成都市'}">selected="selected"</c:if>>成都市</option>
						<option value="广州市" <c:if test="${account.city eq '广州市'}">selected="selected"</c:if>>广州市</option>
						<option value="深圳市" <c:if test="${account.city eq '深圳市'}">selected="selected"</c:if>>深圳市</option>
						<option value="南京市" <c:if test="${account.city eq '南京市'}">selected="selected"</c:if>>南京市</option>
						<option value="苏州市" <c:if test="${account.city eq '苏州市'}">selected="selected"</c:if>>苏州市</option>
						<option value="无锡市" <c:if test="${account.city eq '无锡市'}">selected="selected"</c:if>>无锡市</option>
						<option value="其他" <c:if test="${account.city eq '其他'}">selected="selected"</c:if>>其他</option>
					</select>
				</div>
				<span id="cityBtn" class="information-password-again" style="top:12px;">保存</span>
			</div>
		</div>
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


	function setInterestStatus() {
        var interestStr = '${account.interestJson}';
//        var interestStr = '["珠宝","奢包","腰带"]';
        var interestValues;

        if(interestStr == ''){
	        console.log('values is empty,,,');
	        return;
		}else {
            interestValues = JSON.parse(interestStr);
        }
        var checkBoxAll = $("input[name='interest']");
        for(var i=0;i<interestValues.length;i++){
            //获取所有复选框对象的value属性，然后，用checkArray[i]和他们匹配，如果有，则说明他应被选中

			$.each(checkBoxAll,function(j,checkbox){
				//获取复选框的value属性
				var checkValue=$(checkbox).val();
				if(interestValues[i]==checkValue){
					$(checkbox).attr("checked",true);
				}
			});
		}
    }

function interestCheck() {  //jquery获取复选框值
    var chk_value = [];
    $('input[name="interest"]:checked').each(function () {
        chk_value.push($(this).val());
    });
    return chk_value;
}


	$(function () {

        setInterestStatus();//回显用户兴趣


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
				    openid:$("#openid").val(),
					fullName:$("#fullName").val(),
				},
				success:function (data) {
				    if(data.code == 1200){
				        alert("更新成功");
					}
					
                }
			});

		});

		$("#phoneBtn").click(function () {
		    if($("#myPhone").val() == ''){
				alert("手机不能为空");
				return;
			}
			$.ajax({
				url:"/wx/update-my-phone",
				method:'post',
				type:'json',
				data:{
				    phone:$("#phone").val(),
				    openid:$("#openid").val(),
					myPhone:$("#myPhone").val(),
				},
				success:function (data) {
                    if(data.code == 1200){
                        alert("更新成功");
                    }
                }
			});
			
        });

		$("#hobbyBtn").click(function(){
			var interestObject = interestCheck();
			var interestValue = JSON.stringify(interestObject);
            console.log(interestValue);
            if(interestObject.length < 1){
                alert("兴趣不能为空");
                return;
			}
            $.ajax({
                url:"/wx/update-interest",
                method:'post',
                type:'json',
                data:{
                    phone:$("#phone").val(),
//                    phone:'15358000878',
                    openid:$("#openid").val(),
                    interest:interestValue,
                },
                success:function (data) {
                    if(data.code == 1200){
                        alert("更新成功");
                    }
                }
            });


		});




            $("#cityBtn").click(function(){
		    $.ajax({
				url:'/wx/update-city',
				method:'post',
				type:'json',
				data:{
				    phone:$('#phone').val(),
				    openid:$('#openid').val(),
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