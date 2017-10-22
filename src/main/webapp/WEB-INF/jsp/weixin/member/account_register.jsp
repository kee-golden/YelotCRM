<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>注册</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<link rel="stylesheet" href="${ctx}/css/weixin-index.css">
	
</head>
<body>
	<div class="rigister-wrap">
		<div class="rigister-content">
			<img src="${ctx}/images/wechat.png" class="wechate_pic">
			<input type="hidden" id="openid" value="${openid}">
			<input type="hidden" id="menu" value="${menu}">
			<input type="number" name="phone" id="phone" class="mobile" maxlength="15" minlength="11" placeholder="输入手机号">
			<div class="rigister-sms">
				<input type="number" name="verifyCode" id="verifyCode" placeholder="输入验证码" minlength="4" maxlength="4" class="rigister-code  mobile">
				<input type="button" name="" value="获取验证码" class="btn-code" id="verifyBtn">
			</div>
			<%--<div><img src="${ctx}/images/weixin.png" id="weixin_login" style="position:relative;top:300px;margin:0 auto;"></div>--%>
			<div class="rigister-submit">
				<button class="rigister-submit-btn" id="registerButton">NEXT</button>
			</div>
		</div>
	</div>
</body>
<script src="https://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">

	var hostUrl = "http://crm.rojewel.com";



    $(function () {

        var wait = 60;
	    //发送验证码
	    $('.btn-code').click(function () {
            var phone = $('#phone').val();
            var verifyCode = $('#verifyCode').val();
            if(!checkIsMobile(phone)){
                alert("输入正确的手机号");
                return;
            }
	        $.ajax({
				url:hostUrl + "/wx/send-verify-code",
				method:'post',
				type:'json',
				data:{
				    phone:$('#phone').val(),
				},
				success:function (data) {
					if(data.code == 1200){
                        var obj = document.getElementById("verifyBtn")
                        time(obj);
					}
                }
			})
			
        });

        /**
         * 倒计时
         * @param o
         */
        function time(o) {
            if (wait == 0) {
                o.removeAttribute("disabled");
                o.value = "获取验证码";
//                o.style.color = "#00a0e9";
                wait = 60;
                UnMaskIt(o);
            } else {
                o.setAttribute("disabled", true);
                o.value = "重新发送(" + wait + ")";
//                o.style.color="#002232";
                if(wait == 60){
                    MaskIt(o);
                }
                wait--;
                setTimeout(function () {
                        time(o);
                    },
                    1000);
            }
        }

        function MaskIt(obj) {
            var hoverdiv = '<div class="divMask" style="position: absolute; width: 120px; height: 100%; right: 0px; top: 0px; background: #fff; opacity: 0; filter: alpha(opacity=0);z-index:5;"></div>';
            $(obj).wrap('<div class="position:relative;"></div>');
            $(obj).before(hoverdiv);
            $(obj).data("mask", true);
        }

        function UnMaskIt(obj) {
            if ($(obj).data("mask") == true) {
                $(obj).parent().find(".divMask").remove();
                $(obj).unwrap();
                $(obj).data("mask", false);
            }
            $(obj).data("mask", false);
        }


        $('#registerButton').click(function () {
		    var phone = $('#phone').val();
		    var verifyCode = $('#verifyCode').val();
		    if(!checkIsMobile(phone)){
		        alert("输入正确的手机号");
		        return;
			}
			if(verifyCode == '' || verifyCode.length != 4){
		        alert('验证码不正确');
		        return;
			}
			$.ajax({
                url:hostUrl+"/wx/check-verify-code",
                method:'post',
                type:'json',
                data:{
                    phone:$('#phone').val(),
                    verifyCode:$('#verifyCode').val(),

                },
                success:function(data){
                    if(data.code == 1200){
                        registerFun();
                    }else{
						alert('验证码不正确');

                    }
                }
			});

		});
        
        function registerFun() {
            $.ajax({
                url:hostUrl+"/wx/to-register",
                method:'post',
                type:'json',
                data:{
                    openid:$('#openid').val(),
                    phone:$('#phone').val(),
                    verifyCode:$('#verifyCode').val(),
                    menu:$('#menu').val(),

                },
                success:function(data){
                    if(data.code == 1200){
                        window.location.href = hostUrl+'/wx/'+data.data.action+'?openid='+$('#openid').val();
                    }else {
                        alert('验证失败');
					}
                }
            });
			
        }


        function checkIsMobile(value){
            var length = value.length;
            var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
            return (length == 11 && mobile.test(value));
        }


    });

</script>
</html>