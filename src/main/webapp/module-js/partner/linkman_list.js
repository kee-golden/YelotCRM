/**
 * Created by lenovo on 2017/4/19.
 */
require(['jquery', 'yaya', 'datatables.net'], function ($, yaya) {
    $(function () {
        var hideUrl = "";
        if($("#roleHide").val() != null && $("#roleHide").val() != "" && $("#roleHide").val() == 1){
            hideUrl = "/linkMan/all-linkMan?sta=0";
        }
        else if($("#roleHide").val() != null && $("#roleHide").val() != "" && $("#roleHide").val() == 10001){
            hideUrl = "/linkMan/all-linkMan?sta=1";
        }
        else {
            hideUrl = "/linkMan/my-create";
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
       /* $("input[type='checkbox']").prop('checked',false);
        $("#keywords").val("");*/
        var sta = $(this).attr('sta');
        var name = '';
        if (sta == '0') {
            name = '/linkMan/all-linkMan?sta='+sta
        }
        else if (sta == '1') {
            name = '/linkMan/all-linkMan?sta='+sta
        }
        else if (sta == '2') {
            name = '/linkMan/my-responsible'
        }
        else if (sta == '3') {
            name = '/linkMan/my-care'
        }
        else if (sta == '10') {
            name = '/linkMan/my-create'
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
