package com.home.bankApplication.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerifyService {

    private static final Logger Log = LoggerFactory.getLogger(VerifyService.class);

    private static VerifyService instance;

    private VerifyService() {
        instance = this;
    }

    public static VerifyService getInstance() {
        if (instance == null) {
            instance = new VerifyService();
        }
        return instance;
    }

    public Boolean verifyIfIDoubleDigitAboveZero(Double digit){
        boolean isAboveZero = true;
        if (digit <= 0){
            isAboveZero = false;
        }
        return isAboveZero;
    }
}
