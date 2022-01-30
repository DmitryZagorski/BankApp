package com.home.bankApplication.servlets;

import com.home.bankApplication.exceptions.EntityRetrievalException;
import com.home.bankApplication.models.Transaction;
import com.home.bankApplication.services.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "findTransactionsServlet")
public class FindTransactionsServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(FindTransactionsServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response){

        String clientID = request.getParameter("clientId");
        Integer clientId = Integer.parseInt(clientID);
        String date = request.getParameter("creationDate");
        Date creationDate = Date.valueOf(date);

        try{
            List<Transaction> transactions = TransactionService.getInstance().findByClientAndDate(clientId, creationDate);
            request.setAttribute("transactions", transactions);
            request.getRequestDispatcher("/viewClientsTransactions.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            Log.error("Error during find transactions of client");
            throw new EntityRetrievalException(e);
        }

    }
}
