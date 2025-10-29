document.addEventListener("DOMContentLoaded", () => {
    const usernameInput = document.getElementById("user-name");
    const alertBox = document.getElementById("username-alert");

    if (usernameInput && alertBox) {
        const originalValue = usernameInput.value.trim();

        usernameInput.addEventListener("input", () => {
            const current = usernameInput.value.trim();

            if (current !== originalValue) {
                alertBox.classList.add("d-none");
            } else {
                alertBox.classList.remove("d-none");
            }
        });
    }
});