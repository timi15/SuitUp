document.addEventListener("DOMContentLoaded", () => {
    const carousels = [
        {id: "topsCarousel", inputId: "selectedTopId"},
        {id: "jacketsCarousel", inputId: "selectedJacketId"},
        {id: "dressesCarousel", inputId: "selectedDressId"},
        {id: "accessoriesCarousel", inputId: "selectedAccessoryId"},
        {id: "pantsCarousel", inputId: "selectedPantsId"},
        {id: "shoesCarousel", inputId: "selectedShoesId"}
    ];

    carousels.forEach(({id, inputId}) => {
        const carouselEl = document.getElementById(id);
        if (!carouselEl) return;

        const carousel = new bootstrap.Carousel(carouselEl, {interval: false});

        const updateSelectedId = () => {
            const activeItem = carouselEl.querySelector(".carousel-item.active");
            const selectedId = activeItem ? activeItem.dataset.id : "";
            const inputEl = document.getElementById(inputId);
            inputEl.value = selectedId || "";
        };

        carouselEl.addEventListener("slid.bs.carousel", updateSelectedId);
        updateSelectedId();
    });

    const form = document.querySelector("form");
    form.addEventListener("submit", (event) => {
        form.querySelectorAll("input[type='hidden']").forEach(input => {
            if (!input.value || input.value.trim() === "") {
                input.removeAttribute("name");
            }
        });
    });
});