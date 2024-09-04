package com.sherlock.apiservice.repository;

import com.sherlock.apiservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByLockerIdAndWithdrawnDateIsNull(Integer lockerId);
}
