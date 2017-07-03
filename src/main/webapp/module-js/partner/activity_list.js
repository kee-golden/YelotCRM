/**
 * Created by lenovo on 2017/4/19.
 */
require(['jquery', 'yaya', 'datatables.net'], function ($, yaya) {

    $(function () {
        $.ajax({
            url: ctx + '/activity/all-activity',
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
        if (sta == '1') {
            name = '/activity/all-activity'

        } else if (sta == '2') {
            name = '/activity/my-responsible'
        }
        else if (sta == '3') {
            name = '/activity/my-care'
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
