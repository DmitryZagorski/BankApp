package com.home.bankApplication.services;

import com.home.bankApplication.models.Currency;
import com.home.bankApplication.repositories.CurrencyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CurrencyService {

    private static final Logger Log = LoggerFactory.getLogger(CurrencyService.class);

    private static CurrencyService instance;

    private CurrencyService() {

        instance = this;
    }

    public static CurrencyService getInstance() {
        if (instance == null) {
            instance = new CurrencyService();
        }
        return instance;
    }

    public List<Currency> findAllCurrency(){
        return CurrencyRepository.getInstance().findAll();
    }

    public Integer getCurrencyIdByAccountId(Integer accountId){
        return CurrencyRepository.getInstance().getCurrencyIdOfBankAccountByAccountId(accountId);
    }

    public String getCurrencyNameById(Integer currencyId){
        return CurrencyRepository.getInstance().getCurrencyNameById(currencyId);
    }

}
