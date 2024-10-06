package com.smartbank.web;

import com.smartbank.models.entities.Credit;
import com.smartbank.models.enums.Civilite;
import com.smartbank.repository.interfaces.CreditRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

@WebServlet(name = "ajouterCreditServlet", value = "/ajouter-credit")
public class AjouterCreditServlet extends HttpServlet {

    private final CreditRepository creditRepository = new CreditRepository();
    private Validator validator;

    @Override
    public void init() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
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

        Set<ConstraintViolation<Credit>> violations = validator.validate(nouveauCredit);

        if (!violations.isEmpty()) {
            response.sendRedirect("index.jsp");
            return;
        }

        creditRepository.add(nouveauCredit);
        response.sendRedirect("succes.jsp");
    }
}
