package com.home.bankApplication.repositories;

import com.home.bankApplication.connection.ConnectionPoolProvider;
import com.home.bankApplication.exceptions.EntityRetrievalException;
import com.home.bankApplication.models.Bank;
import com.home.bankApplication.models.BankClient;
import com.home.bankApplication.models.Client;
import com.home.bankApplication.repositories.mappers.BankClientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BankClientRepository extends AbstractCRUDRepository<BankClient> {

    private static final Logger Log = LoggerFactory.getLogger(BankClientRepository.class);

    private static BankClientRepository instance;

    public BankClientRepository() {
        super(new BankClientMapper(), "bank_clients");
        instance = this;
    }

    public static synchronized BankClientRepository getInstance() {
        if (instance == null) {
            instance = new BankClientRepository();
        }
        return instance;
    }

    @Override
    public BankClient getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public List<BankClient> findAll() {
        return super.findAll();
    }

    @Override
    public List<BankClient> findAllSorted(String fieldName, Integer limit, Integer offset) {
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

    public List<BankClient> findClientsOfBank(Integer bankId){
        String findAllByBankId = "select * from bank_clients where bank_id = ".concat(String.valueOf(bankId));
        Connection connection;
        try {
            connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(findAllByBankId);
            List<BankClient> bankClients = new ArrayList<>();
            while (resultSet.next()) {
                bankClients.add(new BankClientMapper().toObject(resultSet));
            }
            return bankClients;
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval entity ", e);
            throw new EntityRetrievalException(e);
        }
    }


}
