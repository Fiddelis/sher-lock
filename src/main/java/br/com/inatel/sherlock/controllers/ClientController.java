package br.com.inatel.sherlock.controllers;

import br.com.inatel.sherlock.models.Client;
import br.com.inatel.sherlock.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/check/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Long id) {
        Optional<Client> clientOptional = clientService.getById(id);

        return clientOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Client> setClient(@RequestBody Client client) {
        clientService.save(client);

        if (clientService.exists(client.getId()))
            return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/update")
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        if (clientService.exists(client.getId())) {
            clientService.update(client);

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
