<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html>
<head>
    <title>Lớp học - ClassMate</title>
    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>
    <a href="${pageContext.request.contextPath}/UserInfoController?id=${studentId}">${studentName}</a>
    
    <h1>Lớp Học</h1>
    
    <c:forEach items="${danhsachlop}" var="classroom" varStatus="status">
            <div class="class-card">
                <a href="${pageContext.request.contextPath}/t/classcontroller?ccode=${classroom.classCode}" class="card-link-wrapper">
                    <div class="card-header" style="background-color: ${colors[status.index % fn:length(colors)]};">
                        <h2 class="card-title">${classroom.className}</h2>
                        <p class="card-subtitle">${classroom.classCode}</p>
                    </div>
                    <div class="card-body"></div>
                </a>
            </div>
    </c:forEach>
    
    
    
    
    
    <p>Tham gia lớp học</p>
    <form action="${pageContext.request.contextPath}/s/studenthome" method="post">
        <label for="classCode">Mã lớp học</label>
        <input type="text" id="classCode" name="classCode"
               pattern="[a-zA-Z0-9]+"
               title="Chỉ được nhập chữ cái và số, không có khoảng trắng hoặc ký tự đặc biệt"
               maxlength="20"
               required>
        <button type="button" class="cancel-btn">Hủy</button>
        <button type="submit">Tham gia lớp học</button>
        
    </form>
    
    
    
</body>
</html> 