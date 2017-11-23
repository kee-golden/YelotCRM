<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<div class="ibox float-e-margins">
    <div class="ibox-content">
        <div class="row">
            <form id="J_shopForm" role="form">
                <input type="hidden" name="id" id="id" value="${bean.id}">
                <div class="col-xs-12">
                    <div class="form-group">
                        <label>门店名称</label>
                        <input type="text" placeholder="请输入门店名称" name="name" id="name" class="form-control"
                               value="${bean.name}">
                    </div>
                    <div class="form-group">
                        <label>省市</label>
                        <div id="prov_city">
                            <select class="prov" name="province"></select>
                            <select class="city" disabled="disabled" name="city"></select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>门店地址</label>
                        <input type="text" placeholder="请输入门店地址" name="address" id="address" class="form-control"
                               value="${bean.address}">
                    </div>
                    <div class="form-group">
                        <label>联系方式</label>
                        <input type="text" placeholder="请输入联系方式" name="phone" id="phone" class="form-control"
                               value="${bean.phone}">
                    </div>
                </div>
            </form>

        </div>
    </div>
</div>
<script type="text/javascript" src="/static/cityselect/js/jquery.cityselect.js"></script>
<script type="text/javascript">
    require(['jquery', 'yaya', 'datatables.net','dateTimePicker'], function ($, yaya) {


        var province = '${bean.province}';
        var city = '${bean.city}';
        console.log(province+","+city);
        if(province != '' || city != ''){
            $("#prov_city").citySelect({
                url:"/static/cityselect/js/city.min.js",
                prov:province,
                city:city,
                nodata:"none",
                required:false
            });
        }else {
            console.log("default  province")
            $("#prov_city").citySelect({
                url:"/static/cityselect/js/city.min.js",
                prov:"上海",
                city:"黄浦区",
                nodata:"none",
                required:false
            });

        }

    });

</script>
