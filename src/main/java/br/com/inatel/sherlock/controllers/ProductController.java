package br.com.inatel.sherlock.controllers;

import br.com.inatel.sherlock.DTO.ClientProductDTO;
import br.com.inatel.sherlock.models.Client;
import br.com.inatel.sherlock.models.Product;
import br.com.inatel.sherlock.services.ClientService;
import br.com.inatel.sherlock.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @PostMapping("/register")
    public @ResponseBody ResponseEntity<Product> registerClientAndProductInDrawer(@RequestBody ClientProductDTO clientProductDTO) {

        Client client = clientProductDTO.getClient();
        Product product = clientProductDTO.getProduct();

        clientService.save(client);

        if (clientService.getById(client.getId()).isPresent()) {
            product.setClientId(client.getId());
            productService.save(product);
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }


}
