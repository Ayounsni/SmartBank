package com.smartbank.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "Form1Servlet", value = "/form1")
public class Form1Servlet extends HttpServlet {

    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String projet = request.getParameter("projet");
        String fonction = request.getParameter("fonction");
        Long montant = Long.parseLong(request.getParameter("montant"));
        Long duree = Long.parseLong(request.getParameter("duree"));
        Double mensualite = Double.parseDouble(request.getParameter("mensualite"));

        HttpSession session = request.getSession();
        session.setAttribute("projet", projet);
        session.setAttribute("fonction", fonction);
        session.setAttribute("montant", montant);
        session.setAttribute("duree", duree);
        session.setAttribute("mensualite", mensualite);

        response.sendRedirect("form2.jsp");
    }


}
