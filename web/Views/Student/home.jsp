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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Student/css/home.css">
    <%-- Set session alert to global variable --%>
    <c:if test="${not empty sessionScope.folderalert}">
        <script>
            window.sessionAlert = '${sessionScope.folderalert}';
        </script>
        <%-- Clear session attribute after setting --%>
        <c:remove var="folderalert" scope="session" />
    </c:if>
</head>
<body>
    <!-- Header -->
    <header class="main-header">
        <h1 class="header-title">Lớp học</h1>
        <div class="header-right">
            <button id="open-join-class-modal" class="icon-button" title="Tham gia lớp học">+</button>
            <div class="user-menu">
                <button class="menu-button" id="user-menu-button">⋮</button>
                <div class="dropdown-menu" id="user-dropdown">
                    <div class="user-info">
                        <div class="user-info-header">
                            <span class="user-icon">👤</span>
                            <span class="user-info-title">Thông tin người dùng</span>
                        </div>
                        
                        <div class="user-info-content">
                            <div class="user-info-item">
                                <span class="info-label">ID:</span>
                                <span class="info-value">${sessionScope.us.getUserId()}</span>
                            </div>
                            <div class="user-info-item">
                                <span class="info-label">Tên:</span>
                                <span class="info-value">${sessionScope.us.getFullName()}</span>
                            </div>
                            <div class="user-info-item">
                                <span class="info-label">Email:</span>
                                <span class="info-value">${sessionScope.us.getEmail()}</span>
                            </div>
                            <div class="user-info-item">
                                <span class="info-label">Vai trò:</span>
                                <span class="info-value role-badge ${sessionScope.us.getRole() == 0 ? "teacher" : "student"}">
                                    ${sessionScope.us.getRole() == 0 ? "Giáo viên" : "Học sinh"}
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="dropdown-divider"></div>
                    <a href="${pageContext.request.contextPath}/updatethongtin?id=${sessionScope.us.getUserId()}" class="dropdown-item logout-item">
                        <span class="logout-icon">🚪</span>
                        Chỉnh sửa thông tin
                    <a href="${pageContext.request.contextPath}/exit" class="dropdown-item logout-item">
                        <span class="logout-icon">🚪</span>
                        Đăng xuất
                    </a>
                </div>
            </div>
        </div>
    </header>

    <!-- Class Grid -->
    <main class="class-grid-container">
        <c:forEach items="${danhsachlop}" var="classroom" varStatus="status">
            <%-- Define an array of colors --%>
            <c:set var="colors" value="${['#1e8e3e', '#d93025', '#1a73e8', '#f29900', '#8f2ead', '#007b83']}" />
            <c:set var="avatarColors" value="${['#187a32', '#c4281f', '#1668c9', '#e08c00', '#7d269c', '#006a70']}" />
            
            <div class="class-card">
                <a href="${pageContext.request.contextPath}/s/bangtin?ccode=${classroom.classCode}" class="card-link-wrapper">
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
                    <form action="${pageContext.request.contextPath}/s/studenthome" method="post">
    <input type="hidden" name="ccode" value="${classroom.classCode}">
    <button type="submit" name="leave" value="leave">Rời</button>
</form>
            </div>
        </c:forEach>
    </main>

    <!-- Join Class Modal -->
    <div id="join-class-modal" class="modal-overlay">
        <div class="modal-content">
            <span class="close-modal">&times;</span>
            <h3>Tham gia lớp học</h3>
            <form action="${pageContext.request.contextPath}/s/studenthome" method="post">
                <div class="form-group">
                    <label for="classCode">Mã lớp học:</label>
                    <input type="text" id="classCode" name="ccode"
                           pattern="[a-zA-Z0-9]+"
                           title="Chỉ được nhập chữ cái và số, không có khoảng trắng hoặc ký tự đặc biệt"
                           maxlength="20"
                           placeholder="Nhập mã lớp học để tham gia"
                           required>
                </div>
                <div class="form-actions">
                    <button type="button" class="cancel-btn">Hủy</button>
                    <button type="submit">Tham gia</button>
                </div>
            </form>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/Views/Student/js/home.js"></script>
</body>
</html> 