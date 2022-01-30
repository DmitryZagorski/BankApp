package com.home.bankApplication.repositories;

import com.home.bankApplication.connection.ConnectionPoolProvider;
import com.home.bankApplication.exceptions.EntityRetrievalException;
import com.home.bankApplication.exceptions.EntitySavingException;
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

    public List<BankClient> findClientsOfBank(Integer bankId) {
        String findClientsOfBankById = "select bank_clients.id, clients.name, clients.surname from bank_clients inner join clients on bank_clients.client_id = clients.id where bank_id = ".concat(String.valueOf(bankId));
        Connection connection;
        try {
            connection = ConnectionPoolProvider.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(findClientsOfBankById);
            List<BankClient> bankClients = new ArrayList<>();
            while (resultSet.next()) {
                BankClient bankClient = new BankClient();
                bankClient.setId(resultSet.getInt("id"));
                bankClient.setClientName(resultSet.getString("name"));
                bankClient.setClientSurname(resultSet.getString("surname"));
                bankClients.add(bankClient);
            }
            return bankClients;
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval entity ", e);
            throw new EntityRetrievalException(e);
        }
    }

    public List<BankClient> findAllBankClientsWithJoin() {
        Log.info("Getting bank clients with string status started");
        String viewClients = "select bank_clients.client_id, clients.name, clients.surname, banks.bank_name from bank_clients inner join clients on bank_clients.client_id = clients.id inner join banks on bank_clients.bank_id = banks.id";
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(viewClients);
            List<BankClient> bankClients = new ArrayList<>();
            while(resultSet.next()){
                BankClient bankClient = new BankClient();
                bankClient.setClientId(resultSet.getInt("client_id"));
                bankClient.setClientName(resultSet.getString("name"));
                bankClient.setClientSurname(resultSet.getString("surname"));
                bankClient.setBankName(resultSet.getString("bank_name"));
                bankClients.add(bankClient);
            }
            return bankClients;
        } catch (SQLException e) {
            Log.error("Error during getting bank clients", e);
            throw new EntityRetrievalException(e);
        }
    }

//
//    String getBookByTitle = "select books.title, books.author, books.issue_date, genres.title from books inner join genres on books.genre_id = genres.id where title = '".concat(title).concat("'");
//        try (Connection connection = ConnectionPoolProvider.getConnection();
//    Statement statement = connection.createStatement()) {
//        ResultSet resultSet = statement.executeQuery(getBookByTitle);
//        Book book = new Book();
//        if (resultSet.next()) {
//            book.setId(resultSet.getInt("id"));
//            book.setTitle(resultSet.getString("title"));
//            book.setAuthor(resultSet.getString("author"));
//            book.setGenreId(resultSet.getInt("genre_id"));
//        }
//        return book;
//    } catch (SQLException e) {
//        Log.error("Some error during getting book by title=" + title, e);
//        throw new BookNotFoundException(title, e);
//    }


}
