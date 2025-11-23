const tagContainer = document.getElementById('tagContainer');
const tagInput = document.getElementById('tagInput');
const hiddenTopics = document.getElementById('hiddenTopics');
let tags = [];
let spaceCount = 0;

function updateHiddenInput() {
    hiddenTopics.value = tags.join(' ');
}

function createTag(label) {
    const badge = document.createElement('span');
    badge.classList.add('badge', 'badge-custom', 'd-flex', 'align-items-center');
    badge.style.gap = '6px';
    badge.innerHTML = `
      ${label}
      <button type="button" class="btn-close btn-sm" aria-label="Remove"></button>
    `;

    badge.querySelector('.btn-close').addEventListener('click', () => {
        removeTag(label);
    });

    tagContainer.insertBefore(badge, tagInput);
}

function removeTag(label) {
    tags = tags.filter(tag => tag !== label);
    renderTags();
}

function renderTags() {
    document.querySelectorAll('#tagContainer .badge').forEach(tag => tag.remove());
    tags.forEach(createTag);
    updateHiddenInput();
}

tagInput.addEventListener('keydown', (e) => {
    if (e.key === ' ') {
        spaceCount++;
        if (spaceCount === 2) {
            e.preventDefault();
            const newTag = tagInput.value.trim();
            if (newTag && !tags.includes(newTag)) {
                tags.push(newTag);
                renderTags();
            }
            tagInput.value = '';
            spaceCount = 0;
        }
    } else {
        spaceCount = 0;
    }
});