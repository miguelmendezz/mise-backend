package com.miguelmendezz.mise.service;

import com.miguelmendezz.mise.entity.Product;
import com.miguelmendezz.mise.entity.StockMovement;
import com.miguelmendezz.mise.entity.enums.MovementReason;
import com.miguelmendezz.mise.entity.enums.MovementType;
import com.miguelmendezz.mise.repository.ProductRepository;
import com.miguelmendezz.mise.repository.StockMovementRepository;
import org.springframework.stereotype.Service;

@Service
public class StockMovementService {

    private final ProductRepository productRepository;
    private final StockMovementRepository stockMovementRepository;

    public StockMovementService(ProductRepository productRepository, StockMovementRepository stockMovementRepository) {
        this.productRepository = productRepository;
        this.stockMovementRepository = stockMovementRepository;
    }

    public void registerSale(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        if (product.getStockQuantity() >= quantity) {
            product.setStockQuantity(product.getStockQuantity() - quantity);
        } else {
            throw new IllegalStateException("Insufficient stock quantity!");
        }

        productRepository.save(product);

        StockMovement movement = new StockMovement(product, quantity, MovementType.OUT, MovementReason.SALE);
        stockMovementRepository.save(movement);
    }
}
