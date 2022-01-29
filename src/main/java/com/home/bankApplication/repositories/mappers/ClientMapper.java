package com.home.bankApplication.repositories.mappers;

import com.home.bankApplication.models.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientMapper implements MapperToObject<Client> {

    private static final Logger Log = LoggerFactory.getLogger(ClientMapper.class);

    @Override
    public Client toObject(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setId(resultSet.getInt("id"));
        client.setName(resultSet.getString("name"));
        client.setSurname(resultSet.getString("surname"));
        client.setStatusId(resultSet.getInt("status_id"));
        return client;
    }
}
