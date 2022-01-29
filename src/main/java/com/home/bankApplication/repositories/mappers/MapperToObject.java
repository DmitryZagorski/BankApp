package com.home.bankApplication.repositories.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface MapperToObject<T> {
    T toObject(ResultSet resultSet) throws SQLException;
}
