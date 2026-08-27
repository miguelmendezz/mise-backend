package com.miguelmendezz.mise.repository;

import com.miguelmendezz.mise.entity.Performance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceRepository extends JpaRepository <Performance, Long> {
}
