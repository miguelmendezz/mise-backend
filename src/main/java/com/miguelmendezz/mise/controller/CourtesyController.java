package com.miguelmendezz.mise.controller;

import com.miguelmendezz.mise.dto.CourtesyRequest;
import com.miguelmendezz.mise.entity.Employee;
import com.miguelmendezz.mise.entity.Performance;
import com.miguelmendezz.mise.entity.Reservation;
import com.miguelmendezz.mise.repository.EmployeeRepository;
import com.miguelmendezz.mise.repository.PerformanceRepository;
import com.miguelmendezz.mise.repository.ProductRepository;
import com.miguelmendezz.mise.repository.ReservationRepository;
import com.miguelmendezz.mise.service.StockMovementService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courtesies")
public class CourtesyController {

    private final StockMovementService stockMovementService;
    private final ProductRepository productRepository;
    private final EmployeeRepository employeeRepository;
    private final ReservationRepository reservationRepository;
    private final PerformanceRepository performanceRepository;

    public CourtesyController(StockMovementService stockMovementService, ProductRepository productRepository,
                              EmployeeRepository employeeRepository, ReservationRepository reservationRepository,
                              PerformanceRepository performanceRepository) {
        this.stockMovementService = stockMovementService;
        this.productRepository = productRepository;
        this.employeeRepository = employeeRepository;
        this.reservationRepository = reservationRepository;
        this.performanceRepository = performanceRepository;
    }

    @PostMapping
    public void registerCourtesy(@RequestBody CourtesyRequest request) {
        Employee employee = request.employeeId() != null
                ? employeeRepository.findById(request.employeeId()).orElseThrow()
                : null;

        Performance performance = request.performanceId() != null
                ? performanceRepository.findById(request.performanceId()).orElseThrow()
                : null;

        Reservation reservation = request.reservationId() != null
                ? reservationRepository.findById(request.reservationId()).orElseThrow()
                : null;

        stockMovementService.registerCourtesy(
                request.productId(), request.quantity(), request.reason(),
                employee, reservation, performance
        );
    }
}
