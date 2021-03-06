package com.home.bankApplication.servlets;

import com.home.bankApplication.exceptions.EntityRemoveException;
import com.home.bankApplication.services.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "removeAllBanksServlet")
public class RemoveAllBanksServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(RemoveAllBanksServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        Log.info("Removing all banks");
        try {
            BankService.getInstance().removeAll();
            request.getRequestDispatcher("/removeAllBanks.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            Log.error("Error during removing all banks");
            throw new EntityRemoveException(e);
        }
    }
}
