/**
 * @author xyzloveabc
 * @2017年9月13日
 */
require([ 'jquery', 'yaya', 'selector2','datatables.net' ], function($, yaya, selector2) {

    $("#status").select2({
        tags: true,
    });
    
	var $JOrderList = $('#J_orderList');
	// 初始化
    var table = $JOrderList.DataTable({
        'scrollX': true,
        'processing': true,
        'searching': false,
        'lengthChange': false,
        'sort': false,
        'serverSide': true,
        'serverSide': true,
        'lengthMenu': [10, 20, 50, 100],
        'ajax':{
            'url':ctx + '/rpt-repair-order/query',
			'method': 'get',
            'data': function (d) {
                d.dateArea = $('#dateArea').val();
                d.shopId = $('#shopId').val();
                d.firstCategory = $('#firstCategory').val();
                d.secondCategory = $('#secondCategory').val();
                d.onLineUser = $('#onLineUser').val();
                d.shopUser = $('#shopUser').val();
                d.deliverType = $('#deliverType').val();
                d.customerType = $('#customerType').val();
                d.channelSource = $('#channelSource').val();
                d.status = $('#status').val() == null ? '' : $('#status').val().toString();
            }
        },
    	'columns': [
    			{'data' : 'shopName'},// 门店
    			{'data' : 'createAt'},// 接单日
    			{'data' : 'today'},// 今天日期
    			{'data' : 'orderNo'},// 单号
    			{'data' : 'pickupAt'},// 预计归还日
    			{'data' : 'daoQiTiXing'},// 到期提醒
    			{'data' : 'songHuiDate'}, // 送回日
    			{'data' : 'quHuoDate'}, // 取货日
    			{'data' : 'consultCreateUserName'}, // 首接人
    			{'data' : 'createUserName'}, // 接单人
    			{'data' : 'deliverType'}, // 接单方式
    			{'data' : 'typeName'}, // 订单类型
    			{'data' : 'statusName'}, // 订单状态 
    			{'data' : 'jiSuanYueFen'}, // 计算月份
    			{'data' : 'brandName'}, // 品牌
    			{'data' : 'firstCategoryName'}, // 货品类型
    			{'data' : 'secondCategoryName'}, // 货品名称
    			{'data' : 'repairDesc'}, // 维修内容
    			{'data' : 'serviceItemNames'}, // 维修工序
    			{'data' : 'isFanXiu'}, // 是否返修
    			{'data' : 'totalPayment'}, // 小结
    			{'data' : 'materialPayment'}, // 料钱
    			{'data' : 'huiShouLiao'}, // 回收料
    			{'data' : 'discountAmountPayment'}, // 优惠
    			{'data' : 'nonPaymentTypeName'}, // 付款方式
    			{'data' : 'fuKuanJine'}, // 付款金额
    			{'data' : 'advancePayment'}, // 定金
    			{'data' : 'pingZhengHao'}, // 凭证号
    			{'data' : 'faPiao'}, // 发票
    			{'data' : 'expressMoney'}, // 快递费
    			{'data' : 'expressName'}, // 快递公司
    			{'data' : 'expressNo'}, // 快递单号
    			{'data' : 'insuranceAmount'}, // 保费
    			{'data' : 'insuranceNo'}, // 保单号
    			{'data' : 'heJiZhiChu'}, // 合计支出
    			{'data' : 'customerName'}, // 姓名
    			{'data' : 'customerSex'}, // 性别
    			{'data' : 'customerPhone'}, // 电话
    			{'data' : 'province'}, // 省
    			{'data' : 'city'}, // 市
    			{'data' : 'customerAddress'}, // 快递地址
    			{'data' : 'wechatNickname'}, // 微信名称
    			{'data' : 'wechatId'}, // 微信号
    			{'data' : 'customerQQ'}, // 其他账号
    			{'data' : 'sheBeiHao'}, // 设备号
    			{'data' : 'customerType'}, // 客户类型
    			{'data' : 'channelSource'}, // 来源
    			{'data' : 'guanJianCi'}, // 搜索关键词
    			{'data' : 'zhuoLuYe'}, // 着陆页链接
    			{'data' : 'beiZhu'}, // 备注
    			{'data' : 'duiBiZhaoPian'}, // 对比照片
    			{'data' : 'consultCreateAt'}],// 起初咨询时间
        'language': {
            'lengthMenu': '每页显示 _MENU_ 条记录',
            'zeroRecords': '没有检索到数据',
            'info': '第 _START_ 至 _END_ 条数据 共 _TOTAL_ 条记录',
            'infoEmpty': '没有数据',
            'processing': '正在加载数据...',
            'paginate': {
                'first': '首页',
                'previous': '前页',
                'next': '后页',
                'last': '尾页'
            }
        }
    });
    
    $("#search").click(function(){
        table.draw();
    });
    
    //显示隐藏列
    $('.toggle-vis').on('change', function (e) {
        e.preventDefault();
        console.log($(this).attr('data-column'));
        var column = table.column($(this).attr('data-column'));
        column.visible(!column.visible());
    });
    
    $('#showcol').click(function (e) {
    	var x = e.target.offsetLeft + e.target.offsetWidth;
    	var y = e.target.offsetTop;
        $('.showul').toggle();
        $('.showul').css({"left":x, "top":y})
    })
    
    $("#shopId").change(function(){
    	$.ajax({
	        url: ctx + '/rpt-repair-order/findUserByShopId',
	        method: 'get',
	        dataType: 'json',
	        data: {
	        	shopId: $("#shopId").val(),
	        },
	        success: function (data) {
	            $("#shopUser").empty();
	            $("#shopUser").append('<option value="">全部</option>');
	            for(var i = 0;i < data.length;i++){
	                var str = '<option value=\"'+data[i].id+'\">'+data[i].realname+'</option>';
	                $("#shopUser").append(str);
	            }
	        }
	    });
    });

    $("#exportExcel").click(function(){
    	var exportColumnList = document.getElementsByClassName("toggle-vis");
    	var  exportColumnStr = "";
    	for(var i=0;i<exportColumnList.length;i++){
    		if(exportColumnList[i].checked){
    			if(i<exportColumnList.length-1){
                    exportColumnStr += exportColumnList[i].id + ",";
				} else {
                    exportColumnStr += exportColumnList[i].id;
				}
			}
		}
    	var params = "";
		params += "&dateArea=" + $('#dateArea').val(); 
		params += "&shopId=" + $('#shopId').val(); 
		params += "&firstCategory=" + $('#firstCategory').val(); 
		params += "&secondCategory=" + $('#secondCategory').val(); 
		params += "&onLineUser=" + $('#onLineUser').val(); 
		params += "&shopUser=" + $('#shopUser').val(); 
		params += "&deliverType=" + $('#deliverType').val(); 
		params += "&customerType=" + $('#customerType').val(); 
		params += "&channelSource=" + $('#channelSource').val(); 
		params += "&status=" + ($('#status').val() == null ? '' : $('#status').val().toString());
        params += "&exportColumnStr=" + exportColumnStr;

        window.location = ctx + '/rpt-repair-order/exportExcel?' + params;
    });
})
