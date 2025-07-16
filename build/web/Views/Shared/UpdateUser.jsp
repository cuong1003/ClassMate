<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cập nhật thông tin</title>
</head>
<body>
    <h2>Cập nhật thông tin người dùng</h2>
    <form action="${pageContext.request.contextPath}/UserUpdateController" method="post">
        <input type="hidden" name="id" value="${user.userId}" />
        <p>Họ tên: <input type="text" name="fullname" value="${user.fullname}" required></p>
        <p>Email: <input type="email" name="email" value="${user.email}" required></p>
        <button type="submit">Lưu thay đổi</button>
    </form>
</body>
</html>