package com.home.bankApplication.servlets;

import com.home.bankApplication.exceptions.EntityRetrievalException;
import com.home.bankApplication.models.Bank;
import com.home.bankApplication.services.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "allBanksBeforeRemovingAllBanksServlet")
public class AllBanksBeforeRemovingAllBanksServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(AllBanksBeforeRemovingAllBanksServlet.class);

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {

        Log.info("Getting all banks for removing all banks");
        try {
            List<Bank> allBanks = BankService.getInstance().findAllBanks();
            request.setAttribute("allBanks", allBanks);
            request.getRequestDispatcher("/removeAllBanks.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            Log.error("Error during retrieval list of banks");
            throw new EntityRetrievalException(e);
        }
    }
}
