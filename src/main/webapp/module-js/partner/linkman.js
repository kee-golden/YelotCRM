/**
 * Created by gc on 2017/4/7 0007.
 */

var table ;
var roles=[];
var name = "";
require(['jquery', 'yaya','webuploader', 'datatables.net'], function ($, yaya,WebUploader) {


    var chooseStatus = $("#chooseStatus").val();

    $("input[type='checkbox']").click(function () {
        roles=[];
        $('input[name="roleCheck"]:checked').each(function(){
            roles.push($(this).val());
        });
        table.draw();

    })

    $("#searfor").click(function () {
        roles=[];
        name = "";
        name = $.trim($("#keywords").val());
        $('input[name="levelCheck"]:checked').each(function(){
            roles.push($(this).val());
        });
        table.draw();
    })

    var $JLinkManList = $('#linkManList');

     table = $JLinkManList.DataTable({
        "scrollX": true,
        'processing': true,
        'searching': false,
        'lengthChange': false,
        'sort': false,
        'serverSide': true,

        'lengthMenu': [10, 20, 50, 100],
        'ajax': {
            'url': ctx + '/linkMan/linkMan-list',
            'traditional':true,
            'data': function (d) {
                d.roles=roles;
                d.name = name;
                d.chooseStatus = chooseStatus;
            }
        },
        'columns': [
            {'data': 'name',
                'render':function(data,type, full, meta){
                    if(full.flag == 0){
                        return '<a style="color: #777777">'+data+'</a>';
                    }
                    else {
                        return '<a data-id="' + full.id + '" class="J_edit">'+data+'</a>';
                    }

                }
            },
            {'data': 'relationCustomer.customerName'},
            {'data': 'responsiblePerson.nickname'},
            {'data': 'phone'},
            {'data': 'roleDesc'},
            {'data': 'dept'},
            {'data': 'job'}
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


    $JLinkManList.on('click', '.J_edit', function () {
        var nid = $(this).data('id');
        var iTop = (window.screen.height-30-550)/2; //获得窗口的垂直位置;
        var iLeft = (window.screen.width-10-800)/2; //获得窗口的水平位置;
        var win= window.open(ctx + '/linkMan/detail-page?linkManId='+nid+'','win3',
            'height=600,innerHeight=600,width=1000,innerWidth=1000,top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');


    });





    $('#J_LinkManAdd').click(function () {
        $.ajax({
            url: ctx + '/linkMan/add-page',
            method: 'post',
            dataType: 'html',
            success: function (str) {
                yaya.layer.open({
                    type: 1,
                    title: '联系人新增',
                    content: str, //注意，如果str是object，那么需要字符拼接。
                    area: ['600px','400px'],
                    shadeClose: true,
                    btn: ['保存'],
                    success: function (layer, index) {
                        uploader();
                    },
                    yes: function (index) {

                        if ($.trim($('#name').val())=="") {
                            yaya.layer.msg("请填写联系人姓名");
                        }
                        else if ($('.responsiblePersonId').length == 0) {
                            yaya.layer.msg("请选择负责人");
                        }
                        else if ($('.relationCustomerId').length == 0) {
                            yaya.layer.msg("请选择关联客户");
                        }
                        else {
                            var loadIndex = yaya.layer.load(2);
                            $(".item").each(function () {
                                var id=$(this).attr("id");
                                setTimeout(function () {
                                    console.log(id);
                                    myUploader.upload(id);
                                },400)
                            })
                            $.ajax({
                                url: ctx + '/linkMan/save',
                                traditional: true,
                                data: {
                                    name: $('#name').val(),
                                    responsiblePersonId: $('.responsiblePersonId').attr('id'),
                                    relationCustomerId: $('.relationCustomerId').attr('id'),
                                    role: $('#role').val(),
                                    phone: $.trim($('#phone').val()),
                                    dept: $.trim($('#depart').val()),
                                    job: $('#job').val(),
                                    mobilePhone: $('#mobilePhone').val(),
                                    QQ: $('#QQ').val(),
                                    relationEmail: $('#relationEmail').val(),
                                    birthday: $.trim($('#birthday').val()),
                                    address: $('#address').val(),
                                    zipCode: $('#zipCode').val(),
                                    remarks: $('#remarks').val(),
                                    linkManFile:crmId
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
            pick: '#linkManPicker',
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
                myUploader.options.formData = {crmId: crmId,state:2};
                $list.append('<div id="' + file.id + '" class="unitem" name=' + file.name + '>' +
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
