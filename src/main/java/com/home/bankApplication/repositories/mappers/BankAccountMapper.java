package com.home.bankApplication.repositories.mappers;

import com.home.bankApplication.models.BankAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountMapper implements MapperToObject<BankAccount>{

    private static final Logger Log = LoggerFactory.getLogger(BankAccountMapper.class);

    @Override
    public BankAccount toObject(ResultSet resultSet) throws SQLException {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(resultSet.getInt("id"));
        bankAccount.setCurrencyId(resultSet.getInt("currency_id"));
        bankAccount.setAmountOfMoney(resultSet.getDouble("amount_of_money"));
        bankAccount.setBankId(resultSet.getInt("bank_id"));
        bankAccount.setClientId(resultSet.getInt("client_id"));
        return bankAccount;
    }
}
