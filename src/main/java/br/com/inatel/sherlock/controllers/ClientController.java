package br.com.inatel.sherlock.controllers;

import br.com.inatel.sherlock.models.Client;
import br.com.inatel.sherlock.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/client")
    public Optional<Client> getClient(int id) {
        return clientService.getById(id);
    }

    @PostMapping("/client")
    public Client setClient(@RequestBody Client client) { return clientService.save(client); }
}
