package com.home.bankApplication.repositories;

import com.home.bankApplication.connection.ConnectionPoolProvider;
import com.home.bankApplication.exceptions.EntityRetrievalException;
import com.home.bankApplication.models.BankAccount;
import com.home.bankApplication.models.Currency;
import com.home.bankApplication.repositories.mappers.CurrencyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CurrencyRepository extends AbstractCRUDRepository<Currency> {

    private static final Logger Log = LoggerFactory.getLogger(CurrencyRepository.class);

    private static CurrencyRepository instance;

    public CurrencyRepository() {
        super(new CurrencyMapper(), "currency");
        instance = this;
    }

    public static synchronized CurrencyRepository getInstance() {
        if (instance == null) {
            instance = new CurrencyRepository();
        }
        return instance;
    }

    @Override
    public Currency getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public List<Currency> findAll() {
        return super.findAll();
    }

    @Override
    public void removeById(Integer id) {
        super.removeById(id);
    }

    @Override
    public void removeAll() {
        super.removeAll();
    }

    public Integer getCurrencyIdOfBankAccountByAccountId(Integer accountId){
        Log.info("Getting currencyId of bank account by bank accountId");
        String findCurrencyByAccountId = "select currency_id from bank_accounts where id =".concat(String.valueOf(accountId));
        Connection connection;
        try {
            connection = ConnectionPoolProvider.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(findCurrencyByAccountId);
            BankAccount bankAccount = new BankAccount();
            if (resultSet.next()) {
                bankAccount.setCurrencyId(resultSet.getInt("currency_id"));
            }
            return bankAccount.getCurrencyId();
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval entity ", e);
            throw new EntityRetrievalException(e);
        }
    }

    public String getCurrencyNameById(Integer currencyId){
        Log.info("Getting currency name by currencyId");
        String findCurrencyName = "select currency_name from currency where id =".concat(String.valueOf(currencyId));
        Connection connection;
        try {
            connection = ConnectionPoolProvider.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(findCurrencyName);
            Currency currency = new Currency();
            if (resultSet.next()) {
                currency.setName(resultSet.getString("currency_name"));
            }
            return currency.getName();
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval entity ", e);
            throw new EntityRetrievalException(e);
        }
    }
}
