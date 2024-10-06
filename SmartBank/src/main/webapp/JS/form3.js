function validateForm() {
    const nom = document.getElementById('nom').value;
    const prenom = document.getElementById('prenom').value;
    const cin = document.getElementById('cin').value;
    const date_naissance = document.getElementById('date_naissance').value;
    const date_embauche = document.getElementById('date_embauche').value;
    const revenus = document.getElementById('revenus').value;

    let errors = [];

    if (nom.trim() === '') {
        errors.push('Le nom est obligatoire.');
    }
    if (prenom.trim() === '') {
        errors.push('Le prénom est obligatoire.');
    }

    const cinPattern = /^[A-Za-z]{2}\d{5,6}$/;
    if (!cinPattern.test(cin)) {
        errors.push('Le numéro CIN doit contenir 2 lettres suivies de 5 ou 6 chiffres.');
    }
    if (date_naissance.trim() === '') {
        errors.push('La date de naissance est obligatoire.');
    } else {
        const datePattern = /^\d{4}-\d{2}-\d{2}$/;
        if (!datePattern.test(date_naissance)) {
            errors.push('La date de naissance doit être au format YYYY-MM-DD.');
        } else {
            const birthDate = new Date(date_naissance);
            const today = new Date();

            if (birthDate > today) {
                errors.push('La date de naissance ne peut pas être dans le futur.');
            } else {
                const age = today.getFullYear() - birthDate.getFullYear();
                const monthDiff = today.getMonth() - birthDate.getMonth();

                if (age < 18 || (age === 18 && monthDiff < 0)) {
                    errors.push('Vous devez avoir plus de 18 ans.');
                }
            }
        }
    }
    if (date_embauche.trim() === '') {
        errors.push('La date d\'embauche est obligatoire.');
    } else {
        const datePattern = /^\d{4}-\d{2}-\d{2}$/;
        if (!datePattern.test(date_embauche)) {
            errors.push('La date d\'embauche doit être au format YYYY-MM-DD.');
        } else {
            const hireDate = new Date(date_embauche);
            const today = new Date();
            today.setHours(0, 0, 0, 0);
            if (hireDate > today) {
                errors.push('La date d\'embauche doit être dans le passé ou aujourd\'hui.');
            }
        }
    }
    if (revenus.trim() === '') {
        errors.push('Le total des revenus est obligatoire.');
    } else {
        const revenusPattern = /^\d+(\.\d+)?$/;
        if (!revenusPattern.test(revenus)) {
            errors.push('Le total des revenus doit être un nombre entier ou décimal.');
        }
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