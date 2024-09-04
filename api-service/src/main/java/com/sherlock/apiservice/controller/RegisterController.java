package com.sherlock.apiservice.controller;

import com.sherlock.apiservice.model.MailDTO;
import com.sherlock.apiservice.model.Product;
import com.sherlock.apiservice.service.ClientService;
import com.sherlock.apiservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class FormController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;


}

