package com.example.group76_part1.dtos;

public record DepartmentResponse(
        Long id,
        String name,
        Double budget,
        String location
) {
}