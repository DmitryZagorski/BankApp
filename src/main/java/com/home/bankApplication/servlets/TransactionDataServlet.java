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

@WebServlet(name = "transactionDataServlet")
public class TransactionDataServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(TransactionDataServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        Log.info("Preparing data to create transaction");
        String clientID = request.getParameter("clientId");
        Integer clientId = Integer.parseInt(clientID);

        try {
            List<BankAccount> bankAccountsByClientId = BankAccountService.getInstance().findBankAccountsByClientId(clientId);
            List<BankAccount> allAccounts = BankAccountService.getInstance().findOtherAccounts(clientId);

            request.setAttribute("senderAccounts", bankAccountsByClientId);
            request.setAttribute("allAccounts", allAccounts);

            request.getRequestDispatcher("/dataToCreateTransaction.jsp").forward(request, response);

        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
