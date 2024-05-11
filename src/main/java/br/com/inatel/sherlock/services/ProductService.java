package br.com.inatel.sherlock.services;

import br.com.inatel.sherlock.models.Product;
import br.com.inatel.sherlock.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        product.setPassCode(UUID.randomUUID().toString());
        return productRepository.save(product);
    }
}
