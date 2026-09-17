package com.miguelmendezz.mise.controller;

import com.miguelmendezz.mise.entity.Product;
import com.miguelmendezz.mise.entity.StockMovement;
import com.miguelmendezz.mise.repository.ProductRepository;
import com.miguelmendezz.mise.service.StockMovementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final StockMovementService stockMovementService;

    public ProductController(ProductRepository productRepository, StockMovementService stockMovementService) {
        this.productRepository = productRepository;
        this.stockMovementService = stockMovementService;
    }

    @GetMapping
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @Valid @RequestBody Product updatedProduct) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        product.setName(updatedProduct.getName());
        product.setCategory(updatedProduct.getCategory());
        product.setStockQuantity(updatedProduct.getStockQuantity());

        return productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
    }

    @PostMapping("/{id}/sell")
    public ResponseEntity<StockMovement> sell(@PathVariable Long id, @RequestParam int quantity) {
        StockMovement registeredSale = stockMovementService.registerSale(id, quantity);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredSale);
    }
}
