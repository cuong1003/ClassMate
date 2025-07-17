function toggleActionColumn() {
    var actionColumn = document.querySelectorAll('.action-column');
    var isVisible = actionColumn[0].style.display === 'table-cell';
    actionColumn.forEach(function(element) {
        element.style.display = isVisible ? 'none' : 'table-cell';
    });
    document.getElementById('optionsBtn').textContent = isVisible ? 'Tùy chọn' : 'Ẩn tùy chọn';
}

function confirmDelete(studentName, userId, classCode) {
    if (confirm('Bạn có chắc chắn muốn xóa học sinh "' + studentName + '" khỏi lớp học này không?')) {
        // Tạo form để gửi POST request
        var form = document.createElement('form');
        form.method = 'POST';
        form.action = contextPath + '/t/studentlist';
        
        var userIdInput = document.createElement('input');
        userIdInput.type = 'hidden';
        userIdInput.name = 'userId';
        userIdInput.value = userId;
        
        var classCodeInput = document.createElement('input');
        classCodeInput.type = 'hidden';
        classCodeInput.name = 'ccode';
        classCodeInput.value = classCode;
        
        form.appendChild(userIdInput);
        form.appendChild(classCodeInput);
        document.body.appendChild(form);
        form.submit();
    }
}
