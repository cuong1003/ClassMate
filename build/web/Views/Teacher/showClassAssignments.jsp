<%-- 
    Document   : showClassAssignments
    Created on : Jul 10, 2025, 2:53:41 PM
    Author     : fakey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Danh sách bài tập cho lớp: ${ccode}</h1>

    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Tiêu đề</th>
                <th>Mô tả</th>
                <th>Hạn nộp</th>
            </tr>
        </thead>
        <tbody>
            <%-- Vòng lặp bắt đầu ở đây --%>
            <c:forEach items="${assignments}" var="item">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.title}</td>
                    <td>${item.description}</td>
                    <td>${item.deadline}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <%-- Kiểm tra nếu danh sách rỗng --%>
    <c:if test="${empty assignments}">
        <p>Lớp này chưa có bài tập nào được giao.</p>
    </c:if>
    </body>
</html>
