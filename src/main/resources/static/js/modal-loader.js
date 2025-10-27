document.addEventListener('DOMContentLoaded', () => {
    const editModal = document.getElementById('editModal');

    if (editModal) {
        editModal.addEventListener('show.bs.modal', event => {
            const button = event.relatedTarget;
            if (!button) return;

            const id = button.getAttribute('data-id');
            const type = button.getAttribute('data-type');
            const color = button.getAttribute('data-color');
            const season = button.getAttribute('data-season');
            const brand = button.getAttribute('data-brand');
            const material = button.getAttribute('data-material');
            const imageUrl = button.getAttribute('data-imageurl');

            const form = editModal.querySelector('form');
            form.action = `/wardrobe-items/update/${id}`;

            editModal.querySelector('#edit-id').value = id;
            editModal.querySelector('#edit-type').value = type;
            editModal.querySelector('#edit-color').value = color;
            editModal.querySelector('#edit-season').value = season;
            editModal.querySelector('#edit-brand').value = brand;
            editModal.querySelector('#edit-material').value = material;
            editModal.querySelector('#edit-imageUrl').value = imageUrl;
        });

        const form = editModal.querySelector('form');
        form.addEventListener('submit', function (e) {
            e.preventDefault();

            Swal.fire({
                background: "#F7F3E3",
                color: "#4D1E10",
                title: 'Biztosan mented a módosításokat?',
                icon: 'question',
                showCancelButton: true,
                confirmButtonColor: '#6F1A07',
                cancelButtonColor: '#999999',
                confirmButtonText: 'Igen, mentés!',
                cancelButtonText: 'Mégse'
            }).then((result) => {
                if (result.isConfirmed) {
                    Swal.fire({
                        background: "#F7F3E3",
                        color: "#4D1E10",
                        title: 'Mentve!',
                        icon: 'success',
                        text: "A módosítások mentése sikeres.",
                        confirmButtonColor: "#6F1A07",
                        showConfirmButton: false
                    })
                    setTimeout(() => {
                        form.submit();
                    }, 1800);

                }
            });
        });
    }
});
