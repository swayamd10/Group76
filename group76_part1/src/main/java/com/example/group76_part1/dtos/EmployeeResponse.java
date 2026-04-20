package com.example.group76_part1.dtos;

import java.time.LocalDate;

public record EmployeeResponse(
        Long id,
        String name,
        String contractType,
        LocalDate startDate,
        Double salary,
        String email,
        String address,
        String rank
) {
}