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

@WebServlet(name = "clientsOfBankServlet")
public class ClientsOfBankServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(ClientsOfBankServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        String bankID = request.getParameter("bankId");
        Integer bankId = Integer.parseInt(bankID);

        try{
            List<BankClient> clientsOfBank = BankClientService.getInstance().findClientsOfBankByBankId(bankId);
            request.setAttribute("clients", clientsOfBank);
            request.getRequestDispatcher("/clientsOfBank.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}