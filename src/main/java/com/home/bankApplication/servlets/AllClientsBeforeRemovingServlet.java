package com.home.bankApplication.servlets;

import com.home.bankApplication.exceptions.EntityRetrievalException;
import com.home.bankApplication.models.Client;
import com.home.bankApplication.services.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "allClientsBeforeRemovingServlet")
public class AllClientsBeforeRemovingServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(AllClientsBeforeRemovingServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        try{
            List<Client> allClients = ClientService.getInstance().findAllClients();
            request.setAttribute("allClients", allClients);
            request.getRequestDispatcher("/removeClient.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            Log.error("Error during retrieval list of clients");
            throw new EntityRetrievalException(e);
        }

    }
}
