require([ 'jquery', 'yaya', 'datatables.net' ], function($, yaya) {
	var $JBrandList = $('#J_brandList');
	// 初始化
    var table = $JBrandList.DataTable({
        'scrollX': true,
        'processing': true,
        'searching': false,
        'lengthChange': false,
        'sort': false,
        'serverSide': true,
        'serverSide': true,
        'lengthMenu': [10, 20, 50, 100],
        'ajax':{
            'url':ctx + '/brand/query',
			'method': 'get',
            'data': function (d) {
                d.extra_search = $('#keywords').val();
            }
        },
    	'columns': [
    			{'data' : 'name'},
    			{'data' : 'firstLetter'},
    			{'data' : 'chinese'},
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
            searchPlaceholder: '请输入品牌名称'
        }
    });
	
    // 品牌查询
    $("#J_brandSerch").click(function(){
        table.draw();
    });	
	
    // 新增品牌
	$("#J_brandAdd").click(function() {
		$.ajax({
			url : ctx + '/brand/add',
			method : 'get',
			dataType : 'html',
			success : function(str) {
				yaya.layer.open({
					type : 1,
					title : '品牌新增',
					content : str, //注意，如果str是object，那么需要字符拼接。
					area : '450px',
					shadeClose : true,
					btn : ['保存'],
					success : function(layer,index) {
						checkBrandForm();
					},
					yes : function(index) {
						var isValid = $("#J_brandForm").valid();
						if (isValid) {
							$.ajax({
								url : ctx + '/brand/save',
								data : $("#J_brandForm").serialize(),
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
		
	// 品牌表单验证
	function checkBrandForm(){
		yaya.validate({
			el : "#J_brandForm",
			rules : {
				name : "required",
				firstLetter : "required",
				chinese : "required",
			},
			messages : {
				name : "品牌名称不能为空",
				firstLetter : "品牌首字母不能为空",
				chinese : "中文含义不能为空",
			}
		});
	}

	// 品牌编辑
	$JBrandList.on('click','.J_edit',function() {
		var editBtn = $(this);
		$.ajax({
			url : ctx + '/brand/edit',
			method : 'get',
			data : {id : editBtn.data('id')},
			dataType : 'html',
			success : function(str) {
				yaya.layer.open({
					type : 1,
					title : '品牌修改',
					content : str, //注意，如果str是object，那么需要字符拼接。
					area : '800px',
					shadeClose : true,
					shade : [0.7,'#000',true ],
					btn : [ '保存' ],
					success : function(layer, index) {
						checkBrandForm();
					},
					yes : function(index) {
						var isValid = $("#J_brandForm").valid();
						if (isValid) {
							$.ajax({url : ctx + '/brand/update',
								traditional : true,
								data : $('#J_brandForm').serialize(),
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

	// 品牌删除
	$JBrandList.on('click', '.J_delete', function() {
		var id = $(this).data('id');
		yaya.layer.confirm('确定删除该品牌？', {
			btn : [ '确定', '取消' ]
		}, function(index) {
			$.ajax({
				url : ctx + '/brand/delete',
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