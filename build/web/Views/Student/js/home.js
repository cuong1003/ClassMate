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