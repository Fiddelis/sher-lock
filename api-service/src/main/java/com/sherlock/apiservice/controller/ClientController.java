package com.sherlock.apiservice.controller;

import com.sherlock.apiservice.model.Client;
import com.sherlock.apiservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    ClientService clientService;
    Map<String, String> errorResponse = new HashMap<>();
    Client client;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllClients() {
        List<Client> clients = clientService.getAll();

        if(clients.isEmpty()) {
            errorResponse.put("error", "clients not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getClient(@PathVariable Integer id) {
        client = clientService.getClientByID(id);

        if (client == null) {
            errorResponse.put("error", "client not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<Object> createClient(@RequestBody Client client) {

        if(client == null) {
            errorResponse.put("error", "missing information");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        this.client = clientService.createClient(client);

        return ResponseEntity.ok(this.client);
        }

    @PutMapping
    public ResponseEntity<Object> updateClient(@RequestBody Client client) {
        this.client = clientService.updateClient(client);

        if(this.client == null) {
            errorResponse.put("error", "client not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        return ResponseEntity.ok(this.client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable Integer id) {
        HashMap<String, String> successResponse = new HashMap<>();
        successResponse.put("message", "client successfully deleted");

        if(!clientService.deleteClient(id)) {
            errorResponse.put("error", "client was not deleted");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        return ResponseEntity.ok(successResponse);
    }
}
