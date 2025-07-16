<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Bảng tin lớp học</title>
    <style>
        body { font-family: Arial; margin: 20px; }
        .post-form { margin-bottom: 30px; }
        .post-box { border: 1px solid #ddd; padding: 15px; border-radius: 8px; margin-bottom: 20px; }
        .post-author { font-weight: bold; }
        .post-content { margin-top: 10px; }
        textarea { width: 100%; height: 80px; }
        button { padding: 8px 16px; background: #1976d2; color: white; border: none; border-radius: 4px; }
        .post-time { font-size: 12px; color: gray; }
    </style>
</head>
<body>
    <a href="${pageContext.request.contextPath}/c/studentlist?ccode=${ccode}">Thành viên</a>

    <a href="${pageContext.request.contextPath}/t/assignmentlist?ccode=${ccode}">Bài tập trên lớp</a>
</body>
</html>
