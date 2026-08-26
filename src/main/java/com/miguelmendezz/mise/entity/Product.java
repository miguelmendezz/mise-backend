package com.miguelmendezz.mise.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @ManyToOne
    private Supplier supplier;
    @NotBlank
    private String name;
    @NotBlank
    private String category;
    @PositiveOrZero
    private int stockQuantity;

    public Product() {
    }

    public Product(Supplier supplier, String name, String category, int stockQuantity) {
        this.supplier = supplier;
        this.name = name;
        this.category = category;
        this.stockQuantity = stockQuantity;
    }

    public Long getId() {
        return id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

}
