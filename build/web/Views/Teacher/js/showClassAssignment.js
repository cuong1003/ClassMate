// JavaScript cho trang hiển thị danh sách assignments
document.addEventListener('DOMContentLoaded', function() {
    
    // Kiểm tra và highlight các assignment đã quá hạn
    checkOverdueAssignments();
    
    // Thêm search functionality (tùy chọn)
    addSearchFunctionality();
    
    // Initialize form toggle functionality
    initializeFormToggle();
    
});

function checkOverdueAssignments() {
    const now = new Date();
    const deadlineCells = document.querySelectorAll('.deadline');
    
    deadlineCells.forEach(function(cell) {
        const deadlineText = cell.textContent.trim();
        
        // Nếu có deadline và không phải "Không có hạn"
        if (deadlineText && !deadlineText.includes('Không có hạn')) {
            try {
                // Parse định dạng dd/MM/yyyy HH:mm
                const parts = deadlineText.split(' ');
                const datePart = parts[0]; // dd/MM/yyyy
                const timePart = parts[1]; // HH:mm
                
                const dateParts = datePart.split('/');
                const timeParts = timePart ? timePart.split(':') : ['23', '59'];
                
                const deadline = new Date(
                    parseInt(dateParts[2]), // year
                    parseInt(dateParts[1]) - 1, // month (0-based)
                    parseInt(dateParts[0]), // day
                    parseInt(timeParts[0]), // hour
                    parseInt(timeParts[1]) // minute
                );
                
                // Nếu đã quá hạn
                if (deadline < now) {
                    cell.classList.add('overdue');
                    cell.title = 'Đã quá hạn nộp bài';
                }
            } catch (error) {
                console.log('Không thể parse ngày:', deadlineText);
            }
        }
    });
}

function addSearchFunctionality() {
    // Tạo search box (tùy chọn)
    const container = document.querySelector('.container');
    const table = document.querySelector('table');
    
    if (container && table) {
        const searchBox = document.createElement('input');
        searchBox.type = 'text';
        searchBox.placeholder = 'Tìm kiếm bài tập...';
        searchBox.style.cssText = `
            margin-bottom: 15px;
            padding: 10px;
            width: 300px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
        `;
        
        container.insertBefore(searchBox, table);
        
        // Search functionality
        searchBox.addEventListener('input', function() {
            const searchTerm = this.value.toLowerCase();
            const rows = table.querySelectorAll('tbody tr');
            
            rows.forEach(function(row) {
                const title = row.querySelector('td:first-child').textContent.toLowerCase();
                const description = row.querySelector('td:nth-child(2)').textContent.toLowerCase();
                
                if (title.includes(searchTerm) || description.includes(searchTerm)) {
                    row.style.display = '';
                } else {
                    row.style.display = 'none';
                }
            });
        });
    }
}

function initializeFormToggle() {
    const toggleBtn = document.getElementById('toggleFormBtn');
    const form = document.getElementById('assignmentForm');
    const cancelBtn = document.getElementById('cancelBtn');
    
    if (toggleBtn && form) {
        // Toggle form khi click nút +
        toggleBtn.addEventListener('click', function() {
            const isVisible = form.classList.contains('show');
            
            if (isVisible) {
                hideForm();
            } else {
                showForm();
            }
        });
        
        // Ẩn form khi click cancel
        if (cancelBtn) {
            cancelBtn.addEventListener('click', function(e) {
                e.preventDefault();
                hideForm();
            });
        }
        
        // Form validation
        const formElement = form.querySelector('form');
        if (formElement) {
            formElement.addEventListener('submit', function(e) {
                if (!validateForm()) {
                    e.preventDefault();
                }
            });
        }
    }
}

function showForm() {
    const toggleBtn = document.getElementById('toggleFormBtn');
    const form = document.getElementById('assignmentForm');
    
    toggleBtn.classList.add('active');
    toggleBtn.innerHTML = '<span class="plus-icon">×</span> Đóng';
    
    form.style.display = 'block';
    setTimeout(function() {
        form.classList.add('show');
    }, 10);
}

function hideForm() {
    const toggleBtn = document.getElementById('toggleFormBtn');
    const form = document.getElementById('assignmentForm');
    
    toggleBtn.classList.remove('active');
    toggleBtn.innerHTML = '<span class="plus-icon">+</span> Tạo bài tập mới';
    
    form.classList.remove('show');
    setTimeout(function() {
        form.style.display = 'none';
        // Reset form
        const formElement = form.querySelector('form');
        if (formElement) {
            formElement.reset();
        }
    }, 400);
}

function validateForm() {
    const title = document.querySelector('input[name="title"]').value.trim();
    const description = document.querySelector('textarea[name="description"]').value.trim();
    
    if (!title) {
        alert('Vui lòng nhập tiêu đề bài tập!');
        return false;
    }
    
    if (!description) {
        alert('Vui lòng nhập mô tả bài tập!');
        return false;
    }
    
    return true;
}
