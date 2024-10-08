package com.smartbank.web;

import com.smartbank.models.entities.Credit;
import com.smartbank.models.enums.Civilite;
import com.smartbank.services.implementations.CreditService;
import com.smartbank.services.interfaces.ICreditService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "CreditServlet", value = "/allCredit")
public class CreditServlet extends HttpServlet {

    private final ICreditService creditService = new CreditService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Credit> credits = creditService.findAllCredits();

        request.setAttribute("credits", credits);

        request.getRequestDispatcher("allCredit.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String projet = (String) session.getAttribute("projet");
        String fonction = (String) session.getAttribute("fonction");
        String email = (String) session.getAttribute("email");
        String telephone = (String) session.getAttribute("telephone");
        String civiliteStr = request.getParameter("civilite").toUpperCase();
        Civilite civilite = Civilite.valueOf(civiliteStr);
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String numeroCin = request.getParameter("numeroCin");
        boolean creditEncours = request.getParameter("creditEncours") != null;
        Long montant = (Long) session.getAttribute("montant");
        Long duree = (Long) session.getAttribute("duree");
        Double mensualite = (Double) session.getAttribute("mensualite");
        String dateNaissanceStr = request.getParameter("dateNaissance");
        if (dateNaissanceStr == null || dateNaissanceStr.isEmpty()) {
            response.sendRedirect("form1.jsp");
            return;
        }
        LocalDate dateNaissance = LocalDate.parse(dateNaissanceStr);
        String dateEmbaucheStr = request.getParameter("dateEmbauche");
        if (dateEmbaucheStr == null || dateEmbaucheStr.isEmpty()) {
            response.sendRedirect("form1.jsp");
            return;
        }
        LocalDate dateEmbauche = LocalDate.parse(dateEmbaucheStr);
        String revenuStr = request.getParameter("revenu");
        if (revenuStr == null || revenuStr.isEmpty()) {
            response.sendRedirect("form1.jsp");
            return;
        }
        Double revenu = Double.parseDouble(revenuStr);

        Credit nouveauCredit = new Credit();
        nouveauCredit.setProjet(projet);
        nouveauCredit.setFonction(fonction);
        nouveauCredit.setMontant(montant);
        nouveauCredit.setDuree(duree);
        nouveauCredit.setMensualite(mensualite);
        nouveauCredit.setEmail(email);
        nouveauCredit.setTelephone(telephone);
        nouveauCredit.setCivilite(civilite);
        nouveauCredit.setNom(nom);
        nouveauCredit.setPrenom(prenom);
        nouveauCredit.setNumeroCin(numeroCin);
        nouveauCredit.setDateNaissance(dateNaissance);
        nouveauCredit.setDateEmbauche(dateEmbauche);
        nouveauCredit.setRevenu(revenu);
        nouveauCredit.setCreditEncours(creditEncours);

        try {
            creditService.add(nouveauCredit);
            session.setAttribute("flashMessage", "Crédit creer avec succes");
            response.sendRedirect("allCredit");
        } catch (IllegalArgumentException e) {
            response.sendRedirect("form1.jsp");
        }
    }
}
