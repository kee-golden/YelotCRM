<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<link href="${ctx}/module-css/nav.css" rel="stylesheet">
<script src="${ctx}/static/jquery/jquery-2.2.4.min.js"></script>
<script src="${ctx}/static/require/require.js"></script>
<script src="${ctx}/static/require/require.config.js"></script>
<script>
    require(['jquery', 'yaya'], function ($, yaya) {
        $(function (e) {

            $.ajax({
                url:ctx+'/indexshow/tPublic',
                success:function (e) {
                    console.log(e.data);
                    console.log(e.data[0].pubvalue);
                    $("#version").val(e.data[1].pubvalue);
                    $("#copyright").val(e.data[0].pubvalue);
                }
            })

        })
    })


</script>
<%--
<boot>框架头</boot>--%>
<style>
    .container-fluid{ background: #FFFFFF}
    .modal-backdrop{ z-index: 1!important;}
    .modal-body input{ border: none; display: block}
    #version{ font-size: 18px; color:#393D49; font-weight: bold}
    #copyright{ width:100%; margin-top: 10px}
    #myModal span{font-size: 20px;}
</style>
<nav class="navbar navbar-default navbar-fixed-top" id="topside">
    <div class="container-fluid top_max ">
        <div class="row">
            <div class="col-xs-6"style="line-height: 60px; ">
                <div class="pull-left"><img src="${ctx}/img/logo.png"></div>
                <h3 style="float: left; margin-left: 50px; line-height: 45px;">${user.shop.name}</h3>
            </div>
            <div class="col-xs-6">
                <ul class="nav navbar-nav pull-right hidden-xs">

                    <li><a href="${ctx}/help"  target="view_window" class="nav_new_font"><i class="glyphicon glyphicon-home"></i>帮助中心</a></li>
                    <%--<li><a href="http://www.itss-china.com/read.php?id=246&classid=34" target='_blank'class="nav_new_font"><i class="glyphicon glyphicon-phone-alt"></i>联系我们</a></li>--%>
                    <li><a href="" class="nav_new_font" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-bookmark"></i>关于版本</a></li>
                    <li class="dropdown">
                        <a href="" data-toggle="dropdown" class="nav_new_font"><i class="glyphicon glyphicon-user man_icon"></i><span
                                id="username">${sessionScope.user.name}</span><span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#" id="resetPsd"><i class="glyphicon glyphicon-pencil ic_mag"></i>修改密码</a></li>
                            <li><a href="${ctx}/logout"><i class="glyphicon glyphicon-ban-circle ic_mag"></i>退出登陆</a></li>
                        </ul>
                    </li>
                </ul>
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title" id="myModalLabel">关于版本</h4>

                            </div>
                            <div class="modal-body">
                               <input id="version" type="text" readonly style="width: 238px">
                                <input id="copyright" type="text" readonly>
                            </div>
                           <%-- <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary">Save changes</button>
                            </div>--%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</nav>
<script>
    require(['jquery', 'yaya'], function ($, yaya) {

        //修改用户密码
        $("#resetPsd").click(function () {
            $.ajax({
                    url: ctx + '/admin/to-edit-psd',
                    method: 'get',
                    dataType: 'html',
                    success: function (str) {
                        yaya.layer.open({
                            type: 1,
                            title: '修改密码',
                            content: str, //注意，如果str是object，那么需要字符拼接。
                            area: '300px',
                            shadeClose: true,
                            btn: ['保存'],
                            success: function (layer, index) {

                                checkPasswordFrom();
                            },

                            yes: function (index) {
                                var isValid = $("#J_passwordForm").valid();
                                if(!isValid){
                                    return;
                                }
                                var loadIndex = yaya.layer.load(2);
                                $.ajax({
                                    url: ctx + '/admin/edit-psd',
                                    traditional: true,
                                    data: {
                                        oldPsd: $('#oldPsd').val(),
                                        newPsd: $('#newPsd').val(),

                                    },

                                    method: 'post',
                                    dataType: 'json',
                                    success: function (data) {
                                        yaya.layer.close(loadIndex);
                                        yaya.layer.close(index);
                                        if (data.code) {
                                            yaya.layer.msg("修改成功！")
                                        }
                                        else {

                                        }
                                    },
                                    error: function () {

                                    }
                                });


                            }
                        });
                    }
                }

            );

        });


        function checkPasswordFrom() {
            yaya.validate({
                el: '#J_passwordForm',
                rules: {
                    oldPsd:{
                        required:true,
                        remote:{
                            url:ctx+"/admin/check-password",
                            type:"post",
                            data:{
                                password:function () {
                                    return $("#oldPsd").val();
                                }
                            }
                        }

                    },
                    newPsd: {
                        required: true,
                        minlength:4,
                        maxlength:20
                    },
                    confirmPsd:{
                        required: true,
                        equalTo:'#newPsd',
                        minlength:4,
                        maxlength:20

                    }
                },
                messages: {
                    oldPsd: {
                        required:"原密码不能为空",
                        remote:"原密码填写不正确"
                    },
                    newPsd: {
                        required: "密码不能为空",
                        minlength:"密码最少4为字符"
                    },
                    confirmPsd:{
                        required:"确认密码不能为空",
                        equalTo:"两次输入的密码不一致",
                        minlength:"密码最少4为字符"
                    }
                }
            });

        }


    });
</script>