package com.home.bankApplication.servlets;

import com.home.bankApplication.exceptions.EntitySavingException;
import com.home.bankApplication.services.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import java.io.IOException;

public class AddBankServlet extends javax.servlet.http.HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(AddBankServlet.class);

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {

        Log.info("Getting parameters to add bank");
        String name = request.getParameter("name");
        String comForIndividual = request.getParameter("commissionForIndividual");
        Double commissionForIndividual = Double.parseDouble(comForIndividual);
        String comForEntity = request.getParameter("commissionForEntity");
        Double commissionForEntity = Double.parseDouble(comForEntity);

        try {
            BankService.getInstance().addBank(name, commissionForIndividual, commissionForEntity);
            request.setAttribute("bankName", name);
            request.getRequestDispatcher("/addBank.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            Log.error("Error during adding new bank");
            throw new EntitySavingException(e);
        }

    }
}
