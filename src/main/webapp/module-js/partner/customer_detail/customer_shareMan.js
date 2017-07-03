/**
 * Created by gc on 2017/4/7 0007.
 */


require(['jquery', 'yaya','webuploader', 'datatables.net'], function ($, yaya,WebUploader) {
    var customerId=$('#customerId').val();
    $('.tab_content').fadeIn(1000);

    var $JCustomerShareManList = $('#customer_shareMan');
    var table = $JCustomerShareManList.DataTable({
        "scrollX": true,
        'processing': true,
        'searching': false,
        'lengthChange': false,
        'sort': false,
        'serverSide': true,
        'lengthMenu': [10, 20, 50, 100],
        'ajax': {
            'url': ctx + '/customer/shareMan-list',
            'traditional':true,
            'data': function (d) {
                d.customerId=customerId;
            }
        },
        'columns': [
            {'data': 'shareMan.nickname'},
            {'data': 'shareMan.phone'},
            {'data': 'addTime'},
            {'data': 'id',
                'render':function(data,type, full, meta){
                    return '<a data-id="' + data + '" class="J_edit" name="'+full.shareMan.nickname+'">取消共享</a>'
                }
            },

        ],
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
            searchPlaceholder: '请输入用户姓名'

        }

    });

    $('#customer_shareManAdd').click(function () {
        $.ajax({
            url:ctx+'/customer/edit-responsePerson',
            success:function(str){
                yaya.layer.open({
                    type: 1,
                    title: '选择共享人',
                    content: str, //注意，如果str是object，那么需要字符拼接。
                    area: ['420px', '450px'],
                    shadeClose: true,
                    btn: ['保存'],
                    success: function (layer, index) {

                    },
                    yes: function (index) {
                        //先判定是否重复
                        var y=$("#selecttree2 .coloritem_right").length;
                        if($("#selecttree2 .coloritem_right").length > 0){
                            var id=$("#selecttree2 .coloritem_right").attr('id').replace("-","");
                            var adminName = $("#selecttree2 .coloritem_right").text();
                            if($("#creatorHiddenId").val() == id){
                                yaya.layer.msg("该成员为客户创建人!");
                            }else {

                                $.ajax({
                                    url: ctx + '/customer/shareMan-repeat',
                                    data: {customerId: customerId, adminId: id},
                                    success: function (data) {
                                        if (data.code) {
                                            var loadIndex = yaya.layer.load(2);
                                            $.ajax({
                                                url: ctx + '/customer/save-shareMan',
                                                data: {customerId: customerId, adminId: id,adminName:adminName},
                                                success: function (data) {
                                                    if (data.code) {
                                                        yaya.layer.close(loadIndex);
                                                        yaya.layer.close(index);
                                                        yaya.layer.msg("保存成功");
                                                        table.draw();
                                                    }
                                                },
                                                error: function () {
                                                    yaya.layer.close(loadIndex);
                                                    yaya.layer.close(index);
                                                    yaya.layer.msg("请求超时");
                                                }
                                            })
                                        }
                                        else {
                                            yaya.layer.msg("已存在该共享人");
                                        }
                                    }
                                })
                            }
                        }else if($("#selecttree2 .coloritem_right").length == 0){
                            yaya.layer.msg("请选择负责人");
                        }
                    }
                })
            }
        })
    })

    $JCustomerShareManList.on('click', '.J_edit', function () {
        var nid = $(this).data('id');
        var adminName = $(this).attr('name');
        yaya.layer.confirm('请确认是否取消？',{
            btn:['确定','取消']//按钮
        },function (index) {
            $.ajax({
                method: 'post',
                url: ctx + '/customer/delete-shareMan',
                dataType: 'json',
                data:{id:nid,adminName:adminName,customerId:customerId},
                success: function (msg) {
                    if (msg.code) {
                      yaya.layer.msg("保存成功");
                        table.draw(false);
                    }
                }
            });
        })
    });



})





