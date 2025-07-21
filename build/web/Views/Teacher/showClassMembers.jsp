<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Mọi người - ${ccode}</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Google+Sans:wght@400;500;700&display=swap">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Teacher/css/showClassMembers.css">
    <script>
        // Truyền contextPath cho JavaScript
        var contextPath = '${pageContext.request.contextPath}';
    </script>
</head>
<body>
    <!-- Header - đồng bộ với home.jsp -->
    <header class="main-header">
        <div class="breadcrumb">
            <a href="${pageContext.request.contextPath}/t/teacherhome">Lớp học</a>
            <span class="breadcrumb-separator">></span>
            <span class="breadcrumb-current">${ccode}</span>
        </div>
    </header>
    
    <!-- Class Header -->
    <div class="class-header">
        <div class="class-title">${ccode}</div>
        <div class="class-code">SE1949</div>
    </div>
    
    <!-- Navigation Tabs -->
    <div class="nav-tabs">
        <a href="${pageContext.request.contextPath}/t/bangtin?ccode=${ccode}" class="nav-tab">Bảng tin</a>
        <a href="${pageContext.request.contextPath}/t/danhsachbaitap?ccode=${ccode}" class="nav-tab">Bài tập trên lớp</a>
        <a href="#" class="nav-tab active">Mọi người</a>
    </div>
    
    <!-- Main Content -->
    <div class="main-content">
        <!-- Error/Success Messages -->
        <c:if test="${not empty error}">
            <div class="message error-message">
                ${error}
            </div>
        </c:if>
        
        <!-- Teacher Section -->
        <div class="section">
            <div class="section-header">
                <div class="section-title">Giáo viên</div>
            </div>
            <div class="teacher-section">
                <div class="teacher-card">
                    <div class="teacher-avatar">
                        ${fn:substring(teacherName, 0, 1)}
                    </div>
                    <div class="teacher-info">
                        <div class="teacher-name">${teacherName}</div>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Students Section -->
        <div class="section">
            <div class="section-header">
                <div class="section-title">Bạn học</div>
                <div class="member-count">${fn:length(members)} sinh viên</div>
            </div>
            <div class="students-section">
                <c:forEach var="m" items="${members}" varStatus="status">
                    <c:set var="avatarColors" value="blue,green,orange,purple,red,teal,pink" />
                    <c:set var="colorArray" value="${fn:split(avatarColors, ',')}" />
                    <c:set var="colorIndex" value="${status.index % 7}" />
                    <c:set var="avatarColor" value="${colorArray[colorIndex]}" />
                    
                    <div class="student-item">
                        <div class="student-avatar avatar-${avatarColor}">
                            ${fn:substring(names[status.index], 0, 1)}
                        </div>
                        <div class="student-info">
                            <div class="student-name">${names[status.index]}</div>
                            <div class="student-email">${emails[status.index]}</div>
                        </div>
                        <button class="delete-btn" onclick="confirmDelete('${names[status.index]}', '${userIds[status.index]}', '${ccode}')">Xóa</button>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/Views/Teacher/js/showClassMembers.js"></script>
</body>
</html>
