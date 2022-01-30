package com.home.bankApplication.services;

import com.home.bankApplication.models.ClientStatus;
import com.home.bankApplication.repositories.ClientStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ClientStatusService {

    private static final Logger Log = LoggerFactory.getLogger(ClientStatusService.class);

    private static ClientStatusService instance;

    private ClientStatusService() {
        instance = this;
    }

    public static ClientStatusService getInstance() {
        if (instance == null) {
            instance = new ClientStatusService();
        }
        return instance;
    }

    public List<ClientStatus> findAllStatuses() {
        Log.info("Getting all statuses");
        return ClientStatusRepository.getInstance().findAll();
    }
}
