package br.com.inatel.sherlock.services;

import br.com.inatel.sherlock.models.Client;
import br.com.inatel.sherlock.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Optional<Client> getById(Long id) {
        return clientRepository.findById(id);
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public Client update(Client clientUpdate) {

        Optional<Client> clientOptional = clientRepository.findById(clientUpdate.getId());

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();

            if (clientUpdate.getAddress() != null) client.setAddress(clientUpdate.getAddress());
            if (clientUpdate.getMail() != null) client.setMail(clientUpdate.getMail());
            if (clientUpdate.getPhone_number() != null) client.setPhone_number(clientUpdate.getPhone_number());
            if (clientUpdate.getName() != null) client.setName(clientUpdate.getName());

            return clientRepository.save(client);
        }
        return null;
    }

    public Boolean exists(Long id) {
        return clientRepository.existsById(id);
    }
}
