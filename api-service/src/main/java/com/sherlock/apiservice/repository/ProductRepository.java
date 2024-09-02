package com.sherlock.apiservice.repository;

import com.sherlock.apiservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
