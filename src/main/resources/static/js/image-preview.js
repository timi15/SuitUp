document.addEventListener('DOMContentLoaded', () => {
    const imageUrlInput = document.getElementById('imageUrl');
    const imagePreview = document.getElementById('imagePreview');
    const imageContainer = document.getElementById('imagePreviewContainer');

    const defaultImage = 'https://www.shutterstock.com/image-vector/default-placeholder-businesswoman-set-four-260nw-1093557821.jpg';

    imagePreview.src = defaultImage;
    imageContainer.style.display = 'block';

    if (imageUrlInput) {
        imageUrlInput.addEventListener('input', () => {
            const url = imageUrlInput.value.trim();

            if (!url) {
                imagePreview.src = defaultImage;
                return;
            }

            imagePreview.src = url;

            imagePreview.onerror = () => {
                imagePreview.src = defaultImage;
            };
        });
    }
});
