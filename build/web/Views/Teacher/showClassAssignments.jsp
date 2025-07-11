<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Bài tập trên lớp - ${ccode}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Teacher/css/showClassAssignments.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>
<body>
    <%-- Sử dụng cấu trúc header giống hệt home.jsp --%>
    <header class="main-header">
        <h1 class="header-title">
             <a href="${pageContext.request.contextPath}/t/teacherhome">Lớp học</a> &gt; <span>${ccode}</span>
        </h1>
    </header>

    <div class="main-container">
        <nav class="class-nav">
            <a href="${pageContext.request.contextPath}/t/classcontroller?ccode=${ccode}">Bảng tin</a>
            <a href="#" class="active">Bài tập trên lớp</a>
            <a href="${pageContext.request.contextPath}/c/studentlist?ccode=${ccode}">Mọi người</a>
            <a href="#">Điểm</a>
        </nav>

        <main class="content-area">
            <div class="create-button-container">
                <a href="#" class="create-button">
                    <span class="material-icons">add</span>Tạo
                </a>
            </div>

            <div class="assignments-list">
                <%-- Kiểm tra nếu danh sách rỗng để hiển thị thông báo --%>
                <c:if test="${empty assignments}">
                    <div class="empty-state">
                        <img src="https://ssl.gstatic.com/classroom/empty_states_classwork.svg" alt="Không có bài tập">
                        <h2>Đây là nơi giao bài tập</h2>
                        <p>Bạn có thể thêm bài tập và các công việc khác cho lớp rồi sắp xếp thành các chủ đề.</p>
                    </div>
                </c:if>

                <%-- Lặp qua danh sách bài tập nếu không rỗng --%>
                <c:if test="${not empty assignments}">
                    <c:forEach items="${assignments}" var="item">
                        <div class="assignment-item">
                            <div class="assignment-icon">
                                <span class="material-icons">assignment</span>
                            </div>
                            <div class="assignment-details">
                                <a href="#" class="assignment-title">${item.title}</a>
                                <p class="assignment-deadline">Hạn nộp: ${item.deadLine}</p>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </main>
    </div>
</body>
</html>
