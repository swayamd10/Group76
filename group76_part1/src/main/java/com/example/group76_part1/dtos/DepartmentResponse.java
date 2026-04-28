package com.example.group76_part1.dtos;

// DTO used to return Department data to the client

public record DepartmentResponse(
        Long id, // ID of the department
        String name, // Name of the department
        Double budget, // Budget allocated to the department
        String location // Location of the department
) {
}