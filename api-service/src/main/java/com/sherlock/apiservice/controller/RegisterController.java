package com.sherlock.apiservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sherlock.apiservice.model.*;
import com.sherlock.apiservice.producer.MailProducer;
import com.sherlock.apiservice.service.ClientService;
import com.sherlock.apiservice.service.LockerService;
import com.sherlock.apiservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/register")
@CrossOrigin(origins = "*")
public class RegisterController {

    private final ClientService clientService;
    private final ProductService productService;
    private final LockerService lockerService;
    private final MailProducer mailProducer;

    @Autowired
    public RegisterController(ClientService clientService, ProductService productService, LockerService lockerService, MailProducer mailProducer) {
        this.clientService = clientService;
        this.productService = productService;
        this.lockerService = lockerService;
        this.mailProducer = mailProducer;
    }

    @PostMapping
    public ResponseEntity<Object> createClientAndProduct(@RequestBody RegisterDTO registerDTO) {
        Client client = clientService.createClient(registerDTO.client);

        registerDTO.product.setClientId(client.getId());
        Product product = productService.setProduct(registerDTO.product);

        Locker locker = lockerService.findLockerByID(product.getLockerId());
        product.setAddress(locker.getAddress());

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

