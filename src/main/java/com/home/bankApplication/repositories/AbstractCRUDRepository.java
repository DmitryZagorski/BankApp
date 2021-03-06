package com.home.bankApplication.repositories;

import com.home.bankApplication.connection.ConnectionPoolProvider;
import com.home.bankApplication.exceptions.EntityRemoveException;
import com.home.bankApplication.exceptions.EntityRetrievalException;
import com.home.bankApplication.repositories.mappers.MapperToObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AbstractCRUDRepository<T> {

    private static final Logger Log = LoggerFactory.getLogger(AbstractCRUDRepository.class);

    private static final String selectById = "select * from %s where id = %d";
    private static final String selectAll = "select * from %s";
    private static final String removeById = "delete from %s where id = %d";
    private static final String removeAll = "delete from %s";

    private MapperToObject mapperToObject;
    private String tableName;

    public AbstractCRUDRepository(MapperToObject mapperToObject, String tableName) {
        this.mapperToObject = mapperToObject;
        this.tableName = tableName;
    }

    public T getById(Integer id) {
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(String.format(selectById, tableName, id))) {
            if (resultSet.next()) {
                T someObject = (T) mapperToObject.toObject(resultSet);
                return someObject;
            } else {
                return null;
            }
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval id " + id, e);
            throw new EntityRetrievalException();
        }
    }

    public List<T> findAll() {
        try (Connection connection = ConnectionPoolProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(String.format(selectAll, tableName))) {
            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                entities.add((T) mapperToObject.toObject(resultSet));
            }
            return entities;
        } catch (SQLException e) {
            Log.error("Something wrong during retrieval entity ", e);
            throw new EntityRetrievalException(e);
        }
    }

    public void removeById(Integer id) {
        try (Connection connection = ConnectionPoolProvider.getConnection()) {
            Statement statement = connection.createStatement();
            int removing = statement.executeUpdate(String.format(removeById, tableName, id));
            if (removing != 1) {
                Log.info("Entity with that Id doesn't exist.");
            }
        } catch (SQLException e) {
            Log.error("Something wrong during removing by id " + id, e);
            throw new EntityRemoveException(e);
        }
    }

    public void removeAll() {
        try (Connection connection = ConnectionPoolProvider.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(String.format(removeAll, tableName));
        } catch (SQLException e) {
            Log.error("Something wrong during removing all entities", e);
            throw new EntityRemoveException(e);
        }
    }
}
