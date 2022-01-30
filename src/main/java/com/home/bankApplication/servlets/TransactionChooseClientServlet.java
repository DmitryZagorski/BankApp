package com.home.bankApplication.servlets;

import com.home.bankApplication.models.BankClient;
import com.home.bankApplication.services.BankClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "transactionChooseClientServlet")
public class TransactionChooseClientServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(TransactionChooseClientServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)  {

        try{
            List<BankClient> bankClients = BankClientService.getInstance().viewAllClientsWithJoin();
            request.setAttribute("clients", bankClients);
            request.getRequestDispatcher("/transactionChooseClient.jsp").forward(request, response);

        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
