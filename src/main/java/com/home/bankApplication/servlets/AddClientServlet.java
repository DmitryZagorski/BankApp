package com.home.bankApplication.servlets;

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

@WebServlet(name = "addClientServlet")
public class AddClientServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(AddClientServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)  {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String clientStatus = request.getParameter("status");
        Integer clientStatusId = Integer.parseInt(clientStatus);

        try{
            ClientService.getInstance().addClient(name, surname, clientStatusId);
            request.setAttribute("clientName", name);
            request.getRequestDispatcher("/addClient.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }


    }
}
