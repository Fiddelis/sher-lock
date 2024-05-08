package br.com.inatel.sherlock.controllers;

import br.com.inatel.sherlock.models.Client;
import br.com.inatel.sherlock.DTO.ClientProductDTO;
import br.com.inatel.sherlock.models.Product;
import br.com.inatel.sherlock.services.ClientService;
import br.com.inatel.sherlock.services.DrawerService;
import br.com.inatel.sherlock.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RegisterController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @Autowired
    private DrawerService drawerService;

    @PostMapping("/register")
    public String registerClientAndProductInDrawer(@RequestBody ClientProductDTO clientProductDTO) {

        Client client = clientProductDTO.getClient();
        Product product = clientProductDTO.getProduct();

        clientService.save(client);

        if(clientService.getById(client.getId()).isPresent()) {
            product.setClientId(client.getId());
            productService.save(product);
            return "201";
        }

        return null;
    }

}
