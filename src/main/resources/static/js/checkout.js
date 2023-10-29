document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('form');

    if (!form) return; // Stop if form is not found

    const inputFields = form.querySelectorAll('.form-control, select');

    inputFields.forEach(field => {
        // Initial check on page load
        updateFieldStyle(field);

        // Add event listener for real-time validation
        field.addEventListener('input', () => {
            updateFieldStyle(field);
        });
    });

    // Add event listener to check the form on submission
    form.addEventListener('submit', (event) => {
        let formIsValid = true;

        inputFields.forEach(field => {
            if (!field.value.trim()) {
                field.classList.add('error-field');
                formIsValid = false;
            }
        });

        // Prevent form submission if there are any errors
        if (!formIsValid) {
            event.preventDefault();
        }
    });
});

function updateFieldStyle(field) {
    if (!field.value.trim()) {
        field.classList.add('error-field');
    } else {
        field.classList.remove('error-field');
    }
}
