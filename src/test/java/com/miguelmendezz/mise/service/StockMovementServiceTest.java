package com.miguelmendezz.mise.service;

import com.miguelmendezz.mise.entity.*;
import com.miguelmendezz.mise.entity.enums.MovementReason;
import com.miguelmendezz.mise.repository.ProductRepository;
import com.miguelmendezz.mise.repository.StockMovementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
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

    @Test
    void shouldDecreaseStockWhenCourtesyIsValidWithoutOptionalBond() {

        Long productId = 1L;
        Product product = new Product(null, "Canelinha", "Dose", 5);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        stockMovementService.registerCourtesy(productId, 3, MovementReason.ARTIST_COURTESY, null, null, null);

        assertEquals(2, product.getStockQuantity());

        verify(productRepository).save(product);
        verify(stockMovementRepository).save(any(StockMovement.class));
    }

    @Test
    void shouldDecreaseStockWhenCourtesyIsValidForEmployee() {

        Long productId = 1L;
        Product product = new Product(null, "Pilsen", "Chopp", 5);
        Employee employee = new Employee("Miguel", true);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        ArgumentCaptor<StockMovement> captor = ArgumentCaptor.forClass(StockMovement.class);

        stockMovementService.registerCourtesy(productId, 2, MovementReason.EMPLOYEE_CLOSING_DRINK, employee, null, null);

        verify(stockMovementRepository).save(captor.capture());
        assertEquals(employee, captor.getValue().getEmployee());

        assertEquals(3, product.getStockQuantity());

        verify(productRepository).save(product);
    }

    @Test
    void shouldDecreaseStockWhenCourtesyIsValidForReservation() {

        Long productId = 1L;
        Product product = new Product(null, "Canelinha", "Dose", 25);
        Reservation reservation = new Reservation("Amanda", 10, LocalDateTime.of(2026, 9, 19, 20, 00));

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        ArgumentCaptor<StockMovement> captor = ArgumentCaptor.forClass(StockMovement.class);

        stockMovementService.registerCourtesy(productId, 10, MovementReason.BIRTHDAY_GUESTS_COURTESY, null, reservation, null);

        verify(stockMovementRepository).save(captor.capture());
        assertEquals(reservation, captor.getValue().getReservation());

        assertEquals(15, product.getStockQuantity());

        verify(productRepository).save(product);
    }

    @Test
    void shouldDecreaseStockWhenCourtesyIsValidForPerformance() {

        Long productId = 1L;
        Product product = new Product(null, "Porção de Fritas", "Porções", 25);
        Performance performance = new Performance("Zé Colmeia", LocalDateTime.of(2026, 9, 14, 17, 00));

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        ArgumentCaptor<StockMovement> captor = ArgumentCaptor.forClass(StockMovement.class);

        stockMovementService.registerCourtesy(productId, 1, MovementReason.ARTIST_COURTESY, null, null, performance);

        verify(stockMovementRepository).save(captor.capture());
        assertEquals(performance, captor.getValue().getPerformance());

        assertEquals(24, product.getStockQuantity());

        verify(productRepository).save(product);
    }

    @Test
    void shouldThrowExceptionWhenCourtesyStockIsInsufficient() {

        Long productId = 1L;
        Product product = new Product(null, "Canelinha", "Dose", 5);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        assertThrows(IllegalStateException.class, () -> {
            stockMovementService.registerCourtesy(productId, 12, MovementReason.EMPLOYEE_MEAL, null, null, null);
        });

        verify(productRepository, never()).save(any());
        verify(stockMovementRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenProductDoesNotExist() {

        Long productId = 1L;

        assertThrows(IllegalArgumentException.class, () -> {
           stockMovementService.registerCourtesy(productId, 4, MovementReason.ARTIST_COURTESY, null, null, null);
        });

        verify(productRepository, never()).save(any());
        verify(stockMovementRepository, never()).save(any());
    }
}