package com.home.bankApplication.servlets;

import com.home.bankApplication.exceptions.EntityRetrievalException;
import com.home.bankApplication.models.Bank;
import com.home.bankApplication.models.ClientStatus;
import com.home.bankApplication.models.Currency;
import com.home.bankApplication.services.BankService;
import com.home.bankApplication.services.ClientStatusService;
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

@WebServlet(name = "beforeAddingClientServlet")
public class BeforeAddingClientServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(BeforeAddingClientServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        Log.info("Getting all banks for adding client");
        try{
            List<Bank> allBanks = BankService.getInstance().findAllBanks();
            List<Currency> allCurrency = CurrencyService.getInstance().findAllCurrency();
            List<ClientStatus> allStatuses = ClientStatusService.getInstance().findAllStatuses();
            request.setAttribute("allBanks", allBanks);
            request.setAttribute("allCurrency", allCurrency);
            request.setAttribute("allStatuses", allStatuses);
            request.getRequestDispatcher("/addClient.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            Log.error("Error during retrieval list of statuses");
            throw new EntityRetrievalException(e);
        }
    }
}
