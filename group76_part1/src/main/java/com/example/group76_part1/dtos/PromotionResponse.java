package com.example.group76_part1.dtos;

public record PromotionResponse(
        Long employeeId,
        String employeeName,
        String oldRank,
        String newRank,
        Double oldSalary,
        Double newSalary,
        Double increaseAmount,
        String message
) {
}
