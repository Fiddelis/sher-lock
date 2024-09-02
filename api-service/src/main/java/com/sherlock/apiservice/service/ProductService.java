package com.sherlock.apiservice.service;

import com.sherlock.apiservice.model.Product;
import com.sherlock.apiservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {
    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductByID(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product setProduct(Product product) {
        product.setPassCode(UUID.randomUUID().toString());
        return productRepository.save(product);
    }

    public Product updateProduct(Product updatedProduct) {
        Product product = productRepository.findById(updatedProduct.getId()).orElse(null);

        if(product != null) {
            if(updatedProduct.getClientId() != null)
                product.setClientId(updatedProduct.getClientId());
            if(updatedProduct.getDrawerId() != null)
                product.setDrawerId(updatedProduct.getDrawerId());
            if(updatedProduct.getLockerId() != null)
                product.setLockerId(updatedProduct.getLockerId());
            if(updatedProduct.getQuantity() != null)
                product.setQuantity(updatedProduct.getQuantity());
            if(updatedProduct.getName() != null)
                product.setName(updatedProduct.getName());
            if(updatedProduct.getDimension() != null)
                product.setDimension(updatedProduct.getDimension());
            if(updatedProduct.getAddress() != null)
                product.setAddress(updatedProduct.getAddress());
            if(updatedProduct.getPassCode() != null)
                product.setPassCode(updatedProduct.getPassCode());
            if(updatedProduct.getEstimatedDate() != null)
                product.setEstimatedDate(updatedProduct.getEstimatedDate());

            return productRepository.save(product);
        }
        return null;
    }

    public Boolean deleteProduct(Integer id) {
        if(productRepository.existsById(id))  {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
