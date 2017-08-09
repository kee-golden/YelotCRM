<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/common/taglibs.jsp"%>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>CRM管理后台</title>

<%@include file="/WEB-INF/common/static.jsp"%>
<link href="${ctx}/module-css/repair-order.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/webuploader2/css/webuploader.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/webuploader2/css/style.css">

<c:set var="PARENT_MENU_CODE" value="RepairOrder_Manage" />
<c:set var="CHILD_MENU_CODE" value="RepairOrder_Add" />

<script>
	var ctx = '${ctx}';
    var attributesJson = eval('${attributesJson}');

</script>

	<style>
		.demo{min-width:360px;margin:30px auto;padding:10px 20px}
		.demo h3{line-height:40px; font-weight: bold;}
		.file-item{float: left; position: relative; width: 110px;height: 110px; margin: 0 20px 20px 0; padding: 4px;}
		.file-item .info{overflow: hidden;}
		.uploader-list{width: 100%; overflow: hidden;}
	</style>


</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow: hidden">
	<div id="wrapper">
		<%@include file="/WEB-INF/common/top_logo_nav.jsp"%>
		<%@include file="/WEB-INF/common/slideBar.jsp"%>

		<!--右侧部分开始-->


		<div id="page-wrapper" class="wrapper wrapper-content cover_banner " style="height: 82%; overflow-y: auto;">
			<div class="container top_con" style="width: 100%; min-width: 1000px">
				<h6>
					<span class="glyphicon glyphicon-briefcase work "></span>客户信息<i class="pull-right iconfont ">&#xe658;</i>
					<div class="clearfix"></div>
				</h6>
				<div class="row top_row col-md-12 b-r">
					<div><h5>查询该客户是否已存在，不存在需要到客户管理中，创建一个客户</h5></div>

					<form class="form-inline bottom10" role="form">
						<div class="form-group">
							<input type="text" class="form-control" id="phone" name="phone" placeholder="请输入手机号"/>
							<input id="customerSearchBtn" type="button" class="form-control btn-group" value="查询"/>
						</div>
					</form>
					<hr/>

					<div id="customerContainer" class="col-md-12 b-r">

						<div class="row bottom10">
							<h3 class="m-t-none m-b">基础信息</h3>
							<input type="hidden" id="customerId" data-id=""/>
							<div class="col-md-2">
								<label><span style="color: red"></span>用户名</label>
								<input type="text" placeholder="请输入用户名" class="form-control" name="name" id="J_name"
									    autocomplete="off">
							</div>

							<div class="col-md-3">
								<label><span style="color: red"></span>手机号</label>
								<input type="text" placeholder="请输入手机号" class="form-control" name="phone" id="J_phone"
									   >
							</div>

							<div class="col-md-2">
								<label>省市</label>
								<div id="prov_city">
									<select class="prov" name="province" id="firstCategory"></select>
									<select class="city" disabled="disabled" name="city" id="secondCategory"></select>
								</div>

							</div>
							<div class="col-md-5">
								<label>详细地址</label>
								<input type="text" placeholder="请输入详细地址" class="form-control" name="address" id="J_address"
									   value="${bean.address}">

							</div>
						</div>

					</div>

					<div id="customerTip">
						<div><h5>该客户不存在，创建一个客户，请点击<a href="${ctx}/customer/index">创建客户</a></h5></div>
					</div>

				</div>
			</div>
			<div class="container top_con" style="width: 100%; min-width: 1000px">
				<h6>
					<span class="glyphicon glyphicon-folder-open work"></span>产品信息<i class="pull-right iconfont ">&#xe658;</i>
					<div class="clearfix"></div>
				</h6>
				<div class="row">
					<div>
						<%--<h3 class="m-t-none m-b">基础信息</h3>--%>

						<div id="category" class="row bottom10">
							<label class="col-xs-1">分类:</label>
							<select class="prov col-xs-1" name="province"></select>
							<select class="city col-xs-1" disabled="disabled" name="city"></select>
							<label class="col-xs-1">品牌：</label>
							<span>
							<select id="brand" name="brand">
								<%--<option value="A">A</option>--%>
								<%--<option value="B">B</option>--%>
								<%--<option value="C">C</option>--%>
								<c:forEach items="${brandList}" var="item">
									<option value="${item.name}">${item.name}</option>
								</c:forEach>
							</select>
						</span>
						</div>
						<div id="attributes" class="bottom10">

						</div>
						<hr/>
						<label>服务项:</label>
							<select id="serviceItem" name="serviceItem">
								<c:forEach items="${categoryServiceItemList}" var="item">
									<option value="${item.serviceItemId}">${item.serviceName}</option>
								</c:forEach>

							</select>
					</div>

				</div>
			</div>
			<div class="container top_con" style="width: 100%; min-width: 1000px">
				<h6>
					<span class="glyphicon glyphicon-folder-open work"></span>图片详情<i class="pull-right iconfont ">&#xe658;</i>
					<div class="clearfix"></div>
				</h6>
				<div class="row">
					<div class="demo">
						<div id="uploader">
							<div class="queueList">
								<div id="dndArea" class="placeholder">
									<div id="filePicker"></div>
									<p>或将照片拖到这里，单次最多可选10张</p>
								</div>
							</div>
							<div class="statusBar" style="display:none;">
								<div class="progress">
									<span class="text">0%</span>
									<span class="percentage"></span>
								</div><div class="info"></div>
								<div class="btns">
									<div id="filePicker2"></div><div class="uploadBtn">开始上传</div>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>

			<div class="container top_con" style="width: 100%; min-width: 1000px">
				<h6>
					<span class="glyphicon glyphicon-folder-open work"></span>维修详情<i class="pull-right iconfont ">&#xe658;</i>
					<div class="clearfix"></div>
				</h6>
				<div class="row">
					<form id="J_customerForm" role="form" class="form-inline">
						<table id="customer_table">
							<tr>
								<td class="customer_table_td_lable4"><label for="username" class="form-label"><span class="titl">维修内容:</span></label></td>
								<td class="customer_table_td_input4"><input type="text" class="form-control" name="username" id="username" autocomplete="off"></td>
								<td class="customer_table_td_lable4"><label for="username" class="form-label"><span class="titl">工期时间:</span></label></td>
								<td class="customer_table_td_input4"><input type="text" class="form-control" name="username" id="username" autocomplete="off" style="width: 40%">&nbsp;至&nbsp;<input type="text" class="form-control" name="username" id="username" autocomplete="off" style="width: 40%"></td>
							</tr>
							<tr>
								<td class="customer_table_td_lable4"><label for="username" class="form-label"><span class="titl">维修预付款:</span></label></td>
								<td class="customer_table_td_input4"><input type="text" class="form-control" name="username" id="username" autocomplete="off"></td>
								<td class="customer_table_td_lable4"><label for="username" class="form-label"><span class="titl">维修总费用:</span></label></td>
								<td class="customer_table_td_input4"><input type="text" class="form-control" name="username" id="username" autocomplete="off"></td>
							</tr>
							<tr>
								<td class="customer_table_td_lable4"></td>
								<td colspan="4" style="text-align: right;">
									<input type="button" class="form-button" value="保存">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" class="form-button" value="重置">
								</td>
								<%--<td class="customer_table_td_input4"></td>--%>
							</tr>
						</table>
					</form>
					<div class="col-lg-6  col-md-12"></div>
					<div class="col-lg-6 col-md-12"></div>
				</div>
			</div>
		</div>
	</div>
	<div style="margin-top: -30px">
		<%@include file="/WEB-INF/common/bottom.jsp"%>
	</div>
