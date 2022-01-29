package com.home.bankApplication.services;

import com.home.bankApplication.models.BankClient;
import com.home.bankApplication.repositories.BankClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BankClientService {

    private static final Logger Log = LoggerFactory.getLogger(BankClientService.class);

    private static BankClientService instance;

    private BankClientService() {

        instance = this;
    }

    public static BankClientService getInstance() {
        if (instance == null) {
            instance = new BankClientService();
        }
        return instance;
    }

    public List<BankClient> findClientsOfBankByBankId(Integer bankId){
        return BankClientRepository.getInstance().findClientsOfBank(bankId);
    }




}
