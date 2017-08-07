/**
 * Created by kee on 17/7/13.
 */

    require(['jquery','yaya','echarts','bootstrap'], function ($, yaya,echarts,DrawEChart) {

        var span=document.getElementById("myTab").querySelectorAll("span")
        for(var i=0;i<span.length;i++){
            console.log(span[i].innerHTML)
            if(span[i].innerHTML==0){
                span[i].style.display="none";
            }
        }
        /* 菜单下拉功能*/
        $("h6 i").click(
            function(){
                var div=( $(this).parent().parent().find(".row"));
                div.slideToggle();
            }
        );

        /* 图标功能*/
        $(function(){
            $.ajax({
                url:ctx+'/example1',
                success:function(data){

                    $('#content').html(data);
                }
            });
        });

        $('#myTab a:first').tab('show');//初始化显示哪个tab

        $('#myTab a').click(function (e) {
            e.preventDefault();//阻止a链接的跳转行为
            //var val= $(e).parent().attr('');
            var sta=$(this).attr('sta');
            var name='';
            if(sta=='1'){
                name='/example1';
            }else if(sta=='2'){
                name='/example2';
            }else if(sta=='3'){
                name='/example3';
            }else if(sta=='4'){
                name='/change/change-approvaleds';
            }
            $.ajax({
                url:ctx+name,
                success:function(data){
                    $('#content').html(data);
                }
            });
            $(this).parent().parent().children().removeClass();
            $(this).parent().addClass("active");
        });

        /*表格插件*/






    });

function table_PendingCount() {
    $.ajax({
        url:ctx+'/indexshow/index-PendingCount',
        async:false,
        success:function(result){
            var s=$("#countAccepted");
            s.html(result);
        }
    });
    var span=$("#countAccepted");
    var val=$(span).html();
    if(val!=0){
        $(span).show();
    }else if (val==0){
        $(span).hide();
    }
}

function table_AuditCount() {
    $.ajax({
        url: ctx + '/indexshow/index-AuditCount',
        async:false,
        success: function (result) {
            var s = $("#count");
            s.html(result);
        }
    });
    var span=$("#count");
    var val=$(span).html();
    if (val != 0) {
        $(span).show();
    }else if (val==0){
        $(span).hide();
    }
}
function table_RequestCount() {
    $.ajax({
        url: ctx + '/indexshow/index-RequestCount',
        async:false,
        success: function (result) {
            var s = $("#countRequest");
            s.html(result);
        }
    });
    var span=$("#countRequest");
    var val=$(span).html();
    if(val!=0){
        $(span).show();
    }else if (val==0){
        $(span).hide();
    }
}

function table_total() {
    $.ajax({
        url: ctx + '/indexshow/index-total',
        async:false,
        success: function (result) {
            var s = $("#total");
            s.html(result);
        }
    });
    var span=$("#total");
    var val=$(span).html();
    if(val!=0){
        $(span).show();
    }else if (val==0){
        $(span).hide();
    }
}

