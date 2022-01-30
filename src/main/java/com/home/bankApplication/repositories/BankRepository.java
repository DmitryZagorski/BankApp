package com.home.bankApplication.repositories;

import com.home.bankApplication.connection.ConnectionPoolProvider;
import com.home.bankApplication.exceptions.EntitySavingException;
import com.home.bankApplication.models.Bank;
import com.home.bankApplication.repositories.mappers.BankMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

public class BankRepository extends AbstractCRUDRepository<Bank> {

    private static final Logger Log = LoggerFactory.getLogger(BankRepository.class);
    private static final String insertBankSQL = "insert into banks (bank_name, commission_for_individual, commission_for_entity) values (?, ?, ?)";
    private static final String updateBankSQL = "update banks set bank_name = ?, commission_for_individual = ?, commission_for_entity = ? where id = ?";

    private static BankRepository instance;

    public BankRepository() {
        super(new BankMapper(), "banks");
        instance = this;
    }

    public static synchronized BankRepository getInstance() {
        if (instance == null) {
            instance = new BankRepository();
        }
        return instance;
    }

    @Override
    public Bank getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public List<Bank> findAll() {
        return super.findAll();
    }

    @Override
    public List<Bank> findAllSorted(String fieldName, Integer limit, Integer offset) {
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

    public Bank addBank(Bank bank) {
        Log.info("Adding new bank");
        PreparedStatement prStatement = null;
        Connection connection = null;
        try {
            connection = ConnectionPoolProvider.getConnection();
            connection.setSavepoint();
            connection.setAutoCommit(false);
            if (bank.getId()==0) {
                prStatement = connection.prepareStatement(insertBankSQL, prStatement.RETURN_GENERATED_KEYS);
            } else {
                prStatement = connection.prepareStatement(updateBankSQL, prStatement.RETURN_GENERATED_KEYS);
            }
            setBankValues(bank, prStatement);
            if (bank.getId() != 0) {
                prStatement.setInt(4, bank.getId());
            }
            int result = prStatement.executeUpdate();
            if (result != 1) {
                throw new EntitySavingException("Bank was not added!");
            }
            ResultSet generatedKey = prStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                bank.setId(generatedKey.getInt(1));
            }
            connection.commit();
            return bank;
        } catch (SQLException e) {
            Log.error("Something wrong during adding bank", e);
            throw new EntitySavingException(e);
        } finally {
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (prStatement != null) {
                try {
                    prStatement.close();
                } catch (SQLException e) {
                    throw new EntitySavingException(e);
                }
            }
        }
    }

    private void setBankValues(Bank bank, PreparedStatement prStatement) throws SQLException {
        Log.info("Setting bank values started");
        prStatement.setString(1, bank.getName());
        prStatement.setDouble(2, bank.getCommissionForIndividual());
        prStatement.setDouble(3, bank.getCommissionForEntity());
    }

}
