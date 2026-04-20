package com.example.group76_part1.dtos;

public record AssignmentResponse(
        Long id,
        Long employeeId,
        String employeeName,
        Long departmentId,
        String departmentName,
        String role,
        String accessLevel
) {
}