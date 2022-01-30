package com.home.bankApplication.repositories;

import com.home.bankApplication.connection.ConnectionPoolProvider;
import com.home.bankApplication.exceptions.EntityRetrievalException;
import com.home.bankApplication.exceptions.EntitySavingException;
import com.home.bankApplication.models.Bank;
import com.home.bankApplication.models.BankAccount;
import com.home.bankApplication.models.BankClient;
import com.home.bankApplication.repositories.mappers.BankAccountMapper;
import com.home.bankApplication.repositories.mappers.BankClientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankAccountRepository extends AbstractCRUDRepository<BankAccount> {

    private static final Logger Log = LoggerFactory.getLogger(BankAccountRepository.class);
    private static final String insertBankAccountSQL = "insert into bank_accounts (currency_id, amount_of_money, bank_id, client_id) values (?, ?, ?, ?)";
    private static final String updateBankAccountSQL = "update bank_accounts set currency_id = ?, amount_of_money = ?, bank_id = ?, client_id = ? where id = ?";

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
        String findAllAccountsByClientId = "select bank_accounts.id, currency.currency_name, bank_accounts.amount_of_money, banks.bank_name, clients.name, clients.surname from bank_accounts inner join currency on bank_accounts.currency_id = currency.id inner join banks on bank_accounts.bank_id = banks.id inner join clients on bank_accounts.client_id = clients.id where client_id=".concat(String.valueOf(clientId));
        //String findAllAccountsByClientId = "select * from bank_accounts where client_id = ".concat(String.valueOf(clientId));
        Connection connection;
        try {
            connection = ConnectionPoolProvider.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(findAllAccountsByClientId);
            List<BankAccount> accounts = new ArrayList<>();
            while (resultSet.next()) {
                BankAccount bankAccount = new BankAccount();
                bankAccount.setId(resultSet.getInt("id"));
                bankAccount.setClientName(resultSet.getString("name"));
                bankAccount.setClientSurname(resultSet.getString("surname"));
                bankAccount.setBankName(resultSet.getString("bank_name"));
                bankAccount.setCurrencyName(resultSet.getString("currency_name"));
                bankAccount.setAmountOfMoney(resultSet.getDouble("amount_of_money"));
                accounts.add(bankAccount);
            }
            return accounts;
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval entity ", e);
            throw new EntityRetrievalException(e);
        }
    }

    public BankAccount addBankAccount(BankAccount bankAccount) {
        Log.info("Adding new bankAccount");
        PreparedStatement prStatement = null;
        Connection connection = null;
        try {
            connection = ConnectionPoolProvider.getConnection();
            connection.setSavepoint();
            connection.setAutoCommit(false);
            if (bankAccount.getId() == 0) {
                prStatement = connection.prepareStatement(insertBankAccountSQL, prStatement.RETURN_GENERATED_KEYS);
            } else {
                prStatement = connection.prepareStatement(updateBankAccountSQL, prStatement.RETURN_GENERATED_KEYS);
            }
            setBankAccountValues(bankAccount, prStatement);
            if (bankAccount.getId() != 0) {
                prStatement.setInt(5, bankAccount.getId());
            }
            int result = prStatement.executeUpdate();
            if (result != 1) {
                throw new EntitySavingException("Bank account was not added!");
            }
            ResultSet generatedKey = prStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                bankAccount.setId(generatedKey.getInt(1));
            }
            connection.commit();
            return bankAccount;
        } catch (SQLException e) {
            Log.error("Something wrong during adding bank account", e);
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

    private void setBankAccountValues(BankAccount bankAccount, PreparedStatement prStatement) throws SQLException {
        Log.info("Setting bankAccount values started");
        prStatement.setInt(1, bankAccount.getCurrencyId());
        prStatement.setDouble(2, bankAccount.getAmountOfMoney());
        prStatement.setInt(3, bankAccount.getBankId());
        prStatement.setInt(4, bankAccount.getClientId());
    }

}
