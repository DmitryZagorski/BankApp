package com.home.bankApplication.servlets;

import com.home.bankApplication.exceptions.EntitySavingException;
import com.home.bankApplication.models.BankAccount;
import com.home.bankApplication.services.BankAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "addClientsAccountServlet")
public class AddClientsAccountServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(AddClientsAccountServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        String clientID = request.getParameter("clientId");
        Integer clientId = Integer.parseInt(clientID);
        String bankID = request.getParameter("bankId");
        Integer bankId = Integer.parseInt(bankID);
        String currencyID = request.getParameter("currencyId");
        Integer currencyId = Integer.parseInt(currencyID);
        String amount = request.getParameter("amountOfMoney");
        Double amountOfMoney = Double.parseDouble(amount);

        try{
            BankAccountService.getInstance().addBankAccount(currencyId, amountOfMoney, bankId, clientId);
            String message = "Account was added successfully to client with id = ".concat(String.valueOf(clientId));
            request.setAttribute("message", message);
            request.getRequestDispatcher("/addBankAccount.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            Log.error("Error during adding bank account of client");
            throw new EntitySavingException(e);
        }

    }
}
