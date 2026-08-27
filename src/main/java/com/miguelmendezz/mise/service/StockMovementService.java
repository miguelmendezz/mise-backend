package com.miguelmendezz.mise.service;

import com.miguelmendezz.mise.entity.*;
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

    private void decreaseStock(Product product, int quantity) {

        if (product.getStockQuantity() >= quantity) {
            product.setStockQuantity(product.getStockQuantity() - quantity);
        } else {
            throw new IllegalStateException("Insufficient stock quantity ");
        }

        productRepository.save(product);
    }

    public void registerSale(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        decreaseStock(product, quantity);

        StockMovement movement = new StockMovement(product, quantity, MovementType.OUT, MovementReason.SALE);
        stockMovementRepository.save(movement);
    }

    public void registerCourtesy(Long productId, int quantity, MovementReason reason,
                                 Employee employee, Reservation reservation, Performance performance) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        decreaseStock(product, quantity);

        StockMovement movement = new StockMovement(product, quantity, MovementType.OUT, reason);
        if (employee != null) {
            movement.setEmployee(employee);
        }
        if (reservation != null) {
            movement.setReservation(reservation);
        }
        if (performance != null) {
            movement.setPerformance(performance);
        }
        stockMovementRepository.save(movement);
    }
}
