<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Nộp bài tập - ${assignment.title}</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Google+Sans:wght@400;500;700&display=swap">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Views/Student/css/showClassAssignments.css">
</head>
<body>
    <!-- Header - đồng bộ với home.jsp -->
    <header class="main-header">
        <div class="breadcrumb">
            <a href="${pageContext.request.contextPath}/s/studenthome">Lớp học</a>
            <span class="breadcrumb-separator">></span>
            <a href="${pageContext.request.contextPath}/s/danhsachbaitap?ccode=${param.ccode}">Bài tập trên lớp</a>
            <span class="breadcrumb-separator">></span>
            <span class="breadcrumb-current">Nộp bài tập</span>
        </div>
    </header>
    
    <!-- Class Header -->
    <div class="class-header">
        <div class="class-title">Nộp bài tập</div>
        <div class="class-code">${assignment.title}</div>
    </div>
    
    <!-- Main Content -->
    <div class="main-content">
        <!-- Assignment Details -->
        <c:if test="${not empty assignment}">
            <div class="assignment-details">
                <h2>Chi tiết bài tập</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Tiêu đề</th>
                            <th>Mô tả</th>
                            <th>Tệp đính kèm</th>
                            <th>Ngày tạo</th>
                            <th>Hạn nộp</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><strong>${assignment.title}</strong></td>
                            <td>${assignment.description}</td>
                            <td>
                                <c:if test="${not empty assignment.fileUrl}">
                                    <a href="${assignment.fileUrl}" target="_blank" class="file-link">
                                        Xem tệp đính kèm
                                    </a>
                                </c:if>
                                <c:if test="${empty assignment.fileUrl}">
                                    <span style="color: #999;">Không có tệp</span>
                                </c:if>
                            </td>
                            <td>
                                <fmt:formatDate value="${assignment.createdAt}" 
                                                pattern="dd/MM/yyyy HH:mm" />
                            </td>
                            <td class="deadline">
                                <c:if test="${not empty assignment.deadline}">
                                    <fmt:formatDate value="${assignment.deadline}" 
                                                    pattern="dd/MM/yyyy HH:mm" />
                                </c:if>
                                <c:if test="${empty assignment.deadline}">
                                    <span style="color: #999;">Không có hạn</span>
                                </c:if>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </c:if>

        <!-- Submit Form Section -->
        <div class="submit-assignment-section">
            <h2>Nộp bài tập</h2>
            <div class="submit-form">
                <form action="${pageContext.request.contextPath}/s/nopbaitap" method="post">
                    <input type="hidden" name="assignment_id" value="${assignment.id}">
                    
                    <div class="form-group">
                        <label for="submit_text">Nội dung bài làm:</label>
                        <textarea id="submit_text" name="submit_text" placeholder="Nhập nội dung bài làm của bạn..." rows="8" required></textarea>
                    </div>
                    
                    <div class="form-group">
                        <label for="file_url">Link tệp đính kèm (tùy chọn):</label>
                        <input type="url" id="file_url" name="file_url" placeholder="https://drive.google.com/... hoặc link tệp khác">
                        <small class="form-help">Bạn có thể đính kèm link Google Drive, OneDrive hoặc link tệp khác</small>
                    </div>
                    
                    <div class="form-actions">
                        <button type="submit" class="submit-btn">✓ Nộp bài tập</button>
                        <button type="button" class="cancel-btn" onclick="history.back()">✗ Quay lại</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
     
    <!-- JavaScript -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/Views/Student/js/showClassAssignment.js"></script>
</body>
</html>
