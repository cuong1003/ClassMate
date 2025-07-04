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
});
