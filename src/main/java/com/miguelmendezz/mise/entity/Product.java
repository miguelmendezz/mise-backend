package com.miguelmendezz.mise.entity;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Supplier supplier;
    private String name;
    private String category;
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
