<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Bảng tin lớp học</title>
        <style>
            body {
                font-family: Arial;
                margin: 20px;
            }
            .post-form {
                margin-bottom: 30px;
            }
            .post-box {
                border: 1px solid #ddd;
                padding: 15px;
                border-radius: 8px;
                margin-bottom: 20px;
            }
            .post-author {
                font-weight: bold;
            }
            .post-content {
                margin-top: 10px;
            }
            textarea {
                width: 100%;
                height: 80px;
            }
            button {
                padding: 8px 16px;
                background: #1976d2;
                color: white;
                border: none;
                border-radius: 4px;
            }
            .post-time {
                font-size: 12px;
                color: gray;
            }
        </style>
    </head>
    <body>
        <a href="${pageContext.request.contextPath}/c/studentlist?ccode=${ccode}">Thành viên</a>

        <!-- RỜI KHỎI LỚP -->
        <form method="post" action="${pageContext.request.contextPath}/s/classcontroller"
              onsubmit="return confirm('Bạn có chắc chắn muốn rời khỏi lớp không?');">
            <input type="hidden" name="ccode" value="${ccode}">
            <button type="submit">Rời khỏi lớp</button>
        </form>




        <h2>Lớp: ${ccode}</h2>

        <!-- 🔵 FORM ĐĂNG BÀI -->
        <div class="post-form">
            <form method="post" action="${pageContext.request.contextPath}/t/classcontroller?ccode=${ccode}">

                <textarea name="message" placeholder="Chia sẻ điều gì đó với lớp..."></textarea><br>
                <button type="submit">Đăng bài</button>
            </form>
        </div>

        <!--  DANH SÁCH BÀI ĐĂNG -->
        <c:forEach var="chat" items="${chats}">
            <c:if test="${not empty chat.message}">
                <div style="margin-bottom: 10px;">
                    <b>${chat.sender}</b> <small>${chat.time}</small><br>
                    <span>${chat.message}</span>
                </div>
            </c:if>
        </c:forEach>

    </body>
</html>