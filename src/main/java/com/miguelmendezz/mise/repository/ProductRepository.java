package com.miguelmendezz.mise.repository;

import com.miguelmendezz.mise.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
