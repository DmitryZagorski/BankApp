package com.home.bankApplication.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankService {

    private static final Logger Log  = LoggerFactory.getLogger(BankService.class);

    private static BankService instance;

    private BankService(){

        instance = this;
    }

    public static BankService getInstance(){
        if (instance==null){
            instance = new BankService();
        }
        return instance;
    }



}
