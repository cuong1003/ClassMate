
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
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Google Sans', 'Roboto', Arial, sans-serif;
            background-color: #f8f9fa;
            color: #3c4043;
        }
        
        /* Header/Breadcrumb - đồng bộ với home.jsp */
        .main-header {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 8px 24px;
            background-color: #ffffff;
            border-bottom: 1px solid #dadce0;
        }
        
        .header-title {
            font-size: 22px;
            color: #5f6368;
            margin: 0;
        }
        
        .breadcrumb {
            display: flex;
            align-items: center;
            gap: 8px;
            font-size: 22px;
        }
        
        .breadcrumb a {
            color: #5f6368;
            text-decoration: none;
            font-weight: 400;
        }
        
        .breadcrumb a:hover {
            color: #1a73e8;
        }
        
        .breadcrumb-separator {
            color: #5f6368;
            margin: 0 4px;
        }
        
        .breadcrumb-current {
            color: #5f6368;
            font-weight: 400;
        }
        
        /* Header Section */
        .class-header {
            background: linear-gradient(135deg, #ff7043 0%, #ff5722 100%);
            position: relative;
            overflow: hidden;
            padding: 40px 24px;
            color: white;
        }
        
        .class-header::after {
            content: '';
            position: absolute;
            right: 0;
            top: 0;
            width: 400px;
            height: 100%;
            background: url('data:image/svg+xml,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 400 200"><circle cx="350" cy="50" r="40" fill="rgba(255,255,255,0.1)"/><circle cx="300" cy="120" r="20" fill="rgba(255,255,255,0.1)"/><rect x="320" y="80" width="60" height="40" rx="8" fill="rgba(255,255,255,0.1)"/></svg>') no-repeat;
            background-size: contain;
        }
        
        .class-title {
            font-size: 36px;
            font-weight: 400;
            margin-bottom: 8px;
            position: relative;
            z-index: 1;
        }
        
        .class-code {
            font-size: 16px;
            opacity: 0.9;
            position: relative;
            z-index: 1;
        }
        
        /* Navigation Tabs */
        .nav-tabs {
            background-color: white;
            border-bottom: 1px solid #e8eaed;
            display: flex;
            padding: 0 24px;
        }
        
        .nav-tab {
            padding: 16px 24px;
            text-decoration: none;
            color: #5f6368;
            font-weight: 500;
            border-bottom: 3px solid transparent;
            transition: all 0.2s ease;
        }
        
        .nav-tab:hover {
            color: #1a73e8;
            background-color: rgba(26, 115, 232, 0.04);
        }
        
        .nav-tab.active {
            color: #1a73e8;
            border-bottom-color: #1a73e8;
        }
        
        /* Main Content */
        .main-content {
            max-width: 1200px;
            margin: 0 auto;
            padding: 24px;
        }
        
        /* Teacher Info Section */
        .teacher-info {
            background: white;
            padding: 16px 24px;
            margin-bottom: 24px;
            border-radius: 8px;
            box-shadow: 0 1px 3px rgba(60, 64, 67, 0.12);
            display: flex;
            align-items: center;
            gap: 12px;
        }
        
        .teacher-avatar {
            width: 40px;
            height: 40px;
            background: #1a73e8;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            font-weight: bold;
            font-size: 16px;
        }
        
        .teacher-name {
            font-size: 16px;
            font-weight: 500;
            color: #3c4043;
        }
        
        /* Create Button */
        .create-announcement-section {
            margin-bottom: 24px;
            text-align: center;
        }
        
        .create-btn {
            background: linear-gradient(135deg, #4CAF50, #45a049);
            color: white;
            border: none;
            border-radius: 50px;
            padding: 12px 24px;
            font-size: 14px;
            font-weight: 500;
            cursor: pointer;
            box-shadow: 0 2px 8px rgba(76, 175, 80, 0.3);
            transition: all 0.3s ease;
        }
        
        .create-btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 12px rgba(76, 175, 80, 0.4);
        }
        
        /* Announcements Cards */
        .announcements-section {
            display: flex;
            flex-direction: column;
            gap: 16px;
        }
        
        .announcement-card {
            background: white;
            border-radius: 8px;
            box-shadow: 0 1px 3px rgba(60, 64, 67, 0.12);
            border: 1px solid #e8eaed;
            overflow: hidden;
            transition: box-shadow 0.2s ease;
        }
        
        .announcement-card:hover {
            box-shadow: 0 2px 8px rgba(60, 64, 67, 0.15);
        }
        
        .announcement-header {
            display: flex;
            align-items: center;
            padding: 20px 24px 16px;
            gap: 12px;
        }
        
        .announcement-avatar {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            font-weight: bold;
            font-size: 16px;
            flex-shrink: 0;
        }
        
        .announcement-meta {
            flex: 1;
        }
        
        .announcement-author {
            font-size: 14px;
            font-weight: 500;
            color: #3c4043;
            margin-bottom: 2px;
        }
        
        .announcement-time {
            font-size: 12px;
            color: #5f6368;
        }
        
        .announcement-content {
            padding: 0 24px 20px;
        }
        
        .announcement-title {
            font-size: 16px;
            font-weight: 500;
            color: #3c4043;
            margin-bottom: 8px;
            line-height: 1.4;
        }
        
        .announcement-description {
            font-size: 14px;
            color: #5f6368;
            line-height: 1.5;
            white-space: pre-wrap;
        }
        
        .announcement-actions {
            padding: 12px 24px;
            border-top: 1px solid #f1f3f4;
            background: #fafbfc;
        }
        
        .comments-section {
            display: flex;
            align-items: center;
            gap: 8px;
            color: #1a73e8;
            font-size: 14px;
            cursor: pointer;
        }
        
        .comments-section:hover {
            text-decoration: underline;
        }
        
        /* Avatar Colors */
        .avatar-color-1 { background: #1a73e8; }
        .avatar-color-2 { background: #9c27b0; }
        .avatar-color-3 { background: #ff7043; }
        .avatar-color-4 { background: #4caf50; }
        .avatar-color-5 { background: #ff9800; }
        .avatar-color-6 { background: #3f51b5; }
        .avatar-color-7 { background: #e91e63; }
        .avatar-color-8 { background: #009688; }
        
        /* Empty State */
        .empty-state {
            text-align: center;
            padding: 60px 24px;
            background: white;
            border-radius: 8px;
            box-shadow: 0 1px 3px rgba(60, 64, 67, 0.12);
        }
        
        .empty-state-icon {
            width: 80px;
            height: 80px;
            background: #f8f9fa;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto 16px;
            font-size: 32px;
            color: #5f6368;
        }
        
        .empty-state-title {
            font-size: 18px;
            font-weight: 500;
            color: #3c4043;
            margin-bottom: 8px;
        }
        
        .empty-state-description {
            font-size: 14px;
            color: #5f6368;
        }
    </style>
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
        <a href="${pageContext.request.contextPath}/t/assignmentlist?ccode=${ccode}" class="nav-tab">Bài tập trên lớp</a>
        <a href="${pageContext.request.contextPath}/c/studentlist?ccode=${ccode}" class="nav-tab">Mọi người</a>
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
    
    <script>
        // Function to generate consistent avatar color based on name
        function getAvatarColor(name) {
            const colors = [
                '#1a73e8', '#9c27b0', '#ff7043', '#4caf50', 
                '#ff9800', '#3f51b5', '#e91e63', '#009688'
            ];
            let hash = 0;
            for (let i = 0; i < name.length; i++) {
                hash = name.charCodeAt(i) + ((hash << 5) - hash);
            }
            return colors[Math.abs(hash) % colors.length];
        }
        
        // Apply avatar colors after page load
        document.addEventListener('DOMContentLoaded', function() {
            const avatars = document.querySelectorAll('.announcement-avatar');
            avatars.forEach(function(avatar) {
                const teacherName = avatar.parentElement.querySelector('.announcement-author').textContent;
                avatar.style.backgroundColor = getAvatarColor(teacherName);
            });
        });
        
        // Add click handler for create announcement button
        document.addEventListener('DOMContentLoaded', function() {
            const createBtn = document.querySelector('.create-btn');
            if (createBtn) {
                createBtn.addEventListener('click', function() {
                    // Placeholder for create announcement functionality
                    alert('Chức năng tạo thông báo sẽ được phát triển trong tương lai!');
                });
            }
        });
    </script>
</body>
</html>
