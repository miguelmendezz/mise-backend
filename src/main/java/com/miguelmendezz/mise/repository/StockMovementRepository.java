package com.miguelmendezz.mise.repository;

import com.miguelmendezz.mise.entity.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {

}
