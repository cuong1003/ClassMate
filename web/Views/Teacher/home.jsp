<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lớp học - ClassMate</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Google+Sans:wght@400;500;700&display=swap">
    <%-- Sửa lại đường dẫn đến file CSS --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Teacher/css/home.css">
</head>
<body>

    <!-- Header -->
    <header class="main-header">
        <h1 class="header-title">Lớp học</h1>
        <div class="header-right">
            <button id="open-add-class-modal" class="icon-button" title="Tạo hoặc tham gia lớp học">+</button>
        </div>
    </header>

    <!-- Class Grid -->
    <main class="class-grid-container">
        <c:forEach items="${danhsachlop}" var="classroom" varStatus="status">
            <%-- Define an array of colors --%>
            <c:set var="colors" value="${['#1e8e3e', '#d93025', '#1a73e8', '#f29900', '#8f2ead', '#007b83']}" />
            <c:set var="avatarColors" value="${['#187a32', '#c4281f', '#1668c9', '#e08c00', '#7d269c', '#006a70']}" />
            
            <div class="class-card">
                <a href="${pageContext.request.contextPath}/ClassController?id=${classroom.classId}" class="card-link-wrapper">
                    <div class="card-header" style="background-color: ${colors[status.index % fn:length(colors)]};">
                        <h2 class="card-title">${classroom.className}</h2>
                        <p class="card-subtitle">${classroom.classCode}</p>
                    </div>
                    <div class="card-body"></div>
                </a>
                <div class="card-avatar" style="background-color: ${avatarColors[status.index % fn:length(avatarColors)]};">
                    <%-- Display first letter of the class name --%>
                    ${fn:substring(classroom.className, 0, 1)}
                </div>
            </div>
        </c:forEach>
    </main>

    <!-- Add Class Modal -->
    <div id="add-class-modal" class="modal-overlay">
        <div class="modal-content">
            <span class="close-modal">&times;</span>
            <h3>Tạo lớp học mới</h3>
            <form action="${pageContext.request.contextPath}/Teacher/TeacherHome" method="post">
                <div class="form-group">
                    <label for="className">Tên lớp học:</label>
                    <input type="text" id="className" name="className" required>
                </div>
                <div class="form-group">
                    <label for="classCode">Mã lớp học:</label>
                    <input type="text" id="classCode" name="classCode"
                           pattern="[a-zA-Z0-9]+"
                           title="Chỉ được nhập chữ cái và số, không có khoảng trắng hoặc ký tự đặc biệt"
                           maxlength="20"
                           required>
                </div>
                <div class="form-actions">
                    <button type="button" class="cancel-btn">Hủy</button>
                    <button type="submit">Tạo lớp</button>
                </div>
            </form>
        </div>
    </div>

    <%-- Sửa lại đường dẫn đến file JavaScript --%>
    <script src="${pageContext.request.contextPath}/Views/Teacher/js/home.js"></script>
</body>
</html> 