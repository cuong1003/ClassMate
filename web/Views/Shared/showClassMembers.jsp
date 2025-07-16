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
        
        /* Members Section */
        .section {
            background: white;
            border-radius: 8px;
            box-shadow: 0 1px 3px rgba(60, 64, 67, 0.12);
            margin-bottom: 24px;
            overflow: hidden;
        }
        
        .section-header {
            padding: 20px 24px;
            border-bottom: 1px solid #e8eaed;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
        
        .section-title {
            font-size: 18px;
            font-weight: 500;
            color: #3c4043;
        }
        
        .member-count {
            font-size: 14px;
            color: #5f6368;
        }
        
        .teacher-section {
            padding: 24px;
        }
        
        .teacher-card {
            display: flex;
            align-items: center;
            gap: 16px;
        }
        
        .teacher-avatar {
            width: 48px;
            height: 48px;
            background: #1a73e8;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            font-weight: bold;
            font-size: 18px;
        }
        
        .teacher-info {
            flex: 1;
        }
        
        .teacher-name {
            font-size: 16px;
            font-weight: 500;
            color: #3c4043;
            margin-bottom: 4px;
        }
        
        .teacher-email {
            font-size: 14px;
            color: #5f6368;
        }
        
        .students-section {
            padding: 0;
        }
        
        .student-item {
            padding: 16px 24px;
            border-bottom: 1px solid #f1f3f4;
            display: flex;
            align-items: center;
            gap: 16px;
        }
        
        .student-item:last-child {
            border-bottom: none;
        }
        
        .student-avatar {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            font-weight: bold;
            font-size: 14px;
        }
        
        .student-info {
            flex: 1;
        }
        
        .student-name {
            font-size: 14px;
            font-weight: 500;
            color: #3c4043;
            margin-bottom: 2px;
        }
        
        .student-email {
            font-size: 13px;
            color: #5f6368;
        }
        
        /* Color variations for avatars */
        .avatar-blue { background: #1a73e8; }
        .avatar-green { background: #34a853; }
        .avatar-orange { background: #ff7043; }
        .avatar-purple { background: #9c27b0; }
        .avatar-red { background: #f44336; }
        .avatar-teal { background: #009688; }
        .avatar-pink { background: #e91e63; }
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
        <a href="${pageContext.request.contextPath}/t/classcontroller?ccode=${ccode}" class="nav-tab">Bảng tin</a>
        <a href="${pageContext.request.contextPath}/t/assignmentlist?ccode=${ccode}" class="nav-tab">Bài tập trên lớp</a>
        <a href="#" class="nav-tab active">Mọi người</a>
    </div>
    
    <!-- Main Content -->
    <div class="main-content">
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
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

</body>
</html>
