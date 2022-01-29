package com.home.bankApplication.servlets;

import com.home.bankApplication.models.Bank;
import com.home.bankApplication.services.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "beforeFindClientsOfBankServlet")
public class BeforeFindClientsOfBankServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(BeforeFindClientsOfBankServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        try{
            List<Bank> allBanks = BankService.getInstance().findAllBanks();
            request.setAttribute("allBanks", allBanks);
            request.getRequestDispatcher("/banksToFindClient.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }


    }
}
