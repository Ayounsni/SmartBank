<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Ajouter un Crédit</title>
</head>
<body>
<h1>Ajouter un Nouveau Crédit</h1>

<form action="ajouter-credit" method="post">
    <label>Projet:</label>
    <input type="text" name="projet" ><br>

    <label>Fonction:</label>
    <input type="text" name="fonction" ><br>

    <label>Montant:</label>
    <input type="number" name="montant" ><br>

    <label>Durée:</label>
    <input type="number" name="duree" ><br>

    <label>Mensualité:</label>
    <input type="text" name="mensualite" ><br>

    <label>Email:</label>
    <input type="text" name="email" ><br>

    <label>Téléphone:</label>
    <input type="text" name="telephone" ><br>

    <label>Civilité:</label>
    <select name="civilite" >
        <option value="MONSIEUR">Monsieur</option>
        <option value="MADAME">Madame</option>
        <option value="MADEMOISELLE">Mademoiselle</option>
    </select><br>

    <label>Nom:</label>
    <input type="text" name="nom" ><br>

    <label>Prénom:</label>
    <input type="text" name="prenom" ><br>

    <label>Numéro CIN:</label>
    <input type="text" name="numeroCin"><br>

    <label>Date de Naissance:</label>
    <input type="date" name="dateNaissance"><br>

    <label>Date d'Embauche:</label>
    <input type="date" name="dateEmbauche" ><br>

    <label>Revenu:</label>
    <input type="text" name="revenu" ><br>

    <label>Crédit en cours:</label>
    <input type="checkbox" name="creditEncours"><br>

    <button type="submit">Ajouter le Crédit</button>
</form>

</body>
</html>
