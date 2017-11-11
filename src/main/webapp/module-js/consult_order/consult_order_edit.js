/**
 * Created by kee on 17/11/9.
 */
require(['jquery','yaya','selector2','cityselect','dateTimePicker'], function ($, yaya,selector2,cityselect) {

    // console.log(${categoryJson});
    // console.log(jsonObj);
    // $("#category").citySelect({
    //     url:jsonObj,
    //     prov:firstCategory,
    //     city:secondCategory,
    //     nodata:"none"
    // });





    // $('#saveBtn').click(function () {
    //
    //     var customerName = $("#customerName").val();
    //     var customerPhone = $('#customerPhone').val();
    //     var customerSex = $('#customerSex').val();
    //     var customerAddress = $('#customerAddress').val();
    //     var wechatNo = $('#wechatNo').val();
    //     var wechatNickname = $('#wechatNickname').val();
    //     var deviceNo = $('#deviceNo').val();
    //     var channelSource = $('#channelSource').val();
    //     var repairCommands = $('#repairCommands').val();
    //     var province = $('#province').val();
    //     var city = $('#city').val();
    //     var brandId = $('#brand').val();
    //     var firstCategory = $('#firstCategory').val();
    //     var secondCategory = $('#secondCategory').val();
    //     var channelUrl = $('#channelUrl').val();
    //     var keywords = $('#keywords').val();
    //     var priceLimit = $('#priceLimit').val();
    //     var timeLimit = $('#timeLimit').val();
    //     var qulityLimit = $('#qulityLimit').val();
    //     var specialCommands = $('#specialCommands').val();
    //     var vistorDate = $('#vistorAt').val();
    //     var imagesPath = $('.filelist').data('path');
    //     var expressNo = $('#expressNo').val();
    //     var deliverType = $('#deliverType').val();
    //     var comment = $('#commentId').val();
    //     var bookShopId = $('#bookShopId').val();
    //     var aliNo = $('#aliNo').val();
    //     var aliNickname = $('#aliNickname').val();
    //     var blogNo = $('#blogNo').val();
    //     var blogNickname = $('#blogNickname').val();
    //     var QQNo = $('#QQNo').val();
    //     var QQNickname = $('#QQNickname').val();
    //
    //
    //
    //     console.log($('.filelist').data("path"));
    //     $.ajax({
    //         url:'/consult-order/update',
    //         method: 'post',
    //         dataType: 'json',
    //         data: {
    //             id:$('#orderId').data("id"),
    //             customerName: customerName,
    //             customerPhone: customerPhone,
    //             customerSex: customerSex,
    //             customerAddress: customerAddress,
    //             wechatNo:wechatNo,
    //             wechatNickname:wechatNickname,
    //             deviceNo:deviceNo,
    //             channelSource:channelSource,
    //             repairCommands:repairCommands,
    //             province:province,
    //             city:city,
    //             brandId:brandId,
    //             firstCategoryName:firstCategory,
    //             secondCategoryName:secondCategory,
    //             channelUrl:channelUrl,
    //             keywords:keywords,
    //             priceLimit:priceLimit,
    //             timeLimit:timeLimit,
    //             qulityLimit:qulityLimit,
    //             specialCommands:specialCommands,
    //             imagesPath:imagesPath,
    //             vistorDate:vistorDate,
    //             expressNo:expressNo,
    //             bookShopId:bookShopId,
    //             deliverType:deliverType,
    //             comment:comment,
    //             aliNo:aliNo,
    //             aliNickname:aliNickname,
    //             blogNo:blogNo,
    //             blogNickname:blogNickname,
    //             QQNo:QQNo,
    //             QQNickname:QQNickname
    //
    //         },
    //         success: function (data) {
    //             if(data.code == 1200){
    //                 yaya.layer.msg("修改成功");
    //                 setTimeout(function () {
    //                     window.location.href = ctx+'/consult-order/alllist';
    //                 },1000);
    //
    //             }
    //
    //         }
    //     });
    //
    // });

});
