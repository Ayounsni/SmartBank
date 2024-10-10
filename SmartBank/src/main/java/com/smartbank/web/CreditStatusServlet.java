package com.smartbank.web;


import com.smartbank.services.implementations.CreditService;
import com.smartbank.services.implementations.CreditStatusService;
import com.smartbank.services.implementations.StatusService;
import com.smartbank.services.interfaces.ICreditService;
import com.smartbank.services.interfaces.ICreditStatusService;
import com.smartbank.services.interfaces.IStatusService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CreditServlet", value = "/editStatus")
public class CreditStatusServlet extends HttpServlet {

    private final ICreditService creditService = new CreditService();
    private final ICreditStatusService creditStatusService = new CreditStatusService();
    private final IStatusService statusService = new StatusService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
