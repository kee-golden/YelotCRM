<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<div class="ibox float-e-margins">
    <div class="ibox-content">
        <div class="row">
            <form id="J_brandForm" role="form">
                <input type="hidden" name="id" id="id" value="${bean.id}">
                <div class="col-xs-12">
                    <div class="form-group">
                        <label>品牌名称</label>
                        <input type="text" placeholder="请输入品牌名称" name="name" id="name" class="form-control"
                               value="${bean.name}">
                    </div>
                    <div class="form-group">
                        <label>品牌首字母</label>
                        <input type="text" placeholder="请输入品牌首字母" name="firstLetter" id="firstLetter" class="form-control"
                               value="${bean.firstLetter}">
                    </div>
                    <div class="form-group">
                        <label>中文含义</label>
                        <input type="text" placeholder="请输入中文含义" name="chinese" id="chinese" class="form-control"
                               value="${bean.chinese}">
                    </div>
                </div>
            </form>

        </div>
    </div>
</div>