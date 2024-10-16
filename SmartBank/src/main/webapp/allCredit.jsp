<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.smartbank.utils.DateTimeUtil" %>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/index.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
            href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap"
            rel="stylesheet">
    <title>Crédit Bancaire</title>
    <script src="JS/allCredit.js" defer></script>
</head>

<body class="bg over">
<%
    String flashMessage = (String) session.getAttribute("flashMessage");
    if (flashMessage != null) {
%>
<div class="toast ">
    <div class="toast-content">
        <svg xmlns="http://www.w3.org/2000/svg" width="21" height="21" fill="#02AFBC"
             class="bi bi-check-circle-fill" viewBox="0 0 16 16">
            <path
                    d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0m-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z" />
        </svg>

        <div class="message">
            <span class="text text-1">Success</span>
            <span class="text text-2"><%= flashMessage %></span>
        </div>
    </div>
    <i class="fa-solid fa-xmark close"><svg xmlns="http://www.w3.org/2000/svg" width="25" height="25"
                                            fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">
        <path
                d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708" />
    </svg></i>
    <div class="progress"></div>
</div>
<%
    session.removeAttribute("flashMessage");
%>
<%
    }
%>
<h1 class="titre-center m-t-10">Tout les crédits avec Wafasalaf !</h1>
<a class="d-flex gap-5 w-fit m-y-1 retour m-b-1" href="index.jsp">
    <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor"
         class="bi bi-arrow-left" viewBox="0 0 16 16">
        <path fill-rule="evenodd"
              d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8" />
    </svg>
    <p class="p-4">Retour</p>
</a>
<div class="table-container">
    <div class="flex-around">
        <form action="allCredit" method="get" class="m-b-1 flex-around">
            <div>
            <label class="label">Date</label>
            <input type="date" class="input2" name="date">
            </div>
            <div>
                <label class="label">Status</label>
                <select name="statusId" id="status">
                    <option value="">Tous</option>
                    <c:forEach var="status" items="${statuses}">
                        <option value="${status.id}">${status.nom}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="button4 ">
                <p class="p-12">Filtrer</p>
            </button>
        </form>
    </div>
    <table class="custom-table">
        <thead>
        <tr>
            <th>Date du demande</th>
            <th>Nom</th>
            <th>Email</th>
            <th>Telephone</th>
            <th>Numero CIN</th>
            <th>Fonction</th>
            <th >Projet</th>
            <th>Montant (en DH)</th>
            <th>Durée (en mois)</th>
            <th>Mensualités (en DH)</th>
            <th>Total revenus mensuels (en DH)</th>
            <th>Crédits en cours</th>
            <th>Status</th>
            <th>Action</th>

        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty credits}">
            <c:forEach var="credit" items="${credits}">
                <tr>
                    <td>${credit.dateCreation}</td>
                    <td>${credit.nom}</td>
                    <td>${credit.email}</td>
                    <td>${credit.telephone}</td>
                    <td>${credit.numeroCin}</td>
                    <td>${credit.fonction}</td>
                    <td>${credit.projet}</td>
                    <td>${credit.montant}</td>
                    <td>${credit.duree}</td>
                    <td>${credit.mensualite}</td>
                    <td>${credit.revenu}</td>
                    <td>${credit.creditEncours ? 'Oui' : 'Non'}</td>
                    <td>
                        <button class="ban-btn" onclick="showModal(${credit.id})">Details</button>
                    </td>
                    <td>
                        <button class="ban-btn" onclick="showForm(${credit.id})">Modifier Status</button>
                    </td>
                </tr>

            </c:forEach>
        </c:if>
        <c:if test="${empty credits}">
            <tr>
                <td colspan="15">Aucun crédit trouvé.</td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>
<c:forEach var="credit" items="${credits}">
<div id="modal-${credit.id}" class="modal">
    <div class="modal-content">
        <div class="table-container h-table">
            <table class="custom-table">
                <thead>
                <tr>
                    <th>Date</th>
                    <th>Status</th>
                    <th>Explication</th>
                </tr>
                </thead>
                <tbody>
                  <c:forEach var="status" items="${credit.creditStatus}" varStatus="loop">
                <tr>
                    <td>${DateTimeUtil.formatLocalDateTime(status.dateStatus, "dd-MM-yyyy  HH:mm")}
                        <c:if test="${loop.first}">
                            <p>(statutActuel)</p>
                        </c:if></td>

                    <td>${status.status.nom}</td>
                    <td>${status.explication} </td>
                </tr>
                  </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="flex-end p-5">
            <button type="button" class="button3 m-t-4" onclick="closeModal(${credit.id})">
                <p class="p-10">Fermer</p>
            </button>
        </div>
    </div>
</div>
</c:forEach>
<c:forEach var="credit" items="${credits}">
<div id="form-${credit.id}" class="modal">
    <div class="modal-content flexe">
        <form action="editStatus" method="post" class="flexe w-75" onsubmit="return validateForm(${credit.id})">
            <div>
                <h2>Modifier status</h2>
                <label class="label">Mon projet</label>
                <select class="minimal m-t-1" name="idStatus">
                <c:forEach var="statuses" items="${statuses}">
                    <option  value="${statuses.id}">${statuses.nom}</option>
                </c:forEach>
                </select>
                <div class="m-t-9 input-container">
                        <textarea name="explication" id="explication-${credit.id}" placeholder="" rows="6" cols="50"
                                  class="custom-input"></textarea>
                    <label for="explication-${credit.id}" class="custom-label">Explication*</label>
                </div>
                <div id="errorList-${credit.id}" class="error-list w-100"></div>
                <input type="text" name="idCredit" value="${credit.id}" hidden>
            </div>
            <div class="flex-end p-5">
                <button type="submit" class="button3 m-t-4">
                    <p class="p-10">Modifier</p>
                </button>
                <button type="button" class="button3 m-t-4" onclick="closeForm(${credit.id})">
                    <p class="p-10">Fermer</p>
                </button>
            </div>
        </form>
    </div>
</div>
</c:forEach>
</body>
</html>
