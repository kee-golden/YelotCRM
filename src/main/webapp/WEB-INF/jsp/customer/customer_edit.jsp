<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<div class="ibox float-e-margins">
    <div class="ibox-content">
        <div class="row">
            <form role="form" id="J_customerForm" class="form-horizontal">
                <input type="hidden" name="id" value="${bean.id}">

                <c:if test="${empty bean.id}">
                    <div class="col-md-12 b-r">
                        <h3 class="m-t-none m-b">基础信息</h3>
                        <div class="row bottom10">
                        <div class="col-md-6">
                            <label><span style="color: red">*</span>用户名</label>
                            <input type="text" placeholder="请输入用户名" class="form-control" name="name" id="J_name"
                                   value="${bean.name}" autocomplete="off">
                        </div>

                        <div class="col-md-6">
                            <label><span style="color: red">*</span>手机号</label>
                            <input type="text" placeholder="请输入手机号" class="form-control" name="phone"
                                   value="${bean.phone}">
                        </div>
                        </div>

                        <div class="row bottom10">
                        <div class="col-md-6">
                            <label>性别</label>
                            <select class="form-control">
                                <option value="0">女</option>
                                <option value="1">男</option>
                            </select>
                        </div>
                            <div class="col-md-6">
                                <label>邮箱</label>
                                <input type="text" placeholder="请输入邮箱" class="form-control" name="email"
                                       value="${bean.email}">

                            </div>
                        </div>

                        <div class="row bottom10">
                            <div class="col-md-6">
                                <label>省市</label>
                                <div id="prov_city">
                                    <select class="prov" name="province"></select>
                                    <select class="city" disabled="disabled" name="city"></select>
                                </div>

                            </div>
                            <div class="col-md-6">
                                <label>详细地址</label>
                                <input type="text" placeholder="请输入详细地址" class="form-control" name="address"
                                       value="${bean.address}">

                            </div>
                        </div>

                        <div class="row bottom10">
                            <div class="col-md-6">
                                <label>QQ</label>
                                <input type="text" placeholder="请输入QQ" class="form-control" name="qq"
                                       value="${bean.qq}">
                            </div>
                            <div class="col-md-6">
                                <label>微信号</label>
                                <input type="text" placeholder="请输入微信号" class="form-control" name="wechatId"
                                       value="${bean.wechatId}">
                            </div>
                        </div>
                        <div class="row bottom10">
                            <div class="col-md-6">
                                <label>微信号昵称</label>
                                <input type="text" placeholder="请输入微信号昵称" class="form-control" name="wechatNickname"
                                       value="${bean.wechatNickname}">
                            </div>
                            <div class="col-md-6">
                                <label>微信号绑定设备名称</label>
                                <input type="text" placeholder="请输入设备名称" class="form-control" name="deviceName"
                                       value="${bean.deviceName}">
                            </div>
                        </div>
                        <div class="row bottom10">
                            <div class="col-md-6">
                                <label>客户来源</label>
                                <select class="form-control">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <label>客户首次咨询时间</label>
                                <input type="text"  class="form-control" name="firstConsultTime" id="firstConsultTime"
                                       value="${bean.firstConsultAt}">
                            </div>
                        </div>
                        <div class="row bottom10">
                            <div class="col-md-6">
                                <label>客户类型</label>
                                <select class="form-control">
                                    <option value="0">新客户</option>
                                    <option value="1">老客户</option>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <label>备注</label>
                                <input type="text"  class="form-control" name="comment"
                                       value="${bean.comment}">
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${not empty bean.id}">
                    <div class="col-md-12 b-r">
                        <h3 class="m-t-none m-b">基础信息</h3>
                        <div class="row bottom10">
                            <div class="col-md-6">
                                <label><span style="color: red">*</span>用户名</label>
                                <input type="text" placeholder="请输入用户名" class="form-control" name="name" id="J_name"
                                       value="${bean.name}" autocomplete="off">
                            </div>

                            <div class="col-md-6">
                                <label><span style="color: red">*</span>手机号</label>
                                <input type="text" placeholder="请输入手机号" class="form-control" name="phone"
                                       value="${bean.phone}">
                            </div>
                        </div>

                        <div class="row bottom10">
                            <div class="col-md-6">
                                <label>性别</label>
                                <select class="form-control">
                                    <option value="0">女</option>
                                    <option value="1">男</option>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <label>邮箱</label>
                                <input type="text" placeholder="请输入邮箱" class="form-control" name="email"
                                       value="${bean.email}">

                            </div>
                        </div>

                        <div class="row bottom10">
                            <div class="col-md-6">
                                <label>省市</label>
                                <div id="prov_city">
                                    <select class="prov" name="province"></select>
                                    <select class="city" disabled="disabled" name="city"></select>
                                </div>

                            </div>
                            <div class="col-md-6">
                                <label>详细地址</label>
                                <input type="text" placeholder="请输入详细地址" class="form-control" name="address"
                                       value="${bean.address}">

                            </div>
                        </div>

                        <div class="row bottom10">
                            <div class="col-md-6">
                                <label>QQ</label>
                                <input type="text" placeholder="请输入QQ" class="form-control" name="qq"
                                       value="${bean.qq}">
                            </div>
                            <div class="col-md-6">
                                <label>微信号</label>
                                <input type="text" placeholder="请输入微信号" class="form-control" name="wechatId"
                                       value="${bean.wechatId}">
                            </div>
                        </div>
                        <div class="row bottom10">
                            <div class="col-md-6">
                                <label>微信号昵称</label>
                                <input type="text" placeholder="请输入微信号昵称" class="form-control" name="wechatNickname"
                                       value="${bean.wechatNickname}">
                            </div>
                            <div class="col-md-6">
                                <label>微信号绑定设备名称</label>
                                <input type="text" placeholder="请输入设备名称" class="form-control" name="deviceName"
                                       value="${bean.deviceName}">
                            </div>
                        </div>
                        <div class="row bottom10">
                            <div class="col-md-6">
                                <label>客户来源</label>
                                <select class="form-control">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <label>客户首次咨询时间</label>
                                <input type="text"  class="form-control" name="firstConsultTime" id="firstConsultTime"
                                       value="${bean.firstConsultAt}">
                            </div>
                        </div>
                        <div class="row bottom10">
                            <div class="col-md-6">
                                <label>客户类型</label>
                                <select class="form-control">
                                    <option value="0">新客户</option>
                                    <option value="1">老客户</option>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <label>备注</label>
                                <input type="text"  class="form-control" name="comment"
                                       value="${bean.comment}">
                            </div>
                        </div>
                    </div>


                </c:if>

            </form>

        </div>
    </div>
</div>
<script type="text/javascript" src="/static/cityselect/js/jquery.cityselect.js"></script>
<script type="text/javascript">
    require(['jquery', 'yaya', 'datatables.net','dateTimePicker'], function ($, yaya) {

        $('#firstConsultTime').datetimepicker({
            lang: 'ch',
            format: 'Y-m-d H:m',
            timepicker:true
        });

        //弹出层与时间控件在最上层
        $(".layui-layer-shade").attr("style", "z-index:888; background-color:#000; opacity:0.3;");
        $(".layui-layer").attr("style", "z-index: 999;width:550px;left:35%!important;top:12%");
//        $("#topside").attr("style", "z-index: 777;");
//        $("#leftside").attr("style", "z-index: 777");

//        $(function(){
            $("#prov_city").citySelect({
                url:"/static/cityselect/js/city.min.js",
                prov:"上海",
                city:"黄浦区",
                nodata:"none",
                required:false
            });
//        });
    });



</script>
