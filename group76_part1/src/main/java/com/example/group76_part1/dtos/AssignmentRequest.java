package com.example.group76_part1.dtos;

import jakarta.validation.constraints.NotNull;

// DTO used for creating or updating an Assignment

public class AssignmentRequest {

    // ID of the employee to be assigned
    // Value must not be null
    @NotNull(message = "Employee id must not be null")
    private Long employeeId;

    // ID of the department where the employee will be assigned
    // Must not be null
    @NotNull(message = "Department id must not be null")
    private Long departmentId;

    private String role; // role of the employeee within the department
    private String accessLevel; // access level granted to the employee within the department

    public Long getEmployeeId() {
        return employeeId;
    } // Gets the employee ID and returns the value

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    } // Sets the employee ID value

    public Long getDepartmentId() {
        return departmentId;
    } // Gets the department ID and returns the value

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    } // Sets the department ID

    public String getRole() {
        return role;
    } // Gets the assignment role and returns it

    public void setRole(String role) {
        this.role = role;
    } // Sets the role of the assignment

    public String getAccessLevel() {
        return accessLevel;
    } // Gets the access level of the assignment and returns it

    public void setAccessLevel(String accessLevel) {this.accessLevel = accessLevel;} //  Sets the access level of the assignment
}