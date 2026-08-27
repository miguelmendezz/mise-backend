package com.miguelmendezz.mise.repository;

import com.miguelmendezz.mise.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository <Reservation, Long> {
}
