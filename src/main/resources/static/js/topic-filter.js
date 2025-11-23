document.querySelectorAll(".topic-sum").forEach(btn => {
    btn.addEventListener("click", (e) => {
        const selectedTopic = btn.textContent.trim().toLowerCase();

        document.querySelectorAll(".whole-card").forEach(card => {

            const badges = card.querySelectorAll(".badge.topic");

            if (badges.length === 0) {
                card.style.display = "none";
                return;
            }

            let match = false;
            badges.forEach(badge => {
                const topicText = badge.textContent.trim().toLowerCase();
                if (topicText === selectedTopic) {
                    match = true;
                }
            });

            if (match) {
                card.style.display = "";
            } else {
                card.style.display = "none";
            }
        })
    })
})

const showAll = document.getElementById("showAll");
showAll.addEventListener("click", (e) => {
    window.location.reload();
})