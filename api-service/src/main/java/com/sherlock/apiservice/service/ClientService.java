package com.sherlock.apiservice.service;

import com.sherlock.apiservice.model.Client;
import com.sherlock.apiservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Client getClientByID(Integer id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client createClient(Client client) {
        if(client == null) {
            return null;
        }

        return clientRepository.save(client);
    }

    public Client updateClient(Client updatedClient) {
        Client client = clientRepository.findById(updatedClient.getId()).orElse(null);

        if(client != null) {
            if(updatedClient.getAddress() != null)
                client.setAddress(updatedClient.getAddress());
            if(updatedClient.getMail() != null)
                client.setMail(updatedClient.getMail());
            if(updatedClient.getName() != null)
                client.setName(updatedClient.getName());
            if(updatedClient.getPhoneNumber() != null)
                client.setPhoneNumber(updatedClient.getPhoneNumber());

            return clientRepository.save(client);
        }
        return null;
    }

    public Boolean deleteClient(Integer id) {
        if(clientRepository.existsById(id))  {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
