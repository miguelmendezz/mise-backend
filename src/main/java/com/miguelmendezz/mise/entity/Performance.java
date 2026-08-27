package com.miguelmendezz.mise.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
public class Performance {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String artistName;
    private LocalDateTime date;

    public Performance() {
    }

    public Performance(String artistName, LocalDateTime date) {
        this.artistName = artistName;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
