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

        /* Header */
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

        .header-right {
            display: flex;
            align-items: center;
            gap: 16px;
        }

        .icon-button {
            width: 48px;
            height: 48px;
            border-radius: 50%;
            border: none;
            background-color: #ffffff;
            color: #5f6368;
            font-size: 24px;
            font-weight: 300;
            cursor: pointer;
            display: flex;
            align-items: center;
            justify-content: center;
            transition: all 0.2s ease;
            box-shadow: 0 1px 3px rgba(60, 64, 67, 0.12);
        }

        .icon-button:hover {
            background-color: #f1f3f4;
            box-shadow: 0 2px 8px rgba(60, 64, 67, 0.15);
        }

        /* Class Grid */
        .class-grid-container {
            display: grid;
            padding: 24px;
            gap: 24px;
            grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
        }

        .class-card {
            border-radius: 8px;
            border: 1px solid #dadce0;
            background-color: #ffffff;
            overflow: hidden;
            position: relative;
            height: 300px;
            display: flex;
            flex-direction: column;
            transition: box-shadow 0.2s;
        }

        .class-card:hover {
            box-shadow: 0 1px 3px rgba(0,0,0,0.1), 0 1px 2px rgba(0,0,0,0.2);
        }

        .card-link-wrapper {
            text-decoration: none;
            color: inherit;
            display: flex;
            flex-direction: column;
            flex-grow: 1;
        }

        .card-header {
            padding: 16px;
            color: white;
            height: 100px;
        }

        .card-title {
            font-size: 1.4rem;
            font-weight: 500;
            margin: 0;
        }

        .card-subtitle {
            font-size: 0.875rem;
            margin-top: 4px;
            font-weight: 400;
            opacity: 0.9;
        }

        .card-body {
            flex-grow: 1;
        }

        .card-avatar {
            width: 76px;
            height: 76px;
            border-radius: 50%;
            position: absolute;
            top: 75px;
            right: 16px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 36px;
            font-weight: 500;
            color: white;
            border: 2px solid white;
        }

        /* Modal Styles */
        .modal-overlay {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            z-index: 1000;
        }

        .modal-content {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background: white;
            border-radius: 8px;
            padding: 24px;
            width: 400px;
            max-width: 90vw;
            box-shadow: 0 8px 24px rgba(60, 64, 67, 0.15);
        }

        .close-modal {
            position: absolute;
            top: 16px;
            right: 20px;
            font-size: 24px;
            cursor: pointer;
            color: #5f6368;
        }

        .close-modal:hover {
            color: #3c4043;
        }

        .modal-content h3 {
            margin-bottom: 20px;
            font-size: 20px;
            font-weight: 500;
            color: #3c4043;
        }

        .form-group {
            margin-bottom: 16px;
        }

        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-size: 14px;
            font-weight: 500;
            color: #3c4043;
        }

        .form-group input {
            width: 100%;
            padding: 12px 16px;
            border: 1px solid #dadce0;
            border-radius: 4px;
            font-size: 14px;
            transition: border-color 0.2s ease;
        }

        .form-group input:focus {
            outline: none;
            border-color: #1a73e8;
            box-shadow: 0 0 0 2px rgba(26, 115, 232, 0.1);
        }

        .form-actions {
            display: flex;
            justify-content: flex-end;
            gap: 12px;
            margin-top: 24px;
        }

        .form-actions button {
            padding: 10px 24px;
            border: none;
            border-radius: 4px;
            font-size: 14px;
            font-weight: 500;
            cursor: pointer;
            transition: all 0.2s ease;
        }

        .cancel-btn {
            background: transparent;
            color: #1a73e8;
            border: 1px solid #dadce0;
        }

        .cancel-btn:hover {
            background-color: rgba(26, 115, 232, 0.04);
        }

        .form-actions button[type="submit"] {
            background-color: #1a73e8;
            color: white;
        }

        .form-actions button[type="submit"]:hover {
            background-color: #1557b0;
        }

        /* User info link */
        /* User menu dropdown */
        .user-menu {
            position: relative;
        }

        .menu-button {
            width: 48px;
            height: 48px;
            border-radius: 50%;
            border: none;
            background-color: #ffffff;
            color: #5f6368;
            font-size: 20px;
            cursor: pointer;
            display: flex;
            align-items: center;
            justify-content: center;
            transition: all 0.2s ease;
            box-shadow: 0 1px 3px rgba(60, 64, 67, 0.12);
        }

        .menu-button:hover {
            background-color: #f1f3f4;
            box-shadow: 0 2px 8px rgba(60, 64, 67, 0.15);
        }

        .dropdown-menu {
            position: absolute;
            top: 56px;
            right: 0;
            background: white;
            border-radius: 8px;
            box-shadow: 0 4px 16px rgba(60, 64, 67, 0.15);
            border: 1px solid #dadce0;
            min-width: 250px;
            z-index: 1001;
            display: none;
        }

        .dropdown-menu.show {
            display: block;
        }

        .dropdown-item {
            display: block;
            padding: 12px 16px;
            text-decoration: none;
            color: #3c4043;
            font-size: 14px;
            transition: background-color 0.2s ease;
            border-bottom: 1px solid #f1f3f4;
        }

        .dropdown-item:last-child {
            border-bottom: none;
        }

        .dropdown-item:hover {
            background-color: #f8f9fa;
        }

        .dropdown-item:first-child {
            border-radius: 8px 8px 0 0;
        }

        .dropdown-item:last-child {
            border-radius: 0 0 8px 8px;
        }

        /* User info in dropdown */
        .user-info {
            background: linear-gradient(135deg, #f8f9fa 0%, #e8f0fe 100%);
            border-radius: 8px 8px 0 0;
        }

        .user-info-header {
            display: flex;
            align-items: center;
            gap: 8px;
            padding: 12px 16px 8px;
            border-bottom: 1px solid #e8eaed;
            margin-bottom: 12px;
        }

        .user-icon {
            font-size: 16px;
        }

        .user-info-title {
            font-size: 14px;
            font-weight: 600;
            color: #1a73e8;
        }

        .user-info-content {
            padding: 0 16px 16px;
        }

        .user-info-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 10px;
            padding: 6px 0;
        }

        .user-info-item:last-child {
            margin-bottom: 0;
        }

        .info-label {
            font-size: 12px;
            color: #5f6368;
            font-weight: 500;
            min-width: 50px;
        }

        .info-value {
            font-size: 13px;
            color: #3c4043;
            font-weight: 500;
            text-align: right;
            max-width: 160px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }

        .role-badge {
            padding: 2px 8px;
            border-radius: 12px;
            font-size: 11px;
            font-weight: 600;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        .role-badge.teacher {
            background: #e3f2fd;
            color: #1565c0;
        }

        .role-badge.student {
            background: #f3e5f5;
            color: #7b1fa2;
        }

        .dropdown-divider {
            height: 1px;
            background: #e8eaed;
            margin: 0;
        }

        .logout-item {
            color: #d93025 !important;
            font-weight: 500;
            display: flex;
            align-items: center;
            gap: 8px;
        }

        .logout-item:hover {
            background-color: #fce8e6;
        }

        .logout-icon {
            font-size: 14px;
        }
    </style>
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
                                <span class="info-value role-badge ${sessionScope.us.getRole() == 0 ? "Giáo viên" : "Học sinh"}">
                            </div>
                        </div>
                    </div>
                    <div class="dropdown-divider"></div>
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
                <a href="${pageContext.request.contextPath}/s/classcontroller?ccode=${classroom.classCode}" class="card-link-wrapper">
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

    <%-- Set session alert to global variable --%>
    <c:if test="${not empty sessionScope.folderalert}">
        <script>
            window.sessionAlert = '${sessionScope.folderalert}';
        </script>
        <%-- Clear session attribute after setting --%>
        <c:remove var="folderalert" scope="session" />
    </c:if>
    
    <script>
        // Modal functionality
        document.addEventListener('DOMContentLoaded', function() {
            const modal = document.getElementById('join-class-modal');
            const openBtn = document.getElementById('open-join-class-modal');
            const closeBtn = document.querySelector('.close-modal');
            const cancelBtn = document.querySelector('.cancel-btn');

            // Open modal
            openBtn.addEventListener('click', function() {
                modal.style.display = 'block';
            });

            // Close modal functions
            function closeModal() {
                modal.style.display = 'none';
                document.getElementById('classCode').value = '';
            }

            closeBtn.addEventListener('click', closeModal);
            cancelBtn.addEventListener('click', closeModal);

            // Close modal when clicking outside
            modal.addEventListener('click', function(e) {
                if (e.target === modal) {
                    closeModal();
                }
            });

            // Display session alert if exists
            if (window.sessionAlert) {
                alert(window.sessionAlert);
            }

            // User menu dropdown functionality
            const userMenuButton = document.getElementById('user-menu-button');
            const userDropdown = document.getElementById('user-dropdown');

            userMenuButton.addEventListener('click', function(e) {
                e.stopPropagation();
                userDropdown.classList.toggle('show');
            });

            // Close dropdown when clicking outside
            document.addEventListener('click', function(e) {
                if (!userMenuButton.contains(e.target) && !userDropdown.contains(e.target)) {
                    userDropdown.classList.remove('show');
                }
            });
        });
    </script>
</body>
</html> 