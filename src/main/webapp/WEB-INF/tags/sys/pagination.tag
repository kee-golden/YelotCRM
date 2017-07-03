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

<%--总记录数--%>
<%@ attribute name="records" type="java.lang.Integer" required="true" description="值" %>

<%@ attribute name="baseUrl" type="java.lang.String" required="true" description="值" %>

<%--当前页数--%>
<%@ attribute name="currentPage" type="java.lang.Integer" required="true" description="值" %>

<%--当前页面行数--%>
<%@ attribute name="pageSize" type="java.lang.Integer" required="true" description="值" %>

<%--暂时未使用到--%>
<%@ attribute name="paginationSize" type="java.lang.Integer" description="值" %>


<%-- <div class="page">
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
    int total;
    boolean hasParameter = false;
    if (records > 0 && pageSize > 0) {
        if ((records % pageSize) > 0) {
            total = (records / pageSize) + 1;
        } else {
            total = (records / pageSize);
        }
        StringBuilder ret = new StringBuilder();
        ret.append("<div class=\"page\">");
        if (baseUrl.indexOf("?") > 0) {
            hasParameter = true;
        }
        if (1 == currentPage) {
            ret.append("<span class=\"disabled_page\">首页</span>");
        } else {
            if (hasParameter) {
                ret.append("<a href=\"").append(baseUrl).append("&page=1&size=").append(pageSize).append("\">首页</span>");
            } else {
                ret.append("<a href=\"").append(baseUrl).append("?page=1&size=").append(pageSize).append("\">首页</span>");
            }
        }


        int begin = 1, end = currentPage + 2;
        if (currentPage > 3) {
            begin = currentPage - 2;
        }
        if (end > total) {
            end = total;
        }
        if (end < 5 && total >= 5) {
            end = 5;
        }

        if (end > 5 && total < currentPage + 2) {
            begin = total - 4;
        }

        for (; begin <= end; begin++) {
            if (begin == currentPage) {
                /*  <a href="javascript:void(0)" class="active">1</a>*/
                ret.append("<a href=\"#\" class=\"active\">").append(begin).append("</a>");
            } else {
                /*                    <a href="/wenda/0/1/6">6</a> */
                if (hasParameter) {
                    ret.append("<a href=\"").append(baseUrl).append("&page=").append(begin).append("&size=").append(pageSize).append("\">").append(begin).append("</a>");
                } else {
                    ret.append("<a href=\"").append(baseUrl).append("?page=").append(begin).append("&size=").append(pageSize).append("\">").append(begin).append("</a>");

                }
            }
        }
//        ret.append("<a href=\"").append(baseUrl).append("?page=").append(begin + 1).append("&size=").append(pageSize).append("\">下一页</a>");
        if (total == currentPage) {
            ret.append("<span class=\"disabled_page\">尾页</span>");
        } else {
            if (hasParameter) {
                ret.append("<a href=\"").append(baseUrl).append("&page=").append(total).append("&size=").append(pageSize).append("\">尾页</a>");
            } else {
                ret.append("<a href=\"").append(baseUrl).append("?page=").append(total).append("&size=").append(pageSize).append("\">尾页</a>");
            }
        }
        ret.append("</div>");
        out.print(ret.toString());
    }

%>