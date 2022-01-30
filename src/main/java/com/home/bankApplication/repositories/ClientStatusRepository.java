package com.home.bankApplication.repositories;

import com.home.bankApplication.models.ClientStatus;
import com.home.bankApplication.repositories.mappers.ClientStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ClientStatusRepository extends AbstractCRUDRepository<ClientStatus> {

    private static final Logger Log = LoggerFactory.getLogger(ClientStatusRepository.class);

    private static ClientStatusRepository instance;

    public ClientStatusRepository() {
        super(new ClientStatusMapper(), "client_status");
        instance = this;
    }

    public static synchronized ClientStatusRepository getInstance() {
        if (instance == null) {
            instance = new ClientStatusRepository();
        }
        return instance;
    }

    @Override
    public ClientStatus getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public List<ClientStatus> findAll() {
        return super.findAll();
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
