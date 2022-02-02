package com.home.bankApplication.services;

import com.home.bankApplication.repositories.AddClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddClientService {

    private static final Logger Log = LoggerFactory.getLogger(AddClientService.class);

    private static AddClientService instance;

    private AddClientService() {
        instance = this;
    }

    public static AddClientService getInstance() {
        if (instance == null) {
            instance = new AddClientService();
        }
        return instance;
    }

    public void addClientWithBankAccount(String clientName, String clientSurname, Integer clientStatusId, Integer currencyId, Integer bankId, Double amountOfMoney) {
        Log.info("Adding client with bank account");
        AddClientRepository.getInstance().addClientWithBankAccount(clientName, clientSurname, clientStatusId, currencyId, bankId, amountOfMoney);
    }
}
