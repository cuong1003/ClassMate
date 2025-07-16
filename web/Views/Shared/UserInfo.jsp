<%-- 
    Document   : UserInfo
    Created on : Jul 15, 2025, 7:19:02 PM
    Author     : dinhh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Thông tin người dùng</h2>
        <p>ID: ${user.userId}</p>
        <p>Tên: ${user.fullname}</p>
        <p>Email: ${user.email}</p>
        <p>Role: ${user.role == 0 ? "Giáo Viên" : "Học Sinh"}</p>
        <form action="${pageContext.request.contextPath}/UserUpdateController" method="get">
            <input type="hidden" name="id" value="${user.userId}">
            <button type="submit">Cập nhật thông tin</button>
        </form>
    </body>
</html>
