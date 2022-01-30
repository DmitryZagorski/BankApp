package com.home.bankApplication.repositories;

import com.home.bankApplication.connection.ConnectionPoolProvider;
import com.home.bankApplication.models.Bank;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

class BankRepositoryTest {

//    @Before
//    public void init() throws SQLException, IOException {
//        Statement createStatement = ConnectionPoolProvider.getConnection().createStatement();
//        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("db-init.sql");
//        String string = new BufferedReader(
//                new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8))
//                .lines()
//                .collect(Collectors.joining("\n"));
//        createStatement.executeUpdate(string);
//    }
//
//    @Test
//    void should_get_bank_by_id() {
//
//
//    }
//
//    @Test
//    void findAll() {
//    }
//
//    @Test
//    void removeById() {
//    }
//
//    @Test
//    void removeAll() {
//    }
//
//    @Test
//    void should_add_bank() {
//        BankRepository instance = BankRepository.getInstance();
//
//        Bank bank = new Bank();
//        bank.setName("BestBank");
//        bank.setCommissionForIndividual(0.001);
//        bank.setCommissionForEntity(0.002);
//        List<Bank> firstCondition = instance.findAll();
//        int firstSize = firstCondition.size();
//
//        instance.addBank(bank);
//        List<Bank> secondCondition = instance.findAll();
//        int secondSize = secondCondition.size();
//
//        Assertions.assertEquals(secondSize, firstSize + 1);
//    }
}