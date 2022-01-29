package com.home.bankApplication.servlets;

import com.home.bankApplication.exceptions.EntityRemoveException;
import com.home.bankApplication.services.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "removeClientServlet")
public class RemoveClientServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(RemoveClientServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        String clientID = request.getParameter("clientId");
        Integer clientId = Integer.parseInt(clientID);

        try {
            ClientService.getInstance().removeById(clientId);
            request.setAttribute("removedClientId", clientId);
            request.getRequestDispatcher("/removeClient.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            Log.error("Error during removing client by clientId");
            throw new EntityRemoveException(e);
        }
    }
}
