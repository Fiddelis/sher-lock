package com.sherlock.apiservice.controller;

import com.sherlock.apiservice.model.Client;
import com.sherlock.apiservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/client")
public class ClientController {

    ClientService clientService;
    Client client;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getClient(@PathVariable Integer id) {
        client = clientService.getClientByID(id);

        if (client == null) {
            Map<String, String> errorResponse = new HashMap<>();

            errorResponse.put("error", "Client not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping
    public  ResponseEntity<Object> createClient(@RequestBody Client client) {
        client = clientService.setClient()
    }
}
