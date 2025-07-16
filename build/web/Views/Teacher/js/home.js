document.addEventListener('DOMContentLoaded', function () {
    // Modal elements
    const modal = document.getElementById('add-class-modal');
    const openModalBtn = document.getElementById('open-add-class-modal');
    const closeModalSpans = [
        modal.querySelector('.close-modal'), 
        modal.querySelector('.cancel-btn')
    ];

    // Open modal
    openModalBtn.addEventListener('click', function () {
        modal.style.display = 'flex';
    });

    // Close modal via buttons
    closeModalSpans.forEach(btn => {
        if (btn) {
            btn.addEventListener('click', function () {
                modal.style.display = 'none';
            });
        }
    });

    // Close modal by clicking on the overlay
    window.addEventListener('click', function (event) {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    });

    // Form validation logic
    const classCodeInput = document.getElementById('classCode');
    if (classCodeInput) {
        classCodeInput.addEventListener('input', function (e) {
            this.value = this.value.replace(/[^a-zA-Z0-9]/g, '');
        });
    }

    // Toast notification functionality
    function createToastContainer() {
        let container = document.querySelector('.toast-container');
        if (!container) {
            container = document.createElement('div');
            container.className = 'toast-container';
            document.body.appendChild(container);
        }
        return container;
    }

    function showToast(message, type = 'info', duration = 5000) {
        const container = createToastContainer();
        
        const toast = document.createElement('div');
        toast.className = `toast ${type}`;
        toast.innerHTML = `
            ${message}
            <button class="toast-close">&times;</button>
        `;
        
        container.appendChild(toast);
        
        // Show toast with animation
        setTimeout(() => {
            toast.classList.add('show');
        }, 100);
        
        // Auto hide after duration
        const autoHide = setTimeout(() => {
            hideToast(toast);
        }, duration);
        
        // Close button functionality
        const closeBtn = toast.querySelector('.toast-close');
        closeBtn.addEventListener('click', () => {
            clearTimeout(autoHide);
            hideToast(toast);
        });
    }

    function hideToast(toast) {
        toast.classList.remove('show');
        setTimeout(() => {
            if (toast.parentNode) {
                toast.parentNode.removeChild(toast);
            }
        }, 300);
    }

    // Check for session alert and show toast
    if (window.sessionAlert && window.sessionAlert.trim() !== '') {
        // Determine toast type based on message content
        let toastType = 'info';
        if (window.sessionAlert.toLowerCase().includes('thành công') || window.sessionAlert.toLowerCase().includes('success')) {
            toastType = 'success';
        } else if (window.sessionAlert.toLowerCase().includes('lỗi') || window.sessionAlert.toLowerCase().includes('error')) {
            toastType = 'error';
        } else if (window.sessionAlert.toLowerCase().includes('cảnh báo') || window.sessionAlert.toLowerCase().includes('warning')) {
            toastType = 'warning';
        }
        
        showToast(window.sessionAlert, toastType);
        
        // Clear the global variable
        window.sessionAlert = null;
    }
});
