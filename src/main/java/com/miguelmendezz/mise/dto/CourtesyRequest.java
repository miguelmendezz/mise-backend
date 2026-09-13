package com.miguelmendezz.mise.dto;

import com.miguelmendezz.mise.entity.enums.MovementReason;

public record CourtesyRequest(
        Long productId,
        int quantity,
        MovementReason reason,
        Long employeeId,
        Long reservationId,
        Long performanceId
) {}