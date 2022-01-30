package com.home.bankApplication.repositories;

import com.home.bankApplication.connection.ConnectionPoolProvider;
import com.home.bankApplication.exceptions.EntityRetrievalException;
import com.home.bankApplication.exceptions.EntitySavingException;
import com.home.bankApplication.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class AddTransactionRepository {

    private static final Logger Log = LoggerFactory.getLogger(AddTransactionRepository.class);
    private static final String insertIntoTransactionSQL = "insert into transactions (client_id, sender_bank_account_id, recipient_bank_account_id, currency_id, amount_of_money, creation_date) values (?,?,?,?,?,?)";
    private static final String updateTransactionSQL = "update transactions set client_id = ?, sender_bank_account_id = ?, recipient_bank_account_id = ?, currency_id = ?, amount_of_money = ?, creation_date = ? where id = ?";

    private static AddTransactionRepository instance;

    public AddTransactionRepository() {
        instance = this;
    }

    public static synchronized AddTransactionRepository getInstance() {
        if (instance == null) {
            instance = new AddTransactionRepository();
        }
        return instance;
    }

    public Transaction addTransaction(Integer clientId, Integer senderAccountId, Integer recipientAccountId, Integer currencyId, Double amountOfMoney, Date creationDate) {
        Log.info("Adding transaction");
        PreparedStatement prStatement = null;
        Connection connection = null;
        try {
            connection = ConnectionPoolProvider.getConnection();
            connection.setSavepoint();
            connection.setAutoCommit(false);

            Transaction transaction = createTransaction(clientId, senderAccountId, recipientAccountId, currencyId, amountOfMoney, creationDate);
            if (transaction.getId() == 0) {
                prStatement = connection.prepareStatement(insertIntoTransactionSQL, prStatement.RETURN_GENERATED_KEYS);
            } else {
                prStatement = connection.prepareStatement(updateTransactionSQL, prStatement.RETURN_GENERATED_KEYS);
            }
            setTransactionValues(transaction, prStatement);
            if (transaction.getId() != 0) {
                prStatement.setInt(4, transaction.getId());
            }
            int result = prStatement.executeUpdate();
            if (result != 1) {
                throw new EntitySavingException("Transaction was not added!");
            }
            ResultSet generatedKey = prStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                transaction.setId(generatedKey.getInt(1));
            }

            Integer clientStatusId = getStatusIdByClientId(clientId, connection);
            Double commission = commissionController(senderAccountId, recipientAccountId, clientStatusId, connection);
            Double amountOfCommission = commission*amountOfMoney;

            Double moneyOfSender = BankAccountRepository.getInstance().getAmountOfMoneyByBankAccountId(senderAccountId);
            Double moneyOfRecipient = BankAccountRepository.getInstance().getAmountOfMoneyByBankAccountId(recipientAccountId);
            Integer currencyIdOfRecipient = CurrencyRepository.getInstance().getCurrencyIdOfBankAccountByAccountId(recipientAccountId);

            String updateSenderAccount = "update bank_accounts set amount_of_money = ".concat(String.valueOf(moneyOfSender - amountOfMoney-amountOfCommission)) + " where id = ".concat(String.valueOf(senderAccountId));
            prStatement = connection.prepareStatement(updateSenderAccount);
            int secondResult = prStatement.executeUpdate();
            if (secondResult!=1){
                throw new EntitySavingException("Sender account was not updated");
            }

            Double receivedMoney = currencyConverter(currencyId, currencyIdOfRecipient, amountOfMoney, connection);
            String updateRecipientMoney = "update bank_accounts set amount_of_money = ".concat(String.valueOf(moneyOfRecipient + receivedMoney)) + " where id = ".concat(String.valueOf(recipientAccountId));
            prStatement = connection.prepareStatement(updateRecipientMoney);
            int thirdResult = prStatement.executeUpdate();
            if (thirdResult!=1){
                throw new EntitySavingException("Recipient account was not updated");
            }

            connection.commit();
            return transaction;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Log.error("Error during rollback");
            }
            Log.error("Something wrong during adding transaction", e);
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

    private Transaction createTransaction(Integer clientId, Integer senderAccountId, Integer recipientAccountId, Integer currencyId, Double amountOfMoney, Date creationDate) {
        Log.info("Creation of transaction");
        Transaction transaction = new Transaction();
        transaction.setClientId(clientId);
        transaction.setSenderBankAccountId(senderAccountId);
        transaction.setRecipientBankAccountId(recipientAccountId);
        transaction.setCurrencyId(currencyId);
        transaction.setAmountOfMoney(amountOfMoney);
        transaction.setCreationDate(creationDate);
        return transaction;
    }

    private void setTransactionValues(Transaction transaction, PreparedStatement prStatement) throws SQLException {
        Log.info("Setting transaction values started");
        prStatement.setInt(1, transaction.getClientId());
        prStatement.setInt(2, transaction.getSenderBankAccountId());
        prStatement.setInt(3, transaction.getRecipientBankAccountId());
        prStatement.setInt(4, transaction.getCurrencyId());
        prStatement.setDouble(5, transaction.getAmountOfMoney());
        prStatement.setDate(6, transaction.getCreationDate());
    }

    private Double currencyConverter(Integer senderCurrencyId, Integer recipientCurrencyId, Double amountOfMoney, Connection connection) throws SQLException {
        Log.info("Converter started");
        Double senderRate = getRateOfCurrency(senderCurrencyId, connection);
        Double recipientRate = getRateOfCurrency(recipientCurrencyId, connection);
        return senderRate / recipientRate * amountOfMoney;
    }

    private Double commissionController(Integer senderAccountId, Integer recipientAccountId, Integer clientStatusId, Connection connection) {
        Log.info("Commission controller started");
        Integer senderBankId = getBankIdByBankAccountId(senderAccountId, connection);
        Integer recipientBankId = getBankIdByBankAccountId(recipientAccountId, connection);
        Double commission = 0.0;
        if (senderBankId.equals(recipientBankId)) {
            return commission;
        } else {
            commission = getCommissionOfBankByBankAccountId(recipientAccountId, clientStatusId, connection);
        }
        return commission;
    }

    private Integer getBankIdByBankAccountId(Integer bankAccountId, Connection connection) {
        Log.info("Getting bank id by Account id");
        String getBank = "select bank_id from bank_accounts where id =".concat(String.valueOf(bankAccountId));
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getBank);
            BankAccount bankAccount = new BankAccount();
            if (resultSet.next()) {
                bankAccount.setBankId(resultSet.getInt("bank_id"));
            }
            return bankAccount.getBankId();
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval entity ", e);
            throw new EntityRetrievalException(e);
        }
    }

    private Double getCommissionOfBankByBankAccountId(Integer bankAccountId, Integer statusId, Connection connection) {
        Log.info("Getting commission of bank by bank account");
        String getCommissionForIndividual = "select commission_for_individual from banks where id =".concat(String.valueOf(bankAccountId));
        String getCommissionForEntity = "select commission_for_entity from banks where id =".concat(String.valueOf(bankAccountId));
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            Double commission = null;
            Bank bank = new Bank();
            if (statusId == 1) {
                resultSet = statement.executeQuery(getCommissionForIndividual);
                if (resultSet.next()) {
                    bank.setCommissionForIndividual(resultSet.getDouble("commission_for_individual"));
                    commission = bank.getCommissionForIndividual();
                }
            }
            else {
                resultSet = statement.executeQuery(getCommissionForEntity);
                if (resultSet.next()) {
                    bank.setCommissionForEntity(resultSet.getDouble("commission_for_entity"));
                    commission = bank.getCommissionForEntity();
                }
            }
            return commission;
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval entity ", e);
            throw new EntityRetrievalException(e);
        }
    }

    private Integer getStatusIdByClientId(Integer clientId, Connection connection) {
        Log.info("Getting status of client by clientId");
        String getStatusId = "select status_id from clients where id =".concat(String.valueOf(clientId));
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getStatusId);
            Client client = new Client();
            if (resultSet.next()) {
                client.setStatusId(resultSet.getInt("status_id"));
            }
            return client.getStatusId();
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval entity ", e);
            throw new EntityRetrievalException(e);
        }
    }

    private Double getRateOfCurrency(Integer currencyId, Connection connection) {
        Log.info("Getting rate of currency");
        String getRateOfCurrency = "select rate from currency where id =".concat(String.valueOf(currencyId));
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getRateOfCurrency);
            Currency currency = new Currency();
            if (resultSet.next()) {
                currency.setRate(resultSet.getDouble("rate"));
            }
            return currency.getRate();
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval entity ", e);
            throw new EntityRetrievalException(e);
        }
    }
}
