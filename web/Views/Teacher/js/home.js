// JavaScript cho Teacher Dashboard
document.addEventListener('DOMContentLoaded', function() {
    // Lấy các element
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
        // Reset form
        document.getElementById('className').value = '';
        document.getElementById('classCode').value = '';
    });

    // Kiểm soát input mã lớp học - chỉ cho phép chữ cái và số
    if (classCodeInput) {
        classCodeInput.addEventListener('keypress', function(e) {
            const char = String.fromCharCode(e.which);
            // Chỉ cho phép: a-z, A-Z, 0-9
            if (!/[a-zA-Z0-9]/.test(char)) {
                e.preventDefault();
                return false;
            }
        });

        // Xóa ký tự không hợp lệ khi paste
        classCodeInput.addEventListener('input', function(e) {
            this.value = this.value.replace(/[^a-zA-Z0-9]/g, '');
        });
    }
});
