package com.home.bankApplication.services;

import com.home.bankApplication.models.BankAccount;
import com.home.bankApplication.models.BankClient;
import com.home.bankApplication.repositories.BankAccountRepository;
import com.home.bankApplication.repositories.BankClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BankAccountService {

    private static final Logger Log = LoggerFactory.getLogger(BankAccountService.class);

    private static BankAccountService instance;

    private BankAccountService() {

        instance = this;
    }

    public static BankAccountService getInstance() {
        if (instance == null) {
            instance = new BankAccountService();
        }
        return instance;
    }

    public List<BankAccount> findBankAccountsByClientId(Integer clientId){
        return BankAccountRepository.getInstance().findAccountsOfClient(clientId);
    }


}
