package br.com.inatel.sherlock.repository;

import br.com.inatel.sherlock.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
