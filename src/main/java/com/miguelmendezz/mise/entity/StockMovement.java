package com.miguelmendezz.mise.entity;

import com.miguelmendezz.mise.entity.enums.MovementReason;
import com.miguelmendezz.mise.entity.enums.MovementType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

@Entity
public class StockMovement {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Employee employee;
    @ManyToOne
    private Reservation reservation;
    @ManyToOne
    private Performance performance;
    @Positive
    private int quantity;
    @Enumerated(EnumType.STRING)
    private MovementType type;
    @Enumerated(EnumType.STRING)
    private MovementReason reason;
    private LocalDateTime timestamp;

    public StockMovement() {
    }

    public StockMovement(Product product, int quantity, MovementType type, MovementReason reason) {
        this.product = product;
        this.quantity = quantity;
        this.type = type;
        this.reason = reason;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public MovementType getType() {
        return type;
    }

    public void setType(MovementType type) {
        this.type = type;
    }

    public MovementReason getReason() {
        return reason;
    }

    public void setReason(MovementReason reason) {
        this.reason = reason;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
