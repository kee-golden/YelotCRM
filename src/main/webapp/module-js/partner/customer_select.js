require(['jquery', 'yaya', 'datatables.net'], function ($, yaya) {

$("#dept").change(
    function(){

        $("#selecttree1").empty();
        var deptId=$("#dept").val();
        $.ajax({
            url:ctx+'/customer/dept-admin',
            data:{deptId:deptId},
            success:function(data){
                var s="";
                for(var i=0;i<data.data.length;i++){
                 var s1="<div class='coloritem' id='"+data.data[i].id+"'>"+data.data[i].nickname+"</div>";
                    s=s+s1;
                }
                $("#selecttree1").html(s);
                $("#selecttree1 .coloritem").click(function(){
                    txt=$(this).text();
                    var id=$(this).attr('id')
                    var sid=id+"-";
                    if( $("#"+sid).length==0){
                        var t="<div class='coloritem_right' id='"+id+"-'>"+txt+"<i class='fa fa-close facol'></i></div>"
                        $("#selecttree2").empty();
                        $("#selecttree2").append(t);
                    }
                    $("#"+sid).click(function(){
                        var  id2=$(this).attr('id')
                        $(this).remove();
                        $("#"+id).show();
                    })
                });
            }
        })
    }
);
});
