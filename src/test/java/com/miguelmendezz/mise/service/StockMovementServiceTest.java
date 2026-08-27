package com.miguelmendezz.mise.service;

import com.miguelmendezz.mise.entity.Product;
import com.miguelmendezz.mise.entity.StockMovement;
import com.miguelmendezz.mise.repository.ProductRepository;
import com.miguelmendezz.mise.repository.StockMovementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StockMovementServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private StockMovementRepository stockMovementRepository;
    @InjectMocks
    private StockMovementService stockMovementService;

    @Test
    void shouldDecreaseStockWhenSaleIsValid() {

        Long productId = 1L;
        Product product = new Product(null, "Canelinha", "Dose", 10);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        stockMovementService.registerSale(productId, 5);

        assertEquals(5, product.getStockQuantity());

        verify(productRepository).save(product);
        verify(stockMovementRepository).save(any(StockMovement.class));
    }

    @Test
    void shouldThrowExceptionWhenStockIsInsufficient() {

        Long productId = 1L;
        Product product = new Product(null, "Canelinha", "Dose", 5);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        assertThrows(IllegalStateException.class, () -> {
            stockMovementService.registerSale(productId, 10);
        });

        verify(productRepository, never()).save(any());
        verify(stockMovementRepository, never()).save(any());

    }
}