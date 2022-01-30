package com.home.bankApplication.services;

import com.home.bankApplication.models.BankClient;
import com.home.bankApplication.models.Client;
import com.home.bankApplication.repositories.BankClientRepository;
import com.home.bankApplication.repositories.ClientRepository;
import com.home.bankApplication.repositories.RemoveClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ClientService {

    private static final Logger Log = LoggerFactory.getLogger(ClientService.class);

    private static ClientService instance;

    private ClientService() {

        instance = this;
    }

    public static ClientService getInstance() {
        if (instance == null) {
            instance = new ClientService();
        }
        return instance;
    }

//    public Client addClient(String name, String surname, Integer statusId) {
//        Log.info("Adding new client");
//        Client client = new Client();
//        client.setName(name);
//        client.setSurname(surname);
//        client.setStatusId(statusId);
//        return ClientRepository.getInstance().addClient(client);
//    }

    public Client getById(Integer id) {
        Log.info("Getting client by id");
        return ClientRepository.getInstance().getById(id);
    }

    public List<Client> findAllClients() {
        Log.info("Getting all clients");
        return ClientRepository.getInstance().findAll();
    }

    public void removeById(Integer id) {
        Log.info("Removing client by id");
        ClientRepository.getInstance().removeById(id);
    }

    public void removeAll() {
        Log.info("Removing all clients");
        RemoveClientRepository.getInstance().removeAllClients();
    }

    public void removeClientFromAllTablesById(Integer clientId){
        RemoveClientRepository.getInstance().removeClientFromAllTablesById(clientId);
    }



}
