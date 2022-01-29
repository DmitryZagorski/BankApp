package com.home.bankApplication.servlets;

import com.home.bankApplication.exceptions.EntityRetrievalException;
import com.home.bankApplication.models.Bank;
import com.home.bankApplication.models.Client;
import com.home.bankApplication.models.Currency;
import com.home.bankApplication.services.BankService;
import com.home.bankApplication.services.ClientService;
import com.home.bankApplication.services.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "allDataToAddAccountServlet")
public class AllDataToAddAccountServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(AllDataToAddAccountServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        try {
            List<Client> allClients = ClientService.getInstance().findAllClients();
            List<Bank> allBanks = BankService.getInstance().findAllBanks();
            List<Currency> allCurrency = CurrencyService.getInstance().findAllCurrency();
            request.setAttribute("clients", allClients);
            request.setAttribute("banks", allBanks);
            request.setAttribute("allCurrency", allCurrency);
            request.getRequestDispatcher("/addBankAccount.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            Log.error("Error during retrieval list of clients, banks and currency");
            throw new EntityRetrievalException(e);
        }

    }
}
