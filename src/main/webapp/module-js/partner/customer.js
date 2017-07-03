/**
 * Created by gc on 2017/4/7 0007.
 */


var table ;
var customerLevels=[];
var customerStatuss=[];
var customerSources=[];
var customerSizes=[];
var customerProfessions=[];
var customerName = "";
require(['jquery', 'yaya','webuploader', 'datatables.net'], function ($, yaya,WebUploader) {

    var  chooseStatus = $("#chooseStatus").val();


$("input[type='checkbox']").click(function () {
    customerLevels=[];
    customerStatuss=[];
    customerSources=[];
    customerSizes=[];
    customerProfessions=[];
    $('input[name="levelCheck"]:checked').each(function(){
        customerLevels.push($(this).val());
    });
    $('input[name="statusCheck"]:checked').each(function(){
        customerStatuss.push($(this).val());
    });
    $('input[name="sizeCheck"]:checked').each(function(){
        customerSources.push($(this).val());
    });
    $('input[name="professionCheck"]:checked').each(function(){
        customerSizes.push($(this).val());
    });
    $('input[name="sourceCheck"]:checked').each(function(){
        customerProfessions.push($(this).val());
    });
    table.draw();

})

 $("#searfor").click(function () {
     customerLevels=[];
     customerStatuss=[];
     customerSources=[];
     customerSizes=[];
     customerProfessions=[];
     customerName = "";
      customerName = $.trim($("#keywords").val());
     $('input[name="levelCheck"]:checked').each(function(){
         customerLevels.push($(this).val());
     });
     $('input[name="statusCheck"]:checked').each(function(){
         customerStatuss.push($(this).val());
     });
     $('input[name="sizeCheck"]:checked').each(function(){
         customerSources.push($(this).val());
     });
     $('input[name="professionCheck"]:checked').each(function(){
         customerSizes.push($(this).val());
     });
     $('input[name="sourceCheck"]:checked').each(function(){
         customerProfessions.push($(this).val());
     });
     table.draw();

 })


    var $JCustomerList = $('#customerList');

     table = $JCustomerList.DataTable({
        "scrollX": true,
        'processing': true,
        'searching': false,
        'lengthChange': false,
        'sort': false,
        'serverSide': true,
        'lengthMenu': [10, 20, 50, 100],
        'ajax': {
            'url': ctx + '/customer/customer-list',
            'traditional':true,
            'data': function (d) {
                d.customerLevels=customerLevels;
                d.customerStatuss=customerStatuss;
                d.customerSources=customerSources;
                d.customerSizes=customerSizes;
                d.customerProfessions=customerProfessions;
                d.chooseStatus = chooseStatus;
                d.customerName = customerName;
            }
        },
        'columns': [
            {'data': 'customerName',
                'render':function(data,type, full, meta){
                    //console.log(full)
                    if(full.flag == 0){
                        return '<a style="color: #777777">'+data+'</a>';
                    }
                    else {
                        return '<a data-id="' + full.id + '" class="J_edit">'+data+'</a>';
                    }

                }

            },
            {'data': 'responsiblePerson.nickname'},
            {'data': 'customerPhone'},
            {'data': 'customerLevelDesc'},
            {'data': 'customerStatusDesc'},
            {'data': 'createTime'}
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


    $JCustomerList.on('click', '.J_edit', function () {
        var nid = $(this).data('id');
        var iTop = (window.screen.height-30-550)/2; //获得窗口的垂直位置;
        var iLeft = (window.screen.width-10-800)/2; //获得窗口的水平位置;
        if(chooseStatus != 4){
            var win= window.open(ctx + '/customer/detail?nid='+nid+'','win3',
                'height=600,innerHeight=600,width=1000,innerWidth=1000,top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
        }else {
            var win= window.open(ctx + '/customer/detail?nid='+nid+'&&shareStatus=1','win3',
                'height=600,innerHeight=600,width=1000,innerWidth=1000,top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
        }


    });





    $('#J_PartnerAdd').click(function () {
        $.ajax({
            url: ctx + '/customer/customer-edit',
            method: 'get',
            dataType: 'html',
            success: function (str) {
                yaya.layer.open({
                    type: 1,
                    title: '客户新增',
                    content: str, //注意，如果str是object，那么需要字符拼接。
                    area: ['600px','400px'],
                    shadeClose: true,
                    btn: ['保存'],
                    success: function (layer, index) {
                        uploader();
                    },
                    yes: function (index) {

                        if ($.trim($('#customerName').val()) == "") {
                            yaya.layer.msg("请填写客户名称");
                        }
                        else if ($('.responsiblePersonId').length==0) {
                            yaya.layer.msg("请选择负责人");
                        }
                        else if ($('#ca').val() == null ||$('#ca').val() == "") {
                            yaya.layer.msg("请选择省市区");
                        }
                        else if ($.trim($('#detailAddress').val()) == null || $.trim($('#detailAddress').val()) == "") {
                            yaya.layer.msg("请填写详情地址");
                        }
                        else if ($.trim($('#customerPhone').val()) == null || $.trim($('#customerPhone').val()) == "") {
                            yaya.layer.msg("请填写客户电话");
                        }
                        else {
                            var loadIndex = yaya.layer.load(2);
                            $(".item").each(function () {
                                var id=$(this).attr("id");
                                setTimeout(function () {
                                    myUploader.upload(id);
                                },400)
                            })
                            $.ajax({
                                url: ctx + '/customer/save',
                                traditional: true,
                                data: {
                                    customerName: $('#customerName').val(),
                                    responsiblePersonId: $('.responsiblePersonId').attr('id'),
                                    provinceId: $('#cp').val(),
                                    cityId: $('#cc').val(),
                                    areaId: $('#ca').val(),
                                    detailAddress: $('#detailAddress').val(),
                                    contactsName: $('#contactsName').val(),
                                    customerPhone: $('#customerPhone').val(),
                                    customerLevel: $('#customerLevel').val(),
                                    customerStatus: $('#customerStatus').val(),
                                    customerSource: $('#customerSource').val(),
                                    customerSourceAddDesc : $.trim($("#customerSourceAddDesc").val()),
                                    customerProfession: $('#customerProfession').val(),
                                    customerProfessionAddDesc : $.trim($("#customerProfessionAddDesc").val()),
                                    customerNature : $("#customerNature").val(),
                                    customerSize: $('#customerSize').val(),
                                    customerCode: $('#customerCode').val(),
                                    remarks: $('#remarks').val(),
                                    privacy : $("input:radio[name='privacy']:checked").val(),
                                    customerFile:crmId
                                },

                                method: 'post',
                                dataType: 'json',
                                success: function (data) {
                                    yaya.layer.close(loadIndex);
                                    yaya.layer.close(index);
                                    if (data.code) {
                                        yaya.layer.msg("保存成功");
                                        table.draw();
                                    }
                                },
                                error: function () {
                                    yaya.layer.msg("系统异常");
                                }
                            });
                        }
                    }
                });
            }
        });//end ajax
    });

    var myUploader;
    var mydate = new Date();
    var crmId = mydate.getTime();

    function uploader() {

        myUploader = WebUploader.create({
            auto: false,
            swf: ctx + '/static/webuploader/Uploader.swf',
            server: ctx + '/file/crmFile-upload',
            pick: '#picker',
            fileVal: "fileName",
            duplicate: true,
            compress: false,
            accept: {
                title: 'doc',
                extensions: 'doc,pdf,docx,xlsx,xls',
                mimeTypes: 'application/pdf,application/msword,' +
                'application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' +
                ',application/excel'
            }
        });

        myUploader.on('uploadSuccess', function (file, responseData) {
            $('#' + file.id).find('p.state').text('已上传').css({"color": "#5FB878"});
            console.log(responseData.data);//存储时间的路径
            $('#path').val(responseData.data);
            $('#docPath').html(responseData.message);
        });

        // 当有文件被添加进队列的时候
        myUploader.on('fileQueued', function (file) {

            $('.webuploader-pick').text('继续添加');
            $("#cancelUpLoad").show();
            var same = 0;
            $(".item").each(function () {
                var name = $(this).attr("name");

                if (name == file.name) {
                    same = 1;
                    myUploader.removeFile(file.id);
                }
            })

            if (same == 0) {
                var $list = $("#thelist");
                //每次只上传一个文件,需要重新初始化该组件的内容,
                /*$list.empty();*/
                var subName;
                if (file.name.length > 5) {
                    subName = file.name.substring(0, 5) + '...';
                } else {
                    subName = file.name;
                }
                myUploader.options.formData = {crmId: crmId,state:1};
                $list.append('<div id="' + file.id + '" class="item" name=' + file.name + '>' +
                    '<h4 class="info"><a title=' + file.name + '  class="titlea">' + subName + '</a></h4>' +
                    '<span class="imgspan"><img src=' + ctx + '/img/closepm.png  class="removeFile" id=' + file.id + '></span>' +
                    '<div class="clearfix"></div>' +
                    '<div class="state" style="color: #1E9FFF">等待上传...</div>' +
                    '</div>');

                $('.removeFile').click(function () {
                    var id = this.id;
                    myUploader.removeFile(id);
                    $(this).parent().parent('.item').remove();
                })
            }

        });

        // 文件上传过程中创建进度条实时显示。
        myUploader.on('uploadProgress', function (file, percentage) {
            /*var $li = $( '#'+file.id ),
             $percent = $li.find('.progress .progress-bar');

             // 避免重复创建
             if ( !$percent.length ) {
             $percent = $('<div class="progress progress-striped active">' +
             '<div class="progress-bar" role="progressbar" style="width: 0%">' +
             '</div>' +
             '</div>').appendTo( $li ).find('.progress-bar');
             }*/

            /*  $li.find('p.state').text('上传中');*/

            /*  $percent.css( 'width', percentage * 100 + '%' );*/
        });


        myUploader.on('uploadError', function (file) {
            $('#' + file.id).find('p.state').text('上传出错').css({"color": "#FF5722"});
        });

        myUploader.on('uploadComplete', function (file) {
            $('#' + file.id).find('.progress').fadeOut();
        });
        return myUploader;
    }

})

function getTableDraw() {
    table.draw(false);
}



