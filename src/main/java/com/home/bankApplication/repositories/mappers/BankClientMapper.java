package com.home.bankApplication.repositories.mappers;

import com.home.bankApplication.models.BankClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankClientMapper implements MapperToObject<BankClient> {

    private static final Logger Log = LoggerFactory.getLogger(BankClientMapper.class);

    @Override
    public BankClient toObject(ResultSet resultSet) throws SQLException {
        Log.info("Mapping of bank client");
        BankClient bankClient = new BankClient();
        bankClient.setId(resultSet.getInt("id"));
        bankClient.setBankId(resultSet.getInt("bank_id"));
        bankClient.setClientId(resultSet.getInt("client_id"));
        return bankClient;
    }
}
