document.addEventListener("DOMContentLoaded", () => {
    const logoutForms = document.querySelectorAll(".logout");

    logoutForms.forEach(form => {
        form.addEventListener("submit", function (event) {
            event.preventDefault();

            Swal.fire({
                background: "#F7F3E3",
                color: "#4D1E10",
                title: "Biztosan ki szeretnél jelentkezni?",
                showCancelButton: true,
                icon: 'question',
                confirmButtonColor: "#6F1A07",
                cancelButtonColor: "#B3B6B7",
                confirmButtonText: "Igen, kijelentkezem",
                cancelButtonText: "Mégse"
            }).then((result) => {
                if (result.isConfirmed) {
                    form.submit();
                }
            });
        });
    });
});
