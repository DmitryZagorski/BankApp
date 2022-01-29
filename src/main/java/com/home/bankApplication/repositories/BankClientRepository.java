package com.home.bankApplication.repositories;

import com.home.bankApplication.models.BankClient;
import com.home.bankApplication.repositories.mappers.BankClientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
}
