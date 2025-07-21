<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Bài tập trên lớp - ${ccode}</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Google+Sans:wght@400;500;700&display=swap">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Views/Student/css/showClassAssignments.css">
</head>
<body>
    <!-- Header - đồng bộ với home.jsp -->
    <header class="main-header">
        <div class="breadcrumb">
            <a href="${pageContext.request.contextPath}/s/studenthome">Lớp học</a>
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
        <a href="${pageContext.request.contextPath}/s/bangtin?ccode=${ccode}" class="nav-tab">Bảng tin</a>
        <a href="#" class="nav-tab active">Bài tập trên lớp</a>
        <a href="${pageContext.request.contextPath}/s/danhsachlop?ccode=${ccode}" class="nav-tab">Mọi người</a>
    </div>
    
    <!-- Main Content -->
    <div class="main-content">        
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
                            <tr class="clickable-row" onclick="window.location.href='${pageContext.request.contextPath}/s/nopbaitap?id=${assignment.id}'">
                                <td><strong>${assignment.title}</strong></td>
                                <td>${assignment.description}</td>
                                <td>
                                    <c:if test="${not empty assignment.fileUrl}">
                                        <a href="${assignment.fileUrl}" target="_blank" class="file-link" onclick="event.stopPropagation();">
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/Views/Student/js/showClassAssignment.js"></script>
</body>
</html>
