package com.home.bankApplication.repositories;

import com.home.bankApplication.connection.ConnectionPoolProvider;
import com.home.bankApplication.exceptions.EntitySavingException;
import com.home.bankApplication.models.BankAccount;
import com.home.bankApplication.models.BankClient;
import com.home.bankApplication.models.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddClientRepository {

    private static final Logger Log = LoggerFactory.getLogger(AddClientRepository.class);
    private static final String insertClientSQL = "insert into clients (name, surname, status_id) values (?, ?, ?)";
    private static final String updateClientSQL = "update clients set name = ?, surname = ?, status_id = ? where id = ?";
    private static final String insertBankClientSQL = "insert into bank_clients (bank_id, client_id) values (?, ?)";
    private static final String insertBankAccountSQL = "insert into bank_accounts (currency_id, amount_of_money, bank_id, client_id) values (?, ?, ?, ?)";

    private static AddClientRepository instance;

    public AddClientRepository() {
        instance = this;
    }

    public static synchronized AddClientRepository getInstance() {
        if (instance == null) {
            instance = new AddClientRepository();
        }
        return instance;
    }

    public void addClientWithBankAccount(String clientName, String clientSurname, Integer clientStatusId, Integer currencyId, Integer bankId, Double amountOfMoney){
        Log.info("Adding new client");
        PreparedStatement prStatement = null;
        Connection connection = null;
        try {
            connection = ConnectionPoolProvider.getConnection();
            connection.setSavepoint();
            connection.setAutoCommit(false);

            Client client = createClient(clientName, clientSurname, clientStatusId);
            if (client.getId()==0) {
                prStatement = connection.prepareStatement(insertClientSQL, prStatement.RETURN_GENERATED_KEYS);
            } else {
                prStatement = connection.prepareStatement(updateClientSQL, prStatement.RETURN_GENERATED_KEYS);
            }
            setClientValues(client, prStatement);
            if (client.getId() != 0) {
                prStatement.setInt(4, client.getId());
            }
            int result = prStatement.executeUpdate();
            if (result != 1) {
                throw new EntitySavingException("Bank was not added!");
            }
            ResultSet generatedKey = prStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                client.setId(generatedKey.getInt(1));
            }

            BankClient bankClient = createBankClient(bankId, client);
            prStatement = connection.prepareStatement(insertBankClientSQL);
            setBankClientValues(bankClient, prStatement);
            int secondResult = prStatement.executeUpdate();
            if (secondResult!=1){
                throw new EntitySavingException("Bank client was not added");
            }

            BankAccount bankAccount = createBankAccount(currencyId, bankId, amountOfMoney, client);
            prStatement = connection.prepareStatement(insertBankAccountSQL);
            setBankAccountValues(bankAccount, prStatement);
            int thirdResult = prStatement.executeUpdate();
            if (thirdResult!=1){
                throw new EntitySavingException("Bank account was not added");
            }
            connection.commit();
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

    private BankAccount createBankAccount(Integer currencyId, Integer bankId, Double amountOfMoney, Client client) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setCurrencyId(currencyId);
        bankAccount.setAmountOfMoney(amountOfMoney);
        bankAccount.setBankId(bankId);
        bankAccount.setClientId(client.getId());
        return bankAccount;
    }

    private BankClient createBankClient(Integer bankId, Client client) {
        BankClient bankClient = new BankClient();
        bankClient.setBankId(bankId);
        bankClient.setClientId(client.getId());
        return bankClient;
    }

    private Client createClient(String clientName, String clientSurname, Integer clientStatusId) {
        Client client = new Client();
        client.setName(clientName);
        client.setSurname(clientSurname);
        client.setStatusId(clientStatusId);
        return client;
    }

    private void setClientValues(Client client, PreparedStatement prStatement) throws SQLException {
        Log.info("Setting client values started");
        prStatement.setString(1, client.getName());
        prStatement.setString(2, client.getSurname());
        prStatement.setInt(3, client.getStatusId());
    }

    private void setBankClientValues(BankClient bankClient, PreparedStatement prStatement) throws SQLException {
        Log.info("Setting bank client values started");
        prStatement.setInt(1, bankClient.getBankId());
        prStatement.setInt(2, bankClient.getClientId());
    }

    private void setBankAccountValues(BankAccount bankAccount, PreparedStatement prStatement) throws SQLException {
        Log.info("Setting bank account values started");
        prStatement.setInt(1, bankAccount.getCurrencyId());
        prStatement.setDouble(2, bankAccount.getAmountOfMoney());
        prStatement.setInt(3, bankAccount.getBankId());
        prStatement.setInt(4, bankAccount.getClientId());
    }

}
