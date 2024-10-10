function showModal(id) {
    let modal = document.getElementById('modal-'+id);
    modal.style.display = 'block';
}
function closeModal(id) {
    let modal = document.getElementById('modal-'+id);
    modal.style.display = 'none';
}
function showForm(id) {
    let modal = document.getElementById('form-'+id);
    modal.style.display = 'block';
}
function closeForm(id) {
    let modal = document.getElementById('form-'+id);
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

function validateForm(id) {
    const explication = document.getElementById('explication-'+id).value;
    let errors = [];

    if (explication.trim() === '') {
        errors.push('L explication  obligatoire.');
    } else if (explication.length < 10) {
        errors.push('L explication doit avoir plus de 10 lettres');
    }

    if (errors.length > 0) {
        showError(errors);
        return false;
    }
    return true;

    function showError(errors) {
        let errorList = document.getElementById('errorList');
        errorList.innerHTML = '';

        errors.forEach(error => {
            let p = document.createElement('p');
            p.style.marginBottom = '8px';
            p.textContent = error;
            errorList.appendChild(p);
        });
    }
}