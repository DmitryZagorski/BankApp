package com.home.bankApplication.servlets;

import com.home.bankApplication.services.BankService;
import com.home.bankApplication.services.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "removeAllClientsServlet")
public class RemoveAllClientsServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(RemoveAllClientsServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            ClientService.getInstance().removeAll();
            String message = "All clients were removed";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/removeAllClients.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
