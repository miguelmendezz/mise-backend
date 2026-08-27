package com.miguelmendezz.mise.controller;

import com.miguelmendezz.mise.entity.Reservation;
import com.miguelmendezz.mise.repository.ReservationRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationRepository reservationRepository;

    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Reservation getById(@PathVariable Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
    }

    @PostMapping
    public Reservation create(@Valid @RequestBody Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @PutMapping("/{id}")
    public Reservation update(@PathVariable Long id, @Valid @RequestBody Reservation updatedReservation) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        reservation.setDate(updatedReservation.getDate());
        reservation.setCelebrantName(updatedReservation.getCelebrantName());
        reservation.setGuestCount(updatedReservation.getGuestCount());

        return reservationRepository.save(reservation);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
        }
    }
}
