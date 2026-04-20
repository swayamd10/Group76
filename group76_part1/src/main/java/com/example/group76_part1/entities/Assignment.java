package com.example.group76_part1.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name="assignments")
public class Assignment {

    // Set GenerationType to Identity so that SpringBoot automatically instantiates an ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Role must not be null or blank")
    private String role;

    @NotBlank(message = "Access level must not be null or blank")
    private String accessLevel;

    // Creating an M:1 relationship between Assignment and Department.
    // Using lazy loading to reduce database transactions, only when required
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    // Creating an M:1 relationship between Assignment and Employee.
    // Using lazy loading to reduce database transactions, only when required
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getAccessLevel() { return accessLevel; }
    public void setAccessLevel(String accessLevel) { this.accessLevel = accessLevel; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }
}
