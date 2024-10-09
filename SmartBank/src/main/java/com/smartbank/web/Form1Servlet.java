package com.smartbank.web;

import com.smartbank.services.implementations.CreditService;
import com.smartbank.services.interfaces.ICreditService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "Form1Servlet", value = "/form1")
public class Form1Servlet extends HttpServlet {

    private final ICreditService creditService = new CreditService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String projet = request.getParameter("projet");
        String fonction = request.getParameter("fonction");
        Long montant = Long.parseLong(request.getParameter("montant"));
        Long duree = Long.parseLong(request.getParameter("duree"));
        Double mensualite = Double.parseDouble(request.getParameter("mensualite"));

        Double mensualiteBack = creditService.calculerMensualite(montant,duree);

        HttpSession session = request.getSession();
        session.setAttribute("projet", projet);
        session.setAttribute("fonction", fonction);
        session.setAttribute("montant", montant);
        session.setAttribute("duree", duree);
        if (!mensualite.equals(mensualiteBack)) {
            session.setAttribute("mensualite", mensualiteBack);
        }else {
            session.setAttribute("mensualite", mensualite);
        }
        session.setAttribute("flashMessage", mensualiteBack.toString());
        response.sendRedirect("form2.jsp");

    }

}
