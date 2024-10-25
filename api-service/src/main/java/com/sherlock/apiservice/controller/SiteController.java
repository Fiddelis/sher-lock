package com.sherlock.apiservice.controller;

import com.google.zxing.WriterException;
import com.sherlock.apiservice.model.Client;
import com.sherlock.apiservice.model.Product;
import com.sherlock.apiservice.service.ClientService;
import com.sherlock.apiservice.service.ProductService;
import com.sherlock.apiservice.service.SiteService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
public class SiteController {
    SiteService siteService;
    ProductService productService;
    ClientService clientService;

    @Autowired
    public SiteController(SiteService siteService, ProductService productService, ClientService clientService) {
        this.siteService = siteService;
        this.productService = productService;
        this.clientService = clientService;
    }

    @GetMapping
    public String home(Model model) throws IOException, WriterException {
        List<Product> products = productService.findAllByLockerIdAndWithdrawnDateIsNull(1);

        if (products.isEmpty()) {
            throw new RuntimeException("Nenhum produto encontrado.");
        }
        Client client = clientService.findClientByID(products.getFirst().getClientId());

        // Gerar QR codes como byte array
        byte[] qrCodeDelivery = siteService.generateQRCodeImage(products.getFirst().getDeliveryCode(), 200, 200);
        byte[] qrCodeWithdrawn = siteService.generateQRCodeImage(products.getFirst().getWithdrawnCode(), 200, 200);

        // Codificar em Base64
        String base64Delivery = Base64.getEncoder().encodeToString(qrCodeDelivery);
        String base64Withdrawn = Base64.getEncoder().encodeToString(qrCodeWithdrawn);

        model.addAttribute("product", products.getFirst());
        model.addAttribute("client", client);
        model.addAttribute("delivery", base64Delivery);
        model.addAttribute("withdrawn", base64Withdrawn);

        return "mail";
    }
}
