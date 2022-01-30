package com.home.bankApplication.services;

import com.home.bankApplication.models.Bank;
import com.home.bankApplication.repositories.BankRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BankService {

    private static final Logger Log = LoggerFactory.getLogger(BankService.class);

    private static BankService instance;

    private BankService() {
        instance = this;
    }

    public static BankService getInstance() {
        if (instance == null) {
            instance = new BankService();
        }
        return instance;
    }

    public Bank addBank(String name, Double commissionForIndividual, Double commissionForEntity) {
        Log.info("Adding new bank");
        Bank bank = new Bank();
        bank.setName(name);
        bank.setCommissionForIndividual(commissionForIndividual);
        bank.setCommissionForEntity(commissionForEntity);
        return BankRepository.getInstance().addBank(bank);
    }

    public Bank getById(Integer id) {
        Log.info("Getting bank by id");
        return BankRepository.getInstance().getById(id);
    }

    public List<Bank> findAllBanks() {
        Log.info("Getting all banks");
        return BankRepository.getInstance().findAll();
    }

    public void removeById(Integer id) {
        Log.info("Removing bank by id");
        BankRepository.getInstance().removeById(id);
    }

    public void removeAll() {
        Log.info("Removing all banks");
        BankRepository.getInstance().removeAll();
    }
}
