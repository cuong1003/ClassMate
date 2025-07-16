```jsp
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

        .student-table th.action-column, .student-table td.action-column {
            display: none; 
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

        .options-btn {
            background-color: #4a90e2;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 4px;
            cursor: pointer;
            margin-bottom: 10px;
        }

        .options-btn:hover {
            background-color: #357abd;
        }

        .delete-btn {
            background-color: #ff4d4d;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 4px;
            cursor: pointer;
        }

        .delete-btn:hover {
            background-color: #cc0000;
        }
    </style>
    <script>
        function toggleActionColumn() {
            var actionColumn = document.querySelectorAll('.action-column');
            var isVisible = actionColumn[0].style.display === 'table-cell';
            actionColumn.forEach(function(element) {
                element.style.display = isVisible ? 'none' : 'table-cell';
            });
            document.getElementById('optionsBtn').textContent = isVisible ? 'Tùy chọn' : 'Ẩn tùy chọn';
        }
    </script>
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

    <c:if test="${not empty error}">
        <div style="color: red; margin-bottom: 10px;">${error}</div>
    </c:if>

    <button id="optionsBtn" class="options-btn" onclick="toggleActionColumn()">Tùy chọn</button>

    <table class="student-table">
        <tr>
            <th>STT</th>
            <th>Họ tên</th>
            <th>Email</th>
            <th class="action-column">Hành động</th>
        </tr>
        <c:forEach var="m" items="${members}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${names[status.index]}</td>
                <td>${emails[status.index]}</td>
                <td class="action-column">
                    <form action="${pageContext.request.contextPath}/c/studentlist" method="post" style="display:inline;">
                        <input type="hidden" name="ccode" value="${ccode}">
                        <input type="hidden" name="userId" value="${m.user_id}">
                        <button type="submit" class="delete-btn" onclick="return confirm('Bạn có chắc muốn xóa học sinh này khỏi lớp?')">Xóa</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
