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

@WebServlet(name = "RemoveBankServlet")
public class RemoveBankServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(RemoveBankServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        String bankID = request.getParameter("bankId");
        Integer bankId = Integer.parseInt(bankID);

        try{
            BankService.getInstance().removeById(bankId);
            request.setAttribute("removedBankId", bankId);
            request.getRequestDispatcher("/allBanksBeforeRemoveServlet").forward(request, response);
        } catch (ServletException | IOException e) {
            Log.error("Error during removing bank by bankId");
            throw new EntityRemoveException(e);
        }

    }
}
