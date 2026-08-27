package com.miguelmendezz.mise.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;

@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String celebrantName;
    @PositiveOrZero
    private int guestCount;
    private LocalDateTime date;

    public Reservation() {
    }

    public Reservation(String celebrantName, int guestCount, LocalDateTime date) {
        this.celebrantName = celebrantName;
        this.guestCount = guestCount;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getCelebrantName() {
        return celebrantName;
    }

    public void setCelebrantName(String celebrantName) {
        this.celebrantName = celebrantName;
    }

    public int getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
