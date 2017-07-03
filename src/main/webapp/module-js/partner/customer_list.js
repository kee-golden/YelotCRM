/**
 * Created by lenovo on 2017/4/19.
 */
require(['jquery', 'yaya', 'datatables.net'], function ($, yaya) {


    $(function () {
        var hideUrl = "";
        if($("#roleHide").val() != null && $("#roleHide").val() != "" && $("#roleHide").val() == 1){
            hideUrl = "/customer/all-customer?sta=0";
        }else if($("#roleHide").val() != null && $("#roleHide").val() != "" && $("#roleHide").val() == 10001){
            hideUrl = "/customer/all-customer?sta=1";
        }
        else {
            hideUrl = "/customer/my-create";
        }
        $.ajax({
            url: ctx + hideUrl,
            success: function (data) {
                $('#content').html(data);
            }
        });
    });
    $('#myTab a:first').tab('show');//初始化显示哪个tab

    $('#myTab a').click(function (e) {

        e.preventDefault();//阻止a链接的跳转行为
        var sta = $(this).attr('sta');
        var name = '';
        if (sta == '0') {
            name = '/customer/all-customer?sta='+sta
        }
        else if (sta == '1') {
            name = '/customer/all-customer?sta='+sta
        }
        else if (sta == '2') {
            name = '/customer/my-responsible'
        }
         else if (sta == '3') {
            name = '/customer/my-care'
        }
        else if (sta == '4') {
            name = '/customer/my-share'
        }
        else if (sta == '10') {
            name = '/customer/my-create'
        }
        $.ajax({
            url: ctx + name,
            success: function (data) {
                $('#content').html(data);
            }
        });
        $(this).parent().parent().children().removeClass();
        $(this).parent().addClass("active");
    });
})
