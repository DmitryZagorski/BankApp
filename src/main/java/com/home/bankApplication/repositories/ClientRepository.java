package com.home.bankApplication.repositories;

import com.home.bankApplication.connection.ConnectionPoolProvider;
import com.home.bankApplication.exceptions.EntityRetrievalException;
import com.home.bankApplication.exceptions.EntitySavingException;
import com.home.bankApplication.models.Bank;
import com.home.bankApplication.models.BankAccount;
import com.home.bankApplication.models.Client;
import com.home.bankApplication.models.Currency;
import com.home.bankApplication.repositories.mappers.ClientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

public class ClientRepository extends AbstractCRUDRepository<Client> {

    private static final Logger Log = LoggerFactory.getLogger(ClientRepository.class);
    private static final String insertClientSQL = "insert into clients (name, surname, status_id) values (?, ?, ?)";
    private static final String updateClientSQL = "update clients set name = ?, surname = ?, status_id = ? where id = ?";

    private static ClientRepository instance;

    public ClientRepository() {
        super(new ClientMapper(), "clients");
        instance = this;
    }

    public static synchronized ClientRepository getInstance() {
        if (instance == null) {
            instance = new ClientRepository();
        }
        return instance;
    }

    @Override
    public Client getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public List<Client> findAll() {
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

    public Client addClient(Client client) {
        Log.info("Adding new client");
        PreparedStatement prStatement = null;
        Connection connection = null;
        try {
            connection = ConnectionPoolProvider.getConnection();
            connection.setSavepoint();
            connection.setAutoCommit(false);
            if (client.getId() == 0) {
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
                throw new EntitySavingException("Client was not added!");
            }
            ResultSet generatedKey = prStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                client.setId(generatedKey.getInt(1));
            }
            return client;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Log.error("Error during rollback");
            }
            Log.error("Something wrong during adding client", e);
            throw new EntitySavingException(e);
        } finally {
            try {
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

    public Integer getClientIdByAccountId(Integer bankAccountId){
        Log.info("Getting clientId by bank accountId");
        String getClientId = "select client_id from bank_accounts where id =".concat(String.valueOf(bankAccountId));
        Connection connection;
        try {
            connection = ConnectionPoolProvider.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getClientId);
            BankAccount bankAccount = new BankAccount();
            if (resultSet.next()) {
                bankAccount.setClientId(resultSet.getInt("client_id"));
            }
            return bankAccount.getClientId();
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval entity ", e);
            throw new EntityRetrievalException(e);
        }
    }

    public Client getClientById(Integer clientId){
        Log.info("Getting client by clientId");
        String getClient = "select * from clients where id =".concat(String.valueOf(clientId));
        Connection connection;
        try {
            connection = ConnectionPoolProvider.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getClient);
            Client client = new Client();
            if (resultSet.next()) {
                client.setName(resultSet.getString("name"));
                client.setSurname(resultSet.getString("surname"));
            }
            return client;
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval entity ", e);
            throw new EntityRetrievalException(e);
        }
    }

    private void setClientValues(Client client, PreparedStatement prStatement) throws SQLException {
        Log.info("Setting client values started");
        prStatement.setString(1, client.getName());
        prStatement.setString(2, client.getSurname());
        prStatement.setInt(3, client.getStatusId());
    }
}