</body>
<script src="${ctx}/static/require/require.js"></script>
<script src="${ctx}/static/require/require.config.js"></script>
<script src="${ctx}/static/jquery/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="${ctx}/static/webuploader2/js/webuploader.min.js"></script>
<script type="text/javascript" src="${ctx}/static/webuploader2/js/upload.js"></script>

<script type="text/javascript" src="${ctx}/static/cityselect/js/jquery.cityselect.js"></script>
<script src="${ctx}/module-js/order/repair_order_add.js"></script>

<script>
	<%--var attrbutesJson = eval(${attributesJson});--%>
//	console.log("attrbutes:"+JSON.stringify(attrbutesJson));
	var jsonObj = eval(${categoryJson});//转化为json 对象
	 $("#category").citySelect({
        url:jsonObj,
		prov:'${firstCategory}',
        city:'${secondCategory}',
        nodata:"none"
    });

</script>

<script>
    $(function(){
        var $list = $('#thelist'),
            $btn = $('#ctlBtn');

        var uploader = WebUploader.create({
            resize: false, // 不压缩image
            swf: '${ctx}/static/webuploader2/js/uploader.swf', // swf文件路径
            server: 'upload.php', // 文件接收服务端。
            pick: '#picker', // 选择文件的按钮。可选
            chunked: true, //是否要分片处理大文件上传
            chunkSize:2*1024*1024 //分片上传，每片2M，默认是5M
            //auto: false //选择文件后是否自动上传
            // chunkRetry : 2, //如果某个分片由于网络问题出错，允许自动重传次数
            //runtimeOrder: 'html5,flash',
            // accept: {
            //   title: 'Images',
            //   extensions: 'gif,jpg,jpeg,bmp,png',
            //   mimeTypes: 'image/*'
            // }
        });
        // 当有文件被添加进队列的时候
        uploader.on( 'fileQueued', function( file ) {
            $list.append( '<div id="' + file.id + '" class="item">' +
                '<h4 class="info">' + file.name + '</h4>' +
                '<p class="state">等待上传...</p>' +
                '</div>' );
        });
        // 文件上传过程中创建进度条实时显示。
        uploader.on( 'uploadProgress', function( file, percentage ) {
            var $li = $( '#'+file.id ),
                $percent = $li.find('.progress .progress-bar');

            // 避免重复创建
            if ( !$percent.length ) {
                $percent = $('<div class="progress progress-striped active">' +
                    '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                    '</div>' +
                    '</div>').appendTo( $li ).find('.progress-bar');
            }

            $li.find('p.state').text('上传中');

            $percent.css( 'width', percentage * 100 + '%' );
        });
        // 文件上传成功
        uploader.on( 'uploadSuccess', function( file ) {
            $( '#'+file.id ).find('p.state').text('已上传');
        });

        // 文件上传失败，显示上传出错
        uploader.on( 'uploadError', function( file ) {
            $( '#'+file.id ).find('p.state').text('上传出错');
        });
        // 完成上传完
        uploader.on( 'uploadComplete', function( file ) {
            $( '#'+file.id ).find('.progress').fadeOut();
        });

        $btn.on('click', function () {
            if ($(this).hasClass('disabled')) {
                return false;
            }
            uploader.upload();
            // if (state === 'ready') {
            //     uploader.upload();
            // } else if (state === 'paused') {
            //     uploader.upload();
            // } else if (state === 'uploading') {
            //     uploader.stop();
            // }
        });

    });

    //上传图片
    // 初始化Web Uploader
    var uploader = WebUploader.create({

        // 选完文件后，是否自动上传。
        auto: true,

        // swf文件路径
        swf: '${ctx}/static/webuploader2/js/Uploader.swf',

        // 文件接收服务端。
        server: 'upload.php',

        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#imgPicker',

        // 只允许选择图片文件。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        }
    });

    // 当有文件添加进来的时候
    uploader.on( 'fileQueued', function( file ) {
        var $list = $("#fileList"),
            $li = $(
                '<div id="' + file.id + '" class="file-item thumbnail">' +
                '<img>' +
                '<div class="info">' + file.name + '</div>' +
                '</div>'
            ),
            $img = $li.find('img');


        // $list为容器jQuery实例
        $list.append( $li );

        // 创建缩略图
        // 如果为非图片文件，可以不用调用此方法。
        // thumbnailWidth x thumbnailHeight 为 100 x 100
        uploader.makeThumb( file, function( error, src ) {
            if ( error ) {
                $img.replaceWith('<span>不能预览</span>');
                return;
            }

            $img.attr( 'src', src );
        }, 100, 100 );
    });
    // 文件上传过程中创建进度条实时显示。
    uploader.on( 'uploadProgress', function( file, percentage ) {
        var $li = $( '#'+file.id ),
            $percent = $li.find('.progress span');

        // 避免重复创建
        if ( !$percent.length ) {
            $percent = $('<p class="progress"><span></span></p>')
                .appendTo( $li )
                .find('span');
        }

        $percent.css( 'width', percentage * 100 + '%' );
    });

    // 文件上传成功，给item添加成功class, 用样式标记上传成功。
    uploader.on( 'uploadSuccess', function( file ) {
        $( '#'+file.id ).addClass('upload-state-done');
    });

    // 文件上传失败，显示上传出错。
    uploader.on( 'uploadError', function( file ) {
        var $li = $( '#'+file.id ),
            $error = $li.find('div.error');

        // 避免重复创建
        if ( !$error.length ) {
            $error = $('<div class="error"></div>').appendTo( $li );
        }

        $error.text('上传失败');
    });

    // 完成上传完了，成功或者失败，先删除进度条。
    uploader.on( 'uploadComplete', function( file ) {
        $( '#'+file.id ).find('.progress').remove();
    });

</script>

</html>
