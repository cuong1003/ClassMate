<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Teacher Dashboard - ClassMate</title>
    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>
    <h1>Teacher Dashboard</h1>
    <!-- TODO: Add teacher dashboard content -->
    
    <button id="addClassBtn">Thêm lớp học</button>
    
    <!-- Form tạo lớp học (ẩn ban đầu) -->
    <div id="createClassForm" style="display: none;">
        <h3>Tạo lớp học mới</h3>
        <form action="createClass" method="post">
            <div>
                <label for="className">Tên lớp học:</label>
                <input type="text" id="className" name="className" required>
            </div>
            <br>
            <div>
                <label for="classCode">Mã lớp học:</label>
                <input type="text" id="classCode" name="classCode" 
                       pattern="[a-zA-Z0-9]+" 
                       title="Chỉ được nhập chữ cái và số, không có khoảng trắng hoặc ký tự đặc biệt"
                       maxlength="20"
                       required>
            </div>
            <br>
            <div>
                <button type="submit">Tạo lớp</button>
                <button type="button" id="cancelBtn">Hủy</button>
            </div>
        </form>
    </div>

    <!-- Test: JavaScript inline để kiểm tra logic -->
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const addClassBtn = document.getElementById('addClassBtn');
            const createClassForm = document.getElementById('createClassForm');
            const cancelBtn = document.getElementById('cancelBtn');
            const classCodeInput = document.getElementById('classCode');

            // Hiện form khi click "Thêm lớp học"
            addClassBtn.addEventListener('click', function() {
                createClassForm.style.display = 'block';
                addClassBtn.style.display = 'none';
            });

            // Ẩn form khi click "Hủy"
            cancelBtn.addEventListener('click', function() {
                createClassForm.style.display = 'none';
                addClassBtn.style.display = 'block';
                document.getElementById('className').value = '';
                document.getElementById('classCode').value = '';
            });

            // Kiểm soát input mã lớp học
            classCodeInput.addEventListener('keypress', function(e) {
                const char = String.fromCharCode(e.which);
                if (!/[a-zA-Z0-9]/.test(char)) {
                    e.preventDefault();
                    return false;
                }
            });

            classCodeInput.addEventListener('input', function(e) {
                this.value = this.value.replace(/[^a-zA-Z0-9]/g, '');
            });
        });
    </script>
</body>
</html> 