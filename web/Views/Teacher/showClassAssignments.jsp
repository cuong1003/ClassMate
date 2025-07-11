<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Bài tập trên lớp - ${ccode}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Teacher/css/showClassAssignments.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>
<body>
    <%-- Sử dụng cấu trúc header giống hệt home.jsp --%>
    <header class="main-header">
        <h1 class="header-title">
             <a href="${pageContext.request.contextPath}/t/teacherhome">Lớp học</a> &gt; <span>${ccode}</span>
        </h1>
    </header>


    <div class="main-container">
        <nav class="class-nav">
            <a href="${pageContext.request.contextPath}/t/classcontroller?ccode=${ccode}">Bảng tin</a>
            <a href="#" class="active">Bài tập trên lớp</a>
            <a href="${pageContext.request.contextPath}/c/studentlist?ccode=${ccode}">Mọi người</a>
            <a href="#">Điểm</a>
        </nav>

        <main class="content-area">
            <div class="create-button-container">
                <a href="#" class="create-button" id="open-create-modal">
                    <span class="material-icons">add</span>Tạo
                </a>
            </div>

            <div class="assignments-list">
                <%-- Kiểm tra nếu danh sách rỗng để hiển thị thông báo --%>
                <c:if test="${empty assignments}">
                    <div class="empty-state">
                        <img src="https://ssl.gstatic.com/classroom/empty_states_classwork.svg" alt="Không có bài tập">
                        <h2>Đây là nơi giao bài tập</h2>
                        <p>Bạn có thể thêm bài tập và các công việc khác cho lớp rồi sắp xếp thành các chủ đề.</p>
                    </div>
                </c:if>

                <%-- Lặp qua danh sách bài tập nếu không rỗng --%>
                <c:if test="${not empty assignments}">
                    <c:forEach items="${assignments}" var="item">
                        <div class="assignment-item">
                            <div class="assignment-icon">
                                <span class="material-icons">assignment</span>
                            </div>
                            <div class="assignment-details">
                                <a href="#" class="assignment-title">${item.title}</a>
                                <p class="assignment-deadline">Hạn nộp: ${item.deadLine}</p>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </main>
    </div>

    <!-- Modal tạo bài tập -->
    <div id="create-assignment-modal" class="modal-overlay">
        <div class="modal-content">
            <span class="close-modal">&times;</span>
            <h3>Tạo bài tập mới</h3>
            <form action="${pageContext.request.contextPath}/t/assignmentlist" method="post">
                <input type="hidden" name="ccode" value="${ccode}">
                <input type="hidden" name="createdBy" value="${sessionScope.us.userId}">
                
                <div class="form-group">
                    <label for="title">Tiêu đề bài tập:</label>
                    <input type="text" id="title" name="title" required>
                </div>
                
                <div class="form-group">
                    <label for="description">Mô tả:</label>
                    <textarea id="description" name="description" rows="4"></textarea>
                </div>
                
                <div class="form-group">
                    <label for="deadline">Hạn nộp:</label>
                    <input type="date" id="deadline" name="deadline" required>
                </div>
                
                <div class="form-actions">
                    <button type="button" class="cancel-btn">Hủy</button>
                    <button type="submit">Tạo bài tập</button>
                </div>
            </form>
        </div>
    </div>

    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const modal = document.getElementById('create-assignment-modal');
            const openModalBtn = document.getElementById('open-create-modal');
            const closeModalBtn = modal.querySelector('.close-modal');
            const cancelBtn = modal.querySelector('.cancel-btn');

            // Mở modal
            openModalBtn.addEventListener('click', function (e) {
                e.preventDefault();
                modal.style.display = 'flex';
            });

            // Đóng modal
            [closeModalBtn, cancelBtn].forEach(btn => {
                btn.addEventListener('click', function () {
                    modal.style.display = 'none';
                });
            });

            // Đóng modal khi click bên ngoài
            window.addEventListener('click', function (event) {
                if (event.target === modal) {
                    modal.style.display = 'none';
                }
            });
        });
    </script>

    <style>
        /* Modal styles */
        .modal-overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            display: none;
            align-items: center;
            justify-content: center;
            z-index: 1000;
        }

        .modal-content {
            background-color: white;
            padding: 24px;
            border-radius: 8px;
            width: 90%;
            max-width: 500px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.2);
            position: relative;
        }

        .close-modal {
            position: absolute;
            top: 10px;
            right: 20px;
            font-size: 28px;
            font-weight: bold;
            color: #888;
            cursor: pointer;
        }

        .modal-content h3 {
            margin-top: 0;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 16px;
        }

        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: 500;
        }

        .form-group input[type="text"],
        .form-group input[type="date"],
        .form-group textarea {
            width: 100%;
            padding: 8px 12px;
            box-sizing: border-box;
            border: 1px solid #dadce0;
            border-radius: 4px;
            font-size: 14px;
        }

        .form-group textarea {
            resize: vertical;
            min-height: 80px;
        }

        .form-actions {
            text-align: right;
            margin-top: 24px;
        }

        .form-actions button {
            padding: 8px 16px;
            border-radius: 4px;
            border: 1px solid transparent;
            cursor: pointer;
            font-size: 14px;
            margin-left: 8px;
        }

        .form-actions button[type="submit"] {
            background-color: #1a73e8;
            color: white;
        }

        .form-actions button[type="submit"]:hover {
            background-color: #1557b0;
        }

        .form-actions .cancel-btn {
            background-color: #f1f3f4;
            color: #3c4043;
        }

        .form-actions .cancel-btn:hover {
            background-color: #e8eaed;
        }
    </style>
</body>
</html>
