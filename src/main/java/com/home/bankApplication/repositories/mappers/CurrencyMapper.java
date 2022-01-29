package com.home.bankApplication.repositories.mappers;

import com.home.bankApplication.models.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrencyMapper implements MapperToObject<Currency> {

    private static final Logger Log = LoggerFactory.getLogger(CurrencyMapper.class);

    @Override
    public Currency toObject(ResultSet resultSet) throws SQLException {
        Currency currency = new Currency();
        currency.setId(resultSet.getInt("id"));
        currency.setName(resultSet.getString("name"));
        currency.setRate(resultSet.getDouble("rate"));
        return currency;
    }
}
