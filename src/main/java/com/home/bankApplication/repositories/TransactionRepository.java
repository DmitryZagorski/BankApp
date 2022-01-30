package com.home.bankApplication.repositories;

import com.home.bankApplication.connection.ConnectionPoolProvider;
import com.home.bankApplication.exceptions.EntityRetrievalException;
import com.home.bankApplication.models.Client;
import com.home.bankApplication.models.Transaction;
import com.home.bankApplication.repositories.mappers.TransactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository extends AbstractCRUDRepository<Transaction> {

    private static final Logger Log = LoggerFactory.getLogger(TransactionRepository.class);

    private static TransactionRepository instance;

    public TransactionRepository() {
        super(new TransactionMapper(), "transactions");
        instance = this;
    }

    public static synchronized TransactionRepository getInstance() {
        if (instance == null) {
            instance = new TransactionRepository();
        }
        return instance;
    }

    @Override
    public Transaction getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public List<Transaction> findAll() {
        return super.findAll();
    }

    @Override
    public List<Transaction> findAllSorted(String fieldName, Integer limit, Integer offset) {
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

    public List<Transaction> findByClientAndDate(Integer clientId, Date creationDate){
        String getTransaction = "select transactions.id, clients.name, clients.surname, transactions.sender_bank_account_id, transactions.recipient_bank_account_id, currency.currency_name, transactions.amount_of_money, transactions.creation_date from transactions inner join clients on client_id = clients.id inner join currency on transactions.currency_id = currency.id where client_id =".concat(String.valueOf(clientId))+" and creation_date > ".concat(String.valueOf(creationDate));
        Connection connection;
        try {
            connection = ConnectionPoolProvider.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getTransaction);
            List<Transaction> transactions = new ArrayList<>();
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(resultSet.getInt("id"));
                transaction.setClientName(resultSet.getString("name"));
                transaction.setClientSurname(resultSet.getString("surname"));
                transaction.setSenderBankAccountId(resultSet.getInt("sender_bank_account_id"));
                transaction.setRecipientBankAccountId(resultSet.getInt("recipient_bank_account_id"));
                transaction.setCurrencyName(resultSet.getString("currency_name"));
                transaction.setAmountOfMoney(resultSet.getDouble("amount_of_money"));
                transaction.setCreationDate(resultSet.getDate("creation_date"));
                transactions.add(transaction);
            }
            return transactions;
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval entity ", e);
            throw new EntityRetrievalException(e);
        }
    }

}
