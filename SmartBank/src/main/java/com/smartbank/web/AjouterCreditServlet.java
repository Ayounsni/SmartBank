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
        String projet = request.getParameter("projet");
        String fonction = request.getParameter("fonction");
        Long montant = Long.parseLong(request.getParameter("montant"));
        Long duree = Long.parseLong(request.getParameter("duree"));
        Double mensualite = Double.parseDouble(request.getParameter("mensualite"));
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        String civiliteStr = request.getParameter("civilite").toUpperCase();
        Civilite civilite = Civilite.valueOf(civiliteStr);
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String numeroCin = request.getParameter("numeroCin");
        LocalDate dateNaissance = LocalDate.parse(request.getParameter("dateNaissance"));
        LocalDate dateEmbauche = LocalDate.parse(request.getParameter("dateEmbauche"));
        Double revenu = Double.parseDouble(request.getParameter("revenu"));
        boolean creditEncours = request.getParameter("creditEncours") != null;

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
            request.setAttribute("violations", violations);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        creditRepository.add(nouveauCredit);
        response.sendRedirect("succes.jsp");
    }
}
