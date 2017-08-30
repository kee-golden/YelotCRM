<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--左侧导航开始-->
<nav class="navbar-default navbar-static-side" role="navigation" id="leftside" style="height: 100%;overflow-y: auto">
    <div class="nav-close"><i class="fa fa-times-circle"></i></div>
    <div class="sidebar-collapse">
        <ul class="nav" id="side-menu">


            <c:forEach items="${sessionScope.menus}" var="menu">
                <c:if test="${empty menu.children}">
                    <c:set var="url" value="/${menu.action}"/>
                </c:if>
                <c:if test="${!empty menu.children}">
                    <c:set var="url" value="#"/>

                </c:if>

                <li <c:if test="${PARENT_MENU_CODE eq menu.code}">class="active"</c:if>>
                    <a href="${ctx}${url}">
                            <%--<i class="glyphicon glyphicon-cog"></i>--%>
                        <i class="${menu.menuClass}"></i>
                        <span class="nav-label">${menu.name}</span>
                                <c:if test="${menu.action eq '#'}">
                                    <span class="fa arrow"></span>
                                </c:if>

                    </a>

                    <c:if test="${url eq '#'}">
                    <c:if test="${PARENT_MENU_CODE eq menu.code}">
                        <ul class="nav nav-second-level collapse in" aria-expanded="true">
                            <c:forEach items="${menu.children}" var="child">
                                <li <c:if test="${CHILD_MENU_CODE eq child.code}">class="active"</c:if>>
                                    <a class="J_menuItem"
                                       href="${ctx}/${child.action}">${child.name}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                    <c:if test="${PARENT_MENU_CODE != menu.code}">
                        <ul class="nav nav-second-level collapse" aria-expanded="false">
                            <c:forEach items="${menu.children}" var="child">
                                <li>
                                    <a class="J_menuItem"
                                       href="${ctx}/${child.action}">${child.name}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                    </c:if>
                </li>
            </c:forEach>
        </ul>
    </div>
</nav>
<!--左侧导航结束-->