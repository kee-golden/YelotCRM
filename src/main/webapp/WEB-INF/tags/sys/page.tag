<%@ tag pageEncoding="UTF-8" %>
<%--
   用来处理分页显示
   其中需要在页面中定义一个 jump 函数来处理跳转请求
 1、在jsp页面中引入taglib
    <%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
  2、 在需要翻译系统代码列表的地方使用如下代码：
    <sys:pagination currentPage="1" pageSize="10" records=""100/>
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="baseUrl" type="java.lang.String" required="true" description="值" %>

<%--当前页数--%>
<%@ attribute name="currentPage" type="java.lang.Integer" required="true" description="值" %>

<%--当前页面行数--%>
<%@ attribute name="pageSize" type="java.lang.Integer" required="true" description="值" %>

<%--总记录数--%>
<%@ attribute name="totalPages" type="java.lang.Integer" required="true" description="值" %>


<%--暂时未使用到--%>
<%@ attribute name="paginationSize" type="java.lang.Integer" description="值" %>


<%--查询附加条件--%>
<%@ attribute name="queryParam" type="java.lang.String" description="值" %>


<%--
                <div class="page">
                    <span class="disabled_page">首页</span>
                    <span class="disabled_page">上一页</span>
                    <a href="javascript:void(0)" class="active">1</a>
                    <a href="/wenda/0/1/2">2</a>
                    <a href="/wenda/0/1/3">3</a>
                    <a href="/wenda/0/1/4">4</a>
                    <a href="/wenda/0/1/5">5</a>
                    <a href="/wenda/0/1/6">6</a>
                    <a href="/wenda/0/1/7">7</a>
                    <a href="/wenda/0/1/2">下一页</a>
                    <a href="/wenda/0/1/2770">尾页</a>
                </div>
                --%>

<%
    boolean hasParameter = false;


    if (totalPages > 0 && pageSize > 0) {

        StringBuilder ret = new StringBuilder();
        ret.append("<div class=\"page\">");
        if (baseUrl.indexOf("?") > 0) {
            hasParameter = true;
        }
        if (0 == currentPage) {
            ret.append("<span class=\"disabled_page\">首页</span>");
        } else {
            if (hasParameter) {
                ret.append("<a href=\"").append(baseUrl).append("&page=1&size=").append(pageSize).append("\">首页</span>");
            } else {
                ret.append("<a href=\"").append(baseUrl).append("?page=1&size=").append(pageSize).append("\">首页</span>");
            }
        }


        int begin = currentPage - 2, end = currentPage + 2;

        if (begin < 0) {
            begin = 0;
            end = begin + 4;
        }

        if (end > totalPages - 1) {
            end = totalPages - 1;
            begin = end - 4;
            if (begin < 0) {
                begin = 0;
            }
        }


        for (; begin <= end; begin++) {
            if (begin == currentPage) {

                ret.append("<a href=\"#\" class=\"active\">").append(begin + 1).append("</a>");
            } else {
                /*                    <a href="/wenda/0/1/6">6</a> */
                if (hasParameter) {
                    ret.append("<a href=\"").append(baseUrl).append("&page=").append(begin + 1).append("&size=").append(pageSize).append("\">").append(begin + 1).append("</a>");
                } else {
                    ret.append("<a href=\"").append(baseUrl).append("?page=").append(begin + 1).append("&size=").append(pageSize).append("\">").append(begin + 1).append("</a>");

                }
            }
        }
//        ret.append("<a href=\"").append(baseUrl).append("?page=").append(begin + 1).append("&size=").append(pageSize).append("\">下一页</a>");
        if (totalPages == currentPage + 1) {
            ret.append("<span class=\"disabled_page\">尾页</span>");
        } else {
            if (hasParameter) {
                ret.append("<a href=\"").append(baseUrl).append("&page=").append(totalPages).append("&size=").append(pageSize).append("\">尾页</a>");
            } else {
                ret.append("<a href=\"").append(baseUrl).append("?page=").append(totalPages).append("&size=").append(pageSize).append("\">尾页</a>");
            }
        }
        ret.append("<div  class=\"total_pages\">共").append(totalPages).append("页</div>");

        ret.append("</div>");
        out.print(ret.toString());
    }

%>