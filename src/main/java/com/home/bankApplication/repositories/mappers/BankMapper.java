package com.home.bankApplication.repositories.mappers;

import com.home.bankApplication.models.Bank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankMapper implements MapperToObject<Bank> {

    private static final Logger Log = LoggerFactory.getLogger(BankMapper.class);

    @Override
    public Bank toObject(ResultSet resultSet) throws SQLException {
        Log.info("Mapping of bank");
        Bank bank = new Bank();
        bank.setId(resultSet.getInt("id"));
        bank.setName(resultSet.getString("bank_name"));
        bank.setCommissionForIndividual(resultSet.getDouble("commission_for_individual"));
        bank.setCommissionForEntity(resultSet.getDouble("commission_for_entity"));
        return bank;
    }
}
