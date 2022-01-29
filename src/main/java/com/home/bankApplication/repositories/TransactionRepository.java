package com.home.bankApplication.repositories;

import com.home.bankApplication.models.Transaction;
import com.home.bankApplication.repositories.mappers.TransactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TransactionRepository extends AbstractCRUDRepository<Transaction> {

    private static final Logger Log = LoggerFactory.getLogger(TransactionRepository.class);

    private static TransactionRepository instance;

    public TransactionRepository() {
        super(new TransactionMapper(), "transactions");
        instance = this;
    }

    public static synchronized TransactionRepository getInstance() {
        if (instance == null) {
            instance = new TransactionRepository();
        }
        return instance;
    }

    @Override
    public Transaction getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public List<Transaction> findAll() {
        return super.findAll();
    }

    @Override
    public List<Transaction> findAllSorted(String fieldName, Integer limit, Integer offset) {
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
