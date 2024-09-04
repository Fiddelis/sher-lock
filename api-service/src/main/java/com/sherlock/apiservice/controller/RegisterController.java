package com.sherlock.apiservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sherlock.apiservice.model.Client;
import com.sherlock.apiservice.model.Product;
import com.sherlock.apiservice.model.RegisterDTO;
import com.sherlock.apiservice.producer.MailProducer;
import com.sherlock.apiservice.service.ClientService;
import com.sherlock.apiservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @Autowired
    private MailProducer mailProducer;

    @PostMapping
    public ResponseEntity<Object> createClientAndProduct(@RequestBody RegisterDTO registerDTO) {
        Client client = clientService.createClient(registerDTO.client);

        registerDTO.product.setClientId(client.getId());
        Product product = productService.setProduct(registerDTO.product);

        registerDTO.client = client;
        registerDTO.product = product;

        try {
            mailProducer.send(registerDTO);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }

        return ResponseEntity.ok(registerDTO);
    }
}

