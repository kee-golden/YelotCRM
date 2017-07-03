<%--
  Created by IntelliJ IDEA.
  User: kee
  Date: 16/11/24
  Time: PM10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/common/taglibs.jsp" %>
<html>
<head>
    <title>用户注册</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Mobile support -->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <%@include file="/WEB-INF/common/static.jsp" %>

    <script>
        var ctx = '${ctx}';
    </script>
</head>
<body>

<div class="middle-box text-center loginscreen  animated fadeInDown">
    <div>
        <div>

            <h2 class="logo-name">ITSS</h2>

        </div>
        <h3>用户注册</h3>

        <form class="m-t" role="form" action="${ctx}/admin/register" method="post">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="请输入用户手机号" name="phone" id="phone" required="" autofocus="autofocus">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="短信验证码" name="verify" id="verify" required="" >
                <span class="timer">重新获取</span>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="您的名字" autocomplete="off" name="nickname" id="nickname" required="" >
            </div>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="组织名称" autocomplete="off" name="groupName" id="group" required="" >
            </div>

            <div class="form-group">
                <input type="password" class="form-control" placeholder="密码（字母，数字，至少6位）" autocomplete="off" name="password" id="password"
                       required="">
            </div>
            <button type="submit" class="btn btn-primary block full-width m-b">注册</button>
            <c:if test="${not empty errorMessage}">
                <H3 class="text-danger">${errorMessage}</H3>
            </c:if>

        </form>
    </div>
</div>

</body>
</html>
