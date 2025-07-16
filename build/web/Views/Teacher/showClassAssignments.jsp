<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Bài tập trên lớp - ${ccode}</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Google+Sans:wght@400;500;700&display=swap">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Views/Teacher/css/showClassAssignments.css">
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
        <a href="#" class="nav-tab active">Bài tập trên lớp</a>
        <a href="${pageContext.request.contextPath}/t/studentlist?ccode=${ccode}" class="nav-tab">Mọi người</a>
    </div>
    
    <!-- Main Content -->
    <div class="main-content">
        <!-- Section tạo bài tập mới -->
        <div class="create-assignment-section">
            <button type="button" id="toggleFormBtn" class="toggle-form-btn">
                <span class="plus-icon">+</span> Tạo bài tập mới
            </button>
            
            <div id="assignmentForm" class="assignment-form">
                <form action="${pageContext.request.contextPath}/t/assignmentlist" method="post">
                    <input type="hidden" name="ccode" value="${ccode}">
                    
                    <div class="form-group">
                        <label for="title">Tiêu đề bài tập:</label>
                        <input type="text" id="title" name="title" placeholder="Nhập tiêu đề bài tập" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="description">Mô tả chi tiết:</label>
                        <textarea id="description" name="description" placeholder="Nhập mô tả chi tiết về bài tập" required></textarea>
                    </div>
                    
                    <div class="form-group">
                        <label for="file_url">Link tệp đính kèm (tùy chọn):</label>
                        <input type="url" id="file_url" name="file_url" placeholder="https://drive.google.com/...">
                    </div>
                    
                    <div class="form-group">
                        <label for="deadline">Hạn nộp bài:</label>
                        <input type="datetime-local" id="deadline" name="deadline">
                    </div>
                    
                    <div class="form-actions">
                        <button type="submit" class="submit-btn">✓ Tạo bài tập</button>
                        <button type="button" id="cancelBtn" class="cancel-btn">✗ Hủy</button>
                    </div>
                </form>
            </div>
        </div>
        
        <c:choose>
            <c:when test="${not empty assignments}">
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
                        <c:forEach items="${assignments}" var="assignment">
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
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="no-data">
                    <p>Chưa có bài tập nào trong lớp này.</p>
                </div>
                         </c:otherwise>
         </c:choose>
     </div>
     
     <!-- JavaScript -->
     <script type="text/javascript" src="${pageContext.request.contextPath}/Views/Teacher/js/showClassAssignment.js"></script>
 </body>
 </html>
