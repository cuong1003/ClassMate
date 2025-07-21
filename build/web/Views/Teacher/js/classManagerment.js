// Function to generate consistent avatar color based on name
function getAvatarColor(name) {
    const colors = [
        '#1a73e8', '#9c27b0', '#ff7043', '#4caf50', 
        '#ff9800', '#3f51b5', '#e91e63', '#009688'
    ];
    let hash = 0;
    for (let i = 0; i < name.length; i++) {
        hash = name.charCodeAt(i) + ((hash << 5) - hash);
    }
    return colors[Math.abs(hash) % colors.length];
}

// Apply avatar colors after page load
document.addEventListener('DOMContentLoaded', function() {
    const avatars = document.querySelectorAll('.announcement-avatar');
    avatars.forEach(function(avatar) {
        const teacherName = avatar.parentElement.querySelector('.announcement-author').textContent;
        avatar.style.backgroundColor = getAvatarColor(teacherName);
    });
});

// Add click handler for create announcement button
document.addEventListener('DOMContentLoaded', function() {
    const toggleBtn = document.getElementById('toggleAnnouncementBtn');
    const announcementForm = document.getElementById('announcementForm');
    const cancelBtn = document.getElementById('cancelAnnouncementBtn');
    
    if (toggleBtn && announcementForm) {
        toggleBtn.addEventListener('click', function() {
            if (announcementForm.classList.contains('show')) {
                // Hide form
                announcementForm.classList.remove('show');
                toggleBtn.textContent = '+ Tạo thông báo mới';
            } else {
                // Show form
                announcementForm.classList.add('show');
                toggleBtn.textContent = '✗ Đóng form';
                // Focus vào trường title
                document.getElementById('title').focus();
            }
        });
    }
    
    if (cancelBtn) {
        cancelBtn.addEventListener('click', function() {
            // Hide form and reset
            announcementForm.classList.remove('show');
            toggleBtn.textContent = '+ Tạo thông báo mới';
            // Reset form
            document.getElementById('title').value = '';
            document.getElementById('description').value = '';
        });
    }
});
