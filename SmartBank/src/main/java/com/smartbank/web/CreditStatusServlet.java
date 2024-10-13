package com.smartbank.web;

import com.smartbank.models.entities.Credit;
import com.smartbank.models.entities.CreditStatus;
import com.smartbank.models.entities.Status;
import com.smartbank.services.interfaces.ICreditService;
import com.smartbank.services.interfaces.ICreditStatusService;
import com.smartbank.services.interfaces.IStatusService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

@WebServlet(name = "CreditStatusServlet", value = "/editStatus")
public class CreditStatusServlet extends HttpServlet {

    @Inject
    private ICreditService creditService ;
    @Inject
    private  ICreditStatusService creditStatusService ;
    @Inject
    private IStatusService statusService ;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Long idCredit = Long.parseLong(request.getParameter("idCredit"));
        Long idStatus = Long.parseLong(request.getParameter("idStatus"));
        String explication = request.getParameter("explication");

        Credit newCredit = creditService.findById(idCredit);

        Optional<CreditStatus> statusActuel = newCredit.getCreditStatus().stream()
                .max(Comparator.comparing(CreditStatus::getDateStatus));

        Status newStatus = statusService.findById(idStatus);
        CreditStatus creditStatus = new CreditStatus();
        creditStatus.setCredit(newCredit);
        creditStatus.setStatus(newStatus);
        creditStatus.setExplication(explication);
        creditStatus.setDateStatus(LocalDateTime.now());
        creditStatus.setActif(true);

        if (statusActuel.isPresent()) {
            CreditStatus latestStatus = statusActuel.get();
            if (latestStatus.getStatus().getNom().equals(newStatus.getNom())) {
                session.setAttribute("flashMessage", "Le statut n’a pas été modifié, puisqu’il est déjà le même");
                response.sendRedirect("allCredit");
            } else {
                CreditStatus oldStatus = creditStatusService.findActiveCreditStatus(newCredit);
                oldStatus.setActif(false);
                creditStatusService.merge(oldStatus);

                CreditStatus listCreditStatus = creditStatusService.persist(creditStatus);
                newCredit.getCreditStatus().add(listCreditStatus);
                session.setAttribute("flashMessage", "Le status modifier avec succes");
                response.sendRedirect("allCredit");
            }
        } else {
            response.sendRedirect("allCredit");;
        }
    }
}
