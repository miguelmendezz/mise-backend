package com.miguelmendezz.mise.controller;

import com.miguelmendezz.mise.entity.Performance;
import com.miguelmendezz.mise.repository.PerformanceRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/performances")
public class PerformanceController {

    private final PerformanceRepository performanceRepository;

    public PerformanceController(PerformanceRepository performanceRepository) {
        this.performanceRepository = performanceRepository;
    }

    @GetMapping
    public List<Performance> getAll() {
        return performanceRepository.findAll();
    }

    @GetMapping("/{id}")
    public Performance getById(@PathVariable Long id) {
        return performanceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Performance not found"));
    }

    @PostMapping
    public Performance create(@Valid @RequestBody Performance performance) {
        return performanceRepository.save(performance);
    }

    @PutMapping("/{id}")
    public Performance update(@PathVariable Long id, @Valid @RequestBody Performance updatedPerformance) {
        Performance performance = performanceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Performance not found"));

        performance.setDate(updatedPerformance.getDate());
        performance.setArtistName(updatedPerformance.getArtistName());

        return performanceRepository.save(performance);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

        if (performanceRepository.existsById(id)) {
            performanceRepository.deleteById(id);
        }
    }
}
