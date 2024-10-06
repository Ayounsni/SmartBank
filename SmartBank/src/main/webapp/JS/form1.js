const slider = document.getElementById('mySlider');
const numberInput = document.getElementById('sliderValue');
const durationSlider = document.getElementById('durationSlider');
const durationValue = document.getElementById('durationValue');
const mensualiteSlider = document.getElementById('mensualiteSlider');
const mensualiteValue = document.getElementById('mensualiteValue');

const tauxInteretAnnuel = 0.12;

function calculerMensualite(capital, dureeEnMois, tauxAnnuel) {
    const tauxMensuel = tauxAnnuel / 12;
    const mensualite = (capital * tauxMensuel) / (1 - Math.pow(1 + tauxMensuel, -dureeEnMois));
    return mensualite.toFixed(2);
}

function calculerDuree(capital, mensualite, tauxAnnuel) {
    const tauxMensuel = tauxAnnuel / 12;
    const dureeEnMois = -Math.log(1 - (capital * tauxMensuel) / mensualite) / Math.log(1 + tauxMensuel);
    return Math.round(dureeEnMois);
}

function updateMensualite() {
    const capital = parseFloat(numberInput.value);
    const dureeEnMois = parseInt(durationValue.value);
    let mensualite = calculerMensualite(capital, dureeEnMois, tauxInteretAnnuel);


    mensualiteValue.value = mensualite;
    mensualiteSlider.value = mensualite;
}

function updateDureeFromMensualite() {
    const capital = parseFloat(numberInput.value);
    const mensualite = parseFloat(mensualiteValue.value);
    const dureeEnMois = calculerDuree(capital, mensualite, tauxInteretAnnuel);

    durationValue.value = dureeEnMois;
    durationSlider.value = dureeEnMois;
}

slider.addEventListener('input', function () {
    numberInput.value = slider.value;
    updateMensualite();
});

numberInput.addEventListener('input', function () {
    slider.value = numberInput.value;
    updateMensualite();
});

durationSlider.addEventListener('input', function () {
    durationValue.value = durationSlider.value;
    updateMensualite();
});

durationValue.addEventListener('input', function () {
    durationSlider.value = durationValue.value;
    updateMensualite();
});

mensualiteSlider.addEventListener('input', function () {
    mensualiteValue.value = mensualiteSlider.value;
    updateDureeFromMensualite();
});

mensualiteValue.addEventListener('input', function () {
    mensualiteSlider.value = mensualiteValue.value;
    updateDureeFromMensualite();
});

updateMensualite();

function validateForm() {
    const montant = document.getElementById('sliderValue').value;
    const duree = document.getElementById('durationValue').value;
    const mensualites = document.getElementById('mensualiteValue').value;
    let errors = [];

    if (!montant || montant <= 0) {
        errors.push('La durée doit être un nombre entier supérieur à 0.');
    }

    if (!duree || duree <= 0) {
        errors.push('La durée doit être un nombre entier supérieur à 0.');
    }

    if (!mensualites || mensualites <= 0) {
        errors.push('Les mensualités doivent être supérieures à 0.');
    }

    if (errors.length > 0) {
        showModal(errors);
        return false;
    }
    return true;
}

function showModal(errors) {
    let errorList = document.getElementById('errorList');
    errorList.innerHTML = '';

    errors.forEach(error => {
        let li = document.createElement('li');
        li.style.marginBottom = '8px';
        li.textContent = error;
        errorList.appendChild(li);
    });

    let modal = document.getElementById('errorModal');
    modal.style.display = 'block';
}

function closeModal() {
    let modal = document.getElementById('errorModal');
    modal.style.display = 'none';
}