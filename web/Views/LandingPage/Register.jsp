<%-- 
    Document   : Register
    Created on : Jun 27, 2025, 11:35:25 AM
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
        <h1>Register</h1>
        <%-- Display error message if present --%>
        <% if (request.getAttribute("errorMessage") != null) { %>
            <p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
        <% } %>
        <form action="${pageContext.request.contextPath}/register" method="post">
            Username: <input type="text" name="username" /><br>
            Password: <input type="password" name="password" /><br>
            Fullname: <input type="text" name="fullname" /><br>
            Email: <input type="email" name="email" /><br>
            Loại tài khoản: <select name="role">
                <option value="1">Học sinh</option>
                <option value="0">Giáo viên</option>              
            </select>
            <br><input type="submit" value="Register" />
        </form>
    </body>
</html>
