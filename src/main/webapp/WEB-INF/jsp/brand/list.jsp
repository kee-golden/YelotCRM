<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<title>CRM管理后台</title>

		<%@include file="/WEB-INF/common/static.jsp" %>

		<c:set var="PARENT_MENU_CODE" value="Brand_Manage"/>

		<c:set var="CHILD_MENU_CODE" value="Brand_List"/>

		<script>
			var ctx = '${ctx}';
			var adminId = '${sessionScope.user.id}';
		</script>

		<style>
			#content-main{ position: relative}
			.xbb{ margin-right: 2px}
			#search_Big{background:#FFFFFF; margin: 10px 0;padding: 8px 0px 0px 0px; font-weight: bold; margin-bottom: 5px}
			#search_Big ul,#search_Big ul li{ padding: 0; margin: 0}
			#search_Big ul li{ float: left; margin-right: 15px}
			#search_Big ul li span{ font-size: 13px; font-family: 微软雅黑; font-weight: bold; margin-right: 15px; margin-left: 10px;
			vertical-align: middle; display: inline-block; width: 65px; text-align: right; float: left; line-height: 24px}
			#search_Big  input{ border: 1px solid #e5e5e5;  height: 24px; padding-left: 3px}
			.sear{ line-height:26px; font-size: 13px; margin-right: 15px}
			.line{ width: 100%; margin-top: 10px; border: 1px solid #efefef}
			.lrx{display: none}
			.mwdth tr{ border: 1px solid #FFFFFF  !important;}
			.inpt_width{ width: 165px; border: 1px solid #e5e5e5; height: 24px; font-weight: normal}
			#cont{ margin-top: 10px; padding-bottom: 5px}
			#cont ul{ margin-bottom: 10px}
			#cont ul li span{float: left}
			#cont select{ float: left; padding: 0}
			#otherTab select{float: left; padding: 0}
			#otherTab ul{ margin-bottom: 10px}
			#otherTab ul li span{float: left}
			.search_right{ margin:5px 15px 0 0}
			#search_Big{  box-shadow: 0 1px 5px  0 #e5e5e5;}
			#searchFrom{ margin-left: 15px; float: right}
			.ibox{ padding: 0}
			.sear_icon{ font-size: 18px; color: #428bca; vertical-align: middle; padding: 0 3px; cursor: pointer;margin-top: -10px}
			#J_brandList tr td{ position: relative; white-space: nowrap}
			#J_brandList tr td .span1{ padding:0 15px; font-size: 12px; line-height: 18px; position: absolute; left: 50%; background: #FFFFFF;
			padding: 5px;z-index: 999; border-radius: 5px; border: 1px solid #e5e5e5; display: none}
			#J_brandList{ width: 100%!important;}
			#J_brandList tr th{ white-space: nowrap}
		</style>

	</head>
	<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden" >
	<%--<div id="wrapper">--%>
		<%@include file="/WEB-INF/common/top_logo_nav.jsp" %>
		<%@include file="/WEB-INF/common/slideBar.jsp" %>

		<!--右侧部分开始-->
		<div id="page-wrapper" class="wrapper wrapper-content " style="height: 82%;overflow-y: auto;overflow-x: hidden">
			<div class="row animated fadeInRight" id="content-main">
				<div class="col-xs-12">
					<div class="ibox float-e-margins">
						<ul class="nav nav-tabs" id="myTab">
							<li class="active"><a>品牌列表</a></li>
						</ul>
						
						<div id="content" class="ibox-content">
							<div class="co_all">
								<div style="float: left">
									<a class="collapse-link" id="J_brandAdd"> <i class="fa fa-plus"></i>添加</a>
								</div>
								<form id="searchFrom"style="margin-top: -20px">
									<div id="search_Big">
										<ul class="pull-left" id="otherTab">
											<li><span >搜索：</span>
												<input type="text" id="keywords" class="inpt_width" placeholder="请输入品牌名称">
												<div class="clearfix"></div>
											</li>
										</ul>
										<div class="pull-right search_right"><a id="J_brandSerch" class="searfor"> <i class="glyphicon glyphicon-search sear_icon sear_icon"></i></a></div>
										<div class="clearfix" ></div>
										<div class="line"></div><%--线--%>
									</div>
								</form>

								<div class="clearfix"></div>
							</div>
							<div class="clearfix"></div>

							<table id="J_brandList" class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>品牌名称</th>
										<th>品牌首字母</th>
										<th>中文含义</th>
										<th>编辑</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<!--右侧部分结束-->
			</div>
			<div style="margin-top:10px;">
				<%@include file="/WEB-INF/common/bottom.jsp" %>
			</div>
		</div>
		
		<script src="${ctx}/static/require/require.js"></script>
		<script src="${ctx}/static/require/require.config.js"></script>
		<script src="${ctx}/module-js/brand/brand.js"></script>
	</body>
</html>