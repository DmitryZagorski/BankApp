package com.home.bankApplication.servlets;

import com.home.bankApplication.exceptions.EntitySavingException;
import com.home.bankApplication.models.Bank;
import com.home.bankApplication.models.ClientStatus;
import com.home.bankApplication.models.Currency;
import com.home.bankApplication.services.AddClientService;
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

@WebServlet(name = "addClientServlet")
public class AddClientServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(AddClientServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        Log.info("Getting parameters for adding client");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String clientStatus = request.getParameter("statusId");
        Integer clientStatusId = Integer.parseInt(clientStatus);
        String currencyID = request.getParameter("currencyId");
        Integer currencyId = Integer.parseInt(currencyID);
        String amount = request.getParameter("amountOfMoney");
        Double amountOfMoney = Double.parseDouble(amount);
        String bankID = request.getParameter("bankId");
        Integer bankId = Integer.parseInt(bankID);

        try {
            AddClientService.getInstance().addClientWithBankAccount(name, surname, clientStatusId, currencyId, bankId, amountOfMoney);
            List<Bank> allBanks = BankService.getInstance().findAllBanks();
            List<Currency> allCurrency = CurrencyService.getInstance().findAllCurrency();
            List<ClientStatus> allStatuses = ClientStatusService.getInstance().findAllStatuses();
            request.setAttribute("allBanks", allBanks);
            request.setAttribute("allCurrency", allCurrency);
            request.setAttribute("allStatuses", allStatuses);
            request.setAttribute("clientName", name);
            request.getRequestDispatcher("/addClient.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            Log.error("Error during adding client");
            throw new EntitySavingException(e);
        }
    }
}
