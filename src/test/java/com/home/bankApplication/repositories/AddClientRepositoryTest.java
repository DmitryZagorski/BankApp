package com.home.bankApplication.repositories;

import com.home.bankApplication.connection.ConnectionPoolProvider;

import org.apache.commons.io.IOUtils;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.sql.Statement;

class AddClientRepositoryTest {


    @Before
    public void init() throws SQLException, IOException {
        Statement createStatement = ConnectionPoolProvider.getConnection().createStatement();
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("db-init.sql");
        String string = IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8);
        createStatement.executeUpdate(string);
    }

//    @Test
//    void should_add_new_client_with_bank_account() {
//
//        AddClientRepository instance = AddClientRepository.getInstance();
//
//        Client client = new Client();
//        client.setName("Boris");
//        client.setSurname("Jonse");
//        client.setStatusId(1);

       // instance.addClientWithBankAccount("Boris", "Jonse", 1, );


//    }
}