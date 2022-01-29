package com.home.bankApplication.repositories;

import com.home.bankApplication.models.BankAccount;
import com.home.bankApplication.repositories.mappers.BankAccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BankAccountRepository extends AbstractCRUDRepository<BankAccount> {

    private static final Logger Log = LoggerFactory.getLogger(BankAccountRepository.class);

    private static BankAccountRepository instance;

    public BankAccountRepository() {
        super(new BankAccountMapper(), "bank_accounts");
        instance = this;
    }

    public static synchronized BankAccountRepository getInstance(){
        if (instance==null){
            instance = new BankAccountRepository();
        }
        return instance;
    }

    @Override
    public BankAccount getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public List<BankAccount> findAll() {
        return super.findAll();
    }

    @Override
    public List<BankAccount> findAllSorted(String fieldName, Integer limit, Integer offset) {
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
