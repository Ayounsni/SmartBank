function showModal() {
    let modal = document.getElementById('modal');
    modal.style.display = 'block';
}
function closeModal() {
    let modal = document.getElementById('modal');
    modal.style.display = 'none';
}
const toast = document.querySelector(".toast");
const progress = document.querySelector(".progress");
const closeIcon = document.querySelector(".close");

let timer1, timer2;

window.onload = () => {
    toast.classList.add("active");
    progress.classList.add("active");

    timer1 = setTimeout(() => {
        toast.classList.remove("active");
    }, 5000);

    timer2 = setTimeout(() => {
        progress.classList.remove("active");
    }, 5300);
};

closeIcon.addEventListener("click", () => {
    toast.classList.remove("active");
    progress.classList.remove("active");

    clearTimeout(timer1);
    clearTimeout(timer2);
});