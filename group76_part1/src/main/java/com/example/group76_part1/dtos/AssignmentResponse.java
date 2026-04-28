package com.example.group76_part1.dtos;

// DTO used to return Assignment data to the client

public record AssignmentResponse(
        Long id, // ID for assignment
        Long employeeId, // ID for employee
        String employeeName, // Name of the employee
        Long departmentId, // ID for department
        String departmentName, // name of Department
        String role, // role of employee
        String accessLevel // Access level granted to the employee
) {
}