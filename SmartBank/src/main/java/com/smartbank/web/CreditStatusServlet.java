package com.smartbank.web;


import com.smartbank.models.entities.Credit;
import com.smartbank.models.entities.CreditStatus;
import com.smartbank.models.entities.Status;
import com.smartbank.services.implementations.CreditService;
import com.smartbank.services.implementations.CreditStatusService;
import com.smartbank.services.implementations.StatusService;
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
import java.time.LocalDate;

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
        Status newStatus = statusService.findById(idStatus);
        CreditStatus creditStatus = new CreditStatus();
        creditStatus.setCredit(newCredit);
        creditStatus.setStatus(newStatus);
        creditStatus.setExplication(explication);
        creditStatus.setDateStatus(LocalDate.now());

        CreditStatus listCreditStatus = creditStatusService.persist(creditStatus);
        newCredit.getCreditStatus().add(listCreditStatus);
        session.setAttribute("flashMessage", "Le status modifier avec succes");
        response.sendRedirect("allCredit");


    }


}
