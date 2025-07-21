
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Quản lý lớp học - ${ccode}</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Google+Sans:wght@400;500;700&display=swap">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Teacher/css/classManagement.css">
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
        <a href="#" class="nav-tab active">Bảng tin</a>
        <a href="${pageContext.request.contextPath}/t/danhsachbaitap?ccode=${ccode}" class="nav-tab">Bài tập trên lớp</a>
        <a href="${pageContext.request.contextPath}/t/danhsachlop?ccode=${ccode}" class="nav-tab">Mọi người</a>
    </div>
    
    <!-- Main Content -->
    <div class="main-content">
        <!-- Teacher Info -->
        <c:if test="${not empty teacher}">
            <div class="teacher-info">
                <div class="teacher-avatar">
                    ${fn:substring(teacher, 0, 1)}
                </div>
                <div class="teacher-name">${teacher}</div>
            </div>
        </c:if>
        
        <!-- Create Announcement Button -->
        <div class="create-announcement-section">
            <button class="create-btn">+ Tạo thông báo mới</button>
        </div>
        
        <!-- Announcements Cards -->
        <div class="announcements-section">
            <c:choose>
                <c:when test="${not empty announcementList}">
                    <c:forEach items="${announcementList}" var="announcement">
                        <div class="announcement-card">
                            <!-- Header: Avatar + Tên giáo viên + Thời gian -->
                            <div class="announcement-header">
                                <div class="announcement-avatar">
                                    ${fn:substring(teacher, 0, 1)}
                                </div>
                                <div class="announcement-meta">
                                    <div class="announcement-author">${teacher}</div>
                                    <div class="announcement-time">
                                        <fmt:formatDate value="${announcement.createdAt}" 
                                                        pattern="dd 'thg' M" />
                                    </div>
                                </div>
                                <div style="color: #5f6368; cursor: pointer;">⋮</div>
                            </div>
                            
                            <!-- Nội dung: Tiêu đề + Mô tả -->
                            <div class="announcement-content">
                                <div class="announcement-title">
                                    ${announcement.title}
                                </div>
                                <div class="announcement-description">
                                    ${announcement.description}
                                </div>
                            </div>
                            
                            <!-- Actions: Nhận xét -->
                            <div class="announcement-actions">
                                <div class="comments-section">
                                    <span>👥</span>
                                    <span>Nhận xét về lớp học</span>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="empty-state">
                        <div class="empty-state-icon">📢</div>
                        <div class="empty-state-title">Thông báo nội dung nào đó cho lớp học của bạn</div>
                        <div class="empty-state-description">Chia sẻ tài nguyên, đưa ra thông báo hoặc tạo cuộc thảo luận</div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    
    <script src="${pageContext.request.contextPath}/Views/Teacher/js/classManagerment.js"></script>
</body>
</html>
