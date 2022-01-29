package com.home.bankApplication.repositories.mappers;

import com.home.bankApplication.models.ClientStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientStatusMapper implements MapperToObject<ClientStatus> {

    private static final Logger Log = LoggerFactory.getLogger(ClientStatusMapper.class);

    @Override
    public ClientStatus toObject(ResultSet resultSet) throws SQLException {
        ClientStatus clientStatus = new ClientStatus();
        clientStatus.setId(resultSet.getInt("id"));
        clientStatus.setName(resultSet.getString("name"));
        return clientStatus;
    }
}