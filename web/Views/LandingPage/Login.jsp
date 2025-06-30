<%-- 
    Document   : Login
    Created on : Jun 27, 2025, 11:35:16 AM
    Author     : fakey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login page</title>
    </head>
    <body>
        <h1>Login to ClassMate</h1>
        <form action="Login" method="post">
            Username: <input type="text" name="username" value="${autoFillUsername != null ? autoFillUsername : ''}" /><br>
            Password: <input type="password" name="password" /><br>
            <input type="hidden" name="role" value="${autoFillRole != null ? autoFillRole : param.role}" />
            <input type="submit" value="Login" />
        </form>
    </body>
</html>
