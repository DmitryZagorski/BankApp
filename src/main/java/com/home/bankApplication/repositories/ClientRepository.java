package com.home.bankApplication.repositories;

import com.home.bankApplication.models.Client;
import com.home.bankApplication.repositories.mappers.ClientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ClientRepository extends AbstractCRUDRepository<Client> {

    private static final Logger Log = LoggerFactory.getLogger(ClientRepository.class);

    private static ClientRepository instance;

    public ClientRepository() {
        super(new ClientMapper(), "clients");
        instance = this;
    }

    public static synchronized ClientRepository getInstance() {
        if (instance == null) {
            instance = new ClientRepository();
        }
        return instance;
    }

    @Override
    public Client getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public List<Client> findAll() {
        return super.findAll();
    }

    @Override
    public List<Client> findAllSorted(String fieldName, Integer limit, Integer offset) {
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
