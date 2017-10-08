<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<div class="ibox float-e-margins">
    <div class="ibox-content">
        <div class="row">
            <form role="form" id="J_expressForm" class="form-horizontal">
                <input type="hidden" name="id" value="${bean.id}">

                <c:if test="${empty bean.id}">
                    <div class="col-md-12 b-r">
                        <h3 class="m-t-none m-b">基础信息</h3>
                        <div class="row bottom10">
                        <div class="col-md-6">
                            <label><span style="color: red">*</span>快递名称</label>
                            <input type="text" placeholder="请输入快递名称" class="form-control" name="expressName" id="expressName"
                                   value="" autocomplete="off">
                        </div>

                        <div class="col-md-6">
                            <label><span style="color: red">*</span>快递单号</label>
                            <input type="text" placeholder="请输入快递单号" class="form-control" name="expressNo" id="expressNo"
                                   value="">
                        </div>
                        </div>

                        <div class="row bottom10">

                            <div class="col-md-6">
                                <label>付款方式</label>
                                <select class="form-control" name="payType">
                                    <option value="1">寄付</option>
                                    <option value="2">到付</option>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <label>付款金额</label>
                                <input type="number" placeholder="请输入金额" class="form-control" name="payAmount"
                                       value="">
                            </div>
                        </div>

                        <div class="row bottom10">
                            <div class="col-md-6">
                                <label>寄件人姓名</label>
                                <input type="text" placeholder="请输入姓名" class="form-control" name="sendPersonName"
                                       value="">

                            </div>
                            <div class="col-md-6">
                                <label>寄件人手机</label>
                                <input type="text" placeholder="请输入手机号码" class="form-control" name="sendPersonPhone"
                                       value="">

                            </div>
                        </div>

                        <div class="row bottom10">
                            <div class="col-md-6">
                                <label>收件人姓名</label>
                                <input type="text" placeholder="请输入收件人姓名" class="form-control" name="acceptPersonName"
                                       value="">
                            </div>
                            <div class="col-md-6">
                                <label>收件人电话</label>
                                <input type="text" placeholder="请输入收件人电话" class="form-control" name="acceptPersonPhone"
                                       value="">
                            </div>
                        </div>
                        <div class="row bottom10">
                            <div class="col-md-6">
                                <label>保单号</label>
                                <input type="text" placeholder="请输入保单号" class="form-control" name="insuranceNo"
                                       value="">
                            </div>
                            <div class="col-md-6">
                                <label>保单金额</label>
                                <input type="number" placeholder="请输入保单金额" class="form-control" name="insuranceAmount"
                                       value="">
                            </div>
                        </div>
                        <div class="row bottom10">
                            <div class="col-md-12">
                                <label>关联维修单号(逗号分隔)</label>
                                <input type="textarea" placeholder="请输入维修单" class="form-control" name="repairOrderNoJson" id="repairOrderNoJson"
                                       value="">
                            </div>
                        </div>
                        <div class="row bottom10">
                            <div class="col-md-6">
                                <label>类别</label>
                                <select class="form-control" name="sendType">
                                    <option value="1">寄件</option>
                                    <option value="2">收件</option>
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
                                <label><span style="color: red">*</span>快递名称</label>
                                <input type="text" placeholder="请输入快递名称" class="form-control" name="expressName" id="expressName"
                                       value="${bean.expressName}" autocomplete="off">
                            </div>

                            <div class="col-md-6">
                                <label><span style="color: red">*</span>快递单号</label>
                                <input type="text" placeholder="请输入快递单号" class="form-control" name="expressNo" id="expressNo"
                                       value="${bean.expressNo}">
                            </div>
                        </div>

                        <div class="row bottom10">

                            <div class="col-md-6">
                                <label>付款方式</label>
                                <select class="form-control" name="payType">
                                    <option value="1" <c:if test="${bean.payType eq 1}">selected="selected"</c:if>>寄付</option>
                                    <option value="2" <c:if test="${bean.payType eq 2}">selected="selected"</c:if>>到付</option>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <label>付款金额</label>
                                <input type="number" placeholder="请输入金额" class="form-control" name="payAmount"
                                       value="${bean.payAmount}">
                            </div>
                        </div>

                        <div class="row bottom10">
                            <div class="col-md-6">
                                <label>寄件人姓名</label>
                                <input type="text" placeholder="请输入姓名" class="form-control" name="sendPersonName"
                                       value="${bean.sendPersonName}">

                            </div>
                            <div class="col-md-6">
                                <label>寄件人手机</label>
                                <input type="text" placeholder="请输入手机号码" class="form-control" name="sendPersonPhone"
                                       value="${bean.sendPersonPhone}">

                            </div>
                        </div>

                        <div class="row bottom10">
                            <div class="col-md-6">
                                <label>收件人姓名</label>
                                <input type="text" placeholder="请输入收件人姓名" class="form-control" name="acceptPersonName"
                                       value="${bean.acceptPersonName}">
                            </div>
                            <div class="col-md-6">
                                <label>收件人电话</label>
                                <input type="text" placeholder="请输入收件人电话" class="form-control" name="acceptPersonPhone"
                                       value="${bean.acceptPersonPhone}">
                            </div>
                        </div>
                        <div class="row bottom10">
                            <div class="col-md-6">
                                <label>保单号</label>
                                <input type="text" placeholder="请输入保单号" class="form-control" name="insuranceNo"
                                       value="${bean.insuranceNo}">
                            </div>
                            <div class="col-md-6">
                                <label>保单金额</label>
                                <input type="number" placeholder="请输入保单金额" class="form-control" name="insuranceAmount"
                                       value="${bean.insuranceAmount}">
                            </div>
                        </div>
                        <div class="row bottom10">
                            <div class="col-md-12">
                                <label>关联维修单号(逗号分隔)</label>
                                <input type="textarea" placeholder="请输入维修单" class="form-control" name="repairOrderNoJson" id="repairOrderNoJson"
                                       value="${bean.repairOrderNoJson}">
                            </div>
                        </div>
                        <div class="row bottom10">
                            <div class="col-md-6">
                                <label>类别</label>
                                <select class="form-control" name="sendType">
                                    <option value="1" <c:if test="${bean.sendType eq 1}">selected="selected"</c:if>>寄件</option>
                                    <option value="2" <c:if test="${bean.sendType eq 2}">selected="selected"</c:if>>收件</option>
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

