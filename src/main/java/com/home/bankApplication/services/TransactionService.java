package com.home.bankApplication.services;

import com.home.bankApplication.models.Transaction;
import com.home.bankApplication.repositories.AddTransactionRepository;
import com.home.bankApplication.repositories.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.List;

public class TransactionService {

    private static final Logger Log = LoggerFactory.getLogger(TransactionService.class);

    private static TransactionService instance;

    private TransactionService() {

        instance = this;
    }

    public static TransactionService getInstance() {
        if (instance == null) {
            instance = new TransactionService();
        }
        return instance;
    }

    public Transaction addTransaction(Integer clientId, Integer senderAccountId, Integer recipientAccountId, Integer currencyId, Double amountOfMoney, Date creationDate){
        return AddTransactionRepository.getInstance().addTransaction(clientId, senderAccountId, recipientAccountId, currencyId, amountOfMoney, creationDate);
    }

    public List<Transaction> findByClientAndDate(Integer clientId, Date creationDate){
        return TransactionRepository.getInstance().findByClientAndDate(clientId, creationDate);
    }

}
