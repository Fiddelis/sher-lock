package com.sherlock.apiservice.controller;

import com.sherlock.apiservice.model.Product;
import com.sherlock.apiservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


@RestController
@RequestMapping("/api/product")
public class ProductController {
    private static final Logger logger = Logger.getLogger(ProductController.class.getName());

    private final ProductService productService;
    private final Map<String, String> errorResponse = new HashMap<>();
    private Product product;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllProducts() {
        List<Product> products = productService.findAll();

        if(products.isEmpty()) {
            errorResponse.put("error", "products not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable Integer id) {
        product = productService.findProductByID(id);

        if (product == null) {
            errorResponse.put("error", "product not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        return ResponseEntity.ok(product);
    }

    @GetMapping("/by_locker/{lockerID}")
    public ResponseEntity<Object> getAllProductsByLockerID(@PathVariable Integer lockerID) {
        List<Product> products = productService.findAllByLockerIdAndWithdrawnDateIsNull(lockerID);

        if(products.isEmpty()) {
            errorResponse.put("error", "products not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        logger.info("GET by_locker:" + products.getFirst().getDeliveryCode());
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {

        if(product == null) {
            errorResponse.put("error", "missing information");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        this.product = productService.setProduct(product);

        return ResponseEntity.ok(this.product);
    }

    @PutMapping
    public ResponseEntity<Object> updateProduct(@RequestBody Product product) {
        this.product = productService.updateProduct(product);
        System.out.println(product);

        if(this.product == null) {
            errorResponse.put("error", "product not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        return ResponseEntity.ok(this.product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Integer id) {
        HashMap<String, String> successResponse = new HashMap<>();
        successResponse.put("message", "product successfully deleted");

        if(!productService.deleteProduct(id)) {
            errorResponse.put("error", "product was not deleted");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        return ResponseEntity.ok(successResponse);
    }
}
