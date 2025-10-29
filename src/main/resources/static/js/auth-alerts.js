document.addEventListener("DOMContentLoaded", () => {
    // Hibás bejelentkezés
    const loginError = document.getElementById("login-error");
    if (loginError) {
        Swal.fire({
            background: "#F7F3E3",
            color: "#4D1E10",
            icon: 'error',
            title: 'Hibás bejelentkezés!',
            text: 'A felhasználónév vagy jelszó nem megfelelő.',
            confirmButtonText: 'OK',
            confirmButtonColor: '#6F1A07'
        });
    }

    // Sikeres kijelentkezés
    const logoutSuccess = document.getElementById("logout-success");
    if (logoutSuccess) {
        Swal.fire({
            background: "#F7F3E3",
            color: "#4D1E10",
            icon: 'success',
            title: 'Sikeres kijelentkezés!',
            text: 'Viszlát, hamarosan újra találkozunk!',
            confirmButtonText: 'OK',
            confirmButtonColor: '#6F1A07'
        });
    }

    // Sikeres regisztráció
    const registerSuccess = document.getElementById("register-success");
    if (registerSuccess) {
        Swal.fire({
            background: "#F7F3E3",
            color: "#4D1E10",
            icon: 'success',
            title: 'Sikeres regisztráció!',
            text: 'Most már bejelentkezhetsz a SuitUp! fiókodba.',
            confirmButtonText: 'Belépés',
            confirmButtonColor: '#6F1A07'
        });
    }
});