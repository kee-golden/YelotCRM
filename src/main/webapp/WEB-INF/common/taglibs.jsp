<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>


<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:if test="${ctx == '/'}">
    <c:set var="ctx" value=""/>
</c:if>
<c:set var="qiniuHost" value="http://o8qpatbkm.bkt.clouddn.com/"/>

<%
    response.setHeader("Pragma", "No-cache");        //HTTP     1.1
    response.setHeader("Cache-Control", "no-cache");//HTTP     1.0
    response.setHeader("Expires", "0");               //防止被缓存
%>

