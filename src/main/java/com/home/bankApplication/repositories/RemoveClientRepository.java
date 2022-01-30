package com.home.bankApplication.repositories;

import com.home.bankApplication.connection.ConnectionPoolProvider;
import com.home.bankApplication.exceptions.EntitySavingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class RemoveClientRepository {

    private static final Logger Log = LoggerFactory.getLogger(RemoveClientRepository.class);

    private static RemoveClientRepository instance;

    public RemoveClientRepository() {
        instance = this;
    }

    public static synchronized RemoveClientRepository getInstance() {
        if (instance == null) {
            instance = new RemoveClientRepository();
        }
        return instance;
    }

    public void removeClientFromAllTablesById(Integer id) {
        Log.info("Removing client");
        String removeClientFromClients = "delete from clients where id =".concat(String.valueOf(id));
        String removeClientFromBankClients = "delete from bank_clients where client_id=".concat(String.valueOf(id));
        String removeClientFromBankAccounts = "delete from bank_accounts where client_id=".concat(String.valueOf(id));
        PreparedStatement prStatement = null;
        Connection connection = null;
        try {
            connection = ConnectionPoolProvider.getConnection();
            connection.setSavepoint();
            connection.setAutoCommit(false);

            prStatement = connection.prepareStatement(removeClientFromBankAccounts);
            int thirdResult = prStatement.executeUpdate();
            if (thirdResult != 1) {
                throw new EntitySavingException("Client was not removed from 'bank_accounts'!");
            }

            prStatement = connection.prepareStatement(removeClientFromBankClients);
            int secondResult = prStatement.executeUpdate();
            if (secondResult != 1) {
                throw new EntitySavingException("Client was not removed from 'bank_clients'!");
            }

            prStatement = connection.prepareStatement(removeClientFromClients);
            int result = prStatement.executeUpdate();
            if (result != 1) {
                throw new EntitySavingException("Client was not removed from 'clients'!");
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

    public void removeAllClients() {
        Log.info("Removing clients");
        String removeClientFromClients = "delete from clients";
        String removeClientFromBankClients = "delete from bank_clients";
        String removeClientFromBankAccounts = "delete from bank_accounts";
        Statement prStatement = null;
        Connection connection = null;
        try {
            connection = ConnectionPoolProvider.getConnection();
            connection.setSavepoint();
            connection.setAutoCommit(false);

            prStatement = connection.createStatement();
            prStatement.executeUpdate(removeClientFromBankClients);
            prStatement = connection.createStatement();
            prStatement.executeUpdate(removeClientFromBankAccounts);
            prStatement = connection.createStatement();
            prStatement.executeUpdate(removeClientFromClients);

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

}
