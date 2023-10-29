document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('form');
    const inputFields = form.querySelectorAll('input, select, textarea');

    inputFields.forEach(field => {
        // Initial check on page load
        updateFieldStyle(field);

        // Add event listener for real-time validation
        field.addEventListener('input', () => {
            updateFieldStyle(field);
        });
    });
});

function updateFieldStyle(field) {
    if (!field.value.trim()) {
        field.style.borderColor = 'red';
    } else {
        field.style.borderColor = ''; // or set to your default border color
    }
}
