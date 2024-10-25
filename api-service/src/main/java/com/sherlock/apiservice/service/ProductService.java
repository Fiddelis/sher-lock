package com.sherlock.apiservice.service;

import com.sherlock.apiservice.model.Product;
import com.sherlock.apiservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findProductByID(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findAllByLockerIdAndWithdrawnDateIsNull(Integer lockerID) {
        return productRepository.findAllByLockerIdAndWithdrawnDateIsNull(lockerID);
    }

    public Product setProduct(Product product) {
        product.setDeliveryCode(UUID.randomUUID().toString());
        product.setWithdrawnCode(UUID.randomUUID().toString());
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
            if(updatedProduct.getDeliveryCode() != null)
                product.setDeliveryCode(updatedProduct.getDeliveryCode());
            if(updatedProduct.getWithdrawnCode() != null)
                product.setWithdrawnCode(updatedProduct.getWithdrawnCode());
            if(updatedProduct.getEstimatedDate() != null)
                product.setEstimatedDate(updatedProduct.getEstimatedDate());
            if(updatedProduct.getInsertedDate() != null)
                product.setInsertedDate(updatedProduct.getInsertedDate());
            if(updatedProduct.getWithdrawnDate() != null)
                product.setWithdrawnDate(updatedProduct.getWithdrawnDate());
            if(updatedProduct.getOpeningDate() != null)
                product.setOpeningDate(updatedProduct.getOpeningDate());
            if(updatedProduct.getClosingDate() != null)
                product.setClosingDate(updatedProduct.getClosingDate());

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
