<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>GOD</title>
</head>
<body>

<hr>
<form action="/wx/account-bind">
    <input type="hidden" name="openid" value="${openid}"/>
<label>Phone:</label><input type="text" name="phone" id="phone"><br>
    <label>Verify Code:</label><input type="text" name="verfiyCode" id="verifyCode"/><br/>
    <input type="submit">

</form>

</body>
</html>