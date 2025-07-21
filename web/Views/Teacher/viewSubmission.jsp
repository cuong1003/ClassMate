<%-- 
    Document   : viewSubmission
    Created on : Jul 21, 2025, 11:15:13 PM
    Author     : fakey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Xem bài nộp - ${ccode}</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Google+Sans:wght@400;500;700&display=swap">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Views/Teacher/css/showClassAssignments.css">
</head>
<body>
    <!-- Header - đồng bộ với home.jsp -->
    <header class="main-header">
        <div class="breadcrumb">
            <a href="${pageContext.request.contextPath}/t/teacherhome">Lớp học</a>
            <span class="breadcrumb-separator">></span>
            <a href="${pageContext.request.contextPath}/t/danhsachbaitap?ccode=${ccode}">Bài tập trên lớp</a>
            <span class="breadcrumb-separator">></span>
            <span class="breadcrumb-current">Xem bài nộp</span>
        </div>
    </header>
    
    <!-- Class Header -->
    <div class="class-header">
        <div class="class-title">Xem bài nộp</div>
        <div class="class-code">${ccode}</div>
    </div>
    
    <!-- Navigation Tabs -->
    <div class="nav-tabs">
        <a href="${pageContext.request.contextPath}/t/bangtin?ccode=${ccode}" class="nav-tab">Bảng tin</a>
        <a href="${pageContext.request.contextPath}/t/danhsachbaitap?ccode=${ccode}" class="nav-tab">Bài tập trên lớp</a>
        <a href="${pageContext.request.contextPath}/t/danhsachlop?ccode=${ccode}" class="nav-tab">Mọi người</a>
    </div>
    
    <!-- Main Content -->
    <div class="main-content">
        <c:choose>
            <c:when test="${not empty slist}">
                <div class="submissions-info">
                    <h2>Danh sách bài nộp của sinh viên</h2>
                    <p class="submission-count">Tổng số bài nộp: <strong>${slist.size()}</strong></p>
                </div>
                
                <div class="submissions-accordion">
                    <c:forEach items="${slist}" var="submission" varStatus="status">
                        <div class="accordion-item">
                            <!-- Accordion Header - Always Visible -->
                            <div class="accordion-header" data-accordion-index="${status.index}">
                                <div class="header-main">
                                    <div class="student-name">
                                        <span class="name-label">ID:</span>
                                        <strong>${submission.user_id}</strong>
                                    </div>
                                    <div class="submission-title">
                                        <span class="title-label">Bài làm:</span>
                                        <c:choose>
                                            <c:when test="${submission.submit_text.length() > 50}">
                                                ${submission.submit_text.substring(0, 50)}...
                                            </c:when>
                                            <c:otherwise>
                                                ${submission.submit_text}
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                                <div class="header-actions">
                                    <div class="quick-info">
                                        <div class="file-status">
                                            <c:if test="${not empty submission.fileUrl}">
                                                <a href="${submission.fileUrl}" target="_blank" class="file-link-small" onclick="event.stopPropagation();">
                                                    📎 Xem tệp
                                                </a>
                                            </c:if>
                                            <c:if test="${empty submission.fileUrl}">
                                                <span class="no-file">Không có tệp</span>
                                            </c:if>
                                        </div>
                                        <div class="grade-status">
                                            <c:choose>
                                                <c:when test="${submission.grade == -1}">
                                                    <span class="ungraded">Chưa chấm</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="grade">${submission.grade}/10</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="feedback-status">
                                            <c:if test="${not empty submission.feedback}">
                                                <span class="has-feedback">Có phản hồi</span>
                                            </c:if>
                                            <c:if test="${empty submission.feedback}">
                                                <span class="no-feedback">Chưa phản hồi</span>
                                            </c:if>
                                        </div>
                                        <button class="grade-btn-small" onclick="event.stopPropagation(); gradeSubmission(${submission.id})">
                                            <c:choose>
                                                <c:when test="${submission.grade == -1}">Chấm điểm</c:when>
                                                <c:otherwise>Sửa điểm</c:otherwise>
                                            </c:choose>
                                        </button>
                                    </div>
                                    <div class="accordion-toggle">
                                        <span class="toggle-icon">▼</span>
                                    </div>
                                </div>
                            </div>
                            
                            <!-- Accordion Content - Expandable -->
                            <div class="accordion-content" id="accordion-${status.index}">
                                <div class="content-grid">
                                    <div class="detail-item">
                                        <label>ID Submission:</label>
                                        <span>${submission.id}</span>
                                    </div>
                                    <div class="detail-item">
                                        <label>User ID:</label>
                                        <span>${submission.user_id}</span>
                                    </div>
                                    <div class="detail-item">
                                        <label>Tên sinh viên:</label>
                                        <span>${submission.user_id}</span>
                                    </div>
                                    <div class="detail-item">
                                        <label>Thời gian nộp:</label>
                                        <span>
                                            <fmt:formatDate value="${submission.submmitted_at}" 
                                                            pattern="dd/MM/yyyy HH:mm:ss" />
                                        </span>
                                    </div>
                                    <div class="detail-item full-width">
                                        <label>Nội dung bài làm đầy đủ:</label>
                                        <div class="full-content">
                                            ${submission.submit_text}
                                        </div>
                                    </div>
                                    <div class="detail-item full-width">
                                        <label>Phản hồi của giáo viên:</label>
                                        <div class="feedback-content">
                                            <c:if test="${not empty submission.feedback}">
                                                ${submission.feedback}
                                            </c:if>
                                            <c:if test="${empty submission.feedback}">
                                                <span class="no-feedback-text">Chưa có phản hồi từ giáo viên</span>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="detail-actions">
                                        <button class="grade-btn-full" onclick="gradeSubmission(${submission.id})">
                                            <c:choose>
                                                <c:when test="${submission.grade == -1}">📝 Chấm điểm cho bài này</c:when>
                                                <c:otherwise>✏️ Sửa điểm (${submission.grade}/10)</c:otherwise>
                                            </c:choose>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
            <c:otherwise>
                <div class="no-data">
                    <div class="empty-icon">📝</div>
                    <h3>Chưa có bài nộp nào</h3>
                    <p>Chưa có sinh viên nào nộp bài tập này.</p>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
     
    <!-- JavaScript -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/Views/Teacher/js/showClassAssignment.js"></script>
    <script>
        function gradeSubmission(submissionId) {
            // TODO: Implement grading functionality
            alert('Chức năng chấm điểm sẽ được phát triển sau. Submission ID: ' + submissionId);
        }
        
        function toggleAccordion(header) {
            console.log('toggleAccordion called');
            
            // Get the accordion item (parent of header)
            const accordionItem = header.closest('.accordion-item');
            console.log('Accordion item found:', accordionItem);
            
            if (!accordionItem) {
                console.error('No accordion item found');
                return;
            }
            
            // Toggle active class on the accordion item
            const isActive = accordionItem.classList.contains('active');
            console.log('Current active state:', isActive);
            
            // Close all other accordions first
            const allItems = document.querySelectorAll('.accordion-item');
            allItems.forEach(item => {
                item.classList.remove('active');
            });
            
            // If this accordion wasn't active, open it
            if (!isActive) {
                accordionItem.classList.add('active');
                console.log('Accordion opened');
            } else {
                console.log('Accordion closed');
            }
        }
        
        // Initialize accordion states
        document.addEventListener('DOMContentLoaded', function() {
            // All accordions start closed
            const allItems = document.querySelectorAll('.accordion-item');
            console.log('Found accordion items:', allItems.length);
            
            allItems.forEach((item, index) => {
                item.classList.remove('active');
                console.log('Initialized accordion item', index);
            });
            
            // Add click event listeners to accordion headers
            const headers = document.querySelectorAll('.accordion-header');
            console.log('Found accordion headers:', headers.length);
            
            headers.forEach((header, index) => {
                console.log(`Adding click listener to header ${index}`);
                header.addEventListener('click', function(e) {
                    console.log('Header clicked:', index);
                    toggleAccordion(header);
                });
            });
            
            // Check all accordion content elements
            const contents = document.querySelectorAll('.accordion-content');
            console.log('Found accordion contents:', contents.length);
            contents.forEach((content, index) => {
                console.log(`Content ${index} ID:`, content.id);
            });
        });
    </script>
</body>
</html>
