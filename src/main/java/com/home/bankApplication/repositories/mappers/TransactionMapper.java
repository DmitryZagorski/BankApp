package com.home.bankApplication.repositories.mappers;

import com.home.bankApplication.models.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionMapper implements MapperToObject<Transaction> {

    private static final Logger Log = LoggerFactory.getLogger(TransactionMapper.class);

    @Override
    public Transaction toObject(ResultSet resultSet) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setId(resultSet.getInt("id"));
        transaction.setClientId(resultSet.getInt("client_id"));
        transaction.setSenderBankAccountId(resultSet.getInt("sender_bank_account_id"));
        transaction.setRecipientBankAccountId(resultSet.getInt("recipient_bank_account_id"));
        transaction.setCurrencyId(resultSet.getInt("currency_id"));
        transaction.setAmountOfMoney(resultSet.getDouble("amount_of_money"));
        transaction.setCreationDate(resultSet.getDate("creation_date"));
        return transaction;
    }
}
