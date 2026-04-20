package com.example.group76_part1.dtos;

import jakarta.validation.constraints.NotNull;

public class AssignmentRequest {

    @NotNull(message = "Employee id must not be null")
    private Long employeeId;

    @NotNull(message = "Department id must not be null")
    private Long departmentId;

    private String role;
    private String accessLevel;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
}