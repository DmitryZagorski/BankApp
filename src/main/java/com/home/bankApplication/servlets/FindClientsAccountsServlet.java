package com.home.bankApplication.servlets;

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
import java.util.List;

@WebServlet(name = "findClientsAccountsServlet")
public class FindClientsAccountsServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(FindClientsAccountsServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)  {

        String clientID = request.getParameter("clientId");
        Integer clientId = Integer.parseInt(clientID);

        try{
            List<BankAccount> clientsAccounts = BankAccountService.getInstance().findBankAccountsByClientId(clientId);
            request.setAttribute("accounts", clientsAccounts);
            request.getRequestDispatcher("/clientsAccounts.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
