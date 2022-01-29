package com.home.bankApplication.repositories;

import com.home.bankApplication.connection.ConnectionPoolProvider;
import com.home.bankApplication.exceptions.EntityRetrievalException;
import com.home.bankApplication.models.BankAccount;
import com.home.bankApplication.models.BankClient;
import com.home.bankApplication.repositories.mappers.BankAccountMapper;
import com.home.bankApplication.repositories.mappers.BankClientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BankAccountRepository extends AbstractCRUDRepository<BankAccount> {

    private static final Logger Log = LoggerFactory.getLogger(BankAccountRepository.class);

    private static BankAccountRepository instance;

    public BankAccountRepository() {
        super(new BankAccountMapper(), "bank_accounts");
        instance = this;
    }

    public static synchronized BankAccountRepository getInstance(){
        if (instance==null){
            instance = new BankAccountRepository();
        }
        return instance;
    }

    @Override
    public BankAccount getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public List<BankAccount> findAll() {
        return super.findAll();
    }

    @Override
    public List<BankAccount> findAllSorted(String fieldName, Integer limit, Integer offset) {
        return super.findAllSorted(fieldName, limit, offset);
    }

    @Override
    public void removeById(Integer id) {
        super.removeById(id);
    }

    @Override
    public void removeAll() {
        super.removeAll();
    }

    public List<BankAccount> findAccountsOfClient(Integer clientId){
        String findAllAccountsByClientId = "select * from bank_accounts where client_id = ".concat(String.valueOf(clientId));
        Connection connection;
        try {
            connection = ConnectionPoolProvider.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(findAllAccountsByClientId);
            List<BankAccount> accounts = new ArrayList<>();
            while (resultSet.next()) {
                accounts.add(new BankAccountMapper().toObject(resultSet));
            }
            return accounts;
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval entity ", e);
            throw new EntityRetrievalException(e);
        }
    }

}
