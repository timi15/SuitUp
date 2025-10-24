function confirmDelete(id) {
    Swal.fire({
        background: "#F7F3E3",
        color: "#4D1E10",
        title: 'Biztosan törlöd?',
        text: 'Ez a művelet visszavonhatatlan!',
        iconColor: "#4D1E10",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: "#6F1A07",
        cancelButtonColor: "#B3B6B7",
        confirmButtonText: 'Igen, törölés!',
        cancelButtonText: 'Mégse'
    }).then((result) => {
        if (result.isConfirmed) {
            fetch(`/wardrobe-items/delete/${id}`, {
                method: 'POST'
            }).then(() => {
                Swal.fire({
                    background: "#F7F3E3",
                    color: "#4D1E10",
                    title: 'Törölve!',
                    icon: 'success',
                    text: "A törlés sikeres.",
                    showConfirmButton: false
                }).then(() => {
                    location.reload();
                });
            });
        }
    });
}