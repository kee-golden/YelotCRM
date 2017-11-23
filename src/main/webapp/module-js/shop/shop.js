require([ 'jquery', 'yaya', 'datatables.net' ], function($, yaya) {
	var $JShopList = $('#J_shopList');
	// 初始化
    var table = $JShopList.DataTable({
        'scrollX': true,
        'processing': true,
        'searching': false,
        'lengthChange': false,
        'sort': false,
        'serverSide': true,
        'serverSide': true,
        'lengthMenu': [10, 20, 50, 100],
        'ajax':{
            'url':ctx + '/shop/query',
			'method': 'get',
            'data': function (d) {
                d.extra_search = $('#keywords').val();
            }
        },
    	'columns': [
    			{'data' : 'name'},
    			{'data' : 'address'},
    			{'data' : 'phone'},
    			{'data' : 'create_at'},
    			{'data' : 'update_at'},
    			{'data' : 'id', 'render' : function(data, type,full, meta) {
    				return '<a href="javascript:;;" data-id="' + data + '" class="J_edit"><i class="fa fa-edit" aria-hidden="true"></i>编辑</a>&nbsp;&nbsp;'
    				+ '<a href="javascript:;;" data-id="' + data + '" class="J_delete"><i class="fa fa-trash" aria-hidden="true"></i>删除</a>';
    			}
    			}],
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
            },
            'search': '查询',
            searchPlaceholder: '请输入门店名称'
        }
    });
	
    // 门店查询
    $("#J_shopSerch").click(function(){
        table.draw();
    });	
	
    // 新增门店
	$("#J_shopAdd").click(function() {
		$.ajax({
			url : ctx + '/shop/add',
			method : 'get',
			dataType : 'html',
			success : function(str) {
				yaya.layer.open({
					type : 1,
					title : '门店新增',
					content : str, //注意，如果str是object，那么需要字符拼接。
					area : '500px',
					shadeClose : true,
					btn : ['保存'],
					success : function(layer,index) {
						checkShopForm();
					},
					yes : function(index) {
						var isValid = $("#J_shopForm").valid();
						if (isValid) {
							$.ajax({
								url : ctx + '/shop/save',
								data : $("#J_shopForm").serialize(),
								method : 'post',
								dataType : 'json',
								success : function(data) {
	                                if (data.code == 1200) {
	                                    table.draw();
	                                    yaya.layer.close(index);
	
	                                }
	                                else {
	                                    yaya.layer.msg(data.data.msg);
	                                    $("#name").focus;
	                                }
								},
								error : function(data) {
									
								}
							});
						}
					}
				});
			},
			error : function() {
	
			}
		});
	});
		
	// 门店表单验证
	function checkShopForm(){
		yaya.validate({
			el : "#J_shopForm",
			rules : {
				name : "required",
				address : "required",
				phone : "required",
			},
			messages : {
				name : "门店名称不能为空",
				address : "门店地址不能为空",
				phone : "联系方式不能为空",
			}
		});
	}

	// 门店编辑
	$JShopList.on('click','.J_edit',function() {
		var editBtn = $(this);
		$.ajax({
			url : ctx + '/shop/edit',
			method : 'get',
			data : {id : editBtn.data('id')},
			dataType : 'html',
			success : function(str) {
				yaya.layer.open({
					type : 1,
					title : '门店修改',
					content : str, //注意，如果str是object，那么需要字符拼接。
					area : '500px',
					shadeClose : true,
					shade : [0.7,'#000',true ],
					btn : [ '保存' ],
					success : function(layer, index) {
						checkShopForm();
					},
					yes : function(index) {
						var isValid = $("#J_shopForm").valid();
						if (isValid) {
							$.ajax({url : ctx + '/shop/update',
								traditional : true,
								data : $('#J_shopForm').serialize(),
								method : 'post',
								dataType : 'json',
								success : function(data) {
	                                if (data.code == 1200) {
	                                    table.draw();
	                                    yaya.layer.close(index);
	
	                                }
	                                else {
	                                    yaya.layer.msg(data.data.msg);
	                                    $("#name").focus;
	                                }
								},
								error : function() {

								}
							});
						}
					}

				});
			},
			error : function() {

			}
		});
	});

	// 门店删除
	$JShopList.on('click', '.J_delete', function() {
		var id = $(this).data('id');
		yaya.layer.confirm('确定删除该门店？', {
			btn : [ '确定', '取消' ]
		}, function(index) {
			$.ajax({
				url : ctx + '/shop/delete',
				data : {id : id},
				method : 'get',
				dataType : 'json',
				success : function(data) {
                    if (data.code == 1200) {
						yaya.layer.msg('删除成功!');
                        table.draw();
                    }
                    else {
						yaya.layer.msg('删除失败!');
                    }
				},
				error : function() {
					
				}
			})

		}, function() {
		})
	});
})