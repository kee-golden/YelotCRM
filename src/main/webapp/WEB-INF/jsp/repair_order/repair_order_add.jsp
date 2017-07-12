<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>ITSS管理后台</title>

    <%@include file="/WEB-INF/common/static.jsp" %>

    <c:set var="PARENT_MENU_CODE" value="RepairOrder_Manage"/>
    <c:set var="CHILD_MENU_CODE" value="RepairOrder_Add"/>

    <script>
        var ctx = '${ctx}';
    </script>


</head>
<style>


.btn-default{color: #333;background-color: #fff;border-color: #ccc;}
.top_row{ padding: 15px;}
.top_con{   box-shadow: 0 1px 5px 0 #e5e5e5; background: #FFFFFF;}
.tab-content{ background: #FFFFFF}
#myTab{ position: relative;}
#myTab li{ margin-right: 20px}
#myTab span{ display: inline-block; font-size: 11px;color: #FFFFFF; background: #c46f82;text-align: center;
     border-radius: 50%; width: 23px; height: 23px; line-height: 23px; position: absolute; right:-12px; top: -12px;}
    .top_con h6{ height:28px; background: #f1efef; font-size: 14px;  line-height: 28px; font-weight: 600; padding-left: 10px; margin-bottom: 20px}
    .work{ margin-right: 10px;}
#box01,#box02{ height:350px; padding-top: 10px}
#box02{ padding-left: 50px;}
.iconfont{ margin-right: 10px; font-size: 24px; cursor: pointer}
.container h6{ overflow: hidden}
    #page-wrapper,.wrapper,.wrapper-content{ padding: 0!important;}
</style>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
    <%@include file="/WEB-INF/common/top_logo_nav.jsp" %>
    <%@include file="/WEB-INF/common/slideBar.jsp" %>

    <!--右侧部分开始-->


    <div id="page-wrapper" class="wrapper wrapper-content cover_banner "
        style="height: 82%;overflow-y: auto; ">
        <div class="container top_con" style=" width: 100%;min-width: 1200px">
            <h6><span class="glyphicon glyphicon-briefcase work "></span>顾客信息<i class="pull-right iconfont ">&#xe658;</i> <div class="clearfix"></div></h6>
            <div class="row top_row">
               <%-- tab组件--%>
               <ul class="nav nav-tabs" id="myTab">
                         <c:if test="${power!=1}">
                             <li class="active"><a sta="1">待处理 <span id="countAccepted">${countAccepted}</span></a></li>
                         </c:if>
                        <c:if test="${stausList=='2'}">
                            <li ><a sta="2">待审批<span id="count">${count}</span ></a></li>
                        </c:if>
                        <li><a sta="3">我的请求<span id="countRequest">${countRequest}</span></a></li>
                        <c:if test="${stausList1=='11'}">
                        <li><a sta="4">待发布<span id="total">${total}</span></a></li>
                        </c:if>
                </ul>
                <div class="clearfix"></div>

                   <%--tab页面内容--%>
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="content"></div>
                </div>
            </div>
     </div>
        <div class="container top_con" style=" width: 100%;min-width: 1200px">
            <h6><span class="glyphicon glyphicon-folder-open work"></span>产品信息<i class="pull-right iconfont ">&#xe658;</i><div class="clearfix"></div></h6>
            <div class="row">
                <div class="col-lg-6  col-md-12" id="box01">


                </div>
                <div class="col-lg-6 col-md-12" id="box02"></div>
            </div>
        </div>
        <div class="container top_con" style=" width: 100%;min-width: 1200px">
            <h6><span class="glyphicon glyphicon-folder-open work"></span>图片详情<i class="pull-right iconfont ">&#xe658;</i><div class="clearfix"></div></h6>
            <div class="row">
                <div class="col-lg-6  col-md-12">


                </div>
                <div class="col-lg-6 col-md-12"></div>
            </div>
        </div>
        <div class="container top_con" style=" width: 100%;min-width: 1200px">
            <h6><span class="glyphicon glyphicon-folder-open work"></span>维修详情<i class="pull-right iconfont ">&#xe658;</i><div class="clearfix"></div></h6>
            <div class="row">
                <div class="col-lg-6  col-md-12">


                </div>
                <div class="col-lg-6 col-md-12"></div>
            </div>
        </div>
    </div>
</div>
<div style="margin-top:-30px">
    <%@include file="/WEB-INF/common/bottom.jsp" %>
</div>
</body>

<script src="${ctx}/static/require/require.js"></script>
<script src="${ctx}/static/require/require.config.js"></script>
<script src="${ctx}/module-js/order/repair_order.js"></script>
</html>
