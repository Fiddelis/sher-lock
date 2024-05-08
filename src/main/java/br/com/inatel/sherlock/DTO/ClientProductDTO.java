package br.com.inatel.sherlock.DTO;

import br.com.inatel.sherlock.models.Client;
import br.com.inatel.sherlock.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientProductDTO {
    private Client client;
    private Product product;
}
