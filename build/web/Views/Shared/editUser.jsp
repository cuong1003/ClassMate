<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa thông tin</title>
</head>
<body>
    <h2>Chỉnh sửa thông tin cá nhân</h2>
    <form action="${pageContext.request.contextPath}/updatethongtin" method="post">
        <input type="hidden" name="id" value="${user.userId}" />

        <label for="fullname">Họ và tên:</label>
        <input type="text" id="fullname" name="fullname" value="${user.fullName}" required /><br><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${user.email}" required /><br><br>

        <button type="submit">Lưu thay đổi</button>
    </form>
    <a href="${pageContext.request.contextPath}/s/studenthome">← Quay lại</a>
</body>
</html>
