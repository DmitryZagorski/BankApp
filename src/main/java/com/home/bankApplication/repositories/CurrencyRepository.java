package com.home.bankApplication.repositories;

import com.home.bankApplication.models.Currency;
import com.home.bankApplication.repositories.mappers.CurrencyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CurrencyRepository extends AbstractCRUDRepository<Currency> {

    private static final Logger Log = LoggerFactory.getLogger(CurrencyRepository.class);

    private static CurrencyRepository instance;

    public CurrencyRepository() {
        super(new CurrencyMapper(), "currency");
        instance = this;
    }

    public static synchronized CurrencyRepository getInstance() {
        if (instance == null) {
            instance = new CurrencyRepository();
        }
        return instance;
    }

    @Override
    public Currency getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public List<Currency> findAll() {
        return super.findAll();
    }

    @Override
    public List<Currency> findAllSorted(String fieldName, Integer limit, Integer offset) {
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
