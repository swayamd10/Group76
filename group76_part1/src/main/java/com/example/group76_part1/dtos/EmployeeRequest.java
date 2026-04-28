package com.example.group76_part1.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

// DTO used to create or update an Employee

public class EmployeeRequest {

    // Name of the employee
    // Must not be null or blank
    @NotBlank(message = "Employee name must not be null or blank")
    private String name;

    // Contract type of the employee
    // Must not be null or blank
    @NotBlank(message = "Contract type must not be null or blank")
    private String contractType;

    // Start date of the employee
    // Must not be null
    @NotNull(message = "Start date must not be a null value")
    private LocalDate startDate;

    // Salary of the employee
    // Must not be null and the value must be positive value
    @Positive(message = "Salary must be a positive value")
    @NotNull(message = "Salary must not be a null value")
    private Double salary;

    // Email of the employee
    // Must be a valid email address and value must not be blank
    @Email(message = "Email must be a valid email address")
    @NotBlank(message = "Email must not be blank")
    private String email;

    // Address of the employee
    // Must not be blank
    @NotBlank(message = "Address must not be blank")
    private String address;

    //  rank of the employee
    private String rank;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}