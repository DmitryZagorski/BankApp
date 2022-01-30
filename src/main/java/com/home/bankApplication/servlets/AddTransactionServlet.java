package com.home.bankApplication.servlets;

import com.home.bankApplication.models.Client;
import com.home.bankApplication.models.Transaction;
import com.home.bankApplication.services.ClientService;
import com.home.bankApplication.services.CurrencyService;
import com.home.bankApplication.services.TransactionService;
import com.mysql.cj.ServerPreparedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "addTransactionServlet")
public class AddTransactionServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(AddTransactionServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)  {

        String senderAccount = request.getParameter("accountId");
        Integer senderAccountId = Integer.parseInt(senderAccount);
        String recipientAccount = request.getParameter("recipientAccountId");
        Integer recipientAccountId = Integer.parseInt(recipientAccount);
        String money = request.getParameter("amountOfMoney");
        Double amountOfMoney = Double.parseDouble(money);
        Date creationDate = new Date(System.currentTimeMillis());

        try{
            Integer clientIdByBankAccountId = ClientService.getInstance().getClientIdByBankAccountId(senderAccountId);
            Integer currencyIdOfSender = CurrencyService.getInstance().getCurrencyIdByAccountId(senderAccountId);
            Transaction transaction = TransactionService.getInstance().addTransaction(clientIdByBankAccountId, senderAccountId, recipientAccountId, currencyIdOfSender, amountOfMoney, creationDate);
            Client clientById = ClientService.getInstance().getClientById(clientIdByBankAccountId);
            String currencyNameById = CurrencyService.getInstance().getCurrencyNameById(currencyIdOfSender);

            request.setAttribute("id", transaction.getId());
            request.setAttribute("clientName", clientById.getName());
            request.setAttribute("clientSurname", clientById.getSurname());
            request.setAttribute("senderAccountId", senderAccountId);
            request.setAttribute("recipientAccountId", recipientAccountId);
            request.setAttribute("currency", currencyNameById);
            request.setAttribute("amountOfMoney", amountOfMoney);
            request.setAttribute("creationDate", creationDate);

            request.getRequestDispatcher("/finalTransactionPage.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
