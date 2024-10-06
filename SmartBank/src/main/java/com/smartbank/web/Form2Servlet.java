package com.smartbank.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "Form2Servlet", value = "/form2")
public class Form2Servlet extends HttpServlet {

    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");

        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        session.setAttribute("telephone", telephone);

        response.sendRedirect("form3.jsp");

    }


}
