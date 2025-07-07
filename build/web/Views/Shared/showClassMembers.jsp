<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách thành viên lớp</title>
    <style>
        body {
            font-family: "Segoe UI", sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 40px;
            text-align: center;
        }

        h2 {
            color: #333;
            margin-bottom: 10px;
        }

        .section-title {
            font-size: 20px;
            text-align: left;
            margin-bottom: 10px;
        }

        .teacher-box {
            padding: 15px;
            background-color: #ffffff;
            border: 1px solid #ddd;
            width: 60%;
            margin: 0 auto 30px;
            text-align: left;
            border-radius: 8px;
            box-shadow: 0 1px 3px rgba(0,0,0,0.05);
        }

        .student-table {
            width: 80%;
            margin: 0 auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }

        .student-table th, .student-table td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }

        .student-table th {
            background-color: #f0f0f0;
            color: #444;
        }

        .student-count {
            font-weight: normal;
            font-size: 14px;
            color: #666;
        }
    </style>
</head>
<body>

    <div class="section-title">Giáo viên</div>
    <div class="teacher-box">
        <strong>${teacherName}</strong>
    </div>

    <div class="section-title">
        Bạn học 
        <span class="student-count">(${fn:length(members)} sinh viên)</span>
    </div>

    <table class="student-table">
        <tr>
            <th>STT</th>
            <th>Họ tên</th>
            <th>Email</th>
        </tr>
        <c:forEach var="m" items="${members}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${names[status.index]}</td>
                <td>${emails[status.index]}</td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
